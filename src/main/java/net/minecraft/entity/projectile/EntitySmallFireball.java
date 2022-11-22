package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySmallFireball extends EntityFireball
{
    private static final String __OBFID = "CL_00001721";

    public EntitySmallFireball(World p_i1770_1_)
    {
        super(p_i1770_1_);
        this.func_70105_a(0.3125F, 0.3125F);
    }

    public EntitySmallFireball(World p_i1771_1_, EntityLivingBase p_i1771_2_, double p_i1771_3_, double p_i1771_5_, double p_i1771_7_)
    {
        super(p_i1771_1_, p_i1771_2_, p_i1771_3_, p_i1771_5_, p_i1771_7_);
        this.func_70105_a(0.3125F, 0.3125F);
    }

    public EntitySmallFireball(World p_i1772_1_, double p_i1772_2_, double p_i1772_4_, double p_i1772_6_, double p_i1772_8_, double p_i1772_10_, double p_i1772_12_)
    {
        super(p_i1772_1_, p_i1772_2_, p_i1772_4_, p_i1772_6_, p_i1772_8_, p_i1772_10_, p_i1772_12_);
        this.func_70105_a(0.3125F, 0.3125F);
    }

    protected void func_70227_a(MovingObjectPosition p_70227_1_)
    {
        if (!this.world.field_72995_K)
        {
            if (p_70227_1_.field_72308_g != null)
            {
                if (!p_70227_1_.field_72308_g.func_70045_F() && p_70227_1_.field_72308_g.func_70097_a(DamageSource.func_76362_a(this, this.field_70235_a), 5.0F))
                {
                    p_70227_1_.field_72308_g.func_70015_d(5);
                }
            }
            else
            {
                int i = p_70227_1_.field_72311_b;
                int j = p_70227_1_.field_72312_c;
                int k = p_70227_1_.field_72309_d;

                switch (p_70227_1_.field_72310_e)
                {
                    case 0:
                        --j;
                        break;
                    case 1:
                        ++j;
                        break;
                    case 2:
                        --k;
                        break;
                    case 3:
                        ++k;
                        break;
                    case 4:
                        --i;
                        break;
                    case 5:
                        ++i;
                }

                if (this.world.func_147437_c(i, j, k))
                {
                    this.world.func_147449_b(i, j, k, Blocks.FIRE);
                }
            }

            this.func_70106_y();
        }
    }

    public boolean func_70067_L()
    {
        return false;
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        return false;
    }
}