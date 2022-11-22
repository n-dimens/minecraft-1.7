package net.minecraft.world;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEventData;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.INpc;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldServer extends World
{
    private static final Logger field_147491_a = LogManager.getLogger();
    private final MinecraftServer field_73061_a;
    private final EntityTracker field_73062_L;
    private final PlayerManager field_73063_M;
    private Set field_73064_N;
    private TreeSet field_73065_O;
    public ChunkProviderServer field_73059_b;
    public boolean field_73058_d;
    private boolean field_73068_P;
    private int field_80004_Q;
    private final Teleporter field_85177_Q;
    private final SpawnerAnimals field_135059_Q = new SpawnerAnimals();
    private WorldServer.ServerBlockEventList[] field_147490_S = new WorldServer.ServerBlockEventList[] {new WorldServer.ServerBlockEventList(null), new WorldServer.ServerBlockEventList(null)};
    private int field_147489_T;
    public static final WeightedRandomChestContent[] field_73069_S = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.STICK, 0, 1, 3, 10), new WeightedRandomChestContent(Item.func_150898_a(Blocks.PLANKS), 0, 1, 3, 10), new WeightedRandomChestContent(Item.func_150898_a(Blocks.LOG), 0, 1, 3, 10), new WeightedRandomChestContent(Items.STONE_AXE, 0, 1, 1, 3), new WeightedRandomChestContent(Items.WOODEN_AXE, 0, 1, 1, 5), new WeightedRandomChestContent(Items.STONE_PICKAXE, 0, 1, 1, 3), new WeightedRandomChestContent(Items.WOODEN_PICKAXE, 0, 1, 1, 5), new WeightedRandomChestContent(Items.APPLE, 0, 2, 3, 5), new WeightedRandomChestContent(Items.BREAD, 0, 2, 3, 3), new WeightedRandomChestContent(Item.func_150898_a(Blocks.LOG_2), 0, 1, 3, 10)};
    private List field_94579_S = new ArrayList();
    private IntHashMap field_73066_T;
    private static final String __OBFID = "CL_00001437";

    public WorldServer(MinecraftServer p_i45284_1_, ISaveHandler p_i45284_2_, String p_i45284_3_, int p_i45284_4_, WorldSettings p_i45284_5_, Profiler p_i45284_6_)
    {
        super(p_i45284_2_, p_i45284_3_, p_i45284_5_, WorldProvider.func_76570_a(p_i45284_4_), p_i45284_6_);
        this.field_73061_a = p_i45284_1_;
        this.field_73062_L = new EntityTracker(this);
        this.field_73063_M = new PlayerManager(this);

        if (this.field_73066_T == null)
        {
            this.field_73066_T = new IntHashMap();
        }

        if (this.field_73064_N == null)
        {
            this.field_73064_N = new HashSet();
        }

        if (this.field_73065_O == null)
        {
            this.field_73065_O = new TreeSet();
        }

        this.field_85177_Q = new Teleporter(this);
        this.field_96442_D = new ServerScoreboard(p_i45284_1_);
        ScoreboardSaveData scoreboardsavedata = (ScoreboardSaveData)this.field_72988_C.func_75742_a(ScoreboardSaveData.class, "scoreboard");

        if (scoreboardsavedata == null)
        {
            scoreboardsavedata = new ScoreboardSaveData();
            this.field_72988_C.func_75745_a("scoreboard", scoreboardsavedata);
        }

        scoreboardsavedata.func_96499_a(this.field_96442_D);
        ((ServerScoreboard)this.field_96442_D).func_96547_a(scoreboardsavedata);
    }

    public void func_72835_b()
    {
        super.func_72835_b();

        if (this.func_72912_H().func_76093_s() && this.field_73013_u != EnumDifficulty.HARD)
        {
            this.field_73013_u = EnumDifficulty.HARD;
        }

        this.field_73011_w.field_76578_c.func_76938_b();

        if (this.func_73056_e())
        {
            if (this.func_82736_K().getBooleanValue("doDaylightCycle"))
            {
                long i = this.field_72986_A.func_76073_f() + 24000L;
                this.field_72986_A.func_76068_b(i - i % 24000L);
            }

            this.func_73053_d();
        }

        this.profiler.startMeasure("mobSpawner");

        if (this.func_82736_K().getBooleanValue("doMobSpawning"))
        {
            this.field_135059_Q.func_77192_a(this, this.field_72985_G, this.field_72992_H, this.field_72986_A.func_82573_f() % 400L == 0L);
        }

        this.profiler.startNewMeasure("chunkSource");
        this.field_73020_y.func_73156_b();
        int j = this.func_72967_a(1.0F);

        if (j != this.field_73008_k)
        {
            this.field_73008_k = j;
        }

        this.field_72986_A.func_82572_b(this.field_72986_A.func_82573_f() + 1L);

        if (this.func_82736_K().getBooleanValue("doDaylightCycle"))
        {
            this.field_72986_A.func_76068_b(this.field_72986_A.func_76073_f() + 1L);
        }

        this.profiler.startNewMeasure("tickPending");
        this.func_72955_a(false);
        this.profiler.startNewMeasure("tickBlocks");
        this.func_147456_g();
        this.profiler.startNewMeasure("chunkMap");
        this.field_73063_M.func_72693_b();
        this.profiler.startNewMeasure("village");
        this.field_72982_D.func_75544_a();
        this.field_72983_E.func_75528_a();
        this.profiler.startNewMeasure("portalForcer");
        this.field_85177_Q.func_85189_a(this.func_82737_E());
        this.profiler.endMeasure();
        this.func_147488_Z();
    }

    public BiomeGenBase.SpawnListEntry func_73057_a(EnumCreatureType p_73057_1_, int p_73057_2_, int p_73057_3_, int p_73057_4_)
    {
        List list = this.func_72863_F().func_73155_a(p_73057_1_, p_73057_2_, p_73057_3_, p_73057_4_);
        return list != null && !list.isEmpty() ? (BiomeGenBase.SpawnListEntry)WeightedRandom.func_76271_a(this.field_73012_v, list) : null;
    }

    public void func_72854_c()
    {
        this.field_73068_P = !this.field_73010_i.isEmpty();
        Iterator iterator = this.field_73010_i.iterator();

        while (iterator.hasNext())
        {
            EntityPlayer entityplayer = (EntityPlayer)iterator.next();

            if (!entityplayer.func_70608_bn())
            {
                this.field_73068_P = false;
                break;
            }
        }
    }

    protected void func_73053_d()
    {
        this.field_73068_P = false;
        Iterator iterator = this.field_73010_i.iterator();

        while (iterator.hasNext())
        {
            EntityPlayer entityplayer = (EntityPlayer)iterator.next();

            if (entityplayer.func_70608_bn())
            {
                entityplayer.func_70999_a(false, false, true);
            }
        }

        this.func_73051_P();
    }

    private void func_73051_P()
    {
        this.field_72986_A.func_76080_g(0);
        this.field_72986_A.func_76084_b(false);
        this.field_72986_A.func_76090_f(0);
        this.field_72986_A.func_76069_a(false);
    }

    public boolean func_73056_e()
    {
        if (this.field_73068_P && !this.field_72995_K)
        {
            Iterator iterator = this.field_73010_i.iterator();
            EntityPlayer entityplayer;

            do
            {
                if (!iterator.hasNext())
                {
                    return true;
                }

                entityplayer = (EntityPlayer)iterator.next();
            }
            while (entityplayer.func_71026_bH());

            return false;
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_72974_f()
    {
        if (this.field_72986_A.func_76075_d() <= 0)
        {
            this.field_72986_A.func_76056_b(64);
        }

        int i = this.field_72986_A.func_76079_c();
        int j = this.field_72986_A.func_76074_e();
        int k = 0;

        while (this.func_147474_b(i, j).func_149688_o() == Material.field_151579_a)
        {
            i += this.field_73012_v.nextInt(8) - this.field_73012_v.nextInt(8);
            j += this.field_73012_v.nextInt(8) - this.field_73012_v.nextInt(8);
            ++k;

            if (k == 10000)
            {
                break;
            }
        }

        this.field_72986_A.func_76058_a(i);
        this.field_72986_A.func_76087_c(j);
    }

    protected void func_147456_g()
    {
        super.func_147456_g();
        int i = 0;
        int j = 0;
        Iterator iterator = this.field_72993_I.iterator();

        while (iterator.hasNext())
        {
            ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)iterator.next();
            int k = chunkcoordintpair.field_77276_a * 16;
            int l = chunkcoordintpair.field_77275_b * 16;
            this.profiler.startMeasure("getChunk");
            Chunk chunk = this.func_72964_e(chunkcoordintpair.field_77276_a, chunkcoordintpair.field_77275_b);
            this.func_147467_a(k, l, chunk);
            this.profiler.startNewMeasure("tickChunk");
            chunk.func_150804_b(false);
            this.profiler.startNewMeasure("thunder");
            int i1;
            int j1;
            int k1;
            int l1;

            if (this.field_73012_v.nextInt(100000) == 0 && this.func_72896_J() && this.func_72911_I())
            {
                this.field_73005_l = this.field_73005_l * 3 + 1013904223;
                i1 = this.field_73005_l >> 2;
                j1 = k + (i1 & 15);
                k1 = l + (i1 >> 8 & 15);
                l1 = this.func_72874_g(j1, k1);

                if (this.func_72951_B(j1, l1, k1))
                {
                    this.func_72942_c(new EntityLightningBolt(this, (double)j1, (double)l1, (double)k1));
                }
            }

            this.profiler.startNewMeasure("iceandsnow");

            if (this.field_73012_v.nextInt(16) == 0)
            {
                this.field_73005_l = this.field_73005_l * 3 + 1013904223;
                i1 = this.field_73005_l >> 2;
                j1 = i1 & 15;
                k1 = i1 >> 8 & 15;
                l1 = this.func_72874_g(j1 + k, k1 + l);

                if (this.func_72850_v(j1 + k, l1 - 1, k1 + l))
                {
                    this.func_147449_b(j1 + k, l1 - 1, k1 + l, Blocks.ICE);
                }

                if (this.func_72896_J() && this.func_147478_e(j1 + k, l1, k1 + l, true))
                {
                    this.func_147449_b(j1 + k, l1, k1 + l, Blocks.SNOW_LAYER);
                }

                if (this.func_72896_J())
                {
                    BiomeGenBase biomegenbase = this.func_72807_a(j1 + k, k1 + l);

                    if (biomegenbase.func_76738_d())
                    {
                        this.func_147439_a(j1 + k, l1 - 1, k1 + l).func_149639_l(this, j1 + k, l1 - 1, k1 + l);
                    }
                }
            }

            this.profiler.startNewMeasure("tickBlocks");
            ExtendedBlockStorage[] aextendedblockstorage = chunk.func_76587_i();
            j1 = aextendedblockstorage.length;

            for (k1 = 0; k1 < j1; ++k1)
            {
                ExtendedBlockStorage extendedblockstorage = aextendedblockstorage[k1];

                if (extendedblockstorage != null && extendedblockstorage.func_76675_b())
                {
                    for (int i3 = 0; i3 < 3; ++i3)
                    {
                        this.field_73005_l = this.field_73005_l * 3 + 1013904223;
                        int i2 = this.field_73005_l >> 2;
                        int j2 = i2 & 15;
                        int k2 = i2 >> 8 & 15;
                        int l2 = i2 >> 16 & 15;
                        ++j;
                        Block block = extendedblockstorage.func_150819_a(j2, l2, k2);

                        if (block.func_149653_t())
                        {
                            ++i;
                            block.func_149674_a(this, j2 + k, l2 + extendedblockstorage.func_76662_d(), k2 + l, this.field_73012_v);
                        }
                    }
                }
            }

            this.profiler.endMeasure();
        }
    }

    public boolean func_147477_a(int p_147477_1_, int p_147477_2_, int p_147477_3_, Block p_147477_4_)
    {
        NextTickListEntry nextticklistentry = new NextTickListEntry(p_147477_1_, p_147477_2_, p_147477_3_, p_147477_4_);
        return this.field_94579_S.contains(nextticklistentry);
    }

    public void func_147464_a(int p_147464_1_, int p_147464_2_, int p_147464_3_, Block p_147464_4_, int p_147464_5_)
    {
        this.func_147454_a(p_147464_1_, p_147464_2_, p_147464_3_, p_147464_4_, p_147464_5_, 0);
    }

    public void func_147454_a(int p_147454_1_, int p_147454_2_, int p_147454_3_, Block p_147454_4_, int p_147454_5_, int p_147454_6_)
    {
        NextTickListEntry nextticklistentry = new NextTickListEntry(p_147454_1_, p_147454_2_, p_147454_3_, p_147454_4_);
        byte b0 = 0;

        if (this.field_72999_e && p_147454_4_.func_149688_o() != Material.field_151579_a)
        {
            if (p_147454_4_.func_149698_L())
            {
                b0 = 8;

                if (this.func_72904_c(nextticklistentry.field_77183_a - b0, nextticklistentry.field_77181_b - b0, nextticklistentry.field_77182_c - b0, nextticklistentry.field_77183_a + b0, nextticklistentry.field_77181_b + b0, nextticklistentry.field_77182_c + b0))
                {
                    Block block1 = this.func_147439_a(nextticklistentry.field_77183_a, nextticklistentry.field_77181_b, nextticklistentry.field_77182_c);

                    if (block1.func_149688_o() != Material.field_151579_a && block1 == nextticklistentry.func_151351_a())
                    {
                        block1.func_149674_a(this, nextticklistentry.field_77183_a, nextticklistentry.field_77181_b, nextticklistentry.field_77182_c, this.field_73012_v);
                    }
                }

                return;
            }

            p_147454_5_ = 1;
        }

        if (this.func_72904_c(p_147454_1_ - b0, p_147454_2_ - b0, p_147454_3_ - b0, p_147454_1_ + b0, p_147454_2_ + b0, p_147454_3_ + b0))
        {
            if (p_147454_4_.func_149688_o() != Material.field_151579_a)
            {
                nextticklistentry.func_77176_a((long)p_147454_5_ + this.field_72986_A.func_82573_f());
                nextticklistentry.func_82753_a(p_147454_6_);
            }

            if (!this.field_73064_N.contains(nextticklistentry))
            {
                this.field_73064_N.add(nextticklistentry);
                this.field_73065_O.add(nextticklistentry);
            }
        }
    }

    public void func_147446_b(int p_147446_1_, int p_147446_2_, int p_147446_3_, Block p_147446_4_, int p_147446_5_, int p_147446_6_)
    {
        NextTickListEntry nextticklistentry = new NextTickListEntry(p_147446_1_, p_147446_2_, p_147446_3_, p_147446_4_);
        nextticklistentry.func_82753_a(p_147446_6_);

        if (p_147446_4_.func_149688_o() != Material.field_151579_a)
        {
            nextticklistentry.func_77176_a((long)p_147446_5_ + this.field_72986_A.func_82573_f());
        }

        if (!this.field_73064_N.contains(nextticklistentry))
        {
            this.field_73064_N.add(nextticklistentry);
            this.field_73065_O.add(nextticklistentry);
        }
    }

    public void func_72939_s()
    {
        if (this.field_73010_i.isEmpty())
        {
            if (this.field_80004_Q++ >= 1200)
            {
                return;
            }
        }
        else
        {
            this.func_82742_i();
        }

        super.func_72939_s();
    }

    public void func_82742_i()
    {
        this.field_80004_Q = 0;
    }

    public boolean func_72955_a(boolean p_72955_1_)
    {
        int i = this.field_73065_O.size();

        if (i != this.field_73064_N.size())
        {
            throw new IllegalStateException("TickNextTick list out of synch");
        }
        else
        {
            if (i > 1000)
            {
                i = 1000;
            }

            this.profiler.startMeasure("cleaning");
            NextTickListEntry nextticklistentry;

            for (int j = 0; j < i; ++j)
            {
                nextticklistentry = (NextTickListEntry)this.field_73065_O.first();

                if (!p_72955_1_ && nextticklistentry.field_77180_e > this.field_72986_A.func_82573_f())
                {
                    break;
                }

                this.field_73065_O.remove(nextticklistentry);
                this.field_73064_N.remove(nextticklistentry);
                this.field_94579_S.add(nextticklistentry);
            }

            this.profiler.endMeasure();
            this.profiler.startMeasure("ticking");
            Iterator iterator = this.field_94579_S.iterator();

            while (iterator.hasNext())
            {
                nextticklistentry = (NextTickListEntry)iterator.next();
                iterator.remove();
                byte b0 = 0;

                if (this.func_72904_c(nextticklistentry.field_77183_a - b0, nextticklistentry.field_77181_b - b0, nextticklistentry.field_77182_c - b0, nextticklistentry.field_77183_a + b0, nextticklistentry.field_77181_b + b0, nextticklistentry.field_77182_c + b0))
                {
                    Block block = this.func_147439_a(nextticklistentry.field_77183_a, nextticklistentry.field_77181_b, nextticklistentry.field_77182_c);

                    if (block.func_149688_o() != Material.field_151579_a && Block.func_149680_a(block, nextticklistentry.func_151351_a()))
                    {
                        try
                        {
                            block.func_149674_a(this, nextticklistentry.field_77183_a, nextticklistentry.field_77181_b, nextticklistentry.field_77182_c, this.field_73012_v);
                        }
                        catch (Throwable throwable1)
                        {
                            CrashReport crashreport = CrashReport.func_85055_a(throwable1, "Exception while ticking a block");
                            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Block being ticked");
                            int k;

                            try
                            {
                                k = this.func_72805_g(nextticklistentry.field_77183_a, nextticklistentry.field_77181_b, nextticklistentry.field_77182_c);
                            }
                            catch (Throwable throwable)
                            {
                                k = -1;
                            }

                            CrashReportCategory.func_147153_a(crashreportcategory, nextticklistentry.field_77183_a, nextticklistentry.field_77181_b, nextticklistentry.field_77182_c, block, k);
                            throw new ReportedException(crashreport);
                        }
                    }
                }
                else
                {
                    this.func_147464_a(nextticklistentry.field_77183_a, nextticklistentry.field_77181_b, nextticklistentry.field_77182_c, nextticklistentry.func_151351_a(), 0);
                }
            }

            this.profiler.endMeasure();
            this.field_94579_S.clear();
            return !this.field_73065_O.isEmpty();
        }
    }

    public List func_72920_a(Chunk p_72920_1_, boolean p_72920_2_)
    {
        ArrayList arraylist = null;
        ChunkCoordIntPair chunkcoordintpair = p_72920_1_.func_76632_l();
        int i = (chunkcoordintpair.field_77276_a << 4) - 2;
        int j = i + 16 + 2;
        int k = (chunkcoordintpair.field_77275_b << 4) - 2;
        int l = k + 16 + 2;

        for (int i1 = 0; i1 < 2; ++i1)
        {
            Iterator iterator;

            if (i1 == 0)
            {
                iterator = this.field_73065_O.iterator();
            }
            else
            {
                iterator = this.field_94579_S.iterator();

                if (!this.field_94579_S.isEmpty())
                {
                    field_147491_a.debug("toBeTicked = " + this.field_94579_S.size());
                }
            }

            while (iterator.hasNext())
            {
                NextTickListEntry nextticklistentry = (NextTickListEntry)iterator.next();

                if (nextticklistentry.field_77183_a >= i && nextticklistentry.field_77183_a < j && nextticklistentry.field_77182_c >= k && nextticklistentry.field_77182_c < l)
                {
                    if (p_72920_2_)
                    {
                        this.field_73064_N.remove(nextticklistentry);
                        iterator.remove();
                    }

                    if (arraylist == null)
                    {
                        arraylist = new ArrayList();
                    }

                    arraylist.add(nextticklistentry);
                }
            }
        }

        return arraylist;
    }

    public void func_72866_a(Entity p_72866_1_, boolean p_72866_2_)
    {
        if (!this.field_73061_a.func_71268_U() && (p_72866_1_ instanceof EntityAnimal || p_72866_1_ instanceof EntityWaterMob))
        {
            p_72866_1_.func_70106_y();
        }

        if (!this.field_73061_a.func_71220_V() && p_72866_1_ instanceof INpc)
        {
            p_72866_1_.func_70106_y();
        }

        super.func_72866_a(p_72866_1_, p_72866_2_);
    }

    protected IChunkProvider func_72970_h()
    {
        IChunkLoader ichunkloader = this.field_73019_z.func_75763_a(this.field_73011_w);
        this.field_73059_b = new ChunkProviderServer(this, ichunkloader, this.field_73011_w.func_76555_c());
        return this.field_73059_b;
    }

    public List func_147486_a(int p_147486_1_, int p_147486_2_, int p_147486_3_, int p_147486_4_, int p_147486_5_, int p_147486_6_)
    {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.field_147482_g.size(); ++k1)
        {
            TileEntity tileentity = (TileEntity)this.field_147482_g.get(k1);

            if (tileentity.field_145851_c >= p_147486_1_ && tileentity.field_145848_d >= p_147486_2_ && tileentity.field_145849_e >= p_147486_3_ && tileentity.field_145851_c < p_147486_4_ && tileentity.field_145848_d < p_147486_5_ && tileentity.field_145849_e < p_147486_6_)
            {
                arraylist.add(tileentity);
            }
        }

        return arraylist;
    }

    public boolean func_72962_a(EntityPlayer p_72962_1_, int p_72962_2_, int p_72962_3_, int p_72962_4_)
    {
        return !this.field_73061_a.func_96290_a(this, p_72962_2_, p_72962_3_, p_72962_4_, p_72962_1_);
    }

    protected void func_72963_a(WorldSettings p_72963_1_)
    {
        if (this.field_73066_T == null)
        {
            this.field_73066_T = new IntHashMap();
        }

        if (this.field_73064_N == null)
        {
            this.field_73064_N = new HashSet();
        }

        if (this.field_73065_O == null)
        {
            this.field_73065_O = new TreeSet();
        }

        this.func_73052_b(p_72963_1_);
        super.func_72963_a(p_72963_1_);
    }

    protected void func_73052_b(WorldSettings p_73052_1_)
    {
        if (!this.field_73011_w.func_76567_e())
        {
            this.field_72986_A.func_76081_a(0, this.field_73011_w.func_76557_i(), 0);
        }
        else
        {
            this.field_72987_B = true;
            WorldChunkManager worldchunkmanager = this.field_73011_w.field_76578_c;
            List list = worldchunkmanager.func_76932_a();
            Random random = new Random(this.func_72905_C());
            ChunkPosition chunkposition = worldchunkmanager.func_150795_a(0, 0, 256, list, random);
            int i = 0;
            int j = this.field_73011_w.func_76557_i();
            int k = 0;

            if (chunkposition != null)
            {
                i = chunkposition.field_151329_a;
                k = chunkposition.field_151328_c;
            }
            else
            {
                field_147491_a.warn("Unable to find spawn biome");
            }

            int l = 0;

            while (!this.field_73011_w.func_76566_a(i, k))
            {
                i += random.nextInt(64) - random.nextInt(64);
                k += random.nextInt(64) - random.nextInt(64);
                ++l;

                if (l == 1000)
                {
                    break;
                }
            }

            this.field_72986_A.func_76081_a(i, j, k);
            this.field_72987_B = false;

            if (p_73052_1_.func_77167_c())
            {
                this.func_73047_i();
            }
        }
    }

    protected void func_73047_i()
    {
        WorldGeneratorBonusChest worldgeneratorbonuschest = new WorldGeneratorBonusChest(field_73069_S, 10);

        for (int i = 0; i < 10; ++i)
        {
            int j = this.field_72986_A.func_76079_c() + this.field_73012_v.nextInt(6) - this.field_73012_v.nextInt(6);
            int k = this.field_72986_A.func_76074_e() + this.field_73012_v.nextInt(6) - this.field_73012_v.nextInt(6);
            int l = this.func_72825_h(j, k) + 1;

            if (worldgeneratorbonuschest.func_76484_a(this, this.field_73012_v, j, l, k))
            {
                break;
            }
        }
    }

    public ChunkCoordinates func_73054_j()
    {
        return this.field_73011_w.func_76554_h();
    }

    public void func_73044_a(boolean p_73044_1_, IProgressUpdate p_73044_2_) throws MinecraftException
    {
        if (this.field_73020_y.func_73157_c())
        {
            if (p_73044_2_ != null)
            {
                p_73044_2_.func_73720_a("Saving level");
            }

            this.func_73042_a();

            if (p_73044_2_ != null)
            {
                p_73044_2_.func_73719_c("Saving chunks");
            }

            this.field_73020_y.func_73151_a(p_73044_1_, p_73044_2_);
            ArrayList arraylist = Lists.newArrayList(this.field_73059_b.func_152380_a());
            Iterator iterator = arraylist.iterator();

            while (iterator.hasNext())
            {
                Chunk chunk = (Chunk)iterator.next();

                if (chunk != null && !this.field_73063_M.func_152621_a(chunk.field_76635_g, chunk.field_76647_h))
                {
                    this.field_73059_b.func_73241_b(chunk.field_76635_g, chunk.field_76647_h);
                }
            }
        }
    }

    public void func_104140_m()
    {
        if (this.field_73020_y.func_73157_c())
        {
            this.field_73020_y.func_104112_b();
        }
    }

    protected void func_73042_a() throws MinecraftException
    {
        this.func_72906_B();
        this.field_73019_z.func_75755_a(this.field_72986_A, this.field_73061_a.func_71203_ab().func_72378_q());
        this.field_72988_C.func_75744_a();
    }

    public void func_72923_a(Entity p_72923_1_)
    {
        super.func_72923_a(p_72923_1_);
        this.field_73066_T.func_76038_a(p_72923_1_.func_145782_y(), p_72923_1_);
        Entity[] aentity = p_72923_1_.func_70021_al();

        if (aentity != null)
        {
            for (int i = 0; i < aentity.length; ++i)
            {
                this.field_73066_T.func_76038_a(aentity[i].func_145782_y(), aentity[i]);
            }
        }
    }

    public void func_72847_b(Entity p_72847_1_)
    {
        super.func_72847_b(p_72847_1_);
        this.field_73066_T.func_76049_d(p_72847_1_.func_145782_y());
        Entity[] aentity = p_72847_1_.func_70021_al();

        if (aentity != null)
        {
            for (int i = 0; i < aentity.length; ++i)
            {
                this.field_73066_T.func_76049_d(aentity[i].func_145782_y());
            }
        }
    }

    public Entity func_73045_a(int p_73045_1_)
    {
        return (Entity)this.field_73066_T.func_76041_a(p_73045_1_);
    }

    public boolean func_72942_c(Entity p_72942_1_)
    {
        if (super.func_72942_c(p_72942_1_))
        {
            this.field_73061_a.func_71203_ab().func_148541_a(p_72942_1_.field_70165_t, p_72942_1_.field_70163_u, p_72942_1_.field_70161_v, 512.0D, this.field_73011_w.field_76574_g, new S2CPacketSpawnGlobalEntity(p_72942_1_));
            return true;
        }
        else
        {
            return false;
        }
    }

    public void func_72960_a(Entity p_72960_1_, byte p_72960_2_)
    {
        this.func_73039_n().func_151248_b(p_72960_1_, new S19PacketEntityStatus(p_72960_1_, p_72960_2_));
    }

    public Explosion func_72885_a(Entity p_72885_1_, double p_72885_2_, double p_72885_4_, double p_72885_6_, float p_72885_8_, boolean p_72885_9_, boolean p_72885_10_)
    {
        Explosion explosion = new Explosion(this, p_72885_1_, p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_);
        explosion.field_77286_a = p_72885_9_;
        explosion.field_82755_b = p_72885_10_;
        explosion.func_77278_a();
        explosion.func_77279_a(false);

        if (!p_72885_10_)
        {
            explosion.field_77281_g.clear();
        }

        Iterator iterator = this.field_73010_i.iterator();

        while (iterator.hasNext())
        {
            EntityPlayer entityplayer = (EntityPlayer)iterator.next();

            if (entityplayer.func_70092_e(p_72885_2_, p_72885_4_, p_72885_6_) < 4096.0D)
            {
                ((EntityPlayerMP)entityplayer).field_71135_a.func_147359_a(new S27PacketExplosion(p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_, explosion.field_77281_g, (Vec3)explosion.func_77277_b().get(entityplayer)));
            }
        }

        return explosion;
    }

    public void func_147452_c(int p_147452_1_, int p_147452_2_, int p_147452_3_, Block p_147452_4_, int p_147452_5_, int p_147452_6_)
    {
        BlockEventData blockeventdata = new BlockEventData(p_147452_1_, p_147452_2_, p_147452_3_, p_147452_4_, p_147452_5_, p_147452_6_);
        Iterator iterator = this.field_147490_S[this.field_147489_T].iterator();
        BlockEventData blockeventdata1;

        do
        {
            if (!iterator.hasNext())
            {
                this.field_147490_S[this.field_147489_T].add(blockeventdata);
                return;
            }

            blockeventdata1 = (BlockEventData)iterator.next();
        }
        while (!blockeventdata1.equals(blockeventdata));
    }

    private void func_147488_Z()
    {
        while (!this.field_147490_S[this.field_147489_T].isEmpty())
        {
            int i = this.field_147489_T;
            this.field_147489_T ^= 1;
            Iterator iterator = this.field_147490_S[i].iterator();

            while (iterator.hasNext())
            {
                BlockEventData blockeventdata = (BlockEventData)iterator.next();

                if (this.func_147485_a(blockeventdata))
                {
                    this.field_73061_a.func_71203_ab().func_148541_a((double)blockeventdata.func_151340_a(), (double)blockeventdata.func_151342_b(), (double)blockeventdata.func_151341_c(), 64.0D, this.field_73011_w.field_76574_g, new S24PacketBlockAction(blockeventdata.func_151340_a(), blockeventdata.func_151342_b(), blockeventdata.func_151341_c(), blockeventdata.func_151337_f(), blockeventdata.func_151339_d(), blockeventdata.func_151338_e()));
                }
            }

            this.field_147490_S[i].clear();
        }
    }

    private boolean func_147485_a(BlockEventData p_147485_1_)
    {
        Block block = this.func_147439_a(p_147485_1_.func_151340_a(), p_147485_1_.func_151342_b(), p_147485_1_.func_151341_c());
        return block == p_147485_1_.func_151337_f() ? block.func_149696_a(this, p_147485_1_.func_151340_a(), p_147485_1_.func_151342_b(), p_147485_1_.func_151341_c(), p_147485_1_.func_151339_d(), p_147485_1_.func_151338_e()) : false;
    }

    public void func_73041_k()
    {
        this.field_73019_z.func_75759_a();
    }

    protected void func_72979_l()
    {
        boolean flag = this.func_72896_J();
        super.func_72979_l();

        if (this.field_73003_n != this.field_73004_o)
        {
            this.field_73061_a.func_71203_ab().func_148537_a(new S2BPacketChangeGameState(7, this.field_73004_o), this.field_73011_w.field_76574_g);
        }

        if (this.field_73018_p != this.field_73017_q)
        {
            this.field_73061_a.func_71203_ab().func_148537_a(new S2BPacketChangeGameState(8, this.field_73017_q), this.field_73011_w.field_76574_g);
        }

        if (flag != this.func_72896_J())
        {
            if (flag)
            {
                this.field_73061_a.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(2, 0.0F));
            }
            else
            {
                this.field_73061_a.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(1, 0.0F));
            }

            this.field_73061_a.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(7, this.field_73004_o));
            this.field_73061_a.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(8, this.field_73017_q));
        }
    }

    protected int func_152379_p()
    {
        return this.field_73061_a.func_71203_ab().func_72395_o();
    }

    public MinecraftServer func_73046_m()
    {
        return this.field_73061_a;
    }

    public EntityTracker func_73039_n()
    {
        return this.field_73062_L;
    }

    public PlayerManager func_73040_p()
    {
        return this.field_73063_M;
    }

    public Teleporter func_85176_s()
    {
        return this.field_85177_Q;
    }

    public void func_147487_a(String p_147487_1_, double p_147487_2_, double p_147487_4_, double p_147487_6_, int p_147487_8_, double p_147487_9_, double p_147487_11_, double p_147487_13_, double p_147487_15_)
    {
        S2APacketParticles s2apacketparticles = new S2APacketParticles(p_147487_1_, (float)p_147487_2_, (float)p_147487_4_, (float)p_147487_6_, (float)p_147487_9_, (float)p_147487_11_, (float)p_147487_13_, (float)p_147487_15_, p_147487_8_);

        for (int j = 0; j < this.field_73010_i.size(); ++j)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)this.field_73010_i.get(j);
            ChunkCoordinates chunkcoordinates = entityplayermp.func_82114_b();
            double d7 = p_147487_2_ - (double)chunkcoordinates.field_71574_a;
            double d8 = p_147487_4_ - (double)chunkcoordinates.field_71572_b;
            double d9 = p_147487_6_ - (double)chunkcoordinates.field_71573_c;
            double d10 = d7 * d7 + d8 * d8 + d9 * d9;

            if (d10 <= 256.0D)
            {
                entityplayermp.field_71135_a.func_147359_a(s2apacketparticles);
            }
        }
    }

    static class ServerBlockEventList extends ArrayList
        {
            private static final String __OBFID = "CL_00001439";

            private ServerBlockEventList() {}

            ServerBlockEventList(Object p_i1521_1_)
            {
                this();
            }
        }
}