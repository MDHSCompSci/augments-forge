package com.mdhscompsci.augments.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup AUGMENTS_GROUP = new ItemGroup("augmentsTab") {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(ModItems.TUNGSTEN_INGOT.get());
        }
    };
}
