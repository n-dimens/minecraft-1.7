package net.minecraft.client.multiplayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class ChunkProviderClient implements IChunkProvider
{
    private static final Logger field_147436_a = LogManager.getLogger();
    private Chunk field_73238_a;
    private LongHashMap field_73236_b = new LongHashMap();
    private List field_73237_c = new ArrayList();
    private World field_73235_d;
    private static final String __OBFID = "CL_00000880";

    public ChunkProviderClient(World p_i1184_1_)
    {
        this.field_73238_a = new EmptyChunk(p_i1184_1_, 0, 0);
        this.field_73235_d = p_i1184_1_;
    }

    public boolean func_73149_a(int p_73149_1_, int p_73149_2_)
    {
        return true;
    }

    public void func_73234_b(int p_73234_1_, int p_73234_2_)
    {
        Chunk chunk = this.func_73154_d(p_73234_1_, p_73234_2_);

        if (!chunk.func_76621_g())
        {
            chunk.func_76623_d();
        }

        this.field_73236_b.func_76159_d(ChunkCoordIntPair.func_77272_a(p_73234_1_, p_73234_2_));
        this.field_73237_c.remove(chunk);
    }

    public Chunk func_73158_c(int p_73158_1_, int p_73158_2_)
    {
        Chunk chunk = new Chunk(this.field_73235_d, p_73158_1_, p_73158_2_);
        this.field_73236_b.func_76163_a(ChunkCoordIntPair.func_77272_a(p_73158_1_, p_73158_2_), chunk);
        this.field_73237_c.add(chunk);
        chunk.field_76636_d = true;
        return chunk;
    }

    public Chunk func_73154_d(int p_73154_1_, int p_73154_2_)
    {
        Chunk chunk = (Chunk)this.field_73236_b.func_76164_a(ChunkCoordIntPair.func_77272_a(p_73154_1_, p_73154_2_));
        return chunk == null ? this.field_73238_a : chunk;
    }

    public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        return true;
    }

    public void func_104112_b() {}

    public boolean func_73156_b()
    {
        long i = System.currentTimeMillis();
        Iterator iterator = this.field_73237_c.iterator();

        while (iterator.hasNext())
        {
            Chunk chunk = (Chunk)iterator.next();
            chunk.func_150804_b(System.currentTimeMillis() - i > 5L);
        }

        if (System.currentTimeMillis() - i > 100L)
        {
            field_147436_a.info("Warning: Clientside chunk ticking took {} ms", new Object[] {Long.valueOf(System.currentTimeMillis() - i)});
        }

        return false;
    }

    public boolean func_73157_c()
    {
        return false;
    }

    public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {}

    public String func_73148_d()
    {
        return "MultiplayerChunkCache: " + this.field_73236_b.func_76162_a() + ", " + this.field_73237_c.size();
    }

    public List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
    {
        return null;
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return null;
    }

    public int func_73152_e()
    {
        return this.field_73237_c.size();
    }

    public void func_82695_e(int p_82695_1_, int p_82695_2_) {}
}