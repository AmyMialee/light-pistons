package amymialee.lightpistons.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MovingPistonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.PistonType;
import net.minecraft.util.Direction;

public class LightMovingPistonBlock extends MovingPistonBlock {
    public LightMovingPistonBlock() {
        super(AbstractBlock.Properties.create(Material.PISTON).hardnessAndResistance(-1.0F).variableOpacity().noDrops().notSolid());
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(TYPE, PistonType.DEFAULT));
    }


}
