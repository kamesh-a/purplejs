package io.purplejs.testing.junit;

import java.util.List;

import org.junit.runner.RunWith;

import com.google.common.collect.Lists;

import io.purplejs.Engine;
import io.purplejs.EngineBinder;
import io.purplejs.EngineBuilder;

@RunWith(ScriptRunner.class)
public abstract class ScriptTestSupport
    implements ScriptTestSuite
{
    protected Engine engine;

    private final List<String> testFiles;

    public ScriptTestSupport()
    {
        this.testFiles = Lists.newArrayList();
    }

    @Override
    public final List<String> getTestFiles()
    {
        return this.testFiles;
    }

    protected final void addTestFile( final String file )
    {
        this.testFiles.add( file );
    }

    @Override
    public void initialize( final Engine engine )
    {
        this.engine = engine;
    }

    @Override
    public void configure( final EngineBuilder builder )
    {
        builder.classLoader( getClass().getClassLoader() );
        builder.module( this );
    }

    @Override
    public void setUp()
        throws Exception
    {
        // Do nothing
    }

    @Override
    public void tearDown()
        throws Exception
    {
        // Do nothing
    }

    @Override
    public void configure( final EngineBinder binder )
    {
        // Do nothing
    }
}
