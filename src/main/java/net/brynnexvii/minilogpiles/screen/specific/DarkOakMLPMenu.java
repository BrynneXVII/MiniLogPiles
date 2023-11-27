package net.brynnexvii.minilogpiles.screen.specific;

import net.brynnexvii.minilogpiles.block.MLPBlocks;
import net.brynnexvii.minilogpiles.screen.AbstractMiniLogPileMenu;
import net.brynnexvii.minilogpiles.screen.MLPMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DarkOakMLPMenu extends AbstractMiniLogPileMenu {
    public DarkOakMLPMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(MLPMenuTypes.DARK_OAK_MLP_MENU.get(), id, inv, extraData);
    }

    public DarkOakMLPMenu(int id, Inventory inv, BlockEntity entity, ContainerData data){
        super(MLPMenuTypes.DARK_OAK_MLP_MENU.get(), id, inv, entity, data);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, MLPBlocks.DARK_OAK_MINI_LOG_PILE.get());
    }
}
