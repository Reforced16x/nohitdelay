package com.rnhd;

import net.fabricmc.api.ClientModInitializer;

/**
 * Main client entrypoint and shared runtime state for RNHD.
 */
public class RnhdMod implements ClientModInitializer {
    public static volatile boolean enabled = true;
    public static volatile int hitDelay = 0;

    @Override
    public void onInitializeClient() {
        OneConfigCompat.init();
    }

    /**
     * Returns the miss attack cooldown in ticks (clamped 0..10, or 10 when disabled).
     */
    public static int getEffectiveHitDelay() {
        if (!enabled) {
            return 10;
        }
        return Math.max(0, Math.min(10, hitDelay));
    }
}
