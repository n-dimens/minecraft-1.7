package net.minecraft.village;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MerchantRecipe
{
    private ItemStack field_77403_a;
    private ItemStack field_77401_b;
    private ItemStack field_77402_c;
    private int field_77400_d;
    private int field_82786_e;
    private static final String __OBFID = "CL_00000126";

    public MerchantRecipe(NBTTagCompound p_i1940_1_)
    {
        this.func_77390_a(p_i1940_1_);
    }

    public MerchantRecipe(ItemStack p_i1941_1_, ItemStack p_i1941_2_, ItemStack p_i1941_3_)
    {
        this.field_77403_a = p_i1941_1_;
        this.field_77401_b = p_i1941_2_;
        this.field_77402_c = p_i1941_3_;
        this.field_82786_e = 7;
    }

    public MerchantRecipe(ItemStack p_i1942_1_, ItemStack p_i1942_2_)
    {
        this(p_i1942_1_, (ItemStack)null, p_i1942_2_);
    }

    public MerchantRecipe(ItemStack p_i1943_1_, Item p_i1943_2_)
    {
        this(p_i1943_1_, new ItemStack(p_i1943_2_));
    }

    public ItemStack func_77394_a()
    {
        return this.field_77403_a;
    }

    public ItemStack func_77396_b()
    {
        return this.field_77401_b;
    }

    public boolean func_77398_c()
    {
        return this.field_77401_b != null;
    }

    public ItemStack func_77397_d()
    {
        return this.field_77402_c;
    }

    public boolean func_77393_a(MerchantRecipe p_77393_1_)
    {
        return this.field_77403_a.getBaseItem() == p_77393_1_.field_77403_a.getBaseItem() && this.field_77402_c.getBaseItem() == p_77393_1_.field_77402_c.getBaseItem() ? this.field_77401_b == null && p_77393_1_.field_77401_b == null || this.field_77401_b != null && p_77393_1_.field_77401_b != null && this.field_77401_b.getBaseItem() == p_77393_1_.field_77401_b.getBaseItem() : false;
    }

    public boolean func_77391_b(MerchantRecipe p_77391_1_)
    {
        return this.func_77393_a(p_77391_1_) && (this.field_77403_a.count < p_77391_1_.field_77403_a.count || this.field_77401_b != null && this.field_77401_b.count < p_77391_1_.field_77401_b.count);
    }

    public void func_77399_f()
    {
        ++this.field_77400_d;
    }

    public void func_82783_a(int p_82783_1_)
    {
        this.field_82786_e += p_82783_1_;
    }

    public boolean func_82784_g()
    {
        return this.field_77400_d >= this.field_82786_e;
    }

    @SideOnly(Side.CLIENT)
    public void func_82785_h()
    {
        this.field_77400_d = this.field_82786_e;
    }

    public void func_77390_a(NBTTagCompound p_77390_1_)
    {
        NBTTagCompound nbttagcompound1 = p_77390_1_.func_74775_l("buy");
        this.field_77403_a = ItemStack.func_77949_a(nbttagcompound1);
        NBTTagCompound nbttagcompound2 = p_77390_1_.func_74775_l("sell");
        this.field_77402_c = ItemStack.func_77949_a(nbttagcompound2);

        if (p_77390_1_.func_150297_b("buyB", 10))
        {
            this.field_77401_b = ItemStack.func_77949_a(p_77390_1_.func_74775_l("buyB"));
        }

        if (p_77390_1_.func_150297_b("uses", 99))
        {
            this.field_77400_d = p_77390_1_.func_74762_e("uses");
        }

        if (p_77390_1_.func_150297_b("maxUses", 99))
        {
            this.field_82786_e = p_77390_1_.func_74762_e("maxUses");
        }
        else
        {
            this.field_82786_e = 7;
        }
    }

    public NBTTagCompound func_77395_g()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74782_a("buy", this.field_77403_a.func_77955_b(new NBTTagCompound()));
        nbttagcompound.func_74782_a("sell", this.field_77402_c.func_77955_b(new NBTTagCompound()));

        if (this.field_77401_b != null)
        {
            nbttagcompound.func_74782_a("buyB", this.field_77401_b.func_77955_b(new NBTTagCompound()));
        }

        nbttagcompound.func_74768_a("uses", this.field_77400_d);
        nbttagcompound.func_74768_a("maxUses", this.field_82786_e);
        return nbttagcompound;
    }
}