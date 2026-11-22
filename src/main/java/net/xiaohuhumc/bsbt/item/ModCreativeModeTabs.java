package net.xiaohuhumc.bsbt.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xiaohuhumc.bsbt.BSBT;
import dev.rew1nd.sableschematicapi.SableSchematicApi;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BSBT.MOD_ID);

    public static final Supplier<CreativeModeTab> BSBT_TAB =
            CREATIVE_MODE_TAB.register("bsbt_tab", ()-> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BETTER_BLUEPRINT_TOOL.get()))
                    .title(Component.translatable("itemGroup.bsbt_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BETTER_BLUEPRINT_TOOL);
                    }).withTabsBefore(ResourceLocation.fromNamespaceAndPath(SableSchematicApi.MODID, "sable_schematic_api"))
                    .build());

    public static void register (IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
