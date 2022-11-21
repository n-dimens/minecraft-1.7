package net.minecraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;

public class WorldProviderEnd extends WorldProvider
{
    private static final String __OBFID = "CL_00000389";

    public void func_76572_b()
    {
        this.field_76578_c = new WorldChunkManagerHell(BiomeGenBase.field_76779_k, 0.0F);
        this.field_76574_g = 1;
        this.field_76576_e = true;
    }

    public IChunkProvider func_76555_c()
    {
        return new ChunkProviderEnd(this.field_76579_a, this.field_76579_a.func_72905_C());
    }

    public float func_76563_a(long p_76563_1_, float p_76563_3_)
    {
        return 0.0F;
    }

    @SideOnly(Side.CLIENT)
    public float[] func_76560_a(float p_76560_1_, float p_76560_2_)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_)
    {
        int i = 10518688;
        float f2 = MathHelper.func_76134_b(p_76562_1_ * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = (float)(i >> 16 & 255) / 255.0F;
        float f4 = (float)(i >> 8 & 255) / 255.0F;
        float f5 = (float)(i & 255) / 255.0F;
        f3 *= f2 * 0.0F + 0.15F;
        f4 *= f2 * 0.0F + 0.15F;
        f5 *= f2 * 0.0F + 0.15F;
        return Vec3.func_72443_a((double)f3, (double)f4, (double)f5);
    }

    @SideOnly(Side.CLIENT)
    public boolean func_76561_g()
    {
        return false;
    }

    public boolean func_76567_e()
    {
        return false;
    }

    public boolean func_76569_d()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float func_76571_f()
    {
        return 8.0F;
    }

    public boolean func_76566_a(int p_76566_1_, int p_76566_2_)
    {
        return this.field_76579_a.func_147474_b(p_76566_1_, p_76566_2_).func_149688_o().func_76230_c();
    }

    public ChunkCoordinates func_76554_h()
    {
        return new ChunkCoordinates(100, 50, 0);
    }

    public int func_76557_i()
    {
        return 50;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_76568_b(int p_76568_1_, int p_76568_2_)
    {
        return true;
    }

    public String func_80007_l()
    {
        return "The End";
    }
}