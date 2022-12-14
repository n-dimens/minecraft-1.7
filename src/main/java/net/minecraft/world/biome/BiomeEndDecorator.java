package net.minecraft.world.biome;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenSpikes;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeEndDecorator extends BiomeDecorator
{
    protected WorldGenerator field_76835_L;
    private static final String __OBFID = "CL_00000188";

    public BiomeEndDecorator()
    {
        this.field_76835_L = new WorldGenSpikes(Blocks.END_STONE);
    }

    protected void func_150513_a(BiomeGenBase p_150513_1_)
    {
        this.func_76797_b();

        if (this.field_76813_b.nextInt(5) == 0)
        {
            int i = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            int j = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            int k = this.field_76815_a.func_72825_h(i, j);
            this.field_76835_L.func_76484_a(this.field_76815_a, this.field_76813_b, i, k, j);
        }

        if (this.field_76814_c == 0 && this.field_76811_d == 0)
        {
            EntityDragon entitydragon = new EntityDragon(this.field_76815_a);
            entitydragon.func_70012_b(0.0D, 128.0D, 0.0D, this.field_76813_b.nextFloat() * 360.0F, 0.0F);
            this.field_76815_a.func_72838_d(entitydragon);
        }
    }
}