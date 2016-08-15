package io.purplejs.testing;

import java.util.List;

import io.purplejs.Engine;
import io.purplejs.EngineBuilder;
import io.purplejs.EngineModule;

public interface ScriptTestSuite
    extends EngineModule
{
    List<String> getTestFiles();

    void initialize( Engine engine );

    void configure( EngineBuilder builder );

    void setUp();

    void tearDown();
}