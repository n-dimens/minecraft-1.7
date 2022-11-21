package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSlime extends RenderLiving
{
    private static final ResourceLocation field_110897_a = new ResourceLocation("textures/entity/slime/slime.png");
    private ModelBase field_77092_a;
    private static final String __OBFID = "CL_00001024";

    public RenderSlime(ModelBase p_i1267_1_, ModelBase p_i1267_2_, float p_i1267_3_)
    {
        super(p_i1267_1_, p_i1267_3_);
        this.field_77092_a = p_i1267_2_;
    }

    protected int func_77032_a(EntitySlime p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        if (p_77032_1_.func_82150_aj())
        {
            return 0;
        }
        else if (p_77032_2_ == 0)
        {
            this.func_77042_a(this.field_77092_a);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return 1;
        }
        else
        {
            if (p_77032_2_ == 1)
            {
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            return -1;
        }
    }

    protected void func_77041_b(EntitySlime p_77041_1_, float p_77041_2_)
    {
        float f1 = (float)p_77041_1_.func_70809_q();
        float f2 = (p_77041_1_.field_70812_c + (p_77041_1_.field_70811_b - p_77041_1_.field_70812_c) * p_77041_2_) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    protected ResourceLocation func_110775_a(EntitySlime p_110775_1_)
    {
        return field_110897_a;
    }

    protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.func_77041_b((EntitySlime)p_77041_1_, p_77041_2_);
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.func_77032_a((EntitySlime)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntitySlime)p_110775_1_);
    }
}