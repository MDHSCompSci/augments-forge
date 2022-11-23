package com.mdhscompsci.augments.item;

import net.minecraftforge.common.MinecraftForge;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.*;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

public class Springhand extends Item implements ICurioItem{

    public Springhand(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    //THIS SHIT does WORK
    //thanks :)

    private static ICuriosHelper curiosHelper;

    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent e){
       DamageSource source = e.getSource();
       if(source.getEntity() instanceof PlayerEntity){
        PlayerEntity player = (PlayerEntity) source.getEntity();
        if(!player.level.isClientSide){

            //THIS CAUSES A NULL EXCEPTION
            //will come back to this later (or fix it for me xoxo)
            if(!curiosHelper.findFirstCurio(player, this).equals(null)){
                //equipped
                //fist
                if(player.inventory.getSelected().equals(null))e.setAmount(1000f);
            }
            
        }
       }
       
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        //will run every tick
        PlayerEntity player = (PlayerEntity) livingEntity;

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
