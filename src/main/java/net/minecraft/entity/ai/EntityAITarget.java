package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;

public abstract class EntityAITarget extends EntityAIBase
{
    protected EntityCreature field_75299_d;
    protected boolean field_75297_f;
    private boolean field_75303_a;
    private int field_75301_b;
    private int field_75302_c;
    private int field_75298_g;
    private static final String __OBFID = "CL_00001626";

    public EntityAITarget(EntityCreature p_i1669_1_, boolean p_i1669_2_)
    {
        this(p_i1669_1_, p_i1669_2_, false);
    }

    public EntityAITarget(EntityCreature p_i1670_1_, boolean p_i1670_2_, boolean p_i1670_3_)
    {
        this.field_75299_d = p_i1670_1_;
        this.field_75297_f = p_i1670_2_;
        this.field_75303_a = p_i1670_3_;
    }

    public boolean func_75253_b()
    {
        EntityLivingBase entitylivingbase = this.field_75299_d.func_70638_az();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.func_70089_S())
        {
            return false;
        }
        else
        {
            double d0 = this.func_111175_f();

            if (this.field_75299_d.func_70068_e(entitylivingbase) > d0 * d0)
            {
                return false;
            }
            else
            {
                if (this.field_75297_f)
                {
                    if (this.field_75299_d.func_70635_at().func_75522_a(entitylivingbase))
                    {
                        this.field_75298_g = 0;
                    }
                    else if (++this.field_75298_g > 60)
                    {
                        return false;
                    }
                }

                return !(entitylivingbase instanceof EntityPlayerMP) || !((EntityPlayerMP)entitylivingbase).field_71134_c.func_73083_d();
            }
        }
    }

    protected double func_111175_f()
    {
        IAttributeInstance iattributeinstance = this.field_75299_d.func_110148_a(SharedMonsterAttributes.field_111265_b);
        return iattributeinstance == null ? 16.0D : iattributeinstance.func_111126_e();
    }

    public void func_75249_e()
    {
        this.field_75301_b = 0;
        this.field_75302_c = 0;
        this.field_75298_g = 0;
    }

    public void func_75251_c()
    {
        this.field_75299_d.func_70624_b((EntityLivingBase)null);
    }

    protected boolean func_75296_a(EntityLivingBase p_75296_1_, boolean p_75296_2_)
    {
        if (p_75296_1_ == null)
        {
            return false;
        }
        else if (p_75296_1_ == this.field_75299_d)
        {
            return false;
        }
        else if (!p_75296_1_.func_70089_S())
        {
            return false;
        }
        else if (!this.field_75299_d.func_70686_a(p_75296_1_.getClass()))
        {
            return false;
        }
        else
        {
            if (this.field_75299_d instanceof IEntityOwnable && StringUtils.isNotEmpty(((IEntityOwnable)this.field_75299_d).func_152113_b()))
            {
                if (p_75296_1_ instanceof IEntityOwnable && ((IEntityOwnable)this.field_75299_d).func_152113_b().equals(((IEntityOwnable)p_75296_1_).func_152113_b()))
                {
                    return false;
                }

                if (p_75296_1_ == ((IEntityOwnable)this.field_75299_d).func_70902_q())
                {
                    return false;
                }
            }
            else if (p_75296_1_ instanceof EntityPlayer && !p_75296_2_ && ((EntityPlayer)p_75296_1_).capabilities.invulnerable)
            {
                return false;
            }

            if (!this.field_75299_d.func_110176_b(MathHelper.func_76128_c(p_75296_1_.field_70165_t), MathHelper.func_76128_c(p_75296_1_.field_70163_u), MathHelper.func_76128_c(p_75296_1_.field_70161_v)))
            {
                return false;
            }
            else if (this.field_75297_f && !this.field_75299_d.func_70635_at().func_75522_a(p_75296_1_))
            {
                return false;
            }
            else
            {
                if (this.field_75303_a)
                {
                    if (--this.field_75302_c <= 0)
                    {
                        this.field_75301_b = 0;
                    }

                    if (this.field_75301_b == 0)
                    {
                        this.field_75301_b = this.func_75295_a(p_75296_1_) ? 1 : 2;
                    }

                    if (this.field_75301_b == 2)
                    {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    private boolean func_75295_a(EntityLivingBase p_75295_1_)
    {
        this.field_75302_c = 10 + this.field_75299_d.func_70681_au().nextInt(5);
        PathEntity pathentity = this.field_75299_d.func_70661_as().func_75494_a(p_75295_1_);

        if (pathentity == null)
        {
            return false;
        }
        else
        {
            PathPoint pathpoint = pathentity.func_75870_c();

            if (pathpoint == null)
            {
                return false;
            }
            else
            {
                int i = pathpoint.field_75839_a - MathHelper.func_76128_c(p_75295_1_.field_70165_t);
                int j = pathpoint.field_75838_c - MathHelper.func_76128_c(p_75295_1_.field_70161_v);
                return (double)(i * i + j * j) <= 2.25D;
            }
        }
    }
}