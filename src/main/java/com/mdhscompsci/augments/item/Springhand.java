package com.mdhscompsci.augments.item;

import net.minecraftforge.common.MinecraftForge;

import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class Springhand extends Item implements ICurioItem{

    public Springhand(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    //applies knockback to entity if right clicked with hand with springhand equipped

    @SubscribeEvent
    public void onEntityRightClick(PlayerInteractEvent.EntityInteract e){
        PlayerEntity player = e.getPlayer();

        if(!player.level.isClientSide){
            List<SlotResult> items = CuriosApi.getCuriosHelper().findCurios(player, this);

            if(items.size() > 0 && player.inventory.getSelected().equals(ItemStack.EMPTY)){
                LivingEntity target = (LivingEntity) e.getTarget();

                double knockbackX = -player.getLookAngle().x();
                double knockbackZ = -player.getLookAngle().z();
                target.knockback(10f, knockbackX, knockbackZ);
            }
        }
    }

    //applies knockback to self if block right clicked with hand with springhand equipped

    @SubscribeEvent
    public void onBlockRightClick(PlayerInteractEvent.RightClickBlock e){
        PlayerEntity player = e.getPlayer();

        List<SlotResult> items = CuriosApi.getCuriosHelper().findCurios(player, this);

        //MUST be done on both sides
        if(items.size() > 0 && player.inventory.getSelected().equals(ItemStack.EMPTY)){
            Vector3d playerMotion = player.getDeltaMovement();
            Vector3d playerLookAngle = player.getLookAngle();
            player.setDeltaMovement(new Vector3d(playerMotion.x() - playerLookAngle.x(), playerMotion.y() - playerLookAngle.y(), playerMotion.z() - playerLookAngle.z()));
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
