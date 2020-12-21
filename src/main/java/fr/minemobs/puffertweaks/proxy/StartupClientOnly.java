package fr.minemobs.puffertweaks.proxy;

import fr.minemobs.puffertweaks.Main;
import fr.minemobs.puffertweaks.init.BlockInit;
import fr.minemobs.puffertweaks.object.blocks.BathroomSink;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Main.modId, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class StartupClientOnly {

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockInit.bathroom_sink.get(), RenderType.getCutout());
    }

}
