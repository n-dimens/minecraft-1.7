package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class CommandEmote extends CommandBase
{
    private static final String __OBFID = "CL_00000351";

    public String func_71517_b()
    {
        return "me";
    }

    public int func_82362_a()
    {
        return 0;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.me.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length > 0)
        {
            IChatComponent ichatcomponent = func_147176_a(p_71515_1_, p_71515_2_, 0, p_71515_1_.func_70003_b(1, "me"));
            MinecraftServer.func_71276_C().func_71203_ab().func_148539_a(new ChatComponentTranslation("chat.type.emote", new Object[] {p_71515_1_.func_145748_c_(), ichatcomponent}));
        }
        else
        {
            throw new WrongUsageException("commands.me.usage", new Object[0]);
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return func_71530_a(p_71516_2_, MinecraftServer.func_71276_C().func_71213_z());
    }
}