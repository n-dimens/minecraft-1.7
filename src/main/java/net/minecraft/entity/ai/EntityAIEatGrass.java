package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIEatGrass extends EntityAIBase
{
    private EntityLiving field_151500_b;
    private World field_151501_c;
    int field_151502_a;
    private static final String __OBFID = "CL_00001582";

    public EntityAIEatGrass(EntityLiving p_i45314_1_)
    {
        this.field_151500_b = p_i45314_1_;
        this.field_151501_c = p_i45314_1_.world;
        this.func_75248_a(7);
    }

    public boolean func_75250_a()
    {
        if (this.field_151500_b.func_70681_au().nextInt(this.field_151500_b.func_70631_g_() ? 50 : 1000) != 0)
        {
            return false;
        }
        else
        {
            int i = MathHelper.func_76128_c(this.field_151500_b.field_70165_t);
            int j = MathHelper.func_76128_c(this.field_151500_b.field_70163_u);
            int k = MathHelper.func_76128_c(this.field_151500_b.field_70161_v);
            return this.field_151501_c.func_147439_a(i, j, k) == Blocks.TALLGRASS && this.field_151501_c.func_72805_g(i, j, k) == 1 ? true : this.field_151501_c.func_147439_a(i, j - 1, k) == Blocks.GRASS;
        }
    }

    public void func_75249_e()
    {
        this.field_151502_a = 40;
        this.field_151501_c.func_72960_a(this.field_151500_b, (byte)10);
        this.field_151500_b.func_70661_as().func_75499_g();
    }

    public void func_75251_c()
    {
        this.field_151502_a = 0;
    }

    public boolean func_75253_b()
    {
        return this.field_151502_a > 0;
    }

    public int func_151499_f()
    {
        return this.field_151502_a;
    }

    public void func_75246_d()
    {
        this.field_151502_a = Math.max(0, this.field_151502_a - 1);

        if (this.field_151502_a == 4)
        {
            int i = MathHelper.func_76128_c(this.field_151500_b.field_70165_t);
            int j = MathHelper.func_76128_c(this.field_151500_b.field_70163_u);
            int k = MathHelper.func_76128_c(this.field_151500_b.field_70161_v);

            if (this.field_151501_c.func_147439_a(i, j, k) == Blocks.TALLGRASS)
            {
                if (this.field_151501_c.func_82736_K().getBooleanValue("mobGriefing"))
                {
                    this.field_151501_c.func_147480_a(i, j, k, false);
                }

                this.field_151500_b.func_70615_aA();
            }
            else if (this.field_151501_c.func_147439_a(i, j - 1, k) == Blocks.GRASS)
            {
                if (this.field_151501_c.func_82736_K().getBooleanValue("mobGriefing"))
                {
                    this.field_151501_c.func_72926_e(2001, i, j - 1, k, Block.func_149682_b(Blocks.GRASS));
                    this.field_151501_c.func_147465_d(i, j - 1, k, Blocks.DIRT, 0, 2);
                }

                this.field_151500_b.func_70615_aA();
            }
        }
    }
}