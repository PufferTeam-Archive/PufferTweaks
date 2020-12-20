package fr.minemobs.puffertweaks.object.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class BathroomSink extends Block {

    CauldronBlock cb;

    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 1);
    private static final VoxelShape INSIDE = Block.makeCuboidShape(4, 2, 4, 12, 5, 12);


    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(4, 0, 3, 12, 5, 4),
            Block.makeCuboidShape(4, 0, 4, 12, 2, 12),
            Block.makeCuboidShape(7, 5, 13, 9, 8, 14),
            Block.makeCuboidShape(7, 8, 10, 9, 9, 14),
            Block.makeCuboidShape(7, 7.699999999999999, 10, 9, 8, 11),
            Block.makeCuboidShape(10, 5, 12, 11, 6, 13),
            Block.makeCuboidShape(5, 5, 12, 6, 6, 13),
            Block.makeCuboidShape(4, 0, 12, 12, 5, 14),
            Block.makeCuboidShape(3, 0, 3, 4, 5, 14),
            Block.makeCuboidShape(12, 0, 3, 13, 5, 14)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();

    private static final VoxelShape SHAPE2 = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.or(SHAPE, INSIDE), IBooleanFunction.ONLY_FIRST);

    public BathroomSink(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(LEVEL, Integer.valueOf(0)));
    }

    @Override
    public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return INSIDE;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public void setWaterLevel(World worldIn, BlockPos pos, BlockState state, int level) {
        worldIn.setBlockState(pos, state.with(LEVEL, Integer.valueOf(MathHelper.clamp(level, 0, 3))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        if (itemstack.isEmpty()) {
            return ActionResultType.PASS;
        } else {
            int i = state.get(LEVEL);
            Item item = itemstack.getItem();
            if (item == Items.WATER_BUCKET) {
                if (i < 1 && !worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                    }

                    player.addStat(Stats.FILL_CAULDRON);
                    this.setWaterLevel(worldIn, pos, state, 1);
                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return ActionResultType.func_233537_a_(worldIn.isRemote);
            } else if (item == Items.BUCKET) {
                if (i == 1 && !worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setHeldItem(handIn, new ItemStack(Items.WATER_BUCKET));
                        } else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
                            player.dropItem(new ItemStack(Items.WATER_BUCKET), false);
                        }
                    }

                    player.addStat(Stats.USE_CAULDRON);
                    this.setWaterLevel(worldIn, pos, state, 0);
                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                return ActionResultType.func_233537_a_(worldIn.isRemote);
            }
        }
        return ActionResultType.PASS;
    }

    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return blockState.get(LEVEL);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}
