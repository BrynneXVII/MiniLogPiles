package net.brynnexvii.minilogpiles.block.entity;

import net.brynnexvii.minilogpiles.MiniLogPiles;
import net.brynnexvii.minilogpiles.block.MLPBlocks;
import net.brynnexvii.minilogpiles.block.entity.specific.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MLPBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MiniLogPiles.MODID);

    public static final RegistryObject<BlockEntityType<AcaciaMLPBlockEntity>> ACACIA_MLP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("acacia_mlp_block_entity", () ->
                    BlockEntityType.Builder.of(AcaciaMLPBlockEntity::new,
                            MLPBlocks.ACACIA_MINI_LOG_PILE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BirchMLPBlockEntity>> BIRCH_MLP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("birch_mlp_block_entity", () ->
                    BlockEntityType.Builder.of(BirchMLPBlockEntity::new,
                            MLPBlocks.BIRCH_MINI_LOG_PILE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CrimsonMLPBlockEntity>> CRIMSON_MLP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("crimson_mlp_block_entity", () ->
                    BlockEntityType.Builder.of(CrimsonMLPBlockEntity::new,
                            MLPBlocks.CRIMSON_MINI_LOG_PILE.get()).build(null));
    public static final RegistryObject<BlockEntityType<DarkOakMLPBlockEntity>> DARK_OAK_MLP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dark_oak_mlp_block_entity", () ->
                    BlockEntityType.Builder.of(DarkOakMLPBlockEntity::new,
                            MLPBlocks.DARK_OAK_MINI_LOG_PILE.get()).build(null));
    public static final RegistryObject<BlockEntityType<JungleMLPBlockEntity>> JUNGLE_MLP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("jungle_mlp_block_entity", () ->
                    BlockEntityType.Builder.of(JungleMLPBlockEntity::new,
                            MLPBlocks.JUNGLE_MINI_LOG_PILE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MangroveMLPBlockEntity>> MANGROVE_MLP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("mangrove_mlp_block_entity", () ->
                    BlockEntityType.Builder.of(MangroveMLPBlockEntity::new,
                            MLPBlocks.MANGROVE_MINI_LOG_PILE.get()).build(null));
    public static final RegistryObject<BlockEntityType<OakMLPBlockEntity>> OAK_MLP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("oak_mlp_block_entity", () ->
                    BlockEntityType.Builder.of(OakMLPBlockEntity::new,
                            MLPBlocks.OAK_MINI_LOG_PILE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SpruceMLPBlockEntity>> SPRUCE_MLP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("spruce_mlp_block_entity", () ->
                    BlockEntityType.Builder.of(SpruceMLPBlockEntity::new,
                            MLPBlocks.SPRUCE_MINI_LOG_PILE.get()).build(null));
    public static final RegistryObject<BlockEntityType<WarpedMLPBlockEntity>> WARPED_MLP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("warped_mlp_block_entity", () ->
                    BlockEntityType.Builder.of(WarpedMLPBlockEntity::new,
                            MLPBlocks.WARPED_MINI_LOG_PILE.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
