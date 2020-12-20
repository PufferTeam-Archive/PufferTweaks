package fr.minemobs.puffertweaks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlockState;

import java.util.Collection;

public class SetFireCmd {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(
                LiteralArgumentBuilder.<CommandSource>literal("setfire")
                        .requires(src -> src.hasPermissionLevel(2))
                        .then(
                                Commands.literal("entities")
                                        .then(
                                                Commands.argument("targets", EntityArgument.entities())
                                                        .executes(ctx -> setFireEntities(ctx.getSource(), EntityArgument.getEntitiesAllowingNone(ctx, "targets"), 5))
                                                        .then(
                                                                Commands.argument("duration", IntegerArgumentType.integer(0))
                                                                        .executes(ctx -> setFireEntities(ctx.getSource(), EntityArgument.getEntitiesAllowingNone(ctx, "targets"), IntegerArgumentType.getInteger(ctx, "duration")))
                                                        )
                                        )
                        )
                        .then(
                                Commands.literal("blocks")
                                        .then(
                                                Commands.argument("radius", IntegerArgumentType.integer(0))
                                                        .executes(ctx -> setFireBlocks(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "radius")))
                                        )
                        )
        );
    }

    /**
     * Met en feu les entités données pendant la durée donnée. Indique aussi au joueur que les entités ont été mises en
     * feu.
     * @param src La source de la commande
     * @param targets Les entités à mettre en feu
     * @param duration La durée du feu
     * @return le nombre d'entités mises en feu
     */
    private static int setFireEntities(CommandSource src, Collection<? extends Entity> targets, int duration)
    {
        targets.forEach(e -> e.setFire(duration));

        src.sendFeedback(new StringTextComponent(targets.size() + " entities burnt !"), false);

        return targets.size();
    }

    /**
     * Met les blocs autour de la source en feu
     * @param src La source de la commande
     * @param radius Le rayon d'action
     * @return le nombre de blocs mis en feu
     */
    private static int setFireBlocks(CommandSource src, int radius)
    {
        Vector3d srcPos = src.getPos();
        World world = src.getWorld();
        int count = 0;
        for(int x = -radius; x < radius; x++)
        {
            for(int z = -radius; z < radius; z++)
            {
                BlockPos pos = new BlockPos(srcPos.x + x, srcPos.y, srcPos.z + z);
                IForgeBlockState state = world.getBlockState(pos);
                if(state.getBlockState().getBlock() == Blocks.AIR)
                {
                    world.setBlockState(pos, Blocks.FIRE.getDefaultState());
                    count++;
                }
            }
        }

        src.sendFeedback(new StringTextComponent(count + " blocks set to fire !"), false);

        return count;
    }

}
