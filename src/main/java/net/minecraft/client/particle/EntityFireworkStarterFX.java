package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityFireworkStarterFX extends EntityFX
{
    private int field_92042_ax;
    private final EffectRenderer field_92040_ay;
    private NBTTagList field_92039_az;
    boolean field_92041_a;
    private static final String __OBFID = "CL_00000906";

    public EntityFireworkStarterFX(World p_i1208_1_, double p_i1208_2_, double p_i1208_4_, double p_i1208_6_, double p_i1208_8_, double p_i1208_10_, double p_i1208_12_, EffectRenderer p_i1208_14_, NBTTagCompound p_i1208_15_)
    {
        super(p_i1208_1_, p_i1208_2_, p_i1208_4_, p_i1208_6_, 0.0D, 0.0D, 0.0D);
        this.field_70159_w = p_i1208_8_;
        this.field_70181_x = p_i1208_10_;
        this.field_70179_y = p_i1208_12_;
        this.field_92040_ay = p_i1208_14_;
        this.field_70547_e = 8;

        if (p_i1208_15_ != null)
        {
            this.field_92039_az = p_i1208_15_.func_150295_c("Explosions", 10);

            if (this.field_92039_az.func_74745_c() == 0)
            {
                this.field_92039_az = null;
            }
            else
            {
                this.field_70547_e = this.field_92039_az.func_74745_c() * 2 - 1;

                for (int i = 0; i < this.field_92039_az.func_74745_c(); ++i)
                {
                    NBTTagCompound nbttagcompound1 = this.field_92039_az.func_150305_b(i);

                    if (nbttagcompound1.func_74767_n("Flicker"))
                    {
                        this.field_92041_a = true;
                        this.field_70547_e += 15;
                        break;
                    }
                }
            }
        }
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {}

    public void func_70071_h_()
    {
        boolean flag;

        if (this.field_92042_ax == 0 && this.field_92039_az != null)
        {
            flag = this.func_92037_i();
            boolean flag1 = false;

            if (this.field_92039_az.func_74745_c() >= 3)
            {
                flag1 = true;
            }
            else
            {
                for (int i = 0; i < this.field_92039_az.func_74745_c(); ++i)
                {
                    NBTTagCompound nbttagcompound = this.field_92039_az.func_150305_b(i);

                    if (nbttagcompound.func_74771_c("Type") == 1)
                    {
                        flag1 = true;
                        break;
                    }
                }
            }

            String s1 = "fireworks." + (flag1 ? "largeBlast" : "blast") + (flag ? "_far" : "");
            this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, s1, 20.0F, 0.95F + this.field_70146_Z.nextFloat() * 0.1F, true);
        }

        if (this.field_92042_ax % 2 == 0 && this.field_92039_az != null && this.field_92042_ax / 2 < this.field_92039_az.func_74745_c())
        {
            int k = this.field_92042_ax / 2;
            NBTTagCompound nbttagcompound1 = this.field_92039_az.func_150305_b(k);
            byte b0 = nbttagcompound1.func_74771_c("Type");
            boolean flag3 = nbttagcompound1.func_74767_n("Trail");
            boolean flag2 = nbttagcompound1.func_74767_n("Flicker");
            int[] aint = nbttagcompound1.func_74759_k("Colors");
            int[] aint1 = nbttagcompound1.func_74759_k("FadeColors");

            if (b0 == 1)
            {
                this.func_92035_a(0.5D, 4, aint, aint1, flag3, flag2);
            }
            else if (b0 == 2)
            {
                this.func_92038_a(0.5D, new double[][] {{0.0D, 1.0D}, {0.3455D, 0.309D}, {0.9511D, 0.309D}, {0.3795918367346939D, -0.12653061224489795D}, {0.6122448979591837D, -0.8040816326530612D}, {0.0D, -0.35918367346938773D}}, aint, aint1, flag3, flag2, false);
            }
            else if (b0 == 3)
            {
                this.func_92038_a(0.5D, new double[][] {{0.0D, 0.2D}, {0.2D, 0.2D}, {0.2D, 0.6D}, {0.6D, 0.6D}, {0.6D, 0.2D}, {0.2D, 0.2D}, {0.2D, 0.0D}, {0.4D, 0.0D}, {0.4D, -0.6D}, {0.2D, -0.6D}, {0.2D, -0.4D}, {0.0D, -0.4D}}, aint, aint1, flag3, flag2, true);
            }
            else if (b0 == 4)
            {
                this.func_92036_a(aint, aint1, flag3, flag2);
            }
            else
            {
                this.func_92035_a(0.25D, 2, aint, aint1, flag3, flag2);
            }

            int j = aint[0];
            float f = (float)((j & 16711680) >> 16) / 255.0F;
            float f1 = (float)((j & 65280) >> 8) / 255.0F;
            float f2 = (float)((j & 255) >> 0) / 255.0F;
            EntityFireworkOverlayFX entityfireworkoverlayfx = new EntityFireworkOverlayFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            entityfireworkoverlayfx.func_70538_b(f, f1, f2);
            this.field_92040_ay.func_78873_a(entityfireworkoverlayfx);
        }

        ++this.field_92042_ax;

        if (this.field_92042_ax > this.field_70547_e)
        {
            if (this.field_92041_a)
            {
                flag = this.func_92037_i();
                String s = "fireworks." + (flag ? "twinkle_far" : "twinkle");
                this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, s, 20.0F, 0.9F + this.field_70146_Z.nextFloat() * 0.15F, true);
            }

            this.func_70106_y();
        }
    }

    private boolean func_92037_i()
    {
        Minecraft minecraft = Minecraft.func_71410_x();
        return minecraft == null || minecraft.field_71451_h == null || minecraft.field_71451_h.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v) >= 256.0D;
    }

    private void func_92034_a(double p_92034_1_, double p_92034_3_, double p_92034_5_, double p_92034_7_, double p_92034_9_, double p_92034_11_, int[] p_92034_13_, int[] p_92034_14_, boolean p_92034_15_, boolean p_92034_16_)
    {
        EntityFireworkSparkFX entityfireworksparkfx = new EntityFireworkSparkFX(this.field_70170_p, p_92034_1_, p_92034_3_, p_92034_5_, p_92034_7_, p_92034_9_, p_92034_11_, this.field_92040_ay);
        entityfireworksparkfx.func_92045_e(p_92034_15_);
        entityfireworksparkfx.func_92043_f(p_92034_16_);
        int i = this.field_70146_Z.nextInt(p_92034_13_.length);
        entityfireworksparkfx.func_92044_a(p_92034_13_[i]);

        if (p_92034_14_ != null && p_92034_14_.length > 0)
        {
            entityfireworksparkfx.func_92046_g(p_92034_14_[this.field_70146_Z.nextInt(p_92034_14_.length)]);
        }

        this.field_92040_ay.func_78873_a(entityfireworksparkfx);
    }

    private void func_92035_a(double p_92035_1_, int p_92035_3_, int[] p_92035_4_, int[] p_92035_5_, boolean p_92035_6_, boolean p_92035_7_)
    {
        double d1 = this.field_70165_t;
        double d2 = this.field_70163_u;
        double d3 = this.field_70161_v;

        for (int j = -p_92035_3_; j <= p_92035_3_; ++j)
        {
            for (int k = -p_92035_3_; k <= p_92035_3_; ++k)
            {
                for (int l = -p_92035_3_; l <= p_92035_3_; ++l)
                {
                    double d4 = (double)k + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * 0.5D;
                    double d5 = (double)j + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * 0.5D;
                    double d6 = (double)l + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * 0.5D;
                    double d7 = (double)MathHelper.func_76133_a(d4 * d4 + d5 * d5 + d6 * d6) / p_92035_1_ + this.field_70146_Z.nextGaussian() * 0.05D;
                    this.func_92034_a(d1, d2, d3, d4 / d7, d5 / d7, d6 / d7, p_92035_4_, p_92035_5_, p_92035_6_, p_92035_7_);

                    if (j != -p_92035_3_ && j != p_92035_3_ && k != -p_92035_3_ && k != p_92035_3_)
                    {
                        l += p_92035_3_ * 2 - 1;
                    }
                }
            }
        }
    }

    private void func_92038_a(double p_92038_1_, double[][] p_92038_3_, int[] p_92038_4_, int[] p_92038_5_, boolean p_92038_6_, boolean p_92038_7_, boolean p_92038_8_)
    {
        double d1 = p_92038_3_[0][0];
        double d2 = p_92038_3_[0][1];
        this.func_92034_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, d1 * p_92038_1_, d2 * p_92038_1_, 0.0D, p_92038_4_, p_92038_5_, p_92038_6_, p_92038_7_);
        float f = this.field_70146_Z.nextFloat() * (float)Math.PI;
        double d3 = p_92038_8_ ? 0.034D : 0.34D;

        for (int i = 0; i < 3; ++i)
        {
            double d4 = (double)f + (double)((float)i * (float)Math.PI) * d3;
            double d5 = d1;
            double d6 = d2;

            for (int j = 1; j < p_92038_3_.length; ++j)
            {
                double d7 = p_92038_3_[j][0];
                double d8 = p_92038_3_[j][1];

                for (double d9 = 0.25D; d9 <= 1.0D; d9 += 0.25D)
                {
                    double d10 = (d5 + (d7 - d5) * d9) * p_92038_1_;
                    double d11 = (d6 + (d8 - d6) * d9) * p_92038_1_;
                    double d12 = d10 * Math.sin(d4);
                    d10 *= Math.cos(d4);

                    for (double d13 = -1.0D; d13 <= 1.0D; d13 += 2.0D)
                    {
                        this.func_92034_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, d10 * d13, d11, d12 * d13, p_92038_4_, p_92038_5_, p_92038_6_, p_92038_7_);
                    }
                }

                d5 = d7;
                d6 = d8;
            }
        }
    }

    private void func_92036_a(int[] p_92036_1_, int[] p_92036_2_, boolean p_92036_3_, boolean p_92036_4_)
    {
        double d0 = this.field_70146_Z.nextGaussian() * 0.05D;
        double d1 = this.field_70146_Z.nextGaussian() * 0.05D;

        for (int i = 0; i < 70; ++i)
        {
            double d2 = this.field_70159_w * 0.5D + this.field_70146_Z.nextGaussian() * 0.15D + d0;
            double d3 = this.field_70179_y * 0.5D + this.field_70146_Z.nextGaussian() * 0.15D + d1;
            double d4 = this.field_70181_x * 0.5D + this.field_70146_Z.nextDouble() * 0.5D;
            this.func_92034_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, d2, d4, d3, p_92036_1_, p_92036_2_, p_92036_3_, p_92036_4_);
        }
    }

    public int func_70537_b()
    {
        return 0;
    }
}