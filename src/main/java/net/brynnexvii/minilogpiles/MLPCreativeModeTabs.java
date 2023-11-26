package net.brynnexvii.minilogpiles;

import net.brynnexvii.minilogpiles.MiniLogPiles;
import net.brynnexvii.minilogpiles.block.MLPBlocks;
import net.brynnexvii.minilogpiles.item.MLPItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;

public class MLPCreativeModeTabs {
    public static final CreativeModeTab MLP_CREATIVE_MODE_TAB = new CreativeModeTab("mlptab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MLPBlocks.OAK_MINI_LOG_PILE.get());
        }
    };
}
