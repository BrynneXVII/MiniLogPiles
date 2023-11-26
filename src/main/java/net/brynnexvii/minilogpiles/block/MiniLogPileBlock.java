package net.brynnexvii.minilogpiles.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;


public class MiniLogPileBlock extends HorizontalDirectionalBlock {
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);
    private static final VoxelShape SHAPE_AXE = Block.box(1.0, 0.0, 1.0, 15.0, 4.0, 15.0);
    private static final VoxelShape SHAPE_SMALL = Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0);
    private static final VoxelShape SHAPE_MID = Block.box(1.0, 0.0, 1.0, 15.0, 12.0, 15.0);
    private static final VoxelShape SHAPE_BIG = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public MiniLogPileBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)this.defaultBlockState().setValue(STAGE, 1));
    }

    
    
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        int stage = (Integer)state.getValue(STAGE);
        if (stage == 0) {
            return SHAPE_AXE;
        } else if (stage == 1) {
            return SHAPE_SMALL;
        } else if (stage == 2) {
            return SHAPE_MID;
        } else {
            return stage == 3 ? SHAPE_BIG : SHAPE_AXE;
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            int stage = (Integer)state.getValue(STAGE);
            ItemStack stack = player.getItemInHand(hand);
            if (player.isShiftKeyDown()) {
                if (stack.isEmpty() && stage > 0) {
                    --stage;
                    if(stage != 0) {
                        player.addItem(new ItemStack(this.asItem(), 1));
                    }
                    world.setBlockAndUpdate(pos, (BlockState)state.setValue(STAGE, stage));
                    world.playSound((Player)null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }
            } else if (stack.is(this.asItem())) {
                if (stage < 3 && stage > 0) {
                    ++stage;
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                }
            } else if (stack.is(Items.IRON_AXE) && stage == 1 && stack.getDamageValue() == 0) { // ***NEED TO CHANGE, as it stands, this eats your axe
                stage = 0;
                if (!player.getAbilities().instabuild){
                    stack.shrink(1);
                }
            }

            if (stage == (Integer)state.getValue(STAGE)) {
                return InteractionResult.PASS;
            } else {
                world.setBlockAndUpdate(pos, (BlockState)state.setValue(STAGE, stage));
                world.playSound((Player)null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
    }

    private void dropItemStack(Level world, BlockPos pos) {
        Block.popResource(world, pos, new ItemStack(this.asItem(), 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(new Property[]{STAGE});
    }    
    
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
}
