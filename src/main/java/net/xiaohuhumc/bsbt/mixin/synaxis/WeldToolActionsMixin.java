package net.xiaohuhumc.bsbt.mixin.synaxis;

import net.minecraft.server.level.ServerPlayer;
import net.xiaohuhumc.bsbt.util.ToolChecks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.verr1.synaxis.compat.sableblueprint.weld.WeldToolActions")
public abstract class WeldToolActionsMixin {
    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private static void bsbt$canUse(ServerPlayer player, CallbackInfoReturnable<Boolean> cir) {
        if (ToolChecks.holdingBetterTool(player)) {
            cir.setReturnValue(true);
        }
    }
}
