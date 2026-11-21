package net.xiaohuhumc.bsbt.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xiaohuhumc.bsbt.BSBT;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BSBT.MOD_ID);

    public static final DeferredItem<Item> BETTER_BLUEPRINT_TOOL =
            ITEMS.register("better_blueprint_tool", () -> new Item(new Item.Properties().stacksTo(1)));

    public static void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
