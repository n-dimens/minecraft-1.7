package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class ItemBucketMilk extends Item
{
    private static final String __OBFID = "CL_00000048";

    public ItemBucketMilk()
    {
        this.setStackMaxSize(1);
        this.func_77637_a(CreativeTabs.field_78026_f);
    }

    public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        if (!p_77654_3_.capabilities.instabuild)
        {
            --p_77654_1_.count;
        }

        if (!p_77654_2_.field_72995_K)
        {
            p_77654_3_.func_70674_bp();
        }

        return p_77654_1_.count <= 0 ? new ItemStack(Items.BUCKET) : p_77654_1_;
    }

    public int func_77626_a(ItemStack p_77626_1_)
    {
        return 32;
    }

    public EnumAction func_77661_b(ItemStack p_77661_1_)
    {
        return EnumAction.drink;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
        return p_77659_1_;
    }
}