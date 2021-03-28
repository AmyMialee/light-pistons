package amymialee.lightpistons.mixin;

import amymialee.lightpistons.RegisterPistons;
import net.minecraft.block.*;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.PistonType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonHeadBlock.class)
public class PistonHeadBlockMixin {
    @Shadow @Final public static EnumProperty<PistonType> TYPE;
    @Inject(method = "isExtended", at = @At("HEAD"), cancellable = true)
    private void isExtendedInject(BlockState baseState, BlockState extendedState, CallbackInfoReturnable<Boolean> cir) {
        Block block1 = baseState.get(TYPE) == PistonType.DEFAULT ? Blocks.PISTON : Blocks.STICKY_PISTON;
        Block block2 = baseState.get(TYPE) == PistonType.DEFAULT ? RegisterPistons.lightPiston : RegisterPistons.lightStickyPiston;
        cir.setReturnValue((extendedState.matchesBlock(block1) || extendedState.matchesBlock(block2)));
    }
}
