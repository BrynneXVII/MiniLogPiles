package net.brynnexvii.minilogpiles.block.entity.specific;

import net.brynnexvii.minilogpiles.MiniLogPiles;
import net.brynnexvii.minilogpiles.block.entity.AbstractMiniLogPileBlockEntity;
import net.brynnexvii.minilogpiles.block.entity.MLPBlockEntities;
import net.brynnexvii.minilogpiles.screen.specific.JungleMLPMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class JungleMLPBlockEntity extends AbstractMiniLogPileBlockEntity {
    public JungleMLPBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MLPBlockEntities.JUNGLE_MLP_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block." + MiniLogPiles.MODID + "." + "jungle" + "_mini_log_pile");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new JungleMLPMenu(pContainerId, pPlayerInventory, this, this.data);
    }
}
