package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.world.World;

public class ItemEgg extends Item
{
    private static final String __OBFID = "CL_00000023";

    public ItemEgg()
    {
        this.stackMaxSize = 16;
        this.func_77637_a(CreativeTabs.field_78035_l);
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        if (!p_77659_3_.capabilities.instabuild)
        {
            --p_77659_1_.count;
        }

        p_77659_2_.func_72956_a(p_77659_3_, "random.bow", 0.5F, 0.4F / (randomizer.nextFloat() * 0.4F + 0.8F));

        if (!p_77659_2_.field_72995_K)
        {
            p_77659_2_.func_72838_d(new EntityEgg(p_77659_2_, p_77659_3_));
        }

        return p_77659_1_;
    }
}