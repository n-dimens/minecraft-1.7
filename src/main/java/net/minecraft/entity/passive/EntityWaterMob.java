package net.minecraft.entity.passive;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityWaterMob extends EntityCreature implements IAnimals
{
    private static final String __OBFID = "CL_00001653";

    public EntityWaterMob(World p_i1695_1_)
    {
        super(p_i1695_1_);
    }

    public boolean func_70648_aU()
    {
        return true;
    }

    public boolean func_70601_bi()
    {
        return this.world.func_72855_b(this.field_70121_D);
    }

    public int func_70627_aG()
    {
        return 120;
    }

    protected boolean func_70692_ba()
    {
        return true;
    }

    protected int func_70693_a(EntityPlayer p_70693_1_)
    {
        return 1 + this.world.field_73012_v.nextInt(3);
    }

    public void func_70030_z()
    {
        int i = this.func_70086_ai();
        super.func_70030_z();

        if (this.func_70089_S() && !this.func_70090_H())
        {
            --i;
            this.func_70050_g(i);

            if (this.func_70086_ai() == -20)
            {
                this.func_70050_g(0);
                this.func_70097_a(DamageSource.field_76369_e, 2.0F);
            }
        }
        else
        {
            this.func_70050_g(300);
        }
    }
}