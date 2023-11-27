package net.brynnexvii.minilogpiles.screen;

import net.brynnexvii.minilogpiles.MiniLogPiles;
import net.brynnexvii.minilogpiles.screen.specific.*;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MLPMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MiniLogPiles.MODID);

    public static final RegistryObject<MenuType<AcaciaMLPMenu>> ACACIA_MLP_MENU = registerMenuType(AcaciaMLPMenu::new, "acacia_mini_log_pile_menu");
    public static final RegistryObject<MenuType<BirchMLPMenu>> BIRCH_MLP_MENU = registerMenuType(BirchMLPMenu::new, "birch_mini_log_pile_menu");
    public static final RegistryObject<MenuType<CrimsonMLPMenu>> CRIMSON_MLP_MENU = registerMenuType(CrimsonMLPMenu::new, "crimson_mini_log_pile_menu");
    public static final RegistryObject<MenuType<DarkOakMLPMenu>> DARK_OAK_MLP_MENU = registerMenuType(DarkOakMLPMenu::new, "dark_oak_mini_log_pile_menu");
    public static final RegistryObject<MenuType<JungleMLPMenu>> JUNGLE_MLP_MENU = registerMenuType(JungleMLPMenu::new, "jungle_mini_log_pile_menu");
    public static final RegistryObject<MenuType<MangroveMLPMenu>> MANGROVE_MLP_MENU = registerMenuType(MangroveMLPMenu::new, "mangrove_mini_log_pile_menu");
    public static final RegistryObject<MenuType<OakMLPMenu>> OAK_MLP_MENU = registerMenuType(OakMLPMenu::new, "oak_mini_log_pile_menu");
    public static final RegistryObject<MenuType<SpruceMLPMenu>> SPRUCE_MLP_MENU = registerMenuType(SpruceMLPMenu::new, "spruce_mini_log_pile_menu");
    public static final RegistryObject<MenuType<WarpedMLPMenu>> WARPED_MLP_MENU = registerMenuType(WarpedMLPMenu::new, "warped_mini_log_pile_menu");

    //Helper methods
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
