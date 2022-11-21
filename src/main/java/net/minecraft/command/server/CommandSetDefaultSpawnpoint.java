package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChunkCoordinates;

public class CommandSetDefaultSpawnpoint extends CommandBase
{
    private static final String __OBFID = "CL_00000973";

    public String func_71517_b()
    {
        return "setworldspawn";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.setworldspawn.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length == 3)
        {
            if (p_71515_1_.func_130014_f_() == null)
            {
                throw new WrongUsageException("commands.setworldspawn.usage", new Object[0]);
            }

            byte b0 = 0;
            int l = b0 + 1;
            int i = func_71532_a(p_71515_1_, p_71515_2_[b0], -30000000, 30000000);
            int j = func_71532_a(p_71515_1_, p_71515_2_[l++], 0, 256);
            int k = func_71532_a(p_71515_1_, p_71515_2_[l++], -30000000, 30000000);
            p_71515_1_.func_130014_f_().func_72950_A(i, j, k);
            func_152373_a(p_71515_1_, this, "commands.setworldspawn.success", new Object[] {Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k)});
        }
        else
        {
            if (p_71515_2_.length != 0)
            {
                throw new WrongUsageException("commands.setworldspawn.usage", new Object[0]);
            }

            ChunkCoordinates chunkcoordinates = func_71521_c(p_71515_1_).func_82114_b();
            p_71515_1_.func_130014_f_().func_72950_A(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c);
            func_152373_a(p_71515_1_, this, "commands.setworldspawn.success", new Object[] {Integer.valueOf(chunkcoordinates.field_71574_a), Integer.valueOf(chunkcoordinates.field_71572_b), Integer.valueOf(chunkcoordinates.field_71573_c)});
        }
    }
}