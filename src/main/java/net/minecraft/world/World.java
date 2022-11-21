package net.minecraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.village.VillageCollection;
import net.minecraft.village.VillageSiege;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldInfo;

public abstract class World implements IBlockAccess
{
    public boolean field_72999_e;
    public List field_72996_f = new ArrayList();
    protected List field_72997_g = new ArrayList();
    public List field_147482_g = new ArrayList();
    private List field_147484_a = new ArrayList();
    private List field_147483_b = new ArrayList();
    public List field_73010_i = new ArrayList();
    public List field_73007_j = new ArrayList();
    private long field_73001_c = 16777215L;
    public int field_73008_k;
    protected int field_73005_l = (new Random()).nextInt();
    protected final int field_73006_m = 1013904223;
    public float field_73003_n;
    public float field_73004_o;
    public float field_73018_p;
    public float field_73017_q;
    public int field_73016_r;
    public EnumDifficulty field_73013_u;
    public Random field_73012_v = new Random();
    public final WorldProvider field_73011_w;
    protected List field_73021_x = new ArrayList();
    protected IChunkProvider field_73020_y;
    protected final ISaveHandler field_73019_z;
    protected WorldInfo field_72986_A;
    public boolean field_72987_B;
    public MapStorage field_72988_C;
    public VillageCollection field_72982_D;
    protected final VillageSiege field_72983_E = new VillageSiege(this);
    public final Profiler field_72984_F;
    private final Calendar field_83016_L = Calendar.getInstance();
    protected Scoreboard field_96442_D = new Scoreboard();
    public boolean field_72995_K;
    protected Set field_72993_I = new HashSet();
    private int field_72990_M;
    protected boolean field_72985_G;
    protected boolean field_72992_H;
    private ArrayList field_72998_d;
    private boolean field_147481_N;
    int[] field_72994_J;
    private static final String __OBFID = "CL_00000140";

    public BiomeGenBase func_72807_a(final int p_72807_1_, final int p_72807_2_)
    {
        if (this.func_72899_e(p_72807_1_, 0, p_72807_2_))
        {
            Chunk chunk = this.func_72938_d(p_72807_1_, p_72807_2_);

            try
            {
                return chunk.func_76591_a(p_72807_1_ & 15, p_72807_2_ & 15, this.field_73011_w.field_76578_c);
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.func_85055_a(throwable, "Getting biome");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Coordinates of biome request");
                crashreportcategory.func_71500_a("Location", new Callable()
                {
                    private static final String __OBFID = "CL_00000141";
                    public String call()
                    {
                        return CrashReportCategory.func_85071_a(p_72807_1_, 0, p_72807_2_);
                    }
                });
                throw new ReportedException(crashreport);
            }
        }
        else
        {
            return this.field_73011_w.field_76578_c.func_76935_a(p_72807_1_, p_72807_2_);
        }
    }

    public WorldChunkManager func_72959_q()
    {
        return this.field_73011_w.field_76578_c;
    }

    @SideOnly(Side.CLIENT)
    public World(ISaveHandler p_i45368_1_, String p_i45368_2_, WorldProvider p_i45368_3_, WorldSettings p_i45368_4_, Profiler p_i45368_5_)
    {
        this.field_72990_M = this.field_73012_v.nextInt(12000);
        this.field_72985_G = true;
        this.field_72992_H = true;
        this.field_72998_d = new ArrayList();
        this.field_72994_J = new int[32768];
        this.field_73019_z = p_i45368_1_;
        this.field_72984_F = p_i45368_5_;
        this.field_72986_A = new WorldInfo(p_i45368_4_, p_i45368_2_);
        this.field_73011_w = p_i45368_3_;
        this.field_72988_C = new MapStorage(p_i45368_1_);
        VillageCollection villagecollection = (VillageCollection)this.field_72988_C.func_75742_a(VillageCollection.class, "villages");

        if (villagecollection == null)
        {
            this.field_72982_D = new VillageCollection(this);
            this.field_72988_C.func_75745_a("villages", this.field_72982_D);
        }
        else
        {
            this.field_72982_D = villagecollection;
            this.field_72982_D.func_82566_a(this);
        }

        p_i45368_3_.func_76558_a(this);
        this.field_73020_y = this.func_72970_h();
        this.func_72966_v();
        this.func_72947_a();
    }

    public World(ISaveHandler p_i45369_1_, String p_i45369_2_, WorldSettings p_i45369_3_, WorldProvider p_i45369_4_, Profiler p_i45369_5_)
    {
        this.field_72990_M = this.field_73012_v.nextInt(12000);
        this.field_72985_G = true;
        this.field_72992_H = true;
        this.field_72998_d = new ArrayList();
        this.field_72994_J = new int[32768];
        this.field_73019_z = p_i45369_1_;
        this.field_72984_F = p_i45369_5_;
        this.field_72988_C = new MapStorage(p_i45369_1_);
        this.field_72986_A = p_i45369_1_.func_75757_d();

        if (p_i45369_4_ != null)
        {
            this.field_73011_w = p_i45369_4_;
        }
        else if (this.field_72986_A != null && this.field_72986_A.func_76076_i() != 0)
        {
            this.field_73011_w = WorldProvider.func_76570_a(this.field_72986_A.func_76076_i());
        }
        else
        {
            this.field_73011_w = WorldProvider.func_76570_a(0);
        }

        if (this.field_72986_A == null)
        {
            this.field_72986_A = new WorldInfo(p_i45369_3_, p_i45369_2_);
        }
        else
        {
            this.field_72986_A.func_76062_a(p_i45369_2_);
        }

        this.field_73011_w.func_76558_a(this);
        this.field_73020_y = this.func_72970_h();

        if (!this.field_72986_A.func_76070_v())
        {
            try
            {
                this.func_72963_a(p_i45369_3_);
            }
            catch (Throwable throwable1)
            {
                CrashReport crashreport = CrashReport.func_85055_a(throwable1, "Exception initializing level");

                try
                {
                    this.func_72914_a(crashreport);
                }
                catch (Throwable throwable)
                {
                    ;
                }

                throw new ReportedException(crashreport);
            }

            this.field_72986_A.func_76091_d(true);
        }

        VillageCollection villagecollection = (VillageCollection)this.field_72988_C.func_75742_a(VillageCollection.class, "villages");

        if (villagecollection == null)
        {
            this.field_72982_D = new VillageCollection(this);
            this.field_72988_C.func_75745_a("villages", this.field_72982_D);
        }
        else
        {
            this.field_72982_D = villagecollection;
            this.field_72982_D.func_82566_a(this);
        }

        this.func_72966_v();
        this.func_72947_a();
    }

    protected abstract IChunkProvider func_72970_h();

    protected void func_72963_a(WorldSettings p_72963_1_)
    {
        this.field_72986_A.func_76091_d(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_72974_f()
    {
        this.func_72950_A(8, 64, 8);
    }

    public Block func_147474_b(int p_147474_1_, int p_147474_2_)
    {
        int k;

        for (k = 63; !this.func_147437_c(p_147474_1_, k + 1, p_147474_2_); ++k)
        {
            ;
        }

        return this.func_147439_a(p_147474_1_, k, p_147474_2_);
    }

    public Block func_147439_a(int p_147439_1_, int p_147439_2_, int p_147439_3_)
    {
        if (p_147439_1_ >= -30000000 && p_147439_3_ >= -30000000 && p_147439_1_ < 30000000 && p_147439_3_ < 30000000 && p_147439_2_ >= 0 && p_147439_2_ < 256)
        {
            Chunk chunk = null;

            try
            {
                chunk = this.func_72964_e(p_147439_1_ >> 4, p_147439_3_ >> 4);
                return chunk.func_150810_a(p_147439_1_ & 15, p_147439_2_, p_147439_3_ & 15);
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.func_85055_a(throwable, "Exception getting block type in world");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Requested block coordinates");
                crashreportcategory.func_71507_a("Found chunk", Boolean.valueOf(chunk == null));
                crashreportcategory.func_71507_a("Location", CrashReportCategory.func_85071_a(p_147439_1_, p_147439_2_, p_147439_3_));
                throw new ReportedException(crashreport);
            }
        }
        else
        {
            return Blocks.AIR;
        }
    }

    public boolean func_147437_c(int p_147437_1_, int p_147437_2_, int p_147437_3_)
    {
        return this.func_147439_a(p_147437_1_, p_147437_2_, p_147437_3_).func_149688_o() == Material.field_151579_a;
    }

    public boolean func_72899_e(int p_72899_1_, int p_72899_2_, int p_72899_3_)
    {
        return p_72899_2_ >= 0 && p_72899_2_ < 256 ? this.func_72916_c(p_72899_1_ >> 4, p_72899_3_ >> 4) : false;
    }

    public boolean func_72873_a(int p_72873_1_, int p_72873_2_, int p_72873_3_, int p_72873_4_)
    {
        return this.func_72904_c(p_72873_1_ - p_72873_4_, p_72873_2_ - p_72873_4_, p_72873_3_ - p_72873_4_, p_72873_1_ + p_72873_4_, p_72873_2_ + p_72873_4_, p_72873_3_ + p_72873_4_);
    }

    public boolean func_72904_c(int p_72904_1_, int p_72904_2_, int p_72904_3_, int p_72904_4_, int p_72904_5_, int p_72904_6_)
    {
        if (p_72904_5_ >= 0 && p_72904_2_ < 256)
        {
            p_72904_1_ >>= 4;
            p_72904_3_ >>= 4;
            p_72904_4_ >>= 4;
            p_72904_6_ >>= 4;

            for (int k1 = p_72904_1_; k1 <= p_72904_4_; ++k1)
            {
                for (int l1 = p_72904_3_; l1 <= p_72904_6_; ++l1)
                {
                    if (!this.func_72916_c(k1, l1))
                    {
                        return false;
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean func_72916_c(int p_72916_1_, int p_72916_2_)
    {
        return this.field_73020_y.func_73149_a(p_72916_1_, p_72916_2_);
    }

    public Chunk func_72938_d(int p_72938_1_, int p_72938_2_)
    {
        return this.func_72964_e(p_72938_1_ >> 4, p_72938_2_ >> 4);
    }

    public Chunk func_72964_e(int p_72964_1_, int p_72964_2_)
    {
        return this.field_73020_y.func_73154_d(p_72964_1_, p_72964_2_);
    }

    public boolean func_147465_d(int p_147465_1_, int p_147465_2_, int p_147465_3_, Block p_147465_4_, int p_147465_5_, int p_147465_6_)
    {
        if (p_147465_1_ >= -30000000 && p_147465_3_ >= -30000000 && p_147465_1_ < 30000000 && p_147465_3_ < 30000000)
        {
            if (p_147465_2_ < 0)
            {
                return false;
            }
            else if (p_147465_2_ >= 256)
            {
                return false;
            }
            else
            {
                Chunk chunk = this.func_72964_e(p_147465_1_ >> 4, p_147465_3_ >> 4);
                Block block1 = null;

                if ((p_147465_6_ & 1) != 0)
                {
                    block1 = chunk.func_150810_a(p_147465_1_ & 15, p_147465_2_, p_147465_3_ & 15);
                }

                boolean flag = chunk.func_150807_a(p_147465_1_ & 15, p_147465_2_, p_147465_3_ & 15, p_147465_4_, p_147465_5_);
                this.field_72984_F.func_76320_a("checkLight");
                this.func_147451_t(p_147465_1_, p_147465_2_, p_147465_3_);
                this.field_72984_F.func_76319_b();

                if (flag)
                {
                    if ((p_147465_6_ & 2) != 0 && (!this.field_72995_K || (p_147465_6_ & 4) == 0) && chunk.func_150802_k())
                    {
                        this.func_147471_g(p_147465_1_, p_147465_2_, p_147465_3_);
                    }

                    if (!this.field_72995_K && (p_147465_6_ & 1) != 0)
                    {
                        this.func_147444_c(p_147465_1_, p_147465_2_, p_147465_3_, block1);

                        if (p_147465_4_.func_149740_M())
                        {
                            this.func_147453_f(p_147465_1_, p_147465_2_, p_147465_3_, p_147465_4_);
                        }
                    }
                }

                return flag;
            }
        }
        else
        {
            return false;
        }
    }

    public int func_72805_g(int p_72805_1_, int p_72805_2_, int p_72805_3_)
    {
        if (p_72805_1_ >= -30000000 && p_72805_3_ >= -30000000 && p_72805_1_ < 30000000 && p_72805_3_ < 30000000)
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
                Chunk chunk = this.func_72964_e(p_72805_1_ >> 4, p_72805_3_ >> 4);
                p_72805_1_ &= 15;
                p_72805_3_ &= 15;
                return chunk.func_76628_c(p_72805_1_, p_72805_2_, p_72805_3_);
            }
        }
        else
        {
            return 0;
        }
    }

    public boolean func_72921_c(int p_72921_1_, int p_72921_2_, int p_72921_3_, int p_72921_4_, int p_72921_5_)
    {
        if (p_72921_1_ >= -30000000 && p_72921_3_ >= -30000000 && p_72921_1_ < 30000000 && p_72921_3_ < 30000000)
        {
            if (p_72921_2_ < 0)
            {
                return false;
            }
            else if (p_72921_2_ >= 256)
            {
                return false;
            }
            else
            {
                Chunk chunk = this.func_72964_e(p_72921_1_ >> 4, p_72921_3_ >> 4);
                int j1 = p_72921_1_ & 15;
                int k1 = p_72921_3_ & 15;
                boolean flag = chunk.func_76589_b(j1, p_72921_2_, k1, p_72921_4_);

                if (flag)
                {
                    Block block = chunk.func_150810_a(j1, p_72921_2_, k1);

                    if ((p_72921_5_ & 2) != 0 && (!this.field_72995_K || (p_72921_5_ & 4) == 0) && chunk.func_150802_k())
                    {
                        this.func_147471_g(p_72921_1_, p_72921_2_, p_72921_3_);
                    }

                    if (!this.field_72995_K && (p_72921_5_ & 1) != 0)
                    {
                        this.func_147444_c(p_72921_1_, p_72921_2_, p_72921_3_, block);

                        if (block.func_149740_M())
                        {
                            this.func_147453_f(p_72921_1_, p_72921_2_, p_72921_3_, block);
                        }
                    }
                }

                return flag;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean func_147468_f(int p_147468_1_, int p_147468_2_, int p_147468_3_)
    {
        return this.func_147465_d(p_147468_1_, p_147468_2_, p_147468_3_, Blocks.AIR, 0, 3);
    }

    public boolean func_147480_a(int p_147480_1_, int p_147480_2_, int p_147480_3_, boolean p_147480_4_)
    {
        Block block = this.func_147439_a(p_147480_1_, p_147480_2_, p_147480_3_);

        if (block.func_149688_o() == Material.field_151579_a)
        {
            return false;
        }
        else
        {
            int l = this.func_72805_g(p_147480_1_, p_147480_2_, p_147480_3_);
            this.func_72926_e(2001, p_147480_1_, p_147480_2_, p_147480_3_, Block.func_149682_b(block) + (l << 12));

            if (p_147480_4_)
            {
                block.func_149697_b(this, p_147480_1_, p_147480_2_, p_147480_3_, l, 0);
            }

            return this.func_147465_d(p_147480_1_, p_147480_2_, p_147480_3_, Blocks.AIR, 0, 3);
        }
    }

    public boolean func_147449_b(int p_147449_1_, int p_147449_2_, int p_147449_3_, Block p_147449_4_)
    {
        return this.func_147465_d(p_147449_1_, p_147449_2_, p_147449_3_, p_147449_4_, 0, 3);
    }

    public void func_147471_g(int p_147471_1_, int p_147471_2_, int p_147471_3_)
    {
        for (int l = 0; l < this.field_73021_x.size(); ++l)
        {
            ((IWorldAccess)this.field_73021_x.get(l)).func_147586_a(p_147471_1_, p_147471_2_, p_147471_3_);
        }
    }

    public void func_147444_c(int p_147444_1_, int p_147444_2_, int p_147444_3_, Block p_147444_4_)
    {
        this.func_147459_d(p_147444_1_, p_147444_2_, p_147444_3_, p_147444_4_);
    }

    public void func_72975_g(int p_72975_1_, int p_72975_2_, int p_72975_3_, int p_72975_4_)
    {
        int i1;

        if (p_72975_3_ > p_72975_4_)
        {
            i1 = p_72975_4_;
            p_72975_4_ = p_72975_3_;
            p_72975_3_ = i1;
        }

        if (!this.field_73011_w.field_76576_e)
        {
            for (i1 = p_72975_3_; i1 <= p_72975_4_; ++i1)
            {
                this.func_147463_c(EnumSkyBlock.Sky, p_72975_1_, i1, p_72975_2_);
            }
        }

        this.func_147458_c(p_72975_1_, p_72975_3_, p_72975_2_, p_72975_1_, p_72975_4_, p_72975_2_);
    }

    public void func_147458_c(int p_147458_1_, int p_147458_2_, int p_147458_3_, int p_147458_4_, int p_147458_5_, int p_147458_6_)
    {
        for (int k1 = 0; k1 < this.field_73021_x.size(); ++k1)
        {
            ((IWorldAccess)this.field_73021_x.get(k1)).func_147585_a(p_147458_1_, p_147458_2_, p_147458_3_, p_147458_4_, p_147458_5_, p_147458_6_);
        }
    }

    public void func_147459_d(int p_147459_1_, int p_147459_2_, int p_147459_3_, Block p_147459_4_)
    {
        this.func_147460_e(p_147459_1_ - 1, p_147459_2_, p_147459_3_, p_147459_4_);
        this.func_147460_e(p_147459_1_ + 1, p_147459_2_, p_147459_3_, p_147459_4_);
        this.func_147460_e(p_147459_1_, p_147459_2_ - 1, p_147459_3_, p_147459_4_);
        this.func_147460_e(p_147459_1_, p_147459_2_ + 1, p_147459_3_, p_147459_4_);
        this.func_147460_e(p_147459_1_, p_147459_2_, p_147459_3_ - 1, p_147459_4_);
        this.func_147460_e(p_147459_1_, p_147459_2_, p_147459_3_ + 1, p_147459_4_);
    }

    public void func_147441_b(int p_147441_1_, int p_147441_2_, int p_147441_3_, Block p_147441_4_, int p_147441_5_)
    {
        if (p_147441_5_ != 4)
        {
            this.func_147460_e(p_147441_1_ - 1, p_147441_2_, p_147441_3_, p_147441_4_);
        }

        if (p_147441_5_ != 5)
        {
            this.func_147460_e(p_147441_1_ + 1, p_147441_2_, p_147441_3_, p_147441_4_);
        }

        if (p_147441_5_ != 0)
        {
            this.func_147460_e(p_147441_1_, p_147441_2_ - 1, p_147441_3_, p_147441_4_);
        }

        if (p_147441_5_ != 1)
        {
            this.func_147460_e(p_147441_1_, p_147441_2_ + 1, p_147441_3_, p_147441_4_);
        }

        if (p_147441_5_ != 2)
        {
            this.func_147460_e(p_147441_1_, p_147441_2_, p_147441_3_ - 1, p_147441_4_);
        }

        if (p_147441_5_ != 3)
        {
            this.func_147460_e(p_147441_1_, p_147441_2_, p_147441_3_ + 1, p_147441_4_);
        }
    }

    public void func_147460_e(int p_147460_1_, int p_147460_2_, int p_147460_3_, final Block p_147460_4_)
    {
        if (!this.field_72995_K)
        {
            Block block = this.func_147439_a(p_147460_1_, p_147460_2_, p_147460_3_);

            try
            {
                block.func_149695_a(this, p_147460_1_, p_147460_2_, p_147460_3_, p_147460_4_);
            }
            catch (Throwable throwable1)
            {
                CrashReport crashreport = CrashReport.func_85055_a(throwable1, "Exception while updating neighbours");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Block being updated");
                int l;

                try
                {
                    l = this.func_72805_g(p_147460_1_, p_147460_2_, p_147460_3_);
                }
                catch (Throwable throwable)
                {
                    l = -1;
                }

                crashreportcategory.func_71500_a("Source block type", new Callable()
                {
                    private static final String __OBFID = "CL_00000142";
                    public String call()
                    {
                        try
                        {
                            return String.format("ID #%d (%s // %s)", new Object[] {Integer.valueOf(Block.func_149682_b(p_147460_4_)), p_147460_4_.func_149739_a(), p_147460_4_.getClass().getCanonicalName()});
                        }
                        catch (Throwable throwable2)
                        {
                            return "ID #" + Block.func_149682_b(p_147460_4_);
                        }
                    }
                });
                CrashReportCategory.func_147153_a(crashreportcategory, p_147460_1_, p_147460_2_, p_147460_3_, block, l);
                throw new ReportedException(crashreport);
            }
        }
    }

    public boolean func_147477_a(int p_147477_1_, int p_147477_2_, int p_147477_3_, Block p_147477_4_)
    {
        return false;
    }

    public boolean func_72937_j(int p_72937_1_, int p_72937_2_, int p_72937_3_)
    {
        return this.func_72964_e(p_72937_1_ >> 4, p_72937_3_ >> 4).func_76619_d(p_72937_1_ & 15, p_72937_2_, p_72937_3_ & 15);
    }

    public int func_72883_k(int p_72883_1_, int p_72883_2_, int p_72883_3_)
    {
        if (p_72883_2_ < 0)
        {
            return 0;
        }
        else
        {
            if (p_72883_2_ >= 256)
            {
                p_72883_2_ = 255;
            }

            return this.func_72964_e(p_72883_1_ >> 4, p_72883_3_ >> 4).func_76629_c(p_72883_1_ & 15, p_72883_2_, p_72883_3_ & 15, 0);
        }
    }

    public int func_72957_l(int p_72957_1_, int p_72957_2_, int p_72957_3_)
    {
        return this.func_72849_a(p_72957_1_, p_72957_2_, p_72957_3_, true);
    }

    public int func_72849_a(int p_72849_1_, int p_72849_2_, int p_72849_3_, boolean p_72849_4_)
    {
        if (p_72849_1_ >= -30000000 && p_72849_3_ >= -30000000 && p_72849_1_ < 30000000 && p_72849_3_ < 30000000)
        {
            if (p_72849_4_ && this.func_147439_a(p_72849_1_, p_72849_2_, p_72849_3_).func_149710_n())
            {
                int l1 = this.func_72849_a(p_72849_1_, p_72849_2_ + 1, p_72849_3_, false);
                int l = this.func_72849_a(p_72849_1_ + 1, p_72849_2_, p_72849_3_, false);
                int i1 = this.func_72849_a(p_72849_1_ - 1, p_72849_2_, p_72849_3_, false);
                int j1 = this.func_72849_a(p_72849_1_, p_72849_2_, p_72849_3_ + 1, false);
                int k1 = this.func_72849_a(p_72849_1_, p_72849_2_, p_72849_3_ - 1, false);

                if (l > l1)
                {
                    l1 = l;
                }

                if (i1 > l1)
                {
                    l1 = i1;
                }

                if (j1 > l1)
                {
                    l1 = j1;
                }

                if (k1 > l1)
                {
                    l1 = k1;
                }

                return l1;
            }
            else if (p_72849_2_ < 0)
            {
                return 0;
            }
            else
            {
                if (p_72849_2_ >= 256)
                {
                    p_72849_2_ = 255;
                }

                Chunk chunk = this.func_72964_e(p_72849_1_ >> 4, p_72849_3_ >> 4);
                p_72849_1_ &= 15;
                p_72849_3_ &= 15;
                return chunk.func_76629_c(p_72849_1_, p_72849_2_, p_72849_3_, this.field_73008_k);
            }
        }
        else
        {
            return 15;
        }
    }

    public int func_72976_f(int p_72976_1_, int p_72976_2_)
    {
        if (p_72976_1_ >= -30000000 && p_72976_2_ >= -30000000 && p_72976_1_ < 30000000 && p_72976_2_ < 30000000)
        {
            if (!this.func_72916_c(p_72976_1_ >> 4, p_72976_2_ >> 4))
            {
                return 0;
            }
            else
            {
                Chunk chunk = this.func_72964_e(p_72976_1_ >> 4, p_72976_2_ >> 4);
                return chunk.func_76611_b(p_72976_1_ & 15, p_72976_2_ & 15);
            }
        }
        else
        {
            return 64;
        }
    }

    public int func_82734_g(int p_82734_1_, int p_82734_2_)
    {
        if (p_82734_1_ >= -30000000 && p_82734_2_ >= -30000000 && p_82734_1_ < 30000000 && p_82734_2_ < 30000000)
        {
            if (!this.func_72916_c(p_82734_1_ >> 4, p_82734_2_ >> 4))
            {
                return 0;
            }
            else
            {
                Chunk chunk = this.func_72964_e(p_82734_1_ >> 4, p_82734_2_ >> 4);
                return chunk.field_82912_p;
            }
        }
        else
        {
            return 64;
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_72925_a(EnumSkyBlock p_72925_1_, int p_72925_2_, int p_72925_3_, int p_72925_4_)
    {
        if (this.field_73011_w.field_76576_e && p_72925_1_ == EnumSkyBlock.Sky)
        {
            return 0;
        }
        else
        {
            if (p_72925_3_ < 0)
            {
                p_72925_3_ = 0;
            }

            if (p_72925_3_ >= 256)
            {
                return p_72925_1_.field_77198_c;
            }
            else if (p_72925_2_ >= -30000000 && p_72925_4_ >= -30000000 && p_72925_2_ < 30000000 && p_72925_4_ < 30000000)
            {
                int l = p_72925_2_ >> 4;
                int i1 = p_72925_4_ >> 4;

                if (!this.func_72916_c(l, i1))
                {
                    return p_72925_1_.field_77198_c;
                }
                else if (this.func_147439_a(p_72925_2_, p_72925_3_, p_72925_4_).func_149710_n())
                {
                    int j2 = this.func_72972_b(p_72925_1_, p_72925_2_, p_72925_3_ + 1, p_72925_4_);
                    int j1 = this.func_72972_b(p_72925_1_, p_72925_2_ + 1, p_72925_3_, p_72925_4_);
                    int k1 = this.func_72972_b(p_72925_1_, p_72925_2_ - 1, p_72925_3_, p_72925_4_);
                    int l1 = this.func_72972_b(p_72925_1_, p_72925_2_, p_72925_3_, p_72925_4_ + 1);
                    int i2 = this.func_72972_b(p_72925_1_, p_72925_2_, p_72925_3_, p_72925_4_ - 1);

                    if (j1 > j2)
                    {
                        j2 = j1;
                    }

                    if (k1 > j2)
                    {
                        j2 = k1;
                    }

                    if (l1 > j2)
                    {
                        j2 = l1;
                    }

                    if (i2 > j2)
                    {
                        j2 = i2;
                    }

                    return j2;
                }
                else
                {
                    Chunk chunk = this.func_72964_e(l, i1);
                    return chunk.func_76614_a(p_72925_1_, p_72925_2_ & 15, p_72925_3_, p_72925_4_ & 15);
                }
            }
            else
            {
                return p_72925_1_.field_77198_c;
            }
        }
    }

    public int func_72972_b(EnumSkyBlock p_72972_1_, int p_72972_2_, int p_72972_3_, int p_72972_4_)
    {
        if (p_72972_3_ < 0)
        {
            p_72972_3_ = 0;
        }

        if (p_72972_3_ >= 256)
        {
            p_72972_3_ = 255;
        }

        if (p_72972_2_ >= -30000000 && p_72972_4_ >= -30000000 && p_72972_2_ < 30000000 && p_72972_4_ < 30000000)
        {
            int l = p_72972_2_ >> 4;
            int i1 = p_72972_4_ >> 4;

            if (!this.func_72916_c(l, i1))
            {
                return p_72972_1_.field_77198_c;
            }
            else
            {
                Chunk chunk = this.func_72964_e(l, i1);
                return chunk.func_76614_a(p_72972_1_, p_72972_2_ & 15, p_72972_3_, p_72972_4_ & 15);
            }
        }
        else
        {
            return p_72972_1_.field_77198_c;
        }
    }

    public void func_72915_b(EnumSkyBlock p_72915_1_, int p_72915_2_, int p_72915_3_, int p_72915_4_, int p_72915_5_)
    {
        if (p_72915_2_ >= -30000000 && p_72915_4_ >= -30000000 && p_72915_2_ < 30000000 && p_72915_4_ < 30000000)
        {
            if (p_72915_3_ >= 0)
            {
                if (p_72915_3_ < 256)
                {
                    if (this.func_72916_c(p_72915_2_ >> 4, p_72915_4_ >> 4))
                    {
                        Chunk chunk = this.func_72964_e(p_72915_2_ >> 4, p_72915_4_ >> 4);
                        chunk.func_76633_a(p_72915_1_, p_72915_2_ & 15, p_72915_3_, p_72915_4_ & 15, p_72915_5_);

                        for (int i1 = 0; i1 < this.field_73021_x.size(); ++i1)
                        {
                            ((IWorldAccess)this.field_73021_x.get(i1)).func_147588_b(p_72915_2_, p_72915_3_, p_72915_4_);
                        }
                    }
                }
            }
        }
    }

    public void func_147479_m(int p_147479_1_, int p_147479_2_, int p_147479_3_)
    {
        for (int l = 0; l < this.field_73021_x.size(); ++l)
        {
            ((IWorldAccess)this.field_73021_x.get(l)).func_147588_b(p_147479_1_, p_147479_2_, p_147479_3_);
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_72802_i(int p_72802_1_, int p_72802_2_, int p_72802_3_, int p_72802_4_)
    {
        int i1 = this.func_72925_a(EnumSkyBlock.Sky, p_72802_1_, p_72802_2_, p_72802_3_);
        int j1 = this.func_72925_a(EnumSkyBlock.Block, p_72802_1_, p_72802_2_, p_72802_3_);

        if (j1 < p_72802_4_)
        {
            j1 = p_72802_4_;
        }

        return i1 << 20 | j1 << 4;
    }

    public float func_72801_o(int p_72801_1_, int p_72801_2_, int p_72801_3_)
    {
        return this.field_73011_w.field_76573_f[this.func_72957_l(p_72801_1_, p_72801_2_, p_72801_3_)];
    }

    public boolean func_72935_r()
    {
        return this.field_73008_k < 4;
    }

    public MovingObjectPosition func_72933_a(Vec3 p_72933_1_, Vec3 p_72933_2_)
    {
        return this.func_147447_a(p_72933_1_, p_72933_2_, false, false, false);
    }

    public MovingObjectPosition func_72901_a(Vec3 p_72901_1_, Vec3 p_72901_2_, boolean p_72901_3_)
    {
        return this.func_147447_a(p_72901_1_, p_72901_2_, p_72901_3_, false, false);
    }

    public MovingObjectPosition func_147447_a(Vec3 p_147447_1_, Vec3 p_147447_2_, boolean p_147447_3_, boolean p_147447_4_, boolean p_147447_5_)
    {
        if (!Double.isNaN(p_147447_1_.field_72450_a) && !Double.isNaN(p_147447_1_.field_72448_b) && !Double.isNaN(p_147447_1_.field_72449_c))
        {
            if (!Double.isNaN(p_147447_2_.field_72450_a) && !Double.isNaN(p_147447_2_.field_72448_b) && !Double.isNaN(p_147447_2_.field_72449_c))
            {
                int i = MathHelper.func_76128_c(p_147447_2_.field_72450_a);
                int j = MathHelper.func_76128_c(p_147447_2_.field_72448_b);
                int k = MathHelper.func_76128_c(p_147447_2_.field_72449_c);
                int l = MathHelper.func_76128_c(p_147447_1_.field_72450_a);
                int i1 = MathHelper.func_76128_c(p_147447_1_.field_72448_b);
                int j1 = MathHelper.func_76128_c(p_147447_1_.field_72449_c);
                Block block = this.func_147439_a(l, i1, j1);
                int k1 = this.func_72805_g(l, i1, j1);

                if ((!p_147447_4_ || block.func_149668_a(this, l, i1, j1) != null) && block.func_149678_a(k1, p_147447_3_))
                {
                    MovingObjectPosition movingobjectposition = block.func_149731_a(this, l, i1, j1, p_147447_1_, p_147447_2_);

                    if (movingobjectposition != null)
                    {
                        return movingobjectposition;
                    }
                }

                MovingObjectPosition movingobjectposition2 = null;
                k1 = 200;

                while (k1-- >= 0)
                {
                    if (Double.isNaN(p_147447_1_.field_72450_a) || Double.isNaN(p_147447_1_.field_72448_b) || Double.isNaN(p_147447_1_.field_72449_c))
                    {
                        return null;
                    }

                    if (l == i && i1 == j && j1 == k)
                    {
                        return p_147447_5_ ? movingobjectposition2 : null;
                    }

                    boolean flag6 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999.0D;
                    double d1 = 999.0D;
                    double d2 = 999.0D;

                    if (i > l)
                    {
                        d0 = (double)l + 1.0D;
                    }
                    else if (i < l)
                    {
                        d0 = (double)l + 0.0D;
                    }
                    else
                    {
                        flag6 = false;
                    }

                    if (j > i1)
                    {
                        d1 = (double)i1 + 1.0D;
                    }
                    else if (j < i1)
                    {
                        d1 = (double)i1 + 0.0D;
                    }
                    else
                    {
                        flag3 = false;
                    }

                    if (k > j1)
                    {
                        d2 = (double)j1 + 1.0D;
                    }
                    else if (k < j1)
                    {
                        d2 = (double)j1 + 0.0D;
                    }
                    else
                    {
                        flag4 = false;
                    }

                    double d3 = 999.0D;
                    double d4 = 999.0D;
                    double d5 = 999.0D;
                    double d6 = p_147447_2_.field_72450_a - p_147447_1_.field_72450_a;
                    double d7 = p_147447_2_.field_72448_b - p_147447_1_.field_72448_b;
                    double d8 = p_147447_2_.field_72449_c - p_147447_1_.field_72449_c;

                    if (flag6)
                    {
                        d3 = (d0 - p_147447_1_.field_72450_a) / d6;
                    }

                    if (flag3)
                    {
                        d4 = (d1 - p_147447_1_.field_72448_b) / d7;
                    }

                    if (flag4)
                    {
                        d5 = (d2 - p_147447_1_.field_72449_c) / d8;
                    }

                    boolean flag5 = false;
                    byte b0;

                    if (d3 < d4 && d3 < d5)
                    {
                        if (i > l)
                        {
                            b0 = 4;
                        }
                        else
                        {
                            b0 = 5;
                        }

                        p_147447_1_.field_72450_a = d0;
                        p_147447_1_.field_72448_b += d7 * d3;
                        p_147447_1_.field_72449_c += d8 * d3;
                    }
                    else if (d4 < d5)
                    {
                        if (j > i1)
                        {
                            b0 = 0;
                        }
                        else
                        {
                            b0 = 1;
                        }

                        p_147447_1_.field_72450_a += d6 * d4;
                        p_147447_1_.field_72448_b = d1;
                        p_147447_1_.field_72449_c += d8 * d4;
                    }
                    else
                    {
                        if (k > j1)
                        {
                            b0 = 2;
                        }
                        else
                        {
                            b0 = 3;
                        }

                        p_147447_1_.field_72450_a += d6 * d5;
                        p_147447_1_.field_72448_b += d7 * d5;
                        p_147447_1_.field_72449_c = d2;
                    }

                    Vec3 vec32 = Vec3.func_72443_a(p_147447_1_.field_72450_a, p_147447_1_.field_72448_b, p_147447_1_.field_72449_c);
                    l = (int)(vec32.field_72450_a = (double)MathHelper.func_76128_c(p_147447_1_.field_72450_a));

                    if (b0 == 5)
                    {
                        --l;
                        ++vec32.field_72450_a;
                    }

                    i1 = (int)(vec32.field_72448_b = (double)MathHelper.func_76128_c(p_147447_1_.field_72448_b));

                    if (b0 == 1)
                    {
                        --i1;
                        ++vec32.field_72448_b;
                    }

                    j1 = (int)(vec32.field_72449_c = (double)MathHelper.func_76128_c(p_147447_1_.field_72449_c));

                    if (b0 == 3)
                    {
                        --j1;
                        ++vec32.field_72449_c;
                    }

                    Block block1 = this.func_147439_a(l, i1, j1);
                    int l1 = this.func_72805_g(l, i1, j1);

                    if (!p_147447_4_ || block1.func_149668_a(this, l, i1, j1) != null)
                    {
                        if (block1.func_149678_a(l1, p_147447_3_))
                        {
                            MovingObjectPosition movingobjectposition1 = block1.func_149731_a(this, l, i1, j1, p_147447_1_, p_147447_2_);

                            if (movingobjectposition1 != null)
                            {
                                return movingobjectposition1;
                            }
                        }
                        else
                        {
                            movingobjectposition2 = new MovingObjectPosition(l, i1, j1, b0, p_147447_1_, false);
                        }
                    }
                }

                return p_147447_5_ ? movingobjectposition2 : null;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public void func_72956_a(Entity p_72956_1_, String p_72956_2_, float p_72956_3_, float p_72956_4_)
    {
        for (int i = 0; i < this.field_73021_x.size(); ++i)
        {
            ((IWorldAccess)this.field_73021_x.get(i)).func_72704_a(p_72956_2_, p_72956_1_.field_70165_t, p_72956_1_.field_70163_u - (double)p_72956_1_.field_70129_M, p_72956_1_.field_70161_v, p_72956_3_, p_72956_4_);
        }
    }

    public void func_85173_a(EntityPlayer p_85173_1_, String p_85173_2_, float p_85173_3_, float p_85173_4_)
    {
        for (int i = 0; i < this.field_73021_x.size(); ++i)
        {
            ((IWorldAccess)this.field_73021_x.get(i)).func_85102_a(p_85173_1_, p_85173_2_, p_85173_1_.field_70165_t, p_85173_1_.field_70163_u - (double)p_85173_1_.field_70129_M, p_85173_1_.field_70161_v, p_85173_3_, p_85173_4_);
        }
    }

    public void func_72908_a(double p_72908_1_, double p_72908_3_, double p_72908_5_, String p_72908_7_, float p_72908_8_, float p_72908_9_)
    {
        for (int i = 0; i < this.field_73021_x.size(); ++i)
        {
            ((IWorldAccess)this.field_73021_x.get(i)).func_72704_a(p_72908_7_, p_72908_1_, p_72908_3_, p_72908_5_, p_72908_8_, p_72908_9_);
        }
    }

    public void func_72980_b(double p_72980_1_, double p_72980_3_, double p_72980_5_, String p_72980_7_, float p_72980_8_, float p_72980_9_, boolean p_72980_10_) {}

    public void func_72934_a(String p_72934_1_, int p_72934_2_, int p_72934_3_, int p_72934_4_)
    {
        for (int l = 0; l < this.field_73021_x.size(); ++l)
        {
            ((IWorldAccess)this.field_73021_x.get(l)).func_72702_a(p_72934_1_, p_72934_2_, p_72934_3_, p_72934_4_);
        }
    }

    public void func_72869_a(String p_72869_1_, double p_72869_2_, double p_72869_4_, double p_72869_6_, double p_72869_8_, double p_72869_10_, double p_72869_12_)
    {
        for (int i = 0; i < this.field_73021_x.size(); ++i)
        {
            ((IWorldAccess)this.field_73021_x.get(i)).func_72708_a(p_72869_1_, p_72869_2_, p_72869_4_, p_72869_6_, p_72869_8_, p_72869_10_, p_72869_12_);
        }
    }

    public boolean func_72942_c(Entity p_72942_1_)
    {
        this.field_73007_j.add(p_72942_1_);
        return true;
    }

    public boolean func_72838_d(Entity p_72838_1_)
    {
        int i = MathHelper.func_76128_c(p_72838_1_.field_70165_t / 16.0D);
        int j = MathHelper.func_76128_c(p_72838_1_.field_70161_v / 16.0D);
        boolean flag = p_72838_1_.field_98038_p;

        if (p_72838_1_ instanceof EntityPlayer)
        {
            flag = true;
        }

        if (!flag && !this.func_72916_c(i, j))
        {
            return false;
        }
        else
        {
            if (p_72838_1_ instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)p_72838_1_;
                this.field_73010_i.add(entityplayer);
                this.func_72854_c();
            }

            this.func_72964_e(i, j).func_76612_a(p_72838_1_);
            this.field_72996_f.add(p_72838_1_);
            this.func_72923_a(p_72838_1_);
            return true;
        }
    }

    public void func_72923_a(Entity p_72923_1_)
    {
        for (int i = 0; i < this.field_73021_x.size(); ++i)
        {
            ((IWorldAccess)this.field_73021_x.get(i)).func_72703_a(p_72923_1_);
        }
    }

    public void func_72847_b(Entity p_72847_1_)
    {
        for (int i = 0; i < this.field_73021_x.size(); ++i)
        {
            ((IWorldAccess)this.field_73021_x.get(i)).func_72709_b(p_72847_1_);
        }
    }

    public void func_72900_e(Entity p_72900_1_)
    {
        if (p_72900_1_.field_70153_n != null)
        {
            p_72900_1_.field_70153_n.func_70078_a((Entity)null);
        }

        if (p_72900_1_.field_70154_o != null)
        {
            p_72900_1_.func_70078_a((Entity)null);
        }

        p_72900_1_.func_70106_y();

        if (p_72900_1_ instanceof EntityPlayer)
        {
            this.field_73010_i.remove(p_72900_1_);
            this.func_72854_c();
            this.func_72847_b(p_72900_1_);
        }
    }

    public void func_72973_f(Entity p_72973_1_)
    {
        p_72973_1_.func_70106_y();

        if (p_72973_1_ instanceof EntityPlayer)
        {
            this.field_73010_i.remove(p_72973_1_);
            this.func_72854_c();
        }

        int i = p_72973_1_.field_70176_ah;
        int j = p_72973_1_.field_70164_aj;

        if (p_72973_1_.field_70175_ag && this.func_72916_c(i, j))
        {
            this.func_72964_e(i, j).func_76622_b(p_72973_1_);
        }

        this.field_72996_f.remove(p_72973_1_);
        this.func_72847_b(p_72973_1_);
    }

    public void func_72954_a(IWorldAccess p_72954_1_)
    {
        this.field_73021_x.add(p_72954_1_);
    }

    public List func_72945_a(Entity p_72945_1_, AxisAlignedBB p_72945_2_)
    {
        this.field_72998_d.clear();
        int i = MathHelper.func_76128_c(p_72945_2_.field_72340_a);
        int j = MathHelper.func_76128_c(p_72945_2_.field_72336_d + 1.0D);
        int k = MathHelper.func_76128_c(p_72945_2_.field_72338_b);
        int l = MathHelper.func_76128_c(p_72945_2_.field_72337_e + 1.0D);
        int i1 = MathHelper.func_76128_c(p_72945_2_.field_72339_c);
        int j1 = MathHelper.func_76128_c(p_72945_2_.field_72334_f + 1.0D);

        for (int k1 = i; k1 < j; ++k1)
        {
            for (int l1 = i1; l1 < j1; ++l1)
            {
                if (this.func_72899_e(k1, 64, l1))
                {
                    for (int i2 = k - 1; i2 < l; ++i2)
                    {
                        Block block;

                        if (k1 >= -30000000 && k1 < 30000000 && l1 >= -30000000 && l1 < 30000000)
                        {
                            block = this.func_147439_a(k1, i2, l1);
                        }
                        else
                        {
                            block = Blocks.STONE;
                        }

                        block.func_149743_a(this, k1, i2, l1, p_72945_2_, this.field_72998_d, p_72945_1_);
                    }
                }
            }
        }

        double d0 = 0.25D;
        List list = this.func_72839_b(p_72945_1_, p_72945_2_.func_72314_b(d0, d0, d0));

        for (int j2 = 0; j2 < list.size(); ++j2)
        {
            AxisAlignedBB axisalignedbb1 = ((Entity)list.get(j2)).func_70046_E();

            if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(p_72945_2_))
            {
                this.field_72998_d.add(axisalignedbb1);
            }

            axisalignedbb1 = p_72945_1_.func_70114_g((Entity)list.get(j2));

            if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(p_72945_2_))
            {
                this.field_72998_d.add(axisalignedbb1);
            }
        }

        return this.field_72998_d;
    }

    public List func_147461_a(AxisAlignedBB p_147461_1_)
    {
        this.field_72998_d.clear();
        int i = MathHelper.func_76128_c(p_147461_1_.field_72340_a);
        int j = MathHelper.func_76128_c(p_147461_1_.field_72336_d + 1.0D);
        int k = MathHelper.func_76128_c(p_147461_1_.field_72338_b);
        int l = MathHelper.func_76128_c(p_147461_1_.field_72337_e + 1.0D);
        int i1 = MathHelper.func_76128_c(p_147461_1_.field_72339_c);
        int j1 = MathHelper.func_76128_c(p_147461_1_.field_72334_f + 1.0D);

        for (int k1 = i; k1 < j; ++k1)
        {
            for (int l1 = i1; l1 < j1; ++l1)
            {
                if (this.func_72899_e(k1, 64, l1))
                {
                    for (int i2 = k - 1; i2 < l; ++i2)
                    {
                        Block block;

                        if (k1 >= -30000000 && k1 < 30000000 && l1 >= -30000000 && l1 < 30000000)
                        {
                            block = this.func_147439_a(k1, i2, l1);
                        }
                        else
                        {
                            block = Blocks.BEDROCK;
                        }

                        block.func_149743_a(this, k1, i2, l1, p_147461_1_, this.field_72998_d, (Entity)null);
                    }
                }
            }
        }

        return this.field_72998_d;
    }

    public int func_72967_a(float p_72967_1_)
    {
        float f1 = this.func_72826_c(p_72967_1_);
        float f2 = 1.0F - (MathHelper.func_76134_b(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        f2 = 1.0F - f2;
        f2 = (float)((double)f2 * (1.0D - (double)(this.func_72867_j(p_72967_1_) * 5.0F) / 16.0D));
        f2 = (float)((double)f2 * (1.0D - (double)(this.func_72819_i(p_72967_1_) * 5.0F) / 16.0D));
        f2 = 1.0F - f2;
        return (int)(f2 * 11.0F);
    }

    @SideOnly(Side.CLIENT)
    public void func_72848_b(IWorldAccess p_72848_1_)
    {
        this.field_73021_x.remove(p_72848_1_);
    }

    @SideOnly(Side.CLIENT)
    public float func_72971_b(float p_72971_1_)
    {
        float f1 = this.func_72826_c(p_72971_1_);
        float f2 = 1.0F - (MathHelper.func_76134_b(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.2F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        f2 = 1.0F - f2;
        f2 = (float)((double)f2 * (1.0D - (double)(this.func_72867_j(p_72971_1_) * 5.0F) / 16.0D));
        f2 = (float)((double)f2 * (1.0D - (double)(this.func_72819_i(p_72971_1_) * 5.0F) / 16.0D));
        return f2 * 0.8F + 0.2F;
    }

    @SideOnly(Side.CLIENT)
    public Vec3 func_72833_a(Entity p_72833_1_, float p_72833_2_)
    {
        float f1 = this.func_72826_c(p_72833_2_);
        float f2 = MathHelper.func_76134_b(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        int i = MathHelper.func_76128_c(p_72833_1_.field_70165_t);
        int j = MathHelper.func_76128_c(p_72833_1_.field_70163_u);
        int k = MathHelper.func_76128_c(p_72833_1_.field_70161_v);
        BiomeGenBase biomegenbase = this.func_72807_a(i, k);
        float f3 = biomegenbase.func_150564_a(i, j, k);
        int l = biomegenbase.func_76731_a(f3);
        float f4 = (float)(l >> 16 & 255) / 255.0F;
        float f5 = (float)(l >> 8 & 255) / 255.0F;
        float f6 = (float)(l & 255) / 255.0F;
        f4 *= f2;
        f5 *= f2;
        f6 *= f2;
        float f7 = this.func_72867_j(p_72833_2_);
        float f8;
        float f9;

        if (f7 > 0.0F)
        {
            f8 = (f4 * 0.3F + f5 * 0.59F + f6 * 0.11F) * 0.6F;
            f9 = 1.0F - f7 * 0.75F;
            f4 = f4 * f9 + f8 * (1.0F - f9);
            f5 = f5 * f9 + f8 * (1.0F - f9);
            f6 = f6 * f9 + f8 * (1.0F - f9);
        }

        f8 = this.func_72819_i(p_72833_2_);

        if (f8 > 0.0F)
        {
            f9 = (f4 * 0.3F + f5 * 0.59F + f6 * 0.11F) * 0.2F;
            float f10 = 1.0F - f8 * 0.75F;
            f4 = f4 * f10 + f9 * (1.0F - f10);
            f5 = f5 * f10 + f9 * (1.0F - f10);
            f6 = f6 * f10 + f9 * (1.0F - f10);
        }

        if (this.field_73016_r > 0)
        {
            f9 = (float)this.field_73016_r - p_72833_2_;

            if (f9 > 1.0F)
            {
                f9 = 1.0F;
            }

            f9 *= 0.45F;
            f4 = f4 * (1.0F - f9) + 0.8F * f9;
            f5 = f5 * (1.0F - f9) + 0.8F * f9;
            f6 = f6 * (1.0F - f9) + 1.0F * f9;
        }

        return Vec3.func_72443_a((double)f4, (double)f5, (double)f6);
    }

    public float func_72826_c(float p_72826_1_)
    {
        return this.field_73011_w.func_76563_a(this.field_72986_A.func_76073_f(), p_72826_1_);
    }

    @SideOnly(Side.CLIENT)
    public int func_72853_d()
    {
        return this.field_73011_w.func_76559_b(this.field_72986_A.func_76073_f());
    }

    public float func_130001_d()
    {
        return WorldProvider.field_111203_a[this.field_73011_w.func_76559_b(this.field_72986_A.func_76073_f())];
    }

    public float func_72929_e(float p_72929_1_)
    {
        float f1 = this.func_72826_c(p_72929_1_);
        return f1 * (float)Math.PI * 2.0F;
    }

    @SideOnly(Side.CLIENT)
    public Vec3 func_72824_f(float p_72824_1_)
    {
        float f1 = this.func_72826_c(p_72824_1_);
        float f2 = MathHelper.func_76134_b(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = (float)(this.field_73001_c >> 16 & 255L) / 255.0F;
        float f4 = (float)(this.field_73001_c >> 8 & 255L) / 255.0F;
        float f5 = (float)(this.field_73001_c & 255L) / 255.0F;
        float f6 = this.func_72867_j(p_72824_1_);
        float f7;
        float f8;

        if (f6 > 0.0F)
        {
            f7 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.6F;
            f8 = 1.0F - f6 * 0.95F;
            f3 = f3 * f8 + f7 * (1.0F - f8);
            f4 = f4 * f8 + f7 * (1.0F - f8);
            f5 = f5 * f8 + f7 * (1.0F - f8);
        }

        f3 *= f2 * 0.9F + 0.1F;
        f4 *= f2 * 0.9F + 0.1F;
        f5 *= f2 * 0.85F + 0.15F;
        f7 = this.func_72819_i(p_72824_1_);

        if (f7 > 0.0F)
        {
            f8 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.2F;
            float f9 = 1.0F - f7 * 0.95F;
            f3 = f3 * f9 + f8 * (1.0F - f9);
            f4 = f4 * f9 + f8 * (1.0F - f9);
            f5 = f5 * f9 + f8 * (1.0F - f9);
        }

        return Vec3.func_72443_a((double)f3, (double)f4, (double)f5);
    }

    @SideOnly(Side.CLIENT)
    public Vec3 func_72948_g(float p_72948_1_)
    {
        float f1 = this.func_72826_c(p_72948_1_);
        return this.field_73011_w.func_76562_b(f1, p_72948_1_);
    }

    public int func_72874_g(int p_72874_1_, int p_72874_2_)
    {
        return this.func_72938_d(p_72874_1_, p_72874_2_).func_76626_d(p_72874_1_ & 15, p_72874_2_ & 15);
    }

    public int func_72825_h(int p_72825_1_, int p_72825_2_)
    {
        Chunk chunk = this.func_72938_d(p_72825_1_, p_72825_2_);
        int k = chunk.func_76625_h() + 15;
        p_72825_1_ &= 15;

        for (p_72825_2_ &= 15; k > 0; --k)
        {
            Block block = chunk.func_150810_a(p_72825_1_, k, p_72825_2_);

            if (block.func_149688_o().func_76230_c() && block.func_149688_o() != Material.field_151584_j)
            {
                return k + 1;
            }
        }

        return -1;
    }

    @SideOnly(Side.CLIENT)
    public float func_72880_h(float p_72880_1_)
    {
        float f1 = this.func_72826_c(p_72880_1_);
        float f2 = 1.0F - (MathHelper.func_76134_b(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.25F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        return f2 * f2 * 0.5F;
    }

    public void func_147464_a(int p_147464_1_, int p_147464_2_, int p_147464_3_, Block p_147464_4_, int p_147464_5_) {}

    public void func_147454_a(int p_147454_1_, int p_147454_2_, int p_147454_3_, Block p_147454_4_, int p_147454_5_, int p_147454_6_) {}

    public void func_147446_b(int p_147446_1_, int p_147446_2_, int p_147446_3_, Block p_147446_4_, int p_147446_5_, int p_147446_6_) {}

    public void func_72939_s()
    {
        this.field_72984_F.func_76320_a("entities");
        this.field_72984_F.func_76320_a("global");
        int i;
        Entity entity;
        CrashReport crashreport;
        CrashReportCategory crashreportcategory;

        for (i = 0; i < this.field_73007_j.size(); ++i)
        {
            entity = (Entity)this.field_73007_j.get(i);

            try
            {
                ++entity.field_70173_aa;
                entity.func_70071_h_();
            }
            catch (Throwable throwable2)
            {
                crashreport = CrashReport.func_85055_a(throwable2, "Ticking entity");
                crashreportcategory = crashreport.func_85058_a("Entity being ticked");

                if (entity == null)
                {
                    crashreportcategory.func_71507_a("Entity", "~~NULL~~");
                }
                else
                {
                    entity.func_85029_a(crashreportcategory);
                }

                throw new ReportedException(crashreport);
            }

            if (entity.field_70128_L)
            {
                this.field_73007_j.remove(i--);
            }
        }

        this.field_72984_F.func_76318_c("remove");
        this.field_72996_f.removeAll(this.field_72997_g);
        int j;
        int l;

        for (i = 0; i < this.field_72997_g.size(); ++i)
        {
            entity = (Entity)this.field_72997_g.get(i);
            j = entity.field_70176_ah;
            l = entity.field_70164_aj;

            if (entity.field_70175_ag && this.func_72916_c(j, l))
            {
                this.func_72964_e(j, l).func_76622_b(entity);
            }
        }

        for (i = 0; i < this.field_72997_g.size(); ++i)
        {
            this.func_72847_b((Entity)this.field_72997_g.get(i));
        }

        this.field_72997_g.clear();
        this.field_72984_F.func_76318_c("regular");

        for (i = 0; i < this.field_72996_f.size(); ++i)
        {
            entity = (Entity)this.field_72996_f.get(i);

            if (entity.field_70154_o != null)
            {
                if (!entity.field_70154_o.field_70128_L && entity.field_70154_o.field_70153_n == entity)
                {
                    continue;
                }

                entity.field_70154_o.field_70153_n = null;
                entity.field_70154_o = null;
            }

            this.field_72984_F.func_76320_a("tick");

            if (!entity.field_70128_L)
            {
                try
                {
                    this.func_72870_g(entity);
                }
                catch (Throwable throwable1)
                {
                    crashreport = CrashReport.func_85055_a(throwable1, "Ticking entity");
                    crashreportcategory = crashreport.func_85058_a("Entity being ticked");
                    entity.func_85029_a(crashreportcategory);
                    throw new ReportedException(crashreport);
                }
            }

            this.field_72984_F.func_76319_b();
            this.field_72984_F.func_76320_a("remove");

            if (entity.field_70128_L)
            {
                j = entity.field_70176_ah;
                l = entity.field_70164_aj;

                if (entity.field_70175_ag && this.func_72916_c(j, l))
                {
                    this.func_72964_e(j, l).func_76622_b(entity);
                }

                this.field_72996_f.remove(i--);
                this.func_72847_b(entity);
            }

            this.field_72984_F.func_76319_b();
        }

        this.field_72984_F.func_76318_c("blockEntities");
        this.field_147481_N = true;
        Iterator iterator = this.field_147482_g.iterator();

        while (iterator.hasNext())
        {
            TileEntity tileentity = (TileEntity)iterator.next();

            if (!tileentity.func_145837_r() && tileentity.func_145830_o() && this.func_72899_e(tileentity.field_145851_c, tileentity.field_145848_d, tileentity.field_145849_e))
            {
                try
                {
                    tileentity.func_145845_h();
                }
                catch (Throwable throwable)
                {
                    crashreport = CrashReport.func_85055_a(throwable, "Ticking block entity");
                    crashreportcategory = crashreport.func_85058_a("Block entity being ticked");
                    tileentity.func_145828_a(crashreportcategory);
                    throw new ReportedException(crashreport);
                }
            }

            if (tileentity.func_145837_r())
            {
                iterator.remove();

                if (this.func_72916_c(tileentity.field_145851_c >> 4, tileentity.field_145849_e >> 4))
                {
                    Chunk chunk = this.func_72964_e(tileentity.field_145851_c >> 4, tileentity.field_145849_e >> 4);

                    if (chunk != null)
                    {
                        chunk.func_150805_f(tileentity.field_145851_c & 15, tileentity.field_145848_d, tileentity.field_145849_e & 15);
                    }
                }
            }
        }

        this.field_147481_N = false;

        if (!this.field_147483_b.isEmpty())
        {
            this.field_147482_g.removeAll(this.field_147483_b);
            this.field_147483_b.clear();
        }

        this.field_72984_F.func_76318_c("pendingBlockEntities");

        if (!this.field_147484_a.isEmpty())
        {
            for (int k = 0; k < this.field_147484_a.size(); ++k)
            {
                TileEntity tileentity1 = (TileEntity)this.field_147484_a.get(k);

                if (!tileentity1.func_145837_r())
                {
                    if (!this.field_147482_g.contains(tileentity1))
                    {
                        this.field_147482_g.add(tileentity1);
                    }

                    if (this.func_72916_c(tileentity1.field_145851_c >> 4, tileentity1.field_145849_e >> 4))
                    {
                        Chunk chunk1 = this.func_72964_e(tileentity1.field_145851_c >> 4, tileentity1.field_145849_e >> 4);

                        if (chunk1 != null)
                        {
                            chunk1.func_150812_a(tileentity1.field_145851_c & 15, tileentity1.field_145848_d, tileentity1.field_145849_e & 15, tileentity1);
                        }
                    }

                    this.func_147471_g(tileentity1.field_145851_c, tileentity1.field_145848_d, tileentity1.field_145849_e);
                }
            }

            this.field_147484_a.clear();
        }

        this.field_72984_F.func_76319_b();
        this.field_72984_F.func_76319_b();
    }

    public void func_147448_a(Collection p_147448_1_)
    {
        if (this.field_147481_N)
        {
            this.field_147484_a.addAll(p_147448_1_);
        }
        else
        {
            this.field_147482_g.addAll(p_147448_1_);
        }
    }

    public void func_72870_g(Entity p_72870_1_)
    {
        this.func_72866_a(p_72870_1_, true);
    }

    public void func_72866_a(Entity p_72866_1_, boolean p_72866_2_)
    {
        int i = MathHelper.func_76128_c(p_72866_1_.field_70165_t);
        int j = MathHelper.func_76128_c(p_72866_1_.field_70161_v);
        byte b0 = 32;

        if (!p_72866_2_ || this.func_72904_c(i - b0, 0, j - b0, i + b0, 0, j + b0))
        {
            p_72866_1_.field_70142_S = p_72866_1_.field_70165_t;
            p_72866_1_.field_70137_T = p_72866_1_.field_70163_u;
            p_72866_1_.field_70136_U = p_72866_1_.field_70161_v;
            p_72866_1_.field_70126_B = p_72866_1_.field_70177_z;
            p_72866_1_.field_70127_C = p_72866_1_.field_70125_A;

            if (p_72866_2_ && p_72866_1_.field_70175_ag)
            {
                ++p_72866_1_.field_70173_aa;

                if (p_72866_1_.field_70154_o != null)
                {
                    p_72866_1_.func_70098_U();
                }
                else
                {
                    p_72866_1_.func_70071_h_();
                }
            }

            this.field_72984_F.func_76320_a("chunkCheck");

            if (Double.isNaN(p_72866_1_.field_70165_t) || Double.isInfinite(p_72866_1_.field_70165_t))
            {
                p_72866_1_.field_70165_t = p_72866_1_.field_70142_S;
            }

            if (Double.isNaN(p_72866_1_.field_70163_u) || Double.isInfinite(p_72866_1_.field_70163_u))
            {
                p_72866_1_.field_70163_u = p_72866_1_.field_70137_T;
            }

            if (Double.isNaN(p_72866_1_.field_70161_v) || Double.isInfinite(p_72866_1_.field_70161_v))
            {
                p_72866_1_.field_70161_v = p_72866_1_.field_70136_U;
            }

            if (Double.isNaN((double)p_72866_1_.field_70125_A) || Double.isInfinite((double)p_72866_1_.field_70125_A))
            {
                p_72866_1_.field_70125_A = p_72866_1_.field_70127_C;
            }

            if (Double.isNaN((double)p_72866_1_.field_70177_z) || Double.isInfinite((double)p_72866_1_.field_70177_z))
            {
                p_72866_1_.field_70177_z = p_72866_1_.field_70126_B;
            }

            int k = MathHelper.func_76128_c(p_72866_1_.field_70165_t / 16.0D);
            int l = MathHelper.func_76128_c(p_72866_1_.field_70163_u / 16.0D);
            int i1 = MathHelper.func_76128_c(p_72866_1_.field_70161_v / 16.0D);

            if (!p_72866_1_.field_70175_ag || p_72866_1_.field_70176_ah != k || p_72866_1_.field_70162_ai != l || p_72866_1_.field_70164_aj != i1)
            {
                if (p_72866_1_.field_70175_ag && this.func_72916_c(p_72866_1_.field_70176_ah, p_72866_1_.field_70164_aj))
                {
                    this.func_72964_e(p_72866_1_.field_70176_ah, p_72866_1_.field_70164_aj).func_76608_a(p_72866_1_, p_72866_1_.field_70162_ai);
                }

                if (this.func_72916_c(k, i1))
                {
                    p_72866_1_.field_70175_ag = true;
                    this.func_72964_e(k, i1).func_76612_a(p_72866_1_);
                }
                else
                {
                    p_72866_1_.field_70175_ag = false;
                }
            }

            this.field_72984_F.func_76319_b();

            if (p_72866_2_ && p_72866_1_.field_70175_ag && p_72866_1_.field_70153_n != null)
            {
                if (!p_72866_1_.field_70153_n.field_70128_L && p_72866_1_.field_70153_n.field_70154_o == p_72866_1_)
                {
                    this.func_72870_g(p_72866_1_.field_70153_n);
                }
                else
                {
                    p_72866_1_.field_70153_n.field_70154_o = null;
                    p_72866_1_.field_70153_n = null;
                }
            }
        }
    }

    public boolean func_72855_b(AxisAlignedBB p_72855_1_)
    {
        return this.func_72917_a(p_72855_1_, (Entity)null);
    }

    public boolean func_72917_a(AxisAlignedBB p_72917_1_, Entity p_72917_2_)
    {
        List list = this.func_72839_b((Entity)null, p_72917_1_);

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = (Entity)list.get(i);

            if (!entity1.field_70128_L && entity1.field_70156_m && entity1 != p_72917_2_)
            {
                return false;
            }
        }

        return true;
    }

    public boolean func_72829_c(AxisAlignedBB p_72829_1_)
    {
        int i = MathHelper.func_76128_c(p_72829_1_.field_72340_a);
        int j = MathHelper.func_76128_c(p_72829_1_.field_72336_d + 1.0D);
        int k = MathHelper.func_76128_c(p_72829_1_.field_72338_b);
        int l = MathHelper.func_76128_c(p_72829_1_.field_72337_e + 1.0D);
        int i1 = MathHelper.func_76128_c(p_72829_1_.field_72339_c);
        int j1 = MathHelper.func_76128_c(p_72829_1_.field_72334_f + 1.0D);

        if (p_72829_1_.field_72340_a < 0.0D)
        {
            --i;
        }

        if (p_72829_1_.field_72338_b < 0.0D)
        {
            --k;
        }

        if (p_72829_1_.field_72339_c < 0.0D)
        {
            --i1;
        }

        for (int k1 = i; k1 < j; ++k1)
        {
            for (int l1 = k; l1 < l; ++l1)
            {
                for (int i2 = i1; i2 < j1; ++i2)
                {
                    Block block = this.func_147439_a(k1, l1, i2);

                    if (block.func_149688_o() != Material.field_151579_a)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean func_72953_d(AxisAlignedBB p_72953_1_)
    {
        int i = MathHelper.func_76128_c(p_72953_1_.field_72340_a);
        int j = MathHelper.func_76128_c(p_72953_1_.field_72336_d + 1.0D);
        int k = MathHelper.func_76128_c(p_72953_1_.field_72338_b);
        int l = MathHelper.func_76128_c(p_72953_1_.field_72337_e + 1.0D);
        int i1 = MathHelper.func_76128_c(p_72953_1_.field_72339_c);
        int j1 = MathHelper.func_76128_c(p_72953_1_.field_72334_f + 1.0D);

        if (p_72953_1_.field_72340_a < 0.0D)
        {
            --i;
        }

        if (p_72953_1_.field_72338_b < 0.0D)
        {
            --k;
        }

        if (p_72953_1_.field_72339_c < 0.0D)
        {
            --i1;
        }

        for (int k1 = i; k1 < j; ++k1)
        {
            for (int l1 = k; l1 < l; ++l1)
            {
                for (int i2 = i1; i2 < j1; ++i2)
                {
                    Block block = this.func_147439_a(k1, l1, i2);

                    if (block.func_149688_o().func_76224_d())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean func_147470_e(AxisAlignedBB p_147470_1_)
    {
        int i = MathHelper.func_76128_c(p_147470_1_.field_72340_a);
        int j = MathHelper.func_76128_c(p_147470_1_.field_72336_d + 1.0D);
        int k = MathHelper.func_76128_c(p_147470_1_.field_72338_b);
        int l = MathHelper.func_76128_c(p_147470_1_.field_72337_e + 1.0D);
        int i1 = MathHelper.func_76128_c(p_147470_1_.field_72339_c);
        int j1 = MathHelper.func_76128_c(p_147470_1_.field_72334_f + 1.0D);

        if (this.func_72904_c(i, k, i1, j, l, j1))
        {
            for (int k1 = i; k1 < j; ++k1)
            {
                for (int l1 = k; l1 < l; ++l1)
                {
                    for (int i2 = i1; i2 < j1; ++i2)
                    {
                        Block block = this.func_147439_a(k1, l1, i2);

                        if (block == Blocks.FIRE || block == Blocks.FLOWING_LAVA || block == Blocks.LAVA)
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean func_72918_a(AxisAlignedBB p_72918_1_, Material p_72918_2_, Entity p_72918_3_)
    {
        int i = MathHelper.func_76128_c(p_72918_1_.field_72340_a);
        int j = MathHelper.func_76128_c(p_72918_1_.field_72336_d + 1.0D);
        int k = MathHelper.func_76128_c(p_72918_1_.field_72338_b);
        int l = MathHelper.func_76128_c(p_72918_1_.field_72337_e + 1.0D);
        int i1 = MathHelper.func_76128_c(p_72918_1_.field_72339_c);
        int j1 = MathHelper.func_76128_c(p_72918_1_.field_72334_f + 1.0D);

        if (!this.func_72904_c(i, k, i1, j, l, j1))
        {
            return false;
        }
        else
        {
            boolean flag = false;
            Vec3 vec3 = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);

            for (int k1 = i; k1 < j; ++k1)
            {
                for (int l1 = k; l1 < l; ++l1)
                {
                    for (int i2 = i1; i2 < j1; ++i2)
                    {
                        Block block = this.func_147439_a(k1, l1, i2);

                        if (block.func_149688_o() == p_72918_2_)
                        {
                            double d0 = (double)((float)(l1 + 1) - BlockLiquid.func_149801_b(this.func_72805_g(k1, l1, i2)));

                            if ((double)l >= d0)
                            {
                                flag = true;
                                block.func_149640_a(this, k1, l1, i2, p_72918_3_, vec3);
                            }
                        }
                    }
                }
            }

            if (vec3.func_72433_c() > 0.0D && p_72918_3_.func_96092_aw())
            {
                vec3 = vec3.func_72432_b();
                double d1 = 0.014D;
                p_72918_3_.field_70159_w += vec3.field_72450_a * d1;
                p_72918_3_.field_70181_x += vec3.field_72448_b * d1;
                p_72918_3_.field_70179_y += vec3.field_72449_c * d1;
            }

            return flag;
        }
    }

    public boolean func_72875_a(AxisAlignedBB p_72875_1_, Material p_72875_2_)
    {
        int i = MathHelper.func_76128_c(p_72875_1_.field_72340_a);
        int j = MathHelper.func_76128_c(p_72875_1_.field_72336_d + 1.0D);
        int k = MathHelper.func_76128_c(p_72875_1_.field_72338_b);
        int l = MathHelper.func_76128_c(p_72875_1_.field_72337_e + 1.0D);
        int i1 = MathHelper.func_76128_c(p_72875_1_.field_72339_c);
        int j1 = MathHelper.func_76128_c(p_72875_1_.field_72334_f + 1.0D);

        for (int k1 = i; k1 < j; ++k1)
        {
            for (int l1 = k; l1 < l; ++l1)
            {
                for (int i2 = i1; i2 < j1; ++i2)
                {
                    if (this.func_147439_a(k1, l1, i2).func_149688_o() == p_72875_2_)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean func_72830_b(AxisAlignedBB p_72830_1_, Material p_72830_2_)
    {
        int i = MathHelper.func_76128_c(p_72830_1_.field_72340_a);
        int j = MathHelper.func_76128_c(p_72830_1_.field_72336_d + 1.0D);
        int k = MathHelper.func_76128_c(p_72830_1_.field_72338_b);
        int l = MathHelper.func_76128_c(p_72830_1_.field_72337_e + 1.0D);
        int i1 = MathHelper.func_76128_c(p_72830_1_.field_72339_c);
        int j1 = MathHelper.func_76128_c(p_72830_1_.field_72334_f + 1.0D);

        for (int k1 = i; k1 < j; ++k1)
        {
            for (int l1 = k; l1 < l; ++l1)
            {
                for (int i2 = i1; i2 < j1; ++i2)
                {
                    Block block = this.func_147439_a(k1, l1, i2);

                    if (block.func_149688_o() == p_72830_2_)
                    {
                        int j2 = this.func_72805_g(k1, l1, i2);
                        double d0 = (double)(l1 + 1);

                        if (j2 < 8)
                        {
                            d0 = (double)(l1 + 1) - (double)j2 / 8.0D;
                        }

                        if (d0 >= p_72830_1_.field_72338_b)
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public Explosion func_72876_a(Entity p_72876_1_, double p_72876_2_, double p_72876_4_, double p_72876_6_, float p_72876_8_, boolean p_72876_9_)
    {
        return this.func_72885_a(p_72876_1_, p_72876_2_, p_72876_4_, p_72876_6_, p_72876_8_, false, p_72876_9_);
    }

    public Explosion func_72885_a(Entity p_72885_1_, double p_72885_2_, double p_72885_4_, double p_72885_6_, float p_72885_8_, boolean p_72885_9_, boolean p_72885_10_)
    {
        Explosion explosion = new Explosion(this, p_72885_1_, p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_);
        explosion.field_77286_a = p_72885_9_;
        explosion.field_82755_b = p_72885_10_;
        explosion.func_77278_a();
        explosion.func_77279_a(true);
        return explosion;
    }

    public float func_72842_a(Vec3 p_72842_1_, AxisAlignedBB p_72842_2_)
    {
        double d0 = 1.0D / ((p_72842_2_.field_72336_d - p_72842_2_.field_72340_a) * 2.0D + 1.0D);
        double d1 = 1.0D / ((p_72842_2_.field_72337_e - p_72842_2_.field_72338_b) * 2.0D + 1.0D);
        double d2 = 1.0D / ((p_72842_2_.field_72334_f - p_72842_2_.field_72339_c) * 2.0D + 1.0D);

        if (d0 >= 0.0D && d1 >= 0.0D && d2 >= 0.0D)
        {
            int i = 0;
            int j = 0;

            for (float f = 0.0F; f <= 1.0F; f = (float)((double)f + d0))
            {
                for (float f1 = 0.0F; f1 <= 1.0F; f1 = (float)((double)f1 + d1))
                {
                    for (float f2 = 0.0F; f2 <= 1.0F; f2 = (float)((double)f2 + d2))
                    {
                        double d3 = p_72842_2_.field_72340_a + (p_72842_2_.field_72336_d - p_72842_2_.field_72340_a) * (double)f;
                        double d4 = p_72842_2_.field_72338_b + (p_72842_2_.field_72337_e - p_72842_2_.field_72338_b) * (double)f1;
                        double d5 = p_72842_2_.field_72339_c + (p_72842_2_.field_72334_f - p_72842_2_.field_72339_c) * (double)f2;

                        if (this.func_72933_a(Vec3.func_72443_a(d3, d4, d5), p_72842_1_) == null)
                        {
                            ++i;
                        }

                        ++j;
                    }
                }
            }

            return (float)i / (float)j;
        }
        else
        {
            return 0.0F;
        }
    }

    public boolean func_72886_a(EntityPlayer p_72886_1_, int p_72886_2_, int p_72886_3_, int p_72886_4_, int p_72886_5_)
    {
        if (p_72886_5_ == 0)
        {
            --p_72886_3_;
        }

        if (p_72886_5_ == 1)
        {
            ++p_72886_3_;
        }

        if (p_72886_5_ == 2)
        {
            --p_72886_4_;
        }

        if (p_72886_5_ == 3)
        {
            ++p_72886_4_;
        }

        if (p_72886_5_ == 4)
        {
            --p_72886_2_;
        }

        if (p_72886_5_ == 5)
        {
            ++p_72886_2_;
        }

        if (this.func_147439_a(p_72886_2_, p_72886_3_, p_72886_4_) == Blocks.FIRE)
        {
            this.func_72889_a(p_72886_1_, 1004, p_72886_2_, p_72886_3_, p_72886_4_, 0);
            this.func_147468_f(p_72886_2_, p_72886_3_, p_72886_4_);
            return true;
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public String func_72981_t()
    {
        return "All: " + this.field_72996_f.size();
    }

    @SideOnly(Side.CLIENT)
    public String func_72827_u()
    {
        return this.field_73020_y.func_73148_d();
    }

    public TileEntity func_147438_o(int p_147438_1_, int p_147438_2_, int p_147438_3_)
    {
        if (p_147438_2_ >= 0 && p_147438_2_ < 256)
        {
            TileEntity tileentity = null;
            int l;
            TileEntity tileentity1;

            if (this.field_147481_N)
            {
                for (l = 0; l < this.field_147484_a.size(); ++l)
                {
                    tileentity1 = (TileEntity)this.field_147484_a.get(l);

                    if (!tileentity1.func_145837_r() && tileentity1.field_145851_c == p_147438_1_ && tileentity1.field_145848_d == p_147438_2_ && tileentity1.field_145849_e == p_147438_3_)
                    {
                        tileentity = tileentity1;
                        break;
                    }
                }
            }

            if (tileentity == null)
            {
                Chunk chunk = this.func_72964_e(p_147438_1_ >> 4, p_147438_3_ >> 4);

                if (chunk != null)
                {
                    tileentity = chunk.func_150806_e(p_147438_1_ & 15, p_147438_2_, p_147438_3_ & 15);
                }
            }

            if (tileentity == null)
            {
                for (l = 0; l < this.field_147484_a.size(); ++l)
                {
                    tileentity1 = (TileEntity)this.field_147484_a.get(l);

                    if (!tileentity1.func_145837_r() && tileentity1.field_145851_c == p_147438_1_ && tileentity1.field_145848_d == p_147438_2_ && tileentity1.field_145849_e == p_147438_3_)
                    {
                        tileentity = tileentity1;
                        break;
                    }
                }
            }

            return tileentity;
        }
        else
        {
            return null;
        }
    }

    public void func_147455_a(int p_147455_1_, int p_147455_2_, int p_147455_3_, TileEntity p_147455_4_)
    {
        if (p_147455_4_ != null && !p_147455_4_.func_145837_r())
        {
            if (this.field_147481_N)
            {
                p_147455_4_.field_145851_c = p_147455_1_;
                p_147455_4_.field_145848_d = p_147455_2_;
                p_147455_4_.field_145849_e = p_147455_3_;
                Iterator iterator = this.field_147484_a.iterator();

                while (iterator.hasNext())
                {
                    TileEntity tileentity1 = (TileEntity)iterator.next();

                    if (tileentity1.field_145851_c == p_147455_1_ && tileentity1.field_145848_d == p_147455_2_ && tileentity1.field_145849_e == p_147455_3_)
                    {
                        tileentity1.func_145843_s();
                        iterator.remove();
                    }
                }

                this.field_147484_a.add(p_147455_4_);
            }
            else
            {
                this.field_147482_g.add(p_147455_4_);
                Chunk chunk = this.func_72964_e(p_147455_1_ >> 4, p_147455_3_ >> 4);

                if (chunk != null)
                {
                    chunk.func_150812_a(p_147455_1_ & 15, p_147455_2_, p_147455_3_ & 15, p_147455_4_);
                }
            }
        }
    }

    public void func_147475_p(int p_147475_1_, int p_147475_2_, int p_147475_3_)
    {
        TileEntity tileentity = this.func_147438_o(p_147475_1_, p_147475_2_, p_147475_3_);

        if (tileentity != null && this.field_147481_N)
        {
            tileentity.func_145843_s();
            this.field_147484_a.remove(tileentity);
        }
        else
        {
            if (tileentity != null)
            {
                this.field_147484_a.remove(tileentity);
                this.field_147482_g.remove(tileentity);
            }

            Chunk chunk = this.func_72964_e(p_147475_1_ >> 4, p_147475_3_ >> 4);

            if (chunk != null)
            {
                chunk.func_150805_f(p_147475_1_ & 15, p_147475_2_, p_147475_3_ & 15);
            }
        }
    }

    public void func_147457_a(TileEntity p_147457_1_)
    {
        this.field_147483_b.add(p_147457_1_);
    }

    public boolean func_147469_q(int p_147469_1_, int p_147469_2_, int p_147469_3_)
    {
        AxisAlignedBB axisalignedbb = this.func_147439_a(p_147469_1_, p_147469_2_, p_147469_3_).func_149668_a(this, p_147469_1_, p_147469_2_, p_147469_3_);
        return axisalignedbb != null && axisalignedbb.func_72320_b() >= 1.0D;
    }

    public static boolean func_147466_a(IBlockAccess p_147466_0_, int p_147466_1_, int p_147466_2_, int p_147466_3_)
    {
        Block block = p_147466_0_.func_147439_a(p_147466_1_, p_147466_2_, p_147466_3_);
        int l = p_147466_0_.func_72805_g(p_147466_1_, p_147466_2_, p_147466_3_);
        return block.func_149688_o().func_76218_k() && block.func_149686_d() ? true : (block instanceof BlockStairs ? (l & 4) == 4 : (block instanceof BlockSlab ? (l & 8) == 8 : (block instanceof BlockHopper ? true : (block instanceof BlockSnow ? (l & 7) == 7 : false))));
    }

    public boolean func_147445_c(int p_147445_1_, int p_147445_2_, int p_147445_3_, boolean p_147445_4_)
    {
        if (p_147445_1_ >= -30000000 && p_147445_3_ >= -30000000 && p_147445_1_ < 30000000 && p_147445_3_ < 30000000)
        {
            Chunk chunk = this.field_73020_y.func_73154_d(p_147445_1_ >> 4, p_147445_3_ >> 4);

            if (chunk != null && !chunk.func_76621_g())
            {
                Block block = this.func_147439_a(p_147445_1_, p_147445_2_, p_147445_3_);
                return block.func_149688_o().func_76218_k() && block.func_149686_d();
            }
            else
            {
                return p_147445_4_;
            }
        }
        else
        {
            return p_147445_4_;
        }
    }

    public void func_72966_v()
    {
        int i = this.func_72967_a(1.0F);

        if (i != this.field_73008_k)
        {
            this.field_73008_k = i;
        }
    }

    public void func_72891_a(boolean p_72891_1_, boolean p_72891_2_)
    {
        this.field_72985_G = p_72891_1_;
        this.field_72992_H = p_72891_2_;
    }

    public void func_72835_b()
    {
        this.func_72979_l();
    }

    private void func_72947_a()
    {
        if (this.field_72986_A.func_76059_o())
        {
            this.field_73004_o = 1.0F;

            if (this.field_72986_A.func_76061_m())
            {
                this.field_73017_q = 1.0F;
            }
        }
    }

    protected void func_72979_l()
    {
        if (!this.field_73011_w.field_76576_e)
        {
            if (!this.field_72995_K)
            {
                int i = this.field_72986_A.func_76071_n();

                if (i <= 0)
                {
                    if (this.field_72986_A.func_76061_m())
                    {
                        this.field_72986_A.func_76090_f(this.field_73012_v.nextInt(12000) + 3600);
                    }
                    else
                    {
                        this.field_72986_A.func_76090_f(this.field_73012_v.nextInt(168000) + 12000);
                    }
                }
                else
                {
                    --i;
                    this.field_72986_A.func_76090_f(i);

                    if (i <= 0)
                    {
                        this.field_72986_A.func_76069_a(!this.field_72986_A.func_76061_m());
                    }
                }

                this.field_73018_p = this.field_73017_q;

                if (this.field_72986_A.func_76061_m())
                {
                    this.field_73017_q = (float)((double)this.field_73017_q + 0.01D);
                }
                else
                {
                    this.field_73017_q = (float)((double)this.field_73017_q - 0.01D);
                }

                this.field_73017_q = MathHelper.func_76131_a(this.field_73017_q, 0.0F, 1.0F);
                int j = this.field_72986_A.func_76083_p();

                if (j <= 0)
                {
                    if (this.field_72986_A.func_76059_o())
                    {
                        this.field_72986_A.func_76080_g(this.field_73012_v.nextInt(12000) + 12000);
                    }
                    else
                    {
                        this.field_72986_A.func_76080_g(this.field_73012_v.nextInt(168000) + 12000);
                    }
                }
                else
                {
                    --j;
                    this.field_72986_A.func_76080_g(j);

                    if (j <= 0)
                    {
                        this.field_72986_A.func_76084_b(!this.field_72986_A.func_76059_o());
                    }
                }

                this.field_73003_n = this.field_73004_o;

                if (this.field_72986_A.func_76059_o())
                {
                    this.field_73004_o = (float)((double)this.field_73004_o + 0.01D);
                }
                else
                {
                    this.field_73004_o = (float)((double)this.field_73004_o - 0.01D);
                }

                this.field_73004_o = MathHelper.func_76131_a(this.field_73004_o, 0.0F, 1.0F);
            }
        }
    }

    protected void func_72903_x()
    {
        this.field_72993_I.clear();
        this.field_72984_F.func_76320_a("buildList");
        int i;
        EntityPlayer entityplayer;
        int j;
        int k;
        int l;

        for (i = 0; i < this.field_73010_i.size(); ++i)
        {
            entityplayer = (EntityPlayer)this.field_73010_i.get(i);
            j = MathHelper.func_76128_c(entityplayer.field_70165_t / 16.0D);
            k = MathHelper.func_76128_c(entityplayer.field_70161_v / 16.0D);
            l = this.func_152379_p();

            for (int i1 = -l; i1 <= l; ++i1)
            {
                for (int j1 = -l; j1 <= l; ++j1)
                {
                    this.field_72993_I.add(new ChunkCoordIntPair(i1 + j, j1 + k));
                }
            }
        }

        this.field_72984_F.func_76319_b();

        if (this.field_72990_M > 0)
        {
            --this.field_72990_M;
        }

        this.field_72984_F.func_76320_a("playerCheckLight");

        if (!this.field_73010_i.isEmpty())
        {
            i = this.field_73012_v.nextInt(this.field_73010_i.size());
            entityplayer = (EntityPlayer)this.field_73010_i.get(i);
            j = MathHelper.func_76128_c(entityplayer.field_70165_t) + this.field_73012_v.nextInt(11) - 5;
            k = MathHelper.func_76128_c(entityplayer.field_70163_u) + this.field_73012_v.nextInt(11) - 5;
            l = MathHelper.func_76128_c(entityplayer.field_70161_v) + this.field_73012_v.nextInt(11) - 5;
            this.func_147451_t(j, k, l);
        }

        this.field_72984_F.func_76319_b();
    }

    protected abstract int func_152379_p();

    protected void func_147467_a(int p_147467_1_, int p_147467_2_, Chunk p_147467_3_)
    {
        this.field_72984_F.func_76318_c("moodSound");

        if (this.field_72990_M == 0 && !this.field_72995_K)
        {
            this.field_73005_l = this.field_73005_l * 3 + 1013904223;
            int k = this.field_73005_l >> 2;
            int l = k & 15;
            int i1 = k >> 8 & 15;
            int j1 = k >> 16 & 255;
            Block block = p_147467_3_.func_150810_a(l, j1, i1);
            l += p_147467_1_;
            i1 += p_147467_2_;

            if (block.func_149688_o() == Material.field_151579_a && this.func_72883_k(l, j1, i1) <= this.field_73012_v.nextInt(8) && this.func_72972_b(EnumSkyBlock.Sky, l, j1, i1) <= 0)
            {
                EntityPlayer entityplayer = this.func_72977_a((double)l + 0.5D, (double)j1 + 0.5D, (double)i1 + 0.5D, 8.0D);

                if (entityplayer != null && entityplayer.func_70092_e((double)l + 0.5D, (double)j1 + 0.5D, (double)i1 + 0.5D) > 4.0D)
                {
                    this.func_72908_a((double)l + 0.5D, (double)j1 + 0.5D, (double)i1 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.field_73012_v.nextFloat() * 0.2F);
                    this.field_72990_M = this.field_73012_v.nextInt(12000) + 6000;
                }
            }
        }

        this.field_72984_F.func_76318_c("checkLight");
        p_147467_3_.func_76594_o();
    }

    protected void func_147456_g()
    {
        this.func_72903_x();
    }

    public boolean func_72884_u(int p_72884_1_, int p_72884_2_, int p_72884_3_)
    {
        return this.func_72834_c(p_72884_1_, p_72884_2_, p_72884_3_, false);
    }

    public boolean func_72850_v(int p_72850_1_, int p_72850_2_, int p_72850_3_)
    {
        return this.func_72834_c(p_72850_1_, p_72850_2_, p_72850_3_, true);
    }

    public boolean func_72834_c(int p_72834_1_, int p_72834_2_, int p_72834_3_, boolean p_72834_4_)
    {
        BiomeGenBase biomegenbase = this.func_72807_a(p_72834_1_, p_72834_3_);
        float f = biomegenbase.func_150564_a(p_72834_1_, p_72834_2_, p_72834_3_);

        if (f > 0.15F)
        {
            return false;
        }
        else
        {
            if (p_72834_2_ >= 0 && p_72834_2_ < 256 && this.func_72972_b(EnumSkyBlock.Block, p_72834_1_, p_72834_2_, p_72834_3_) < 10)
            {
                Block block = this.func_147439_a(p_72834_1_, p_72834_2_, p_72834_3_);

                if ((block == Blocks.WATER || block == Blocks.FLOWING_WATER) && this.func_72805_g(p_72834_1_, p_72834_2_, p_72834_3_) == 0)
                {
                    if (!p_72834_4_)
                    {
                        return true;
                    }

                    boolean flag1 = true;

                    if (flag1 && this.func_147439_a(p_72834_1_ - 1, p_72834_2_, p_72834_3_).func_149688_o() != Material.field_151586_h)
                    {
                        flag1 = false;
                    }

                    if (flag1 && this.func_147439_a(p_72834_1_ + 1, p_72834_2_, p_72834_3_).func_149688_o() != Material.field_151586_h)
                    {
                        flag1 = false;
                    }

                    if (flag1 && this.func_147439_a(p_72834_1_, p_72834_2_, p_72834_3_ - 1).func_149688_o() != Material.field_151586_h)
                    {
                        flag1 = false;
                    }

                    if (flag1 && this.func_147439_a(p_72834_1_, p_72834_2_, p_72834_3_ + 1).func_149688_o() != Material.field_151586_h)
                    {
                        flag1 = false;
                    }

                    if (!flag1)
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean func_147478_e(int p_147478_1_, int p_147478_2_, int p_147478_3_, boolean p_147478_4_)
    {
        BiomeGenBase biomegenbase = this.func_72807_a(p_147478_1_, p_147478_3_);
        float f = biomegenbase.func_150564_a(p_147478_1_, p_147478_2_, p_147478_3_);

        if (f > 0.15F)
        {
            return false;
        }
        else if (!p_147478_4_)
        {
            return true;
        }
        else
        {
            if (p_147478_2_ >= 0 && p_147478_2_ < 256 && this.func_72972_b(EnumSkyBlock.Block, p_147478_1_, p_147478_2_, p_147478_3_) < 10)
            {
                Block block = this.func_147439_a(p_147478_1_, p_147478_2_, p_147478_3_);

                if (block.func_149688_o() == Material.field_151579_a && Blocks.SNOW_LAYER.func_149742_c(this, p_147478_1_, p_147478_2_, p_147478_3_))
                {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean func_147451_t(int p_147451_1_, int p_147451_2_, int p_147451_3_)
    {
        boolean flag = false;

        if (!this.field_73011_w.field_76576_e)
        {
            flag |= this.func_147463_c(EnumSkyBlock.Sky, p_147451_1_, p_147451_2_, p_147451_3_);
        }

        flag |= this.func_147463_c(EnumSkyBlock.Block, p_147451_1_, p_147451_2_, p_147451_3_);
        return flag;
    }

    private int func_98179_a(int p_98179_1_, int p_98179_2_, int p_98179_3_, EnumSkyBlock p_98179_4_)
    {
        if (p_98179_4_ == EnumSkyBlock.Sky && this.func_72937_j(p_98179_1_, p_98179_2_, p_98179_3_))
        {
            return 15;
        }
        else
        {
            Block block = this.func_147439_a(p_98179_1_, p_98179_2_, p_98179_3_);
            int l = p_98179_4_ == EnumSkyBlock.Sky ? 0 : block.func_149750_m();
            int i1 = block.func_149717_k();

            if (i1 >= 15 && block.func_149750_m() > 0)
            {
                i1 = 1;
            }

            if (i1 < 1)
            {
                i1 = 1;
            }

            if (i1 >= 15)
            {
                return 0;
            }
            else if (l >= 14)
            {
                return l;
            }
            else
            {
                for (int j1 = 0; j1 < 6; ++j1)
                {
                    int k1 = p_98179_1_ + Facing.field_71586_b[j1];
                    int l1 = p_98179_2_ + Facing.field_71587_c[j1];
                    int i2 = p_98179_3_ + Facing.field_71585_d[j1];
                    int j2 = this.func_72972_b(p_98179_4_, k1, l1, i2) - i1;

                    if (j2 > l)
                    {
                        l = j2;
                    }

                    if (l >= 14)
                    {
                        return l;
                    }
                }

                return l;
            }
        }
    }

    public boolean func_147463_c(EnumSkyBlock p_147463_1_, int p_147463_2_, int p_147463_3_, int p_147463_4_)
    {
        if (!this.func_72873_a(p_147463_2_, p_147463_3_, p_147463_4_, 17))
        {
            return false;
        }
        else
        {
            int l = 0;
            int i1 = 0;
            this.field_72984_F.func_76320_a("getBrightness");
            int j1 = this.func_72972_b(p_147463_1_, p_147463_2_, p_147463_3_, p_147463_4_);
            int k1 = this.func_98179_a(p_147463_2_, p_147463_3_, p_147463_4_, p_147463_1_);
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            int i3;
            int j3;
            int k3;
            int l3;

            if (k1 > j1)
            {
                this.field_72994_J[i1++] = 133152;
            }
            else if (k1 < j1)
            {
                this.field_72994_J[i1++] = 133152 | j1 << 18;

                while (l < i1)
                {
                    l1 = this.field_72994_J[l++];
                    i2 = (l1 & 63) - 32 + p_147463_2_;
                    j2 = (l1 >> 6 & 63) - 32 + p_147463_3_;
                    k2 = (l1 >> 12 & 63) - 32 + p_147463_4_;
                    l2 = l1 >> 18 & 15;
                    i3 = this.func_72972_b(p_147463_1_, i2, j2, k2);

                    if (i3 == l2)
                    {
                        this.func_72915_b(p_147463_1_, i2, j2, k2, 0);

                        if (l2 > 0)
                        {
                            j3 = MathHelper.func_76130_a(i2 - p_147463_2_);
                            k3 = MathHelper.func_76130_a(j2 - p_147463_3_);
                            l3 = MathHelper.func_76130_a(k2 - p_147463_4_);

                            if (j3 + k3 + l3 < 17)
                            {
                                for (int i4 = 0; i4 < 6; ++i4)
                                {
                                    int j4 = i2 + Facing.field_71586_b[i4];
                                    int k4 = j2 + Facing.field_71587_c[i4];
                                    int l4 = k2 + Facing.field_71585_d[i4];
                                    int i5 = Math.max(1, this.func_147439_a(j4, k4, l4).func_149717_k());
                                    i3 = this.func_72972_b(p_147463_1_, j4, k4, l4);

                                    if (i3 == l2 - i5 && i1 < this.field_72994_J.length)
                                    {
                                        this.field_72994_J[i1++] = j4 - p_147463_2_ + 32 | k4 - p_147463_3_ + 32 << 6 | l4 - p_147463_4_ + 32 << 12 | l2 - i5 << 18;
                                    }
                                }
                            }
                        }
                    }
                }

                l = 0;
            }

            this.field_72984_F.func_76319_b();
            this.field_72984_F.func_76320_a("checkedPosition < toCheckCount");

            while (l < i1)
            {
                l1 = this.field_72994_J[l++];
                i2 = (l1 & 63) - 32 + p_147463_2_;
                j2 = (l1 >> 6 & 63) - 32 + p_147463_3_;
                k2 = (l1 >> 12 & 63) - 32 + p_147463_4_;
                l2 = this.func_72972_b(p_147463_1_, i2, j2, k2);
                i3 = this.func_98179_a(i2, j2, k2, p_147463_1_);

                if (i3 != l2)
                {
                    this.func_72915_b(p_147463_1_, i2, j2, k2, i3);

                    if (i3 > l2)
                    {
                        j3 = Math.abs(i2 - p_147463_2_);
                        k3 = Math.abs(j2 - p_147463_3_);
                        l3 = Math.abs(k2 - p_147463_4_);
                        boolean flag = i1 < this.field_72994_J.length - 6;

                        if (j3 + k3 + l3 < 17 && flag)
                        {
                            if (this.func_72972_b(p_147463_1_, i2 - 1, j2, k2) < i3)
                            {
                                this.field_72994_J[i1++] = i2 - 1 - p_147463_2_ + 32 + (j2 - p_147463_3_ + 32 << 6) + (k2 - p_147463_4_ + 32 << 12);
                            }

                            if (this.func_72972_b(p_147463_1_, i2 + 1, j2, k2) < i3)
                            {
                                this.field_72994_J[i1++] = i2 + 1 - p_147463_2_ + 32 + (j2 - p_147463_3_ + 32 << 6) + (k2 - p_147463_4_ + 32 << 12);
                            }

                            if (this.func_72972_b(p_147463_1_, i2, j2 - 1, k2) < i3)
                            {
                                this.field_72994_J[i1++] = i2 - p_147463_2_ + 32 + (j2 - 1 - p_147463_3_ + 32 << 6) + (k2 - p_147463_4_ + 32 << 12);
                            }

                            if (this.func_72972_b(p_147463_1_, i2, j2 + 1, k2) < i3)
                            {
                                this.field_72994_J[i1++] = i2 - p_147463_2_ + 32 + (j2 + 1 - p_147463_3_ + 32 << 6) + (k2 - p_147463_4_ + 32 << 12);
                            }

                            if (this.func_72972_b(p_147463_1_, i2, j2, k2 - 1) < i3)
                            {
                                this.field_72994_J[i1++] = i2 - p_147463_2_ + 32 + (j2 - p_147463_3_ + 32 << 6) + (k2 - 1 - p_147463_4_ + 32 << 12);
                            }

                            if (this.func_72972_b(p_147463_1_, i2, j2, k2 + 1) < i3)
                            {
                                this.field_72994_J[i1++] = i2 - p_147463_2_ + 32 + (j2 - p_147463_3_ + 32 << 6) + (k2 + 1 - p_147463_4_ + 32 << 12);
                            }
                        }
                    }
                }
            }

            this.field_72984_F.func_76319_b();
            return true;
        }
    }

    public boolean func_72955_a(boolean p_72955_1_)
    {
        return false;
    }

    public List func_72920_a(Chunk p_72920_1_, boolean p_72920_2_)
    {
        return null;
    }

    public List func_72839_b(Entity p_72839_1_, AxisAlignedBB p_72839_2_)
    {
        return this.func_94576_a(p_72839_1_, p_72839_2_, (IEntitySelector)null);
    }

    public List func_94576_a(Entity p_94576_1_, AxisAlignedBB p_94576_2_, IEntitySelector p_94576_3_)
    {
        ArrayList arraylist = new ArrayList();
        int i = MathHelper.func_76128_c((p_94576_2_.field_72340_a - 2.0D) / 16.0D);
        int j = MathHelper.func_76128_c((p_94576_2_.field_72336_d + 2.0D) / 16.0D);
        int k = MathHelper.func_76128_c((p_94576_2_.field_72339_c - 2.0D) / 16.0D);
        int l = MathHelper.func_76128_c((p_94576_2_.field_72334_f + 2.0D) / 16.0D);

        for (int i1 = i; i1 <= j; ++i1)
        {
            for (int j1 = k; j1 <= l; ++j1)
            {
                if (this.func_72916_c(i1, j1))
                {
                    this.func_72964_e(i1, j1).func_76588_a(p_94576_1_, p_94576_2_, arraylist, p_94576_3_);
                }
            }
        }

        return arraylist;
    }

    public List func_72872_a(Class p_72872_1_, AxisAlignedBB p_72872_2_)
    {
        return this.func_82733_a(p_72872_1_, p_72872_2_, (IEntitySelector)null);
    }

    public List func_82733_a(Class p_82733_1_, AxisAlignedBB p_82733_2_, IEntitySelector p_82733_3_)
    {
        int i = MathHelper.func_76128_c((p_82733_2_.field_72340_a - 2.0D) / 16.0D);
        int j = MathHelper.func_76128_c((p_82733_2_.field_72336_d + 2.0D) / 16.0D);
        int k = MathHelper.func_76128_c((p_82733_2_.field_72339_c - 2.0D) / 16.0D);
        int l = MathHelper.func_76128_c((p_82733_2_.field_72334_f + 2.0D) / 16.0D);
        ArrayList arraylist = new ArrayList();

        for (int i1 = i; i1 <= j; ++i1)
        {
            for (int j1 = k; j1 <= l; ++j1)
            {
                if (this.func_72916_c(i1, j1))
                {
                    this.func_72964_e(i1, j1).func_76618_a(p_82733_1_, p_82733_2_, arraylist, p_82733_3_);
                }
            }
        }

        return arraylist;
    }

    public Entity func_72857_a(Class p_72857_1_, AxisAlignedBB p_72857_2_, Entity p_72857_3_)
    {
        List list = this.func_72872_a(p_72857_1_, p_72857_2_);
        Entity entity1 = null;
        double d0 = Double.MAX_VALUE;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity2 = (Entity)list.get(i);

            if (entity2 != p_72857_3_)
            {
                double d1 = p_72857_3_.func_70068_e(entity2);

                if (d1 <= d0)
                {
                    entity1 = entity2;
                    d0 = d1;
                }
            }
        }

        return entity1;
    }

    public abstract Entity func_73045_a(int p_73045_1_);

    @SideOnly(Side.CLIENT)
    public List func_72910_y()
    {
        return this.field_72996_f;
    }

    public void func_147476_b(int p_147476_1_, int p_147476_2_, int p_147476_3_, TileEntity p_147476_4_)
    {
        if (this.func_72899_e(p_147476_1_, p_147476_2_, p_147476_3_))
        {
            this.func_72938_d(p_147476_1_, p_147476_3_).func_76630_e();
        }
    }

    public int func_72907_a(Class p_72907_1_)
    {
        int i = 0;

        for (int j = 0; j < this.field_72996_f.size(); ++j)
        {
            Entity entity = (Entity)this.field_72996_f.get(j);

            if ((!(entity instanceof EntityLiving) || !((EntityLiving)entity).func_104002_bU()) && p_72907_1_.isAssignableFrom(entity.getClass()))
            {
                ++i;
            }
        }

        return i;
    }

    public void func_72868_a(List p_72868_1_)
    {
        this.field_72996_f.addAll(p_72868_1_);

        for (int i = 0; i < p_72868_1_.size(); ++i)
        {
            this.func_72923_a((Entity)p_72868_1_.get(i));
        }
    }

    public void func_72828_b(List p_72828_1_)
    {
        this.field_72997_g.addAll(p_72828_1_);
    }

    public boolean func_147472_a(Block p_147472_1_, int p_147472_2_, int p_147472_3_, int p_147472_4_, boolean p_147472_5_, int p_147472_6_, Entity p_147472_7_, ItemStack p_147472_8_)
    {
        Block block1 = this.func_147439_a(p_147472_2_, p_147472_3_, p_147472_4_);
        AxisAlignedBB axisalignedbb = p_147472_5_ ? null : p_147472_1_.func_149668_a(this, p_147472_2_, p_147472_3_, p_147472_4_);
        return axisalignedbb != null && !this.func_72917_a(axisalignedbb, p_147472_7_) ? false : (block1.func_149688_o() == Material.field_151594_q && p_147472_1_ == Blocks.ANVIL ? true : block1.func_149688_o().func_76222_j() && p_147472_1_.func_149705_a(this, p_147472_2_, p_147472_3_, p_147472_4_, p_147472_6_, p_147472_8_));
    }

    public PathEntity func_72865_a(Entity p_72865_1_, Entity p_72865_2_, float p_72865_3_, boolean p_72865_4_, boolean p_72865_5_, boolean p_72865_6_, boolean p_72865_7_)
    {
        this.field_72984_F.func_76320_a("pathfind");
        int i = MathHelper.func_76128_c(p_72865_1_.field_70165_t);
        int j = MathHelper.func_76128_c(p_72865_1_.field_70163_u + 1.0D);
        int k = MathHelper.func_76128_c(p_72865_1_.field_70161_v);
        int l = (int)(p_72865_3_ + 16.0F);
        int i1 = i - l;
        int j1 = j - l;
        int k1 = k - l;
        int l1 = i + l;
        int i2 = j + l;
        int j2 = k + l;
        ChunkCache chunkcache = new ChunkCache(this, i1, j1, k1, l1, i2, j2, 0);
        PathEntity pathentity = (new PathFinder(chunkcache, p_72865_4_, p_72865_5_, p_72865_6_, p_72865_7_)).func_75856_a(p_72865_1_, p_72865_2_, p_72865_3_);
        this.field_72984_F.func_76319_b();
        return pathentity;
    }

    public PathEntity func_72844_a(Entity p_72844_1_, int p_72844_2_, int p_72844_3_, int p_72844_4_, float p_72844_5_, boolean p_72844_6_, boolean p_72844_7_, boolean p_72844_8_, boolean p_72844_9_)
    {
        this.field_72984_F.func_76320_a("pathfind");
        int l = MathHelper.func_76128_c(p_72844_1_.field_70165_t);
        int i1 = MathHelper.func_76128_c(p_72844_1_.field_70163_u);
        int j1 = MathHelper.func_76128_c(p_72844_1_.field_70161_v);
        int k1 = (int)(p_72844_5_ + 8.0F);
        int l1 = l - k1;
        int i2 = i1 - k1;
        int j2 = j1 - k1;
        int k2 = l + k1;
        int l2 = i1 + k1;
        int i3 = j1 + k1;
        ChunkCache chunkcache = new ChunkCache(this, l1, i2, j2, k2, l2, i3, 0);
        PathEntity pathentity = (new PathFinder(chunkcache, p_72844_6_, p_72844_7_, p_72844_8_, p_72844_9_)).func_75859_a(p_72844_1_, p_72844_2_, p_72844_3_, p_72844_4_, p_72844_5_);
        this.field_72984_F.func_76319_b();
        return pathentity;
    }

    public int func_72879_k(int p_72879_1_, int p_72879_2_, int p_72879_3_, int p_72879_4_)
    {
        return this.func_147439_a(p_72879_1_, p_72879_2_, p_72879_3_).func_149748_c(this, p_72879_1_, p_72879_2_, p_72879_3_, p_72879_4_);
    }

    public int func_94577_B(int p_94577_1_, int p_94577_2_, int p_94577_3_)
    {
        byte b0 = 0;
        int l = Math.max(b0, this.func_72879_k(p_94577_1_, p_94577_2_ - 1, p_94577_3_, 0));

        if (l >= 15)
        {
            return l;
        }
        else
        {
            l = Math.max(l, this.func_72879_k(p_94577_1_, p_94577_2_ + 1, p_94577_3_, 1));

            if (l >= 15)
            {
                return l;
            }
            else
            {
                l = Math.max(l, this.func_72879_k(p_94577_1_, p_94577_2_, p_94577_3_ - 1, 2));

                if (l >= 15)
                {
                    return l;
                }
                else
                {
                    l = Math.max(l, this.func_72879_k(p_94577_1_, p_94577_2_, p_94577_3_ + 1, 3));

                    if (l >= 15)
                    {
                        return l;
                    }
                    else
                    {
                        l = Math.max(l, this.func_72879_k(p_94577_1_ - 1, p_94577_2_, p_94577_3_, 4));

                        if (l >= 15)
                        {
                            return l;
                        }
                        else
                        {
                            l = Math.max(l, this.func_72879_k(p_94577_1_ + 1, p_94577_2_, p_94577_3_, 5));
                            return l >= 15 ? l : l;
                        }
                    }
                }
            }
        }
    }

    public boolean func_94574_k(int p_94574_1_, int p_94574_2_, int p_94574_3_, int p_94574_4_)
    {
        return this.func_72878_l(p_94574_1_, p_94574_2_, p_94574_3_, p_94574_4_) > 0;
    }

    public int func_72878_l(int p_72878_1_, int p_72878_2_, int p_72878_3_, int p_72878_4_)
    {
        return this.func_147439_a(p_72878_1_, p_72878_2_, p_72878_3_).func_149721_r() ? this.func_94577_B(p_72878_1_, p_72878_2_, p_72878_3_) : this.func_147439_a(p_72878_1_, p_72878_2_, p_72878_3_).func_149709_b(this, p_72878_1_, p_72878_2_, p_72878_3_, p_72878_4_);
    }

    public boolean func_72864_z(int p_72864_1_, int p_72864_2_, int p_72864_3_)
    {
        return this.func_72878_l(p_72864_1_, p_72864_2_ - 1, p_72864_3_, 0) > 0 ? true : (this.func_72878_l(p_72864_1_, p_72864_2_ + 1, p_72864_3_, 1) > 0 ? true : (this.func_72878_l(p_72864_1_, p_72864_2_, p_72864_3_ - 1, 2) > 0 ? true : (this.func_72878_l(p_72864_1_, p_72864_2_, p_72864_3_ + 1, 3) > 0 ? true : (this.func_72878_l(p_72864_1_ - 1, p_72864_2_, p_72864_3_, 4) > 0 ? true : this.func_72878_l(p_72864_1_ + 1, p_72864_2_, p_72864_3_, 5) > 0))));
    }

    public int func_94572_D(int p_94572_1_, int p_94572_2_, int p_94572_3_)
    {
        int l = 0;

        for (int i1 = 0; i1 < 6; ++i1)
        {
            int j1 = this.func_72878_l(p_94572_1_ + Facing.field_71586_b[i1], p_94572_2_ + Facing.field_71587_c[i1], p_94572_3_ + Facing.field_71585_d[i1], i1);

            if (j1 >= 15)
            {
                return 15;
            }

            if (j1 > l)
            {
                l = j1;
            }
        }

        return l;
    }

    public EntityPlayer func_72890_a(Entity p_72890_1_, double p_72890_2_)
    {
        return this.func_72977_a(p_72890_1_.field_70165_t, p_72890_1_.field_70163_u, p_72890_1_.field_70161_v, p_72890_2_);
    }

    public EntityPlayer func_72977_a(double p_72977_1_, double p_72977_3_, double p_72977_5_, double p_72977_7_)
    {
        double d4 = -1.0D;
        EntityPlayer entityplayer = null;

        for (int i = 0; i < this.field_73010_i.size(); ++i)
        {
            EntityPlayer entityplayer1 = (EntityPlayer)this.field_73010_i.get(i);
            double d5 = entityplayer1.func_70092_e(p_72977_1_, p_72977_3_, p_72977_5_);

            if ((p_72977_7_ < 0.0D || d5 < p_72977_7_ * p_72977_7_) && (d4 == -1.0D || d5 < d4))
            {
                d4 = d5;
                entityplayer = entityplayer1;
            }
        }

        return entityplayer;
    }

    public EntityPlayer func_72856_b(Entity p_72856_1_, double p_72856_2_)
    {
        return this.func_72846_b(p_72856_1_.field_70165_t, p_72856_1_.field_70163_u, p_72856_1_.field_70161_v, p_72856_2_);
    }

    public EntityPlayer func_72846_b(double p_72846_1_, double p_72846_3_, double p_72846_5_, double p_72846_7_)
    {
        double d4 = -1.0D;
        EntityPlayer entityplayer = null;

        for (int i = 0; i < this.field_73010_i.size(); ++i)
        {
            EntityPlayer entityplayer1 = (EntityPlayer)this.field_73010_i.get(i);

            if (!entityplayer1.field_71075_bZ.field_75102_a && entityplayer1.func_70089_S())
            {
                double d5 = entityplayer1.func_70092_e(p_72846_1_, p_72846_3_, p_72846_5_);
                double d6 = p_72846_7_;

                if (entityplayer1.func_70093_af())
                {
                    d6 = p_72846_7_ * 0.800000011920929D;
                }

                if (entityplayer1.func_82150_aj())
                {
                    float f = entityplayer1.func_82243_bO();

                    if (f < 0.1F)
                    {
                        f = 0.1F;
                    }

                    d6 *= (double)(0.7F * f);
                }

                if ((p_72846_7_ < 0.0D || d5 < d6 * d6) && (d4 == -1.0D || d5 < d4))
                {
                    d4 = d5;
                    entityplayer = entityplayer1;
                }
            }
        }

        return entityplayer;
    }

    public EntityPlayer func_72924_a(String p_72924_1_)
    {
        for (int i = 0; i < this.field_73010_i.size(); ++i)
        {
            EntityPlayer entityplayer = (EntityPlayer)this.field_73010_i.get(i);

            if (p_72924_1_.equals(entityplayer.func_70005_c_()))
            {
                return entityplayer;
            }
        }

        return null;
    }

    public EntityPlayer func_152378_a(UUID p_152378_1_)
    {
        for (int i = 0; i < this.field_73010_i.size(); ++i)
        {
            EntityPlayer entityplayer = (EntityPlayer)this.field_73010_i.get(i);

            if (p_152378_1_.equals(entityplayer.func_110124_au()))
            {
                return entityplayer;
            }
        }

        return null;
    }

    @SideOnly(Side.CLIENT)
    public void func_72882_A() {}

    public void func_72906_B() throws MinecraftException
    {
        this.field_73019_z.func_75762_c();
    }

    @SideOnly(Side.CLIENT)
    public void func_82738_a(long p_82738_1_)
    {
        this.field_72986_A.func_82572_b(p_82738_1_);
    }

    public long func_72905_C()
    {
        return this.field_72986_A.func_76063_b();
    }

    public long func_82737_E()
    {
        return this.field_72986_A.func_82573_f();
    }

    public long func_72820_D()
    {
        return this.field_72986_A.func_76073_f();
    }

    public void func_72877_b(long p_72877_1_)
    {
        this.field_72986_A.func_76068_b(p_72877_1_);
    }

    public ChunkCoordinates func_72861_E()
    {
        return new ChunkCoordinates(this.field_72986_A.func_76079_c(), this.field_72986_A.func_76075_d(), this.field_72986_A.func_76074_e());
    }

    public void func_72950_A(int p_72950_1_, int p_72950_2_, int p_72950_3_)
    {
        this.field_72986_A.func_76081_a(p_72950_1_, p_72950_2_, p_72950_3_);
    }

    @SideOnly(Side.CLIENT)
    public void func_72897_h(Entity p_72897_1_)
    {
        int i = MathHelper.func_76128_c(p_72897_1_.field_70165_t / 16.0D);
        int j = MathHelper.func_76128_c(p_72897_1_.field_70161_v / 16.0D);
        byte b0 = 2;

        for (int k = i - b0; k <= i + b0; ++k)
        {
            for (int l = j - b0; l <= j + b0; ++l)
            {
                this.func_72964_e(k, l);
            }
        }

        if (!this.field_72996_f.contains(p_72897_1_))
        {
            this.field_72996_f.add(p_72897_1_);
        }
    }

    public boolean func_72962_a(EntityPlayer p_72962_1_, int p_72962_2_, int p_72962_3_, int p_72962_4_)
    {
        return true;
    }

    public void func_72960_a(Entity p_72960_1_, byte p_72960_2_) {}

    public IChunkProvider func_72863_F()
    {
        return this.field_73020_y;
    }

    public void func_147452_c(int p_147452_1_, int p_147452_2_, int p_147452_3_, Block p_147452_4_, int p_147452_5_, int p_147452_6_)
    {
        p_147452_4_.func_149696_a(this, p_147452_1_, p_147452_2_, p_147452_3_, p_147452_5_, p_147452_6_);
    }

    public ISaveHandler func_72860_G()
    {
        return this.field_73019_z;
    }

    public WorldInfo func_72912_H()
    {
        return this.field_72986_A;
    }

    public GameRules func_82736_K()
    {
        return this.field_72986_A.func_82574_x();
    }

    public void func_72854_c() {}

    public float func_72819_i(float p_72819_1_)
    {
        return (this.field_73018_p + (this.field_73017_q - this.field_73018_p) * p_72819_1_) * this.func_72867_j(p_72819_1_);
    }

    @SideOnly(Side.CLIENT)
    public void func_147442_i(float p_147442_1_)
    {
        this.field_73018_p = p_147442_1_;
        this.field_73017_q = p_147442_1_;
    }

    public float func_72867_j(float p_72867_1_)
    {
        return this.field_73003_n + (this.field_73004_o - this.field_73003_n) * p_72867_1_;
    }

    @SideOnly(Side.CLIENT)
    public void func_72894_k(float p_72894_1_)
    {
        this.field_73003_n = p_72894_1_;
        this.field_73004_o = p_72894_1_;
    }

    public boolean func_72911_I()
    {
        return (double)this.func_72819_i(1.0F) > 0.9D;
    }

    public boolean func_72896_J()
    {
        return (double)this.func_72867_j(1.0F) > 0.2D;
    }

    public boolean func_72951_B(int p_72951_1_, int p_72951_2_, int p_72951_3_)
    {
        if (!this.func_72896_J())
        {
            return false;
        }
        else if (!this.func_72937_j(p_72951_1_, p_72951_2_, p_72951_3_))
        {
            return false;
        }
        else if (this.func_72874_g(p_72951_1_, p_72951_3_) > p_72951_2_)
        {
            return false;
        }
        else
        {
            BiomeGenBase biomegenbase = this.func_72807_a(p_72951_1_, p_72951_3_);
            return biomegenbase.func_76746_c() ? false : (this.func_147478_e(p_72951_1_, p_72951_2_, p_72951_3_, false) ? false : biomegenbase.func_76738_d());
        }
    }

    public boolean func_72958_C(int p_72958_1_, int p_72958_2_, int p_72958_3_)
    {
        BiomeGenBase biomegenbase = this.func_72807_a(p_72958_1_, p_72958_3_);
        return biomegenbase.func_76736_e();
    }

    public void func_72823_a(String p_72823_1_, WorldSavedData p_72823_2_)
    {
        this.field_72988_C.func_75745_a(p_72823_1_, p_72823_2_);
    }

    public WorldSavedData func_72943_a(Class p_72943_1_, String p_72943_2_)
    {
        return this.field_72988_C.func_75742_a(p_72943_1_, p_72943_2_);
    }

    public int func_72841_b(String p_72841_1_)
    {
        return this.field_72988_C.func_75743_a(p_72841_1_);
    }

    public void func_82739_e(int p_82739_1_, int p_82739_2_, int p_82739_3_, int p_82739_4_, int p_82739_5_)
    {
        for (int j1 = 0; j1 < this.field_73021_x.size(); ++j1)
        {
            ((IWorldAccess)this.field_73021_x.get(j1)).func_82746_a(p_82739_1_, p_82739_2_, p_82739_3_, p_82739_4_, p_82739_5_);
        }
    }

    public void func_72926_e(int p_72926_1_, int p_72926_2_, int p_72926_3_, int p_72926_4_, int p_72926_5_)
    {
        this.func_72889_a((EntityPlayer)null, p_72926_1_, p_72926_2_, p_72926_3_, p_72926_4_, p_72926_5_);
    }

    public void func_72889_a(EntityPlayer p_72889_1_, int p_72889_2_, int p_72889_3_, int p_72889_4_, int p_72889_5_, int p_72889_6_)
    {
        try
        {
            for (int j1 = 0; j1 < this.field_73021_x.size(); ++j1)
            {
                ((IWorldAccess)this.field_73021_x.get(j1)).func_72706_a(p_72889_1_, p_72889_2_, p_72889_3_, p_72889_4_, p_72889_5_, p_72889_6_);
            }
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Playing level event");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Level event being played");
            crashreportcategory.func_71507_a("Block coordinates", CrashReportCategory.func_85071_a(p_72889_3_, p_72889_4_, p_72889_5_));
            crashreportcategory.func_71507_a("Event source", p_72889_1_);
            crashreportcategory.func_71507_a("Event type", Integer.valueOf(p_72889_2_));
            crashreportcategory.func_71507_a("Event data", Integer.valueOf(p_72889_6_));
            throw new ReportedException(crashreport);
        }
    }

    public int func_72800_K()
    {
        return 256;
    }

    public int func_72940_L()
    {
        return this.field_73011_w.field_76576_e ? 128 : 256;
    }

    public Random func_72843_D(int p_72843_1_, int p_72843_2_, int p_72843_3_)
    {
        long l = (long)p_72843_1_ * 341873128712L + (long)p_72843_2_ * 132897987541L + this.func_72912_H().func_76063_b() + (long)p_72843_3_;
        this.field_73012_v.setSeed(l);
        return this.field_73012_v;
    }

    public ChunkPosition func_147440_b(String p_147440_1_, int p_147440_2_, int p_147440_3_, int p_147440_4_)
    {
        return this.func_72863_F().func_147416_a(this, p_147440_1_, p_147440_2_, p_147440_3_, p_147440_4_);
    }

    @SideOnly(Side.CLIENT)
    public boolean func_72806_N()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public double func_72919_O()
    {
        return this.field_72986_A.func_76067_t() == WorldType.field_77138_c ? 0.0D : 63.0D;
    }

    public CrashReportCategory func_72914_a(CrashReport p_72914_1_)
    {
        CrashReportCategory crashreportcategory = p_72914_1_.func_85057_a("Affected level", 1);
        crashreportcategory.func_71507_a("Level name", this.field_72986_A == null ? "????" : this.field_72986_A.func_76065_j());
        crashreportcategory.func_71500_a("All players", new Callable()
        {
            private static final String __OBFID = "CL_00000143";
            public String call()
            {
                return World.this.field_73010_i.size() + " total; " + World.this.field_73010_i.toString();
            }
        });
        crashreportcategory.func_71500_a("Chunk stats", new Callable()
        {
            private static final String __OBFID = "CL_00000144";
            public String call()
            {
                return World.this.field_73020_y.func_73148_d();
            }
        });

        try
        {
            this.field_72986_A.func_85118_a(crashreportcategory);
        }
        catch (Throwable throwable)
        {
            crashreportcategory.func_71499_a("Level Data Unobtainable", throwable);
        }

        return crashreportcategory;
    }

    public void func_147443_d(int p_147443_1_, int p_147443_2_, int p_147443_3_, int p_147443_4_, int p_147443_5_)
    {
        for (int j1 = 0; j1 < this.field_73021_x.size(); ++j1)
        {
            IWorldAccess iworldaccess = (IWorldAccess)this.field_73021_x.get(j1);
            iworldaccess.func_147587_b(p_147443_1_, p_147443_2_, p_147443_3_, p_147443_4_, p_147443_5_);
        }
    }

    public Calendar func_83015_S()
    {
        if (this.func_82737_E() % 600L == 0L)
        {
            this.field_83016_L.setTimeInMillis(MinecraftServer.func_130071_aq());
        }

        return this.field_83016_L;
    }

    @SideOnly(Side.CLIENT)
    public void func_92088_a(double p_92088_1_, double p_92088_3_, double p_92088_5_, double p_92088_7_, double p_92088_9_, double p_92088_11_, NBTTagCompound p_92088_13_) {}

    public Scoreboard func_96441_U()
    {
        return this.field_96442_D;
    }

    public void func_147453_f(int p_147453_1_, int p_147453_2_, int p_147453_3_, Block p_147453_4_)
    {
        for (int l = 0; l < 4; ++l)
        {
            int i1 = p_147453_1_ + Direction.field_71583_a[l];
            int j1 = p_147453_3_ + Direction.field_71581_b[l];
            Block block1 = this.func_147439_a(i1, p_147453_2_, j1);

            if (Blocks.UNPOWERED_COMPARATOR.func_149907_e(block1))
            {
                block1.func_149695_a(this, i1, p_147453_2_, j1, p_147453_4_);
            }
            else if (block1.func_149721_r())
            {
                i1 += Direction.field_71583_a[l];
                j1 += Direction.field_71581_b[l];
                Block block2 = this.func_147439_a(i1, p_147453_2_, j1);

                if (Blocks.UNPOWERED_COMPARATOR.func_149907_e(block2))
                {
                    block2.func_149695_a(this, i1, p_147453_2_, j1, p_147453_4_);
                }
            }
        }
    }

    public float func_147462_b(double p_147462_1_, double p_147462_3_, double p_147462_5_)
    {
        return this.func_147473_B(MathHelper.func_76128_c(p_147462_1_), MathHelper.func_76128_c(p_147462_3_), MathHelper.func_76128_c(p_147462_5_));
    }

    public float func_147473_B(int p_147473_1_, int p_147473_2_, int p_147473_3_)
    {
        float f = 0.0F;
        boolean flag = this.field_73013_u == EnumDifficulty.HARD;

        if (this.func_72899_e(p_147473_1_, p_147473_2_, p_147473_3_))
        {
            float f1 = this.func_130001_d();
            f += MathHelper.func_76131_a((float)this.func_72938_d(p_147473_1_, p_147473_3_).field_111204_q / 3600000.0F, 0.0F, 1.0F) * (flag ? 1.0F : 0.75F);
            f += f1 * 0.25F;
        }

        if (this.field_73013_u == EnumDifficulty.EASY || this.field_73013_u == EnumDifficulty.PEACEFUL)
        {
            f *= (float)this.field_73013_u.func_151525_a() / 2.0F;
        }

        return MathHelper.func_76131_a(f, 0.0F, flag ? 1.5F : 1.0F);
    }

    public void func_147450_X()
    {
        Iterator iterator = this.field_73021_x.iterator();

        while (iterator.hasNext())
        {
            IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
            iworldaccess.func_147584_b();
        }
    }
}