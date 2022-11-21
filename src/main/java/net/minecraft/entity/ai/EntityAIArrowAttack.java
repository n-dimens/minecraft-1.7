package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.util.MathHelper;

public class EntityAIArrowAttack extends EntityAIBase
{
    private final EntityLiving field_75322_b;
    private final IRangedAttackMob field_82641_b;
    private EntityLivingBase field_75323_c;
    private int field_75320_d;
    private double field_75321_e;
    private int field_75318_f;
    private int field_96561_g;
    private int field_75325_h;
    private float field_96562_i;
    private float field_82642_h;
    private static final String __OBFID = "CL_00001609";

    public EntityAIArrowAttack(IRangedAttackMob p_i1649_1_, double p_i1649_2_, int p_i1649_4_, float p_i1649_5_)
    {
        this(p_i1649_1_, p_i1649_2_, p_i1649_4_, p_i1649_4_, p_i1649_5_);
    }

    public EntityAIArrowAttack(IRangedAttackMob p_i1650_1_, double p_i1650_2_, int p_i1650_4_, int p_i1650_5_, float p_i1650_6_)
    {
        this.field_75320_d = -1;

        if (!(p_i1650_1_ instanceof EntityLivingBase))
        {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        else
        {
            this.field_82641_b = p_i1650_1_;
            this.field_75322_b = (EntityLiving)p_i1650_1_;
            this.field_75321_e = p_i1650_2_;
            this.field_96561_g = p_i1650_4_;
            this.field_75325_h = p_i1650_5_;
            this.field_96562_i = p_i1650_6_;
            this.field_82642_h = p_i1650_6_ * p_i1650_6_;
            this.func_75248_a(3);
        }
    }

    public boolean func_75250_a()
    {
        EntityLivingBase entitylivingbase = this.field_75322_b.func_70638_az();

        if (entitylivingbase == null)
        {
            return false;
        }
        else
        {
            this.field_75323_c = entitylivingbase;
            return true;
        }
    }

    public boolean func_75253_b()
    {
        return this.func_75250_a() || !this.field_75322_b.func_70661_as().func_75500_f();
    }

    public void func_75251_c()
    {
        this.field_75323_c = null;
        this.field_75318_f = 0;
        this.field_75320_d = -1;
    }

    public void func_75246_d()
    {
        double d0 = this.field_75322_b.func_70092_e(this.field_75323_c.field_70165_t, this.field_75323_c.field_70121_D.field_72338_b, this.field_75323_c.field_70161_v);
        boolean flag = this.field_75322_b.func_70635_at().func_75522_a(this.field_75323_c);

        if (flag)
        {
            ++this.field_75318_f;
        }
        else
        {
            this.field_75318_f = 0;
        }

        if (d0 <= (double)this.field_82642_h && this.field_75318_f >= 20)
        {
            this.field_75322_b.func_70661_as().func_75499_g();
        }
        else
        {
            this.field_75322_b.func_70661_as().func_75497_a(this.field_75323_c, this.field_75321_e);
        }

        this.field_75322_b.func_70671_ap().func_75651_a(this.field_75323_c, 30.0F, 30.0F);
        float f;

        if (--this.field_75320_d == 0)
        {
            if (d0 > (double)this.field_82642_h || !flag)
            {
                return;
            }

            f = MathHelper.func_76133_a(d0) / this.field_96562_i;
            float f1 = f;

            if (f < 0.1F)
            {
                f1 = 0.1F;
            }

            if (f1 > 1.0F)
            {
                f1 = 1.0F;
            }

            this.field_82641_b.func_82196_d(this.field_75323_c, f1);
            this.field_75320_d = MathHelper.func_76141_d(f * (float)(this.field_75325_h - this.field_96561_g) + (float)this.field_96561_g);
        }
        else if (this.field_75320_d < 0)
        {
            f = MathHelper.func_76133_a(d0) / this.field_96562_i;
            this.field_75320_d = MathHelper.func_76141_d(f * (float)(this.field_75325_h - this.field_96561_g) + (float)this.field_96561_g);
        }
    }
}