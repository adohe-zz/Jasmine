package com.xqbase.gatekeeper.tcp.gate;

import java.io.File;

/**
 * Interface for generating class from source code.
 *
 * @author Tony He
 */
public interface DynamicCodeCompiler {

    Class compile(String code, String name) throws Exception;

    Class compile(File file) throws Exception;
}
