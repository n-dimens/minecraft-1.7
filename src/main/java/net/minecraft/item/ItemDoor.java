package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDoor extends Item
{
    private Material field_77870_a;
    private static final String __OBFID = "CL_00000020";

    public ItemDoor(Material p_i45334_1_)
    {
        this.field_77870_a = p_i45334_1_;
        this.field_77777_bU = 1;
        this.func_77637_a(CreativeTabs.field_78028_d);
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_7_ != 1)
        {
            return false;
        }
        else
        {
            ++p_77648_5_;
            Block block;

            if (this.field_77870_a == Material.field_151575_d)
            {
                block = Blocks.field_150466_ao;
            }
            else
            {
                block = Blocks.field_150454_av;
            }

            if (p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_ + 1, p_77648_6_, p_77648_7_, p_77648_1_))
            {
                if (!block.func_149742_c(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))
                {
                    return false;
                }
                else
                {
                    int i1 = MathHelper.func_76128_c((double)((p_77648_2_.field_70177_z + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                    func_150924_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, i1, block);
                    --p_77648_1_.field_77994_a;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static void func_150924_a(World p_150924_0_, int p_150924_1_, int p_150924_2_, int p_150924_3_, int p_150924_4_, Block p_150924_5_)
    {
        byte b0 = 0;
        byte b1 = 0;

        if (p_150924_4_ == 0)
        {
            b1 = 1;
        }

        if (p_150924_4_ == 1)
        {
            b0 = -1;
        }

        if (p_150924_4_ == 2)
        {
            b1 = -1;
        }

        if (p_150924_4_ == 3)
        {
            b0 = 1;
        }

        int i1 = (p_150924_0_.func_147439_a(p_150924_1_ - b0, p_150924_2_, p_150924_3_ - b1).func_149721_r() ? 1 : 0) + (p_150924_0_.func_147439_a(p_150924_1_ - b0, p_150924_2_ + 1, p_150924_3_ - b1).func_149721_r() ? 1 : 0);
        int j1 = (p_150924_0_.func_147439_a(p_150924_1_ + b0, p_150924_2_, p_150924_3_ + b1).func_149721_r() ? 1 : 0) + (p_150924_0_.func_147439_a(p_150924_1_ + b0, p_150924_2_ + 1, p_150924_3_ + b1).func_149721_r() ? 1 : 0);
        boolean flag = p_150924_0_.func_147439_a(p_150924_1_ - b0, p_150924_2_, p_150924_3_ - b1) == p_150924_5_ || p_150924_0_.func_147439_a(p_150924_1_ - b0, p_150924_2_ + 1, p_150924_3_ - b1) == p_150924_5_;
        boolean flag1 = p_150924_0_.func_147439_a(p_150924_1_ + b0, p_150924_2_, p_150924_3_ + b1) == p_150924_5_ || p_150924_0_.func_147439_a(p_150924_1_ + b0, p_150924_2_ + 1, p_150924_3_ + b1) == p_150924_5_;
        boolean flag2 = false;

        if (flag && !flag1)
        {
            flag2 = true;
        }
        else if (j1 > i1)
        {
            flag2 = true;
        }

        p_150924_0_.func_147465_d(p_150924_1_, p_150924_2_, p_150924_3_, p_150924_5_, p_150924_4_, 2);
        p_150924_0_.func_147465_d(p_150924_1_, p_150924_2_ + 1, p_150924_3_, p_150924_5_, 8 | (flag2 ? 1 : 0), 2);
        p_150924_0_.func_147459_d(p_150924_1_, p_150924_2_, p_150924_3_, p_150924_5_);
        p_150924_0_.func_147459_d(p_150924_1_, p_150924_2_ + 1, p_150924_3_, p_150924_5_);
    }
}