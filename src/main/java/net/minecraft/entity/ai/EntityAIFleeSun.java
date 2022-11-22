package net.minecraft.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAIFleeSun extends EntityAIBase
{
    private EntityCreature field_75372_a;
    private double field_75370_b;
    private double field_75371_c;
    private double field_75368_d;
    private double field_75369_e;
    private World field_75367_f;
    private static final String __OBFID = "CL_00001583";

    public EntityAIFleeSun(EntityCreature p_i1623_1_, double p_i1623_2_)
    {
        this.field_75372_a = p_i1623_1_;
        this.field_75369_e = p_i1623_2_;
        this.field_75367_f = p_i1623_1_.world;
        this.func_75248_a(1);
    }

    public boolean func_75250_a()
    {
        if (!this.field_75367_f.func_72935_r())
        {
            return false;
        }
        else if (!this.field_75372_a.func_70027_ad())
        {
            return false;
        }
        else if (!this.field_75367_f.func_72937_j(MathHelper.func_76128_c(this.field_75372_a.field_70165_t), (int)this.field_75372_a.field_70121_D.field_72338_b, MathHelper.func_76128_c(this.field_75372_a.field_70161_v)))
        {
            return false;
        }
        else
        {
            Vec3 vec3 = this.func_75366_f();

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.field_75370_b = vec3.field_72450_a;
                this.field_75371_c = vec3.field_72448_b;
                this.field_75368_d = vec3.field_72449_c;
                return true;
            }
        }
    }

    public boolean func_75253_b()
    {
        return !this.field_75372_a.func_70661_as().func_75500_f();
    }

    public void func_75249_e()
    {
        this.field_75372_a.func_70661_as().func_75492_a(this.field_75370_b, this.field_75371_c, this.field_75368_d, this.field_75369_e);
    }

    private Vec3 func_75366_f()
    {
        Random random = this.field_75372_a.func_70681_au();

        for (int i = 0; i < 10; ++i)
        {
            int j = MathHelper.func_76128_c(this.field_75372_a.field_70165_t + (double)random.nextInt(20) - 10.0D);
            int k = MathHelper.func_76128_c(this.field_75372_a.field_70121_D.field_72338_b + (double)random.nextInt(6) - 3.0D);
            int l = MathHelper.func_76128_c(this.field_75372_a.field_70161_v + (double)random.nextInt(20) - 10.0D);

            if (!this.field_75367_f.func_72937_j(j, k, l) && this.field_75372_a.func_70783_a(j, k, l) < 0.0F)
            {
                return Vec3.func_72443_a((double)j, (double)k, (double)l);
            }
        }

        return null;
    }
}