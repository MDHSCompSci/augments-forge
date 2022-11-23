package com.mdhscompsci.augments.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.*;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class Springhand extends Item implements ICurioItem{

    public Springhand(Properties pProperties) {
        super(pProperties);
    }
    
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack){
        PlayerEntity player = (PlayerEntity) slotContext.getWearer();
        player.kill();
    }
    
    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        //will run every tick
        PlayerEntity player = (PlayerEntity) livingEntity;

        System.out.println("FUCK");

        //checks if running on logical server
        if(!player.level.isClientSide){
            boolean hasPlayerStrength = player.getActiveEffects().contains(Effects.DAMAGE_BOOST);

            if(!hasPlayerStrength){
                player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 200));
            }
        }
        

        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }
}
