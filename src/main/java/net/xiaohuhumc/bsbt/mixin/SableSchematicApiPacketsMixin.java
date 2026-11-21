package net.xiaohuhumc.bsbt.mixin;

import dev.rew1nd.sableschematicapi.network.SableSchematicApiPackets;
import net.minecraft.server.level.ServerPlayer;
import net.xiaohuhumc.bsbt.util.ToolChecks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SableSchematicApiPackets.class)
public abstract class SableSchematicApiPacketsMixin {
    @Inject(method = "canUseBlueprintTool", at = @At("HEAD"), cancellable = true)
    private static void bsbt$canUse(ServerPlayer player, CallbackInfoReturnable<Boolean> cir) {
        if (ToolChecks.holdingBetterTool(player)) {
            cir.setReturnValue(true);
        }
    }
}
