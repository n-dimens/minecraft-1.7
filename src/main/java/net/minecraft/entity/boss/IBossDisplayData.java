package net.minecraft.entity.boss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IChatComponent;

@SideOnly(Side.CLIENT)
public interface IBossDisplayData
{
    float func_110138_aP();

    float func_110143_aJ();

    IChatComponent func_145748_c_();
}