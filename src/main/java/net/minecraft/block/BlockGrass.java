package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockGrass extends Block implements IGrowable
{
    private static final Logger field_149992_a = LogManager.getLogger();
    @SideOnly(Side.CLIENT)
    private IIcon field_149991_b;
    @SideOnly(Side.CLIENT)
    private IIcon field_149993_M;
    @SideOnly(Side.CLIENT)
    private IIcon field_149994_N;
    private static final String __OBFID = "CL_00000251";

    protected BlockGrass()
    {
        super(Material.field_151577_b);
        this.func_149675_a(true);
        this.func_149647_a(CreativeTabs.field_78030_b);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.field_149991_b : (p_149691_1_ == 0 ? Blocks.field_150346_d.func_149733_h(p_149691_1_) : this.field_149761_L);
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (!p_149674_1_.field_72995_K)
        {
            if (p_149674_1_.func_72957_l(p_149674_2_, p_149674_3_ + 1, p_149674_4_) < 4 && p_149674_1_.func_147439_a(p_149674_2_, p_149674_3_ + 1, p_149674_4_).func_149717_k() > 2)
            {
                p_149674_1_.func_147449_b(p_149674_2_, p_149674_3_, p_149674_4_, Blocks.field_150346_d);
            }
            else if (p_149674_1_.func_72957_l(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int i1 = p_149674_2_ + p_149674_5_.nextInt(3) - 1;
                    int j1 = p_149674_3_ + p_149674_5_.nextInt(5) - 3;
                    int k1 = p_149674_4_ + p_149674_5_.nextInt(3) - 1;
                    Block block = p_149674_1_.func_147439_a(i1, j1 + 1, k1);

                    if (p_149674_1_.func_147439_a(i1, j1, k1) == Blocks.field_150346_d && p_149674_1_.func_72805_g(i1, j1, k1) == 0 && p_149674_1_.func_72957_l(i1, j1 + 1, k1) >= 4 && block.func_149717_k() <= 2)
                    {
                        p_149674_1_.func_147449_b(i1, j1, k1, Blocks.field_150349_c);
                    }
                }
            }
        }
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Blocks.field_150346_d.func_149650_a(0, p_149650_2_, p_149650_3_);
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
    {
        if (p_149673_5_ == 1)
        {
            return this.field_149991_b;
        }
        else if (p_149673_5_ == 0)
        {
            return Blocks.field_150346_d.func_149733_h(p_149673_5_);
        }
        else
        {
            Material material = p_149673_1_.func_147439_a(p_149673_2_, p_149673_3_ + 1, p_149673_4_).func_149688_o();
            return material != Material.field_151597_y && material != Material.field_151596_z ? this.field_149761_L : this.field_149993_M;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_)
    {
        this.field_149761_L = p_149651_1_.func_94245_a(this.func_149641_N() + "_side");
        this.field_149991_b = p_149651_1_.func_94245_a(this.func_149641_N() + "_top");
        this.field_149993_M = p_149651_1_.func_94245_a(this.func_149641_N() + "_side_snowed");
        this.field_149994_N = p_149651_1_.func_94245_a(this.func_149641_N() + "_side_overlay");
    }

    @SideOnly(Side.CLIENT)
    public int func_149635_D()
    {
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerGrass.func_77480_a(d0, d1);
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int p_149741_1_)
    {
        return this.func_149635_D();
    }

    @SideOnly(Side.CLIENT)
    public int func_149720_d(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = -1; k1 <= 1; ++k1)
        {
            for (int l1 = -1; l1 <= 1; ++l1)
            {
                int i2 = p_149720_1_.func_72807_a(p_149720_2_ + l1, p_149720_4_ + k1).func_150558_b(p_149720_2_ + l1, p_149720_3_, p_149720_4_ + k1);
                l += (i2 & 16711680) >> 16;
                i1 += (i2 & 65280) >> 8;
                j1 += i2 & 255;
            }
        }

        return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
    }

    @SideOnly(Side.CLIENT)
    public static IIcon func_149990_e()
    {
        return Blocks.field_150349_c.field_149994_N;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        int l = 0;

        while (l < 128)
        {
            int i1 = p_149853_3_;
            int j1 = p_149853_4_ + 1;
            int k1 = p_149853_5_;
            int l1 = 0;

            while (true)
            {
                if (l1 < l / 16)
                {
                    i1 += p_149853_2_.nextInt(3) - 1;
                    j1 += (p_149853_2_.nextInt(3) - 1) * p_149853_2_.nextInt(3) / 2;
                    k1 += p_149853_2_.nextInt(3) - 1;

                    if (p_149853_1_.func_147439_a(i1, j1 - 1, k1) == Blocks.field_150349_c && !p_149853_1_.func_147439_a(i1, j1, k1).func_149721_r())
                    {
                        ++l1;
                        continue;
                    }
                }
                else if (p_149853_1_.func_147439_a(i1, j1, k1).field_149764_J == Material.field_151579_a)
                {
                    if (p_149853_2_.nextInt(8) != 0)
                    {
                        if (Blocks.field_150329_H.func_149718_j(p_149853_1_, i1, j1, k1))
                        {
                            p_149853_1_.func_147465_d(i1, j1, k1, Blocks.field_150329_H, 1, 3);
                        }
                    }
                    else
                    {
                        String s = p_149853_1_.func_72807_a(i1, k1).func_150572_a(p_149853_2_, i1, j1, k1);
                        field_149992_a.debug("Flower in " + p_149853_1_.func_72807_a(i1, k1).field_76791_y + ": " + s);
                        BlockFlower blockflower = BlockFlower.func_149857_e(s);

                        if (blockflower != null && blockflower.func_149718_j(p_149853_1_, i1, j1, k1))
                        {
                            int i2 = BlockFlower.func_149856_f(s);
                            p_149853_1_.func_147465_d(i1, j1, k1, blockflower, i2, 3);
                        }
                    }
                }

                ++l;
                break;
            }
        }
    }
}