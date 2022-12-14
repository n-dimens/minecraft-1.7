package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class EntityAIOcelotSit extends EntityAIBase
{
    private final EntityOcelot field_151493_a;
    private final double field_151491_b;
    private int field_151492_c;
    private int field_151489_d;
    private int field_151490_e;
    private int field_151487_f;
    private int field_151488_g;
    private int field_151494_h;
    private static final String __OBFID = "CL_00001601";

    public EntityAIOcelotSit(EntityOcelot p_i45315_1_, double p_i45315_2_)
    {
        this.field_151493_a = p_i45315_1_;
        this.field_151491_b = p_i45315_2_;
        this.func_75248_a(5);
    }

    public boolean func_75250_a()
    {
        return this.field_151493_a.func_70909_n() && !this.field_151493_a.func_70906_o() && this.field_151493_a.func_70681_au().nextDouble() <= 0.006500000134110451D && this.func_151485_f();
    }

    public boolean func_75253_b()
    {
        return this.field_151492_c <= this.field_151490_e && this.field_151489_d <= 60 && this.func_151486_a(this.field_151493_a.world, this.field_151487_f, this.field_151488_g, this.field_151494_h);
    }

    public void func_75249_e()
    {
        this.field_151493_a.func_70661_as().func_75492_a((double)((float)this.field_151487_f) + 0.5D, (double)(this.field_151488_g + 1), (double)((float)this.field_151494_h) + 0.5D, this.field_151491_b);
        this.field_151492_c = 0;
        this.field_151489_d = 0;
        this.field_151490_e = this.field_151493_a.func_70681_au().nextInt(this.field_151493_a.func_70681_au().nextInt(1200) + 1200) + 1200;
        this.field_151493_a.func_70907_r().func_75270_a(false);
    }

    public void func_75251_c()
    {
        this.field_151493_a.func_70904_g(false);
    }

    public void func_75246_d()
    {
        ++this.field_151492_c;
        this.field_151493_a.func_70907_r().func_75270_a(false);

        if (this.field_151493_a.func_70092_e((double)this.field_151487_f, (double)(this.field_151488_g + 1), (double)this.field_151494_h) > 1.0D)
        {
            this.field_151493_a.func_70904_g(false);
            this.field_151493_a.func_70661_as().func_75492_a((double)((float)this.field_151487_f) + 0.5D, (double)(this.field_151488_g + 1), (double)((float)this.field_151494_h) + 0.5D, this.field_151491_b);
            ++this.field_151489_d;
        }
        else if (!this.field_151493_a.func_70906_o())
        {
            this.field_151493_a.func_70904_g(true);
        }
        else
        {
            --this.field_151489_d;
        }
    }

    private boolean func_151485_f()
    {
        int i = (int)this.field_151493_a.field_70163_u;
        double d0 = 2.147483647E9D;

        for (int j = (int)this.field_151493_a.field_70165_t - 8; (double)j < this.field_151493_a.field_70165_t + 8.0D; ++j)
        {
            for (int k = (int)this.field_151493_a.field_70161_v - 8; (double)k < this.field_151493_a.field_70161_v + 8.0D; ++k)
            {
                if (this.func_151486_a(this.field_151493_a.world, j, i, k) && this.field_151493_a.world.func_147437_c(j, i + 1, k))
                {
                    double d1 = this.field_151493_a.func_70092_e((double)j, (double)i, (double)k);

                    if (d1 < d0)
                    {
                        this.field_151487_f = j;
                        this.field_151488_g = i;
                        this.field_151494_h = k;
                        d0 = d1;
                    }
                }
            }
        }

        return d0 < 2.147483647E9D;
    }

    private boolean func_151486_a(World p_151486_1_, int p_151486_2_, int p_151486_3_, int p_151486_4_)
    {
        Block block = p_151486_1_.func_147439_a(p_151486_2_, p_151486_3_, p_151486_4_);
        int l = p_151486_1_.func_72805_g(p_151486_2_, p_151486_3_, p_151486_4_);

        if (block == Blocks.CHEST)
        {
            TileEntityChest tileentitychest = (TileEntityChest)p_151486_1_.func_147438_o(p_151486_2_, p_151486_3_, p_151486_4_);

            if (tileentitychest.field_145987_o < 1)
            {
                return true;
            }
        }
        else
        {
            if (block == Blocks.LIT_FURNACE)
            {
                return true;
            }

            if (block == Blocks.BED && !BlockBed.func_149975_b(l))
            {
                return true;
            }
        }

        return false;
    }
}