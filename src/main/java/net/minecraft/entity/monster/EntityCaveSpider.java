package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCaveSpider extends EntitySpider
{
    private static final String __OBFID = "CL_00001683";

    public EntityCaveSpider(World p_i1732_1_)
    {
        super(p_i1732_1_);
        this.func_70105_a(0.7F, 0.5F);
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(12.0D);
    }

    public boolean func_70652_k(Entity p_70652_1_)
    {
        if (super.func_70652_k(p_70652_1_))
        {
            if (p_70652_1_ instanceof EntityLivingBase)
            {
                byte b0 = 0;

                if (this.world.field_73013_u == EnumDifficulty.NORMAL)
                {
                    b0 = 7;
                }
                else if (this.world.field_73013_u == EnumDifficulty.HARD)
                {
                    b0 = 15;
                }

                if (b0 > 0)
                {
                    ((EntityLivingBase)p_70652_1_).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, b0 * 20, 0));
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
    {
        return p_110161_1_;
    }
}