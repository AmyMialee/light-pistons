package amymialee.lightpistons.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MovingPistonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.PistonType;
import net.minecraft.tileentity.PistonTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightMovingPistonBlock extends MovingPistonBlock {
    public LightMovingPistonBlock() {
        super(AbstractBlock.Properties.create(Material.PISTON).hardnessAndResistance(-1.0F).variableOpacity().noDrops().notSolid());
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(TYPE, PistonType.DEFAULT));
    }

    public static TileEntity createTilePiston(BlockState state, Direction direction, boolean extending, boolean shouldHeadBeRendered) {
        return new PistonTileEntity(state, direction, extending, shouldHeadBeRendered);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.matchesBlock(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof PistonTileEntity) {
                ((PistonTileEntity)tileentity).clearPistonTileEntity();
            }

        }
    }
}
