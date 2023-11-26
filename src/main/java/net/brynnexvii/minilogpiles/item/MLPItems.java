package net.brynnexvii.minilogpiles.item;

import net.brynnexvii.minilogpiles.MiniLogPiles;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MLPItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MiniLogPiles.MODID);

    //item register method
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

