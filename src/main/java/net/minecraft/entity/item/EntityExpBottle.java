package net.minecraft.entity.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityExpBottle extends EntityThrowable
{
    private static final String __OBFID = "CL_00001726";

    public EntityExpBottle(World p_i1785_1_)
    {
        super(p_i1785_1_);
    }

    public EntityExpBottle(World p_i1786_1_, EntityLivingBase p_i1786_2_)
    {
        super(p_i1786_1_, p_i1786_2_);
    }

    public EntityExpBottle(World p_i1787_1_, double p_i1787_2_, double p_i1787_4_, double p_i1787_6_)
    {
        super(p_i1787_1_, p_i1787_2_, p_i1787_4_, p_i1787_6_);
    }

    protected float func_70185_h()
    {
        return 0.07F;
    }

    protected float func_70182_d()
    {
        return 0.7F;
    }

    protected float func_70183_g()
    {
        return -20.0F;
    }

    protected void func_70184_a(MovingObjectPosition p_70184_1_)
    {
        if (!this.world.field_72995_K)
        {
            this.world.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v), 0);
            int i = 3 + this.world.field_73012_v.nextInt(5) + this.world.field_73012_v.nextInt(5);

            while (i > 0)
            {
                int j = EntityXPOrb.func_70527_a(i);
                i -= j;
                this.world.func_72838_d(new EntityXPOrb(this.world, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
            }

            this.func_70106_y();
        }
    }
}