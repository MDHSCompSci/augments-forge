package com.mdhscompsci.augments.item;

import java.util.Objects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import top.theillusivec4.curios.api.event.CurioEquipEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class Springhand extends Item implements ICurioItem{

    

    public Springhand(Properties pProperties) {
        super(pProperties);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        //will run every tick
        PlayerEntity player = (PlayerEntity) livingEntity;

        if(player.level.isClientSide){
            boolean hasPlayerStrength = player.getActiveEffects().contains(Effects.DAMAGE_BOOST);
        }

        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }
}
