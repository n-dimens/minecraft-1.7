package net.minecraft.client.renderer.culling;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

@SideOnly(Side.CLIENT)
public interface ICamera
{
    boolean func_78546_a(AxisAlignedBB p_78546_1_);

    void func_78547_a(double p_78547_1_, double p_78547_3_, double p_78547_5_);
}