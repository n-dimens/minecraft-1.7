package net.minecraft.command;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;

public class CommandPlaySound extends CommandBase
{
    private static final String __OBFID = "CL_00000774";

    public String func_71517_b()
    {
        return "playsound";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.playsound.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length < 2)
        {
            throw new WrongUsageException(this.func_71518_a(p_71515_1_), new Object[0]);
        }
        else
        {
            byte b0 = 0;
            int i = b0 + 1;
            String s = p_71515_2_[b0];
            EntityPlayerMP entityplayermp = func_82359_c(p_71515_1_, p_71515_2_[i++]);
            double d0 = (double)entityplayermp.func_82114_b().field_71574_a;
            double d1 = (double)entityplayermp.func_82114_b().field_71572_b;
            double d2 = (double)entityplayermp.func_82114_b().field_71573_c;
            double d3 = 1.0D;
            double d4 = 1.0D;
            double d5 = 0.0D;

            if (p_71515_2_.length > i)
            {
                d0 = func_110666_a(p_71515_1_, d0, p_71515_2_[i++]);
            }

            if (p_71515_2_.length > i)
            {
                d1 = func_110665_a(p_71515_1_, d1, p_71515_2_[i++], 0, 0);
            }

            if (p_71515_2_.length > i)
            {
                d2 = func_110666_a(p_71515_1_, d2, p_71515_2_[i++]);
            }

            if (p_71515_2_.length > i)
            {
                d3 = func_110661_a(p_71515_1_, p_71515_2_[i++], 0.0D, 3.4028234663852886E38D);
            }

            if (p_71515_2_.length > i)
            {
                d4 = func_110661_a(p_71515_1_, p_71515_2_[i++], 0.0D, 2.0D);
            }

            if (p_71515_2_.length > i)
            {
                d5 = func_110661_a(p_71515_1_, p_71515_2_[i++], 0.0D, 1.0D);
            }

            double d6 = d3 > 1.0D ? d3 * 16.0D : 16.0D;
            double d7 = entityplayermp.func_70011_f(d0, d1, d2);

            if (d7 > d6)
            {
                if (d5 <= 0.0D)
                {
                    throw new CommandException("commands.playsound.playerTooFar", new Object[] {entityplayermp.func_70005_c_()});
                }

                double d8 = d0 - entityplayermp.field_70165_t;
                double d9 = d1 - entityplayermp.field_70163_u;
                double d10 = d2 - entityplayermp.field_70161_v;
                double d11 = Math.sqrt(d8 * d8 + d9 * d9 + d10 * d10);
                double d12 = entityplayermp.field_70165_t;
                double d13 = entityplayermp.field_70163_u;
                double d14 = entityplayermp.field_70161_v;

                if (d11 > 0.0D)
                {
                    d12 += d8 / d11 * 2.0D;
                    d13 += d9 / d11 * 2.0D;
                    d14 += d10 / d11 * 2.0D;
                }

                entityplayermp.field_71135_a.func_147359_a(new S29PacketSoundEffect(s, d12, d13, d14, (float)d5, (float)d4));
            }
            else
            {
                entityplayermp.field_71135_a.func_147359_a(new S29PacketSoundEffect(s, d0, d1, d2, (float)d3, (float)d4));
            }

            func_152373_a(p_71515_1_, this, "commands.playsound.success", new Object[] {s, entityplayermp.func_70005_c_()});
        }
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_2_ == 1;
    }
}