package com.mdhscompsci.augments.item;

import net.minecraftforge.common.MinecraftForge;

import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

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
                event.setAmount(9f);
                double knockbackX = -player.getLookAngle().x();
                double knockbackZ = -player.getLookAngle().z();
                ((LivingEntity) event.getEntity()).knockback(10f, knockbackX, knockbackZ);
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
