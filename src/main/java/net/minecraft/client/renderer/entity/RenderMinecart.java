package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderMinecart extends Render
{
    private static final ResourceLocation field_110804_g = new ResourceLocation("textures/entity/minecart.png");
    protected ModelBase field_77013_a = new ModelMinecart();
    protected final RenderBlocks field_94145_f;
    private static final String __OBFID = "CL_00001013";

    public RenderMinecart()
    {
        this.field_76989_e = 0.5F;
        this.field_94145_f = new RenderBlocks();
    }

    public void func_76986_a(EntityMinecart p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        GL11.glPushMatrix();
        this.func_110777_b(p_76986_1_);
        long i = (long)p_76986_1_.func_145782_y() * 493286711L;
        i = i * i * 4392167121L + i * 98761L;
        float f2 = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f3 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f4 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        GL11.glTranslatef(f2, f3, f4);
        double d3 = p_76986_1_.field_70142_S + (p_76986_1_.field_70165_t - p_76986_1_.field_70142_S) * (double)p_76986_9_;
        double d4 = p_76986_1_.field_70137_T + (p_76986_1_.field_70163_u - p_76986_1_.field_70137_T) * (double)p_76986_9_;
        double d5 = p_76986_1_.field_70136_U + (p_76986_1_.field_70161_v - p_76986_1_.field_70136_U) * (double)p_76986_9_;
        double d6 = 0.30000001192092896D;
        Vec3 vec3 = p_76986_1_.func_70489_a(d3, d4, d5);
        float f5 = p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_;

        if (vec3 != null)
        {
            Vec3 vec31 = p_76986_1_.func_70495_a(d3, d4, d5, d6);
            Vec3 vec32 = p_76986_1_.func_70495_a(d3, d4, d5, -d6);

            if (vec31 == null)
            {
                vec31 = vec3;
            }

            if (vec32 == null)
            {
                vec32 = vec3;
            }

            p_76986_2_ += vec3.field_72450_a - d3;
            p_76986_4_ += (vec31.field_72448_b + vec32.field_72448_b) / 2.0D - d4;
            p_76986_6_ += vec3.field_72449_c - d5;
            Vec3 vec33 = vec32.func_72441_c(-vec31.field_72450_a, -vec31.field_72448_b, -vec31.field_72449_c);

            if (vec33.func_72433_c() != 0.0D)
            {
                vec33 = vec33.func_72432_b();
                p_76986_8_ = (float)(Math.atan2(vec33.field_72449_c, vec33.field_72450_a) * 180.0D / Math.PI);
                f5 = (float)(Math.atan(vec33.field_72448_b) * 73.0D);
            }
        }

        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        GL11.glRotatef(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-f5, 0.0F, 0.0F, 1.0F);
        float f7 = (float)p_76986_1_.func_70496_j() - p_76986_9_;
        float f8 = p_76986_1_.func_70491_i() - p_76986_9_;

        if (f8 < 0.0F)
        {
            f8 = 0.0F;
        }

        if (f7 > 0.0F)
        {
            GL11.glRotatef(MathHelper.func_76126_a(f7) * f7 * f8 / 10.0F * (float)p_76986_1_.func_70493_k(), 1.0F, 0.0F, 0.0F);
        }

        int k = p_76986_1_.func_94099_q();
        Block block = p_76986_1_.func_145820_n();
        int j = p_76986_1_.func_94098_o();

        if (block.func_149645_b() != -1)
        {
            GL11.glPushMatrix();
            this.func_110776_a(TextureMap.field_110575_b);
            float f6 = 0.75F;
            GL11.glScalef(f6, f6, f6);
            GL11.glTranslatef(0.0F, (float)k / 16.0F, 0.0F);
            this.func_147910_a(p_76986_1_, p_76986_9_, block, j);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_110777_b(p_76986_1_);
        }

        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.field_77013_a.func_78088_a(p_76986_1_, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(EntityMinecart p_110775_1_)
    {
        return field_110804_g;
    }

    protected void func_147910_a(EntityMinecart p_147910_1_, float p_147910_2_, Block p_147910_3_, int p_147910_4_)
    {
        float f1 = p_147910_1_.func_70013_c(p_147910_2_);
        GL11.glPushMatrix();
        this.field_94145_f.func_147800_a(p_147910_3_, p_147910_4_, f1);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityMinecart)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityMinecart)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}