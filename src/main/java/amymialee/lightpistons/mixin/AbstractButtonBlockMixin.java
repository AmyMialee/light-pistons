package amymialee.lightpistons.mixin;

import amymialee.lightpistons.blocks.FlickButtonBlock;
import net.minecraft.block.AbstractButtonBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractButtonBlock.class)
public abstract class AbstractButtonBlockMixin {
    @Shadow protected abstract int getActiveDuration();

    @Redirect(method = "checkPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractButtonBlock;getActiveDuration()I"))
    private int getActiveDurationPressed(AbstractButtonBlock abstractButtonBlock) {
        return (abstractButtonBlock.getBlock() instanceof FlickButtonBlock) ? 2 : getActiveDuration();
    }

    @Redirect(method = "powerBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractButtonBlock;getActiveDuration()I"))
    private int getActiveDurationPower(AbstractButtonBlock abstractButtonBlock) {
        return (abstractButtonBlock.getBlock() instanceof FlickButtonBlock) ? 2 : getActiveDuration();
    }
}
