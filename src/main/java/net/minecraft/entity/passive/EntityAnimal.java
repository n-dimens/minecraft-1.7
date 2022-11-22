package net.minecraft.entity.passive;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityAnimal extends EntityAgeable implements IAnimals
{
    private int field_70881_d;
    private int field_70882_e;
    private EntityPlayer field_146084_br;
    private static final String __OBFID = "CL_00001638";

    public EntityAnimal(World p_i1681_1_)
    {
        super(p_i1681_1_);
    }

    protected void func_70629_bd()
    {
        if (this.func_70874_b() != 0)
        {
            this.field_70881_d = 0;
        }

        super.func_70629_bd();
    }

    public void func_70636_d()
    {
        super.func_70636_d();

        if (this.func_70874_b() != 0)
        {
            this.field_70881_d = 0;
        }

        if (this.field_70881_d > 0)
        {
            --this.field_70881_d;
            String s = "heart";

            if (this.field_70881_d % 10 == 0)
            {
                double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
                this.world.func_72869_a(s, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 0.5D + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, d0, d1, d2);
            }
        }
        else
        {
            this.field_70882_e = 0;
        }
    }

    protected void func_70785_a(Entity p_70785_1_, float p_70785_2_)
    {
        if (p_70785_1_ instanceof EntityPlayer)
        {
            if (p_70785_2_ < 3.0F)
            {
                double d0 = p_70785_1_.field_70165_t - this.field_70165_t;
                double d1 = p_70785_1_.field_70161_v - this.field_70161_v;
                this.field_70177_z = (float)(Math.atan2(d1, d0) * 180.0D / Math.PI) - 90.0F;
                this.field_70787_b = true;
            }

            EntityPlayer entityplayer = (EntityPlayer)p_70785_1_;

            if (entityplayer.func_71045_bC() == null || !this.func_70877_b(entityplayer.func_71045_bC()))
            {
                this.field_70789_a = null;
            }
        }
        else if (p_70785_1_ instanceof EntityAnimal)
        {
            EntityAnimal entityanimal = (EntityAnimal)p_70785_1_;

            if (this.func_70874_b() > 0 && entityanimal.func_70874_b() < 0)
            {
                if ((double)p_70785_2_ < 2.5D)
                {
                    this.field_70787_b = true;
                }
            }
            else if (this.field_70881_d > 0 && entityanimal.field_70881_d > 0)
            {
                if (entityanimal.field_70789_a == null)
                {
                    entityanimal.field_70789_a = this;
                }

                if (entityanimal.field_70789_a == this && (double)p_70785_2_ < 3.5D)
                {
                    ++entityanimal.field_70881_d;
                    ++this.field_70881_d;
                    ++this.field_70882_e;

                    if (this.field_70882_e % 4 == 0)
                    {
                        this.world.func_72869_a("heart", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 0.5D + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, 0.0D, 0.0D, 0.0D);
                    }

                    if (this.field_70882_e == 60)
                    {
                        this.func_70876_c((EntityAnimal)p_70785_1_);
                    }
                }
                else
                {
                    this.field_70882_e = 0;
                }
            }
            else
            {
                this.field_70882_e = 0;
                this.field_70789_a = null;
            }
        }
    }

    private void func_70876_c(EntityAnimal p_70876_1_)
    {
        EntityAgeable entityageable = this.func_90011_a(p_70876_1_);

        if (entityageable != null)
        {
            if (this.field_146084_br == null && p_70876_1_.func_146083_cb() != null)
            {
                this.field_146084_br = p_70876_1_.func_146083_cb();
            }

            if (this.field_146084_br != null)
            {
                this.field_146084_br.func_71029_a(StatList.ANIMALS_BRED);

                if (this instanceof EntityCow)
                {
                    this.field_146084_br.func_71029_a(AchievementList.BREED_COW);
                }
            }

            this.func_70873_a(6000);
            p_70876_1_.func_70873_a(6000);
            this.field_70881_d = 0;
            this.field_70882_e = 0;
            this.field_70789_a = null;
            p_70876_1_.field_70789_a = null;
            p_70876_1_.field_70882_e = 0;
            p_70876_1_.field_70881_d = 0;
            entityageable.func_70873_a(-24000);
            entityageable.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);

            for (int i = 0; i < 7; ++i)
            {
                double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
                this.world.func_72869_a("heart", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 0.5D + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, d0, d1, d2);
            }

            this.world.func_72838_d(entityageable);
        }
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else
        {
            this.field_70788_c = 60;

            if (!this.func_70650_aV())
            {
                IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);

                if (iattributeinstance.func_111127_a(field_110179_h) == null)
                {
                    iattributeinstance.func_111121_a(field_110181_i);
                }
            }

            this.field_70789_a = null;
            this.field_70881_d = 0;
            return super.func_70097_a(p_70097_1_, p_70097_2_);
        }
    }

    public float func_70783_a(int p_70783_1_, int p_70783_2_, int p_70783_3_)
    {
        return this.world.func_147439_a(p_70783_1_, p_70783_2_ - 1, p_70783_3_) == Blocks.GRASS ? 10.0F : this.world.func_72801_o(p_70783_1_, p_70783_2_, p_70783_3_) - 0.5F;
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74768_a("InLove", this.field_70881_d);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.field_70881_d = p_70037_1_.func_74762_e("InLove");
    }

    protected Entity func_70782_k()
    {
        if (this.field_70788_c > 0)
        {
            return null;
        }
        else
        {
            float f = 8.0F;
            List list;
            int i;
            EntityAnimal entityanimal;

            if (this.field_70881_d > 0)
            {
                list = this.world.func_72872_a(this.getClass(), this.field_70121_D.func_72314_b((double)f, (double)f, (double)f));

                for (i = 0; i < list.size(); ++i)
                {
                    entityanimal = (EntityAnimal)list.get(i);

                    if (entityanimal != this && entityanimal.field_70881_d > 0)
                    {
                        return entityanimal;
                    }
                }
            }
            else if (this.func_70874_b() == 0)
            {
                list = this.world.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b((double)f, (double)f, (double)f));

                for (i = 0; i < list.size(); ++i)
                {
                    EntityPlayer entityplayer = (EntityPlayer)list.get(i);

                    if (entityplayer.func_71045_bC() != null && this.func_70877_b(entityplayer.func_71045_bC()))
                    {
                        return entityplayer;
                    }
                }
            }
            else if (this.func_70874_b() > 0)
            {
                list = this.world.func_72872_a(this.getClass(), this.field_70121_D.func_72314_b((double)f, (double)f, (double)f));

                for (i = 0; i < list.size(); ++i)
                {
                    entityanimal = (EntityAnimal)list.get(i);

                    if (entityanimal != this && entityanimal.func_70874_b() < 0)
                    {
                        return entityanimal;
                    }
                }
            }

            return null;
        }
    }

    public boolean func_70601_bi()
    {
        int i = MathHelper.func_76128_c(this.field_70165_t);
        int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
        int k = MathHelper.func_76128_c(this.field_70161_v);
        return this.world.func_147439_a(i, j - 1, k) == Blocks.GRASS && this.world.func_72883_k(i, j, k) > 8 && super.func_70601_bi();
    }

    public int func_70627_aG()
    {
        return 120;
    }

    protected boolean func_70692_ba()
    {
        return false;
    }

    protected int func_70693_a(EntityPlayer p_70693_1_)
    {
        return 1 + this.world.field_73012_v.nextInt(3);
    }

    public boolean func_70877_b(ItemStack p_70877_1_)
    {
        return p_70877_1_.func_77973_b() == Items.WHEAT;
    }

    public boolean func_70085_c(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();

        if (itemstack != null && this.func_70877_b(itemstack) && this.func_70874_b() == 0 && this.field_70881_d <= 0)
        {
            if (!p_70085_1_.field_71075_bZ.field_75098_d)
            {
                --itemstack.field_77994_a;

                if (itemstack.field_77994_a <= 0)
                {
                    p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack)null);
                }
            }

            this.func_146082_f(p_70085_1_);
            return true;
        }
        else
        {
            return super.func_70085_c(p_70085_1_);
        }
    }

    public void func_146082_f(EntityPlayer p_146082_1_)
    {
        this.field_70881_d = 600;
        this.field_146084_br = p_146082_1_;
        this.field_70789_a = null;
        this.world.func_72960_a(this, (byte)18);
    }

    public EntityPlayer func_146083_cb()
    {
        return this.field_146084_br;
    }

    public boolean func_70880_s()
    {
        return this.field_70881_d > 0;
    }

    public void func_70875_t()
    {
        this.field_70881_d = 0;
    }

    public boolean func_70878_b(EntityAnimal p_70878_1_)
    {
        return p_70878_1_ == this ? false : (p_70878_1_.getClass() != this.getClass() ? false : this.func_70880_s() && p_70878_1_.func_70880_s());
    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte p_70103_1_)
    {
        if (p_70103_1_ == 18)
        {
            for (int i = 0; i < 7; ++i)
            {
                double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
                this.world.func_72869_a("heart", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 0.5D + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, d0, d1, d2);
            }
        }
        else
        {
            super.func_70103_a(p_70103_1_);
        }
    }
}