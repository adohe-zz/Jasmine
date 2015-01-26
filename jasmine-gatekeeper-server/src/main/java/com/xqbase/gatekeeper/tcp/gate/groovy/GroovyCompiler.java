package com.xqbase.gatekeeper.tcp.gate.groovy;

import com.xqbase.gatekeeper.tcp.gate.DynamicCodeCompiler;

import java.io.File;

/**
 * This compiler compile the groovy source code to java class.
 *
 * @author Tony He
 */
public class GroovyCompiler implements DynamicCodeCompiler {

    @Override
    public Class compile(String code, String name) throws Exception {
        return null;
    }

    @Override
    public Class compile(File file) throws Exception {
        return null;
    }
}
