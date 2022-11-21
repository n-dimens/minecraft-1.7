package net.minecraft.command.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;

public class CommandAchievement extends CommandBase
{
    private static final String __OBFID = "CL_00000113";

    public String func_71517_b()
    {
        return "achievement";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.achievement.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length >= 2)
        {
            StatBase statbase = StatList.func_151177_a(p_71515_2_[1]);

            if (statbase == null && !p_71515_2_[1].equals("*"))
            {
                throw new CommandException("commands.achievement.unknownAchievement", new Object[] {p_71515_2_[1]});
            }

            EntityPlayerMP entityplayermp;

            if (p_71515_2_.length >= 3)
            {
                entityplayermp = func_82359_c(p_71515_1_, p_71515_2_[2]);
            }
            else
            {
                entityplayermp = func_71521_c(p_71515_1_);
            }

            if (p_71515_2_[0].equalsIgnoreCase("give"))
            {
                if (statbase == null)
                {
                    Iterator iterator = AchievementList.ACHIEVEMENTS.iterator();

                    while (iterator.hasNext())
                    {
                        Achievement achievement = (Achievement)iterator.next();
                        entityplayermp.func_71029_a(achievement);
                    }

                    func_152373_a(p_71515_1_, this, "commands.achievement.give.success.all", new Object[] {entityplayermp.func_70005_c_()});
                }
                else
                {
                    if (statbase instanceof Achievement)
                    {
                        Achievement achievement2 = (Achievement)statbase;
                        ArrayList arraylist;

                        for (arraylist = Lists.newArrayList(); achievement2.requiredAchievement != null && !entityplayermp.func_147099_x().func_77443_a(achievement2.requiredAchievement); achievement2 = achievement2.requiredAchievement)
                        {
                            arraylist.add(achievement2.requiredAchievement);
                        }

                        Iterator iterator1 = Lists.reverse(arraylist).iterator();

                        while (iterator1.hasNext())
                        {
                            Achievement achievement1 = (Achievement)iterator1.next();
                            entityplayermp.func_71029_a(achievement1);
                        }
                    }

                    entityplayermp.func_71029_a(statbase);
                    func_152373_a(p_71515_1_, this, "commands.achievement.give.success.one", new Object[] {entityplayermp.func_70005_c_(), statbase.func_150955_j()});
                }

                return;
            }
        }

        throw new WrongUsageException("commands.achievement.usage", new Object[0]);
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        if (p_71516_2_.length == 1)
        {
            return func_71530_a(p_71516_2_, new String[] {"give"});
        }
        else if (p_71516_2_.length != 2)
        {
            return p_71516_2_.length == 3 ? func_71530_a(p_71516_2_, MinecraftServer.func_71276_C().func_71213_z()) : null;
        }
        else
        {
            ArrayList arraylist = Lists.newArrayList();
            Iterator iterator = StatList.field_75940_b.iterator();

            while (iterator.hasNext())
            {
                StatBase statbase = (StatBase)iterator.next();
                arraylist.add(statbase.id);
            }

            return func_71531_a(p_71516_2_, arraylist);
        }
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_2_ == 2;
    }
}