package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityFireworkOverlayFX extends EntityFX
{
    private static final String __OBFID = "CL_00000904";

    protected EntityFireworkOverlayFX(World p_i1206_1_, double p_i1206_2_, double p_i1206_4_, double p_i1206_6_)
    {
        super(p_i1206_1_, p_i1206_2_, p_i1206_4_, p_i1206_6_);
        this.field_70547_e = 4;
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
    {
        float f6 = 0.25F;
        float f7 = f6 + 0.25F;
        float f8 = 0.125F;
        float f9 = f8 + 0.25F;
        float f10 = 7.1F * MathHelper.func_76126_a(((float)this.field_70546_d + p_70539_2_ - 1.0F) * 0.25F * (float)Math.PI);
        this.field_82339_as = 0.6F - ((float)this.field_70546_d + p_70539_2_ - 1.0F) * 0.25F * 0.5F;
        float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_70539_2_ - field_70556_an);
        float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_70539_2_ - field_70554_ao);
        float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_70539_2_ - field_70555_ap);
        p_70539_1_.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
        p_70539_1_.func_78374_a((double)(f11 - p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - p_70539_4_ * f10), (double)(f13 - p_70539_5_ * f10 - p_70539_7_ * f10), (double)f7, (double)f9);
        p_70539_1_.func_78374_a((double)(f11 - p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + p_70539_4_ * f10), (double)(f13 - p_70539_5_ * f10 + p_70539_7_ * f10), (double)f7, (double)f8);
        p_70539_1_.func_78374_a((double)(f11 + p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + p_70539_4_ * f10), (double)(f13 + p_70539_5_ * f10 + p_70539_7_ * f10), (double)f6, (double)f8);
        p_70539_1_.func_78374_a((double)(f11 + p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - p_70539_4_ * f10), (double)(f13 + p_70539_5_ * f10 - p_70539_7_ * f10), (double)f6, (double)f9);
    }
}