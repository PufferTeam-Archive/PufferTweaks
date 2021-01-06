package fr.minemobs.puffertweaks;

import fr.minemobs.puffertweaks.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = Main.modId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegistryEvent {

    @SubscribeEvent
    public static void registerBlockColors(final ColorHandlerEvent.Block event) {
        event.getBlockColors().register((p_getColor_1_, p_getColor_2_, p_getColor_3_, p_getColor_4_) ->
                p_getColor_2_ != null && p_getColor_3_ != null ? BiomeColors.getWaterColor(p_getColor_2_, p_getColor_3_)
                        : -1, Blocks.WATER, BlockInit.bathroom_sink.get());

        event.getBlockColors().register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getWaterColor(reader, pos) : -1, Blocks.WATER, Blocks.BUBBLE_COLUMN, BlockInit.acacia_cauldron.get());
    }

    @SubscribeEvent
    public static void onTextureStich(TextureStitchEvent.Pre event) {
        if (!event.getMap().getTextureLocation().getPath().equals("textures")) {
            return;
        }
        event.addSprite(new ResourceLocation(Main.modId, "block/ghost_block"));
    }

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockInit.bathroom_sink.get(), RenderType.getCutout());
    }

    private static final Logger LOGGER = LogManager.getLogger();
}
