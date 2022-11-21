package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EntityLargeExplodeFX extends EntityFX
{
    private static final ResourceLocation field_110127_a = new ResourceLocation("textures/entity/explosion.png");
    private int field_70581_a;
    private int field_70584_aq;
    private TextureManager field_70583_ar;
    private float field_70582_as;
    private static final String __OBFID = "CL_00000910";

    public EntityLargeExplodeFX(TextureManager p_i1213_1_, World p_i1213_2_, double p_i1213_3_, double p_i1213_5_, double p_i1213_7_, double p_i1213_9_, double p_i1213_11_, double p_i1213_13_)
    {
        super(p_i1213_2_, p_i1213_3_, p_i1213_5_, p_i1213_7_, 0.0D, 0.0D, 0.0D);
        this.field_70583_ar = p_i1213_1_;
        this.field_70584_aq = 6 + this.field_70146_Z.nextInt(4);
        this.field_70552_h = this.field_70553_i = this.field_70551_j = this.field_70146_Z.nextFloat() * 0.6F + 0.4F;
        this.field_70582_as = 1.0F - (float)p_i1213_9_ * 0.5F;
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
    {
        int i = (int)(((float)this.field_70581_a + p_70539_2_) * 15.0F / (float)this.field_70584_aq);

        if (i <= 15)
        {
            this.field_70583_ar.func_110577_a(field_110127_a);
            float f6 = (float)(i % 4) / 4.0F;
            float f7 = f6 + 0.24975F;
            float f8 = (float)(i / 4) / 4.0F;
            float f9 = f8 + 0.24975F;
            float f10 = 2.0F * this.field_70582_as;
            float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_70539_2_ - field_70556_an);
            float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_70539_2_ - field_70554_ao);
            float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_70539_2_ - field_70555_ap);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            RenderHelper.func_74518_a();
            p_70539_1_.func_78382_b();
            p_70539_1_.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F);
            p_70539_1_.func_78375_b(0.0F, 1.0F, 0.0F);
            p_70539_1_.func_78380_c(240);
            p_70539_1_.func_78374_a((double)(f11 - p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - p_70539_4_ * f10), (double)(f13 - p_70539_5_ * f10 - p_70539_7_ * f10), (double)f7, (double)f9);
            p_70539_1_.func_78374_a((double)(f11 - p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + p_70539_4_ * f10), (double)(f13 - p_70539_5_ * f10 + p_70539_7_ * f10), (double)f7, (double)f8);
            p_70539_1_.func_78374_a((double)(f11 + p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + p_70539_4_ * f10), (double)(f13 + p_70539_5_ * f10 + p_70539_7_ * f10), (double)f6, (double)f8);
            p_70539_1_.func_78374_a((double)(f11 + p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - p_70539_4_ * f10), (double)(f13 + p_70539_5_ * f10 - p_70539_7_ * f10), (double)f6, (double)f9);
            p_70539_1_.func_78381_a();
            GL11.glPolygonOffset(0.0F, 0.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
        }
    }

    public int func_70070_b(float p_70070_1_)
    {
        return 61680;
    }

    public void func_70071_h_()
    {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        ++this.field_70581_a;

        if (this.field_70581_a == this.field_70584_aq)
        {
            this.func_70106_y();
        }
    }

    public int func_70537_b()
    {
        return 3;
    }
}