package net.minecraft.world.gen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;

public class ChunkProviderFlat implements IChunkProvider
{
    private World field_73163_a;
    private Random field_73161_b;
    private final Block[] field_82700_c = new Block[256];
    private final byte[] field_82698_d = new byte[256];
    private final FlatGeneratorInfo field_82699_e;
    private final List field_82696_f = new ArrayList();
    private final boolean field_82697_g;
    private final boolean field_82702_h;
    private WorldGenLakes field_82703_i;
    private WorldGenLakes field_82701_j;
    private static final String __OBFID = "CL_00000391";

    public ChunkProviderFlat(World p_i2004_1_, long p_i2004_2_, boolean p_i2004_4_, String p_i2004_5_)
    {
        this.field_73163_a = p_i2004_1_;
        this.field_73161_b = new Random(p_i2004_2_);
        this.field_82699_e = FlatGeneratorInfo.func_82651_a(p_i2004_5_);

        if (p_i2004_4_)
        {
            Map map = this.field_82699_e.func_82644_b();

            if (map.containsKey("village"))
            {
                Map map1 = (Map)map.get("village");

                if (!map1.containsKey("size"))
                {
                    map1.put("size", "1");
                }

                this.field_82696_f.add(new MapGenVillage(map1));
            }

            if (map.containsKey("biome_1"))
            {
                this.field_82696_f.add(new MapGenScatteredFeature((Map)map.get("biome_1")));
            }

            if (map.containsKey("mineshaft"))
            {
                this.field_82696_f.add(new MapGenMineshaft((Map)map.get("mineshaft")));
            }

            if (map.containsKey("stronghold"))
            {
                this.field_82696_f.add(new MapGenStronghold((Map)map.get("stronghold")));
            }
        }

        this.field_82697_g = this.field_82699_e.func_82644_b().containsKey("decoration");

        if (this.field_82699_e.func_82644_b().containsKey("lake"))
        {
            this.field_82703_i = new WorldGenLakes(Blocks.field_150355_j);
        }

        if (this.field_82699_e.func_82644_b().containsKey("lava_lake"))
        {
            this.field_82701_j = new WorldGenLakes(Blocks.field_150353_l);
        }

        this.field_82702_h = this.field_82699_e.func_82644_b().containsKey("dungeon");
        Iterator iterator = this.field_82699_e.func_82650_c().iterator();

        while (iterator.hasNext())
        {
            FlatLayerInfo flatlayerinfo = (FlatLayerInfo)iterator.next();

            for (int j = flatlayerinfo.func_82656_d(); j < flatlayerinfo.func_82656_d() + flatlayerinfo.func_82657_a(); ++j)
            {
                this.field_82700_c[j] = flatlayerinfo.func_151536_b();
                this.field_82698_d[j] = (byte)flatlayerinfo.func_82658_c();
            }
        }
    }

    public Chunk func_73158_c(int p_73158_1_, int p_73158_2_)
    {
        return this.func_73154_d(p_73158_1_, p_73158_2_);
    }

    public Chunk func_73154_d(int p_73154_1_, int p_73154_2_)
    {
        Chunk chunk = new Chunk(this.field_73163_a, p_73154_1_, p_73154_2_);
        int l;

        for (int k = 0; k < this.field_82700_c.length; ++k)
        {
            Block block = this.field_82700_c[k];

            if (block != null)
            {
                l = k >> 4;
                ExtendedBlockStorage extendedblockstorage = chunk.func_76587_i()[l];

                if (extendedblockstorage == null)
                {
                    extendedblockstorage = new ExtendedBlockStorage(k, !this.field_73163_a.field_73011_w.field_76576_e);
                    chunk.func_76587_i()[l] = extendedblockstorage;
                }

                for (int i1 = 0; i1 < 16; ++i1)
                {
                    for (int j1 = 0; j1 < 16; ++j1)
                    {
                        extendedblockstorage.func_150818_a(i1, k & 15, j1, block);
                        extendedblockstorage.func_76654_b(i1, k & 15, j1, this.field_82698_d[k]);
                    }
                }
            }
        }

        chunk.func_76603_b();
        BiomeGenBase[] abiomegenbase = this.field_73163_a.func_72959_q().func_76933_b((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
        byte[] abyte = chunk.func_76605_m();

        for (l = 0; l < abyte.length; ++l)
        {
            abyte[l] = (byte)abiomegenbase[l].field_76756_M;
        }

        Iterator iterator = this.field_82696_f.iterator();

        while (iterator.hasNext())
        {
            MapGenBase mapgenbase = (MapGenBase)iterator.next();
            mapgenbase.func_151539_a(this, this.field_73163_a, p_73154_1_, p_73154_2_, (Block[])null);
        }

        chunk.func_76603_b();
        return chunk;
    }

    public boolean func_73149_a(int p_73149_1_, int p_73149_2_)
    {
        return true;
    }

    public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
    {
        int k = p_73153_2_ * 16;
        int l = p_73153_3_ * 16;
        BiomeGenBase biomegenbase = this.field_73163_a.func_72807_a(k + 16, l + 16);
        boolean flag = false;
        this.field_73161_b.setSeed(this.field_73163_a.func_72905_C());
        long i1 = this.field_73161_b.nextLong() / 2L * 2L + 1L;
        long j1 = this.field_73161_b.nextLong() / 2L * 2L + 1L;
        this.field_73161_b.setSeed((long)p_73153_2_ * i1 + (long)p_73153_3_ * j1 ^ this.field_73163_a.func_72905_C());
        Iterator iterator = this.field_82696_f.iterator();

        while (iterator.hasNext())
        {
            MapGenStructure mapgenstructure = (MapGenStructure)iterator.next();
            boolean flag1 = mapgenstructure.func_75051_a(this.field_73163_a, this.field_73161_b, p_73153_2_, p_73153_3_);

            if (mapgenstructure instanceof MapGenVillage)
            {
                flag |= flag1;
            }
        }

        int l1;
        int i2;
        int j2;

        if (this.field_82703_i != null && !flag && this.field_73161_b.nextInt(4) == 0)
        {
            l1 = k + this.field_73161_b.nextInt(16) + 8;
            i2 = this.field_73161_b.nextInt(256);
            j2 = l + this.field_73161_b.nextInt(16) + 8;
            this.field_82703_i.func_76484_a(this.field_73163_a, this.field_73161_b, l1, i2, j2);
        }

        if (this.field_82701_j != null && !flag && this.field_73161_b.nextInt(8) == 0)
        {
            l1 = k + this.field_73161_b.nextInt(16) + 8;
            i2 = this.field_73161_b.nextInt(this.field_73161_b.nextInt(248) + 8);
            j2 = l + this.field_73161_b.nextInt(16) + 8;

            if (i2 < 63 || this.field_73161_b.nextInt(10) == 0)
            {
                this.field_82701_j.func_76484_a(this.field_73163_a, this.field_73161_b, l1, i2, j2);
            }
        }

        if (this.field_82702_h)
        {
            for (l1 = 0; l1 < 8; ++l1)
            {
                i2 = k + this.field_73161_b.nextInt(16) + 8;
                j2 = this.field_73161_b.nextInt(256);
                int k1 = l + this.field_73161_b.nextInt(16) + 8;
                (new WorldGenDungeons()).func_76484_a(this.field_73163_a, this.field_73161_b, i2, j2, k1);
            }
        }

        if (this.field_82697_g)
        {
            biomegenbase.func_76728_a(this.field_73163_a, this.field_73161_b, k, l);
        }
    }

    public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        return true;
    }

    public void func_104112_b() {}

    public boolean func_73156_b()
    {
        return false;
    }

    public boolean func_73157_c()
    {
        return true;
    }

    public String func_73148_d()
    {
        return "FlatLevelSource";
    }

    public List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
    {
        BiomeGenBase biomegenbase = this.field_73163_a.func_72807_a(p_73155_2_, p_73155_4_);
        return biomegenbase.func_76747_a(p_73155_1_);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        if ("Stronghold".equals(p_147416_2_))
        {
            Iterator iterator = this.field_82696_f.iterator();

            while (iterator.hasNext())
            {
                MapGenStructure mapgenstructure = (MapGenStructure)iterator.next();

                if (mapgenstructure instanceof MapGenStronghold)
                {
                    return mapgenstructure.func_151545_a(p_147416_1_, p_147416_3_, p_147416_4_, p_147416_5_);
                }
            }
        }

        return null;
    }

    public int func_73152_e()
    {
        return 0;
    }

    public void func_82695_e(int p_82695_1_, int p_82695_2_)
    {
        Iterator iterator = this.field_82696_f.iterator();

        while (iterator.hasNext())
        {
            MapGenStructure mapgenstructure = (MapGenStructure)iterator.next();
            mapgenstructure.func_151539_a(this, this.field_73163_a, p_82695_1_, p_82695_2_, (Block[])null);
        }
    }
}