package net.minecraft.entity.ai;

import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

public class EntityAIAvoidEntity extends EntityAIBase
{
    public final IEntitySelector field_98218_a = new IEntitySelector()
    {
        private static final String __OBFID = "CL_00001575";
        public boolean func_82704_a(Entity p_82704_1_)
        {
            return p_82704_1_.func_70089_S() && EntityAIAvoidEntity.this.field_75380_a.func_70635_at().func_75522_a(p_82704_1_);
        }
    };
    private EntityCreature field_75380_a;
    private double field_75378_b;
    private double field_75379_c;
    private Entity field_75376_d;
    private float field_75377_e;
    private PathEntity field_75374_f;
    private PathNavigate field_75375_g;
    private Class field_75381_h;
    private static final String __OBFID = "CL_00001574";

    public EntityAIAvoidEntity(EntityCreature p_i1616_1_, Class p_i1616_2_, float p_i1616_3_, double p_i1616_4_, double p_i1616_6_)
    {
        this.field_75380_a = p_i1616_1_;
        this.field_75381_h = p_i1616_2_;
        this.field_75377_e = p_i1616_3_;
        this.field_75378_b = p_i1616_4_;
        this.field_75379_c = p_i1616_6_;
        this.field_75375_g = p_i1616_1_.func_70661_as();
        this.func_75248_a(1);
    }

    public boolean func_75250_a()
    {
        if (this.field_75381_h == EntityPlayer.class)
        {
            if (this.field_75380_a instanceof EntityTameable && ((EntityTameable)this.field_75380_a).func_70909_n())
            {
                return false;
            }

            this.field_75376_d = this.field_75380_a.world.func_72890_a(this.field_75380_a, (double)this.field_75377_e);

            if (this.field_75376_d == null)
            {
                return false;
            }
        }
        else
        {
            List list = this.field_75380_a.world.func_82733_a(this.field_75381_h, this.field_75380_a.field_70121_D.func_72314_b((double)this.field_75377_e, 3.0D, (double)this.field_75377_e), this.field_98218_a);

            if (list.isEmpty())
            {
                return false;
            }

            this.field_75376_d = (Entity)list.get(0);
        }

        Vec3 vec3 = RandomPositionGenerator.func_75461_b(this.field_75380_a, 16, 7, Vec3.func_72443_a(this.field_75376_d.field_70165_t, this.field_75376_d.field_70163_u, this.field_75376_d.field_70161_v));

        if (vec3 == null)
        {
            return false;
        }
        else if (this.field_75376_d.func_70092_e(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c) < this.field_75376_d.func_70068_e(this.field_75380_a))
        {
            return false;
        }
        else
        {
            this.field_75374_f = this.field_75375_g.func_75488_a(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c);
            return this.field_75374_f == null ? false : this.field_75374_f.func_75880_b(vec3);
        }
    }

    public boolean func_75253_b()
    {
        return !this.field_75375_g.func_75500_f();
    }

    public void func_75249_e()
    {
        this.field_75375_g.func_75484_a(this.field_75374_f, this.field_75378_b);
    }

    public void func_75251_c()
    {
        this.field_75376_d = null;
    }

    public void func_75246_d()
    {
        if (this.field_75380_a.func_70068_e(this.field_75376_d) < 49.0D)
        {
            this.field_75380_a.func_70661_as().func_75489_a(this.field_75379_c);
        }
        else
        {
            this.field_75380_a.func_70661_as().func_75489_a(this.field_75378_b);
        }
    }
}