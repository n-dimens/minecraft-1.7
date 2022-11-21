package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ItemHoe extends Item
{
    protected Item.ToolMaterial field_77843_a;
    private static final String __OBFID = "CL_00000039";

    public ItemHoe(Item.ToolMaterial p_i45343_1_)
    {
        this.field_77843_a = p_i45343_1_;
        this.field_77777_bU = 1;
        this.func_77656_e(p_i45343_1_.func_77997_a());
        this.func_77637_a(CreativeTabs.field_78040_i);
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (!p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {
            return false;
        }
        else
        {
            Block block = p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);

            if (p_77648_7_ != 0 && p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_ + 1, p_77648_6_).func_149688_o() == Material.field_151579_a && (block == Blocks.field_150349_c || block == Blocks.field_150346_d))
            {
                Block block1 = Blocks.field_150458_ak;
                p_77648_3_.func_72908_a((double)((float)p_77648_4_ + 0.5F), (double)((float)p_77648_5_ + 0.5F), (double)((float)p_77648_6_ + 0.5F), block1.field_149762_H.func_150498_e(), (block1.field_149762_H.func_150497_c() + 1.0F) / 2.0F, block1.field_149762_H.func_150494_d() * 0.8F);

                if (p_77648_3_.field_72995_K)
                {
                    return true;
                }
                else
                {
                    p_77648_3_.func_147449_b(p_77648_4_, p_77648_5_, p_77648_6_, block1);
                    p_77648_1_.func_77972_a(1, p_77648_2_);
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77662_d()
    {
        return true;
    }

    public String func_77842_f()
    {
        return this.field_77843_a.toString();
    }
}