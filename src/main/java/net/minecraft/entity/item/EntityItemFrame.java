package net.minecraft.entity.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class EntityItemFrame extends EntityHanging
{
    private float field_82337_e = 1.0F;
    private static final String __OBFID = "CL_00001547";

    public EntityItemFrame(World p_i1590_1_)
    {
        super(p_i1590_1_);
    }

    public EntityItemFrame(World p_i1591_1_, int p_i1591_2_, int p_i1591_3_, int p_i1591_4_, int p_i1591_5_)
    {
        super(p_i1591_1_, p_i1591_2_, p_i1591_3_, p_i1591_4_, p_i1591_5_);
        this.func_82328_a(p_i1591_5_);
    }

    protected void func_70088_a()
    {
        this.func_70096_w().func_82709_a(2, 5);
        this.func_70096_w().func_75682_a(3, Byte.valueOf((byte)0));
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else if (this.func_82335_i() != null)
        {
            if (!this.world.field_72995_K)
            {
                this.func_146065_b(p_70097_1_.func_76346_g(), false);
                this.func_82334_a((ItemStack)null);
            }

            return true;
        }
        else
        {
            return super.func_70097_a(p_70097_1_, p_70097_2_);
        }
    }

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
        double d1 = 16.0D;
        d1 *= 64.0D * this.field_70155_l;
        return p_70112_1_ < d1 * d1;
    }

    public void func_110128_b(Entity p_110128_1_)
    {
        this.func_146065_b(p_110128_1_, true);
    }

    public void func_146065_b(Entity p_146065_1_, boolean p_146065_2_)
    {
        ItemStack itemstack = this.func_82335_i();

        if (p_146065_1_ instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)p_146065_1_;

            if (entityplayer.capabilities.instabuild)
            {
                this.func_110131_b(itemstack);
                return;
            }
        }

        if (p_146065_2_)
        {
            this.func_70099_a(new ItemStack(Items.ITEM_FRAME), 0.0F);
        }

        if (itemstack != null && this.randomizer.nextFloat() < this.field_82337_e)
        {
            itemstack = itemstack.func_77946_l();
            this.func_110131_b(itemstack);
            this.func_70099_a(itemstack, 0.0F);
        }
    }

    private void func_110131_b(ItemStack p_110131_1_)
    {
        if (p_110131_1_ != null)
        {
            if (p_110131_1_.getBaseItem() == Items.FILLED_MAP)
            {
                MapData mapdata = ((ItemMap)p_110131_1_.getBaseItem()).func_77873_a(p_110131_1_, this.world);
                mapdata.field_76203_h.remove("frame-" + this.func_145782_y());
            }

            p_110131_1_.func_82842_a((EntityItemFrame)null);
        }
    }

    public ItemStack func_82335_i()
    {
        return this.func_70096_w().func_82710_f(2);
    }

    public void func_82334_a(ItemStack p_82334_1_)
    {
        if (p_82334_1_ != null)
        {
            p_82334_1_ = p_82334_1_.func_77946_l();
            p_82334_1_.count = 1;
            p_82334_1_.func_82842_a(this);
        }

        this.func_70096_w().func_75692_b(2, p_82334_1_);
        this.func_70096_w().func_82708_h(2);
    }

    public int func_82333_j()
    {
        return this.func_70096_w().func_75683_a(3);
    }

    public void func_82336_g(int p_82336_1_)
    {
        this.func_70096_w().func_75692_b(3, Byte.valueOf((byte)(p_82336_1_ % 4)));
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        if (this.func_82335_i() != null)
        {
            p_70014_1_.func_74782_a("Item", this.func_82335_i().func_77955_b(new NBTTagCompound()));
            p_70014_1_.func_74774_a("ItemRotation", (byte)this.func_82333_j());
            p_70014_1_.func_74776_a("ItemDropChance", this.field_82337_e);
        }

        super.func_70014_b(p_70014_1_);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        NBTTagCompound nbttagcompound1 = p_70037_1_.func_74775_l("Item");

        if (nbttagcompound1 != null && !nbttagcompound1.func_82582_d())
        {
            this.func_82334_a(ItemStack.func_77949_a(nbttagcompound1));
            this.func_82336_g(p_70037_1_.func_74771_c("ItemRotation"));

            if (p_70037_1_.func_150297_b("ItemDropChance", 99))
            {
                this.field_82337_e = p_70037_1_.func_74760_g("ItemDropChance");
            }
        }

        super.func_70037_a(p_70037_1_);
    }

    public boolean func_130002_c(EntityPlayer p_130002_1_)
    {
        if (this.func_82335_i() == null)
        {
            ItemStack itemstack = p_130002_1_.func_70694_bm();

            if (itemstack != null && !this.world.field_72995_K)
            {
                this.func_82334_a(itemstack);

                if (!p_130002_1_.capabilities.instabuild && --itemstack.count <= 0)
                {
                    p_130002_1_.inventory.putItem(p_130002_1_.inventory.activeItemPosition, (ItemStack)null);
                }
            }
        }
        else if (!this.world.field_72995_K)
        {
            this.func_82336_g(this.func_82333_j() + 1);
        }

        return true;
    }
}