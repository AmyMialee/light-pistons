package amymialee.lightpistons.blocks;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.PistonType;
import net.minecraft.tileentity.PistonTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class LightMovingPistonBlock extends ContainerBlock {
    public static final DirectionProperty FACING = LightPistonHeadBlock.FACING;
    public static final EnumProperty<PistonType> TYPE = LightPistonHeadBlock.TYPE;

    public LightMovingPistonBlock() {
        super(AbstractBlock.Properties.create(Material.PISTON)
                .hardnessAndResistance(-1.0F)
                .variableOpacity()
                .noDrops()
                .notSolid()
                .setOpaque(LightMovingPistonBlock::isntSolid)
                .setSuffocates(LightMovingPistonBlock::isntSolid)
                .setBlocksVision(LightMovingPistonBlock::isntSolid));
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(TYPE, PistonType.DEFAULT));
    }

    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Nullable
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return null;
    }

    public static TileEntity createTilePiston(BlockState state, Direction direction, boolean extending, boolean shouldHeadBeRendered) {
        return new LightPistonTileEntity(state, direction, extending, shouldHeadBeRendered);
    }

    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.matchesBlock(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof LightPistonTileEntity) {
                ((LightPistonTileEntity)tileentity).clearPistonTileEntity();
            }

        }
    }

    /**
     * Called after a player destroys this Block - the posiiton pos may no longer hold the state indicated.
     */
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.offset(state.get(FACING).getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.getBlock() instanceof LightPistonBlock && blockstate.get(LightPistonBlock.EXTENDED)) {
            worldIn.removeBlock(blockpos, false);
        }

    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && worldIn.getTileEntity(pos) == null) {
            worldIn.removeBlock(pos, false);
            return ActionResultType.CONSUME;
        } else {
            return ActionResultType.PASS;
        }
    }

    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        LightPistonTileEntity pistontileentity = this.getTileEntity(builder.getWorld(), new BlockPos(builder.assertPresent(LootParameters.ORIGIN)));
        return pistontileentity == null ? Collections.emptyList() : pistontileentity.getPistonState().getDrops(builder);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        LightPistonTileEntity pistontileentity = this.getTileEntity(worldIn, pos);
        return pistontileentity != null ? pistontileentity.getCollisionShape(worldIn, pos) : VoxelShapes.empty();
    }

    @Nullable
    private LightPistonTileEntity getTileEntity(IBlockReader blockReader, BlockPos pos) {
        TileEntity tileentity = blockReader.getTileEntity(pos);
        return tileentity instanceof LightPistonTileEntity ? (LightPistonTileEntity)tileentity : null;
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * @deprecated call via {@link /IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
     * fine.
     */
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * @deprecated call via {@link /IBlockState#withMirror(Mirror)} whenever possible. Implementing/overriding is fine.
     */
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}

