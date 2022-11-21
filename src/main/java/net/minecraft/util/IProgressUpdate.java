package net.minecraft.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IProgressUpdate
{
    void func_73720_a(String p_73720_1_);

    @SideOnly(Side.CLIENT)
    void func_73721_b(String p_73721_1_);

    void func_73719_c(String p_73719_1_);

    void func_73718_a(int p_73718_1_);

    @SideOnly(Side.CLIENT)
    void func_146586_a();
}