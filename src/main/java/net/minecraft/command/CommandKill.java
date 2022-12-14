package net.minecraft.command;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;

public class CommandKill extends CommandBase
{
    private static final String __OBFID = "CL_00000570";

    public String func_71517_b()
    {
        return "kill";
    }

    public int func_82362_a()
    {
        return 0;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.kill.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        EntityPlayerMP entityplayermp = func_71521_c(p_71515_1_);
        entityplayermp.func_70097_a(DamageSource.field_76380_i, Float.MAX_VALUE);
        p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.kill.success", new Object[0]));
    }
}