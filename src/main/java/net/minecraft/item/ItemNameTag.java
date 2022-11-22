package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class ItemNameTag extends Item
{
    private static final String __OBFID = "CL_00000052";

    public ItemNameTag()
    {
        this.func_77637_a(CreativeTabs.field_78040_i);
    }

    public boolean func_111207_a(ItemStack p_111207_1_, EntityPlayer p_111207_2_, EntityLivingBase p_111207_3_)
    {
        if (!p_111207_1_.func_82837_s())
        {
            return false;
        }
        else if (p_111207_3_ instanceof EntityLiving)
        {
            EntityLiving entityliving = (EntityLiving)p_111207_3_;
            entityliving.func_94058_c(p_111207_1_.func_82833_r());
            entityliving.func_110163_bv();
            --p_111207_1_.count;
            return true;
        }
        else
        {
            return super.func_111207_a(p_111207_1_, p_111207_2_, p_111207_3_);
        }
    }
}