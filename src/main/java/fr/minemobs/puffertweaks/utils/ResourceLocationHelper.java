package fr.minemobs.puffertweaks.utils;

import fr.minemobs.puffertweaks.Main;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {
    public static ResourceLocation prefix(String pathOfTheResource){
        return new ResourceLocation(Main.modId, pathOfTheResource);
    }
}
