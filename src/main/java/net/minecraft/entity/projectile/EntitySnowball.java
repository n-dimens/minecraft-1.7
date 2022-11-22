package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySnowball extends EntityThrowable
{
    private static final String __OBFID = "CL_00001722";

    public EntitySnowball(World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntitySnowball(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntitySnowball(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    protected void func_70184_a(MovingObjectPosition p_70184_1_)
    {
        if (p_70184_1_.field_72308_g != null)
        {
            byte b0 = 0;

            if (p_70184_1_.field_72308_g instanceof EntityBlaze)
            {
                b0 = 3;
            }

            p_70184_1_.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, this.func_85052_h()), (float)b0);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.world.func_72869_a("snowballpoof", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.field_72995_K)
        {
            this.func_70106_y();
        }
    }
}