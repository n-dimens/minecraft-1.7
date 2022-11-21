package net.minecraft.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIHurtByTarget extends EntityAITarget
{
    boolean field_75312_a;
    private int field_142052_b;
    private static final String __OBFID = "CL_00001619";

    public EntityAIHurtByTarget(EntityCreature p_i1660_1_, boolean p_i1660_2_)
    {
        super(p_i1660_1_, false);
        this.field_75312_a = p_i1660_2_;
        this.func_75248_a(1);
    }

    public boolean func_75250_a()
    {
        int i = this.field_75299_d.func_142015_aE();
        return i != this.field_142052_b && this.func_75296_a(this.field_75299_d.func_70643_av(), false);
    }

    public void func_75249_e()
    {
        this.field_75299_d.func_70624_b(this.field_75299_d.func_70643_av());
        this.field_142052_b = this.field_75299_d.func_142015_aE();

        if (this.field_75312_a)
        {
            double d0 = this.func_111175_f();
            List list = this.field_75299_d.field_70170_p.func_72872_a(this.field_75299_d.getClass(), AxisAlignedBB.func_72330_a(this.field_75299_d.field_70165_t, this.field_75299_d.field_70163_u, this.field_75299_d.field_70161_v, this.field_75299_d.field_70165_t + 1.0D, this.field_75299_d.field_70163_u + 1.0D, this.field_75299_d.field_70161_v + 1.0D).func_72314_b(d0, 10.0D, d0));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityCreature entitycreature = (EntityCreature)iterator.next();

                if (this.field_75299_d != entitycreature && entitycreature.func_70638_az() == null && !entitycreature.func_142014_c(this.field_75299_d.func_70643_av()))
                {
                    entitycreature.func_70624_b(this.field_75299_d.func_70643_av());
                }
            }
        }

        super.func_75249_e();
    }
}