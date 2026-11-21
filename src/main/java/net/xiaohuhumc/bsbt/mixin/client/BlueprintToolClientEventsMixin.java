package net.xiaohuhumc.bsbt.mixin.client;

import dev.rew1nd.sableschematicapi.tool.client.BlueprintToolClientEvents;
import dev.rew1nd.sableschematicapi.tool.client.mode.BlueprintToolMode;
import dev.rew1nd.sableschematicapi.tool.client.mode.BlueprintToolModes;
import dev.rew1nd.sableschematicapi.tool.client.session.BlueprintToolClientSession;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.xiaohuhumc.bsbt.item.ModItems;
import net.xiaohuhumc.bsbt.util.ToolChecks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlueprintToolClientEvents.class)
public abstract class BlueprintToolClientEventsMixin {
    private static final ResourceLocation WELD = ResourceLocation.parse("synaxis:blueprint_tool/weld");
    private static final ResourceLocation NO_CONTACT = ResourceLocation.parse("synaxis:blueprint_tool/no_contact");

    @Inject(
            method = "holdingBlueprintTool(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void bsbt$holdingHand(Player player, InteractionHand hand,
                                         CallbackInfoReturnable<Boolean> cir) {
        if (player != null && player.getItemInHand(hand).is(ModItems.BETTER_BLUEPRINT_TOOL.get())) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "holdingBlueprintTool(Lnet/minecraft/world/entity/player/Player;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void bsbt$holdingAny(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (ToolChecks.holdingBetterTool(player)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "openUi", at = @At("HEAD"))
    private static void bsbt$pickDefaultMode(CallbackInfo ci) {
        final Player player = Minecraft.getInstance().player;
        if (!ToolChecks.holdingBetterTool(player)) {
            return;
        }

        final BlueprintToolMode current = BlueprintToolClientSession.currentMode();
        if (current != null && (WELD.equals(current.id()) || NO_CONTACT.equals(current.id()))) {
            return;
        }

        final BlueprintToolMode weld = BlueprintToolModes.byId(WELD);
        if (WELD.equals(weld.id())) {
            BlueprintToolClientSession.setMode(weld);
            return;
        }

        final BlueprintToolMode noContact = BlueprintToolModes.byId(NO_CONTACT);
        if (NO_CONTACT.equals(noContact.id())) {
            BlueprintToolClientSession.setMode(noContact);
        }
    }
}
