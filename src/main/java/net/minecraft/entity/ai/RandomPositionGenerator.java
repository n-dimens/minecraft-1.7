package net.minecraft.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RandomPositionGenerator
{
    private static Vec3 field_75465_a = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
    private static final String __OBFID = "CL_00001629";

    public static Vec3 func_75463_a(EntityCreature p_75463_0_, int p_75463_1_, int p_75463_2_)
    {
        return func_75462_c(p_75463_0_, p_75463_1_, p_75463_2_, (Vec3)null);
    }

    public static Vec3 func_75464_a(EntityCreature p_75464_0_, int p_75464_1_, int p_75464_2_, Vec3 p_75464_3_)
    {
        field_75465_a.field_72450_a = p_75464_3_.field_72450_a - p_75464_0_.field_70165_t;
        field_75465_a.field_72448_b = p_75464_3_.field_72448_b - p_75464_0_.field_70163_u;
        field_75465_a.field_72449_c = p_75464_3_.field_72449_c - p_75464_0_.field_70161_v;
        return func_75462_c(p_75464_0_, p_75464_1_, p_75464_2_, field_75465_a);
    }

    public static Vec3 func_75461_b(EntityCreature p_75461_0_, int p_75461_1_, int p_75461_2_, Vec3 p_75461_3_)
    {
        field_75465_a.field_72450_a = p_75461_0_.field_70165_t - p_75461_3_.field_72450_a;
        field_75465_a.field_72448_b = p_75461_0_.field_70163_u - p_75461_3_.field_72448_b;
        field_75465_a.field_72449_c = p_75461_0_.field_70161_v - p_75461_3_.field_72449_c;
        return func_75462_c(p_75461_0_, p_75461_1_, p_75461_2_, field_75465_a);
    }

    private static Vec3 func_75462_c(EntityCreature p_75462_0_, int p_75462_1_, int p_75462_2_, Vec3 p_75462_3_)
    {
        Random random = p_75462_0_.func_70681_au();
        boolean flag = false;
        int k = 0;
        int l = 0;
        int i1 = 0;
        float f = -99999.0F;
        boolean flag1;

        if (p_75462_0_.func_110175_bO())
        {
            double d0 = (double)(p_75462_0_.func_110172_bL().func_71569_e(MathHelper.func_76128_c(p_75462_0_.field_70165_t), MathHelper.func_76128_c(p_75462_0_.field_70163_u), MathHelper.func_76128_c(p_75462_0_.field_70161_v)) + 4.0F);
            double d1 = (double)(p_75462_0_.func_110174_bM() + (float)p_75462_1_);
            flag1 = d0 < d1 * d1;
        }
        else
        {
            flag1 = false;
        }

        for (int l1 = 0; l1 < 10; ++l1)
        {
            int j1 = random.nextInt(2 * p_75462_1_) - p_75462_1_;
            int i2 = random.nextInt(2 * p_75462_2_) - p_75462_2_;
            int k1 = random.nextInt(2 * p_75462_1_) - p_75462_1_;

            if (p_75462_3_ == null || (double)j1 * p_75462_3_.field_72450_a + (double)k1 * p_75462_3_.field_72449_c >= 0.0D)
            {
                j1 += MathHelper.func_76128_c(p_75462_0_.field_70165_t);
                i2 += MathHelper.func_76128_c(p_75462_0_.field_70163_u);
                k1 += MathHelper.func_76128_c(p_75462_0_.field_70161_v);

                if (!flag1 || p_75462_0_.func_110176_b(j1, i2, k1))
                {
                    float f1 = p_75462_0_.func_70783_a(j1, i2, k1);

                    if (f1 > f)
                    {
                        f = f1;
                        k = j1;
                        l = i2;
                        i1 = k1;
                        flag = true;
                    }
                }
            }
        }

        if (flag)
        {
            return Vec3.func_72443_a((double)k, (double)l, (double)i1);
        }
        else
        {
            return null;
        }
    }
}