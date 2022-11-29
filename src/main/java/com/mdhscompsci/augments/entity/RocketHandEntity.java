package com.mdhscompsci.augments.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class RocketHandEntity extends ProjectileItemEntity {

    public RocketHandEntity(EntityType<? extends RocketHandEntity> type, World worldIn) {
        super(type, worldIn);
    }


    protected void onHitEntity(EntityRayTraceResult pResult) {
        super.onHitEntity(pResult); 
        
        pResult.getEntity().kill();
        this.kill();
    }

    protected void onHitBlock(BlockRayTraceResult pResult) {
        super.onHitBlock(pResult);
        this.kill();
    }


    public void tick() {
        super.tick();
    }

    @Override
    protected Item getDefaultItem() {
        // TODO Auto-generated method stub
        return null;
    }
    

}
