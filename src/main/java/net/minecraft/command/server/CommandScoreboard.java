package net.minecraft.command.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class CommandScoreboard extends CommandBase
{
    private static final String __OBFID = "CL_00000896";

    public String func_71517_b()
    {
        return "scoreboard";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.scoreboard.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length >= 1)
        {
            if (p_71515_2_[0].equalsIgnoreCase("objectives"))
            {
                if (p_71515_2_.length == 1)
                {
                    throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
                }

                if (p_71515_2_[1].equalsIgnoreCase("list"))
                {
                    this.func_147196_d(p_71515_1_);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("add"))
                {
                    if (p_71515_2_.length < 4)
                    {
                        throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
                    }

                    this.func_147193_c(p_71515_1_, p_71515_2_, 2);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("remove"))
                {
                    if (p_71515_2_.length != 3)
                    {
                        throw new WrongUsageException("commands.scoreboard.objectives.remove.usage", new Object[0]);
                    }

                    this.func_147191_h(p_71515_1_, p_71515_2_[2]);
                }
                else
                {
                    if (!p_71515_2_[1].equalsIgnoreCase("setdisplay"))
                    {
                        throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
                    }

                    if (p_71515_2_.length != 3 && p_71515_2_.length != 4)
                    {
                        throw new WrongUsageException("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
                    }

                    this.func_147198_k(p_71515_1_, p_71515_2_, 2);
                }

                return;
            }

            if (p_71515_2_[0].equalsIgnoreCase("players"))
            {
                if (p_71515_2_.length == 1)
                {
                    throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
                }

                if (p_71515_2_[1].equalsIgnoreCase("list"))
                {
                    if (p_71515_2_.length > 3)
                    {
                        throw new WrongUsageException("commands.scoreboard.players.list.usage", new Object[0]);
                    }

                    this.func_147195_l(p_71515_1_, p_71515_2_, 2);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("add"))
                {
                    if (p_71515_2_.length != 5)
                    {
                        throw new WrongUsageException("commands.scoreboard.players.add.usage", new Object[0]);
                    }

                    this.func_147197_m(p_71515_1_, p_71515_2_, 2);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("remove"))
                {
                    if (p_71515_2_.length != 5)
                    {
                        throw new WrongUsageException("commands.scoreboard.players.remove.usage", new Object[0]);
                    }

                    this.func_147197_m(p_71515_1_, p_71515_2_, 2);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("set"))
                {
                    if (p_71515_2_.length != 5)
                    {
                        throw new WrongUsageException("commands.scoreboard.players.set.usage", new Object[0]);
                    }

                    this.func_147197_m(p_71515_1_, p_71515_2_, 2);
                }
                else
                {
                    if (!p_71515_2_[1].equalsIgnoreCase("reset"))
                    {
                        throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
                    }

                    if (p_71515_2_.length != 3)
                    {
                        throw new WrongUsageException("commands.scoreboard.players.reset.usage", new Object[0]);
                    }

                    this.func_147187_n(p_71515_1_, p_71515_2_, 2);
                }

                return;
            }

            if (p_71515_2_[0].equalsIgnoreCase("teams"))
            {
                if (p_71515_2_.length == 1)
                {
                    throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
                }

                if (p_71515_2_[1].equalsIgnoreCase("list"))
                {
                    if (p_71515_2_.length > 3)
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.list.usage", new Object[0]);
                    }

                    this.func_147186_g(p_71515_1_, p_71515_2_, 2);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("add"))
                {
                    if (p_71515_2_.length < 3)
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
                    }

                    this.func_147185_d(p_71515_1_, p_71515_2_, 2);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("remove"))
                {
                    if (p_71515_2_.length != 3)
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.remove.usage", new Object[0]);
                    }

                    this.func_147194_f(p_71515_1_, p_71515_2_, 2);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("empty"))
                {
                    if (p_71515_2_.length != 3)
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.empty.usage", new Object[0]);
                    }

                    this.func_147188_j(p_71515_1_, p_71515_2_, 2);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("join"))
                {
                    if (p_71515_2_.length < 4 && (p_71515_2_.length != 3 || !(p_71515_1_ instanceof EntityPlayer)))
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.join.usage", new Object[0]);
                    }

                    this.func_147190_h(p_71515_1_, p_71515_2_, 2);
                }
                else if (p_71515_2_[1].equalsIgnoreCase("leave"))
                {
                    if (p_71515_2_.length < 3 && !(p_71515_1_ instanceof EntityPlayer))
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.leave.usage", new Object[0]);
                    }

                    this.func_147199_i(p_71515_1_, p_71515_2_, 2);
                }
                else
                {
                    if (!p_71515_2_[1].equalsIgnoreCase("option"))
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
                    }

                    if (p_71515_2_.length != 4 && p_71515_2_.length != 5)
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
                    }

                    this.func_147200_e(p_71515_1_, p_71515_2_, 2);
                }

                return;
            }
        }

        throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
    }

    protected Scoreboard func_147192_d()
    {
        return MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();
    }

    protected ScoreObjective func_147189_a(String p_147189_1_, boolean p_147189_2_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        ScoreObjective scoreobjective = scoreboard.func_96518_b(p_147189_1_);

        if (scoreobjective == null)
        {
            throw new CommandException("commands.scoreboard.objectiveNotFound", new Object[] {p_147189_1_});
        }
        else if (p_147189_2_ && scoreobjective.func_96680_c().func_96637_b())
        {
            throw new CommandException("commands.scoreboard.objectiveReadOnly", new Object[] {p_147189_1_});
        }
        else
        {
            return scoreobjective;
        }
    }

    protected ScorePlayerTeam func_147183_a(String p_147183_1_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        ScorePlayerTeam scoreplayerteam = scoreboard.func_96508_e(p_147183_1_);

        if (scoreplayerteam == null)
        {
            throw new CommandException("commands.scoreboard.teamNotFound", new Object[] {p_147183_1_});
        }
        else
        {
            return scoreplayerteam;
        }
    }

    protected void func_147193_c(ICommandSender p_147193_1_, String[] p_147193_2_, int p_147193_3_)
    {
        String s = p_147193_2_[p_147193_3_++];
        String s1 = p_147193_2_[p_147193_3_++];
        Scoreboard scoreboard = this.func_147192_d();
        IScoreObjectiveCriteria iscoreobjectivecriteria = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.field_96643_a.get(s1);

        if (iscoreobjectivecriteria == null)
        {
            throw new WrongUsageException("commands.scoreboard.objectives.add.wrongType", new Object[] {s1});
        }
        else if (scoreboard.func_96518_b(s) != null)
        {
            throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[] {s});
        }
        else if (s.length() > 16)
        {
            throw new SyntaxErrorException("commands.scoreboard.objectives.add.tooLong", new Object[] {s, Integer.valueOf(16)});
        }
        else if (s.length() == 0)
        {
            throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
        }
        else
        {
            if (p_147193_2_.length > p_147193_3_)
            {
                String s2 = func_147178_a(p_147193_1_, p_147193_2_, p_147193_3_).func_150260_c();

                if (s2.length() > 32)
                {
                    throw new SyntaxErrorException("commands.scoreboard.objectives.add.displayTooLong", new Object[] {s2, Integer.valueOf(32)});
                }

                if (s2.length() > 0)
                {
                    scoreboard.func_96535_a(s, iscoreobjectivecriteria).func_96681_a(s2);
                }
                else
                {
                    scoreboard.func_96535_a(s, iscoreobjectivecriteria);
                }
            }
            else
            {
                scoreboard.func_96535_a(s, iscoreobjectivecriteria);
            }

            func_152373_a(p_147193_1_, this, "commands.scoreboard.objectives.add.success", new Object[] {s});
        }
    }

    protected void func_147185_d(ICommandSender p_147185_1_, String[] p_147185_2_, int p_147185_3_)
    {
        String s = p_147185_2_[p_147185_3_++];
        Scoreboard scoreboard = this.func_147192_d();

        if (scoreboard.func_96508_e(s) != null)
        {
            throw new CommandException("commands.scoreboard.teams.add.alreadyExists", new Object[] {s});
        }
        else if (s.length() > 16)
        {
            throw new SyntaxErrorException("commands.scoreboard.teams.add.tooLong", new Object[] {s, Integer.valueOf(16)});
        }
        else if (s.length() == 0)
        {
            throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
        }
        else
        {
            if (p_147185_2_.length > p_147185_3_)
            {
                String s1 = func_147178_a(p_147185_1_, p_147185_2_, p_147185_3_).func_150260_c();

                if (s1.length() > 32)
                {
                    throw new SyntaxErrorException("commands.scoreboard.teams.add.displayTooLong", new Object[] {s1, Integer.valueOf(32)});
                }

                if (s1.length() > 0)
                {
                    scoreboard.func_96527_f(s).func_96664_a(s1);
                }
                else
                {
                    scoreboard.func_96527_f(s);
                }
            }
            else
            {
                scoreboard.func_96527_f(s);
            }

            func_152373_a(p_147185_1_, this, "commands.scoreboard.teams.add.success", new Object[] {s});
        }
    }

    protected void func_147200_e(ICommandSender p_147200_1_, String[] p_147200_2_, int p_147200_3_)
    {
        ScorePlayerTeam scoreplayerteam = this.func_147183_a(p_147200_2_[p_147200_3_++]);

        if (scoreplayerteam != null)
        {
            String s = p_147200_2_[p_147200_3_++].toLowerCase();

            if (!s.equalsIgnoreCase("color") && !s.equalsIgnoreCase("friendlyfire") && !s.equalsIgnoreCase("seeFriendlyInvisibles"))
            {
                throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
            }
            else if (p_147200_2_.length == 4)
            {
                if (s.equalsIgnoreCase("color"))
                {
                    throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] {s, func_96333_a(EnumChatFormatting.func_96296_a(true, false))});
                }
                else if (!s.equalsIgnoreCase("friendlyfire") && !s.equalsIgnoreCase("seeFriendlyInvisibles"))
                {
                    throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
                }
                else
                {
                    throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] {s, func_96333_a(Arrays.asList(new String[]{"true", "false"}))});
                }
            }
            else
            {
                String s1 = p_147200_2_[p_147200_3_++];

                if (s.equalsIgnoreCase("color"))
                {
                    EnumChatFormatting enumchatformatting = EnumChatFormatting.func_96300_b(s1);

                    if (enumchatformatting == null || enumchatformatting.func_96301_b())
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] {s, func_96333_a(EnumChatFormatting.func_96296_a(true, false))});
                    }

                    scoreplayerteam.func_96666_b(enumchatformatting.toString());
                    scoreplayerteam.func_96662_c(EnumChatFormatting.RESET.toString());
                }
                else if (s.equalsIgnoreCase("friendlyfire"))
                {
                    if (!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false"))
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] {s, func_96333_a(Arrays.asList(new String[]{"true", "false"}))});
                    }

                    scoreplayerteam.func_96660_a(s1.equalsIgnoreCase("true"));
                }
                else if (s.equalsIgnoreCase("seeFriendlyInvisibles"))
                {
                    if (!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false"))
                    {
                        throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] {s, func_96333_a(Arrays.asList(new String[]{"true", "false"}))});
                    }

                    scoreplayerteam.func_98300_b(s1.equalsIgnoreCase("true"));
                }

                func_152373_a(p_147200_1_, this, "commands.scoreboard.teams.option.success", new Object[] {s, scoreplayerteam.func_96661_b(), s1});
            }
        }
    }

    protected void func_147194_f(ICommandSender p_147194_1_, String[] p_147194_2_, int p_147194_3_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        ScorePlayerTeam scoreplayerteam = this.func_147183_a(p_147194_2_[p_147194_3_++]);

        if (scoreplayerteam != null)
        {
            scoreboard.func_96511_d(scoreplayerteam);
            func_152373_a(p_147194_1_, this, "commands.scoreboard.teams.remove.success", new Object[] {scoreplayerteam.func_96661_b()});
        }
    }

    protected void func_147186_g(ICommandSender p_147186_1_, String[] p_147186_2_, int p_147186_3_)
    {
        Scoreboard scoreboard = this.func_147192_d();

        if (p_147186_2_.length > p_147186_3_)
        {
            ScorePlayerTeam scoreplayerteam = this.func_147183_a(p_147186_2_[p_147186_3_++]);

            if (scoreplayerteam == null)
            {
                return;
            }

            Collection collection = scoreplayerteam.func_96670_d();

            if (collection.size() <= 0)
            {
                throw new CommandException("commands.scoreboard.teams.list.player.empty", new Object[] {scoreplayerteam.func_96661_b()});
            }

            ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.scoreboard.teams.list.player.count", new Object[] {Integer.valueOf(collection.size()), scoreplayerteam.func_96661_b()});
            chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
            p_147186_1_.func_145747_a(chatcomponenttranslation);
            p_147186_1_.func_145747_a(new ChatComponentText(func_71527_a(collection.toArray())));
        }
        else
        {
            Collection collection1 = scoreboard.func_96525_g();

            if (collection1.size() <= 0)
            {
                throw new CommandException("commands.scoreboard.teams.list.empty", new Object[0]);
            }

            ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("commands.scoreboard.teams.list.count", new Object[] {Integer.valueOf(collection1.size())});
            chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
            p_147186_1_.func_145747_a(chatcomponenttranslation1);
            Iterator iterator = collection1.iterator();

            while (iterator.hasNext())
            {
                ScorePlayerTeam scoreplayerteam1 = (ScorePlayerTeam)iterator.next();
                p_147186_1_.func_145747_a(new ChatComponentTranslation("commands.scoreboard.teams.list.entry", new Object[] {scoreplayerteam1.func_96661_b(), scoreplayerteam1.func_96669_c(), Integer.valueOf(scoreplayerteam1.func_96670_d().size())}));
            }
        }
    }

    protected void func_147190_h(ICommandSender p_147190_1_, String[] p_147190_2_, int p_147190_3_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        String s = p_147190_2_[p_147190_3_++];
        HashSet hashset = new HashSet();
        HashSet hashset1 = new HashSet();
        String s1;

        if (p_147190_1_ instanceof EntityPlayer && p_147190_3_ == p_147190_2_.length)
        {
            s1 = func_71521_c(p_147190_1_).func_70005_c_();

            if (scoreboard.func_151392_a(s1, s))
            {
                hashset.add(s1);
            }
            else
            {
                hashset1.add(s1);
            }
        }
        else
        {
            while (p_147190_3_ < p_147190_2_.length)
            {
                s1 = func_96332_d(p_147190_1_, p_147190_2_[p_147190_3_++]);

                if (scoreboard.func_151392_a(s1, s))
                {
                    hashset.add(s1);
                }
                else
                {
                    hashset1.add(s1);
                }
            }
        }

        if (!hashset.isEmpty())
        {
            func_152373_a(p_147190_1_, this, "commands.scoreboard.teams.join.success", new Object[] {Integer.valueOf(hashset.size()), s, func_71527_a(hashset.toArray(new String[0]))});
        }

        if (!hashset1.isEmpty())
        {
            throw new CommandException("commands.scoreboard.teams.join.failure", new Object[] {Integer.valueOf(hashset1.size()), s, func_71527_a(hashset1.toArray(new String[0]))});
        }
    }

    protected void func_147199_i(ICommandSender p_147199_1_, String[] p_147199_2_, int p_147199_3_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        HashSet hashset = new HashSet();
        HashSet hashset1 = new HashSet();
        String s;

        if (p_147199_1_ instanceof EntityPlayer && p_147199_3_ == p_147199_2_.length)
        {
            s = func_71521_c(p_147199_1_).func_70005_c_();

            if (scoreboard.func_96524_g(s))
            {
                hashset.add(s);
            }
            else
            {
                hashset1.add(s);
            }
        }
        else
        {
            while (p_147199_3_ < p_147199_2_.length)
            {
                s = func_96332_d(p_147199_1_, p_147199_2_[p_147199_3_++]);

                if (scoreboard.func_96524_g(s))
                {
                    hashset.add(s);
                }
                else
                {
                    hashset1.add(s);
                }
            }
        }

        if (!hashset.isEmpty())
        {
            func_152373_a(p_147199_1_, this, "commands.scoreboard.teams.leave.success", new Object[] {Integer.valueOf(hashset.size()), func_71527_a(hashset.toArray(new String[0]))});
        }

        if (!hashset1.isEmpty())
        {
            throw new CommandException("commands.scoreboard.teams.leave.failure", new Object[] {Integer.valueOf(hashset1.size()), func_71527_a(hashset1.toArray(new String[0]))});
        }
    }

    protected void func_147188_j(ICommandSender p_147188_1_, String[] p_147188_2_, int p_147188_3_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        ScorePlayerTeam scoreplayerteam = this.func_147183_a(p_147188_2_[p_147188_3_++]);

        if (scoreplayerteam != null)
        {
            ArrayList arraylist = new ArrayList(scoreplayerteam.func_96670_d());

            if (arraylist.isEmpty())
            {
                throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[] {scoreplayerteam.func_96661_b()});
            }
            else
            {
                Iterator iterator = arraylist.iterator();

                while (iterator.hasNext())
                {
                    String s = (String)iterator.next();
                    scoreboard.func_96512_b(s, scoreplayerteam);
                }

                func_152373_a(p_147188_1_, this, "commands.scoreboard.teams.empty.success", new Object[] {Integer.valueOf(arraylist.size()), scoreplayerteam.func_96661_b()});
            }
        }
    }

    protected void func_147191_h(ICommandSender p_147191_1_, String p_147191_2_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        ScoreObjective scoreobjective = this.func_147189_a(p_147191_2_, false);
        scoreboard.func_96519_k(scoreobjective);
        func_152373_a(p_147191_1_, this, "commands.scoreboard.objectives.remove.success", new Object[] {p_147191_2_});
    }

    protected void func_147196_d(ICommandSender p_147196_1_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        Collection collection = scoreboard.func_96514_c();

        if (collection.size() <= 0)
        {
            throw new CommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
        }
        else
        {
            ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.scoreboard.objectives.list.count", new Object[] {Integer.valueOf(collection.size())});
            chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
            p_147196_1_.func_145747_a(chatcomponenttranslation);
            Iterator iterator = collection.iterator();

            while (iterator.hasNext())
            {
                ScoreObjective scoreobjective = (ScoreObjective)iterator.next();
                p_147196_1_.func_145747_a(new ChatComponentTranslation("commands.scoreboard.objectives.list.entry", new Object[] {scoreobjective.func_96679_b(), scoreobjective.func_96678_d(), scoreobjective.func_96680_c().func_96636_a()}));
            }
        }
    }

    protected void func_147198_k(ICommandSender p_147198_1_, String[] p_147198_2_, int p_147198_3_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        String s = p_147198_2_[p_147198_3_++];
        int j = Scoreboard.func_96537_j(s);
        ScoreObjective scoreobjective = null;

        if (p_147198_2_.length == 4)
        {
            scoreobjective = this.func_147189_a(p_147198_2_[p_147198_3_++], false);
        }

        if (j < 0)
        {
            throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[] {s});
        }
        else
        {
            scoreboard.func_96530_a(j, scoreobjective);

            if (scoreobjective != null)
            {
                func_152373_a(p_147198_1_, this, "commands.scoreboard.objectives.setdisplay.successSet", new Object[] {Scoreboard.func_96517_b(j), scoreobjective.func_96679_b()});
            }
            else
            {
                func_152373_a(p_147198_1_, this, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[] {Scoreboard.func_96517_b(j)});
            }
        }
    }

    protected void func_147195_l(ICommandSender p_147195_1_, String[] p_147195_2_, int p_147195_3_)
    {
        Scoreboard scoreboard = this.func_147192_d();

        if (p_147195_2_.length > p_147195_3_)
        {
            String s = func_96332_d(p_147195_1_, p_147195_2_[p_147195_3_++]);
            Map map = scoreboard.func_96510_d(s);

            if (map.size() <= 0)
            {
                throw new CommandException("commands.scoreboard.players.list.player.empty", new Object[] {s});
            }

            ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.scoreboard.players.list.player.count", new Object[] {Integer.valueOf(map.size()), s});
            chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
            p_147195_1_.func_145747_a(chatcomponenttranslation);
            Iterator iterator = map.values().iterator();

            while (iterator.hasNext())
            {
                Score score = (Score)iterator.next();
                p_147195_1_.func_145747_a(new ChatComponentTranslation("commands.scoreboard.players.list.player.entry", new Object[] {Integer.valueOf(score.func_96652_c()), score.func_96645_d().func_96678_d(), score.func_96645_d().func_96679_b()}));
            }
        }
        else
        {
            Collection collection = scoreboard.func_96526_d();

            if (collection.size() <= 0)
            {
                throw new CommandException("commands.scoreboard.players.list.empty", new Object[0]);
            }

            ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("commands.scoreboard.players.list.count", new Object[] {Integer.valueOf(collection.size())});
            chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
            p_147195_1_.func_145747_a(chatcomponenttranslation1);
            p_147195_1_.func_145747_a(new ChatComponentText(func_71527_a(collection.toArray())));
        }
    }

    protected void func_147197_m(ICommandSender p_147197_1_, String[] p_147197_2_, int p_147197_3_)
    {
        String s = p_147197_2_[p_147197_3_ - 1];
        String s1 = func_96332_d(p_147197_1_, p_147197_2_[p_147197_3_++]);
        ScoreObjective scoreobjective = this.func_147189_a(p_147197_2_[p_147197_3_++], true);
        int j = s.equalsIgnoreCase("set") ? func_71526_a(p_147197_1_, p_147197_2_[p_147197_3_++]) : func_71528_a(p_147197_1_, p_147197_2_[p_147197_3_++], 1);
        Scoreboard scoreboard = this.func_147192_d();
        Score score = scoreboard.func_96529_a(s1, scoreobjective);

        if (s.equalsIgnoreCase("set"))
        {
            score.func_96647_c(j);
        }
        else if (s.equalsIgnoreCase("add"))
        {
            score.func_96649_a(j);
        }
        else
        {
            score.func_96646_b(j);
        }

        func_152373_a(p_147197_1_, this, "commands.scoreboard.players.set.success", new Object[] {scoreobjective.func_96679_b(), s1, Integer.valueOf(score.func_96652_c())});
    }

    protected void func_147187_n(ICommandSender p_147187_1_, String[] p_147187_2_, int p_147187_3_)
    {
        Scoreboard scoreboard = this.func_147192_d();
        String s = func_96332_d(p_147187_1_, p_147187_2_[p_147187_3_++]);
        scoreboard.func_96515_c(s);
        func_152373_a(p_147187_1_, this, "commands.scoreboard.players.reset.success", new Object[] {s});
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        if (p_71516_2_.length == 1)
        {
            return func_71530_a(p_71516_2_, new String[] {"objectives", "players", "teams"});
        }
        else
        {
            if (p_71516_2_[0].equalsIgnoreCase("objectives"))
            {
                if (p_71516_2_.length == 2)
                {
                    return func_71530_a(p_71516_2_, new String[] {"list", "add", "remove", "setdisplay"});
                }

                if (p_71516_2_[1].equalsIgnoreCase("add"))
                {
                    if (p_71516_2_.length == 4)
                    {
                        Set set = IScoreObjectiveCriteria.field_96643_a.keySet();
                        return func_71531_a(p_71516_2_, set);
                    }
                }
                else if (p_71516_2_[1].equalsIgnoreCase("remove"))
                {
                    if (p_71516_2_.length == 3)
                    {
                        return func_71531_a(p_71516_2_, this.func_147184_a(false));
                    }
                }
                else if (p_71516_2_[1].equalsIgnoreCase("setdisplay"))
                {
                    if (p_71516_2_.length == 3)
                    {
                        return func_71530_a(p_71516_2_, new String[] {"list", "sidebar", "belowName"});
                    }

                    if (p_71516_2_.length == 4)
                    {
                        return func_71531_a(p_71516_2_, this.func_147184_a(false));
                    }
                }
            }
            else if (p_71516_2_[0].equalsIgnoreCase("players"))
            {
                if (p_71516_2_.length == 2)
                {
                    return func_71530_a(p_71516_2_, new String[] {"set", "add", "remove", "reset", "list"});
                }

                if (!p_71516_2_[1].equalsIgnoreCase("set") && !p_71516_2_[1].equalsIgnoreCase("add") && !p_71516_2_[1].equalsIgnoreCase("remove"))
                {
                    if ((p_71516_2_[1].equalsIgnoreCase("reset") || p_71516_2_[1].equalsIgnoreCase("list")) && p_71516_2_.length == 3)
                    {
                        return func_71531_a(p_71516_2_, this.func_147192_d().func_96526_d());
                    }
                }
                else
                {
                    if (p_71516_2_.length == 3)
                    {
                        return func_71530_a(p_71516_2_, MinecraftServer.func_71276_C().func_71213_z());
                    }

                    if (p_71516_2_.length == 4)
                    {
                        return func_71531_a(p_71516_2_, this.func_147184_a(true));
                    }
                }
            }
            else if (p_71516_2_[0].equalsIgnoreCase("teams"))
            {
                if (p_71516_2_.length == 2)
                {
                    return func_71530_a(p_71516_2_, new String[] {"add", "remove", "join", "leave", "empty", "list", "option"});
                }

                if (p_71516_2_[1].equalsIgnoreCase("join"))
                {
                    if (p_71516_2_.length == 3)
                    {
                        return func_71531_a(p_71516_2_, this.func_147192_d().func_96531_f());
                    }

                    if (p_71516_2_.length >= 4)
                    {
                        return func_71530_a(p_71516_2_, MinecraftServer.func_71276_C().func_71213_z());
                    }
                }
                else
                {
                    if (p_71516_2_[1].equalsIgnoreCase("leave"))
                    {
                        return func_71530_a(p_71516_2_, MinecraftServer.func_71276_C().func_71213_z());
                    }

                    if (!p_71516_2_[1].equalsIgnoreCase("empty") && !p_71516_2_[1].equalsIgnoreCase("list") && !p_71516_2_[1].equalsIgnoreCase("remove"))
                    {
                        if (p_71516_2_[1].equalsIgnoreCase("option"))
                        {
                            if (p_71516_2_.length == 3)
                            {
                                return func_71531_a(p_71516_2_, this.func_147192_d().func_96531_f());
                            }

                            if (p_71516_2_.length == 4)
                            {
                                return func_71530_a(p_71516_2_, new String[] {"color", "friendlyfire", "seeFriendlyInvisibles"});
                            }

                            if (p_71516_2_.length == 5)
                            {
                                if (p_71516_2_[3].equalsIgnoreCase("color"))
                                {
                                    return func_71531_a(p_71516_2_, EnumChatFormatting.func_96296_a(true, false));
                                }

                                if (p_71516_2_[3].equalsIgnoreCase("friendlyfire") || p_71516_2_[3].equalsIgnoreCase("seeFriendlyInvisibles"))
                                {
                                    return func_71530_a(p_71516_2_, new String[] {"true", "false"});
                                }
                            }
                        }
                    }
                    else if (p_71516_2_.length == 3)
                    {
                        return func_71531_a(p_71516_2_, this.func_147192_d().func_96531_f());
                    }
                }
            }

            return null;
        }
    }

    protected List func_147184_a(boolean p_147184_1_)
    {
        Collection collection = this.func_147192_d().func_96514_c();
        ArrayList arraylist = new ArrayList();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext())
        {
            ScoreObjective scoreobjective = (ScoreObjective)iterator.next();

            if (!p_147184_1_ || !scoreobjective.func_96680_c().func_96637_b())
            {
                arraylist.add(scoreobjective.func_96679_b());
            }
        }

        return arraylist;
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_1_[0].equalsIgnoreCase("players") ? p_82358_2_ == 2 : (!p_82358_1_[0].equalsIgnoreCase("teams") ? false : p_82358_2_ == 2 || p_82358_2_ == 3);
    }
}