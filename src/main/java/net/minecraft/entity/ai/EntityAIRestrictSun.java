package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;

public class EntityAIRestrictSun extends EntityAIBase
{
    private EntityCreature field_75273_a;
    private static final String __OBFID = "CL_00001611";

    public EntityAIRestrictSun(EntityCreature p_i1652_1_)
    {
        this.field_75273_a = p_i1652_1_;
    }

    public boolean func_75250_a()
    {
        return this.field_75273_a.world.func_72935_r();
    }

    public void func_75249_e()
    {
        this.field_75273_a.func_70661_as().func_75504_d(true);
    }

    public void func_75251_c()
    {
        this.field_75273_a.func_70661_as().func_75504_d(false);
    }
}