package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderCreeper extends RenderLiving
{
    private static final ResourceLocation field_110831_a = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private static final ResourceLocation field_110830_f = new ResourceLocation("textures/entity/creeper/creeper.png");
    private ModelBase field_77064_a = new ModelCreeper(2.0F);
    private static final String __OBFID = "CL_00000985";

    public RenderCreeper()
    {
        super(new ModelCreeper(), 0.5F);
    }

    protected void func_77041_b(EntityCreeper p_77041_1_, float p_77041_2_)
    {
        float f1 = p_77041_1_.func_70831_j(p_77041_2_);
        float f2 = 1.0F + MathHelper.func_76126_a(f1 * 100.0F) * f1 * 0.01F;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GL11.glScalef(f3, f4, f3);
    }

    protected int func_77030_a(EntityCreeper p_77030_1_, float p_77030_2_, float p_77030_3_)
    {
        float f2 = p_77030_1_.func_70831_j(p_77030_3_);

        if ((int)(f2 * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f2 * 0.2F * 255.0F);

            if (i < 0)
            {
                i = 0;
            }

            if (i > 255)
            {
                i = 255;
            }

            short short1 = 255;
            short short2 = 255;
            short short3 = 255;
            return i << 24 | short1 << 16 | short2 << 8 | short3;
        }
    }

    protected int func_77032_a(EntityCreeper p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        if (p_77032_1_.func_70830_n())
        {
            if (p_77032_1_.func_82150_aj())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            if (p_77032_2_ == 1)
            {
                float f1 = (float)p_77032_1_.field_70173_aa + p_77032_3_;
                this.func_110776_a(field_110831_a);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                float f2 = f1 * 0.01F;
                float f3 = f1 * 0.01F;
                GL11.glTranslatef(f2, f3, 0.0F);
                this.func_77042_a(this.field_77064_a);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_BLEND);
                float f4 = 0.5F;
                GL11.glColor4f(f4, f4, f4, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                return 1;
            }

            if (p_77032_2_ == 2)
            {
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
            }
        }

        return -1;
    }

    protected int func_77035_b(EntityCreeper p_77035_1_, int p_77035_2_, float p_77035_3_)
    {
        return -1;
    }

    protected ResourceLocation func_110775_a(EntityCreeper p_110775_1_)
    {
        return field_110830_f;
    }

    protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.func_77041_b((EntityCreeper)p_77041_1_, p_77041_2_);
    }

    protected int func_77030_a(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_)
    {
        return this.func_77030_a((EntityCreeper)p_77030_1_, p_77030_2_, p_77030_3_);
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.func_77032_a((EntityCreeper)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected int func_77035_b(EntityLivingBase p_77035_1_, int p_77035_2_, float p_77035_3_)
    {
        return this.func_77035_b((EntityCreeper)p_77035_1_, p_77035_2_, p_77035_3_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityCreeper)p_110775_1_);
    }
}