package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMonsterPlacer extends Item
{
    @SideOnly(Side.CLIENT)
    private IIcon field_94593_a;
    private static final String __OBFID = "CL_00000070";

    public ItemMonsterPlacer()
    {
        this.func_77627_a(true);
        this.func_77637_a(CreativeTabs.field_78026_f);
    }

    public String func_77653_i(ItemStack p_77653_1_)
    {
        String s = ("" + StatCollector.func_74838_a(this.func_77658_a() + ".name")).trim();
        String s1 = EntityList.func_75617_a(p_77653_1_.func_77960_j());

        if (s1 != null)
        {
            s = s + " " + StatCollector.func_74838_a("entity." + s1 + ".name");
        }

        return s;
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_)
    {
        EntityList.EntityEggInfo entityegginfo = (EntityList.EntityEggInfo)EntityList.field_75627_a.get(Integer.valueOf(p_82790_1_.func_77960_j()));
        return entityegginfo != null ? (p_82790_2_ == 0 ? entityegginfo.field_75611_b : entityegginfo.field_75612_c) : 16777215;
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_3_.field_72995_K)
        {
            return true;
        }
        else
        {
            Block block = p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
            p_77648_4_ += Facing.field_71586_b[p_77648_7_];
            p_77648_5_ += Facing.field_71587_c[p_77648_7_];
            p_77648_6_ += Facing.field_71585_d[p_77648_7_];
            double d0 = 0.0D;

            if (p_77648_7_ == 1 && block.func_149645_b() == 11)
            {
                d0 = 0.5D;
            }

            Entity entity = func_77840_a(p_77648_3_, p_77648_1_.func_77960_j(), (double)p_77648_4_ + 0.5D, (double)p_77648_5_ + d0, (double)p_77648_6_ + 0.5D);

            if (entity != null)
            {
                if (entity instanceof EntityLivingBase && p_77648_1_.func_82837_s())
                {
                    ((EntityLiving)entity).func_94058_c(p_77648_1_.func_82833_r());
                }

                if (!p_77648_2_.field_71075_bZ.field_75098_d)
                {
                    --p_77648_1_.field_77994_a;
                }
            }

            return true;
        }
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        if (p_77659_2_.field_72995_K)
        {
            return p_77659_1_;
        }
        else
        {
            MovingObjectPosition movingobjectposition = this.func_77621_a(p_77659_2_, p_77659_3_, true);

            if (movingobjectposition == null)
            {
                return p_77659_1_;
            }
            else
            {
                if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
                {
                    int i = movingobjectposition.field_72311_b;
                    int j = movingobjectposition.field_72312_c;
                    int k = movingobjectposition.field_72309_d;

                    if (!p_77659_2_.func_72962_a(p_77659_3_, i, j, k))
                    {
                        return p_77659_1_;
                    }

                    if (!p_77659_3_.func_82247_a(i, j, k, movingobjectposition.field_72310_e, p_77659_1_))
                    {
                        return p_77659_1_;
                    }

                    if (p_77659_2_.func_147439_a(i, j, k) instanceof BlockLiquid)
                    {
                        Entity entity = func_77840_a(p_77659_2_, p_77659_1_.func_77960_j(), (double)i, (double)j, (double)k);

                        if (entity != null)
                        {
                            if (entity instanceof EntityLivingBase && p_77659_1_.func_82837_s())
                            {
                                ((EntityLiving)entity).func_94058_c(p_77659_1_.func_82833_r());
                            }

                            if (!p_77659_3_.field_71075_bZ.field_75098_d)
                            {
                                --p_77659_1_.field_77994_a;
                            }
                        }
                    }
                }

                return p_77659_1_;
            }
        }
    }

    public static Entity func_77840_a(World p_77840_0_, int p_77840_1_, double p_77840_2_, double p_77840_4_, double p_77840_6_)
    {
        if (!EntityList.field_75627_a.containsKey(Integer.valueOf(p_77840_1_)))
        {
            return null;
        }
        else
        {
            Entity entity = null;

            for (int j = 0; j < 1; ++j)
            {
                entity = EntityList.func_75616_a(p_77840_1_, p_77840_0_);

                if (entity != null && entity instanceof EntityLivingBase)
                {
                    EntityLiving entityliving = (EntityLiving)entity;
                    entity.func_70012_b(p_77840_2_, p_77840_4_, p_77840_6_, MathHelper.func_76142_g(p_77840_0_.field_73012_v.nextFloat() * 360.0F), 0.0F);
                    entityliving.field_70759_as = entityliving.field_70177_z;
                    entityliving.field_70761_aq = entityliving.field_70177_z;
                    entityliving.func_110161_a((IEntityLivingData)null);
                    p_77840_0_.func_72838_d(entity);
                    entityliving.func_70642_aH();
                }
            }

            return entity;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77623_v()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77618_c(int p_77618_1_, int p_77618_2_)
    {
        return p_77618_2_ > 0 ? this.field_94593_a : super.func_77618_c(p_77618_1_, p_77618_2_);
    }

    @SideOnly(Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
        Iterator iterator = EntityList.field_75627_a.values().iterator();

        while (iterator.hasNext())
        {
            EntityList.EntityEggInfo entityegginfo = (EntityList.EntityEggInfo)iterator.next();
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, entityegginfo.field_75613_a));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_)
    {
        super.func_94581_a(p_94581_1_);
        this.field_94593_a = p_94581_1_.func_94245_a(this.func_111208_A() + "_overlay");
    }
}