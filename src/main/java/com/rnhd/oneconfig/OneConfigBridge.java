package com.rnhd.oneconfig;

/**
 * Direct bridge between RNHD core and OneConfig v1 APIs.
 * Only loaded reflectively by {@link com.rnhd.OneConfigCompat} when OneConfig is present.
 */
public final class OneConfigBridge {
    private static RnhdOneConfig config;

    private OneConfigBridge() {
    }

    public static synchronized void init() {
        if (config != null) {
            return;
        }
        config = new RnhdOneConfig();
    }
}
