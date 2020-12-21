package fr.minemobs.puffertweaks.init;

import fr.minemobs.puffertweaks.Main;
import fr.minemobs.puffertweaks.object.blocks.BathroomSink;
import fr.minemobs.puffertweaks.object.blocks.DustBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Main.modId);

    public static final RegistryObject<Block> gravel_dust = BLOCKS.register("dust_block", () -> new DustBlock(
            AbstractBlock.Properties
                    .create(Material.SAND, MaterialColor.STONE)
                    .hardnessAndResistance(0.3F)
                    .sound(SoundType.GROUND)
    ));

    public static final RegistryObject<Block> bathroom_sink = BLOCKS.register("bathroom_sink", () -> new BathroomSink(
            AbstractBlock.Properties.from(Blocks.CAULDRON)));

}
