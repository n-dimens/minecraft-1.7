package net.minecraft.entity.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAIMoveThroughVillage extends EntityAIBase
{
    private EntityCreature field_75420_a;
    private double field_75418_b;
    private PathEntity field_75419_c;
    private VillageDoorInfo field_75416_d;
    private boolean field_75417_e;
    private List field_75415_f = new ArrayList();
    private static final String __OBFID = "CL_00001597";

    public EntityAIMoveThroughVillage(EntityCreature p_i1638_1_, double p_i1638_2_, boolean p_i1638_4_)
    {
        this.field_75420_a = p_i1638_1_;
        this.field_75418_b = p_i1638_2_;
        this.field_75417_e = p_i1638_4_;
        this.func_75248_a(1);
    }

    public boolean func_75250_a()
    {
        this.func_75414_f();

        if (this.field_75417_e && this.field_75420_a.world.func_72935_r())
        {
            return false;
        }
        else
        {
            Village village = this.field_75420_a.world.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.field_75420_a.field_70165_t), MathHelper.func_76128_c(this.field_75420_a.field_70163_u), MathHelper.func_76128_c(this.field_75420_a.field_70161_v), 0);

            if (village == null)
            {
                return false;
            }
            else
            {
                this.field_75416_d = this.func_75412_a(village);

                if (this.field_75416_d == null)
                {
                    return false;
                }
                else
                {
                    boolean flag = this.field_75420_a.func_70661_as().func_75507_c();
                    this.field_75420_a.func_70661_as().func_75498_b(false);
                    this.field_75419_c = this.field_75420_a.func_70661_as().func_75488_a((double)this.field_75416_d.field_75481_a, (double)this.field_75416_d.field_75479_b, (double)this.field_75416_d.field_75480_c);
                    this.field_75420_a.func_70661_as().func_75498_b(flag);

                    if (this.field_75419_c != null)
                    {
                        return true;
                    }
                    else
                    {
                        Vec3 vec3 = RandomPositionGenerator.func_75464_a(this.field_75420_a, 10, 7, Vec3.func_72443_a((double)this.field_75416_d.field_75481_a, (double)this.field_75416_d.field_75479_b, (double)this.field_75416_d.field_75480_c));

                        if (vec3 == null)
                        {
                            return false;
                        }
                        else
                        {
                            this.field_75420_a.func_70661_as().func_75498_b(false);
                            this.field_75419_c = this.field_75420_a.func_70661_as().func_75488_a(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c);
                            this.field_75420_a.func_70661_as().func_75498_b(flag);
                            return this.field_75419_c != null;
                        }
                    }
                }
            }
        }
    }

    public boolean func_75253_b()
    {
        if (this.field_75420_a.func_70661_as().func_75500_f())
        {
            return false;
        }
        else
        {
            float f = this.field_75420_a.field_70130_N + 4.0F;
            return this.field_75420_a.func_70092_e((double)this.field_75416_d.field_75481_a, (double)this.field_75416_d.field_75479_b, (double)this.field_75416_d.field_75480_c) > (double)(f * f);
        }
    }

    public void func_75249_e()
    {
        this.field_75420_a.func_70661_as().func_75484_a(this.field_75419_c, this.field_75418_b);
    }

    public void func_75251_c()
    {
        if (this.field_75420_a.func_70661_as().func_75500_f() || this.field_75420_a.func_70092_e((double)this.field_75416_d.field_75481_a, (double)this.field_75416_d.field_75479_b, (double)this.field_75416_d.field_75480_c) < 16.0D)
        {
            this.field_75415_f.add(this.field_75416_d);
        }
    }

    private VillageDoorInfo func_75412_a(Village p_75412_1_)
    {
        VillageDoorInfo villagedoorinfo = null;
        int i = Integer.MAX_VALUE;
        List list = p_75412_1_.func_75558_f();
        Iterator iterator = list.iterator();

        while (iterator.hasNext())
        {
            VillageDoorInfo villagedoorinfo1 = (VillageDoorInfo)iterator.next();
            int j = villagedoorinfo1.func_75474_b(MathHelper.func_76128_c(this.field_75420_a.field_70165_t), MathHelper.func_76128_c(this.field_75420_a.field_70163_u), MathHelper.func_76128_c(this.field_75420_a.field_70161_v));

            if (j < i && !this.func_75413_a(villagedoorinfo1))
            {
                villagedoorinfo = villagedoorinfo1;
                i = j;
            }
        }

        return villagedoorinfo;
    }

    private boolean func_75413_a(VillageDoorInfo p_75413_1_)
    {
        Iterator iterator = this.field_75415_f.iterator();
        VillageDoorInfo villagedoorinfo1;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            villagedoorinfo1 = (VillageDoorInfo)iterator.next();
        }
        while (p_75413_1_.field_75481_a != villagedoorinfo1.field_75481_a || p_75413_1_.field_75479_b != villagedoorinfo1.field_75479_b || p_75413_1_.field_75480_c != villagedoorinfo1.field_75480_c);

        return true;
    }

    private void func_75414_f()
    {
        if (this.field_75415_f.size() > 15)
        {
            this.field_75415_f.remove(0);
        }
    }
}