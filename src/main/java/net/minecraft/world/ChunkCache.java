package net.minecraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class ChunkCache implements IBlockAccess
{
    private int field_72818_a;
    private int field_72816_b;
    private Chunk[][] field_72817_c;
    private boolean field_72814_d;
    private World field_72815_e;
    private static final String __OBFID = "CL_00000155";

    public ChunkCache(World p_i1964_1_, int p_i1964_2_, int p_i1964_3_, int p_i1964_4_, int p_i1964_5_, int p_i1964_6_, int p_i1964_7_, int p_i1964_8_)
    {
        this.field_72815_e = p_i1964_1_;
        this.field_72818_a = p_i1964_2_ - p_i1964_8_ >> 4;
        this.field_72816_b = p_i1964_4_ - p_i1964_8_ >> 4;
        int l1 = p_i1964_5_ + p_i1964_8_ >> 4;
        int i2 = p_i1964_7_ + p_i1964_8_ >> 4;
        this.field_72817_c = new Chunk[l1 - this.field_72818_a + 1][i2 - this.field_72816_b + 1];
        this.field_72814_d = true;
        int j2;
        int k2;
        Chunk chunk;

        for (j2 = this.field_72818_a; j2 <= l1; ++j2)
        {
            for (k2 = this.field_72816_b; k2 <= i2; ++k2)
            {
                chunk = p_i1964_1_.func_72964_e(j2, k2);

                if (chunk != null)
                {
                    this.field_72817_c[j2 - this.field_72818_a][k2 - this.field_72816_b] = chunk;
                }
            }
        }

        for (j2 = p_i1964_2_ >> 4; j2 <= p_i1964_5_ >> 4; ++j2)
        {
            for (k2 = p_i1964_4_ >> 4; k2 <= p_i1964_7_ >> 4; ++k2)
            {
                chunk = this.field_72817_c[j2 - this.field_72818_a][k2 - this.field_72816_b];

                if (chunk != null && !chunk.func_76606_c(p_i1964_3_, p_i1964_6_))
                {
                    this.field_72814_d = false;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_72806_N()
    {
        return this.field_72814_d;
    }

    public Block func_147439_a(int p_147439_1_, int p_147439_2_, int p_147439_3_)
    {
        Block block = Blocks.field_150350_a;

        if (p_147439_2_ >= 0 && p_147439_2_ < 256)
        {
            int l = (p_147439_1_ >> 4) - this.field_72818_a;
            int i1 = (p_147439_3_ >> 4) - this.field_72816_b;

            if (l >= 0 && l < this.field_72817_c.length && i1 >= 0 && i1 < this.field_72817_c[l].length)
            {
                Chunk chunk = this.field_72817_c[l][i1];

                if (chunk != null)
                {
                    block = chunk.func_150810_a(p_147439_1_ & 15, p_147439_2_, p_147439_3_ & 15);
                }
            }
        }

        return block;
    }

    public TileEntity func_147438_o(int p_147438_1_, int p_147438_2_, int p_147438_3_)
    {
        int l = (p_147438_1_ >> 4) - this.field_72818_a;
        int i1 = (p_147438_3_ >> 4) - this.field_72816_b;
        return this.field_72817_c[l][i1].func_150806_e(p_147438_1_ & 15, p_147438_2_, p_147438_3_ & 15);
    }

    @SideOnly(Side.CLIENT)
    public int func_72802_i(int p_72802_1_, int p_72802_2_, int p_72802_3_, int p_72802_4_)
    {
        int i1 = this.func_72810_a(EnumSkyBlock.Sky, p_72802_1_, p_72802_2_, p_72802_3_);
        int j1 = this.func_72810_a(EnumSkyBlock.Block, p_72802_1_, p_72802_2_, p_72802_3_);

        if (j1 < p_72802_4_)
        {
            j1 = p_72802_4_;
        }

        return i1 << 20 | j1 << 4;
    }

    public int func_72805_g(int p_72805_1_, int p_72805_2_, int p_72805_3_)
    {
        if (p_72805_2_ < 0)
        {
            return 0;
        }
        else if (p_72805_2_ >= 256)
        {
            return 0;
        }
        else
        {
            int l = (p_72805_1_ >> 4) - this.field_72818_a;
            int i1 = (p_72805_3_ >> 4) - this.field_72816_b;
            return this.field_72817_c[l][i1].func_76628_c(p_72805_1_ & 15, p_72805_2_, p_72805_3_ & 15);
        }
    }

    public int func_72879_k(int p_72879_1_, int p_72879_2_, int p_72879_3_, int p_72879_4_)
    {
        return this.func_147439_a(p_72879_1_, p_72879_2_, p_72879_3_).func_149748_c(this, p_72879_1_, p_72879_2_, p_72879_3_, p_72879_4_);
    }

    @SideOnly(Side.CLIENT)
    public BiomeGenBase func_72807_a(int p_72807_1_, int p_72807_2_)
    {
        return this.field_72815_e.func_72807_a(p_72807_1_, p_72807_2_);
    }

    @SideOnly(Side.CLIENT)
    public boolean func_147437_c(int p_147437_1_, int p_147437_2_, int p_147437_3_)
    {
        return this.func_147439_a(p_147437_1_, p_147437_2_, p_147437_3_).func_149688_o() == Material.field_151579_a;
    }

    @SideOnly(Side.CLIENT)
    public int func_72810_a(EnumSkyBlock p_72810_1_, int p_72810_2_, int p_72810_3_, int p_72810_4_)
    {
        if (p_72810_3_ < 0)
        {
            p_72810_3_ = 0;
        }

        if (p_72810_3_ >= 256)
        {
            p_72810_3_ = 255;
        }

        if (p_72810_3_ >= 0 && p_72810_3_ < 256 && p_72810_2_ >= -30000000 && p_72810_4_ >= -30000000 && p_72810_2_ < 30000000 && p_72810_4_ <= 30000000)
        {
            if (p_72810_1_ == EnumSkyBlock.Sky && this.field_72815_e.field_73011_w.field_76576_e)
            {
                return 0;
            }
            else
            {
                int l;
                int i1;

                if (this.func_147439_a(p_72810_2_, p_72810_3_, p_72810_4_).func_149710_n())
                {
                    l = this.func_72812_b(p_72810_1_, p_72810_2_, p_72810_3_ + 1, p_72810_4_);
                    i1 = this.func_72812_b(p_72810_1_, p_72810_2_ + 1, p_72810_3_, p_72810_4_);
                    int j1 = this.func_72812_b(p_72810_1_, p_72810_2_ - 1, p_72810_3_, p_72810_4_);
                    int k1 = this.func_72812_b(p_72810_1_, p_72810_2_, p_72810_3_, p_72810_4_ + 1);
                    int l1 = this.func_72812_b(p_72810_1_, p_72810_2_, p_72810_3_, p_72810_4_ - 1);

                    if (i1 > l)
                    {
                        l = i1;
                    }

                    if (j1 > l)
                    {
                        l = j1;
                    }

                    if (k1 > l)
                    {
                        l = k1;
                    }

                    if (l1 > l)
                    {
                        l = l1;
                    }

                    return l;
                }
                else
                {
                    l = (p_72810_2_ >> 4) - this.field_72818_a;
                    i1 = (p_72810_4_ >> 4) - this.field_72816_b;
                    return this.field_72817_c[l][i1].func_76614_a(p_72810_1_, p_72810_2_ & 15, p_72810_3_, p_72810_4_ & 15);
                }
            }
        }
        else
        {
            return p_72810_1_.field_77198_c;
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_72812_b(EnumSkyBlock p_72812_1_, int p_72812_2_, int p_72812_3_, int p_72812_4_)
    {
        if (p_72812_3_ < 0)
        {
            p_72812_3_ = 0;
        }

        if (p_72812_3_ >= 256)
        {
            p_72812_3_ = 255;
        }

        if (p_72812_3_ >= 0 && p_72812_3_ < 256 && p_72812_2_ >= -30000000 && p_72812_4_ >= -30000000 && p_72812_2_ < 30000000 && p_72812_4_ <= 30000000)
        {
            int l = (p_72812_2_ >> 4) - this.field_72818_a;
            int i1 = (p_72812_4_ >> 4) - this.field_72816_b;
            return this.field_72817_c[l][i1].func_76614_a(p_72812_1_, p_72812_2_ & 15, p_72812_3_, p_72812_4_ & 15);
        }
        else
        {
            return p_72812_1_.field_77198_c;
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_72800_K()
    {
        return 256;
    }
}