package net.brynnexvii.minilogpiles.block;

import net.brynnexvii.minilogpiles.block.entity.MLPBlockEntities;
import net.brynnexvii.minilogpiles.block.entity.MiniLogPileBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class MiniLogPileBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);
    private static final VoxelShape SHAPE_AXE = Block.box(1.0, 0.0, 1.0, 15.0, 4.0, 15.0);
    private static final VoxelShape SHAPE_SMALL = Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0);
    private static final VoxelShape SHAPE_MID = Block.box(1.0, 0.0, 1.0, 15.0, 12.0, 15.0);
    private static final VoxelShape SHAPE_BIG = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    private final String WOOD_TYPE;

    public MiniLogPileBlock(Properties pProperties, String woodType) {
        super(pProperties);
        this.registerDefaultState((BlockState)this.defaultBlockState().setValue(STAGE, 1));
        this.WOOD_TYPE = woodType;
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
            } else if (stack.isEmpty()) {
                BlockEntity entity = world.getBlockEntity(pos);
                if(entity instanceof MiniLogPileBlockEntity) {
                    NetworkHooks.openScreen(((ServerPlayer) player), ((MiniLogPileBlockEntity) entity), pos);
                } else {
                    throw new IllegalStateException("Our container provider is missing.");
                }
            } //may need to return something here and above??? ***

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

    /* Block Entity */
    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity((pPos));
            if(blockEntity instanceof MiniLogPileBlockEntity) {
                ((MiniLogPileBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    public boolean triggerEvent(BlockState pState, Level pLevel, BlockPos pPos, int pId, int pParam) {
        super.triggerEvent(pState, pLevel, pPos, pId, pParam);
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        return blockentity == null ? false : blockentity.triggerEvent(pId, pParam);
    }

    @javax.annotation.Nullable
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        return blockentity instanceof MenuProvider ? (MenuProvider)blockentity : null;
    }

    @javax.annotation.Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> pServerType, BlockEntityType<E> pClientType, BlockEntityTicker<? super E> pTicker) {
        return pClientType == pServerType ? (BlockEntityTicker<A>)pTicker : null;
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
            return new MiniLogPileBlockEntity(pos, state, this.WOOD_TYPE);
    }

}
