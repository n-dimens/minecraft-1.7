package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityCrit2FX extends EntityFX
{
    private Entity field_70557_a;
    private int field_70560_aq;
    private int field_70559_ar;
    private String field_70558_as;
    private static final String __OBFID = "CL_00000899";

    public EntityCrit2FX(World p_i1199_1_, Entity p_i1199_2_)
    {
        this(p_i1199_1_, p_i1199_2_, "crit");
    }

    public EntityCrit2FX(World p_i1200_1_, Entity p_i1200_2_, String p_i1200_3_)
    {
        super(p_i1200_1_, p_i1200_2_.field_70165_t, p_i1200_2_.field_70121_D.field_72338_b + (double)(p_i1200_2_.field_70131_O / 2.0F), p_i1200_2_.field_70161_v, p_i1200_2_.field_70159_w, p_i1200_2_.field_70181_x, p_i1200_2_.field_70179_y);
        this.field_70557_a = p_i1200_2_;
        this.field_70559_ar = 3;
        this.field_70558_as = p_i1200_3_;
        this.func_70071_h_();
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {}

    public void func_70071_h_()
    {
        for (int i = 0; i < 16; ++i)
        {
            double d0 = (double)(this.randomizer.nextFloat() * 2.0F - 1.0F);
            double d1 = (double)(this.randomizer.nextFloat() * 2.0F - 1.0F);
            double d2 = (double)(this.randomizer.nextFloat() * 2.0F - 1.0F);

            if (d0 * d0 + d1 * d1 + d2 * d2 <= 1.0D)
            {
                double d3 = this.field_70557_a.field_70165_t + d0 * (double)this.field_70557_a.field_70130_N / 4.0D;
                double d4 = this.field_70557_a.field_70121_D.field_72338_b + (double)(this.field_70557_a.field_70131_O / 2.0F) + d1 * (double)this.field_70557_a.field_70131_O / 4.0D;
                double d5 = this.field_70557_a.field_70161_v + d2 * (double)this.field_70557_a.field_70130_N / 4.0D;
                this.world.func_72869_a(this.field_70558_as, d3, d4, d5, d0, d1 + 0.2D, d2);
            }
        }

        ++this.field_70560_aq;

        if (this.field_70560_aq >= this.field_70559_ar)
        {
            this.func_70106_y();
        }
    }

    public int func_70537_b()
    {
        return 3;
    }
}