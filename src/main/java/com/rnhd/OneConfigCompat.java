package com.rnhd;

import java.lang.reflect.Method;

/**
 * Pure-reflection compatibility facade for optional OneConfig v1 integration.
 * Deliberately holds zero direct references to org.polyfrost.* classes so RNHD
 * loads and runs cleanly even when OneConfig is absent.
 */
public final class OneConfigCompat {
    private static boolean initialized = false;
    private static boolean available = false;

    private OneConfigCompat() {
    }

    public static synchronized void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        try {
            Class.forName(
                    "org.polyfrost.oneconfig.api.config.v1.Config",
                    false,
                    OneConfigCompat.class.getClassLoader()
            );
            Class<?> bridgeClass = Class.forName("com.rnhd.oneconfig.OneConfigBridge");
            Method initMethod = bridgeClass.getMethod("init");
            initMethod.invoke(null);
            available = true;
        } catch (Throwable ignored) {
            available = false;
        }
    }

    public static boolean isAvailable() {
        init();
        return available;
    }
}
