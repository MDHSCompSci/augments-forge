package com.mdhscompsci.augments.item;

import com.mdhscompsci.augments.Augments;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Augments.MOD_ID);

    public static final RegistryObject<Item> TUNGSTEN_INGOT = ITEMS.register("tungsten_ingot",
            () -> new Item(new Item.Properties().tab(ModItemGroup.AUGMENTS_GROUP)));
    public static final RegistryObject<Item> TUNGSTEN_NUGGET = ITEMS.register("tungsten_nugget",
            () -> new Item(new Item.Properties().tab(ModItemGroup.AUGMENTS_GROUP)));
    public static final RegistryObject<Item> SPRINGHAND = ITEMS.register("springhand", 
            () -> new Springhand(new Item.Properties().tab(ModItemGroup.AUGMENTS_GROUP).stacksTo(1)));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
