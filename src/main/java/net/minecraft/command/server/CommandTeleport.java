package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTeleport extends CommandBase
{
    private static final String __OBFID = "CL_00001180";

    public String func_71517_b()
    {
        return "tp";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.tp.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length < 1)
        {
            throw new WrongUsageException("commands.tp.usage", new Object[0]);
        }
        else
        {
            EntityPlayerMP entityplayermp;

            if (p_71515_2_.length != 2 && p_71515_2_.length != 4)
            {
                entityplayermp = func_71521_c(p_71515_1_);
            }
            else
            {
                entityplayermp = func_82359_c(p_71515_1_, p_71515_2_[0]);

                if (entityplayermp == null)
                {
                    throw new PlayerNotFoundException();
                }
            }

            if (p_71515_2_.length != 3 && p_71515_2_.length != 4)
            {
                if (p_71515_2_.length == 1 || p_71515_2_.length == 2)
                {
                    EntityPlayerMP entityplayermp1 = func_82359_c(p_71515_1_, p_71515_2_[p_71515_2_.length - 1]);

                    if (entityplayermp1 == null)
                    {
                        throw new PlayerNotFoundException();
                    }

                    if (entityplayermp1.world != entityplayermp.world)
                    {
                        func_152373_a(p_71515_1_, this, "commands.tp.notSameDimension", new Object[0]);
                        return;
                    }

                    entityplayermp.func_70078_a((Entity)null);
                    entityplayermp.field_71135_a.func_147364_a(entityplayermp1.field_70165_t, entityplayermp1.field_70163_u, entityplayermp1.field_70161_v, entityplayermp1.field_70177_z, entityplayermp1.field_70125_A);
                    func_152373_a(p_71515_1_, this, "commands.tp.success", new Object[] {entityplayermp.func_70005_c_(), entityplayermp1.func_70005_c_()});
                }
            }
            else if (entityplayermp.world != null)
            {
                int i = p_71515_2_.length - 3;
                double d0 = func_110666_a(p_71515_1_, entityplayermp.field_70165_t, p_71515_2_[i++]);
                double d1 = func_110665_a(p_71515_1_, entityplayermp.field_70163_u, p_71515_2_[i++], 0, 0);
                double d2 = func_110666_a(p_71515_1_, entityplayermp.field_70161_v, p_71515_2_[i++]);
                entityplayermp.func_70078_a((Entity)null);
                entityplayermp.func_70634_a(d0, d1, d2);
                func_152373_a(p_71515_1_, this, "commands.tp.success.coordinates", new Object[] {entityplayermp.func_70005_c_(), Double.valueOf(d0), Double.valueOf(d1), Double.valueOf(d2)});
            }
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length != 1 && p_71516_2_.length != 2 ? null : func_71530_a(p_71516_2_, MinecraftServer.func_71276_C().func_71213_z());
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_2_ == 0;
    }
}