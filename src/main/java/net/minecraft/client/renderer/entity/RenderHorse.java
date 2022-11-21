package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHorse extends RenderLiving
{
    private static final Map field_110852_a = Maps.newHashMap();
    private static final ResourceLocation field_110850_f = new ResourceLocation("textures/entity/horse/horse_white.png");
    private static final ResourceLocation field_110851_g = new ResourceLocation("textures/entity/horse/mule.png");
    private static final ResourceLocation field_110855_h = new ResourceLocation("textures/entity/horse/donkey.png");
    private static final ResourceLocation field_110854_k = new ResourceLocation("textures/entity/horse/horse_zombie.png");
    private static final ResourceLocation field_110853_l = new ResourceLocation("textures/entity/horse/horse_skeleton.png");
    private static final String __OBFID = "CL_00001000";

    public RenderHorse(ModelBase p_i1256_1_, float p_i1256_2_)
    {
        super(p_i1256_1_, p_i1256_2_);
    }

    protected void func_77041_b(EntityHorse p_77041_1_, float p_77041_2_)
    {
        float f1 = 1.0F;
        int i = p_77041_1_.func_110265_bP();

        if (i == 1)
        {
            f1 *= 0.87F;
        }
        else if (i == 2)
        {
            f1 *= 0.92F;
        }

        GL11.glScalef(f1, f1, f1);
        super.func_77041_b(p_77041_1_, p_77041_2_);
    }

    protected void func_77036_a(EntityHorse p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        if (p_77036_1_.func_82150_aj())
        {
            this.field_77045_g.func_78087_a(p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_, p_77036_1_);
        }
        else
        {
            this.func_110777_b(p_77036_1_);
            this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
        }
    }

    protected ResourceLocation func_110775_a(EntityHorse p_110775_1_)
    {
        if (!p_110775_1_.func_110239_cn())
        {
            switch (p_110775_1_.func_110265_bP())
            {
                case 0:
                default:
                    return field_110850_f;
                case 1:
                    return field_110855_h;
                case 2:
                    return field_110851_g;
                case 3:
                    return field_110854_k;
                case 4:
                    return field_110853_l;
            }
        }
        else
        {
            return this.func_110848_b(p_110775_1_);
        }
    }

    private ResourceLocation func_110848_b(EntityHorse p_110848_1_)
    {
        String s = p_110848_1_.func_110264_co();
        ResourceLocation resourcelocation = (ResourceLocation)field_110852_a.get(s);

        if (resourcelocation == null)
        {
            resourcelocation = new ResourceLocation(s);
            Minecraft.func_71410_x().func_110434_K().func_110579_a(resourcelocation, new LayeredTexture(p_110848_1_.func_110212_cp()));
            field_110852_a.put(s, resourcelocation);
        }

        return resourcelocation;
    }

    protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.func_77041_b((EntityHorse)p_77041_1_, p_77041_2_);
    }

    protected void func_77036_a(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        this.func_77036_a((EntityHorse)p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityHorse)p_110775_1_);
    }
}