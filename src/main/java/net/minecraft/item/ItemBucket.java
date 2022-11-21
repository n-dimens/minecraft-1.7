package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBucket extends Item
{
    private Block field_77876_a;
    private static final String __OBFID = "CL_00000000";

    public ItemBucket(Block p_i45331_1_)
    {
        this.field_77777_bU = 1;
        this.field_77876_a = p_i45331_1_;
        this.func_77637_a(CreativeTabs.field_78026_f);
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        boolean flag = this.field_77876_a == Blocks.AIR;
        MovingObjectPosition movingobjectposition = this.func_77621_a(p_77659_2_, p_77659_3_, flag);

        if (movingobjectposition == null)
        {
            return p_77659_1_;
        }
        else
        {
            if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int i = movingobjectposition.field_72311_b;
                int j = movingobjectposition.field_72312_c;
                int k = movingobjectposition.field_72309_d;

                if (!p_77659_2_.func_72962_a(p_77659_3_, i, j, k))
                {
                    return p_77659_1_;
                }

                if (flag)
                {
                    if (!p_77659_3_.func_82247_a(i, j, k, movingobjectposition.field_72310_e, p_77659_1_))
                    {
                        return p_77659_1_;
                    }

                    Material material = p_77659_2_.func_147439_a(i, j, k).func_149688_o();
                    int l = p_77659_2_.func_72805_g(i, j, k);

                    if (material == Material.field_151586_h && l == 0)
                    {
                        p_77659_2_.func_147468_f(i, j, k);
                        return this.func_150910_a(p_77659_1_, p_77659_3_, Items.WATER_BUCKET);
                    }

                    if (material == Material.field_151587_i && l == 0)
                    {
                        p_77659_2_.func_147468_f(i, j, k);
                        return this.func_150910_a(p_77659_1_, p_77659_3_, Items.LAVA_BUCKET);
                    }
                }
                else
                {
                    if (this.field_77876_a == Blocks.AIR)
                    {
                        return new ItemStack(Items.BUCKET);
                    }

                    if (movingobjectposition.field_72310_e == 0)
                    {
                        --j;
                    }

                    if (movingobjectposition.field_72310_e == 1)
                    {
                        ++j;
                    }

                    if (movingobjectposition.field_72310_e == 2)
                    {
                        --k;
                    }

                    if (movingobjectposition.field_72310_e == 3)
                    {
                        ++k;
                    }

                    if (movingobjectposition.field_72310_e == 4)
                    {
                        --i;
                    }

                    if (movingobjectposition.field_72310_e == 5)
                    {
                        ++i;
                    }

                    if (!p_77659_3_.func_82247_a(i, j, k, movingobjectposition.field_72310_e, p_77659_1_))
                    {
                        return p_77659_1_;
                    }

                    if (this.func_77875_a(p_77659_2_, i, j, k) && !p_77659_3_.field_71075_bZ.field_75098_d)
                    {
                        return new ItemStack(Items.BUCKET);
                    }
                }
            }

            return p_77659_1_;
        }
    }

    private ItemStack func_150910_a(ItemStack p_150910_1_, EntityPlayer p_150910_2_, Item p_150910_3_)
    {
        if (p_150910_2_.field_71075_bZ.field_75098_d)
        {
            return p_150910_1_;
        }
        else if (--p_150910_1_.field_77994_a <= 0)
        {
            return new ItemStack(p_150910_3_);
        }
        else
        {
            if (!p_150910_2_.field_71071_by.func_70441_a(new ItemStack(p_150910_3_)))
            {
                p_150910_2_.func_71019_a(new ItemStack(p_150910_3_, 1, 0), false);
            }

            return p_150910_1_;
        }
    }

    public boolean func_77875_a(World p_77875_1_, int p_77875_2_, int p_77875_3_, int p_77875_4_)
    {
        if (this.field_77876_a == Blocks.AIR)
        {
            return false;
        }
        else
        {
            Material material = p_77875_1_.func_147439_a(p_77875_2_, p_77875_3_, p_77875_4_).func_149688_o();
            boolean flag = !material.func_76220_a();

            if (!p_77875_1_.func_147437_c(p_77875_2_, p_77875_3_, p_77875_4_) && !flag)
            {
                return false;
            }
            else
            {
                if (p_77875_1_.field_73011_w.field_76575_d && this.field_77876_a == Blocks.FLOWING_WATER)
                {
                    p_77875_1_.func_72908_a((double)((float)p_77875_2_ + 0.5F), (double)((float)p_77875_3_ + 0.5F), (double)((float)p_77875_4_ + 0.5F), "random.fizz", 0.5F, 2.6F + (p_77875_1_.field_73012_v.nextFloat() - p_77875_1_.field_73012_v.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                        p_77875_1_.func_72869_a("largesmoke", (double)p_77875_2_ + Math.random(), (double)p_77875_3_ + Math.random(), (double)p_77875_4_ + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else
                {
                    if (!p_77875_1_.field_72995_K && flag && !material.func_76224_d())
                    {
                        p_77875_1_.func_147480_a(p_77875_2_, p_77875_3_, p_77875_4_, true);
                    }

                    p_77875_1_.func_147465_d(p_77875_2_, p_77875_3_, p_77875_4_, this.field_77876_a, 0, 3);
                }

                return true;
            }
        }
    }
}