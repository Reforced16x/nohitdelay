package com.rnhd.oneconfig;

import com.rnhd.RnhdMod;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.Tree;
import org.polyfrost.oneconfig.api.config.v1.annotations.Slider;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

/**
 * Native OneConfig v1 configuration profile for RNHD.
 */
public final class RnhdOneConfig extends Config {
    @Switch(
        title = "Enabled",
        description = "Enable custom/removed miss hit delay.",
        category = "General",
        subcategory = "No Hit Delay"
    )
    public boolean enabled = true;

    @Slider(
        title = "Hit Delay (Ticks)",
        description = "Attack cooldown after a missed swing in ticks (0 = no hit delay, 10 = vanilla).",
        category = "General",
        subcategory = "No Hit Delay",
        min = 0.0f,
        max = 10.0f,
        step = 1.0f
    )
    public float hitDelay = 0.0f;

    public RnhdOneConfig() {
        super("rnhd.toml", "RNHD", Category.COMBAT);

        initialize(false);
        registerCallbacks();
        syncToCore(false);
    }

    @Override
    protected void onInitialized(Tree tree) {
        syncToCore(false);
    }

    private void registerCallbacks() {
        Runnable onChange = new Runnable() {
            @Override
            public void run() {
                syncToCore(true);
            }
        };
        addCallback("enabled", onChange);
        addCallback("hitDelay", onChange);
    }

    private void syncToCore(boolean saveConfig) {
        RnhdMod.enabled = enabled;
        RnhdMod.hitDelay = Math.max(0, Math.min(10, Math.round(hitDelay)));
        if (saveConfig) {
            save();
        }
    }
}
