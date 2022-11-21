package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;

public class BlockFire extends Block
{
    private int[] field_149849_a = new int[256];
    private int[] field_149848_b = new int[256];
    @SideOnly(Side.CLIENT)
    private IIcon[] field_149850_M;
    private static final String __OBFID = "CL_00000245";

    protected BlockFire()
    {
        super(Material.field_151581_o);
        this.func_149675_a(true);
    }

    public static void func_149843_e()
    {
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.PLANKS), 5, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.DOUBLE_WOODEN_SLAB), 5, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.WOODEN_SLAB), 5, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.FENCE), 5, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.OAK_STAIRS), 5, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.BIRCH_STAIRS), 5, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.SPRUCE_STAIRS), 5, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.JUNGLE_STAIRS), 5, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.LOG), 5, 5);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.LOG_2), 5, 5);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.LEAVES), 30, 60);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.LEAVES_2), 30, 60);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.BOOKSHELF), 30, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.TNT), 15, 100);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.TALLGRASS), 60, 100);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.DOUBLE_PLANT), 60, 100);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.YELLOW_FLOWER), 60, 100);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.RED_FLOWER), 60, 100);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.WOOL), 30, 60);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.VINE), 15, 100);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.COAL_BLOCK), 5, 5);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.HAY_BLOCK), 60, 20);
        Blocks.FIRE.func_149842_a(func_149682_b(Blocks.CARPET), 60, 20);
    }

    public void func_149842_a(int p_149842_1_, int p_149842_2_, int p_149842_3_)
    {
        this.field_149849_a[p_149842_1_] = p_149842_2_;
        this.field_149848_b[p_149842_1_] = p_149842_3_;
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }

    public boolean func_149662_c()
    {
        return false;
    }

    public boolean func_149686_d()
    {
        return false;
    }

    public int func_149645_b()
    {
        return 3;
    }

    public int func_149745_a(Random p_149745_1_)
    {
        return 0;
    }

    public int func_149738_a(World p_149738_1_)
    {
        return 30;
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (p_149674_1_.func_82736_K().getBooleanValue("doFireTick"))
        {
            boolean flag = p_149674_1_.func_147439_a(p_149674_2_, p_149674_3_ - 1, p_149674_4_) == Blocks.NETHERRACK;

            if (p_149674_1_.field_73011_w instanceof WorldProviderEnd && p_149674_1_.func_147439_a(p_149674_2_, p_149674_3_ - 1, p_149674_4_) == Blocks.BEDROCK)
            {
                flag = true;
            }

            if (!this.func_149742_c(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_))
            {
                p_149674_1_.func_147468_f(p_149674_2_, p_149674_3_, p_149674_4_);
            }

            if (!flag && p_149674_1_.func_72896_J() && (p_149674_1_.func_72951_B(p_149674_2_, p_149674_3_, p_149674_4_) || p_149674_1_.func_72951_B(p_149674_2_ - 1, p_149674_3_, p_149674_4_) || p_149674_1_.func_72951_B(p_149674_2_ + 1, p_149674_3_, p_149674_4_) || p_149674_1_.func_72951_B(p_149674_2_, p_149674_3_, p_149674_4_ - 1) || p_149674_1_.func_72951_B(p_149674_2_, p_149674_3_, p_149674_4_ + 1)))
            {
                p_149674_1_.func_147468_f(p_149674_2_, p_149674_3_, p_149674_4_);
            }
            else
            {
                int l = p_149674_1_.func_72805_g(p_149674_2_, p_149674_3_, p_149674_4_);

                if (l < 15)
                {
                    p_149674_1_.func_72921_c(p_149674_2_, p_149674_3_, p_149674_4_, l + p_149674_5_.nextInt(3) / 2, 4);
                }

                p_149674_1_.func_147464_a(p_149674_2_, p_149674_3_, p_149674_4_, this, this.func_149738_a(p_149674_1_) + p_149674_5_.nextInt(10));

                if (!flag && !this.func_149847_e(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_))
                {
                    if (!World.func_147466_a(p_149674_1_, p_149674_2_, p_149674_3_ - 1, p_149674_4_) || l > 3)
                    {
                        p_149674_1_.func_147468_f(p_149674_2_, p_149674_3_, p_149674_4_);
                    }
                }
                else if (!flag && !this.func_149844_e(p_149674_1_, p_149674_2_, p_149674_3_ - 1, p_149674_4_) && l == 15 && p_149674_5_.nextInt(4) == 0)
                {
                    p_149674_1_.func_147468_f(p_149674_2_, p_149674_3_, p_149674_4_);
                }
                else
                {
                    boolean flag1 = p_149674_1_.func_72958_C(p_149674_2_, p_149674_3_, p_149674_4_);
                    byte b0 = 0;

                    if (flag1)
                    {
                        b0 = -50;
                    }

                    this.func_149841_a(p_149674_1_, p_149674_2_ + 1, p_149674_3_, p_149674_4_, 300 + b0, p_149674_5_, l);
                    this.func_149841_a(p_149674_1_, p_149674_2_ - 1, p_149674_3_, p_149674_4_, 300 + b0, p_149674_5_, l);
                    this.func_149841_a(p_149674_1_, p_149674_2_, p_149674_3_ - 1, p_149674_4_, 250 + b0, p_149674_5_, l);
                    this.func_149841_a(p_149674_1_, p_149674_2_, p_149674_3_ + 1, p_149674_4_, 250 + b0, p_149674_5_, l);
                    this.func_149841_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_ - 1, 300 + b0, p_149674_5_, l);
                    this.func_149841_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_ + 1, 300 + b0, p_149674_5_, l);

                    for (int i1 = p_149674_2_ - 1; i1 <= p_149674_2_ + 1; ++i1)
                    {
                        for (int j1 = p_149674_4_ - 1; j1 <= p_149674_4_ + 1; ++j1)
                        {
                            for (int k1 = p_149674_3_ - 1; k1 <= p_149674_3_ + 4; ++k1)
                            {
                                if (i1 != p_149674_2_ || k1 != p_149674_3_ || j1 != p_149674_4_)
                                {
                                    int l1 = 100;

                                    if (k1 > p_149674_3_ + 1)
                                    {
                                        l1 += (k1 - (p_149674_3_ + 1)) * 100;
                                    }

                                    int i2 = this.func_149845_m(p_149674_1_, i1, k1, j1);

                                    if (i2 > 0)
                                    {
                                        int j2 = (i2 + 40 + p_149674_1_.field_73013_u.func_151525_a() * 7) / (l + 30);

                                        if (flag1)
                                        {
                                            j2 /= 2;
                                        }

                                        if (j2 > 0 && p_149674_5_.nextInt(l1) <= j2 && (!p_149674_1_.func_72896_J() || !p_149674_1_.func_72951_B(i1, k1, j1)) && !p_149674_1_.func_72951_B(i1 - 1, k1, p_149674_4_) && !p_149674_1_.func_72951_B(i1 + 1, k1, j1) && !p_149674_1_.func_72951_B(i1, k1, j1 - 1) && !p_149674_1_.func_72951_B(i1, k1, j1 + 1))
                                        {
                                            int k2 = l + p_149674_5_.nextInt(5) / 4;

                                            if (k2 > 15)
                                            {
                                                k2 = 15;
                                            }

                                            p_149674_1_.func_147465_d(i1, k1, j1, this, k2, 3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean func_149698_L()
    {
        return false;
    }

    private void func_149841_a(World p_149841_1_, int p_149841_2_, int p_149841_3_, int p_149841_4_, int p_149841_5_, Random p_149841_6_, int p_149841_7_)
    {
        int j1 = this.field_149848_b[Block.func_149682_b(p_149841_1_.func_147439_a(p_149841_2_, p_149841_3_, p_149841_4_))];

        if (p_149841_6_.nextInt(p_149841_5_) < j1)
        {
            boolean flag = p_149841_1_.func_147439_a(p_149841_2_, p_149841_3_, p_149841_4_) == Blocks.TNT;

            if (p_149841_6_.nextInt(p_149841_7_ + 10) < 5 && !p_149841_1_.func_72951_B(p_149841_2_, p_149841_3_, p_149841_4_))
            {
                int k1 = p_149841_7_ + p_149841_6_.nextInt(5) / 4;

                if (k1 > 15)
                {
                    k1 = 15;
                }

                p_149841_1_.func_147465_d(p_149841_2_, p_149841_3_, p_149841_4_, this, k1, 3);
            }
            else
            {
                p_149841_1_.func_147468_f(p_149841_2_, p_149841_3_, p_149841_4_);
            }

            if (flag)
            {
                Blocks.TNT.func_149664_b(p_149841_1_, p_149841_2_, p_149841_3_, p_149841_4_, 1);
            }
        }
    }

    private boolean func_149847_e(World p_149847_1_, int p_149847_2_, int p_149847_3_, int p_149847_4_)
    {
        return this.func_149844_e(p_149847_1_, p_149847_2_ + 1, p_149847_3_, p_149847_4_) ? true : (this.func_149844_e(p_149847_1_, p_149847_2_ - 1, p_149847_3_, p_149847_4_) ? true : (this.func_149844_e(p_149847_1_, p_149847_2_, p_149847_3_ - 1, p_149847_4_) ? true : (this.func_149844_e(p_149847_1_, p_149847_2_, p_149847_3_ + 1, p_149847_4_) ? true : (this.func_149844_e(p_149847_1_, p_149847_2_, p_149847_3_, p_149847_4_ - 1) ? true : this.func_149844_e(p_149847_1_, p_149847_2_, p_149847_3_, p_149847_4_ + 1)))));
    }

    private int func_149845_m(World p_149845_1_, int p_149845_2_, int p_149845_3_, int p_149845_4_)
    {
        byte b0 = 0;

        if (!p_149845_1_.func_147437_c(p_149845_2_, p_149845_3_, p_149845_4_))
        {
            return 0;
        }
        else
        {
            int l = this.func_149846_a(p_149845_1_, p_149845_2_ + 1, p_149845_3_, p_149845_4_, b0);
            l = this.func_149846_a(p_149845_1_, p_149845_2_ - 1, p_149845_3_, p_149845_4_, l);
            l = this.func_149846_a(p_149845_1_, p_149845_2_, p_149845_3_ - 1, p_149845_4_, l);
            l = this.func_149846_a(p_149845_1_, p_149845_2_, p_149845_3_ + 1, p_149845_4_, l);
            l = this.func_149846_a(p_149845_1_, p_149845_2_, p_149845_3_, p_149845_4_ - 1, l);
            l = this.func_149846_a(p_149845_1_, p_149845_2_, p_149845_3_, p_149845_4_ + 1, l);
            return l;
        }
    }

    public boolean func_149703_v()
    {
        return false;
    }

    public boolean func_149844_e(IBlockAccess p_149844_1_, int p_149844_2_, int p_149844_3_, int p_149844_4_)
    {
        return this.field_149849_a[Block.func_149682_b(p_149844_1_.func_147439_a(p_149844_2_, p_149844_3_, p_149844_4_))] > 0;
    }

    public int func_149846_a(World p_149846_1_, int p_149846_2_, int p_149846_3_, int p_149846_4_, int p_149846_5_)
    {
        int i1 = this.field_149849_a[Block.func_149682_b(p_149846_1_.func_147439_a(p_149846_2_, p_149846_3_, p_149846_4_))];
        return i1 > p_149846_5_ ? i1 : p_149846_5_;
    }

    public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return World.func_147466_a(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_) || this.func_149847_e(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_);
    }

    public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        if (!World.func_147466_a(p_149695_1_, p_149695_2_, p_149695_3_ - 1, p_149695_4_) && !this.func_149847_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_))
        {
            p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
        }
    }

    public void func_149726_b(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
    {
        if (p_149726_1_.field_73011_w.field_76574_g > 0 || !Blocks.PORTAL.func_150000_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_))
        {
            if (!World.func_147466_a(p_149726_1_, p_149726_2_, p_149726_3_ - 1, p_149726_4_) && !this.func_149847_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_))
            {
                p_149726_1_.func_147468_f(p_149726_2_, p_149726_3_, p_149726_4_);
            }
            else
            {
                p_149726_1_.func_147464_a(p_149726_2_, p_149726_3_, p_149726_4_, this, this.func_149738_a(p_149726_1_) + p_149726_1_.field_73012_v.nextInt(10));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
        if (p_149734_5_.nextInt(24) == 0)
        {
            p_149734_1_.func_72980_b((double)((float)p_149734_2_ + 0.5F), (double)((float)p_149734_3_ + 0.5F), (double)((float)p_149734_4_ + 0.5F), "fire.fire", 1.0F + p_149734_5_.nextFloat(), p_149734_5_.nextFloat() * 0.7F + 0.3F, false);
        }

        int l;
        float f;
        float f1;
        float f2;

        if (!World.func_147466_a(p_149734_1_, p_149734_2_, p_149734_3_ - 1, p_149734_4_) && !Blocks.FIRE.func_149844_e(p_149734_1_, p_149734_2_, p_149734_3_ - 1, p_149734_4_))
        {
            if (Blocks.FIRE.func_149844_e(p_149734_1_, p_149734_2_ - 1, p_149734_3_, p_149734_4_))
            {
                for (l = 0; l < 2; ++l)
                {
                    f = (float)p_149734_2_ + p_149734_5_.nextFloat() * 0.1F;
                    f1 = (float)p_149734_3_ + p_149734_5_.nextFloat();
                    f2 = (float)p_149734_4_ + p_149734_5_.nextFloat();
                    p_149734_1_.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Blocks.FIRE.func_149844_e(p_149734_1_, p_149734_2_ + 1, p_149734_3_, p_149734_4_))
            {
                for (l = 0; l < 2; ++l)
                {
                    f = (float)(p_149734_2_ + 1) - p_149734_5_.nextFloat() * 0.1F;
                    f1 = (float)p_149734_3_ + p_149734_5_.nextFloat();
                    f2 = (float)p_149734_4_ + p_149734_5_.nextFloat();
                    p_149734_1_.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Blocks.FIRE.func_149844_e(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_ - 1))
            {
                for (l = 0; l < 2; ++l)
                {
                    f = (float)p_149734_2_ + p_149734_5_.nextFloat();
                    f1 = (float)p_149734_3_ + p_149734_5_.nextFloat();
                    f2 = (float)p_149734_4_ + p_149734_5_.nextFloat() * 0.1F;
                    p_149734_1_.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Blocks.FIRE.func_149844_e(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_ + 1))
            {
                for (l = 0; l < 2; ++l)
                {
                    f = (float)p_149734_2_ + p_149734_5_.nextFloat();
                    f1 = (float)p_149734_3_ + p_149734_5_.nextFloat();
                    f2 = (float)(p_149734_4_ + 1) - p_149734_5_.nextFloat() * 0.1F;
                    p_149734_1_.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Blocks.FIRE.func_149844_e(p_149734_1_, p_149734_2_, p_149734_3_ + 1, p_149734_4_))
            {
                for (l = 0; l < 2; ++l)
                {
                    f = (float)p_149734_2_ + p_149734_5_.nextFloat();
                    f1 = (float)(p_149734_3_ + 1) - p_149734_5_.nextFloat() * 0.1F;
                    f2 = (float)p_149734_4_ + p_149734_5_.nextFloat();
                    p_149734_1_.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
        else
        {
            for (l = 0; l < 3; ++l)
            {
                f = (float)p_149734_2_ + p_149734_5_.nextFloat();
                f1 = (float)p_149734_3_ + p_149734_5_.nextFloat() * 0.5F + 0.5F;
                f2 = (float)p_149734_4_ + p_149734_5_.nextFloat();
                p_149734_1_.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_)
    {
        this.field_149850_M = new IIcon[] {p_149651_1_.func_94245_a(this.func_149641_N() + "_layer_0"), p_149651_1_.func_94245_a(this.func_149641_N() + "_layer_1")};
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149840_c(int p_149840_1_)
    {
        return this.field_149850_M[p_149840_1_];
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
    {
        return this.field_149850_M[0];
    }

    public MapColor func_149728_f(int p_149728_1_)
    {
        return MapColor.field_151656_f;
    }
}