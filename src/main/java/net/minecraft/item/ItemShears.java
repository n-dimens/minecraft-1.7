package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ItemShears extends Item
{
    private static final String __OBFID = "CL_00000062";

    public ItemShears()
    {
        this.setStackMaxSize(1);
        this.setDurability(238);
        this.func_77637_a(CreativeTabs.field_78040_i);
    }

    public boolean func_150894_a(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        if (p_150894_3_.func_149688_o() != Material.field_151584_j && p_150894_3_ != Blocks.WEB && p_150894_3_ != Blocks.TALLGRASS && p_150894_3_ != Blocks.VINE && p_150894_3_ != Blocks.TRIPWIRE)
        {
            return super.func_150894_a(p_150894_1_, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, p_150894_7_);
        }
        else
        {
            p_150894_1_.func_77972_a(1, p_150894_7_);
            return true;
        }
    }

    public boolean func_150897_b(Block p_150897_1_)
    {
        return p_150897_1_ == Blocks.WEB || p_150897_1_ == Blocks.REDSTONE_WIRE || p_150897_1_ == Blocks.TRIPWIRE;
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return p_150893_2_ != Blocks.WEB && p_150893_2_.func_149688_o() != Material.field_151584_j ? (p_150893_2_ == Blocks.WOOL ? 5.0F : super.func_150893_a(p_150893_1_, p_150893_2_)) : 15.0F;
    }
}