package com.rnhd.mixin;

import com.rnhd.RnhdMod;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * Replaces the vanilla 10-tick miss attack cooldown in Minecraft#doAttack
 * without altering the 10000-tick GUI open anti-click-through cooldown.
 */
@Mixin(Minecraft.class)
public class MinecraftMixin {
    @ModifyConstant(method = "doAttack", constant = @Constant(intValue = 10))
    private int rnhd$modifyHitDelay(int original) {
        return RnhdMod.getEffectiveHitDelay();
    }
}
