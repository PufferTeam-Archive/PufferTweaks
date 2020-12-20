package fr.minemobs.puffertweaks;

import com.mojang.brigadier.CommandDispatcher;
import fr.minemobs.puffertweaks.commands.SetFireCmd;
import fr.minemobs.puffertweaks.init.BlockInit;
import fr.minemobs.puffertweaks.init.CommandInit;
import fr.minemobs.puffertweaks.init.ItemInit;
import net.minecraft.command.CommandSource;
import net.minecraft.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("puffertweaks")
@Mod.EventBusSubscriber(modid = "puffertweaks", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Main {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String modId = "puffertweaks";

    public Main(){
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        modEventBus.register(this);
    }

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event){
        MinecraftForge.EVENT_BUS.register(CommandInit.class);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(ModItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties.group(ModItemGroup.instance));
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }

    public static class ModItemGroup extends ItemGroup {

        public static final ModItemGroup instance = new ModItemGroup(ItemGroup.GROUPS.length, "Puffermod Items");

        public ModItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.gravel_dust.get());
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }

    }

}
