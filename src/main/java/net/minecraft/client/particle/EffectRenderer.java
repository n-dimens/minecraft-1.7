package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EffectRenderer
{
    private static final ResourceLocation field_110737_b = new ResourceLocation("textures/particle/particles.png");
    protected World field_78878_a;
    private List[] field_78876_b = new List[4];
    private TextureManager field_78877_c;
    private Random field_78875_d = new Random();
    private static final String __OBFID = "CL_00000915";

    public EffectRenderer(World p_i1220_1_, TextureManager p_i1220_2_)
    {
        if (p_i1220_1_ != null)
        {
            this.field_78878_a = p_i1220_1_;
        }

        this.field_78877_c = p_i1220_2_;

        for (int i = 0; i < 4; ++i)
        {
            this.field_78876_b[i] = new ArrayList();
        }
    }

    public void func_78873_a(EntityFX p_78873_1_)
    {
        int i = p_78873_1_.func_70537_b();

        if (this.field_78876_b[i].size() >= 4000)
        {
            this.field_78876_b[i].remove(0);
        }

        this.field_78876_b[i].add(p_78873_1_);
    }

    public void func_78868_a()
    {
        for (int k = 0; k < 4; ++k)
        {
            final int i = k;

            for (int j = 0; j < this.field_78876_b[i].size(); ++j)
            {
                final EntityFX entityfx = (EntityFX)this.field_78876_b[i].get(j);

                try
                {
                    entityfx.func_70071_h_();
                }
                catch (Throwable throwable)
                {
                    CrashReport crashreport = CrashReport.func_85055_a(throwable, "Ticking Particle");
                    CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being ticked");
                    crashreportcategory.func_71500_a("Particle", new Callable()
                    {
                        private static final String __OBFID = "CL_00000916";
                        public String call()
                        {
                            return entityfx.toString();
                        }
                    });
                    crashreportcategory.func_71500_a("Particle Type", new Callable()
                    {
                        private static final String __OBFID = "CL_00000917";
                        public String call()
                        {
                            return i == 0 ? "MISC_TEXTURE" : (i == 1 ? "TERRAIN_TEXTURE" : (i == 2 ? "ITEM_TEXTURE" : (i == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + i)));
                        }
                    });
                    throw new ReportedException(crashreport);
                }

                if (entityfx.field_70128_L)
                {
                    this.field_78876_b[i].remove(j--);
                }
            }
        }
    }

    public void func_78874_a(Entity p_78874_1_, float p_78874_2_)
    {
        float f1 = ActiveRenderInfo.field_74588_d;
        float f2 = ActiveRenderInfo.field_74586_f;
        float f3 = ActiveRenderInfo.field_74587_g;
        float f4 = ActiveRenderInfo.field_74596_h;
        float f5 = ActiveRenderInfo.field_74589_e;
        EntityFX.field_70556_an = p_78874_1_.field_70142_S + (p_78874_1_.field_70165_t - p_78874_1_.field_70142_S) * (double)p_78874_2_;
        EntityFX.field_70554_ao = p_78874_1_.field_70137_T + (p_78874_1_.field_70163_u - p_78874_1_.field_70137_T) * (double)p_78874_2_;
        EntityFX.field_70555_ap = p_78874_1_.field_70136_U + (p_78874_1_.field_70161_v - p_78874_1_.field_70136_U) * (double)p_78874_2_;

        for (int k = 0; k < 3; ++k)
        {
            final int i = k;

            if (!this.field_78876_b[i].isEmpty())
            {
                switch (i)
                {
                    case 0:
                    default:
                        this.field_78877_c.func_110577_a(field_110737_b);
                        break;
                    case 1:
                        this.field_78877_c.func_110577_a(TextureMap.field_110575_b);
                        break;
                    case 2:
                        this.field_78877_c.func_110577_a(TextureMap.field_110576_c);
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDepthMask(false);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
                Tessellator tessellator = Tessellator.field_78398_a;
                tessellator.func_78382_b();

                for (int j = 0; j < this.field_78876_b[i].size(); ++j)
                {
                    final EntityFX entityfx = (EntityFX)this.field_78876_b[i].get(j);
                    tessellator.func_78380_c(entityfx.func_70070_b(p_78874_2_));

                    try
                    {
                        entityfx.func_70539_a(tessellator, p_78874_2_, f1, f5, f2, f3, f4);
                    }
                    catch (Throwable throwable)
                    {
                        CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering Particle");
                        CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being rendered");
                        crashreportcategory.func_71500_a("Particle", new Callable()
                        {
                            private static final String __OBFID = "CL_00000918";
                            public String call()
                            {
                                return entityfx.toString();
                            }
                        });
                        crashreportcategory.func_71500_a("Particle Type", new Callable()
                        {
                            private static final String __OBFID = "CL_00000919";
                            public String call()
                            {
                                return i == 0 ? "MISC_TEXTURE" : (i == 1 ? "TERRAIN_TEXTURE" : (i == 2 ? "ITEM_TEXTURE" : (i == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + i)));
                            }
                        });
                        throw new ReportedException(crashreport);
                    }
                }

                tessellator.func_78381_a();
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glDepthMask(true);
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            }
        }
    }

    public void func_78872_b(Entity p_78872_1_, float p_78872_2_)
    {
        float f1 = 0.017453292F;
        float f2 = MathHelper.func_76134_b(p_78872_1_.field_70177_z * 0.017453292F);
        float f3 = MathHelper.func_76126_a(p_78872_1_.field_70177_z * 0.017453292F);
        float f4 = -f3 * MathHelper.func_76126_a(p_78872_1_.field_70125_A * 0.017453292F);
        float f5 = f2 * MathHelper.func_76126_a(p_78872_1_.field_70125_A * 0.017453292F);
        float f6 = MathHelper.func_76134_b(p_78872_1_.field_70125_A * 0.017453292F);
        byte b0 = 3;
        List list = this.field_78876_b[b0];

        if (!list.isEmpty())
        {
            Tessellator tessellator = Tessellator.field_78398_a;

            for (int i = 0; i < list.size(); ++i)
            {
                EntityFX entityfx = (EntityFX)list.get(i);
                tessellator.func_78380_c(entityfx.func_70070_b(p_78872_2_));
                entityfx.func_70539_a(tessellator, p_78872_2_, f2, f6, f3, f4, f5);
            }
        }
    }

    public void func_78870_a(World p_78870_1_)
    {
        this.field_78878_a = p_78870_1_;

        for (int i = 0; i < 4; ++i)
        {
            this.field_78876_b[i].clear();
        }
    }

    public void func_147215_a(int p_147215_1_, int p_147215_2_, int p_147215_3_, Block p_147215_4_, int p_147215_5_)
    {
        if (p_147215_4_.func_149688_o() != Material.field_151579_a)
        {
            byte b0 = 4;

            for (int i1 = 0; i1 < b0; ++i1)
            {
                for (int j1 = 0; j1 < b0; ++j1)
                {
                    for (int k1 = 0; k1 < b0; ++k1)
                    {
                        double d0 = (double)p_147215_1_ + ((double)i1 + 0.5D) / (double)b0;
                        double d1 = (double)p_147215_2_ + ((double)j1 + 0.5D) / (double)b0;
                        double d2 = (double)p_147215_3_ + ((double)k1 + 0.5D) / (double)b0;
                        this.func_78873_a((new EntityDiggingFX(this.field_78878_a, d0, d1, d2, d0 - (double)p_147215_1_ - 0.5D, d1 - (double)p_147215_2_ - 0.5D, d2 - (double)p_147215_3_ - 0.5D, p_147215_4_, p_147215_5_)).func_70596_a(p_147215_1_, p_147215_2_, p_147215_3_));
                    }
                }
            }
        }
    }

    public void func_78867_a(int p_78867_1_, int p_78867_2_, int p_78867_3_, int p_78867_4_)
    {
        Block block = this.field_78878_a.func_147439_a(p_78867_1_, p_78867_2_, p_78867_3_);

        if (block.func_149688_o() != Material.field_151579_a)
        {
            float f = 0.1F;
            double d0 = (double)p_78867_1_ + this.field_78875_d.nextDouble() * (block.func_149753_y() - block.func_149704_x() - (double)(f * 2.0F)) + (double)f + block.func_149704_x();
            double d1 = (double)p_78867_2_ + this.field_78875_d.nextDouble() * (block.func_149669_A() - block.func_149665_z() - (double)(f * 2.0F)) + (double)f + block.func_149665_z();
            double d2 = (double)p_78867_3_ + this.field_78875_d.nextDouble() * (block.func_149693_C() - block.func_149706_B() - (double)(f * 2.0F)) + (double)f + block.func_149706_B();

            if (p_78867_4_ == 0)
            {
                d1 = (double)p_78867_2_ + block.func_149665_z() - (double)f;
            }

            if (p_78867_4_ == 1)
            {
                d1 = (double)p_78867_2_ + block.func_149669_A() + (double)f;
            }

            if (p_78867_4_ == 2)
            {
                d2 = (double)p_78867_3_ + block.func_149706_B() - (double)f;
            }

            if (p_78867_4_ == 3)
            {
                d2 = (double)p_78867_3_ + block.func_149693_C() + (double)f;
            }

            if (p_78867_4_ == 4)
            {
                d0 = (double)p_78867_1_ + block.func_149704_x() - (double)f;
            }

            if (p_78867_4_ == 5)
            {
                d0 = (double)p_78867_1_ + block.func_149753_y() + (double)f;
            }

            this.func_78873_a((new EntityDiggingFX(this.field_78878_a, d0, d1, d2, 0.0D, 0.0D, 0.0D, block, this.field_78878_a.func_72805_g(p_78867_1_, p_78867_2_, p_78867_3_))).func_70596_a(p_78867_1_, p_78867_2_, p_78867_3_).func_70543_e(0.2F).func_70541_f(0.6F));
        }
    }

    public String func_78869_b()
    {
        return "" + (this.field_78876_b[0].size() + this.field_78876_b[1].size() + this.field_78876_b[2].size());
    }
}