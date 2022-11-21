package net.minecraft.village;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;

public class MerchantRecipeList extends ArrayList
{
    private static final String __OBFID = "CL_00000127";

    public MerchantRecipeList() {}

    public MerchantRecipeList(NBTTagCompound p_i1944_1_)
    {
        this.func_77201_a(p_i1944_1_);
    }

    public MerchantRecipe func_77203_a(ItemStack p_77203_1_, ItemStack p_77203_2_, int p_77203_3_)
    {
        if (p_77203_3_ > 0 && p_77203_3_ < this.size())
        {
            MerchantRecipe merchantrecipe1 = (MerchantRecipe)this.get(p_77203_3_);
            return p_77203_1_.func_77973_b() == merchantrecipe1.func_77394_a().func_77973_b() && (p_77203_2_ == null && !merchantrecipe1.func_77398_c() || merchantrecipe1.func_77398_c() && p_77203_2_ != null && merchantrecipe1.func_77396_b().func_77973_b() == p_77203_2_.func_77973_b()) && p_77203_1_.field_77994_a >= merchantrecipe1.func_77394_a().field_77994_a && (!merchantrecipe1.func_77398_c() || p_77203_2_.field_77994_a >= merchantrecipe1.func_77396_b().field_77994_a) ? merchantrecipe1 : null;
        }
        else
        {
            for (int j = 0; j < this.size(); ++j)
            {
                MerchantRecipe merchantrecipe = (MerchantRecipe)this.get(j);

                if (p_77203_1_.func_77973_b() == merchantrecipe.func_77394_a().func_77973_b() && p_77203_1_.field_77994_a >= merchantrecipe.func_77394_a().field_77994_a && (!merchantrecipe.func_77398_c() && p_77203_2_ == null || merchantrecipe.func_77398_c() && p_77203_2_ != null && merchantrecipe.func_77396_b().func_77973_b() == p_77203_2_.func_77973_b() && p_77203_2_.field_77994_a >= merchantrecipe.func_77396_b().field_77994_a))
                {
                    return merchantrecipe;
                }
            }

            return null;
        }
    }

    public void func_77205_a(MerchantRecipe p_77205_1_)
    {
        for (int i = 0; i < this.size(); ++i)
        {
            MerchantRecipe merchantrecipe1 = (MerchantRecipe)this.get(i);

            if (p_77205_1_.func_77393_a(merchantrecipe1))
            {
                if (p_77205_1_.func_77391_b(merchantrecipe1))
                {
                    this.set(i, p_77205_1_);
                }

                return;
            }
        }

        this.add(p_77205_1_);
    }

    public void func_151391_a(PacketBuffer p_151391_1_) throws IOException
    {
        p_151391_1_.writeByte((byte)(this.size() & 255));

        for (int i = 0; i < this.size(); ++i)
        {
            MerchantRecipe merchantrecipe = (MerchantRecipe)this.get(i);
            p_151391_1_.func_150788_a(merchantrecipe.func_77394_a());
            p_151391_1_.func_150788_a(merchantrecipe.func_77397_d());
            ItemStack itemstack = merchantrecipe.func_77396_b();
            p_151391_1_.writeBoolean(itemstack != null);

            if (itemstack != null)
            {
                p_151391_1_.func_150788_a(itemstack);
            }

            p_151391_1_.writeBoolean(merchantrecipe.func_82784_g());
        }
    }

    public void func_77201_a(NBTTagCompound p_77201_1_)
    {
        NBTTagList nbttaglist = p_77201_1_.func_150295_c("Recipes", 10);

        for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            this.add(new MerchantRecipe(nbttagcompound1));
        }
    }

    public NBTTagCompound func_77202_a()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.size(); ++i)
        {
            MerchantRecipe merchantrecipe = (MerchantRecipe)this.get(i);
            nbttaglist.func_74742_a(merchantrecipe.func_77395_g());
        }

        nbttagcompound.func_74782_a("Recipes", nbttaglist);
        return nbttagcompound;
    }

    @SideOnly(Side.CLIENT)
    public static MerchantRecipeList func_151390_b(PacketBuffer p_151390_0_) throws IOException
    {
        MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
        int i = p_151390_0_.readByte() & 255;

        for (int j = 0; j < i; ++j)
        {
            ItemStack itemstack = p_151390_0_.func_150791_c();
            ItemStack itemstack1 = p_151390_0_.func_150791_c();
            ItemStack itemstack2 = null;

            if (p_151390_0_.readBoolean())
            {
                itemstack2 = p_151390_0_.func_150791_c();
            }

            boolean flag = p_151390_0_.readBoolean();
            MerchantRecipe merchantrecipe = new MerchantRecipe(itemstack, itemstack2, itemstack1);

            if (flag)
            {
                merchantrecipe.func_82785_h();
            }

            merchantrecipelist.add(merchantrecipe);
        }

        return merchantrecipelist;
    }
}