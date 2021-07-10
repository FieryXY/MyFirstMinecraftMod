package com.fieryxy.firstmod.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.SlimeEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowballEntity.class)
public abstract class SnowballExplosionMixin extends Entity {


    public SnowballExplosionMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method="onCollision", at = @At("TAIL"))
    private void explodeSnowball(HitResult hitResult, CallbackInfo callbackInfo) {
        if(!this.world.isClient) {
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 4.0F, Explosion.DestructionType.BREAK);
        }
    }


}

