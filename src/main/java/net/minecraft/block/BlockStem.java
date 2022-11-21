package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStem extends BlockBush implements IGrowable
{
    private final Block field_149877_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_149876_b;
    private static final String __OBFID = "CL_00000316";

    protected BlockStem(Block p_i45430_1_)
    {
        this.field_149877_a = p_i45430_1_;
        this.func_149675_a(true);
        float f = 0.125F;
        this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.func_149647_a((CreativeTabs)null);
    }

    protected boolean func_149854_a(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.field_150458_ak;
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        super.func_149674_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);

        if (p_149674_1_.func_72957_l(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9)
        {
            float f = this.func_149875_n(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);

            if (p_149674_5_.nextInt((int)(25.0F / f) + 1) == 0)
            {
                int l = p_149674_1_.func_72805_g(p_149674_2_, p_149674_3_, p_149674_4_);

                if (l < 7)
                {
                    ++l;
                    p_149674_1_.func_72921_c(p_149674_2_, p_149674_3_, p_149674_4_, l, 2);
                }
                else
                {
                    if (p_149674_1_.func_147439_a(p_149674_2_ - 1, p_149674_3_, p_149674_4_) == this.field_149877_a)
                    {
                        return;
                    }

                    if (p_149674_1_.func_147439_a(p_149674_2_ + 1, p_149674_3_, p_149674_4_) == this.field_149877_a)
                    {
                        return;
                    }

                    if (p_149674_1_.func_147439_a(p_149674_2_, p_149674_3_, p_149674_4_ - 1) == this.field_149877_a)
                    {
                        return;
                    }

                    if (p_149674_1_.func_147439_a(p_149674_2_, p_149674_3_, p_149674_4_ + 1) == this.field_149877_a)
                    {
                        return;
                    }

                    int i1 = p_149674_5_.nextInt(4);
                    int j1 = p_149674_2_;
                    int k1 = p_149674_4_;

                    if (i1 == 0)
                    {
                        j1 = p_149674_2_ - 1;
                    }

                    if (i1 == 1)
                    {
                        ++j1;
                    }

                    if (i1 == 2)
                    {
                        k1 = p_149674_4_ - 1;
                    }

                    if (i1 == 3)
                    {
                        ++k1;
                    }

                    Block block = p_149674_1_.func_147439_a(j1, p_149674_3_ - 1, k1);

                    if (p_149674_1_.func_147439_a(j1, p_149674_3_, k1).field_149764_J == Material.field_151579_a && (block == Blocks.field_150458_ak || block == Blocks.field_150346_d || block == Blocks.field_150349_c))
                    {
                        p_149674_1_.func_147449_b(j1, p_149674_3_, k1, this.field_149877_a);
                    }
                }
            }
        }
    }

    public void func_149874_m(World p_149874_1_, int p_149874_2_, int p_149874_3_, int p_149874_4_)
    {
        int l = p_149874_1_.func_72805_g(p_149874_2_, p_149874_3_, p_149874_4_) + MathHelper.func_76136_a(p_149874_1_.field_73012_v, 2, 5);

        if (l > 7)
        {
            l = 7;
        }

        p_149874_1_.func_72921_c(p_149874_2_, p_149874_3_, p_149874_4_, l, 2);
    }

    private float func_149875_n(World p_149875_1_, int p_149875_2_, int p_149875_3_, int p_149875_4_)
    {
        float f = 1.0F;
        Block block = p_149875_1_.func_147439_a(p_149875_2_, p_149875_3_, p_149875_4_ - 1);
        Block block1 = p_149875_1_.func_147439_a(p_149875_2_, p_149875_3_, p_149875_4_ + 1);
        Block block2 = p_149875_1_.func_147439_a(p_149875_2_ - 1, p_149875_3_, p_149875_4_);
        Block block3 = p_149875_1_.func_147439_a(p_149875_2_ + 1, p_149875_3_, p_149875_4_);
        Block block4 = p_149875_1_.func_147439_a(p_149875_2_ - 1, p_149875_3_, p_149875_4_ - 1);
        Block block5 = p_149875_1_.func_147439_a(p_149875_2_ + 1, p_149875_3_, p_149875_4_ - 1);
        Block block6 = p_149875_1_.func_147439_a(p_149875_2_ + 1, p_149875_3_, p_149875_4_ + 1);
        Block block7 = p_149875_1_.func_147439_a(p_149875_2_ - 1, p_149875_3_, p_149875_4_ + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for (int l = p_149875_2_ - 1; l <= p_149875_2_ + 1; ++l)
        {
            for (int i1 = p_149875_4_ - 1; i1 <= p_149875_4_ + 1; ++i1)
            {
                Block block8 = p_149875_1_.func_147439_a(l, p_149875_3_ - 1, i1);
                float f1 = 0.0F;

                if (block8 == Blocks.field_150458_ak)
                {
                    f1 = 1.0F;

                    if (p_149875_1_.func_72805_g(l, p_149875_3_ - 1, i1) > 0)
                    {
                        f1 = 3.0F;
                    }
                }

                if (l != p_149875_2_ || i1 != p_149875_4_)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }

        return f;
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int p_149741_1_)
    {
        int j = p_149741_1_ * 32;
        int k = 255 - p_149741_1_ * 8;
        int l = p_149741_1_ * 4;
        return j << 16 | k << 8 | l;
    }

    @SideOnly(Side.CLIENT)
    public int func_149720_d(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return this.func_149741_i(p_149720_1_.func_72805_g(p_149720_2_, p_149720_3_, p_149720_4_));
    }

    public void func_149683_g()
    {
        float f = 0.125F;
        this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        this.field_149756_F = (double)((float)(p_149719_1_.func_72805_g(p_149719_2_, p_149719_3_, p_149719_4_) * 2 + 2) / 16.0F);
        float f = 0.125F;
        this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, (float)this.field_149756_F, 0.5F + f);
    }

    public int func_149645_b()
    {
        return 19;
    }

    @SideOnly(Side.CLIENT)
    public int func_149873_e(IBlockAccess p_149873_1_, int p_149873_2_, int p_149873_3_, int p_149873_4_)
    {
        int l = p_149873_1_.func_72805_g(p_149873_2_, p_149873_3_, p_149873_4_);
        return l < 7 ? -1 : (p_149873_1_.func_147439_a(p_149873_2_ - 1, p_149873_3_, p_149873_4_) == this.field_149877_a ? 0 : (p_149873_1_.func_147439_a(p_149873_2_ + 1, p_149873_3_, p_149873_4_) == this.field_149877_a ? 1 : (p_149873_1_.func_147439_a(p_149873_2_, p_149873_3_, p_149873_4_ - 1) == this.field_149877_a ? 2 : (p_149873_1_.func_147439_a(p_149873_2_, p_149873_3_, p_149873_4_ + 1) == this.field_149877_a ? 3 : -1))));
    }

    public void func_149690_a(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.func_149690_a(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);

        if (!p_149690_1_.field_72995_K)
        {
            Item item = null;

            if (this.field_149877_a == Blocks.field_150423_aK)
            {
                item = Items.field_151080_bb;
            }

            if (this.field_149877_a == Blocks.field_150440_ba)
            {
                item = Items.field_151081_bc;
            }

            for (int j1 = 0; j1 < 3; ++j1)
            {
                if (p_149690_1_.field_73012_v.nextInt(15) <= p_149690_5_)
                {
                    this.func_149642_a(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, new ItemStack(item));
                }
            }
        }
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }

    public int func_149745_a(Random p_149745_1_)
    {
        return 1;
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return p_149851_1_.func_72805_g(p_149851_2_, p_149851_3_, p_149851_4_) != 7;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return this.field_149877_a == Blocks.field_150423_aK ? Items.field_151080_bb : (this.field_149877_a == Blocks.field_150440_ba ? Items.field_151081_bc : Item.func_150899_d(0));
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_)
    {
        this.field_149761_L = p_149651_1_.func_94245_a(this.func_149641_N() + "_disconnected");
        this.field_149876_b = p_149651_1_.func_94245_a(this.func_149641_N() + "_connected");
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149872_i()
    {
        return this.field_149876_b;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        this.func_149874_m(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_);
    }
}