package net.minecraft.command;

import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class CommandWeather extends CommandBase
{
    private static final String __OBFID = "CL_00001185";

    public String func_71517_b()
    {
        return "weather";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.weather.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length >= 1 && p_71515_2_.length <= 2)
        {
            int i = (300 + (new Random()).nextInt(600)) * 20;

            if (p_71515_2_.length >= 2)
            {
                i = func_71532_a(p_71515_1_, p_71515_2_[1], 1, 1000000) * 20;
            }

            WorldServer worldserver = MinecraftServer.func_71276_C().field_71305_c[0];
            WorldInfo worldinfo = worldserver.func_72912_H();

            if ("clear".equalsIgnoreCase(p_71515_2_[0]))
            {
                worldinfo.func_76080_g(0);
                worldinfo.func_76090_f(0);
                worldinfo.func_76084_b(false);
                worldinfo.func_76069_a(false);
                func_152373_a(p_71515_1_, this, "commands.weather.clear", new Object[0]);
            }
            else if ("rain".equalsIgnoreCase(p_71515_2_[0]))
            {
                worldinfo.func_76080_g(i);
                worldinfo.func_76084_b(true);
                worldinfo.func_76069_a(false);
                func_152373_a(p_71515_1_, this, "commands.weather.rain", new Object[0]);
            }
            else
            {
                if (!"thunder".equalsIgnoreCase(p_71515_2_[0]))
                {
                    throw new WrongUsageException("commands.weather.usage", new Object[0]);
                }

                worldinfo.func_76080_g(i);
                worldinfo.func_76090_f(i);
                worldinfo.func_76084_b(true);
                worldinfo.func_76069_a(true);
                func_152373_a(p_71515_1_, this, "commands.weather.thunder", new Object[0]);
            }
        }
        else
        {
            throw new WrongUsageException("commands.weather.usage", new Object[0]);
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length == 1 ? func_71530_a(p_71516_2_, new String[] {"clear", "rain", "thunder"}): null;
    }
}