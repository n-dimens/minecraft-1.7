package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderMagmaCube extends RenderLiving
{
    private static final ResourceLocation field_110873_a = new ResourceLocation("textures/entity/slime/magmacube.png");
    private static final String __OBFID = "CL_00001009";

    public RenderMagmaCube()
    {
        super(new ModelMagmaCube(), 0.25F);
    }

    protected ResourceLocation func_110775_a(EntityMagmaCube p_110775_1_)
    {
        return field_110873_a;
    }

    protected void func_77041_b(EntityMagmaCube p_77041_1_, float p_77041_2_)
    {
        int i = p_77041_1_.func_70809_q();
        float f1 = (p_77041_1_.field_70812_c + (p_77041_1_.field_70811_b - p_77041_1_.field_70812_c) * p_77041_2_) / ((float)i * 0.5F + 1.0F);
        float f2 = 1.0F / (f1 + 1.0F);
        float f3 = (float)i;
        GL11.glScalef(f2 * f3, 1.0F / f2 * f3, f2 * f3);
    }

    protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.func_77041_b((EntityMagmaCube)p_77041_1_, p_77041_2_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityMagmaCube)p_110775_1_);
    }
}