package amymialee.lightpistons;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("light-pistons")
public class LightPistons {
    public LightPistons() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        registerCommonEvents(modEventBus);
    }
    public void registerCommonEvents(IEventBus eventBus) {
        eventBus.register(RegisterPistons.class);
        eventBus.register(RegisterRedstone.class);
    }
}
