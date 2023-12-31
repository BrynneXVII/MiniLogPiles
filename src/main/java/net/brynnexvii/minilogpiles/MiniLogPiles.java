package net.brynnexvii.minilogpiles;

import com.mojang.logging.LogUtils;
import net.brynnexvii.minilogpiles.block.MLPBlocks;
import net.brynnexvii.minilogpiles.block.entity.MLPBlockEntities;
import net.brynnexvii.minilogpiles.item.MLPItems;
import net.brynnexvii.minilogpiles.screen.MLPMenuTypes;
import net.brynnexvii.minilogpiles.screen.MiniLogPileScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MiniLogPiles.MODID)
public class MiniLogPiles
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "minilogpiles";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MiniLogPiles()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        MLPBlocks.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        MLPItems.register(modEventBus);

        MLPBlockEntities.register(modEventBus);
        MLPMenuTypes.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(MLPMenuTypes.ACACIA_MLP_MENU.get(), MiniLogPileScreen::new);
            MenuScreens.register(MLPMenuTypes.BIRCH_MLP_MENU.get(), MiniLogPileScreen::new);
            MenuScreens.register(MLPMenuTypes.CRIMSON_MLP_MENU.get(), MiniLogPileScreen::new);
            MenuScreens.register(MLPMenuTypes.DARK_OAK_MLP_MENU.get(), MiniLogPileScreen::new);
            MenuScreens.register(MLPMenuTypes.JUNGLE_MLP_MENU.get(), MiniLogPileScreen::new);
            MenuScreens.register(MLPMenuTypes.MANGROVE_MLP_MENU.get(), MiniLogPileScreen::new);
            MenuScreens.register(MLPMenuTypes.OAK_MLP_MENU.get(), MiniLogPileScreen::new);
            MenuScreens.register(MLPMenuTypes.SPRUCE_MLP_MENU.get(), MiniLogPileScreen::new);
            MenuScreens.register(MLPMenuTypes.WARPED_MLP_MENU.get(), MiniLogPileScreen::new);
        }
    }
}
