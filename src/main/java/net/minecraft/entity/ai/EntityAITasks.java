package net.minecraft.entity.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAITasks
{
    private static final Logger field_151506_a = LogManager.getLogger();
    public List field_75782_a = new ArrayList();
    private List field_75780_b = new ArrayList();
    private final Profiler field_75781_c;
    private int field_75778_d;
    private int field_75779_e = 3;
    private static final String __OBFID = "CL_00001588";

    public EntityAITasks(Profiler p_i1628_1_)
    {
        this.field_75781_c = p_i1628_1_;
    }

    public void func_75776_a(int p_75776_1_, EntityAIBase p_75776_2_)
    {
        this.field_75782_a.add(new EntityAITasks.EntityAITaskEntry(p_75776_1_, p_75776_2_));
    }

    public void func_85156_a(EntityAIBase p_85156_1_)
    {
        Iterator iterator = this.field_75782_a.iterator();

        while (iterator.hasNext())
        {
            EntityAITasks.EntityAITaskEntry entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();
            EntityAIBase entityaibase1 = entityaitaskentry.field_75733_a;

            if (entityaibase1 == p_85156_1_)
            {
                if (this.field_75780_b.contains(entityaitaskentry))
                {
                    entityaibase1.func_75251_c();
                    this.field_75780_b.remove(entityaitaskentry);
                }

                iterator.remove();
            }
        }
    }

    public void func_75774_a()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator;
        EntityAITasks.EntityAITaskEntry entityaitaskentry;

        if (this.field_75778_d++ % this.field_75779_e == 0)
        {
            iterator = this.field_75782_a.iterator();

            while (iterator.hasNext())
            {
                entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();
                boolean flag = this.field_75780_b.contains(entityaitaskentry);

                if (flag)
                {
                    if (this.func_75775_b(entityaitaskentry) && this.func_75773_a(entityaitaskentry))
                    {
                        continue;
                    }

                    entityaitaskentry.field_75733_a.func_75251_c();
                    this.field_75780_b.remove(entityaitaskentry);
                }

                if (this.func_75775_b(entityaitaskentry) && entityaitaskentry.field_75733_a.func_75250_a())
                {
                    arraylist.add(entityaitaskentry);
                    this.field_75780_b.add(entityaitaskentry);
                }
            }
        }
        else
        {
            iterator = this.field_75780_b.iterator();

            while (iterator.hasNext())
            {
                entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();

                if (!entityaitaskentry.field_75733_a.func_75253_b())
                {
                    entityaitaskentry.field_75733_a.func_75251_c();
                    iterator.remove();
                }
            }
        }

        this.field_75781_c.func_76320_a("goalStart");
        iterator = arraylist.iterator();

        while (iterator.hasNext())
        {
            entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();
            this.field_75781_c.func_76320_a(entityaitaskentry.field_75733_a.getClass().getSimpleName());
            entityaitaskentry.field_75733_a.func_75249_e();
            this.field_75781_c.func_76319_b();
        }

        this.field_75781_c.func_76319_b();
        this.field_75781_c.func_76320_a("goalTick");
        iterator = this.field_75780_b.iterator();

        while (iterator.hasNext())
        {
            entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();
            entityaitaskentry.field_75733_a.func_75246_d();
        }

        this.field_75781_c.func_76319_b();
    }

    private boolean func_75773_a(EntityAITasks.EntityAITaskEntry p_75773_1_)
    {
        this.field_75781_c.func_76320_a("canContinue");
        boolean flag = p_75773_1_.field_75733_a.func_75253_b();
        this.field_75781_c.func_76319_b();
        return flag;
    }

    private boolean func_75775_b(EntityAITasks.EntityAITaskEntry p_75775_1_)
    {
        this.field_75781_c.func_76320_a("canUse");
        Iterator iterator = this.field_75782_a.iterator();

        while (iterator.hasNext())
        {
            EntityAITasks.EntityAITaskEntry entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();

            if (entityaitaskentry != p_75775_1_)
            {
                if (p_75775_1_.field_75731_b >= entityaitaskentry.field_75731_b)
                {
                    if (this.field_75780_b.contains(entityaitaskentry) && !this.func_75777_a(p_75775_1_, entityaitaskentry))
                    {
                        this.field_75781_c.func_76319_b();
                        return false;
                    }
                }
                else if (this.field_75780_b.contains(entityaitaskentry) && !entityaitaskentry.field_75733_a.func_75252_g())
                {
                    this.field_75781_c.func_76319_b();
                    return false;
                }
            }
        }

        this.field_75781_c.func_76319_b();
        return true;
    }

    private boolean func_75777_a(EntityAITasks.EntityAITaskEntry p_75777_1_, EntityAITasks.EntityAITaskEntry p_75777_2_)
    {
        return (p_75777_1_.field_75733_a.func_75247_h() & p_75777_2_.field_75733_a.func_75247_h()) == 0;
    }

    public class EntityAITaskEntry
    {
        public EntityAIBase field_75733_a;
        public int field_75731_b;
        private static final String __OBFID = "CL_00001589";

        public EntityAITaskEntry(int p_i1627_2_, EntityAIBase p_i1627_3_)
        {
            this.field_75731_b = p_i1627_2_;
            this.field_75733_a = p_i1627_3_;
        }
    }
}