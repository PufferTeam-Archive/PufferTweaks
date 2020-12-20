package fr.minemobs.puffertweaks.init;

import com.mojang.brigadier.CommandDispatcher;
import fr.minemobs.puffertweaks.commands.SetFireCmd;
import fr.minemobs.puffertweaks.commands.BroadcastCmd;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommandInit {

    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
        SetFireCmd.register(commandDispatcher);
        BroadcastCmd.register(commandDispatcher);
    }

}
