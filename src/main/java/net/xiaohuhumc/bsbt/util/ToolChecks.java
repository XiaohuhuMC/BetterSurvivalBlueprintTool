package net.xiaohuhumc.bsbt.util;

import net.minecraft.world.entity.player.Player;
import net.xiaohuhumc.bsbt.item.ModItems;

/**
 * Shared checks used by both client and server mixins.
 */
public final class ToolChecks {
    private ToolChecks() {
    }

    public static boolean holdingBetterTool(Player player) {
        if (player == null) {
            return false;
        }
        return player.getMainHandItem().is(ModItems.BETTER_BLUEPRINT_TOOL.get())
                || player.getOffhandItem().is(ModItems.BETTER_BLUEPRINT_TOOL.get());
    }
}
