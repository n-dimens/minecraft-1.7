package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EntityFootStepFX extends EntityFX
{
    private static final ResourceLocation field_110126_a = new ResourceLocation("textures/particle/footprint.png");
    private int field_70576_a;
    private int field_70578_aq;
    private TextureManager field_70577_ar;
    private static final String __OBFID = "CL_00000908";

    public EntityFootStepFX(TextureManager p_i1210_1_, World p_i1210_2_, double p_i1210_3_, double p_i1210_5_, double p_i1210_7_)
    {
        super(p_i1210_2_, p_i1210_3_, p_i1210_5_, p_i1210_7_, 0.0D, 0.0D, 0.0D);
        this.field_70577_ar = p_i1210_1_;
        this.field_70159_w = this.field_70181_x = this.field_70179_y = 0.0D;
        this.field_70578_aq = 200;
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
    {
        float f6 = ((float)this.field_70576_a + p_70539_2_) / (float)this.field_70578_aq;
        f6 *= f6;
        float f7 = 2.0F - f6 * 2.0F;

        if (f7 > 1.0F)
        {
            f7 = 1.0F;
        }

        f7 *= 0.2F;
        GL11.glDisable(GL11.GL_LIGHTING);
        float f8 = 0.125F;
        float f9 = (float)(this.field_70165_t - field_70556_an);
        float f10 = (float)(this.field_70163_u - field_70554_ao);
        float f11 = (float)(this.field_70161_v - field_70555_ap);
        float f12 = this.world.func_72801_o(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
        this.field_70577_ar.func_110577_a(field_110126_a);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        p_70539_1_.func_78382_b();
        p_70539_1_.func_78369_a(f12, f12, f12, f7);
        p_70539_1_.func_78374_a((double)(f9 - f8), (double)f10, (double)(f11 + f8), 0.0D, 1.0D);
        p_70539_1_.func_78374_a((double)(f9 + f8), (double)f10, (double)(f11 + f8), 1.0D, 1.0D);
        p_70539_1_.func_78374_a((double)(f9 + f8), (double)f10, (double)(f11 - f8), 1.0D, 0.0D);
        p_70539_1_.func_78374_a((double)(f9 - f8), (double)f10, (double)(f11 - f8), 0.0D, 0.0D);
        p_70539_1_.func_78381_a();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public void func_70071_h_()
    {
        ++this.field_70576_a;

        if (this.field_70576_a == this.field_70578_aq)
        {
            this.func_70106_y();
        }
    }

    public int func_70537_b()
    {
        return 3;
    }
}