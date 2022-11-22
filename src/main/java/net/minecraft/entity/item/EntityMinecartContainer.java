package net.minecraft.entity.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityMinecartContainer extends EntityMinecart implements IInventory
{
    private ItemStack[] field_94113_a = new ItemStack[36];
    private boolean field_94112_b = true;
    private static final String __OBFID = "CL_00001674";

    public EntityMinecartContainer(World p_i1716_1_)
    {
        super(p_i1716_1_);
    }

    public EntityMinecartContainer(World p_i1717_1_, double p_i1717_2_, double p_i1717_4_, double p_i1717_6_)
    {
        super(p_i1717_1_, p_i1717_2_, p_i1717_4_, p_i1717_6_);
    }

    public void func_94095_a(DamageSource p_94095_1_)
    {
        super.func_94095_a(p_94095_1_);

        for (int i = 0; i < this.func_70302_i_(); ++i)
        {
            ItemStack itemstack = this.func_70301_a(i);

            if (itemstack != null)
            {
                float f = this.randomizer.nextFloat() * 0.8F + 0.1F;
                float f1 = this.randomizer.nextFloat() * 0.8F + 0.1F;
                float f2 = this.randomizer.nextFloat() * 0.8F + 0.1F;

                while (itemstack.count > 0)
                {
                    int j = this.randomizer.nextInt(21) + 10;

                    if (j > itemstack.count)
                    {
                        j = itemstack.count;
                    }

                    itemstack.count -= j;
                    EntityItem entityitem = new EntityItem(this.world, this.field_70165_t + (double)f, this.field_70163_u + (double)f1, this.field_70161_v + (double)f2, new ItemStack(itemstack.getBaseItem(), j, itemstack.func_77960_j()));
                    float f3 = 0.05F;
                    entityitem.field_70159_w = (double)((float)this.randomizer.nextGaussian() * f3);
                    entityitem.field_70181_x = (double)((float)this.randomizer.nextGaussian() * f3 + 0.2F);
                    entityitem.field_70179_y = (double)((float)this.randomizer.nextGaussian() * f3);
                    this.world.func_72838_d(entityitem);
                }
            }
        }
    }

    public ItemStack func_70301_a(int p_70301_1_)
    {
        return this.field_94113_a[p_70301_1_];
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_)
    {
        if (this.field_94113_a[p_70298_1_] != null)
        {
            ItemStack itemstack;

            if (this.field_94113_a[p_70298_1_].count <= p_70298_2_)
            {
                itemstack = this.field_94113_a[p_70298_1_];
                this.field_94113_a[p_70298_1_] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.field_94113_a[p_70298_1_].func_77979_a(p_70298_2_);

                if (this.field_94113_a[p_70298_1_].count == 0)
                {
                    this.field_94113_a[p_70298_1_] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack func_70304_b(int p_70304_1_)
    {
        if (this.field_94113_a[p_70304_1_] != null)
        {
            ItemStack itemstack = this.field_94113_a[p_70304_1_];
            this.field_94113_a[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void putItem(int p_70299_1_, ItemStack p_70299_2_)
    {
        this.field_94113_a[p_70299_1_] = p_70299_2_;

        if (p_70299_2_ != null && p_70299_2_.count > this.func_70297_j_())
        {
            p_70299_2_.count = this.func_70297_j_();
        }
    }

    public void func_70296_d() {}

    public boolean func_70300_a(EntityPlayer p_70300_1_)
    {
        return this.field_70128_L ? false : p_70300_1_.func_70068_e(this) <= 64.0D;
    }

    public void func_70295_k_() {}

    public void func_70305_f() {}

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_)
    {
        return true;
    }

    public String func_145825_b()
    {
        return this.func_145818_k_() ? this.func_95999_t() : "container.minecart";
    }

    public int func_70297_j_()
    {
        return 64;
    }

    public void func_71027_c(int p_71027_1_)
    {
        this.field_94112_b = false;
        super.func_71027_c(p_71027_1_);
    }

    public void func_70106_y()
    {
        if (this.field_94112_b)
        {
            for (int i = 0; i < this.func_70302_i_(); ++i)
            {
                ItemStack itemstack = this.func_70301_a(i);

                if (itemstack != null)
                {
                    float f = this.randomizer.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.randomizer.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.randomizer.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.count > 0)
                    {
                        int j = this.randomizer.nextInt(21) + 10;

                        if (j > itemstack.count)
                        {
                            j = itemstack.count;
                        }

                        itemstack.count -= j;
                        EntityItem entityitem = new EntityItem(this.world, this.field_70165_t + (double)f, this.field_70163_u + (double)f1, this.field_70161_v + (double)f2, new ItemStack(itemstack.getBaseItem(), j, itemstack.func_77960_j()));

                        if (itemstack.func_77942_o())
                        {
                            entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                        }

                        float f3 = 0.05F;
                        entityitem.field_70159_w = (double)((float)this.randomizer.nextGaussian() * f3);
                        entityitem.field_70181_x = (double)((float)this.randomizer.nextGaussian() * f3 + 0.2F);
                        entityitem.field_70179_y = (double)((float)this.randomizer.nextGaussian() * f3);
                        this.world.func_72838_d(entityitem);
                    }
                }
            }
        }

        super.func_70106_y();
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.field_94113_a.length; ++i)
        {
            if (this.field_94113_a[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.func_74774_a("Slot", (byte)i);
                this.field_94113_a[i].func_77955_b(nbttagcompound1);
                nbttaglist.func_74742_a(nbttagcompound1);
            }
        }

        p_70014_1_.func_74782_a("Items", nbttaglist);
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        NBTTagList nbttaglist = p_70037_1_.func_150295_c("Items", 10);
        this.field_94113_a = new ItemStack[this.func_70302_i_()];

        for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            int j = nbttagcompound1.func_74771_c("Slot") & 255;

            if (j >= 0 && j < this.field_94113_a.length)
            {
                this.field_94113_a[j] = ItemStack.func_77949_a(nbttagcompound1);
            }
        }
    }

    public boolean func_130002_c(EntityPlayer p_130002_1_)
    {
        if (!this.world.field_72995_K)
        {
            p_130002_1_.func_71007_a(this);
        }

        return true;
    }

    protected void func_94101_h()
    {
        int i = 15 - Container.func_94526_b(this);
        float f = 0.98F + (float)i * 0.001F;
        this.field_70159_w *= (double)f;
        this.field_70181_x *= 0.0D;
        this.field_70179_y *= (double)f;
    }
}