package net.minecraft.world.storage;

import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

public interface ISaveHandler
{
    WorldInfo func_75757_d();

    void func_75762_c() throws MinecraftException;

    IChunkLoader func_75763_a(WorldProvider p_75763_1_);

    void func_75755_a(WorldInfo p_75755_1_, NBTTagCompound p_75755_2_);

    void func_75761_a(WorldInfo p_75761_1_);

    IPlayerFileData func_75756_e();

    void func_75759_a();

    File func_75765_b();

    File func_75758_b(String p_75758_1_);

    String func_75760_g();
}