package org.infernalstudios.waymorewaystones;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.function.Supplier;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.blay09.mods.waystones.block.WaystoneBlock;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WayMoreWaystones.MOD_ID)
public class WayMoreWaystones
{

    public static final String MOD_ID = "waymorewaystones";

    public WayMoreWaystones()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        WayMoreWaystones.Blocks.BLOCKS.register(modEventBus);
        System.out.println("WayMoreWaystones: Blocks registered!");
        WayMoreWaystones.Items.ITEMS.register(modEventBus);
        System.out.println("WayMoreWaystones: Items registered!");
        
    }
    
    public static class Items {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WayMoreWaystones.MOD_ID);
    }
    
    public static class Blocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WayMoreWaystones.MOD_ID);
        
        public static final RegistryObject<Block> CRYING_OBSIDIAN_WAYSTONE = registerBlockWithDefaultItem("crying_obsidian_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));
        public static final RegistryObject<Block> DEEPSLATE_WAYSTONE = registerBlockWithDefaultItem("deepslate_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));
        public static final RegistryObject<Block> GEODE_WAYSTONE = registerBlockWithDefaultItem("geode_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));     
        public static final RegistryObject<Block> GLASS_WAYSTONE = registerBlockWithDefaultItem("glass_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));     
        public static final RegistryObject<Block> GLOWSTONE_WAYSTONE = registerBlockWithDefaultItem("glowstone_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));
        public static final RegistryObject<Block> MOSSY_WAYSTONE = registerBlockWithDefaultItem("mossy_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));     
        public static final RegistryObject<Block> NETHER_BRICK_CHISEL_WAYSTONE = registerBlockWithDefaultItem("nether_brick_chisel_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));
        public static final RegistryObject<Block> NETHER_BRICK_WAYSTONE = registerBlockWithDefaultItem("nether_brick_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));
        public static final RegistryObject<Block> PRISMARINE_WAYSTONE = registerBlockWithDefaultItem("prismarine_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));
        public static final RegistryObject<Block> PURPUR_WAYSTONE = registerBlockWithDefaultItem("purpur_waystone", () -> new WaystoneBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f, 2000f)));

        public static <T extends Block> RegistryObject<T> registerBlockWithDefaultItem(String name, Supplier<? extends T> blockSupplier) {
            RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
            WayMoreWaystones.Items.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
            return block;
        }
    }
}
