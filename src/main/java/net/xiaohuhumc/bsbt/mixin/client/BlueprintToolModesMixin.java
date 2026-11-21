package net.xiaohuhumc.bsbt.mixin.client;

import dev.rew1nd.sableschematicapi.tool.client.mode.BlueprintToolMode;
import dev.rew1nd.sableschematicapi.tool.client.mode.BlueprintToolModes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.xiaohuhumc.bsbt.util.ToolChecks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BlueprintToolModes.class)
public abstract class BlueprintToolModesMixin {
    private static final ResourceLocation WELD = ResourceLocation.parse("synaxis:blueprint_tool/weld");
    private static final ResourceLocation NO_CONTACT = ResourceLocation.parse("synaxis:blueprint_tool/no_contact");

    @Inject(method = "all", at = @At("RETURN"), cancellable = true)
    private static void bsbt$onlySurvivalModes(CallbackInfoReturnable<List<BlueprintToolMode>> cir) {
        final Player player = Minecraft.getInstance().player;
        if (!ToolChecks.holdingBetterTool(player)) {
            return;
        }

        final List<BlueprintToolMode> filtered = cir.getReturnValue().stream()
                .filter(mode -> WELD.equals(mode.id()) || NO_CONTACT.equals(mode.id()))
                .toList();
        if (!filtered.isEmpty()) {
            cir.setReturnValue(filtered);
        }
    }
}
