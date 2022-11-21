package net.minecraft.item;

import net.minecraft.block.BlockBed;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBed extends Item
{
    private static final String __OBFID = "CL_00001771";

    public ItemBed()
    {
        this.func_77637_a(CreativeTabs.field_78031_c);
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_3_.field_72995_K)
        {
            return true;
        }
        else if (p_77648_7_ != 1)
        {
            return false;
        }
        else
        {
            ++p_77648_5_;
            BlockBed blockbed = (BlockBed)Blocks.BED;
            int i1 = MathHelper.func_76128_c((double)(p_77648_2_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
            byte b0 = 0;
            byte b1 = 0;

            if (i1 == 0)
            {
                b1 = 1;
            }

            if (i1 == 1)
            {
                b0 = -1;
            }

            if (i1 == 2)
            {
                b1 = -1;
            }

            if (i1 == 3)
            {
                b0 = 1;
            }

            if (p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.func_82247_a(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1, p_77648_7_, p_77648_1_))
            {
                if (p_77648_3_.func_147437_c(p_77648_4_, p_77648_5_, p_77648_6_) && p_77648_3_.func_147437_c(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1) && World.func_147466_a(p_77648_3_, p_77648_4_, p_77648_5_ - 1, p_77648_6_) && World.func_147466_a(p_77648_3_, p_77648_4_ + b0, p_77648_5_ - 1, p_77648_6_ + b1))
                {
                    p_77648_3_.func_147465_d(p_77648_4_, p_77648_5_, p_77648_6_, blockbed, i1, 3);

                    if (p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_) == blockbed)
                    {
                        p_77648_3_.func_147465_d(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1, blockbed, i1 + 8, 3);
                    }

                    --p_77648_1_.field_77994_a;
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }
}