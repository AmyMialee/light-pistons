package amymialee.lightpistons;

import amymialee.lightpistons.blocks.FlickButtonBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class RegisterRedstone {
    public static Block flickButton;
    public static BlockItem flickButtonItem;

    @SubscribeEvent
    public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
        Item.Properties itemSimpleProperties = new Item.Properties().group(ItemGroup.REDSTONE);
        flickButtonItem = new BlockItem(flickButton, itemSimpleProperties);
        flickButtonItem.setRegistryName(Objects.requireNonNull(flickButton.getRegistryName()));
        itemRegisterEvent.getRegistry().register(flickButtonItem);
    }

    @SubscribeEvent
    public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
        flickButton = new FlickButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F))
                .setRegistryName("lightpistons", "flick_button");
        blockRegisterEvent.getRegistry().register(flickButton);
    }
}
