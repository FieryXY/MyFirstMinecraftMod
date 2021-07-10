package com.fieryxy.firstmod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public interface CarvedPumpkinAccessor {
    @Accessor("IS_GOLEM_HEAD_PREDICATE")
    public static Predicate<BlockState> getGolemHeadPredicate() {
        throw new AssertionError();
    }


}
