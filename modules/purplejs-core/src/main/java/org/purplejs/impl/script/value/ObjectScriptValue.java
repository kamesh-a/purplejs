package org.purplejs.impl.script.value;

import java.util.Map;
import java.util.Set;

import org.purplejs.impl.script.util.JsObjectConverter;
import org.purplejs.script.ScriptValue;

import jdk.nashorn.api.scripting.JSObject;

final class ObjectScriptValue
    extends AbstractScriptValue
{
    private final ScriptValueFactory factory;

    private final JSObject value;

    public ObjectScriptValue( final ScriptValueFactory factory, final JSObject value )
    {
        this.factory = factory;
        this.value = value;
    }

    @Override
    public boolean isObject()
    {
        return true;
    }

    @Override
    public Set<String> getKeys()
    {
        return this.value.keySet();
    }

    @Override
    public boolean hasMember( final String key )
    {
        return this.value.hasMember( key );
    }

    @Override
    public ScriptValue getMember( final String key )
    {
        return this.factory.newValue( this.value.getMember( key ) );
    }

    @Override
    public Map<String, Object> getMap()
    {
        return JsObjectConverter.toMap( this.value );
    }
}
