package org.purplejs.impl.resource;

import org.purplejs.resource.Resource;
import org.purplejs.resource.ResourceLoader;
import org.purplejs.resource.ResourcePath;

final class ChainedResourceLoader
    implements ResourceLoader
{
    private final ResourceLoader loader;

    private final ResourceLoader next;

    public ChainedResourceLoader( final ResourceLoader loader, final ResourceLoader next )
    {
        this.loader = loader;
        this.next = next;
    }

    @Override
    public Resource loadOrNull( final ResourcePath path )
    {
        final Resource resource = this.loader.loadOrNull( path );
        if ( resource != null )
        {
            return resource;
        }

        return this.next.loadOrNull( path );
    }
}
