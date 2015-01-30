package com.xqbase.gatekeeper.tcp.gate.groovy;

import com.xqbase.gatekeeper.tcp.gate.DynamicCodeCompiler;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * This compiler compile the groovy source code to java class.
 *
 * @author Tony He
 */
public class GroovyCompiler implements DynamicCodeCompiler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyCompiler.class);

    @Override
    public Class compile(String code, String name) throws Exception {
        GroovyClassLoader classLoader = getClassLoader();
        LOGGER.warn("Compiling filter: " + name);
        Class groovyClass = classLoader.parseClass(code, name);
        return groovyClass;
    }

    @Override
    public Class compile(File file) throws Exception {
        GroovyClassLoader classLoader = getClassLoader();
        LOGGER.warn("Compiling filter: " + file.getName());
        Class groovyClass = classLoader.parseClass(file);
        return groovyClass;
    }

    private GroovyClassLoader getClassLoader() {
        return new GroovyClassLoader();
    }
}
