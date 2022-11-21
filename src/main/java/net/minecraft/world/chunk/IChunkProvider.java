package net.minecraft.world.chunk;

import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public interface IChunkProvider
{
    boolean func_73149_a(int p_73149_1_, int p_73149_2_);

    Chunk func_73154_d(int p_73154_1_, int p_73154_2_);

    Chunk func_73158_c(int p_73158_1_, int p_73158_2_);

    void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_);

    boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_);

    boolean func_73156_b();

    boolean func_73157_c();

    String func_73148_d();

    List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_);

    ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_);

    int func_73152_e();

    void func_82695_e(int p_82695_1_, int p_82695_2_);

    void func_104112_b();
}