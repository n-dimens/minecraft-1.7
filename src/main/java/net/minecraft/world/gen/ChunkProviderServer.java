package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkProviderServer implements IChunkProvider
{
    private static final Logger field_147417_b = LogManager.getLogger();
    private Set field_73248_b = Collections.newSetFromMap(new ConcurrentHashMap());
    private Chunk field_73249_c;
    public IChunkProvider field_73246_d;
    public IChunkLoader field_73247_e;
    public boolean field_73250_a = true;
    public LongHashMap field_73244_f = new LongHashMap();
    public List field_73245_g = new ArrayList();
    public WorldServer field_73251_h;
    private static final String __OBFID = "CL_00001436";

    public ChunkProviderServer(WorldServer p_i1520_1_, IChunkLoader p_i1520_2_, IChunkProvider p_i1520_3_)
    {
        this.field_73249_c = new EmptyChunk(p_i1520_1_, 0, 0);
        this.field_73251_h = p_i1520_1_;
        this.field_73247_e = p_i1520_2_;
        this.field_73246_d = p_i1520_3_;
    }

    public boolean func_73149_a(int p_73149_1_, int p_73149_2_)
    {
        return this.field_73244_f.func_76161_b(ChunkCoordIntPair.func_77272_a(p_73149_1_, p_73149_2_));
    }

    public List func_152380_a()
    {
        return this.field_73245_g;
    }

    public void func_73241_b(int p_73241_1_, int p_73241_2_)
    {
        if (this.field_73251_h.field_73011_w.func_76567_e())
        {
            ChunkCoordinates chunkcoordinates = this.field_73251_h.func_72861_E();
            int k = p_73241_1_ * 16 + 8 - chunkcoordinates.field_71574_a;
            int l = p_73241_2_ * 16 + 8 - chunkcoordinates.field_71573_c;
            short short1 = 128;

            if (k < -short1 || k > short1 || l < -short1 || l > short1)
            {
                this.field_73248_b.add(Long.valueOf(ChunkCoordIntPair.func_77272_a(p_73241_1_, p_73241_2_)));
            }
        }
        else
        {
            this.field_73248_b.add(Long.valueOf(ChunkCoordIntPair.func_77272_a(p_73241_1_, p_73241_2_)));
        }
    }

    public void func_73240_a()
    {
        Iterator iterator = this.field_73245_g.iterator();

        while (iterator.hasNext())
        {
            Chunk chunk = (Chunk)iterator.next();
            this.func_73241_b(chunk.field_76635_g, chunk.field_76647_h);
        }
    }

    public Chunk func_73158_c(int p_73158_1_, int p_73158_2_)
    {
        long k = ChunkCoordIntPair.func_77272_a(p_73158_1_, p_73158_2_);
        this.field_73248_b.remove(Long.valueOf(k));
        Chunk chunk = (Chunk)this.field_73244_f.func_76164_a(k);

        if (chunk == null)
        {
            chunk = this.func_73239_e(p_73158_1_, p_73158_2_);

            if (chunk == null)
            {
                if (this.field_73246_d == null)
                {
                    chunk = this.field_73249_c;
                }
                else
                {
                    try
                    {
                        chunk = this.field_73246_d.func_73154_d(p_73158_1_, p_73158_2_);
                    }
                    catch (Throwable throwable)
                    {
                        CrashReport crashreport = CrashReport.func_85055_a(throwable, "Exception generating new chunk");
                        CrashReportCategory crashreportcategory = crashreport.func_85058_a("Chunk to be generated");
                        crashreportcategory.func_71507_a("Location", String.format("%d,%d", new Object[] {Integer.valueOf(p_73158_1_), Integer.valueOf(p_73158_2_)}));
                        crashreportcategory.func_71507_a("Position hash", Long.valueOf(k));
                        crashreportcategory.func_71507_a("Generator", this.field_73246_d.func_73148_d());
                        throw new ReportedException(crashreport);
                    }
                }
            }

            this.field_73244_f.func_76163_a(k, chunk);
            this.field_73245_g.add(chunk);
            chunk.func_76631_c();
            chunk.func_76624_a(this, this, p_73158_1_, p_73158_2_);
        }

        return chunk;
    }

    public Chunk func_73154_d(int p_73154_1_, int p_73154_2_)
    {
        Chunk chunk = (Chunk)this.field_73244_f.func_76164_a(ChunkCoordIntPair.func_77272_a(p_73154_1_, p_73154_2_));
        return chunk == null ? (!this.field_73251_h.field_72987_B && !this.field_73250_a ? this.field_73249_c : this.func_73158_c(p_73154_1_, p_73154_2_)) : chunk;
    }

    private Chunk func_73239_e(int p_73239_1_, int p_73239_2_)
    {
        if (this.field_73247_e == null)
        {
            return null;
        }
        else
        {
            try
            {
                Chunk chunk = this.field_73247_e.func_75815_a(this.field_73251_h, p_73239_1_, p_73239_2_);

                if (chunk != null)
                {
                    chunk.field_76641_n = this.field_73251_h.func_82737_E();

                    if (this.field_73246_d != null)
                    {
                        this.field_73246_d.func_82695_e(p_73239_1_, p_73239_2_);
                    }
                }

                return chunk;
            }
            catch (Exception exception)
            {
                field_147417_b.error("Couldn\'t load chunk", exception);
                return null;
            }
        }
    }

    private void func_73243_a(Chunk p_73243_1_)
    {
        if (this.field_73247_e != null)
        {
            try
            {
                this.field_73247_e.func_75819_b(this.field_73251_h, p_73243_1_);
            }
            catch (Exception exception)
            {
                field_147417_b.error("Couldn\'t save entities", exception);
            }
        }
    }

    private void func_73242_b(Chunk p_73242_1_)
    {
        if (this.field_73247_e != null)
        {
            try
            {
                p_73242_1_.field_76641_n = this.field_73251_h.func_82737_E();
                this.field_73247_e.func_75816_a(this.field_73251_h, p_73242_1_);
            }
            catch (IOException ioexception)
            {
                field_147417_b.error("Couldn\'t save chunk", ioexception);
            }
            catch (MinecraftException minecraftexception)
            {
                field_147417_b.error("Couldn\'t save chunk; already in use by another instance of Minecraft?", minecraftexception);
            }
        }
    }

    public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
    {
        Chunk chunk = this.func_73154_d(p_73153_2_, p_73153_3_);

        if (!chunk.field_76646_k)
        {
            chunk.func_150809_p();

            if (this.field_73246_d != null)
            {
                this.field_73246_d.func_73153_a(p_73153_1_, p_73153_2_, p_73153_3_);
                chunk.func_76630_e();
            }
        }
    }

    public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        int i = 0;
        ArrayList arraylist = Lists.newArrayList(this.field_73245_g);

        for (int j = 0; j < arraylist.size(); ++j)
        {
            Chunk chunk = (Chunk)arraylist.get(j);

            if (p_73151_1_)
            {
                this.func_73243_a(chunk);
            }

            if (chunk.func_76601_a(p_73151_1_))
            {
                this.func_73242_b(chunk);
                chunk.field_76643_l = false;
                ++i;

                if (i == 24 && !p_73151_1_)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public void func_104112_b()
    {
        if (this.field_73247_e != null)
        {
            this.field_73247_e.func_75818_b();
        }
    }

    public boolean func_73156_b()
    {
        if (!this.field_73251_h.field_73058_d)
        {
            for (int i = 0; i < 100; ++i)
            {
                if (!this.field_73248_b.isEmpty())
                {
                    Long olong = (Long)this.field_73248_b.iterator().next();
                    Chunk chunk = (Chunk)this.field_73244_f.func_76164_a(olong.longValue());

                    if (chunk != null)
                    {
                        chunk.func_76623_d();
                        this.func_73242_b(chunk);
                        this.func_73243_a(chunk);
                        this.field_73245_g.remove(chunk);
                    }

                    this.field_73248_b.remove(olong);
                    this.field_73244_f.func_76159_d(olong.longValue());
                }
            }

            if (this.field_73247_e != null)
            {
                this.field_73247_e.func_75817_a();
            }
        }

        return this.field_73246_d.func_73156_b();
    }

    public boolean func_73157_c()
    {
        return !this.field_73251_h.field_73058_d;
    }

    public String func_73148_d()
    {
        return "ServerChunkCache: " + this.field_73244_f.func_76162_a() + " Drop: " + this.field_73248_b.size();
    }

    public List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
    {
        return this.field_73246_d.func_73155_a(p_73155_1_, p_73155_2_, p_73155_3_, p_73155_4_);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return this.field_73246_d.func_147416_a(p_147416_1_, p_147416_2_, p_147416_3_, p_147416_4_, p_147416_5_);
    }

    public int func_73152_e()
    {
        return this.field_73244_f.func_76162_a();
    }

    public void func_82695_e(int p_82695_1_, int p_82695_2_) {}
}