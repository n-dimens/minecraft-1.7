package net.minecraft.world.biome;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.LongHashMap;

public class BiomeCache
{
    private final WorldChunkManager field_76844_a;
    private long field_76842_b;
    private LongHashMap field_76843_c = new LongHashMap();
    private List field_76841_d = new ArrayList();
    private static final String __OBFID = "CL_00000162";

    public BiomeCache(WorldChunkManager p_i1973_1_)
    {
        this.field_76844_a = p_i1973_1_;
    }

    public BiomeCache.Block func_76840_a(int p_76840_1_, int p_76840_2_)
    {
        p_76840_1_ >>= 4;
        p_76840_2_ >>= 4;
        long k = (long)p_76840_1_ & 4294967295L | ((long)p_76840_2_ & 4294967295L) << 32;
        BiomeCache.Block block = (BiomeCache.Block)this.field_76843_c.func_76164_a(k);

        if (block == null)
        {
            block = new BiomeCache.Block(p_76840_1_, p_76840_2_);
            this.field_76843_c.func_76163_a(k, block);
            this.field_76841_d.add(block);
        }

        block.field_76886_f = MinecraftServer.func_130071_aq();
        return block;
    }

    public BiomeGenBase func_76837_b(int p_76837_1_, int p_76837_2_)
    {
        return this.func_76840_a(p_76837_1_, p_76837_2_).func_76885_a(p_76837_1_, p_76837_2_);
    }

    public void func_76838_a()
    {
        long i = MinecraftServer.func_130071_aq();
        long j = i - this.field_76842_b;

        if (j > 7500L || j < 0L)
        {
            this.field_76842_b = i;

            for (int k = 0; k < this.field_76841_d.size(); ++k)
            {
                BiomeCache.Block block = (BiomeCache.Block)this.field_76841_d.get(k);
                long l = i - block.field_76886_f;

                if (l > 30000L || l < 0L)
                {
                    this.field_76841_d.remove(k--);
                    long i1 = (long)block.field_76888_d & 4294967295L | ((long)block.field_76889_e & 4294967295L) << 32;
                    this.field_76843_c.func_76159_d(i1);
                }
            }
        }
    }

    public BiomeGenBase[] func_76839_e(int p_76839_1_, int p_76839_2_)
    {
        return this.func_76840_a(p_76839_1_, p_76839_2_).field_76891_c;
    }

    public class Block
    {
        public float[] field_76890_b = new float[256];
        public BiomeGenBase[] field_76891_c = new BiomeGenBase[256];
        public int field_76888_d;
        public int field_76889_e;
        public long field_76886_f;
        private static final String __OBFID = "CL_00000163";

        public Block(int p_i1972_2_, int p_i1972_3_)
        {
            this.field_76888_d = p_i1972_2_;
            this.field_76889_e = p_i1972_3_;
            BiomeCache.this.field_76844_a.func_76936_a(this.field_76890_b, p_i1972_2_ << 4, p_i1972_3_ << 4, 16, 16);
            BiomeCache.this.field_76844_a.func_76931_a(this.field_76891_c, p_i1972_2_ << 4, p_i1972_3_ << 4, 16, 16, false);
        }

        public BiomeGenBase func_76885_a(int p_76885_1_, int p_76885_2_)
        {
            return this.field_76891_c[p_76885_1_ & 15 | (p_76885_2_ & 15) << 4];
        }
    }
}