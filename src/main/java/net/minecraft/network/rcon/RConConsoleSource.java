package net.minecraft.network.rcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class RConConsoleSource implements ICommandSender
{
    public static final RConConsoleSource field_70010_a = new RConConsoleSource();
    private StringBuffer field_70009_b = new StringBuffer();
    private static final String __OBFID = "CL_00001800";

    public String func_70005_c_()
    {
        return "Rcon";
    }

    public IChatComponent func_145748_c_()
    {
        return new ChatComponentText(this.func_70005_c_());
    }

    public void func_145747_a(IChatComponent p_145747_1_)
    {
        this.field_70009_b.append(p_145747_1_.func_150260_c());
    }

    public boolean func_70003_b(int p_70003_1_, String p_70003_2_)
    {
        return true;
    }

    public ChunkCoordinates func_82114_b()
    {
        return new ChunkCoordinates(0, 0, 0);
    }

    public World func_130014_f_()
    {
        return MinecraftServer.func_71276_C().func_130014_f_();
    }

    @SideOnly(Side.SERVER)
    public void func_70007_b()
    {
        this.field_70009_b.setLength(0);
    }

    @SideOnly(Side.SERVER)
    public String func_70008_c()
    {
        return this.field_70009_b.toString();
    }
}