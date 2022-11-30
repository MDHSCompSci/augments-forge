package com.mdhscompsci.augments.item;

import net.minecraftforge.common.MinecraftForge;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class Springhand extends Item implements ICurioItem{

    private int cooldown = 0;

    public Springhand(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    // applies knockback to entity if right clicked with hand with springhand equipped

    @SubscribeEvent
    public void onEntityRightClick(PlayerInteractEvent.EntityInteract e){
        PlayerEntity player = e.getPlayer();

        if(!player.level.isClientSide){
            List<SlotResult> items = CuriosApi.getCuriosHelper().findCurios(player, this);

            if(items.size() > 0 & player.inventory.getSelected().equals(ItemStack.EMPTY)){
                LivingEntity target = (LivingEntity) e.getTarget();

                double knockbackX = -player.getLookAngle().x();
                double knockbackZ = -player.getLookAngle().z();
                target.knockback(10f, knockbackX, knockbackZ);
            }
        }
    }

    // applies knockback to self if block right clicked with hand with springhand equipped

    @SubscribeEvent
    public void onBlockRightClick(PlayerInteractEvent.RightClickBlock e){
        PlayerEntity player = e.getPlayer();

        List<SlotResult> items = CuriosApi.getCuriosHelper().findCurios(player, this);

        //MUST be done on both sides
        if(items.size() > 0 & player.inventory.getSelected().equals(ItemStack.EMPTY) & cooldown == 0){
            Vector3d playerMotion = player.getDeltaMovement();
            Vector3d playerLookAngle = player.getLookAngle();

            float factor = 1f;
            player.setDeltaMovement(new Vector3d(playerMotion.x() - (playerLookAngle.x() * factor), playerMotion.y() - (playerLookAngle.y() * factor), playerMotion.z() - (playerLookAngle.z() * factor)));
            cooldown = 40;
        }
    }

    @SubscribeEvent
    public void LivingFallEvent(LivingFallEvent e){
        if(e.getEntityLiving() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) e.getEntityLiving();

            List<SlotResult> items = CuriosApi.getCuriosHelper().findCurios(player, this);

            //MUST be done on both sides
            if(items.size() > 0 & player.inventory.getSelected().equals(ItemStack.EMPTY)){
                if(player.isSuppressingBounce()){
                    e.setCanceled(true);
                }
            }
        }
    }

    //on every equipped tick, lower cooldown
    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if(cooldown>0)cooldown--;
    }
    
    
}
