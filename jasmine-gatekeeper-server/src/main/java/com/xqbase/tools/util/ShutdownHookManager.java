package com.xqbase.tools.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class allows you to do some close task when
 * the process shutdown.
 *
 * @author Tony He
 */
public class ShutdownHookManager {

    private static final ShutdownHookManager MGR = new ShutdownHookManager();

    private Set<HookEntry> hooks = Collections.synchronizedSet(new HashSet<HookEntry>());

    private AtomicBoolean shutdownInProgress = new AtomicBoolean(false);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                super.run();
            }
        });
    }

    /**
     * Private constructor
     */
    private ShutdownHookManager() {

    }

    /**
     * Returns the <code>ShutdownHookManager</code>
     */
    public static ShutdownHookManager getInstance() {
        return MGR;
    }

    /**
     * Private structure to store ShutdownHook and its priority.
     */
    private static class HookEntry {
        Runnable hook;
        int priority;

        private HookEntry(Runnable hook, int priority) {
            this.hook = hook;
            this.priority = priority;
        }

        @Override
        public int hashCode() {
            return hook.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            boolean eq = false;
            if (obj != null) {
                if (obj instanceof HookEntry) {
                    eq = (hook == ((HookEntry) obj).hook);
                }
            }

            return eq;
        }
    }

    public void addShutdownHook(Runnable hook, int priority) {
        if (hook == null) {
            throw new IllegalArgumentException("Hook can't be null");
        }
        if (shutdownInProgress.get()) {
            throw new IllegalStateException("Shutdown in progress, can't add shutdown hook");
        }

        hooks.add(new HookEntry(hook, priority));
    }
}
