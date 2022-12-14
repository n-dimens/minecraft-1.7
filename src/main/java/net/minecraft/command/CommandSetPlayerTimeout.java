package net.minecraft.command;

import net.minecraft.server.MinecraftServer;

public class CommandSetPlayerTimeout extends CommandBase
{
    private static final String __OBFID = "CL_00000999";

    public String func_71517_b()
    {
        return "setidletimeout";
    }

    public int func_82362_a()
    {
        return 3;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.setidletimeout.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length == 1)
        {
            int i = func_71528_a(p_71515_1_, p_71515_2_[0], 0);
            MinecraftServer.func_71276_C().func_143006_e(i);
            func_152373_a(p_71515_1_, this, "commands.setidletimeout.success", new Object[] {Integer.valueOf(i)});
        }
        else
        {
            throw new WrongUsageException("commands.setidletimeout.usage", new Object[0]);
        }
    }
}