package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;

public class EntityAIOpenDoor extends EntityAIDoorInteract
{
    boolean field_75361_i;
    int field_75360_j;
    private static final String __OBFID = "CL_00001603";

    public EntityAIOpenDoor(EntityLiving p_i1644_1_, boolean p_i1644_2_)
    {
        super(p_i1644_1_);
        this.field_75356_a = p_i1644_1_;
        this.field_75361_i = p_i1644_2_;
    }

    public boolean func_75253_b()
    {
        return this.field_75361_i && this.field_75360_j > 0 && super.func_75253_b();
    }

    public void func_75249_e()
    {
        this.field_75360_j = 20;
        this.field_151504_e.func_150014_a(this.field_75356_a.world, this.field_75354_b, this.field_75355_c, this.field_75352_d, true);
    }

    public void func_75251_c()
    {
        if (this.field_75361_i)
        {
            this.field_151504_e.func_150014_a(this.field_75356_a.world, this.field_75354_b, this.field_75355_c, this.field_75352_d, false);
        }
    }

    public void func_75246_d()
    {
        --this.field_75360_j;
        super.func_75246_d();
    }
}