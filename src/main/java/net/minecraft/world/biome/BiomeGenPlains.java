package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;

public class BiomeGenPlains extends BiomeGenBase
{
    protected boolean field_150628_aC;
    private static final String __OBFID = "CL_00000180";

    public BiomeGenPlains(int p_i1986_1_)
    {
        super(p_i1986_1_);
        this.func_76732_a(0.8F, 0.4F);
        this.func_150570_a(field_150593_e);
        this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.field_76760_I.field_76832_z = -999;
        this.field_76760_I.field_76802_A = 4;
        this.field_76760_I.field_76803_B = 10;
    }

    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_)
    {
        double d0 = field_150606_ad.func_151601_a((double)p_150572_2_ / 200.0D, (double)p_150572_4_ / 200.0D);
        int l;

        if (d0 < -0.8D)
        {
            l = p_150572_1_.nextInt(4);
            return BlockFlower.field_149859_a[4 + l];
        }
        else if (p_150572_1_.nextInt(3) > 0)
        {
            l = p_150572_1_.nextInt(3);
            return l == 0 ? BlockFlower.field_149859_a[0] : (l == 1 ? BlockFlower.field_149859_a[3] : BlockFlower.field_149859_a[8]);
        }
        else
        {
            return BlockFlower.field_149858_b[0];
        }
    }

    public void func_76728_a(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        double d0 = field_150606_ad.func_151601_a((double)(p_76728_3_ + 8) / 200.0D, (double)(p_76728_4_ + 8) / 200.0D);
        int k;
        int l;
        int i1;
        int j1;

        if (d0 < -0.8D)
        {
            this.field_76760_I.field_76802_A = 15;
            this.field_76760_I.field_76803_B = 5;
        }
        else
        {
            this.field_76760_I.field_76802_A = 4;
            this.field_76760_I.field_76803_B = 10;
            field_150610_ae.func_150548_a(2);

            for (k = 0; k < 7; ++k)
            {
                l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
                i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
                j1 = p_76728_2_.nextInt(p_76728_1_.func_72976_f(l, i1) + 32);
                field_150610_ae.func_76484_a(p_76728_1_, p_76728_2_, l, j1, i1);
            }
        }

        if (this.field_150628_aC)
        {
            field_150610_ae.func_150548_a(0);

            for (k = 0; k < 10; ++k)
            {
                l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
                i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
                j1 = p_76728_2_.nextInt(p_76728_1_.func_72976_f(l, i1) + 32);
                field_150610_ae.func_76484_a(p_76728_1_, p_76728_2_, l, j1, i1);
            }
        }

        super.func_76728_a(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    }

    public BiomeGenBase func_150566_k()
    {
        BiomeGenPlains biomegenplains = new BiomeGenPlains(this.field_76756_M + 128);
        biomegenplains.func_76735_a("Sunflower Plains");
        biomegenplains.field_150628_aC = true;
        biomegenplains.func_76739_b(9286496);
        biomegenplains.field_150609_ah = 14273354;
        return biomegenplains;
    }
}