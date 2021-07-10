package com.fieryxy.firstmod.mixin;

import com.fieryxy.firstmod.MyFirstMinecraftMod;
import com.fieryxy.firstmod.entities.CopperGolemEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import net.minecraft.block.pattern.BlockPattern.Result;

import java.util.Iterator;

@Mixin(CarvedPumpkinBlock.class)
public class CopperGolemSpawningMixin {



    @Inject(method="trySpawnEntity", at=@At("TAIL"))
    private void trySpawnEntity(World world, BlockPos blockPos, CallbackInfo callbackInfo) {
        BlockPattern copperGolemPattern = MyFirstMinecraftMod.copperGolemPattern;
        Result copperResult = copperGolemPattern.searchAround(world, blockPos);
        if(copperResult != null) {
            int k;
            for(k = 0; k < copperGolemPattern.getWidth(); ++k) {
                for(int l = 0; l < copperGolemPattern.getHeight(); ++l) {
                    CachedBlockPosition cachedBlockPosition3 = copperResult.translate(k, l, 0);
                    world.setBlockState(cachedBlockPosition3.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
                    world.syncWorldEvent(2001, cachedBlockPosition3.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition3.getBlockState()));
                }
            }

            CopperGolemEntity newEntity = (CopperGolemEntity) MyFirstMinecraftMod.COPPER_GOLEM.create(world);
            BlockPos spawnPos = copperResult.translate(0, 1, 0).getBlockPos();
            newEntity.refreshPositionAndAngles((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.05D, (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
            world.spawnEntity(newEntity);
            Iterator iter6 = world.getNonSpectatingEntities(ServerPlayerEntity.class, newEntity.getBoundingBox().expand(5.0D)).iterator();

            while(iter6.hasNext()) {
                ServerPlayerEntity serverPlayerEntity2 = (ServerPlayerEntity)iter6.next();
                Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity2, newEntity);
            }

            for(int m = 0; m < copperGolemPattern.getHeight(); ++m) {
                CachedBlockPosition cachedBlockPosition2 = copperResult.translate(0, m, 0);
                world.updateNeighbors(cachedBlockPosition2.getBlockPos(), Blocks.AIR);
            }
        }
    }

}
