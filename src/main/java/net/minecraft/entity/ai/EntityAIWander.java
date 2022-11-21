package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.Vec3;

public class EntityAIWander extends EntityAIBase
{
    private EntityCreature field_75457_a;
    private double field_75455_b;
    private double field_75456_c;
    private double field_75453_d;
    private double field_75454_e;
    private static final String __OBFID = "CL_00001608";

    public EntityAIWander(EntityCreature p_i1648_1_, double p_i1648_2_)
    {
        this.field_75457_a = p_i1648_1_;
        this.field_75454_e = p_i1648_2_;
        this.func_75248_a(1);
    }

    public boolean func_75250_a()
    {
        if (this.field_75457_a.func_70654_ax() >= 100)
        {
            return false;
        }
        else if (this.field_75457_a.func_70681_au().nextInt(120) != 0)
        {
            return false;
        }
        else
        {
            Vec3 vec3 = RandomPositionGenerator.func_75463_a(this.field_75457_a, 10, 7);

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.field_75455_b = vec3.field_72450_a;
                this.field_75456_c = vec3.field_72448_b;
                this.field_75453_d = vec3.field_72449_c;
                return true;
            }
        }
    }

    public boolean func_75253_b()
    {
        return !this.field_75457_a.func_70661_as().func_75500_f();
    }

    public void func_75249_e()
    {
        this.field_75457_a.func_70661_as().func_75492_a(this.field_75455_b, this.field_75456_c, this.field_75453_d, this.field_75454_e);
    }
}