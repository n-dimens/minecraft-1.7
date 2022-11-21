package net.minecraft.world.storage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

@SideOnly(Side.CLIENT)
public class SaveHandlerMP implements ISaveHandler
{
    private static final String __OBFID = "CL_00000602";

    public WorldInfo func_75757_d()
    {
        return null;
    }

    public void func_75762_c() throws MinecraftException {}

    public IChunkLoader func_75763_a(WorldProvider p_75763_1_)
    {
        return null;
    }

    public void func_75755_a(WorldInfo p_75755_1_, NBTTagCompound p_75755_2_) {}

    public void func_75761_a(WorldInfo p_75761_1_) {}

    public IPlayerFileData func_75756_e()
    {
        return null;
    }

    public void func_75759_a() {}

    public File func_75758_b(String p_75758_1_)
    {
        return null;
    }

    public String func_75760_g()
    {
        return "none";
    }

    public File func_75765_b()
    {
        return null;
    }
}