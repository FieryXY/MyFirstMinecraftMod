package com.fieryxy.firstmod;

import com.fieryxy.firstmod.block.TableBlock;
import com.fieryxy.firstmod.entities.CopperGolemEntity;
import com.fieryxy.firstmod.entities.TableBlockEntity;
import com.fieryxy.firstmod.mixin.CarvedPumpkinAccessor;
import com.fieryxy.firstmod.registry.ModItems;
import com.fieryxy.firstmod.screens.TableScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class MyFirstMinecraftMod implements ModInitializer {

    public static final String MOD_ID = "first";
    public static final EntityType<CopperGolemEntity> COPPER_GOLEM = Registry.register(Registry.ENTITY_TYPE, new Identifier("first", "copper_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory<CopperGolemEntity>) (type, world) -> new CopperGolemEntity(type,world)).dimensions(EntityDimensions.fixed(1.0f, 1.0f)).build());

    public static final BlockPattern copperGolemPattern = BlockPatternBuilder.start().aisle(new String[]{"^", "#"}).where('^', CachedBlockPosition.matchesBlockState(CarvedPumpkinAccessor.getGolemHeadPredicate())).where('#',CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.COPPER_BLOCK))).build();

    private static final TableBlock TABLE_BLOCK = new TableBlock(FabricBlockSettings.of(Material.WOOD));

    public static final BlockEntityType<TableBlockEntity> TABLE_BLOCK_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("first", "table_block_entity"),
            FabricBlockEntityTypeBuilder.create((blockPos, blockState) -> new TableBlockEntity(blockPos, blockState), TABLE_BLOCK).build());

    public static final ScreenHandlerType<TableScreenHandler> TABLE_SCREEN_HANDER_TYPE = ScreenHandlerRegistry.registerSimple(new Identifier("first", "table_block"), (syncId, inventory) -> new TableScreenHandler(syncId, inventory));

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        Registry.register(Registry.BLOCK, new Identifier("first", "table_block"), TABLE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("first", "table_block"), new BlockItem(TABLE_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
        FabricDefaultAttributeRegistry.register(COPPER_GOLEM, CopperGolemEntity.createCopperGolemAttributes());
    }
}

