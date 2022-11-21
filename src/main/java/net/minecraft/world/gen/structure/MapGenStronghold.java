package net.minecraft.world.gen.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenStronghold extends MapGenStructure
{
    public final List field_151546_e;
    private boolean field_75056_f;
    private ChunkCoordIntPair[] field_75057_g;
    private double field_82671_h;
    private int field_82672_i;
    private static final String __OBFID = "CL_00000481";

    public MapGenStronghold()
    {
        this.field_75057_g = new ChunkCoordIntPair[3];
        this.field_82671_h = 32.0D;
        this.field_82672_i = 3;
        this.field_151546_e = new ArrayList();
        BiomeGenBase[] abiomegenbase = BiomeGenBase.func_150565_n();
        int i = abiomegenbase.length;

        for (int j = 0; j < i; ++j)
        {
            BiomeGenBase biomegenbase = abiomegenbase[j];

            if (biomegenbase != null && biomegenbase.field_76748_D > 0.0F)
            {
                this.field_151546_e.add(biomegenbase);
            }
        }
    }

    public MapGenStronghold(Map p_i2068_1_)
    {
        this();
        Iterator iterator = p_i2068_1_.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("distance"))
            {
                this.field_82671_h = MathHelper.func_82713_a((String)entry.getValue(), this.field_82671_h, 1.0D);
            }
            else if (((String)entry.getKey()).equals("count"))
            {
                this.field_75057_g = new ChunkCoordIntPair[MathHelper.func_82714_a((String)entry.getValue(), this.field_75057_g.length, 1)];
            }
            else if (((String)entry.getKey()).equals("spread"))
            {
                this.field_82672_i = MathHelper.func_82714_a((String)entry.getValue(), this.field_82672_i, 1);
            }
        }
    }

    public String func_143025_a()
    {
        return "Stronghold";
    }

    protected boolean func_75047_a(int p_75047_1_, int p_75047_2_)
    {
        if (!this.field_75056_f)
        {
            Random random = new Random();
            random.setSeed(this.field_75039_c.func_72905_C());
            double d0 = random.nextDouble() * Math.PI * 2.0D;
            int l = 1;

            for (int i1 = 0; i1 < this.field_75057_g.length; ++i1)
            {
                double d1 = (1.25D * (double)l + random.nextDouble()) * this.field_82671_h * (double)l;
                int j1 = (int)Math.round(Math.cos(d0) * d1);
                int k1 = (int)Math.round(Math.sin(d0) * d1);
                ChunkPosition chunkposition = this.field_75039_c.func_72959_q().func_150795_a((j1 << 4) + 8, (k1 << 4) + 8, 112, this.field_151546_e, random);

                if (chunkposition != null)
                {
                    j1 = chunkposition.field_151329_a >> 4;
                    k1 = chunkposition.field_151328_c >> 4;
                }

                this.field_75057_g[i1] = new ChunkCoordIntPair(j1, k1);
                d0 += (Math.PI * 2D) * (double)l / (double)this.field_82672_i;

                if (i1 == this.field_82672_i)
                {
                    l += 2 + random.nextInt(5);
                    this.field_82672_i += 1 + random.nextInt(2);
                }
            }

            this.field_75056_f = true;
        }

        ChunkCoordIntPair[] achunkcoordintpair = this.field_75057_g;
        int l1 = achunkcoordintpair.length;

        for (int k = 0; k < l1; ++k)
        {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[k];

            if (p_75047_1_ == chunkcoordintpair.field_77276_a && p_75047_2_ == chunkcoordintpair.field_77275_b)
            {
                return true;
            }
        }

        return false;
    }

    protected List func_75052_o_()
    {
        ArrayList arraylist = new ArrayList();
        ChunkCoordIntPair[] achunkcoordintpair = this.field_75057_g;
        int i = achunkcoordintpair.length;

        for (int j = 0; j < i; ++j)
        {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[j];

            if (chunkcoordintpair != null)
            {
                arraylist.add(chunkcoordintpair.func_151349_a(64));
            }
        }

        return arraylist;
    }

    protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_)
    {
        MapGenStronghold.Start start;

        for (start = new MapGenStronghold.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_); start.func_75073_b().isEmpty() || ((StructureStrongholdPieces.Stairs2)start.func_75073_b().get(0)).field_75025_b == null; start = new MapGenStronghold.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_))
        {
            ;
        }

        return start;
    }

    public static class Start extends StructureStart
        {
            private static final String __OBFID = "CL_00000482";

            public Start() {}

            public Start(World p_i2067_1_, Random p_i2067_2_, int p_i2067_3_, int p_i2067_4_)
            {
                super(p_i2067_3_, p_i2067_4_);
                StructureStrongholdPieces.func_75198_a();
                StructureStrongholdPieces.Stairs2 stairs2 = new StructureStrongholdPieces.Stairs2(0, p_i2067_2_, (p_i2067_3_ << 4) + 2, (p_i2067_4_ << 4) + 2);
                this.field_75075_a.add(stairs2);
                stairs2.func_74861_a(stairs2, this.field_75075_a, p_i2067_2_);
                List list = stairs2.field_75026_c;

                while (!list.isEmpty())
                {
                    int k = p_i2067_2_.nextInt(list.size());
                    StructureComponent structurecomponent = (StructureComponent)list.remove(k);
                    structurecomponent.func_74861_a(stairs2, this.field_75075_a, p_i2067_2_);
                }

                this.func_75072_c();
                this.func_75067_a(p_i2067_1_, p_i2067_2_, 10);
            }
        }
}