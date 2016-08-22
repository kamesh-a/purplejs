package io.purplejs.http.internal.error

import com.google.common.net.MediaType
import io.purplejs.core.exception.ProblemException
import io.purplejs.core.mock.MockResourceLoader
import io.purplejs.core.resource.ResourcePath
import io.purplejs.http.Status
import io.purplejs.http.mock.MockRequest
import spock.lang.Specification

class ErrorRendererImplTest
    extends Specification
{
    def ErrorRendererImpl renderer;

    def MockResourceLoader resourceLoader;

    def MockRequest request;

    def setup()
    {
        this.resourceLoader = new MockResourceLoader();
        this.renderer = new ErrorRendererImpl( null, this.resourceLoader );
        this.request = new MockRequest();
    }

    def "handle exception"()
    {
        when:
        def res = this.renderer.handle( this.request, new IOException() );

        then:
        res != null;
        res.status == Status.INTERNAL_SERVER_ERROR;
        res.contentType == MediaType.JSON_UTF_8;
        res.body.size() > 0;
    }

    def "handle status"()
    {
        when:
        def res = this.renderer.handle( this.request, Status.NOT_FOUND );

        then:
        res != null;
        res.status == Status.NOT_FOUND;
        res.contentType == MediaType.JSON_UTF_8;
        res.body.size() > 0;
    }

    def "handle problem exception"()
    {
        setup:
        def problem = ProblemException.newBuilder().
            lineNumber( 1 ).
            callLine( 'test1', 1 ).
            callLine( 'test2', 2 ).
            path( ResourcePath.from( '/test.js' ) ).
            build();

        this.resourceLoader.addResource( '/test.js', '''
            1
            2
            3
        ''' );

        when:
        def res = this.renderer.handle( this.request, problem );

        then:
        res != null;
        res.status == Status.INTERNAL_SERVER_ERROR;
        res.contentType == MediaType.JSON_UTF_8;
        res.body.size() > 0;
    }

    def "handle problem exception, resource not found"()
    {
        setup:
        def problem = ProblemException.newBuilder().
            lineNumber( 1 ).
            callLine( 'test1', 1 ).
            callLine( 'test2', 2 ).
            path( ResourcePath.from( '/test.js' ) ).
            build();

        when:
        def res = this.renderer.handle( this.request, problem );

        then:
        res != null;
        res.status == Status.INTERNAL_SERVER_ERROR;
        res.contentType == MediaType.JSON_UTF_8;
        res.body.size() > 0;
    }
}