package amymialee.lightpistons.mixin;

import net.minecraft.entity.monster.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin {
    //Purely to test if Mixins are working.
    @Shadow protected abstract void explode();
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        this.explode();
    }
}