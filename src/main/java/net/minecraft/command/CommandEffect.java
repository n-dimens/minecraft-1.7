package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandEffect extends CommandBase
{
    private static final String __OBFID = "CL_00000323";

    public String func_71517_b()
    {
        return "effect";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.effect.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length < 2)
        {
            throw new WrongUsageException("commands.effect.usage", new Object[0]);
        }
        else
        {
            EntityPlayerMP entityplayermp = func_82359_c(p_71515_1_, p_71515_2_[0]);

            if (p_71515_2_[1].equals("clear"))
            {
                if (entityplayermp.func_70651_bq().isEmpty())
                {
                    throw new CommandException("commands.effect.failure.notActive.all", new Object[] {entityplayermp.func_70005_c_()});
                }

                entityplayermp.func_70674_bp();
                func_152373_a(p_71515_1_, this, "commands.effect.success.removed.all", new Object[] {entityplayermp.func_70005_c_()});
            }
            else
            {
                int i = func_71528_a(p_71515_1_, p_71515_2_[1], 1);
                int j = 600;
                int k = 30;
                int l = 0;

                if (i < 0 || i >= Potion.field_76425_a.length || Potion.field_76425_a[i] == null)
                {
                    throw new NumberInvalidException("commands.effect.notFound", new Object[] {Integer.valueOf(i)});
                }

                if (p_71515_2_.length >= 3)
                {
                    k = func_71532_a(p_71515_1_, p_71515_2_[2], 0, 1000000);

                    if (Potion.field_76425_a[i].func_76403_b())
                    {
                        j = k;
                    }
                    else
                    {
                        j = k * 20;
                    }
                }
                else if (Potion.field_76425_a[i].func_76403_b())
                {
                    j = 1;
                }

                if (p_71515_2_.length >= 4)
                {
                    l = func_71532_a(p_71515_1_, p_71515_2_[3], 0, 255);
                }

                if (k == 0)
                {
                    if (!entityplayermp.func_82165_m(i))
                    {
                        throw new CommandException("commands.effect.failure.notActive", new Object[] {new ChatComponentTranslation(Potion.field_76425_a[i].func_76393_a(), new Object[0]), entityplayermp.func_70005_c_()});
                    }

                    entityplayermp.func_82170_o(i);
                    func_152373_a(p_71515_1_, this, "commands.effect.success.removed", new Object[] {new ChatComponentTranslation(Potion.field_76425_a[i].func_76393_a(), new Object[0]), entityplayermp.func_70005_c_()});
                }
                else
                {
                    PotionEffect potioneffect = new PotionEffect(i, j, l);
                    entityplayermp.func_70690_d(potioneffect);
                    func_152373_a(p_71515_1_, this, "commands.effect.success", new Object[] {new ChatComponentTranslation(potioneffect.func_76453_d(), new Object[0]), Integer.valueOf(i), Integer.valueOf(l), entityplayermp.func_70005_c_(), Integer.valueOf(k)});
                }
            }
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length == 1 ? func_71530_a(p_71516_2_, this.func_98152_d()) : null;
    }

    protected String[] func_98152_d()
    {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_2_ == 0;
    }
}