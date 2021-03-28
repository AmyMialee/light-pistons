package amymialee.lightpistons;

import amymialee.lightpistons.blocks.LightPistonBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class RegisterPistons {
    public static Block lightPiston;
    public static BlockItem lightPistonItem;
    public static Block lightStickyPiston;
    public static BlockItem lightStickyPistonItem;

    @SubscribeEvent
    public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
        Item.Properties itemSimpleProperties = new Item.Properties().group(ItemGroup.REDSTONE);
        lightPistonItem = new BlockItem(lightPiston, itemSimpleProperties);
        lightStickyPistonItem = new BlockItem(lightStickyPiston, itemSimpleProperties);
        lightPistonItem.setRegistryName(Objects.requireNonNull(lightPiston.getRegistryName()));
        lightStickyPistonItem.setRegistryName(Objects.requireNonNull(lightStickyPiston.getRegistryName()));
        itemRegisterEvent.getRegistry().register(lightPistonItem);
        itemRegisterEvent.getRegistry().register(lightStickyPistonItem);
    }
    @SubscribeEvent
    public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
        AbstractBlock.IPositionPredicate abstractblock$ipositionpredicate = (state, reader, pos) -> !state.get(PistonBlock.EXTENDED);

        lightPiston = new LightPistonBlock(false, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5F)
                .setOpaque(RegisterPistons::isntSolid).setSuffocates(abstractblock$ipositionpredicate).setBlocksVision(abstractblock$ipositionpredicate))
                .setRegistryName("lightpistons", "light_piston");

        lightStickyPiston = new LightPistonBlock(true, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5F)
                .setOpaque(RegisterPistons::isntSolid).setSuffocates(abstractblock$ipositionpredicate).setBlocksVision(abstractblock$ipositionpredicate))
                .setRegistryName("lightpistons", "light_sticky_piston");

        blockRegisterEvent.getRegistry().register(lightPiston);
        blockRegisterEvent.getRegistry().register(lightStickyPiston);
    }
    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }
}
