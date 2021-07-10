package com.fieryxy.firstmod.entities;

import com.fieryxy.firstmod.MyFirstMinecraftMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;

public class CopperGolemEntity extends PathAwareEntity {
    public CopperGolemEntity(EntityType<? extends CopperGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    public CopperGolemEntity(World world) {
        this(MyFirstMinecraftMod.COPPER_GOLEM, world);
    }



    protected void initGoals() {
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
    }


    public static Builder createCopperGolemAttributes() {
        return PathAwareEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f);
    }
}
