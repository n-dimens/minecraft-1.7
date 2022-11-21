package net.minecraft.world.storage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IPlayerFileData
{
    void func_75753_a(EntityPlayer p_75753_1_);

    NBTTagCompound func_75752_b(EntityPlayer p_75752_1_);

    String[] func_75754_f();
}