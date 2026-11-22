package net.xiaohuhumc.bsbt;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.xiaohuhumc.bsbt.item.ModCreativeModeTabs;
import net.xiaohuhumc.bsbt.item.ModItems;
import org.slf4j.Logger;

@Mod(BSBT.MOD_ID)
public class BSBT {
    public static final String MOD_ID = "better_survival_blueprint_tool";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BSBT(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing {} v{}", MOD_ID, modContainer.getModInfo().getVersion());

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::loadComplete);
        modEventBus.addListener(this::addCreative);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
    }

    private void addCreative (BuildCreativeModeTabContentsEvent event) {

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Registered item: {}", BuiltInRegistries.ITEM.getKey(ModItems.BETTER_BLUEPRINT_TOOL.get()));
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
        LOGGER.info("{} is ready", MOD_ID);
    }
}
