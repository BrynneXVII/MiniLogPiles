package net.brynnexvii.minilogpiles.block.entity;

import net.brynnexvii.minilogpiles.block.entity.specific.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMiniLogPileBlockEntity extends BlockEntity implements MenuProvider {
    protected final ContainerData data;
    private int logAmount = 0;
    private static final int MAX_STAGE_0_1 = 64*64;
    private static final int MAX_STAGE_2 = MAX_STAGE_0_1*2;
    private static final int MAX_STAGE_3 = MAX_STAGE_0_1*3;

    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.is(AbstractMiniLogPileBlockEntity.this.getWoodBlock());
                case 1 -> false;
                case 2 -> false;
                case 3 -> false;
                case 4 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public AbstractMiniLogPileBlockEntity(BlockEntityType<? extends AbstractMiniLogPileBlockEntity> entity, BlockPos pPos, BlockState pBlockState) {
        super(entity, pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> AbstractMiniLogPileBlockEntity.this.logAmount;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> AbstractMiniLogPileBlockEntity.this.logAmount = pValue;
                };
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    private Item getWoodBlock(){
        if(this instanceof AcaciaMLPBlockEntity){
            return Items.ACACIA_LOG;
        } else if (this instanceof BirchMLPBlockEntity){
            return Items.BIRCH_LOG;
        } else if (this instanceof CrimsonMLPBlockEntity){
            return Items.CRIMSON_STEM;
        } else if (this instanceof DarkOakMLPBlockEntity){
            return Items.DARK_OAK_LOG;
        } else if (this instanceof JungleMLPBlockEntity){
            return Items.JUNGLE_LOG;
        } else if (this instanceof MangroveMLPBlockEntity){
            return Items.MANGROVE_LOG;
        } else if (this instanceof OakMLPBlockEntity){
            return Items.OAK_LOG;
        } else if (this instanceof SpruceMLPBlockEntity){
            return Items.SPRUCE_LOG;
        } else if (this instanceof WarpedMLPBlockEntity){
            return Items.WARPED_STEM;
        }
        return null;
    }

    @Override
    public abstract Component getDisplayName();

    @Nullable
    @Override
    public abstract AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return this.lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.lazyItemHandler = LazyOptional.of(() -> this.itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", this.itemHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots()); //can adapt to suit this inventory ***
        for(int i = 0; i< this.itemHandler.getSlots(); i++){
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
}
