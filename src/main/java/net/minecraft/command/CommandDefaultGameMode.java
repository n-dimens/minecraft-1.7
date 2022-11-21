package net.minecraft.command;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandDefaultGameMode extends CommandGameMode
{
    private static final String __OBFID = "CL_00000296";

    public String func_71517_b()
    {
        return "defaultgamemode";
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.defaultgamemode.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length > 0)
        {
            WorldSettings.GameType gametype = this.func_71539_b(p_71515_1_, p_71515_2_[0]);
            this.func_71541_a(gametype);
            func_152373_a(p_71515_1_, this, "commands.defaultgamemode.success", new Object[] {new ChatComponentTranslation("gameMode." + gametype.func_77149_b(), new Object[0])});
        }
        else
        {
            throw new WrongUsageException("commands.defaultgamemode.usage", new Object[0]);
        }
    }

    protected void func_71541_a(WorldSettings.GameType p_71541_1_)
    {
        MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
        minecraftserver.func_71235_a(p_71541_1_);
        EntityPlayerMP entityplayermp;

        if (minecraftserver.func_104056_am())
        {
            for (Iterator iterator = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b.iterator(); iterator.hasNext(); entityplayermp.field_70143_R = 0.0F)
            {
                entityplayermp = (EntityPlayerMP)iterator.next();
                entityplayermp.func_71033_a(p_71541_1_);
            }
        }
    }
}