package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCrops extends BlockBush implements IGrowable
{
    @SideOnly(Side.CLIENT)
    private IIcon[] field_149867_a;
    private static final String __OBFID = "CL_00000222";

    protected BlockCrops()
    {
        this.func_149675_a(true);
        float f = 0.5F;
        this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.func_149647_a((CreativeTabs)null);
        this.func_149711_c(0.0F);
        this.func_149672_a(field_149779_h);
        this.func_149649_H();
    }

    protected boolean func_149854_a(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.FARMLAND;
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        super.func_149674_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);

        if (p_149674_1_.func_72957_l(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9)
        {
            int l = p_149674_1_.func_72805_g(p_149674_2_, p_149674_3_, p_149674_4_);

            if (l < 7)
            {
                float f = this.func_149864_n(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);

                if (p_149674_5_.nextInt((int)(25.0F / f) + 1) == 0)
                {
                    ++l;
                    p_149674_1_.func_72921_c(p_149674_2_, p_149674_3_, p_149674_4_, l, 2);
                }
            }
        }
    }

    public void func_149863_m(World p_149863_1_, int p_149863_2_, int p_149863_3_, int p_149863_4_)
    {
        int l = p_149863_1_.func_72805_g(p_149863_2_, p_149863_3_, p_149863_4_) + MathHelper.func_76136_a(p_149863_1_.field_73012_v, 2, 5);

        if (l > 7)
        {
            l = 7;
        }

        p_149863_1_.func_72921_c(p_149863_2_, p_149863_3_, p_149863_4_, l, 2);
    }

    private float func_149864_n(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_)
    {
        float f = 1.0F;
        Block block = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ - 1);
        Block block1 = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ + 1);
        Block block2 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_);
        Block block3 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_);
        Block block4 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ - 1);
        Block block5 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ - 1);
        Block block6 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ + 1);
        Block block7 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for (int l = p_149864_2_ - 1; l <= p_149864_2_ + 1; ++l)
        {
            for (int i1 = p_149864_4_ - 1; i1 <= p_149864_4_ + 1; ++i1)
            {
                float f1 = 0.0F;

                if (p_149864_1_.func_147439_a(l, p_149864_3_ - 1, i1) == Blocks.FARMLAND)
                {
                    f1 = 1.0F;

                    if (p_149864_1_.func_72805_g(l, p_149864_3_ - 1, i1) > 0)
                    {
                        f1 = 3.0F;
                    }
                }

                if (l != p_149864_2_ || i1 != p_149864_4_)
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
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_2_ < 0 || p_149691_2_ > 7)
        {
            p_149691_2_ = 7;
        }

        return this.field_149867_a[p_149691_2_];
    }

    public int func_149645_b()
    {
        return 6;
    }

    protected Item func_149866_i()
    {
        return Items.WHEAT_SEEDS;
    }

    protected Item func_149865_P()
    {
        return Items.WHEAT;
    }

    public void func_149690_a(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.func_149690_a(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, 0);

        if (!p_149690_1_.field_72995_K)
        {
            if (p_149690_5_ >= 7)
            {
                int j1 = 3 + p_149690_7_;

                for (int k1 = 0; k1 < j1; ++k1)
                {
                    if (p_149690_1_.field_73012_v.nextInt(15) <= p_149690_5_)
                    {
                        this.func_149642_a(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, new ItemStack(this.func_149866_i(), 1, 0));
                    }
                }
            }
        }
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return p_149650_1_ == 7 ? this.func_149865_P() : this.func_149866_i();
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
        return this.func_149866_i();
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_)
    {
        this.field_149867_a = new IIcon[8];

        for (int i = 0; i < this.field_149867_a.length; ++i)
        {
            this.field_149867_a[i] = p_149651_1_.func_94245_a(this.func_149641_N() + "_stage_" + i);
        }
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        this.func_149863_m(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_);
    }
}