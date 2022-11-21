package net.minecraft.util;

import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;

public class WeightedRandomChestContent extends WeightedRandom.Item
{
    public ItemStack field_76297_b;
    public int field_76295_d;
    public int field_76296_e;
    private static final String __OBFID = "CL_00001505";

    public WeightedRandomChestContent(Item p_i45311_1_, int p_i45311_2_, int p_i45311_3_, int p_i45311_4_, int p_i45311_5_)
    {
        super(p_i45311_5_);
        this.field_76297_b = new ItemStack(p_i45311_1_, 1, p_i45311_2_);
        this.field_76295_d = p_i45311_3_;
        this.field_76296_e = p_i45311_4_;
    }

    public WeightedRandomChestContent(ItemStack p_i1558_1_, int p_i1558_2_, int p_i1558_3_, int p_i1558_4_)
    {
        super(p_i1558_4_);
        this.field_76297_b = p_i1558_1_;
        this.field_76295_d = p_i1558_2_;
        this.field_76296_e = p_i1558_3_;
    }

    public static void func_76293_a(Random p_76293_0_, WeightedRandomChestContent[] p_76293_1_, IInventory p_76293_2_, int p_76293_3_)
    {
        for (int j = 0; j < p_76293_3_; ++j)
        {
            WeightedRandomChestContent weightedrandomchestcontent = (WeightedRandomChestContent)WeightedRandom.func_76274_a(p_76293_0_, p_76293_1_);
            int k = weightedrandomchestcontent.field_76295_d + p_76293_0_.nextInt(weightedrandomchestcontent.field_76296_e - weightedrandomchestcontent.field_76295_d + 1);

            if (weightedrandomchestcontent.field_76297_b.func_77976_d() >= k)
            {
                ItemStack itemstack = weightedrandomchestcontent.field_76297_b.func_77946_l();
                itemstack.field_77994_a = k;
                p_76293_2_.func_70299_a(p_76293_0_.nextInt(p_76293_2_.func_70302_i_()), itemstack);
            }
            else
            {
                for (int l = 0; l < k; ++l)
                {
                    ItemStack itemstack1 = weightedrandomchestcontent.field_76297_b.func_77946_l();
                    itemstack1.field_77994_a = 1;
                    p_76293_2_.func_70299_a(p_76293_0_.nextInt(p_76293_2_.func_70302_i_()), itemstack1);
                }
            }
        }
    }

    public static void func_150706_a(Random p_150706_0_, WeightedRandomChestContent[] p_150706_1_, TileEntityDispenser p_150706_2_, int p_150706_3_)
    {
        for (int j = 0; j < p_150706_3_; ++j)
        {
            WeightedRandomChestContent weightedrandomchestcontent = (WeightedRandomChestContent)WeightedRandom.func_76274_a(p_150706_0_, p_150706_1_);
            int k = weightedrandomchestcontent.field_76295_d + p_150706_0_.nextInt(weightedrandomchestcontent.field_76296_e - weightedrandomchestcontent.field_76295_d + 1);

            if (weightedrandomchestcontent.field_76297_b.func_77976_d() >= k)
            {
                ItemStack itemstack = weightedrandomchestcontent.field_76297_b.func_77946_l();
                itemstack.field_77994_a = k;
                p_150706_2_.func_70299_a(p_150706_0_.nextInt(p_150706_2_.func_70302_i_()), itemstack);
            }
            else
            {
                for (int l = 0; l < k; ++l)
                {
                    ItemStack itemstack1 = weightedrandomchestcontent.field_76297_b.func_77946_l();
                    itemstack1.field_77994_a = 1;
                    p_150706_2_.func_70299_a(p_150706_0_.nextInt(p_150706_2_.func_70302_i_()), itemstack1);
                }
            }
        }
    }

    public static WeightedRandomChestContent[] func_92080_a(WeightedRandomChestContent[] p_92080_0_, WeightedRandomChestContent ... p_92080_1_)
    {
        WeightedRandomChestContent[] aweightedrandomchestcontent1 = new WeightedRandomChestContent[p_92080_0_.length + p_92080_1_.length];
        int i = 0;

        for (int j = 0; j < p_92080_0_.length; ++j)
        {
            aweightedrandomchestcontent1[i++] = p_92080_0_[j];
        }

        WeightedRandomChestContent[] aweightedrandomchestcontent2 = p_92080_1_;
        int k = p_92080_1_.length;

        for (int l = 0; l < k; ++l)
        {
            WeightedRandomChestContent weightedrandomchestcontent1 = aweightedrandomchestcontent2[l];
            aweightedrandomchestcontent1[i++] = weightedrandomchestcontent1;
        }

        return aweightedrandomchestcontent1;
    }
}