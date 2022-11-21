package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;

public class CommandGameRule extends CommandBase
{
    private static final String __OBFID = "CL_00000475";

    public String func_71517_b()
    {
        return "gamerule";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.gamerule.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        String s1;

        if (p_71515_2_.length == 2)
        {
            s1 = p_71515_2_[0];
            String s2 = p_71515_2_[1];
            GameRules gamerules2 = this.func_82366_d();

            if (gamerules2.contains(s1))
            {
                gamerules2.setRuleValue(s1, s2);
                func_152373_a(p_71515_1_, this, "commands.gamerule.success", new Object[0]);
            }
            else
            {
                func_152373_a(p_71515_1_, this, "commands.gamerule.norule", new Object[] {s1});
            }
        }
        else if (p_71515_2_.length == 1)
        {
            s1 = p_71515_2_[0];
            GameRules gamerules1 = this.func_82366_d();

            if (gamerules1.contains(s1))
            {
                String s = gamerules1.getStringValue(s1);
                p_71515_1_.func_145747_a((new ChatComponentText(s1)).func_150258_a(" = ").func_150258_a(s));
            }
            else
            {
                func_152373_a(p_71515_1_, this, "commands.gamerule.norule", new Object[] {s1});
            }
        }
        else if (p_71515_2_.length == 0)
        {
            GameRules gamerules = this.func_82366_d();
            p_71515_1_.func_145747_a(new ChatComponentText(func_71527_a(gamerules.getRuleNames())));
        }
        else
        {
            throw new WrongUsageException("commands.gamerule.usage", new Object[0]);
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length == 1 ? func_71530_a(p_71516_2_, this.func_82366_d().getRuleNames()) : (p_71516_2_.length == 2 ? func_71530_a(p_71516_2_, new String[] {"true", "false"}): null);
    }

    private GameRules func_82366_d()
    {
        return MinecraftServer.func_71276_C().func_71218_a(0).func_82736_K();
    }
}