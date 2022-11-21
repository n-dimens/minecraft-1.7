package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;

public abstract class EntityAIDoorInteract extends EntityAIBase
{
    protected EntityLiving field_75356_a;
    protected int field_75354_b;
    protected int field_75355_c;
    protected int field_75352_d;
    protected BlockDoor field_151504_e;
    boolean field_75350_f;
    float field_75351_g;
    float field_75357_h;
    private static final String __OBFID = "CL_00001581";

    public EntityAIDoorInteract(EntityLiving p_i1621_1_)
    {
        this.field_75356_a = p_i1621_1_;
    }

    public boolean func_75250_a()
    {
        if (!this.field_75356_a.field_70123_F)
        {
            return false;
        }
        else
        {
            PathNavigate pathnavigate = this.field_75356_a.func_70661_as();
            PathEntity pathentity = pathnavigate.func_75505_d();

            if (pathentity != null && !pathentity.func_75879_b() && pathnavigate.func_75507_c())
            {
                for (int i = 0; i < Math.min(pathentity.func_75873_e() + 2, pathentity.func_75874_d()); ++i)
                {
                    PathPoint pathpoint = pathentity.func_75877_a(i);
                    this.field_75354_b = pathpoint.field_75839_a;
                    this.field_75355_c = pathpoint.field_75837_b + 1;
                    this.field_75352_d = pathpoint.field_75838_c;

                    if (this.field_75356_a.func_70092_e((double)this.field_75354_b, this.field_75356_a.field_70163_u, (double)this.field_75352_d) <= 2.25D)
                    {
                        this.field_151504_e = this.func_151503_a(this.field_75354_b, this.field_75355_c, this.field_75352_d);

                        if (this.field_151504_e != null)
                        {
                            return true;
                        }
                    }
                }

                this.field_75354_b = MathHelper.func_76128_c(this.field_75356_a.field_70165_t);
                this.field_75355_c = MathHelper.func_76128_c(this.field_75356_a.field_70163_u + 1.0D);
                this.field_75352_d = MathHelper.func_76128_c(this.field_75356_a.field_70161_v);
                this.field_151504_e = this.func_151503_a(this.field_75354_b, this.field_75355_c, this.field_75352_d);
                return this.field_151504_e != null;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean func_75253_b()
    {
        return !this.field_75350_f;
    }

    public void func_75249_e()
    {
        this.field_75350_f = false;
        this.field_75351_g = (float)((double)((float)this.field_75354_b + 0.5F) - this.field_75356_a.field_70165_t);
        this.field_75357_h = (float)((double)((float)this.field_75352_d + 0.5F) - this.field_75356_a.field_70161_v);
    }

    public void func_75246_d()
    {
        float f = (float)((double)((float)this.field_75354_b + 0.5F) - this.field_75356_a.field_70165_t);
        float f1 = (float)((double)((float)this.field_75352_d + 0.5F) - this.field_75356_a.field_70161_v);
        float f2 = this.field_75351_g * f + this.field_75357_h * f1;

        if (f2 < 0.0F)
        {
            this.field_75350_f = true;
        }
    }

    private BlockDoor func_151503_a(int p_151503_1_, int p_151503_2_, int p_151503_3_)
    {
        Block block = this.field_75356_a.field_70170_p.func_147439_a(p_151503_1_, p_151503_2_, p_151503_3_);
        return block != Blocks.field_150466_ao ? null : (BlockDoor)block;
    }
}