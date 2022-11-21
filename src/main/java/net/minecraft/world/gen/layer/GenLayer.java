package net.minecraft.world.gen.layer;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class GenLayer
{
    private long field_75907_b;
    protected GenLayer field_75909_a;
    private long field_75908_c;
    protected long field_75906_d;
    private static final String __OBFID = "CL_00000559";

    public static GenLayer[] func_75901_a(long p_75901_0_, WorldType p_75901_2_)
    {
        boolean flag = false;
        GenLayerIsland genlayerisland = new GenLayerIsland(1L);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
        GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
        GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        genlayeraddisland = new GenLayerAddIsland(3L, genlayeraddsnow);
        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, GenLayerEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
        genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);
        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer2 = GenLayerZoom.func_75915_a(1000L, genlayerdeepocean, 0);
        byte b0 = 4;

        if (p_75901_2_ == WorldType.field_77135_d)
        {
            b0 = 6;
        }

        if (flag)
        {
            b0 = 4;
        }

        GenLayer genlayer = GenLayerZoom.func_75915_a(1000L, genlayer2, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        Object object = new GenLayerBiome(200L, genlayer2, p_75901_2_);

        if (!flag)
        {
            GenLayer genlayer3 = GenLayerZoom.func_75915_a(1000L, (GenLayer)object, 2);
            object = new GenLayerBiomeEdge(1000L, genlayer3);
        }

        GenLayer genlayer1 = GenLayerZoom.func_75915_a(1000L, genlayerriverinit, 2);
        GenLayerHills genlayerhills = new GenLayerHills(1000L, (GenLayer)object, genlayer1);
        genlayer = GenLayerZoom.func_75915_a(1000L, genlayerriverinit, 2);
        genlayer = GenLayerZoom.func_75915_a(1000L, genlayer, b0);
        GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        object = new GenLayerRareBiome(1001L, genlayerhills);

        for (int j = 0; j < b0; ++j)
        {
            object = new GenLayerZoom((long)(1000 + j), (GenLayer)object);

            if (j == 0)
            {
                object = new GenLayerAddIsland(3L, (GenLayer)object);
            }

            if (j == 1)
            {
                object = new GenLayerShore(1000L, (GenLayer)object);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)object);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.func_75905_a(p_75901_0_);
        genlayervoronoizoom.func_75905_a(p_75901_0_);
        return new GenLayer[] {genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
    }

    public GenLayer(long p_i2125_1_)
    {
        this.field_75906_d = p_i2125_1_;
        this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
        this.field_75906_d += p_i2125_1_;
        this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
        this.field_75906_d += p_i2125_1_;
        this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
        this.field_75906_d += p_i2125_1_;
    }

    public void func_75905_a(long p_75905_1_)
    {
        this.field_75907_b = p_75905_1_;

        if (this.field_75909_a != null)
        {
            this.field_75909_a.func_75905_a(p_75905_1_);
        }

        this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
        this.field_75907_b += this.field_75906_d;
        this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
        this.field_75907_b += this.field_75906_d;
        this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
        this.field_75907_b += this.field_75906_d;
    }

    public void func_75903_a(long p_75903_1_, long p_75903_3_)
    {
        this.field_75908_c = this.field_75907_b;
        this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
        this.field_75908_c += p_75903_1_;
        this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
        this.field_75908_c += p_75903_3_;
        this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
        this.field_75908_c += p_75903_1_;
        this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
        this.field_75908_c += p_75903_3_;
    }

    protected int func_75902_a(int p_75902_1_)
    {
        int j = (int)((this.field_75908_c >> 24) % (long)p_75902_1_);

        if (j < 0)
        {
            j += p_75902_1_;
        }

        this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
        this.field_75908_c += this.field_75907_b;
        return j;
    }

    public abstract int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_);

    protected static boolean func_151616_a(final int p_151616_0_, final int p_151616_1_)
    {
        if (p_151616_0_ == p_151616_1_)
        {
            return true;
        }
        else if (p_151616_0_ != BiomeGenBase.field_150607_aa.field_76756_M && p_151616_0_ != BiomeGenBase.field_150608_ab.field_76756_M)
        {
            try
            {
                return BiomeGenBase.func_150568_d(p_151616_0_) != null && BiomeGenBase.func_150568_d(p_151616_1_) != null ? BiomeGenBase.func_150568_d(p_151616_0_).func_150569_a(BiomeGenBase.func_150568_d(p_151616_1_)) : false;
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.func_85055_a(throwable, "Comparing biomes");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Biomes being compared");
                crashreportcategory.func_71507_a("Biome A ID", Integer.valueOf(p_151616_0_));
                crashreportcategory.func_71507_a("Biome B ID", Integer.valueOf(p_151616_1_));
                crashreportcategory.func_71500_a("Biome A", new Callable()
                {
                    private static final String __OBFID = "CL_00000560";
                    public String call()
                    {
                        return String.valueOf(BiomeGenBase.func_150568_d(p_151616_0_));
                    }
                });
                crashreportcategory.func_71500_a("Biome B", new Callable()
                {
                    private static final String __OBFID = "CL_00000561";
                    public String call()
                    {
                        return String.valueOf(BiomeGenBase.func_150568_d(p_151616_1_));
                    }
                });
                throw new ReportedException(crashreport);
            }
        }
        else
        {
            return p_151616_1_ == BiomeGenBase.field_150607_aa.field_76756_M || p_151616_1_ == BiomeGenBase.field_150608_ab.field_76756_M;
        }
    }

    protected static boolean func_151618_b(int p_151618_0_)
    {
        return p_151618_0_ == BiomeGenBase.field_76771_b.field_76756_M || p_151618_0_ == BiomeGenBase.field_150575_M.field_76756_M || p_151618_0_ == BiomeGenBase.field_76776_l.field_76756_M;
    }

    protected int func_151619_a(int ... p_151619_1_)
    {
        return p_151619_1_[this.func_75902_a(p_151619_1_.length)];
    }

    protected int func_151617_b(int p_151617_1_, int p_151617_2_, int p_151617_3_, int p_151617_4_)
    {
        return p_151617_2_ == p_151617_3_ && p_151617_3_ == p_151617_4_ ? p_151617_2_ : (p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_3_ ? p_151617_1_ : (p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_4_ ? p_151617_1_ : (p_151617_1_ == p_151617_3_ && p_151617_1_ == p_151617_4_ ? p_151617_1_ : (p_151617_1_ == p_151617_2_ && p_151617_3_ != p_151617_4_ ? p_151617_1_ : (p_151617_1_ == p_151617_3_ && p_151617_2_ != p_151617_4_ ? p_151617_1_ : (p_151617_1_ == p_151617_4_ && p_151617_2_ != p_151617_3_ ? p_151617_1_ : (p_151617_2_ == p_151617_3_ && p_151617_1_ != p_151617_4_ ? p_151617_2_ : (p_151617_2_ == p_151617_4_ && p_151617_1_ != p_151617_3_ ? p_151617_2_ : (p_151617_3_ == p_151617_4_ && p_151617_1_ != p_151617_2_ ? p_151617_3_ : this.func_151619_a(new int[] {p_151617_1_, p_151617_2_, p_151617_3_, p_151617_4_}))))))))));
    }
}