package net.minecraft.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Comparator;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class EntitySorter implements Comparator
{
    private double field_78949_a;
    private double field_78947_b;
    private double field_78948_c;
    private static final String __OBFID = "CL_00000944";

    public EntitySorter(Entity p_i1242_1_)
    {
        this.field_78949_a = -p_i1242_1_.field_70165_t;
        this.field_78947_b = -p_i1242_1_.field_70163_u;
        this.field_78948_c = -p_i1242_1_.field_70161_v;
    }

    public int compare(WorldRenderer p_compare_1_, WorldRenderer p_compare_2_)
    {
        double d0 = (double)p_compare_1_.field_78925_n + this.field_78949_a;
        double d1 = (double)p_compare_1_.field_78926_o + this.field_78947_b;
        double d2 = (double)p_compare_1_.field_78940_p + this.field_78948_c;
        double d3 = (double)p_compare_2_.field_78925_n + this.field_78949_a;
        double d4 = (double)p_compare_2_.field_78926_o + this.field_78947_b;
        double d5 = (double)p_compare_2_.field_78940_p + this.field_78948_c;
        return (int)((d0 * d0 + d1 * d1 + d2 * d2 - (d3 * d3 + d4 * d4 + d5 * d5)) * 1024.0D);
    }

    public int compare(Object p_compare_1_, Object p_compare_2_)
    {
        return this.compare((WorldRenderer)p_compare_1_, (WorldRenderer)p_compare_2_);
    }
}