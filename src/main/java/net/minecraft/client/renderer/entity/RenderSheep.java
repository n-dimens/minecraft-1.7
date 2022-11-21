package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSheep extends RenderLiving
{
    private static final ResourceLocation field_110885_a = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
    private static final ResourceLocation field_110884_f = new ResourceLocation("textures/entity/sheep/sheep.png");
    private static final String __OBFID = "CL_00001021";

    public RenderSheep(ModelBase p_i1266_1_, ModelBase p_i1266_2_, float p_i1266_3_)
    {
        super(p_i1266_1_, p_i1266_3_);
        this.func_77042_a(p_i1266_2_);
    }

    protected int func_77032_a(EntitySheep p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        if (p_77032_2_ == 0 && !p_77032_1_.func_70892_o())
        {
            this.func_110776_a(field_110885_a);

            if (p_77032_1_.func_94056_bM() && "jeb_".equals(p_77032_1_.func_94057_bL()))
            {
                boolean flag = true;
                int k = p_77032_1_.field_70173_aa / 25 + p_77032_1_.func_145782_y();
                int l = k % EntitySheep.field_70898_d.length;
                int i1 = (k + 1) % EntitySheep.field_70898_d.length;
                float f1 = ((float)(p_77032_1_.field_70173_aa % 25) + p_77032_3_) / 25.0F;
                GL11.glColor3f(EntitySheep.field_70898_d[l][0] * (1.0F - f1) + EntitySheep.field_70898_d[i1][0] * f1, EntitySheep.field_70898_d[l][1] * (1.0F - f1) + EntitySheep.field_70898_d[i1][1] * f1, EntitySheep.field_70898_d[l][2] * (1.0F - f1) + EntitySheep.field_70898_d[i1][2] * f1);
            }
            else
            {
                int j = p_77032_1_.func_70896_n();
                GL11.glColor3f(EntitySheep.field_70898_d[j][0], EntitySheep.field_70898_d[j][1], EntitySheep.field_70898_d[j][2]);
            }

            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation func_110775_a(EntitySheep p_110775_1_)
    {
        return field_110884_f;
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.func_77032_a((EntitySheep)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntitySheep)p_110775_1_);
    }
}