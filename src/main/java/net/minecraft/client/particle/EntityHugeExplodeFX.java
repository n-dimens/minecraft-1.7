package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityHugeExplodeFX extends EntityFX
{
    private int field_70579_a;
    private int field_70580_aq = 8;
    private static final String __OBFID = "CL_00000911";

    public EntityHugeExplodeFX(World p_i1214_1_, double p_i1214_2_, double p_i1214_4_, double p_i1214_6_, double p_i1214_8_, double p_i1214_10_, double p_i1214_12_)
    {
        super(p_i1214_1_, p_i1214_2_, p_i1214_4_, p_i1214_6_, 0.0D, 0.0D, 0.0D);
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {}

    public void func_70071_h_()
    {
        for (int i = 0; i < 6; ++i)
        {
            double d0 = this.field_70165_t + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * 4.0D;
            double d1 = this.field_70163_u + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * 4.0D;
            double d2 = this.field_70161_v + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * 4.0D;
            this.world.func_72869_a("largeexplode", d0, d1, d2, (double)((float)this.field_70579_a / (float)this.field_70580_aq), 0.0D, 0.0D);
        }

        ++this.field_70579_a;

        if (this.field_70579_a == this.field_70580_aq)
        {
            this.func_70106_y();
        }
    }

    public int func_70537_b()
    {
        return 1;
    }
}