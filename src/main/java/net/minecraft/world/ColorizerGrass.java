package net.minecraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorizerGrass
{
    private static int[] field_77481_a = new int[65536];
    private static final String __OBFID = "CL_00000138";

    public static void func_77479_a(int[] p_77479_0_)
    {
        field_77481_a = p_77479_0_;
    }

    public static int func_77480_a(double p_77480_0_, double p_77480_2_)
    {
        p_77480_2_ *= p_77480_0_;
        int i = (int)((1.0D - p_77480_0_) * 255.0D);
        int j = (int)((1.0D - p_77480_2_) * 255.0D);
        return field_77481_a[j << 8 | i];
    }
}