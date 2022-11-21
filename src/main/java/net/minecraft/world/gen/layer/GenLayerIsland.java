package net.minecraft.world.gen.layer;

public class GenLayerIsland extends GenLayer
{
    private static final String __OBFID = "CL_00000558";

    public GenLayerIsland(long p_i2124_1_)
    {
        super(p_i2124_1_);
    }

    public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        int[] aint = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

        for (int i1 = 0; i1 < p_75904_4_; ++i1)
        {
            for (int j1 = 0; j1 < p_75904_3_; ++j1)
            {
                this.func_75903_a((long)(p_75904_1_ + j1), (long)(p_75904_2_ + i1));
                aint[j1 + i1 * p_75904_3_] = this.func_75902_a(10) == 0 ? 1 : 0;
            }
        }

        if (p_75904_1_ > -p_75904_3_ && p_75904_1_ <= 0 && p_75904_2_ > -p_75904_4_ && p_75904_2_ <= 0)
        {
            aint[-p_75904_1_ + -p_75904_2_ * p_75904_3_] = 1;
        }

        return aint;
    }
}