package amymialee.lightpistons.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.PistonHeadBlock;
import net.minecraft.block.material.Material;

public class LightPistonHeadBlock extends PistonHeadBlock {
    public LightPistonHeadBlock() {
        super(AbstractBlock.Properties.create(Material.PISTON).hardnessAndResistance(1.5F).noDrops());
    }

}
