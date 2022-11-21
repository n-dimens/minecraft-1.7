package net.minecraft.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Explosion
{
    public boolean field_77286_a;
    public boolean field_82755_b = true;
    private int field_77289_h = 16;
    private Random field_77290_i = new Random();
    private World field_77287_j;
    public double field_77284_b;
    public double field_77285_c;
    public double field_77282_d;
    public Entity field_77283_e;
    public float field_77280_f;
    public List field_77281_g = new ArrayList();
    private Map field_77288_k = new HashMap();
    private static final String __OBFID = "CL_00000134";

    public Explosion(World p_i1948_1_, Entity p_i1948_2_, double p_i1948_3_, double p_i1948_5_, double p_i1948_7_, float p_i1948_9_)
    {
        this.field_77287_j = p_i1948_1_;
        this.field_77283_e = p_i1948_2_;
        this.field_77280_f = p_i1948_9_;
        this.field_77284_b = p_i1948_3_;
        this.field_77285_c = p_i1948_5_;
        this.field_77282_d = p_i1948_7_;
    }

    public void func_77278_a()
    {
        float f = this.field_77280_f;
        HashSet hashset = new HashSet();
        int i;
        int j;
        int k;
        double d5;
        double d6;
        double d7;

        for (i = 0; i < this.field_77289_h; ++i)
        {
            for (j = 0; j < this.field_77289_h; ++j)
            {
                for (k = 0; k < this.field_77289_h; ++k)
                {
                    if (i == 0 || i == this.field_77289_h - 1 || j == 0 || j == this.field_77289_h - 1 || k == 0 || k == this.field_77289_h - 1)
                    {
                        double d0 = (double)((float)i / ((float)this.field_77289_h - 1.0F) * 2.0F - 1.0F);
                        double d1 = (double)((float)j / ((float)this.field_77289_h - 1.0F) * 2.0F - 1.0F);
                        double d2 = (double)((float)k / ((float)this.field_77289_h - 1.0F) * 2.0F - 1.0F);
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 /= d3;
                        d1 /= d3;
                        d2 /= d3;
                        float f1 = this.field_77280_f * (0.7F + this.field_77287_j.field_73012_v.nextFloat() * 0.6F);
                        d5 = this.field_77284_b;
                        d6 = this.field_77285_c;
                        d7 = this.field_77282_d;

                        for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F)
                        {
                            int j1 = MathHelper.func_76128_c(d5);
                            int k1 = MathHelper.func_76128_c(d6);
                            int l1 = MathHelper.func_76128_c(d7);
                            Block block = this.field_77287_j.func_147439_a(j1, k1, l1);

                            if (block.func_149688_o() != Material.field_151579_a)
                            {
                                float f3 = this.field_77283_e != null ? this.field_77283_e.func_145772_a(this, this.field_77287_j, j1, k1, l1, block) : block.func_149638_a(this.field_77283_e);
                                f1 -= (f3 + 0.3F) * f2;
                            }

                            if (f1 > 0.0F && (this.field_77283_e == null || this.field_77283_e.func_145774_a(this, this.field_77287_j, j1, k1, l1, block, f1)))
                            {
                                hashset.add(new ChunkPosition(j1, k1, l1));
                            }

                            d5 += d0 * (double)f2;
                            d6 += d1 * (double)f2;
                            d7 += d2 * (double)f2;
                        }
                    }
                }
            }
        }

        this.field_77281_g.addAll(hashset);
        this.field_77280_f *= 2.0F;
        i = MathHelper.func_76128_c(this.field_77284_b - (double)this.field_77280_f - 1.0D);
        j = MathHelper.func_76128_c(this.field_77284_b + (double)this.field_77280_f + 1.0D);
        k = MathHelper.func_76128_c(this.field_77285_c - (double)this.field_77280_f - 1.0D);
        int i2 = MathHelper.func_76128_c(this.field_77285_c + (double)this.field_77280_f + 1.0D);
        int l = MathHelper.func_76128_c(this.field_77282_d - (double)this.field_77280_f - 1.0D);
        int j2 = MathHelper.func_76128_c(this.field_77282_d + (double)this.field_77280_f + 1.0D);
        List list = this.field_77287_j.func_72839_b(this.field_77283_e, AxisAlignedBB.func_72330_a((double)i, (double)k, (double)l, (double)j, (double)i2, (double)j2));
        Vec3 vec3 = Vec3.func_72443_a(this.field_77284_b, this.field_77285_c, this.field_77282_d);

        for (int i1 = 0; i1 < list.size(); ++i1)
        {
            Entity entity = (Entity)list.get(i1);
            double d4 = entity.func_70011_f(this.field_77284_b, this.field_77285_c, this.field_77282_d) / (double)this.field_77280_f;

            if (d4 <= 1.0D)
            {
                d5 = entity.field_70165_t - this.field_77284_b;
                d6 = entity.field_70163_u + (double)entity.func_70047_e() - this.field_77285_c;
                d7 = entity.field_70161_v - this.field_77282_d;
                double d9 = (double)MathHelper.func_76133_a(d5 * d5 + d6 * d6 + d7 * d7);

                if (d9 != 0.0D)
                {
                    d5 /= d9;
                    d6 /= d9;
                    d7 /= d9;
                    double d10 = (double)this.field_77287_j.func_72842_a(vec3, entity.field_70121_D);
                    double d11 = (1.0D - d4) * d10;
                    entity.func_70097_a(DamageSource.func_94539_a(this), (float)((int)((d11 * d11 + d11) / 2.0D * 8.0D * (double)this.field_77280_f + 1.0D)));
                    double d8 = EnchantmentProtection.func_92092_a(entity, d11);
                    entity.field_70159_w += d5 * d8;
                    entity.field_70181_x += d6 * d8;
                    entity.field_70179_y += d7 * d8;

                    if (entity instanceof EntityPlayer)
                    {
                        this.field_77288_k.put((EntityPlayer)entity, Vec3.func_72443_a(d5 * d11, d6 * d11, d7 * d11));
                    }
                }
            }
        }

        this.field_77280_f = f;
    }

    public void func_77279_a(boolean p_77279_1_)
    {
        this.field_77287_j.func_72908_a(this.field_77284_b, this.field_77285_c, this.field_77282_d, "random.explode", 4.0F, (1.0F + (this.field_77287_j.field_73012_v.nextFloat() - this.field_77287_j.field_73012_v.nextFloat()) * 0.2F) * 0.7F);

        if (this.field_77280_f >= 2.0F && this.field_82755_b)
        {
            this.field_77287_j.func_72869_a("hugeexplosion", this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0D, 0.0D, 0.0D);
        }
        else
        {
            this.field_77287_j.func_72869_a("largeexplode", this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0D, 0.0D, 0.0D);
        }

        Iterator iterator;
        ChunkPosition chunkposition;
        int i;
        int j;
        int k;
        Block block;

        if (this.field_82755_b)
        {
            iterator = this.field_77281_g.iterator();

            while (iterator.hasNext())
            {
                chunkposition = (ChunkPosition)iterator.next();
                i = chunkposition.field_151329_a;
                j = chunkposition.field_151327_b;
                k = chunkposition.field_151328_c;
                block = this.field_77287_j.func_147439_a(i, j, k);

                if (p_77279_1_)
                {
                    double d0 = (double)((float)i + this.field_77287_j.field_73012_v.nextFloat());
                    double d1 = (double)((float)j + this.field_77287_j.field_73012_v.nextFloat());
                    double d2 = (double)((float)k + this.field_77287_j.field_73012_v.nextFloat());
                    double d3 = d0 - this.field_77284_b;
                    double d4 = d1 - this.field_77285_c;
                    double d5 = d2 - this.field_77282_d;
                    double d6 = (double)MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    double d7 = 0.5D / (d6 / (double)this.field_77280_f + 0.1D);
                    d7 *= (double)(this.field_77287_j.field_73012_v.nextFloat() * this.field_77287_j.field_73012_v.nextFloat() + 0.3F);
                    d3 *= d7;
                    d4 *= d7;
                    d5 *= d7;
                    this.field_77287_j.func_72869_a("explode", (d0 + this.field_77284_b * 1.0D) / 2.0D, (d1 + this.field_77285_c * 1.0D) / 2.0D, (d2 + this.field_77282_d * 1.0D) / 2.0D, d3, d4, d5);
                    this.field_77287_j.func_72869_a("smoke", d0, d1, d2, d3, d4, d5);
                }

                if (block.func_149688_o() != Material.field_151579_a)
                {
                    if (block.func_149659_a(this))
                    {
                        block.func_149690_a(this.field_77287_j, i, j, k, this.field_77287_j.func_72805_g(i, j, k), 1.0F / this.field_77280_f, 0);
                    }

                    this.field_77287_j.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
                    block.func_149723_a(this.field_77287_j, i, j, k, this);
                }
            }
        }

        if (this.field_77286_a)
        {
            iterator = this.field_77281_g.iterator();

            while (iterator.hasNext())
            {
                chunkposition = (ChunkPosition)iterator.next();
                i = chunkposition.field_151329_a;
                j = chunkposition.field_151327_b;
                k = chunkposition.field_151328_c;
                block = this.field_77287_j.func_147439_a(i, j, k);
                Block block1 = this.field_77287_j.func_147439_a(i, j - 1, k);

                if (block.func_149688_o() == Material.field_151579_a && block1.func_149730_j() && this.field_77290_i.nextInt(3) == 0)
                {
                    this.field_77287_j.func_147449_b(i, j, k, Blocks.field_150480_ab);
                }
            }
        }
    }

    public Map func_77277_b()
    {
        return this.field_77288_k;
    }

    public EntityLivingBase func_94613_c()
    {
        return this.field_77283_e == null ? null : (this.field_77283_e instanceof EntityTNTPrimed ? ((EntityTNTPrimed)this.field_77283_e).func_94083_c() : (this.field_77283_e instanceof EntityLivingBase ? (EntityLivingBase)this.field_77283_e : null));
    }
}