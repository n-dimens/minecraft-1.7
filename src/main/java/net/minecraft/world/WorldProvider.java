package net.minecraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.FlatGeneratorInfo;

public abstract class WorldProvider
{
    public static final float[] field_111203_a = new float[] {1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
    public World field_76579_a;
    public WorldType field_76577_b;
    public String field_82913_c;
    public WorldChunkManager field_76578_c;
    public boolean field_76575_d;
    public boolean field_76576_e;
    public float[] field_76573_f = new float[16];
    public int field_76574_g;
    private float[] field_76580_h = new float[4];
    private static final String __OBFID = "CL_00000386";

    public final void func_76558_a(World p_76558_1_)
    {
        this.field_76579_a = p_76558_1_;
        this.field_76577_b = p_76558_1_.func_72912_H().func_76067_t();
        this.field_82913_c = p_76558_1_.func_72912_H().func_82571_y();
        this.func_76572_b();
        this.func_76556_a();
    }

    protected void func_76556_a()
    {
        float f = 0.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.field_76573_f[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    protected void func_76572_b()
    {
        if (this.field_76579_a.func_72912_H().func_76067_t() == WorldType.field_77138_c)
        {
            FlatGeneratorInfo flatgeneratorinfo = FlatGeneratorInfo.func_82651_a(this.field_76579_a.func_72912_H().func_82571_y());
            this.field_76578_c = new WorldChunkManagerHell(BiomeGenBase.func_150568_d(flatgeneratorinfo.func_82648_a()), 0.5F);
        }
        else
        {
            this.field_76578_c = new WorldChunkManager(this.field_76579_a);
        }
    }

    public IChunkProvider func_76555_c()
    {
        return (IChunkProvider)(this.field_76577_b == WorldType.field_77138_c ? new ChunkProviderFlat(this.field_76579_a, this.field_76579_a.func_72905_C(), this.field_76579_a.func_72912_H().func_76089_r(), this.field_82913_c) : new ChunkProviderGenerate(this.field_76579_a, this.field_76579_a.func_72905_C(), this.field_76579_a.func_72912_H().func_76089_r()));
    }

    public boolean func_76566_a(int p_76566_1_, int p_76566_2_)
    {
        return this.field_76579_a.func_147474_b(p_76566_1_, p_76566_2_) == Blocks.field_150349_c;
    }

    public float func_76563_a(long p_76563_1_, float p_76563_3_)
    {
        int j = (int)(p_76563_1_ % 24000L);
        float f1 = ((float)j + p_76563_3_) / 24000.0F - 0.25F;

        if (f1 < 0.0F)
        {
            ++f1;
        }

        if (f1 > 1.0F)
        {
            --f1;
        }

        float f2 = f1;
        f1 = 1.0F - (float)((Math.cos((double)f1 * Math.PI) + 1.0D) / 2.0D);
        f1 = f2 + (f1 - f2) / 3.0F;
        return f1;
    }

    public int func_76559_b(long p_76559_1_)
    {
        return (int)(p_76559_1_ / 24000L % 8L + 8L) % 8;
    }

    public boolean func_76569_d()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public float[] func_76560_a(float p_76560_1_, float p_76560_2_)
    {
        float f2 = 0.4F;
        float f3 = MathHelper.func_76134_b(p_76560_1_ * (float)Math.PI * 2.0F) - 0.0F;
        float f4 = -0.0F;

        if (f3 >= f4 - f2 && f3 <= f4 + f2)
        {
            float f5 = (f3 - f4) / f2 * 0.5F + 0.5F;
            float f6 = 1.0F - (1.0F - MathHelper.func_76126_a(f5 * (float)Math.PI)) * 0.99F;
            f6 *= f6;
            this.field_76580_h[0] = f5 * 0.3F + 0.7F;
            this.field_76580_h[1] = f5 * f5 * 0.7F + 0.2F;
            this.field_76580_h[2] = f5 * f5 * 0.0F + 0.2F;
            this.field_76580_h[3] = f6;
            return this.field_76580_h;
        }
        else
        {
            return null;
        }
    }

    @SideOnly(Side.CLIENT)
    public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_)
    {
        float f2 = MathHelper.func_76134_b(p_76562_1_ * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = 0.7529412F;
        float f4 = 0.84705883F;
        float f5 = 1.0F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;
        return Vec3.func_72443_a((double)f3, (double)f4, (double)f5);
    }

    public boolean func_76567_e()
    {
        return true;
    }

    public static WorldProvider func_76570_a(int p_76570_0_)
    {
        return (WorldProvider)(p_76570_0_ == -1 ? new WorldProviderHell() : (p_76570_0_ == 0 ? new WorldProviderSurface() : (p_76570_0_ == 1 ? new WorldProviderEnd() : null)));
    }

    @SideOnly(Side.CLIENT)
    public float func_76571_f()
    {
        return 128.0F;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_76561_g()
    {
        return true;
    }

    public ChunkCoordinates func_76554_h()
    {
        return null;
    }

    public int func_76557_i()
    {
        return this.field_76577_b == WorldType.field_77138_c ? 4 : 64;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_76564_j()
    {
        return this.field_76577_b != WorldType.field_77138_c && !this.field_76576_e;
    }

    @SideOnly(Side.CLIENT)
    public double func_76565_k()
    {
        return this.field_76577_b == WorldType.field_77138_c ? 1.0D : 0.03125D;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_76568_b(int p_76568_1_, int p_76568_2_)
    {
        return false;
    }

    public abstract String func_80007_l();
}