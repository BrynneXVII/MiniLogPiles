package net.brynnexvii.minilogpiles.block.entity;

import net.brynnexvii.minilogpiles.MiniLogPiles;
import net.brynnexvii.minilogpiles.block.MLPBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MLPBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MiniLogPiles.MODID);

    public static final RegistryObject<BlockEntityType<MiniLogPileBlockEntity>> MLP_BLOCK_ENTITY = BLOCK_ENTITIES.register("mlp_block_entity",
            () -> BlockEntityType.Builder.of(MiniLogPileBlockEntity::new, MLPBlocks.OAK_MINI_LOG_PILE.get()).build(null)); //hard coded.. not sure why this gives an error ***

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
