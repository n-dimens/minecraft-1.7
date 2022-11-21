package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderGhast extends RenderLiving
{
    private static final ResourceLocation field_110869_a = new ResourceLocation("textures/entity/ghast/ghast.png");
    private static final ResourceLocation field_110868_f = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");
    private static final String __OBFID = "CL_00000997";

    public RenderGhast()
    {
        super(new ModelGhast(), 0.5F);
    }

    protected ResourceLocation func_110775_a(EntityGhast p_110775_1_)
    {
        return p_110775_1_.func_110182_bF() ? field_110868_f : field_110869_a;
    }

    protected void func_77041_b(EntityGhast p_77041_1_, float p_77041_2_)
    {
        float f1 = ((float)p_77041_1_.field_70794_e + (float)(p_77041_1_.field_70791_f - p_77041_1_.field_70794_e) * p_77041_2_) / 20.0F;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        f1 = 1.0F / (f1 * f1 * f1 * f1 * f1 * 2.0F + 1.0F);
        float f2 = (8.0F + f1) / 2.0F;
        float f3 = (8.0F + 1.0F / f1) / 2.0F;
        GL11.glScalef(f3, f2, f3);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.func_77041_b((EntityGhast)p_77041_1_, p_77041_2_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityGhast)p_110775_1_);
    }
}