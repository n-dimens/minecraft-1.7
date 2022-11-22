package net.minecraft.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityAgeable extends EntityCreature
{
    private float field_98056_d = -1.0F;
    private float field_98057_e;
    private static final String __OBFID = "CL_00001530";

    public EntityAgeable(World p_i1578_1_)
    {
        super(p_i1578_1_);
    }

    public abstract EntityAgeable func_90011_a(EntityAgeable p_90011_1_);

    public boolean resultOfImpact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getActiveItem();

        if (itemstack != null && itemstack.getBaseItem() == Items.SPAWN_EGG)
        {
            if (!this.world.field_72995_K)
            {
                Class oclass = EntityList.func_90035_a(itemstack.func_77960_j());

                if (oclass != null && oclass.isAssignableFrom(this.getClass()))
                {
                    EntityAgeable entityageable = this.func_90011_a(this);

                    if (entityageable != null)
                    {
                        entityageable.func_70873_a(-24000);
                        entityageable.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
                        this.world.func_72838_d(entityageable);

                        if (itemstack.func_82837_s())
                        {
                            entityageable.func_94058_c(itemstack.func_82833_r());
                        }

                        if (!player.capabilities.instabuild)
                        {
                            --itemstack.count;

                            if (itemstack.count <= 0)
                            {
                                player.inventory.putItem(player.inventory.activeItemPosition, (ItemStack)null);
                            }
                        }
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(12, new Integer(0));
    }

    public int func_70874_b()
    {
        return this.field_70180_af.func_75679_c(12);
    }

    public void func_110195_a(int p_110195_1_)
    {
        int j = this.func_70874_b();
        j += p_110195_1_ * 20;

        if (j > 0)
        {
            j = 0;
        }

        this.func_70873_a(j);
    }

    public void func_70873_a(int p_70873_1_)
    {
        this.field_70180_af.func_75692_b(12, Integer.valueOf(p_70873_1_));
        this.func_98054_a(this.func_70631_g_());
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74768_a("Age", this.func_70874_b());
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.func_70873_a(p_70037_1_.func_74762_e("Age"));
    }

    public void func_70636_d()
    {
        super.func_70636_d();

        if (this.world.field_72995_K)
        {
            this.func_98054_a(this.func_70631_g_());
        }
        else
        {
            int i = this.func_70874_b();

            if (i < 0)
            {
                ++i;
                this.func_70873_a(i);
            }
            else if (i > 0)
            {
                --i;
                this.func_70873_a(i);
            }
        }
    }

    public boolean func_70631_g_()
    {
        return this.func_70874_b() < 0;
    }

    public void func_98054_a(boolean p_98054_1_)
    {
        this.func_98055_j(p_98054_1_ ? 0.5F : 1.0F);
    }

    protected final void func_70105_a(float p_70105_1_, float p_70105_2_)
    {
        boolean flag = this.field_98056_d > 0.0F;
        this.field_98056_d = p_70105_1_;
        this.field_98057_e = p_70105_2_;

        if (!flag)
        {
            this.func_98055_j(1.0F);
        }
    }

    protected final void func_98055_j(float p_98055_1_)
    {
        super.func_70105_a(this.field_98056_d * p_98055_1_, this.field_98057_e * p_98055_1_);
    }
}