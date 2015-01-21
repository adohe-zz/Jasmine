package com.xqbase.tools.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class allows you to do some close task when
 * the process shutdown.
 *
 * @author Tony He
 */
public class ShutdownHookManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShutdownHookManager.class);

    private static final ShutdownHookManager MGR = new ShutdownHookManager();

    private Set<HookEntry> hooks = Collections.synchronizedSet(new HashSet<HookEntry>());

    private AtomicBoolean shutdownInProgress = new AtomicBoolean(false);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for (Runnable hook : MGR.getShutdownHooksInOrder()) {
                    try {
                        hook.run();
                    } catch (Throwable t) {
                        LOGGER.warn("ShutdownHook " + hook.getClass().getSimpleName() + " failed " + t.toString(), t);
                    }
                }
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

    /**
     * Adds a ShutDown hook to the hooks set, the higher the priority
     * the earlier it will run. ShutdownHooks with the same priority
     * run in a non-deterministic order.
     */
    public void addShutdownHook(Runnable hook, int priority) {
        if (hook == null) {
            throw new IllegalArgumentException("Hook can't be null");
        }
        if (shutdownInProgress.get()) {
            throw new IllegalStateException("Shutdown in progress, can't add shutdown hook");
        }

        hooks.add(new HookEntry(hook, priority));
    }

    /**
     * Returns the sorted list of ShutdownHook,
     * highest priority first.
     */
    private List<Runnable> getShutdownHooksInOrder() {
        List<HookEntry> list;
        synchronized (MGR.hooks) {
            list = new ArrayList<HookEntry>(MGR.hooks);
        }
        // Sorts the list
        Collections.sort(list, new Comparator<HookEntry>() {
            @Override
            public int compare(HookEntry o1, HookEntry o2) {
                return o1.priority - o2.priority;
            }
        });
        List<Runnable> ordered = new ArrayList<Runnable>();
        for (HookEntry hookEntry : list) {
            ordered.add(hookEntry.hook);
        }

        return ordered;
    }

    /**
     * Removes one hook from the hooks set.
     */
    public void removeShutdownHook(Runnable hook) {
        if (shutdownInProgress.get()) {
            throw new IllegalStateException("shutdown in progress, can't remove shutdown hook");
        }

        hooks.remove(new HookEntry(hook, 0));
    }

    /**
     * Returns whether the hooks set has the shutdown hook.
     */
    public boolean hasShutdownHook(Runnable hook) {
        return hooks.contains(new HookEntry(hook, 0));
    }

    /**
     * Indicates if shutdown is in progress.
     */
    public boolean isShutdownInProgress() {
        return shutdownInProgress.get();
    }
}
