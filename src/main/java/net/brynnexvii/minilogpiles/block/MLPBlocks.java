package net.brynnexvii.minilogpiles.block;

import net.brynnexvii.minilogpiles.MLPCreativeModeTabs;
import net.brynnexvii.minilogpiles.MiniLogPiles;
import net.brynnexvii.minilogpiles.item.MLPItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MLPBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MiniLogPiles.MODID);

    public static final RegistryObject<Block> ACACIA_MINI_LOG_PILE = registerBlock("acacia_mini_log_pile",
            () -> new MiniLogPileBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BIRCH_MINI_LOG_PILE = registerBlock("birch_mini_log_pile",
            () -> new MiniLogPileBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRIMSON_MINI_LOG_PILE = registerBlock("crimson_mini_log_pile",
            () -> new MiniLogPileBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DARK_OAK_MINI_LOG_PILE = registerBlock("dark_oak_mini_log_pile",
            () -> new MiniLogPileBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> JUNGLE_MINI_LOG_PILE = registerBlock("jungle_mini_log_pile",
            () -> new MiniLogPileBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MANGROVE_MINI_LOG_PILE = registerBlock("mangrove_mini_log_pile",
            () -> new MiniLogPileBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OAK_MINI_LOG_PILE = registerBlock("oak_mini_log_pile",
            () -> new MiniLogPileBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SPRUCE_MINI_LOG_PILE = registerBlock("spruce_mini_log_pile",
            () -> new MiniLogPileBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WARPED_MINI_LOG_PILE = registerBlock("warped_mini_log_pile",
            () -> new MiniLogPileBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops()));



    //helper methods
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //register the items for the blocks
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return MLPItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(MLPCreativeModeTabs.MLP_CREATIVE_MODE_TAB)));
    }

    //block register method
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
