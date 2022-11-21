package net.minecraft.entity.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPotion extends EntityThrowable
{
    private ItemStack field_70197_d;
    private static final String __OBFID = "CL_00001727";

    public EntityPotion(World p_i1788_1_)
    {
        super(p_i1788_1_);
    }

    public EntityPotion(World p_i1789_1_, EntityLivingBase p_i1789_2_, int p_i1789_3_)
    {
        this(p_i1789_1_, p_i1789_2_, new ItemStack(Items.field_151068_bn, 1, p_i1789_3_));
    }

    public EntityPotion(World p_i1790_1_, EntityLivingBase p_i1790_2_, ItemStack p_i1790_3_)
    {
        super(p_i1790_1_, p_i1790_2_);
        this.field_70197_d = p_i1790_3_;
    }

    @SideOnly(Side.CLIENT)
    public EntityPotion(World p_i1791_1_, double p_i1791_2_, double p_i1791_4_, double p_i1791_6_, int p_i1791_8_)
    {
        this(p_i1791_1_, p_i1791_2_, p_i1791_4_, p_i1791_6_, new ItemStack(Items.field_151068_bn, 1, p_i1791_8_));
    }

    public EntityPotion(World p_i1792_1_, double p_i1792_2_, double p_i1792_4_, double p_i1792_6_, ItemStack p_i1792_8_)
    {
        super(p_i1792_1_, p_i1792_2_, p_i1792_4_, p_i1792_6_);
        this.field_70197_d = p_i1792_8_;
    }

    protected float func_70185_h()
    {
        return 0.05F;
    }

    protected float func_70182_d()
    {
        return 0.5F;
    }

    protected float func_70183_g()
    {
        return -20.0F;
    }

    public void func_82340_a(int p_82340_1_)
    {
        if (this.field_70197_d == null)
        {
            this.field_70197_d = new ItemStack(Items.field_151068_bn, 1, 0);
        }

        this.field_70197_d.func_77964_b(p_82340_1_);
    }

    public int func_70196_i()
    {
        if (this.field_70197_d == null)
        {
            this.field_70197_d = new ItemStack(Items.field_151068_bn, 1, 0);
        }

        return this.field_70197_d.func_77960_j();
    }

    protected void func_70184_a(MovingObjectPosition p_70184_1_)
    {
        if (!this.field_70170_p.field_72995_K)
        {
            List list = Items.field_151068_bn.func_77832_l(this.field_70197_d);

            if (list != null && !list.isEmpty())
            {
                AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(4.0D, 2.0D, 4.0D);
                List list1 = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);

                if (list1 != null && !list1.isEmpty())
                {
                    Iterator iterator = list1.iterator();

                    while (iterator.hasNext())
                    {
                        EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
                        double d0 = this.func_70068_e(entitylivingbase);

                        if (d0 < 16.0D)
                        {
                            double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

                            if (entitylivingbase == p_70184_1_.field_72308_g)
                            {
                                d1 = 1.0D;
                            }

                            Iterator iterator1 = list.iterator();

                            while (iterator1.hasNext())
                            {
                                PotionEffect potioneffect = (PotionEffect)iterator1.next();
                                int i = potioneffect.func_76456_a();

                                if (Potion.field_76425_a[i].func_76403_b())
                                {
                                    Potion.field_76425_a[i].func_76402_a(this.func_85052_h(), entitylivingbase, potioneffect.func_76458_c(), d1);
                                }
                                else
                                {
                                    int j = (int)(d1 * (double)potioneffect.func_76459_b() + 0.5D);

                                    if (j > 20)
                                    {
                                        entitylivingbase.func_70690_d(new PotionEffect(i, j, potioneffect.func_76458_c()));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            this.field_70170_p.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v), this.func_70196_i());
            this.func_70106_y();
        }
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);

        if (p_70037_1_.func_150297_b("Potion", 10))
        {
            this.field_70197_d = ItemStack.func_77949_a(p_70037_1_.func_74775_l("Potion"));
        }
        else
        {
            this.func_82340_a(p_70037_1_.func_74762_e("potionValue"));
        }

        if (this.field_70197_d == null)
        {
            this.func_70106_y();
        }
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);

        if (this.field_70197_d != null)
        {
            p_70014_1_.func_74782_a("Potion", this.field_70197_d.func_77955_b(new NBTTagCompound()));
        }
    }
}