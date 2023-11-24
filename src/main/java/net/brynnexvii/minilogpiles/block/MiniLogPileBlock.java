package net.brynnexvii.minilogpiles.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MiniLogPileBlock extends Block {
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);
    private static final VoxelShape SHAPE_AXE = Block.box(1.0, 0.0, 1.0, 15.0, 4.0, 15.0);
    private static final VoxelShape SHAPE_SMALL = Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0);
    private static final VoxelShape SHAPE_MID = Block.box(1.0, 0.0, 1.0, 15.0, 12.0, 15.0);
    private static final VoxelShape SHAPE_BIG = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public MiniLogPileBlock(Properties pProperties) {
        super(pProperties);
    }
}
