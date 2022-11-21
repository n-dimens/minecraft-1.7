package net.minecraft.client.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderEnderCrystal extends Render
{
    private static final ResourceLocation field_110787_a = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
    private ModelBase field_76995_b;
    private static final String __OBFID = "CL_00000987";

    public RenderEnderCrystal()
    {
        this.field_76989_e = 0.5F;
        this.field_76995_b = new ModelEnderCrystal(0.0F, true);
    }

    public void func_76986_a(EntityEnderCrystal p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        float f2 = (float)p_76986_1_.field_70261_a + p_76986_9_;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        this.func_110776_a(field_110787_a);
        float f3 = MathHelper.func_76126_a(f2 * 0.2F) / 2.0F + 0.5F;
        f3 += f3 * f3;
        this.field_76995_b.func_78088_a(p_76986_1_, 0.0F, f2 * 3.0F, f3 * 0.2F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(EntityEnderCrystal p_110775_1_)
    {
        return field_110787_a;
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityEnderCrystal)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityEnderCrystal)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}