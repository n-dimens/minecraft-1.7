package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandXP extends CommandBase
{
    private static final String __OBFID = "CL_00000398";

    public String func_71517_b()
    {
        return "xp";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.xp.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length <= 0)
        {
            throw new WrongUsageException("commands.xp.usage", new Object[0]);
        }
        else
        {
            String s = p_71515_2_[0];
            boolean flag = s.endsWith("l") || s.endsWith("L");

            if (flag && s.length() > 1)
            {
                s = s.substring(0, s.length() - 1);
            }

            int i = func_71526_a(p_71515_1_, s);
            boolean flag1 = i < 0;

            if (flag1)
            {
                i *= -1;
            }

            EntityPlayerMP entityplayermp;

            if (p_71515_2_.length > 1)
            {
                entityplayermp = func_82359_c(p_71515_1_, p_71515_2_[1]);
            }
            else
            {
                entityplayermp = func_71521_c(p_71515_1_);
            }

            if (flag)
            {
                if (flag1)
                {
                    entityplayermp.func_82242_a(-i);
                    func_152373_a(p_71515_1_, this, "commands.xp.success.negative.levels", new Object[] {Integer.valueOf(i), entityplayermp.func_70005_c_()});
                }
                else
                {
                    entityplayermp.func_82242_a(i);
                    func_152373_a(p_71515_1_, this, "commands.xp.success.levels", new Object[] {Integer.valueOf(i), entityplayermp.func_70005_c_()});
                }
            }
            else
            {
                if (flag1)
                {
                    throw new WrongUsageException("commands.xp.failure.widthdrawXp", new Object[0]);
                }

                entityplayermp.func_71023_q(i);
                func_152373_a(p_71515_1_, this, "commands.xp.success", new Object[] {Integer.valueOf(i), entityplayermp.func_70005_c_()});
            }
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length == 2 ? func_71530_a(p_71516_2_, this.func_71542_c()) : null;
    }

    protected String[] func_71542_c()
    {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_2_ == 1;
    }
}