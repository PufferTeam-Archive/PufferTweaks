package fr.minemobs.puffertweaks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class BroadcastCmd {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        LiteralArgumentBuilder<CommandSource> setItemHead
                = Commands.literal("broadcast")
                .requires((commandSource) -> commandSource.hasPermissionLevel(2))
                .then(Commands.argument("message", MessageArgument.message())
                        .executes(BroadcastCmd::sendMessage)
                );

        dispatcher.register(setItemHead);
    }

    static int sendMessage(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        ITextComponent messageValue = MessageArgument.getMessage(commandContext, "message");
        String unformattedText = messageValue.getUnformattedComponentText();
        StringTextComponent coloredFormattedText = new StringTextComponent(unformattedText.replaceAll("&", "§"));
        ServerPlayerEntity sender = commandContext.getSource().asPlayer();
        MinecraftServer ms = sender.server;
        List<ServerPlayerEntity> players = ms.getPlayerList().getPlayers();
        for (ServerPlayerEntity player : players) {
            player.sendMessage(coloredFormattedText, sender.getUniqueID());
        }
        return 1;
    }

}
