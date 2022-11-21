package net.minecraft.world.chunk.storage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.NibbleArray;

public class ChunkLoader
{
    private static final String __OBFID = "CL_00000379";

    public static ChunkLoader.AnvilConverterData func_76691_a(NBTTagCompound p_76691_0_)
    {
        int i = p_76691_0_.func_74762_e("xPos");
        int j = p_76691_0_.func_74762_e("zPos");
        ChunkLoader.AnvilConverterData anvilconverterdata = new ChunkLoader.AnvilConverterData(i, j);
        anvilconverterdata.field_76693_g = p_76691_0_.func_74770_j("Blocks");
        anvilconverterdata.field_76692_f = new NibbleArrayReader(p_76691_0_.func_74770_j("Data"), 7);
        anvilconverterdata.field_76695_e = new NibbleArrayReader(p_76691_0_.func_74770_j("SkyLight"), 7);
        anvilconverterdata.field_76694_d = new NibbleArrayReader(p_76691_0_.func_74770_j("BlockLight"), 7);
        anvilconverterdata.field_76697_c = p_76691_0_.func_74770_j("HeightMap");
        anvilconverterdata.field_76696_b = p_76691_0_.func_74767_n("TerrainPopulated");
        anvilconverterdata.field_76702_h = p_76691_0_.func_150295_c("Entities", 10);
        anvilconverterdata.field_151564_i = p_76691_0_.func_150295_c("TileEntities", 10);
        anvilconverterdata.field_151563_j = p_76691_0_.func_150295_c("TileTicks", 10);

        try
        {
            anvilconverterdata.field_76698_a = p_76691_0_.func_74763_f("LastUpdate");
        }
        catch (ClassCastException classcastexception)
        {
            anvilconverterdata.field_76698_a = (long)p_76691_0_.func_74762_e("LastUpdate");
        }

        return anvilconverterdata;
    }

    public static void func_76690_a(ChunkLoader.AnvilConverterData p_76690_0_, NBTTagCompound p_76690_1_, WorldChunkManager p_76690_2_)
    {
        p_76690_1_.func_74768_a("xPos", p_76690_0_.field_76701_k);
        p_76690_1_.func_74768_a("zPos", p_76690_0_.field_76699_l);
        p_76690_1_.func_74772_a("LastUpdate", p_76690_0_.field_76698_a);
        int[] aint = new int[p_76690_0_.field_76697_c.length];

        for (int i = 0; i < p_76690_0_.field_76697_c.length; ++i)
        {
            aint[i] = p_76690_0_.field_76697_c[i];
        }

        p_76690_1_.func_74783_a("HeightMap", aint);
        p_76690_1_.func_74757_a("TerrainPopulated", p_76690_0_.field_76696_b);
        NBTTagList nbttaglist = new NBTTagList();
        int k;

        for (int j = 0; j < 8; ++j)
        {
            boolean flag = true;

            for (k = 0; k < 16 && flag; ++k)
            {
                int l = 0;

                while (l < 16 && flag)
                {
                    int i1 = 0;

                    while (true)
                    {
                        if (i1 < 16)
                        {
                            int j1 = k << 11 | i1 << 7 | l + (j << 4);
                            byte b0 = p_76690_0_.field_76693_g[j1];

                            if (b0 == 0)
                            {
                                ++i1;
                                continue;
                            }

                            flag = false;
                        }

                        ++l;
                        break;
                    }
                }
            }

            if (!flag)
            {
                byte[] abyte1 = new byte[4096];
                NibbleArray nibblearray = new NibbleArray(abyte1.length, 4);
                NibbleArray nibblearray1 = new NibbleArray(abyte1.length, 4);
                NibbleArray nibblearray2 = new NibbleArray(abyte1.length, 4);

                for (int k2 = 0; k2 < 16; ++k2)
                {
                    for (int k1 = 0; k1 < 16; ++k1)
                    {
                        for (int l1 = 0; l1 < 16; ++l1)
                        {
                            int i2 = k2 << 11 | l1 << 7 | k1 + (j << 4);
                            byte b1 = p_76690_0_.field_76693_g[i2];
                            abyte1[k1 << 8 | l1 << 4 | k2] = (byte)(b1 & 255);
                            nibblearray.func_76581_a(k2, k1, l1, p_76690_0_.field_76692_f.func_76686_a(k2, k1 + (j << 4), l1));
                            nibblearray1.func_76581_a(k2, k1, l1, p_76690_0_.field_76695_e.func_76686_a(k2, k1 + (j << 4), l1));
                            nibblearray2.func_76581_a(k2, k1, l1, p_76690_0_.field_76694_d.func_76686_a(k2, k1 + (j << 4), l1));
                        }
                    }
                }

                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.func_74774_a("Y", (byte)(j & 255));
                nbttagcompound1.func_74773_a("Blocks", abyte1);
                nbttagcompound1.func_74773_a("Data", nibblearray.field_76585_a);
                nbttagcompound1.func_74773_a("SkyLight", nibblearray1.field_76585_a);
                nbttagcompound1.func_74773_a("BlockLight", nibblearray2.field_76585_a);
                nbttaglist.func_74742_a(nbttagcompound1);
            }
        }

        p_76690_1_.func_74782_a("Sections", nbttaglist);
        byte[] abyte = new byte[256];

        for (int j2 = 0; j2 < 16; ++j2)
        {
            for (k = 0; k < 16; ++k)
            {
                abyte[k << 4 | j2] = (byte)(p_76690_2_.func_76935_a(p_76690_0_.field_76701_k << 4 | j2, p_76690_0_.field_76699_l << 4 | k).field_76756_M & 255);
            }
        }

        p_76690_1_.func_74773_a("Biomes", abyte);
        p_76690_1_.func_74782_a("Entities", p_76690_0_.field_76702_h);
        p_76690_1_.func_74782_a("TileEntities", p_76690_0_.field_151564_i);

        if (p_76690_0_.field_151563_j != null)
        {
            p_76690_1_.func_74782_a("TileTicks", p_76690_0_.field_151563_j);
        }
    }

    public static class AnvilConverterData
        {
            public long field_76698_a;
            public boolean field_76696_b;
            public byte[] field_76697_c;
            public NibbleArrayReader field_76694_d;
            public NibbleArrayReader field_76695_e;
            public NibbleArrayReader field_76692_f;
            public byte[] field_76693_g;
            public NBTTagList field_76702_h;
            public NBTTagList field_151564_i;
            public NBTTagList field_151563_j;
            public final int field_76701_k;
            public final int field_76699_l;
            private static final String __OBFID = "CL_00000380";

            public AnvilConverterData(int p_i1999_1_, int p_i1999_2_)
            {
                this.field_76701_k = p_i1999_1_;
                this.field_76699_l = p_i1999_2_;
            }
        }
}