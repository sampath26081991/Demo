package com.opencart.utils;

import java.lang.management.ManagementFactory;

/**
 * Small helper to optionally wait for a debugger to attach.
 * Usage:
 * - Add VM option: -DwaitForDebugger=true
 * - Or run normally and attach the IDE debugger to the running process.
 */
public class DebuggerUtil {

    /**
     * If system property `waitForDebugger` is true, wait up to timeoutSeconds for a debugger to attach.
     */
    public static void waitForDebuggerIfRequested(long timeoutSeconds) {
        String prop = System.getProperty("waitForDebugger", "false");
        if (!"true".equalsIgnoreCase(prop)) {
            return;
        }

        long start = System.currentTimeMillis();
        long timeoutMs = timeoutSeconds * 1000L;
        System.out.println("[DebuggerUtil] Waiting for debugger to attach (timeout " + timeoutSeconds + "s)...");

        while ((System.currentTimeMillis() - start) < timeoutMs) {
            if (isDebuggerAttached()) {
                System.out.println("[DebuggerUtil] Debugger attached.");
                return;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("[DebuggerUtil] Timeout waiting for debugger (" + timeoutSeconds + "s).");
    }

    /**
     * Heuristic: inspect JVM input arguments for jdwp/agentlib which IntelliJ uses when launching in debug.
     */
    public static boolean isDebuggerAttached() {
        try {
            for (String arg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
                if (arg != null && (arg.contains("jdwp") || arg.contains("agentlib:jdwp"))) {
                    return true;
                }
            }
        } catch (Throwable ignored) {
        }
        return false;
    }
}

