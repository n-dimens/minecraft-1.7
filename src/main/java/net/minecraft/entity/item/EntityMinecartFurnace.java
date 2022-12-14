package net.minecraft.entity.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMinecartFurnace extends EntityMinecart
{
    private int field_94110_c;
    public double field_94111_a;
    public double field_94109_b;
    private static final String __OBFID = "CL_00001675";

    public EntityMinecartFurnace(World p_i1718_1_)
    {
        super(p_i1718_1_);
    }

    public EntityMinecartFurnace(World p_i1719_1_, double p_i1719_2_, double p_i1719_4_, double p_i1719_6_)
    {
        super(p_i1719_1_, p_i1719_2_, p_i1719_4_, p_i1719_6_);
    }

    public int func_94087_l()
    {
        return 2;
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, new Byte((byte)0));
    }

    public void func_70071_h_()
    {
        super.func_70071_h_();

        if (this.field_94110_c > 0)
        {
            --this.field_94110_c;
        }

        if (this.field_94110_c <= 0)
        {
            this.field_94111_a = this.field_94109_b = 0.0D;
        }

        this.func_94107_f(this.field_94110_c > 0);

        if (this.func_94108_c() && this.randomizer.nextInt(4) == 0)
        {
            this.world.func_72869_a("largesmoke", this.field_70165_t, this.field_70163_u + 0.8D, this.field_70161_v, 0.0D, 0.0D, 0.0D);
        }
    }

    public void func_94095_a(DamageSource p_94095_1_)
    {
        super.func_94095_a(p_94095_1_);

        if (!p_94095_1_.func_94541_c())
        {
            this.func_70099_a(new ItemStack(Blocks.FURNACE, 1), 0.0F);
        }
    }

    protected void func_145821_a(int p_145821_1_, int p_145821_2_, int p_145821_3_, double p_145821_4_, double p_145821_6_, Block p_145821_8_, int p_145821_9_)
    {
        super.func_145821_a(p_145821_1_, p_145821_2_, p_145821_3_, p_145821_4_, p_145821_6_, p_145821_8_, p_145821_9_);
        double d2 = this.field_94111_a * this.field_94111_a + this.field_94109_b * this.field_94109_b;

        if (d2 > 1.0E-4D && this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 0.001D)
        {
            d2 = (double)MathHelper.func_76133_a(d2);
            this.field_94111_a /= d2;
            this.field_94109_b /= d2;

            if (this.field_94111_a * this.field_70159_w + this.field_94109_b * this.field_70179_y < 0.0D)
            {
                this.field_94111_a = 0.0D;
                this.field_94109_b = 0.0D;
            }
            else
            {
                this.field_94111_a = this.field_70159_w;
                this.field_94109_b = this.field_70179_y;
            }
        }
    }

    protected void func_94101_h()
    {
        double d0 = this.field_94111_a * this.field_94111_a + this.field_94109_b * this.field_94109_b;

        if (d0 > 1.0E-4D)
        {
            d0 = (double)MathHelper.func_76133_a(d0);
            this.field_94111_a /= d0;
            this.field_94109_b /= d0;
            double d1 = 0.05D;
            this.field_70159_w *= 0.800000011920929D;
            this.field_70181_x *= 0.0D;
            this.field_70179_y *= 0.800000011920929D;
            this.field_70159_w += this.field_94111_a * d1;
            this.field_70179_y += this.field_94109_b * d1;
        }
        else
        {
            this.field_70159_w *= 0.9800000190734863D;
            this.field_70181_x *= 0.0D;
            this.field_70179_y *= 0.9800000190734863D;
        }

        super.func_94101_h();
    }

    public boolean func_130002_c(EntityPlayer p_130002_1_)
    {
        ItemStack itemstack = p_130002_1_.inventory.getActiveItem();

        if (itemstack != null && itemstack.getBaseItem() == Items.COAL)
        {
            if (!p_130002_1_.capabilities.instabuild && --itemstack.count == 0)
            {
                p_130002_1_.inventory.putItem(p_130002_1_.inventory.activeItemPosition, (ItemStack)null);
            }

            this.field_94110_c += 3600;
        }

        this.field_94111_a = this.field_70165_t - p_130002_1_.field_70165_t;
        this.field_94109_b = this.field_70161_v - p_130002_1_.field_70161_v;
        return true;
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74780_a("PushX", this.field_94111_a);
        p_70014_1_.func_74780_a("PushZ", this.field_94109_b);
        p_70014_1_.func_74777_a("Fuel", (short)this.field_94110_c);
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.field_94111_a = p_70037_1_.func_74769_h("PushX");
        this.field_94109_b = p_70037_1_.func_74769_h("PushZ");
        this.field_94110_c = p_70037_1_.func_74765_d("Fuel");
    }

    protected boolean func_94108_c()
    {
        return (this.field_70180_af.func_75683_a(16) & 1) != 0;
    }

    protected void func_94107_f(boolean p_94107_1_)
    {
        if (p_94107_1_)
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(this.field_70180_af.func_75683_a(16) | 1)));
        }
        else
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(this.field_70180_af.func_75683_a(16) & -2)));
        }
    }

    public Block func_145817_o()
    {
        return Blocks.LIT_FURNACE;
    }

    public int func_94097_p()
    {
        return 2;
    }
}