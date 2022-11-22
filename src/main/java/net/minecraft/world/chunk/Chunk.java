package net.minecraft.world.chunk;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Chunk
{
    private static final Logger field_150817_t = LogManager.getLogger();
    public static boolean field_76640_a;
    private ExtendedBlockStorage[] field_76652_q;
    private byte[] field_76651_r;
    public int[] field_76638_b;
    public boolean[] field_76639_c;
    public boolean field_76636_d;
    public World field_76637_e;
    public int[] field_76634_f;
    public final int field_76635_g;
    public final int field_76647_h;
    private boolean field_76650_s;
    public Map field_150816_i;
    public List[] field_76645_j;
    public boolean field_76646_k;
    public boolean field_150814_l;
    public boolean field_150815_m;
    public boolean field_76643_l;
    public boolean field_76644_m;
    public long field_76641_n;
    public boolean field_76642_o;
    public int field_82912_p;
    public long field_111204_q;
    private int field_76649_t;
    private static final String __OBFID = "CL_00000373";

    public Chunk(World p_i1995_1_, int p_i1995_2_, int p_i1995_3_)
    {
        this.field_76652_q = new ExtendedBlockStorage[16];
        this.field_76651_r = new byte[256];
        this.field_76638_b = new int[256];
        this.field_76639_c = new boolean[256];
        this.field_150816_i = new HashMap();
        this.field_76649_t = 4096;
        this.field_76645_j = new List[16];
        this.field_76637_e = p_i1995_1_;
        this.field_76635_g = p_i1995_2_;
        this.field_76647_h = p_i1995_3_;
        this.field_76634_f = new int[256];

        for (int k = 0; k < this.field_76645_j.length; ++k)
        {
            this.field_76645_j[k] = new ArrayList();
        }

        Arrays.fill(this.field_76638_b, -999);
        Arrays.fill(this.field_76651_r, (byte) - 1);
    }

    public Chunk(World p_i45446_1_, Block[] p_i45446_2_, int p_i45446_3_, int p_i45446_4_)
    {
        this(p_i45446_1_, p_i45446_3_, p_i45446_4_);
        int k = p_i45446_2_.length / 256;
        boolean flag = !p_i45446_1_.field_73011_w.field_76576_e;

        for (int l = 0; l < 16; ++l)
        {
            for (int i1 = 0; i1 < 16; ++i1)
            {
                for (int j1 = 0; j1 < k; ++j1)
                {
                    Block block = p_i45446_2_[l << 11 | i1 << 7 | j1];

                    if (block != null && block.func_149688_o() != Material.field_151579_a)
                    {
                        int k1 = j1 >> 4;

                        if (this.field_76652_q[k1] == null)
                        {
                            this.field_76652_q[k1] = new ExtendedBlockStorage(k1 << 4, flag);
                        }

                        this.field_76652_q[k1].func_150818_a(l, j1 & 15, i1, block);
                    }
                }
            }
        }
    }

    public Chunk(World p_i45447_1_, Block[] p_i45447_2_, byte[] p_i45447_3_, int p_i45447_4_, int p_i45447_5_)
    {
        this(p_i45447_1_, p_i45447_4_, p_i45447_5_);
        int k = p_i45447_2_.length / 256;
        boolean flag = !p_i45447_1_.field_73011_w.field_76576_e;

        for (int l = 0; l < 16; ++l)
        {
            for (int i1 = 0; i1 < 16; ++i1)
            {
                for (int j1 = 0; j1 < k; ++j1)
                {
                    int k1 = l * k * 16 | i1 * k | j1;
                    Block block = p_i45447_2_[k1];

                    if (block != null && block != Blocks.AIR)
                    {
                        int l1 = j1 >> 4;

                        if (this.field_76652_q[l1] == null)
                        {
                            this.field_76652_q[l1] = new ExtendedBlockStorage(l1 << 4, flag);
                        }

                        this.field_76652_q[l1].func_150818_a(l, j1 & 15, i1, block);
                        this.field_76652_q[l1].func_76654_b(l, j1 & 15, i1, p_i45447_3_[k1]);
                    }
                }
            }
        }
    }

    public boolean func_76600_a(int p_76600_1_, int p_76600_2_)
    {
        return p_76600_1_ == this.field_76635_g && p_76600_2_ == this.field_76647_h;
    }

    public int func_76611_b(int p_76611_1_, int p_76611_2_)
    {
        return this.field_76634_f[p_76611_2_ << 4 | p_76611_1_];
    }

    public int func_76625_h()
    {
        for (int i = this.field_76652_q.length - 1; i >= 0; --i)
        {
            if (this.field_76652_q[i] != null)
            {
                return this.field_76652_q[i].func_76662_d();
            }
        }

        return 0;
    }

    public ExtendedBlockStorage[] func_76587_i()
    {
        return this.field_76652_q;
    }

    @SideOnly(Side.CLIENT)
    public void func_76590_a()
    {
        int i = this.func_76625_h();
        this.field_82912_p = Integer.MAX_VALUE;

        for (int j = 0; j < 16; ++j)
        {
            int k = 0;

            while (k < 16)
            {
                this.field_76638_b[j + (k << 4)] = -999;
                int l = i + 16 - 1;

                while (true)
                {
                    if (l > 0)
                    {
                        Block block = this.func_150810_a(j, l - 1, k);

                        if (block.func_149717_k() == 0)
                        {
                            --l;
                            continue;
                        }

                        this.field_76634_f[k << 4 | j] = l;

                        if (l < this.field_82912_p)
                        {
                            this.field_82912_p = l;
                        }
                    }

                    ++k;
                    break;
                }
            }
        }

        this.field_76643_l = true;
    }

    public void func_76603_b()
    {
        int i = this.func_76625_h();
        this.field_82912_p = Integer.MAX_VALUE;

        for (int j = 0; j < 16; ++j)
        {
            int k = 0;

            while (k < 16)
            {
                this.field_76638_b[j + (k << 4)] = -999;
                int l = i + 16 - 1;

                while (true)
                {
                    if (l > 0)
                    {
                        if (this.func_150808_b(j, l - 1, k) == 0)
                        {
                            --l;
                            continue;
                        }

                        this.field_76634_f[k << 4 | j] = l;

                        if (l < this.field_82912_p)
                        {
                            this.field_82912_p = l;
                        }
                    }

                    if (!this.field_76637_e.field_73011_w.field_76576_e)
                    {
                        l = 15;
                        int i1 = i + 16 - 1;

                        do
                        {
                            int j1 = this.func_150808_b(j, i1, k);

                            if (j1 == 0 && l != 15)
                            {
                                j1 = 1;
                            }

                            l -= j1;

                            if (l > 0)
                            {
                                ExtendedBlockStorage extendedblockstorage = this.field_76652_q[i1 >> 4];

                                if (extendedblockstorage != null)
                                {
                                    extendedblockstorage.func_76657_c(j, i1 & 15, k, l);
                                    this.field_76637_e.func_147479_m((this.field_76635_g << 4) + j, i1, (this.field_76647_h << 4) + k);
                                }
                            }

                            --i1;
                        }
                        while (i1 > 0 && l > 0);
                    }

                    ++k;
                    break;
                }
            }
        }

        this.field_76643_l = true;
    }

    private void func_76595_e(int p_76595_1_, int p_76595_2_)
    {
        this.field_76639_c[p_76595_1_ + p_76595_2_ * 16] = true;
        this.field_76650_s = true;
    }

    private void func_150803_c(boolean p_150803_1_)
    {
        this.field_76637_e.profiler.startMeasure("recheckGaps");

        if (this.field_76637_e.func_72873_a(this.field_76635_g * 16 + 8, 0, this.field_76647_h * 16 + 8, 16))
        {
            for (int i = 0; i < 16; ++i)
            {
                for (int j = 0; j < 16; ++j)
                {
                    if (this.field_76639_c[i + j * 16])
                    {
                        this.field_76639_c[i + j * 16] = false;
                        int k = this.func_76611_b(i, j);
                        int l = this.field_76635_g * 16 + i;
                        int i1 = this.field_76647_h * 16 + j;
                        int j1 = this.field_76637_e.func_82734_g(l - 1, i1);
                        int k1 = this.field_76637_e.func_82734_g(l + 1, i1);
                        int l1 = this.field_76637_e.func_82734_g(l, i1 - 1);
                        int i2 = this.field_76637_e.func_82734_g(l, i1 + 1);

                        if (k1 < j1)
                        {
                            j1 = k1;
                        }

                        if (l1 < j1)
                        {
                            j1 = l1;
                        }

                        if (i2 < j1)
                        {
                            j1 = i2;
                        }

                        this.func_76599_g(l, i1, j1);
                        this.func_76599_g(l - 1, i1, k);
                        this.func_76599_g(l + 1, i1, k);
                        this.func_76599_g(l, i1 - 1, k);
                        this.func_76599_g(l, i1 + 1, k);

                        if (p_150803_1_)
                        {
                            this.field_76637_e.profiler.endMeasure();
                            return;
                        }
                    }
                }
            }

            this.field_76650_s = false;
        }

        this.field_76637_e.profiler.endMeasure();
    }

    private void func_76599_g(int p_76599_1_, int p_76599_2_, int p_76599_3_)
    {
        int l = this.field_76637_e.func_72976_f(p_76599_1_, p_76599_2_);

        if (l > p_76599_3_)
        {
            this.func_76609_d(p_76599_1_, p_76599_2_, p_76599_3_, l + 1);
        }
        else if (l < p_76599_3_)
        {
            this.func_76609_d(p_76599_1_, p_76599_2_, l, p_76599_3_ + 1);
        }
    }

    private void func_76609_d(int p_76609_1_, int p_76609_2_, int p_76609_3_, int p_76609_4_)
    {
        if (p_76609_4_ > p_76609_3_ && this.field_76637_e.func_72873_a(p_76609_1_, 0, p_76609_2_, 16))
        {
            for (int i1 = p_76609_3_; i1 < p_76609_4_; ++i1)
            {
                this.field_76637_e.func_147463_c(EnumSkyBlock.Sky, p_76609_1_, i1, p_76609_2_);
            }

            this.field_76643_l = true;
        }
    }

    private void func_76615_h(int p_76615_1_, int p_76615_2_, int p_76615_3_)
    {
        int l = this.field_76634_f[p_76615_3_ << 4 | p_76615_1_] & 255;
        int i1 = l;

        if (p_76615_2_ > l)
        {
            i1 = p_76615_2_;
        }

        while (i1 > 0 && this.func_150808_b(p_76615_1_, i1 - 1, p_76615_3_) == 0)
        {
            --i1;
        }

        if (i1 != l)
        {
            this.field_76637_e.func_72975_g(p_76615_1_ + this.field_76635_g * 16, p_76615_3_ + this.field_76647_h * 16, i1, l);
            this.field_76634_f[p_76615_3_ << 4 | p_76615_1_] = i1;
            int j1 = this.field_76635_g * 16 + p_76615_1_;
            int k1 = this.field_76647_h * 16 + p_76615_3_;
            int l1;
            int i2;

            if (!this.field_76637_e.field_73011_w.field_76576_e)
            {
                ExtendedBlockStorage extendedblockstorage;

                if (i1 < l)
                {
                    for (l1 = i1; l1 < l; ++l1)
                    {
                        extendedblockstorage = this.field_76652_q[l1 >> 4];

                        if (extendedblockstorage != null)
                        {
                            extendedblockstorage.func_76657_c(p_76615_1_, l1 & 15, p_76615_3_, 15);
                            this.field_76637_e.func_147479_m((this.field_76635_g << 4) + p_76615_1_, l1, (this.field_76647_h << 4) + p_76615_3_);
                        }
                    }
                }
                else
                {
                    for (l1 = l; l1 < i1; ++l1)
                    {
                        extendedblockstorage = this.field_76652_q[l1 >> 4];

                        if (extendedblockstorage != null)
                        {
                            extendedblockstorage.func_76657_c(p_76615_1_, l1 & 15, p_76615_3_, 0);
                            this.field_76637_e.func_147479_m((this.field_76635_g << 4) + p_76615_1_, l1, (this.field_76647_h << 4) + p_76615_3_);
                        }
                    }
                }

                l1 = 15;

                while (i1 > 0 && l1 > 0)
                {
                    --i1;
                    i2 = this.func_150808_b(p_76615_1_, i1, p_76615_3_);

                    if (i2 == 0)
                    {
                        i2 = 1;
                    }

                    l1 -= i2;

                    if (l1 < 0)
                    {
                        l1 = 0;
                    }

                    ExtendedBlockStorage extendedblockstorage1 = this.field_76652_q[i1 >> 4];

                    if (extendedblockstorage1 != null)
                    {
                        extendedblockstorage1.func_76657_c(p_76615_1_, i1 & 15, p_76615_3_, l1);
                    }
                }
            }

            l1 = this.field_76634_f[p_76615_3_ << 4 | p_76615_1_];
            i2 = l;
            int j2 = l1;

            if (l1 < l)
            {
                i2 = l1;
                j2 = l;
            }

            if (l1 < this.field_82912_p)
            {
                this.field_82912_p = l1;
            }

            if (!this.field_76637_e.field_73011_w.field_76576_e)
            {
                this.func_76609_d(j1 - 1, k1, i2, j2);
                this.func_76609_d(j1 + 1, k1, i2, j2);
                this.func_76609_d(j1, k1 - 1, i2, j2);
                this.func_76609_d(j1, k1 + 1, i2, j2);
                this.func_76609_d(j1, k1, i2, j2);
            }

            this.field_76643_l = true;
        }
    }

    public int func_150808_b(int p_150808_1_, int p_150808_2_, int p_150808_3_)
    {
        return this.func_150810_a(p_150808_1_, p_150808_2_, p_150808_3_).func_149717_k();
    }

    public Block func_150810_a(final int p_150810_1_, final int p_150810_2_, final int p_150810_3_)
    {
        Block block = Blocks.AIR;

        if (p_150810_2_ >> 4 < this.field_76652_q.length)
        {
            ExtendedBlockStorage extendedblockstorage = this.field_76652_q[p_150810_2_ >> 4];

            if (extendedblockstorage != null)
            {
                try
                {
                    block = extendedblockstorage.func_150819_a(p_150810_1_, p_150810_2_ & 15, p_150810_3_);
                }
                catch (Throwable throwable)
                {
                    CrashReport crashreport = CrashReport.func_85055_a(throwable, "Getting block");
                    CrashReportCategory crashreportcategory = crashreport.func_85058_a("Block being got");
                    crashreportcategory.func_71500_a("Location", new Callable()
                    {
                        private static final String __OBFID = "CL_00000374";
                        public String call()
                        {
                            return CrashReportCategory.func_85071_a(p_150810_1_, p_150810_2_, p_150810_3_);
                        }
                    });
                    throw new ReportedException(crashreport);
                }
            }
        }

        return block;
    }

    public int func_76628_c(int p_76628_1_, int p_76628_2_, int p_76628_3_)
    {
        if (p_76628_2_ >> 4 >= this.field_76652_q.length)
        {
            return 0;
        }
        else
        {
            ExtendedBlockStorage extendedblockstorage = this.field_76652_q[p_76628_2_ >> 4];
            return extendedblockstorage != null ? extendedblockstorage.func_76665_b(p_76628_1_, p_76628_2_ & 15, p_76628_3_) : 0;
        }
    }

    public boolean func_150807_a(int p_150807_1_, int p_150807_2_, int p_150807_3_, Block p_150807_4_, int p_150807_5_)
    {
        int i1 = p_150807_3_ << 4 | p_150807_1_;

        if (p_150807_2_ >= this.field_76638_b[i1] - 1)
        {
            this.field_76638_b[i1] = -999;
        }

        int j1 = this.field_76634_f[i1];
        Block block1 = this.func_150810_a(p_150807_1_, p_150807_2_, p_150807_3_);
        int k1 = this.func_76628_c(p_150807_1_, p_150807_2_, p_150807_3_);

        if (block1 == p_150807_4_ && k1 == p_150807_5_)
        {
            return false;
        }
        else
        {
            ExtendedBlockStorage extendedblockstorage = this.field_76652_q[p_150807_2_ >> 4];
            boolean flag = false;

            if (extendedblockstorage == null)
            {
                if (p_150807_4_ == Blocks.AIR)
                {
                    return false;
                }

                extendedblockstorage = this.field_76652_q[p_150807_2_ >> 4] = new ExtendedBlockStorage(p_150807_2_ >> 4 << 4, !this.field_76637_e.field_73011_w.field_76576_e);
                flag = p_150807_2_ >= j1;
            }

            int l1 = this.field_76635_g * 16 + p_150807_1_;
            int i2 = this.field_76647_h * 16 + p_150807_3_;

            if (!this.field_76637_e.field_72995_K)
            {
                block1.func_149725_f(this.field_76637_e, l1, p_150807_2_, i2, k1);
            }

            extendedblockstorage.func_150818_a(p_150807_1_, p_150807_2_ & 15, p_150807_3_, p_150807_4_);

            if (!this.field_76637_e.field_72995_K)
            {
                block1.func_149749_a(this.field_76637_e, l1, p_150807_2_, i2, block1, k1);
            }
            else if (block1 instanceof ITileEntityProvider && block1 != p_150807_4_)
            {
                this.field_76637_e.func_147475_p(l1, p_150807_2_, i2);
            }

            if (extendedblockstorage.func_150819_a(p_150807_1_, p_150807_2_ & 15, p_150807_3_) != p_150807_4_)
            {
                return false;
            }
            else
            {
                extendedblockstorage.func_76654_b(p_150807_1_, p_150807_2_ & 15, p_150807_3_, p_150807_5_);

                if (flag)
                {
                    this.func_76603_b();
                }
                else
                {
                    int j2 = p_150807_4_.func_149717_k();
                    int k2 = block1.func_149717_k();

                    if (j2 > 0)
                    {
                        if (p_150807_2_ >= j1)
                        {
                            this.func_76615_h(p_150807_1_, p_150807_2_ + 1, p_150807_3_);
                        }
                    }
                    else if (p_150807_2_ == j1 - 1)
                    {
                        this.func_76615_h(p_150807_1_, p_150807_2_, p_150807_3_);
                    }

                    if (j2 != k2 && (j2 < k2 || this.func_76614_a(EnumSkyBlock.Sky, p_150807_1_, p_150807_2_, p_150807_3_) > 0 || this.func_76614_a(EnumSkyBlock.Block, p_150807_1_, p_150807_2_, p_150807_3_) > 0))
                    {
                        this.func_76595_e(p_150807_1_, p_150807_3_);
                    }
                }

                TileEntity tileentity;

                if (block1 instanceof ITileEntityProvider)
                {
                    tileentity = this.func_150806_e(p_150807_1_, p_150807_2_, p_150807_3_);

                    if (tileentity != null)
                    {
                        tileentity.func_145836_u();
                    }
                }

                if (!this.field_76637_e.field_72995_K)
                {
                    p_150807_4_.func_149726_b(this.field_76637_e, l1, p_150807_2_, i2);
                }

                if (p_150807_4_ instanceof ITileEntityProvider)
                {
                    tileentity = this.func_150806_e(p_150807_1_, p_150807_2_, p_150807_3_);

                    if (tileentity == null)
                    {
                        tileentity = ((ITileEntityProvider)p_150807_4_).func_149915_a(this.field_76637_e, p_150807_5_);
                        this.field_76637_e.func_147455_a(l1, p_150807_2_, i2, tileentity);
                    }

                    if (tileentity != null)
                    {
                        tileentity.func_145836_u();
                    }
                }

                this.field_76643_l = true;
                return true;
            }
        }
    }

    public boolean func_76589_b(int p_76589_1_, int p_76589_2_, int p_76589_3_, int p_76589_4_)
    {
        ExtendedBlockStorage extendedblockstorage = this.field_76652_q[p_76589_2_ >> 4];

        if (extendedblockstorage == null)
        {
            return false;
        }
        else
        {
            int i1 = extendedblockstorage.func_76665_b(p_76589_1_, p_76589_2_ & 15, p_76589_3_);

            if (i1 == p_76589_4_)
            {
                return false;
            }
            else
            {
                this.field_76643_l = true;
                extendedblockstorage.func_76654_b(p_76589_1_, p_76589_2_ & 15, p_76589_3_, p_76589_4_);

                if (extendedblockstorage.func_150819_a(p_76589_1_, p_76589_2_ & 15, p_76589_3_) instanceof ITileEntityProvider)
                {
                    TileEntity tileentity = this.func_150806_e(p_76589_1_, p_76589_2_, p_76589_3_);

                    if (tileentity != null)
                    {
                        tileentity.func_145836_u();
                        tileentity.field_145847_g = p_76589_4_;
                    }
                }

                return true;
            }
        }
    }

    public int func_76614_a(EnumSkyBlock p_76614_1_, int p_76614_2_, int p_76614_3_, int p_76614_4_)
    {
        ExtendedBlockStorage extendedblockstorage = this.field_76652_q[p_76614_3_ >> 4];
        return extendedblockstorage == null ? (this.func_76619_d(p_76614_2_, p_76614_3_, p_76614_4_) ? p_76614_1_.field_77198_c : 0) : (p_76614_1_ == EnumSkyBlock.Sky ? (this.field_76637_e.field_73011_w.field_76576_e ? 0 : extendedblockstorage.func_76670_c(p_76614_2_, p_76614_3_ & 15, p_76614_4_)) : (p_76614_1_ == EnumSkyBlock.Block ? extendedblockstorage.func_76674_d(p_76614_2_, p_76614_3_ & 15, p_76614_4_) : p_76614_1_.field_77198_c));
    }

    public void func_76633_a(EnumSkyBlock p_76633_1_, int p_76633_2_, int p_76633_3_, int p_76633_4_, int p_76633_5_)
    {
        ExtendedBlockStorage extendedblockstorage = this.field_76652_q[p_76633_3_ >> 4];

        if (extendedblockstorage == null)
        {
            extendedblockstorage = this.field_76652_q[p_76633_3_ >> 4] = new ExtendedBlockStorage(p_76633_3_ >> 4 << 4, !this.field_76637_e.field_73011_w.field_76576_e);
            this.func_76603_b();
        }

        this.field_76643_l = true;

        if (p_76633_1_ == EnumSkyBlock.Sky)
        {
            if (!this.field_76637_e.field_73011_w.field_76576_e)
            {
                extendedblockstorage.func_76657_c(p_76633_2_, p_76633_3_ & 15, p_76633_4_, p_76633_5_);
            }
        }
        else if (p_76633_1_ == EnumSkyBlock.Block)
        {
            extendedblockstorage.func_76677_d(p_76633_2_, p_76633_3_ & 15, p_76633_4_, p_76633_5_);
        }
    }

    public int func_76629_c(int p_76629_1_, int p_76629_2_, int p_76629_3_, int p_76629_4_)
    {
        ExtendedBlockStorage extendedblockstorage = this.field_76652_q[p_76629_2_ >> 4];

        if (extendedblockstorage == null)
        {
            return !this.field_76637_e.field_73011_w.field_76576_e && p_76629_4_ < EnumSkyBlock.Sky.field_77198_c ? EnumSkyBlock.Sky.field_77198_c - p_76629_4_ : 0;
        }
        else
        {
            int i1 = this.field_76637_e.field_73011_w.field_76576_e ? 0 : extendedblockstorage.func_76670_c(p_76629_1_, p_76629_2_ & 15, p_76629_3_);

            if (i1 > 0)
            {
                field_76640_a = true;
            }

            i1 -= p_76629_4_;
            int j1 = extendedblockstorage.func_76674_d(p_76629_1_, p_76629_2_ & 15, p_76629_3_);

            if (j1 > i1)
            {
                i1 = j1;
            }

            return i1;
        }
    }

    public void func_76612_a(Entity p_76612_1_)
    {
        this.field_76644_m = true;
        int i = MathHelper.func_76128_c(p_76612_1_.field_70165_t / 16.0D);
        int j = MathHelper.func_76128_c(p_76612_1_.field_70161_v / 16.0D);

        if (i != this.field_76635_g || j != this.field_76647_h)
        {
            field_150817_t.warn("Wrong location! " + p_76612_1_ + " (at " + i + ", " + j + " instead of " + this.field_76635_g + ", " + this.field_76647_h + ")");
            Thread.dumpStack();
        }

        int k = MathHelper.func_76128_c(p_76612_1_.field_70163_u / 16.0D);

        if (k < 0)
        {
            k = 0;
        }

        if (k >= this.field_76645_j.length)
        {
            k = this.field_76645_j.length - 1;
        }

        p_76612_1_.field_70175_ag = true;
        p_76612_1_.field_70176_ah = this.field_76635_g;
        p_76612_1_.field_70162_ai = k;
        p_76612_1_.field_70164_aj = this.field_76647_h;
        this.field_76645_j[k].add(p_76612_1_);
    }

    public void func_76622_b(Entity p_76622_1_)
    {
        this.func_76608_a(p_76622_1_, p_76622_1_.field_70162_ai);
    }

    public void func_76608_a(Entity p_76608_1_, int p_76608_2_)
    {
        if (p_76608_2_ < 0)
        {
            p_76608_2_ = 0;
        }

        if (p_76608_2_ >= this.field_76645_j.length)
        {
            p_76608_2_ = this.field_76645_j.length - 1;
        }

        this.field_76645_j[p_76608_2_].remove(p_76608_1_);
    }

    public boolean func_76619_d(int p_76619_1_, int p_76619_2_, int p_76619_3_)
    {
        return p_76619_2_ >= this.field_76634_f[p_76619_3_ << 4 | p_76619_1_];
    }

    public TileEntity func_150806_e(int p_150806_1_, int p_150806_2_, int p_150806_3_)
    {
        ChunkPosition chunkposition = new ChunkPosition(p_150806_1_, p_150806_2_, p_150806_3_);
        TileEntity tileentity = (TileEntity)this.field_150816_i.get(chunkposition);

        if (tileentity == null)
        {
            Block block = this.func_150810_a(p_150806_1_, p_150806_2_, p_150806_3_);

            if (!block.func_149716_u())
            {
                return null;
            }

            tileentity = ((ITileEntityProvider)block).func_149915_a(this.field_76637_e, this.func_76628_c(p_150806_1_, p_150806_2_, p_150806_3_));
            this.field_76637_e.func_147455_a(this.field_76635_g * 16 + p_150806_1_, p_150806_2_, this.field_76647_h * 16 + p_150806_3_, tileentity);
        }

        if (tileentity != null && tileentity.func_145837_r())
        {
            this.field_150816_i.remove(chunkposition);
            return null;
        }
        else
        {
            return tileentity;
        }
    }

    public void func_150813_a(TileEntity p_150813_1_)
    {
        int i = p_150813_1_.field_145851_c - this.field_76635_g * 16;
        int j = p_150813_1_.field_145848_d;
        int k = p_150813_1_.field_145849_e - this.field_76647_h * 16;
        this.func_150812_a(i, j, k, p_150813_1_);

        if (this.field_76636_d)
        {
            this.field_76637_e.field_147482_g.add(p_150813_1_);
        }
    }

    public void func_150812_a(int p_150812_1_, int p_150812_2_, int p_150812_3_, TileEntity p_150812_4_)
    {
        ChunkPosition chunkposition = new ChunkPosition(p_150812_1_, p_150812_2_, p_150812_3_);
        p_150812_4_.func_145834_a(this.field_76637_e);
        p_150812_4_.field_145851_c = this.field_76635_g * 16 + p_150812_1_;
        p_150812_4_.field_145848_d = p_150812_2_;
        p_150812_4_.field_145849_e = this.field_76647_h * 16 + p_150812_3_;

        if (this.func_150810_a(p_150812_1_, p_150812_2_, p_150812_3_) instanceof ITileEntityProvider)
        {
            if (this.field_150816_i.containsKey(chunkposition))
            {
                ((TileEntity)this.field_150816_i.get(chunkposition)).func_145843_s();
            }

            p_150812_4_.func_145829_t();
            this.field_150816_i.put(chunkposition, p_150812_4_);
        }
    }

    public void func_150805_f(int p_150805_1_, int p_150805_2_, int p_150805_3_)
    {
        ChunkPosition chunkposition = new ChunkPosition(p_150805_1_, p_150805_2_, p_150805_3_);

        if (this.field_76636_d)
        {
            TileEntity tileentity = (TileEntity)this.field_150816_i.remove(chunkposition);

            if (tileentity != null)
            {
                tileentity.func_145843_s();
            }
        }
    }

    public void func_76631_c()
    {
        this.field_76636_d = true;
        this.field_76637_e.func_147448_a(this.field_150816_i.values());

        for (int i = 0; i < this.field_76645_j.length; ++i)
        {
            Iterator iterator = this.field_76645_j[i].iterator();

            while (iterator.hasNext())
            {
                Entity entity = (Entity)iterator.next();
                entity.func_110123_P();
            }

            this.field_76637_e.func_72868_a(this.field_76645_j[i]);
        }
    }

    public void func_76623_d()
    {
        this.field_76636_d = false;
        Iterator iterator = this.field_150816_i.values().iterator();

        while (iterator.hasNext())
        {
            TileEntity tileentity = (TileEntity)iterator.next();
            this.field_76637_e.func_147457_a(tileentity);
        }

        for (int i = 0; i < this.field_76645_j.length; ++i)
        {
            this.field_76637_e.func_72828_b(this.field_76645_j[i]);
        }
    }

    public void func_76630_e()
    {
        this.field_76643_l = true;
    }

    public void func_76588_a(Entity p_76588_1_, AxisAlignedBB p_76588_2_, List p_76588_3_, IEntitySelector p_76588_4_)
    {
        int i = MathHelper.func_76128_c((p_76588_2_.field_72338_b - 2.0D) / 16.0D);
        int j = MathHelper.func_76128_c((p_76588_2_.field_72337_e + 2.0D) / 16.0D);
        i = MathHelper.func_76125_a(i, 0, this.field_76645_j.length - 1);
        j = MathHelper.func_76125_a(j, 0, this.field_76645_j.length - 1);

        for (int k = i; k <= j; ++k)
        {
            List list1 = this.field_76645_j[k];

            for (int l = 0; l < list1.size(); ++l)
            {
                Entity entity1 = (Entity)list1.get(l);

                if (entity1 != p_76588_1_ && entity1.field_70121_D.func_72326_a(p_76588_2_) && (p_76588_4_ == null || p_76588_4_.func_82704_a(entity1)))
                {
                    p_76588_3_.add(entity1);
                    Entity[] aentity = entity1.func_70021_al();

                    if (aentity != null)
                    {
                        for (int i1 = 0; i1 < aentity.length; ++i1)
                        {
                            entity1 = aentity[i1];

                            if (entity1 != p_76588_1_ && entity1.field_70121_D.func_72326_a(p_76588_2_) && (p_76588_4_ == null || p_76588_4_.func_82704_a(entity1)))
                            {
                                p_76588_3_.add(entity1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void func_76618_a(Class p_76618_1_, AxisAlignedBB p_76618_2_, List p_76618_3_, IEntitySelector p_76618_4_)
    {
        int i = MathHelper.func_76128_c((p_76618_2_.field_72338_b - 2.0D) / 16.0D);
        int j = MathHelper.func_76128_c((p_76618_2_.field_72337_e + 2.0D) / 16.0D);
        i = MathHelper.func_76125_a(i, 0, this.field_76645_j.length - 1);
        j = MathHelper.func_76125_a(j, 0, this.field_76645_j.length - 1);

        for (int k = i; k <= j; ++k)
        {
            List list1 = this.field_76645_j[k];

            for (int l = 0; l < list1.size(); ++l)
            {
                Entity entity = (Entity)list1.get(l);

                if (p_76618_1_.isAssignableFrom(entity.getClass()) && entity.field_70121_D.func_72326_a(p_76618_2_) && (p_76618_4_ == null || p_76618_4_.func_82704_a(entity)))
                {
                    p_76618_3_.add(entity);
                }
            }
        }
    }

    public boolean func_76601_a(boolean p_76601_1_)
    {
        if (p_76601_1_)
        {
            if (this.field_76644_m && this.field_76637_e.func_82737_E() != this.field_76641_n || this.field_76643_l)
            {
                return true;
            }
        }
        else if (this.field_76644_m && this.field_76637_e.func_82737_E() >= this.field_76641_n + 600L)
        {
            return true;
        }

        return this.field_76643_l;
    }

    public Random func_76617_a(long p_76617_1_)
    {
        return new Random(this.field_76637_e.func_72905_C() + (long)(this.field_76635_g * this.field_76635_g * 4987142) + (long)(this.field_76635_g * 5947611) + (long)(this.field_76647_h * this.field_76647_h) * 4392871L + (long)(this.field_76647_h * 389711) ^ p_76617_1_);
    }

    public boolean func_76621_g()
    {
        return false;
    }

    public void func_76624_a(IChunkProvider p_76624_1_, IChunkProvider p_76624_2_, int p_76624_3_, int p_76624_4_)
    {
        if (!this.field_76646_k && p_76624_1_.func_73149_a(p_76624_3_ + 1, p_76624_4_ + 1) && p_76624_1_.func_73149_a(p_76624_3_, p_76624_4_ + 1) && p_76624_1_.func_73149_a(p_76624_3_ + 1, p_76624_4_))
        {
            p_76624_1_.func_73153_a(p_76624_2_, p_76624_3_, p_76624_4_);
        }

        if (p_76624_1_.func_73149_a(p_76624_3_ - 1, p_76624_4_) && !p_76624_1_.func_73154_d(p_76624_3_ - 1, p_76624_4_).field_76646_k && p_76624_1_.func_73149_a(p_76624_3_ - 1, p_76624_4_ + 1) && p_76624_1_.func_73149_a(p_76624_3_, p_76624_4_ + 1) && p_76624_1_.func_73149_a(p_76624_3_ - 1, p_76624_4_ + 1))
        {
            p_76624_1_.func_73153_a(p_76624_2_, p_76624_3_ - 1, p_76624_4_);
        }

        if (p_76624_1_.func_73149_a(p_76624_3_, p_76624_4_ - 1) && !p_76624_1_.func_73154_d(p_76624_3_, p_76624_4_ - 1).field_76646_k && p_76624_1_.func_73149_a(p_76624_3_ + 1, p_76624_4_ - 1) && p_76624_1_.func_73149_a(p_76624_3_ + 1, p_76624_4_ - 1) && p_76624_1_.func_73149_a(p_76624_3_ + 1, p_76624_4_))
        {
            p_76624_1_.func_73153_a(p_76624_2_, p_76624_3_, p_76624_4_ - 1);
        }

        if (p_76624_1_.func_73149_a(p_76624_3_ - 1, p_76624_4_ - 1) && !p_76624_1_.func_73154_d(p_76624_3_ - 1, p_76624_4_ - 1).field_76646_k && p_76624_1_.func_73149_a(p_76624_3_, p_76624_4_ - 1) && p_76624_1_.func_73149_a(p_76624_3_ - 1, p_76624_4_))
        {
            p_76624_1_.func_73153_a(p_76624_2_, p_76624_3_ - 1, p_76624_4_ - 1);
        }
    }

    public int func_76626_d(int p_76626_1_, int p_76626_2_)
    {
        int k = p_76626_1_ | p_76626_2_ << 4;
        int l = this.field_76638_b[k];

        if (l == -999)
        {
            int i1 = this.func_76625_h() + 15;
            l = -1;

            while (i1 > 0 && l == -1)
            {
                Block block = this.func_150810_a(p_76626_1_, i1, p_76626_2_);
                Material material = block.func_149688_o();

                if (!material.func_76230_c() && !material.func_76224_d())
                {
                    --i1;
                }
                else
                {
                    l = i1 + 1;
                }
            }

            this.field_76638_b[k] = l;
        }

        return l;
    }

    public void func_150804_b(boolean p_150804_1_)
    {
        if (this.field_76650_s && !this.field_76637_e.field_73011_w.field_76576_e && !p_150804_1_)
        {
            this.func_150803_c(this.field_76637_e.field_72995_K);
        }

        this.field_150815_m = true;

        if (!this.field_150814_l && this.field_76646_k)
        {
            this.func_150809_p();
        }
    }

    public boolean func_150802_k()
    {
        return this.field_150815_m && this.field_76646_k && this.field_150814_l;
    }

    public ChunkCoordIntPair func_76632_l()
    {
        return new ChunkCoordIntPair(this.field_76635_g, this.field_76647_h);
    }

    public boolean func_76606_c(int p_76606_1_, int p_76606_2_)
    {
        if (p_76606_1_ < 0)
        {
            p_76606_1_ = 0;
        }

        if (p_76606_2_ >= 256)
        {
            p_76606_2_ = 255;
        }

        for (int k = p_76606_1_; k <= p_76606_2_; k += 16)
        {
            ExtendedBlockStorage extendedblockstorage = this.field_76652_q[k >> 4];

            if (extendedblockstorage != null && !extendedblockstorage.func_76663_a())
            {
                return false;
            }
        }

        return true;
    }

    public void func_76602_a(ExtendedBlockStorage[] p_76602_1_)
    {
        this.field_76652_q = p_76602_1_;
    }

    @SideOnly(Side.CLIENT)
    public void func_76607_a(byte[] p_76607_1_, int p_76607_2_, int p_76607_3_, boolean p_76607_4_)
    {
        int k = 0;
        boolean flag1 = !this.field_76637_e.field_73011_w.field_76576_e;
        int l;

        for (l = 0; l < this.field_76652_q.length; ++l)
        {
            if ((p_76607_2_ & 1 << l) != 0)
            {
                if (this.field_76652_q[l] == null)
                {
                    this.field_76652_q[l] = new ExtendedBlockStorage(l << 4, flag1);
                }

                byte[] abyte1 = this.field_76652_q[l].func_76658_g();
                System.arraycopy(p_76607_1_, k, abyte1, 0, abyte1.length);
                k += abyte1.length;
            }
            else if (p_76607_4_ && this.field_76652_q[l] != null)
            {
                this.field_76652_q[l] = null;
            }
        }

        NibbleArray nibblearray;

        for (l = 0; l < this.field_76652_q.length; ++l)
        {
            if ((p_76607_2_ & 1 << l) != 0 && this.field_76652_q[l] != null)
            {
                nibblearray = this.field_76652_q[l].func_76669_j();
                System.arraycopy(p_76607_1_, k, nibblearray.field_76585_a, 0, nibblearray.field_76585_a.length);
                k += nibblearray.field_76585_a.length;
            }
        }

        for (l = 0; l < this.field_76652_q.length; ++l)
        {
            if ((p_76607_2_ & 1 << l) != 0 && this.field_76652_q[l] != null)
            {
                nibblearray = this.field_76652_q[l].func_76661_k();
                System.arraycopy(p_76607_1_, k, nibblearray.field_76585_a, 0, nibblearray.field_76585_a.length);
                k += nibblearray.field_76585_a.length;
            }
        }

        if (flag1)
        {
            for (l = 0; l < this.field_76652_q.length; ++l)
            {
                if ((p_76607_2_ & 1 << l) != 0 && this.field_76652_q[l] != null)
                {
                    nibblearray = this.field_76652_q[l].func_76671_l();
                    System.arraycopy(p_76607_1_, k, nibblearray.field_76585_a, 0, nibblearray.field_76585_a.length);
                    k += nibblearray.field_76585_a.length;
                }
            }
        }

        for (l = 0; l < this.field_76652_q.length; ++l)
        {
            if ((p_76607_3_ & 1 << l) != 0)
            {
                if (this.field_76652_q[l] == null)
                {
                    k += 2048;
                }
                else
                {
                    nibblearray = this.field_76652_q[l].func_76660_i();

                    if (nibblearray == null)
                    {
                        nibblearray = this.field_76652_q[l].func_76667_m();
                    }

                    System.arraycopy(p_76607_1_, k, nibblearray.field_76585_a, 0, nibblearray.field_76585_a.length);
                    k += nibblearray.field_76585_a.length;
                }
            }
            else if (p_76607_4_ && this.field_76652_q[l] != null && this.field_76652_q[l].func_76660_i() != null)
            {
                this.field_76652_q[l].func_76676_h();
            }
        }

        if (p_76607_4_)
        {
            System.arraycopy(p_76607_1_, k, this.field_76651_r, 0, this.field_76651_r.length);
            int i1 = k + this.field_76651_r.length;
        }

        for (l = 0; l < this.field_76652_q.length; ++l)
        {
            if (this.field_76652_q[l] != null && (p_76607_2_ & 1 << l) != 0)
            {
                this.field_76652_q[l].func_76672_e();
            }
        }

        this.field_150814_l = true;
        this.field_76646_k = true;
        this.func_76590_a();
        Iterator iterator = this.field_150816_i.values().iterator();

        while (iterator.hasNext())
        {
            TileEntity tileentity = (TileEntity)iterator.next();
            tileentity.func_145836_u();
        }
    }

    public BiomeGenBase func_76591_a(int p_76591_1_, int p_76591_2_, WorldChunkManager p_76591_3_)
    {
        int k = this.field_76651_r[p_76591_2_ << 4 | p_76591_1_] & 255;

        if (k == 255)
        {
            BiomeGenBase biomegenbase = p_76591_3_.func_76935_a((this.field_76635_g << 4) + p_76591_1_, (this.field_76647_h << 4) + p_76591_2_);
            k = biomegenbase.field_76756_M;
            this.field_76651_r[p_76591_2_ << 4 | p_76591_1_] = (byte)(k & 255);
        }

        return BiomeGenBase.func_150568_d(k) == null ? BiomeGenBase.field_76772_c : BiomeGenBase.func_150568_d(k);
    }

    public byte[] func_76605_m()
    {
        return this.field_76651_r;
    }

    public void func_76616_a(byte[] p_76616_1_)
    {
        this.field_76651_r = p_76616_1_;
    }

    public void func_76613_n()
    {
        this.field_76649_t = 0;
    }

    public void func_76594_o()
    {
        for (int i = 0; i < 8; ++i)
        {
            if (this.field_76649_t >= 4096)
            {
                return;
            }

            int j = this.field_76649_t % 16;
            int k = this.field_76649_t / 16 % 16;
            int l = this.field_76649_t / 256;
            ++this.field_76649_t;
            int i1 = (this.field_76635_g << 4) + k;
            int j1 = (this.field_76647_h << 4) + l;

            for (int k1 = 0; k1 < 16; ++k1)
            {
                int l1 = (j << 4) + k1;

                if (this.field_76652_q[j] == null && (k1 == 0 || k1 == 15 || k == 0 || k == 15 || l == 0 || l == 15) || this.field_76652_q[j] != null && this.field_76652_q[j].func_150819_a(k, k1, l).func_149688_o() == Material.field_151579_a)
                {
                    if (this.field_76637_e.func_147439_a(i1, l1 - 1, j1).func_149750_m() > 0)
                    {
                        this.field_76637_e.func_147451_t(i1, l1 - 1, j1);
                    }

                    if (this.field_76637_e.func_147439_a(i1, l1 + 1, j1).func_149750_m() > 0)
                    {
                        this.field_76637_e.func_147451_t(i1, l1 + 1, j1);
                    }

                    if (this.field_76637_e.func_147439_a(i1 - 1, l1, j1).func_149750_m() > 0)
                    {
                        this.field_76637_e.func_147451_t(i1 - 1, l1, j1);
                    }

                    if (this.field_76637_e.func_147439_a(i1 + 1, l1, j1).func_149750_m() > 0)
                    {
                        this.field_76637_e.func_147451_t(i1 + 1, l1, j1);
                    }

                    if (this.field_76637_e.func_147439_a(i1, l1, j1 - 1).func_149750_m() > 0)
                    {
                        this.field_76637_e.func_147451_t(i1, l1, j1 - 1);
                    }

                    if (this.field_76637_e.func_147439_a(i1, l1, j1 + 1).func_149750_m() > 0)
                    {
                        this.field_76637_e.func_147451_t(i1, l1, j1 + 1);
                    }

                    this.field_76637_e.func_147451_t(i1, l1, j1);
                }
            }
        }
    }

    public void func_150809_p()
    {
        this.field_76646_k = true;
        this.field_150814_l = true;

        if (!this.field_76637_e.field_73011_w.field_76576_e)
        {
            if (this.field_76637_e.func_72904_c(this.field_76635_g * 16 - 1, 0, this.field_76647_h * 16 - 1, this.field_76635_g * 16 + 1, 63, this.field_76647_h * 16 + 1))
            {
                for (int i = 0; i < 16; ++i)
                {
                    for (int j = 0; j < 16; ++j)
                    {
                        if (!this.func_150811_f(i, j))
                        {
                            this.field_150814_l = false;
                            break;
                        }
                    }
                }

                if (this.field_150814_l)
                {
                    Chunk chunk = this.field_76637_e.func_72938_d(this.field_76635_g * 16 - 1, this.field_76647_h * 16);
                    chunk.func_150801_a(3);
                    chunk = this.field_76637_e.func_72938_d(this.field_76635_g * 16 + 16, this.field_76647_h * 16);
                    chunk.func_150801_a(1);
                    chunk = this.field_76637_e.func_72938_d(this.field_76635_g * 16, this.field_76647_h * 16 - 1);
                    chunk.func_150801_a(0);
                    chunk = this.field_76637_e.func_72938_d(this.field_76635_g * 16, this.field_76647_h * 16 + 16);
                    chunk.func_150801_a(2);
                }
            }
            else
            {
                this.field_150814_l = false;
            }
        }
    }

    private void func_150801_a(int p_150801_1_)
    {
        if (this.field_76646_k)
        {
            int j;

            if (p_150801_1_ == 3)
            {
                for (j = 0; j < 16; ++j)
                {
                    this.func_150811_f(15, j);
                }
            }
            else if (p_150801_1_ == 1)
            {
                for (j = 0; j < 16; ++j)
                {
                    this.func_150811_f(0, j);
                }
            }
            else if (p_150801_1_ == 0)
            {
                for (j = 0; j < 16; ++j)
                {
                    this.func_150811_f(j, 15);
                }
            }
            else if (p_150801_1_ == 2)
            {
                for (j = 0; j < 16; ++j)
                {
                    this.func_150811_f(j, 0);
                }
            }
        }
    }

    private boolean func_150811_f(int p_150811_1_, int p_150811_2_)
    {
        int k = this.func_76625_h();
        boolean flag = false;
        boolean flag1 = false;
        int l;

        for (l = k + 16 - 1; l > 63 || l > 0 && !flag1; --l)
        {
            int i1 = this.func_150808_b(p_150811_1_, l, p_150811_2_);

            if (i1 == 255 && l < 63)
            {
                flag1 = true;
            }

            if (!flag && i1 > 0)
            {
                flag = true;
            }
            else if (flag && i1 == 0 && !this.field_76637_e.func_147451_t(this.field_76635_g * 16 + p_150811_1_, l, this.field_76647_h * 16 + p_150811_2_))
            {
                return false;
            }
        }

        for (; l > 0; --l)
        {
            if (this.func_150810_a(p_150811_1_, l, p_150811_2_).func_149750_m() > 0)
            {
                this.field_76637_e.func_147451_t(this.field_76635_g * 16 + p_150811_1_, l, this.field_76647_h * 16 + p_150811_2_);
            }
        }

        return true;
    }
}