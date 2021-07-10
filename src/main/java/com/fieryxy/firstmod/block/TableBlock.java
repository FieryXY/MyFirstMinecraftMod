package com.fieryxy.firstmod.block;

import com.fieryxy.firstmod.MyFirstMinecraftMod;
import com.fieryxy.firstmod.entities.TableBlockEntity;
import com.fieryxy.firstmod.registry.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TableBlock extends BlockWithEntity {

    public static final BooleanProperty HAS_PLATE = BooleanProperty.of("has_plate");

    public TableBlock(Settings settings) {
        super(settings);
        setDefaultState((getStateManager().getDefaultState().with(HAS_PLATE, false)));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(HAS_PLATE);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(world.isClient) {
            return ActionResult.SUCCESS;
        }

        if(player.getEquippedStack(EquipmentSlot.MAINHAND).getItem() == ModItems.PLATE) {
            world.setBlockState(pos, state.with(HAS_PLATE, true));
        }
        else if(state.get(HAS_PLATE)) {
            world.setBlockState(pos, state.with(HAS_PLATE, false));
            ItemEntity plate = (ItemEntity) EntityType.ITEM.create(world);
            plate.setStack(new ItemStack(ModItems.PLATE, 1));
            plate.refreshPositionAfterTeleport(pos.getX(), pos.getY()+1, pos.getZ());
            world.spawnEntity(plate);
            plate.addVelocity(0, 0.1, 0);
        }
        else {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if(screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        VoxelShape leftBackLeg = Block.createCuboidShape(0,0,14,2,13,16);
        VoxelShape leftFrontLeg = Block.createCuboidShape(0,0,0,2,13,2);
        VoxelShape rightBackLeg = Block.createCuboidShape(14,0,14,16,13,16);
        VoxelShape rightFrontLeg = Block.createCuboidShape(14,0,0,16,13,2);
        VoxelShape rightLegs = VoxelShapes.union(rightBackLeg, rightFrontLeg);
        VoxelShape leftLegs = VoxelShapes.union(leftBackLeg, leftFrontLeg);
        VoxelShape legs = VoxelShapes.union(rightLegs, leftLegs);
        return VoxelShapes.union(Block.createCuboidShape(0,13,0,16,16,16), legs);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TableBlockEntity(pos, state);
    }
}
