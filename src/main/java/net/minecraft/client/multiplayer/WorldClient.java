package net.minecraft.client.multiplayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSoundMinecart;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.particle.EntityFireworkStarterFX;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.SaveHandlerMP;

@SideOnly(Side.CLIENT)
public class WorldClient extends World
{
    private NetHandlerPlayClient field_73035_a;
    private ChunkProviderClient field_73033_b;
    private IntHashMap field_73034_c = new IntHashMap();
    private Set field_73032_d = new HashSet();
    private Set field_73036_L = new HashSet();
    private final Minecraft field_73037_M = Minecraft.func_71410_x();
    private final Set field_73038_N = new HashSet();
    private static final String __OBFID = "CL_00000882";

    public WorldClient(NetHandlerPlayClient p_i45063_1_, WorldSettings p_i45063_2_, int p_i45063_3_, EnumDifficulty p_i45063_4_, Profiler p_i45063_5_)
    {
        super(new SaveHandlerMP(), "MpServer", WorldProvider.func_76570_a(p_i45063_3_), p_i45063_2_, p_i45063_5_);
        this.field_73035_a = p_i45063_1_;
        this.field_73013_u = p_i45063_4_;
        this.func_72950_A(8, 64, 8);
        this.field_72988_C = p_i45063_1_.field_147305_a;
    }

    public void func_72835_b()
    {
        super.func_72835_b();
        this.func_82738_a(this.func_82737_E() + 1L);

        if (this.func_82736_K().getBooleanValue("doDaylightCycle"))
        {
            this.func_72877_b(this.func_72820_D() + 1L);
        }

        this.profiler.startMeasure("reEntryProcessing");

        for (int i = 0; i < 10 && !this.field_73036_L.isEmpty(); ++i)
        {
            Entity entity = (Entity)this.field_73036_L.iterator().next();
            this.field_73036_L.remove(entity);

            if (!this.field_72996_f.contains(entity))
            {
                this.func_72838_d(entity);
            }
        }

        this.profiler.startNewMeasure("connection");
        this.field_73035_a.func_147233_a();
        this.profiler.startNewMeasure("chunkCache");
        this.field_73033_b.func_73156_b();
        this.profiler.startNewMeasure("blocks");
        this.func_147456_g();
        this.profiler.endMeasure();
    }

    public void func_73031_a(int p_73031_1_, int p_73031_2_, int p_73031_3_, int p_73031_4_, int p_73031_5_, int p_73031_6_) {}

    protected IChunkProvider func_72970_h()
    {
        this.field_73033_b = new ChunkProviderClient(this);
        return this.field_73033_b;
    }

    protected void func_147456_g()
    {
        super.func_147456_g();
        this.field_73038_N.retainAll(this.field_72993_I);

        if (this.field_73038_N.size() == this.field_72993_I.size())
        {
            this.field_73038_N.clear();
        }

        int i = 0;
        Iterator iterator = this.field_72993_I.iterator();

        while (iterator.hasNext())
        {
            ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)iterator.next();

            if (!this.field_73038_N.contains(chunkcoordintpair))
            {
                int j = chunkcoordintpair.field_77276_a * 16;
                int k = chunkcoordintpair.field_77275_b * 16;
                this.profiler.startMeasure("getChunk");
                Chunk chunk = this.func_72964_e(chunkcoordintpair.field_77276_a, chunkcoordintpair.field_77275_b);
                this.func_147467_a(j, k, chunk);
                this.profiler.endMeasure();
                this.field_73038_N.add(chunkcoordintpair);
                ++i;

                if (i >= 10)
                {
                    return;
                }
            }
        }
    }

    public void func_73025_a(int p_73025_1_, int p_73025_2_, boolean p_73025_3_)
    {
        if (p_73025_3_)
        {
            this.field_73033_b.func_73158_c(p_73025_1_, p_73025_2_);
        }
        else
        {
            this.field_73033_b.func_73234_b(p_73025_1_, p_73025_2_);
        }

        if (!p_73025_3_)
        {
            this.func_147458_c(p_73025_1_ * 16, 0, p_73025_2_ * 16, p_73025_1_ * 16 + 15, 256, p_73025_2_ * 16 + 15);
        }
    }

    public boolean func_72838_d(Entity p_72838_1_)
    {
        boolean flag = super.func_72838_d(p_72838_1_);
        this.field_73032_d.add(p_72838_1_);

        if (!flag)
        {
            this.field_73036_L.add(p_72838_1_);
        }
        else if (p_72838_1_ instanceof EntityMinecart)
        {
            this.field_73037_M.func_147118_V().func_147682_a(new MovingSoundMinecart((EntityMinecart)p_72838_1_));
        }

        return flag;
    }

    public void func_72900_e(Entity p_72900_1_)
    {
        super.func_72900_e(p_72900_1_);
        this.field_73032_d.remove(p_72900_1_);
    }

    public void func_72923_a(Entity p_72923_1_)
    {
        super.func_72923_a(p_72923_1_);

        if (this.field_73036_L.contains(p_72923_1_))
        {
            this.field_73036_L.remove(p_72923_1_);
        }
    }

    public void func_72847_b(Entity p_72847_1_)
    {
        super.func_72847_b(p_72847_1_);
        boolean flag = false;

        if (this.field_73032_d.contains(p_72847_1_))
        {
            if (p_72847_1_.func_70089_S())
            {
                this.field_73036_L.add(p_72847_1_);
                flag = true;
            }
            else
            {
                this.field_73032_d.remove(p_72847_1_);
            }
        }

        if (RenderManager.field_78727_a.func_78713_a(p_72847_1_).func_147905_a() && !flag)
        {
            this.field_73037_M.field_71438_f.func_147584_b();
        }
    }

    public void func_73027_a(int p_73027_1_, Entity p_73027_2_)
    {
        Entity entity1 = this.func_73045_a(p_73027_1_);

        if (entity1 != null)
        {
            this.func_72900_e(entity1);
        }

        this.field_73032_d.add(p_73027_2_);
        p_73027_2_.func_145769_d(p_73027_1_);

        if (!this.func_72838_d(p_73027_2_))
        {
            this.field_73036_L.add(p_73027_2_);
        }

        this.field_73034_c.func_76038_a(p_73027_1_, p_73027_2_);

        if (RenderManager.field_78727_a.func_78713_a(p_73027_2_).func_147905_a())
        {
            this.field_73037_M.field_71438_f.func_147584_b();
        }
    }

    public Entity func_73045_a(int p_73045_1_)
    {
        return (Entity)(p_73045_1_ == this.field_73037_M.field_71439_g.func_145782_y() ? this.field_73037_M.field_71439_g : (Entity)this.field_73034_c.func_76041_a(p_73045_1_));
    }

    public Entity func_73028_b(int p_73028_1_)
    {
        Entity entity = (Entity)this.field_73034_c.func_76049_d(p_73028_1_);

        if (entity != null)
        {
            this.field_73032_d.remove(entity);
            this.func_72900_e(entity);
        }

        return entity;
    }

    public boolean func_147492_c(int p_147492_1_, int p_147492_2_, int p_147492_3_, Block p_147492_4_, int p_147492_5_)
    {
        this.func_73031_a(p_147492_1_, p_147492_2_, p_147492_3_, p_147492_1_, p_147492_2_, p_147492_3_);
        return super.func_147465_d(p_147492_1_, p_147492_2_, p_147492_3_, p_147492_4_, p_147492_5_, 3);
    }

    public void func_72882_A()
    {
        this.field_73035_a.func_147298_b().func_150718_a(new ChatComponentText("Quitting"));
    }

    protected void func_72979_l()
    {
        if (!this.field_73011_w.field_76576_e)
        {
            ;
        }
    }

    protected int func_152379_p()
    {
        return this.field_73037_M.gameSettings.field_151451_c;
    }

    public void func_73029_E(int p_73029_1_, int p_73029_2_, int p_73029_3_)
    {
        byte b0 = 16;
        Random random = new Random();

        for (int l = 0; l < 1000; ++l)
        {
            int i1 = p_73029_1_ + this.field_73012_v.nextInt(b0) - this.field_73012_v.nextInt(b0);
            int j1 = p_73029_2_ + this.field_73012_v.nextInt(b0) - this.field_73012_v.nextInt(b0);
            int k1 = p_73029_3_ + this.field_73012_v.nextInt(b0) - this.field_73012_v.nextInt(b0);
            Block block = this.func_147439_a(i1, j1, k1);

            if (block.func_149688_o() == Material.field_151579_a)
            {
                if (this.field_73012_v.nextInt(8) > j1 && this.field_73011_w.func_76564_j())
                {
                    this.func_72869_a("depthsuspend", (double)((float)i1 + this.field_73012_v.nextFloat()), (double)((float)j1 + this.field_73012_v.nextFloat()), (double)((float)k1 + this.field_73012_v.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
            }
            else
            {
                block.func_149734_b(this, i1, j1, k1, random);
            }
        }
    }

    public void func_73022_a()
    {
        this.field_72996_f.removeAll(this.field_72997_g);
        int i;
        Entity entity;
        int j;
        int k;

        for (i = 0; i < this.field_72997_g.size(); ++i)
        {
            entity = (Entity)this.field_72997_g.get(i);
            j = entity.field_70176_ah;
            k = entity.field_70164_aj;

            if (entity.field_70175_ag && this.func_72916_c(j, k))
            {
                this.func_72964_e(j, k).func_76622_b(entity);
            }
        }

        for (i = 0; i < this.field_72997_g.size(); ++i)
        {
            this.func_72847_b((Entity)this.field_72997_g.get(i));
        }

        this.field_72997_g.clear();

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

            if (entity.field_70128_L)
            {
                j = entity.field_70176_ah;
                k = entity.field_70164_aj;

                if (entity.field_70175_ag && this.func_72916_c(j, k))
                {
                    this.func_72964_e(j, k).func_76622_b(entity);
                }

                this.field_72996_f.remove(i--);
                this.func_72847_b(entity);
            }
        }
    }

    public CrashReportCategory func_72914_a(CrashReport p_72914_1_)
    {
        CrashReportCategory crashreportcategory = super.func_72914_a(p_72914_1_);
        crashreportcategory.func_71500_a("Forced entities", new Callable()
        {
            private static final String __OBFID = "CL_00000883";
            public String call()
            {
                return WorldClient.this.field_73032_d.size() + " total; " + WorldClient.this.field_73032_d.toString();
            }
        });
        crashreportcategory.func_71500_a("Retry entities", new Callable()
        {
            private static final String __OBFID = "CL_00000884";
            public String call()
            {
                return WorldClient.this.field_73036_L.size() + " total; " + WorldClient.this.field_73036_L.toString();
            }
        });
        crashreportcategory.func_71500_a("Server brand", new Callable()
        {
            private static final String __OBFID = "CL_00000885";
            public String call()
            {
                return WorldClient.this.field_73037_M.field_71439_g.func_142021_k();
            }
        });
        crashreportcategory.func_71500_a("Server type", new Callable()
        {
            private static final String __OBFID = "CL_00000886";
            public String call()
            {
                return WorldClient.this.field_73037_M.func_71401_C() == null ? "Non-integrated multiplayer server" : "Integrated singleplayer server";
            }
        });
        return crashreportcategory;
    }

    public void func_72980_b(double p_72980_1_, double p_72980_3_, double p_72980_5_, String p_72980_7_, float p_72980_8_, float p_72980_9_, boolean p_72980_10_)
    {
        double d3 = this.field_73037_M.field_71451_h.func_70092_e(p_72980_1_, p_72980_3_, p_72980_5_);
        PositionedSoundRecord positionedsoundrecord = new PositionedSoundRecord(new ResourceLocation(p_72980_7_), p_72980_8_, p_72980_9_, (float)p_72980_1_, (float)p_72980_3_, (float)p_72980_5_);

        if (p_72980_10_ && d3 > 100.0D)
        {
            double d4 = Math.sqrt(d3) / 40.0D;
            this.field_73037_M.func_147118_V().func_147681_a(positionedsoundrecord, (int)(d4 * 20.0D));
        }
        else
        {
            this.field_73037_M.func_147118_V().func_147682_a(positionedsoundrecord);
        }
    }

    public void func_92088_a(double p_92088_1_, double p_92088_3_, double p_92088_5_, double p_92088_7_, double p_92088_9_, double p_92088_11_, NBTTagCompound p_92088_13_)
    {
        this.field_73037_M.field_71452_i.func_78873_a(new EntityFireworkStarterFX(this, p_92088_1_, p_92088_3_, p_92088_5_, p_92088_7_, p_92088_9_, p_92088_11_, this.field_73037_M.field_71452_i, p_92088_13_));
    }

    public void func_96443_a(Scoreboard p_96443_1_)
    {
        this.field_96442_D = p_96443_1_;
    }

    public void func_72877_b(long p_72877_1_)
    {
        if (p_72877_1_ < 0L)
        {
            p_72877_1_ = -p_72877_1_;
            this.func_82736_K().setRuleValue("doDaylightCycle", "false");
        }
        else
        {
            this.func_82736_K().setRuleValue("doDaylightCycle", "true");
        }

        super.func_72877_b(p_72877_1_);
    }
}