package net.minecraft.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityLeashKnot extends EntityHanging
{
    private static final String __OBFID = "CL_00001548";

    public EntityLeashKnot(World p_i1592_1_)
    {
        super(p_i1592_1_);
    }

    public EntityLeashKnot(World p_i1593_1_, int p_i1593_2_, int p_i1593_3_, int p_i1593_4_)
    {
        super(p_i1593_1_, p_i1593_2_, p_i1593_3_, p_i1593_4_, 0);
        this.func_70107_b((double)p_i1593_2_ + 0.5D, (double)p_i1593_3_ + 0.5D, (double)p_i1593_4_ + 0.5D);
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
    }

    public void func_82328_a(int p_82328_1_) {}

    public int func_82329_d()
    {
        return 9;
    }

    public int func_82330_g()
    {
        return 9;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_70112_a(double p_70112_1_)
    {
        return p_70112_1_ < 1024.0D;
    }

    public void func_110128_b(Entity p_110128_1_) {}

    public boolean func_70039_c(NBTTagCompound p_70039_1_)
    {
        return false;
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {}

    public void func_70037_a(NBTTagCompound p_70037_1_) {}

    public boolean func_130002_c(EntityPlayer p_130002_1_)
    {
        ItemStack itemstack = p_130002_1_.func_70694_bm();
        boolean flag = false;
        double d0;
        List list;
        Iterator iterator;
        EntityLiving entityliving;

        if (itemstack != null && itemstack.func_77973_b() == Items.field_151058_ca && !this.field_70170_p.field_72995_K)
        {
            d0 = 7.0D;
            list = this.field_70170_p.func_72872_a(EntityLiving.class, AxisAlignedBB.func_72330_a(this.field_70165_t - d0, this.field_70163_u - d0, this.field_70161_v - d0, this.field_70165_t + d0, this.field_70163_u + d0, this.field_70161_v + d0));

            if (list != null)
            {
                iterator = list.iterator();

                while (iterator.hasNext())
                {
                    entityliving = (EntityLiving)iterator.next();

                    if (entityliving.func_110167_bD() && entityliving.func_110166_bE() == p_130002_1_)
                    {
                        entityliving.func_110162_b(this, true);
                        flag = true;
                    }
                }
            }
        }

        if (!this.field_70170_p.field_72995_K && !flag)
        {
            this.func_70106_y();

            if (p_130002_1_.field_71075_bZ.field_75098_d)
            {
                d0 = 7.0D;
                list = this.field_70170_p.func_72872_a(EntityLiving.class, AxisAlignedBB.func_72330_a(this.field_70165_t - d0, this.field_70163_u - d0, this.field_70161_v - d0, this.field_70165_t + d0, this.field_70163_u + d0, this.field_70161_v + d0));

                if (list != null)
                {
                    iterator = list.iterator();

                    while (iterator.hasNext())
                    {
                        entityliving = (EntityLiving)iterator.next();

                        if (entityliving.func_110167_bD() && entityliving.func_110166_bE() == this)
                        {
                            entityliving.func_110160_i(true, false);
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean func_70518_d()
    {
        return this.field_70170_p.func_147439_a(this.field_146063_b, this.field_146064_c, this.field_146062_d).func_149645_b() == 11;
    }

    public static EntityLeashKnot func_110129_a(World p_110129_0_, int p_110129_1_, int p_110129_2_, int p_110129_3_)
    {
        EntityLeashKnot entityleashknot = new EntityLeashKnot(p_110129_0_, p_110129_1_, p_110129_2_, p_110129_3_);
        entityleashknot.field_98038_p = true;
        p_110129_0_.func_72838_d(entityleashknot);
        return entityleashknot;
    }

    public static EntityLeashKnot func_110130_b(World p_110130_0_, int p_110130_1_, int p_110130_2_, int p_110130_3_)
    {
        List list = p_110130_0_.func_72872_a(EntityLeashKnot.class, AxisAlignedBB.func_72330_a((double)p_110130_1_ - 1.0D, (double)p_110130_2_ - 1.0D, (double)p_110130_3_ - 1.0D, (double)p_110130_1_ + 1.0D, (double)p_110130_2_ + 1.0D, (double)p_110130_3_ + 1.0D));

        if (list != null)
        {
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityLeashKnot entityleashknot = (EntityLeashKnot)iterator.next();

                if (entityleashknot.field_146063_b == p_110130_1_ && entityleashknot.field_146064_c == p_110130_2_ && entityleashknot.field_146062_d == p_110130_3_)
                {
                    return entityleashknot;
                }
            }
        }

        return null;
    }
}