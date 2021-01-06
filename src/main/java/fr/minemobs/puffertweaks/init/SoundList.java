package fr.minemobs.puffertweaks.init;

import fr.minemobs.puffertweaks.Main;
import fr.minemobs.puffertweaks.utils.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static fr.minemobs.puffertweaks.Main.modId;

public class SoundList {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, modId);

    public static final RegistryObject<SoundEvent> TEST_SOUND = SOUNDS.register("block.test_sound", () -> new SoundEvent(
            ResourceLocationHelper.prefix("block.test_sound")));

    public static final RegistryObject<SoundEvent> OPEN_FAUCET_SOUND = SOUNDS.register("block.open_faucet", () -> new SoundEvent(
            ResourceLocationHelper.prefix("block.open_faucet")));

    public static final RegistryObject<SoundEvent> CLOSE_FAUCET_SOUND = SOUNDS.register("block.close_faucet", () -> new SoundEvent(
            ResourceLocationHelper.prefix("block.close_faucet")));

}
