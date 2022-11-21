package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBoat extends Render
{
    private static final ResourceLocation field_110782_f = new ResourceLocation("textures/entity/boat.png");
    protected ModelBase field_76998_a;
    private static final String __OBFID = "CL_00000981";

    public RenderBoat()
    {
        this.field_76989_e = 0.5F;
        this.field_76998_a = new ModelBoat();
    }

    public void func_76986_a(EntityBoat p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        GL11.glRotatef(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
        float f2 = (float)p_76986_1_.func_70268_h() - p_76986_9_;
        float f3 = p_76986_1_.func_70271_g() - p_76986_9_;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }

        if (f2 > 0.0F)
        {
            GL11.glRotatef(MathHelper.func_76126_a(f2) * f2 * f3 / 10.0F * (float)p_76986_1_.func_70267_i(), 1.0F, 0.0F, 0.0F);
        }

        float f4 = 0.75F;
        GL11.glScalef(f4, f4, f4);
        GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
        this.func_110777_b(p_76986_1_);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.field_76998_a.func_78088_a(p_76986_1_, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(EntityBoat p_110775_1_)
    {
        return field_110782_f;
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityBoat)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityBoat)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}