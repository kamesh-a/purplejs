package io.purplejs.core.internal.registry;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableMap;

import io.purplejs.core.registry.Registry;

final class RegistryImpl
    implements Registry
{
    private final ImmutableMap<Class, Supplier> map;

    RegistryImpl( final ImmutableMap<Class, Supplier> map )
    {
        this.map = map;
    }

    @Override
    public <T> T getInstance( final Class<T> type )
    {
        return getProvider( type ).get();
    }

    @Override
    public <T> T getInstanceOrNull( final Class<T> type )
    {
        final Supplier<T> binding = getBindingOrNull( type );
        return binding != null ? binding.get() : null;
    }

    @Override
    public <T> Supplier<T> getProvider( final Class<T> type )
    {
        return getBinding( type );
    }

    private <T> Supplier<T> getBinding( final Class<T> type )
    {
        final Supplier<T> binding = getBindingOrNull( type );
        if ( binding != null )
        {
            return binding;
        }

        throw new IllegalArgumentException( String.format( "No binding of type [%s]", type.getName() ) );
    }

    @SuppressWarnings("unchecked")
    private <T> Supplier<T> getBindingOrNull( final Class<T> type )
    {
        return (Supplier<T>) this.map.get( type );
    }
}
