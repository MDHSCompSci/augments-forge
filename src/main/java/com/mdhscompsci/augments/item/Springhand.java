package com.mdhscompsci.augments.item;

import net.minecraftforge.common.MinecraftForge;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.IItemHandlerModifiable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;

public class Springhand extends Item implements ICurioItem{

    public Springhand(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void LivingHurtEvent(LivingHurtEvent event){
       DamageSource source = event.getSource();
       
       if(source.getEntity() instanceof PlayerEntity){
        PlayerEntity player = (PlayerEntity) source.getEntity();

        if(!player.level.isClientSide){
            List<SlotResult> items = CuriosApi.getCuriosHelper().findCurios(player, this);
            Boolean isEquipped = items.size() > 0;

            if(isEquipped && player.inventory.getSelected().equals(ItemStack.EMPTY)){
                event.setAmount(7f);
                source.getEntity().knockback()-\;
            }
            
        }
       }
       
    }

    /* 
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
    */
}
