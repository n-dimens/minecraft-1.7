package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDye extends Item
{
    public static final String[] field_150923_a = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};
    public static final String[] field_150921_b = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
    public static final int[] field_150922_c = new int[] {1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};
    @SideOnly(Side.CLIENT)
    private IIcon[] field_150920_d;
    private static final String __OBFID = "CL_00000022";

    public ItemDye()
    {
        this.func_77627_a(true);
        this.setDurability(0);
        this.func_77637_a(CreativeTabs.field_78035_l);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_)
    {
        int j = MathHelper.func_76125_a(p_77617_1_, 0, 15);
        return this.field_150920_d[j];
    }

    public String func_77667_c(ItemStack p_77667_1_)
    {
        int i = MathHelper.func_76125_a(p_77667_1_.func_77960_j(), 0, 15);
        return super.func_77658_a() + "." + field_150923_a[i];
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (!p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {
            return false;
        }
        else
        {
            if (p_77648_1_.func_77960_j() == 15)
            {
                if (func_150919_a(p_77648_1_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))
                {
                    if (!p_77648_3_.field_72995_K)
                    {
                        p_77648_3_.func_72926_e(2005, p_77648_4_, p_77648_5_, p_77648_6_, 0);
                    }

                    return true;
                }
            }
            else if (p_77648_1_.func_77960_j() == 3)
            {
                Block block = p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
                int i1 = p_77648_3_.func_72805_g(p_77648_4_, p_77648_5_, p_77648_6_);

                if (block == Blocks.LOG && BlockLog.func_150165_c(i1) == 3)
                {
                    if (p_77648_7_ == 0)
                    {
                        return false;
                    }

                    if (p_77648_7_ == 1)
                    {
                        return false;
                    }

                    if (p_77648_7_ == 2)
                    {
                        --p_77648_6_;
                    }

                    if (p_77648_7_ == 3)
                    {
                        ++p_77648_6_;
                    }

                    if (p_77648_7_ == 4)
                    {
                        --p_77648_4_;
                    }

                    if (p_77648_7_ == 5)
                    {
                        ++p_77648_4_;
                    }

                    if (p_77648_3_.func_147437_c(p_77648_4_, p_77648_5_, p_77648_6_))
                    {
                        int j1 = Blocks.COCOA.func_149660_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, 0);
                        p_77648_3_.func_147465_d(p_77648_4_, p_77648_5_, p_77648_6_, Blocks.COCOA, j1, 2);

                        if (!p_77648_2_.capabilities.instabuild)
                        {
                            --p_77648_1_.count;
                        }
                    }

                    return true;
                }
            }

            return false;
        }
    }

    public static boolean func_150919_a(ItemStack p_150919_0_, World p_150919_1_, int p_150919_2_, int p_150919_3_, int p_150919_4_)
    {
        Block block = p_150919_1_.func_147439_a(p_150919_2_, p_150919_3_, p_150919_4_);

        if (block instanceof IGrowable)
        {
            IGrowable igrowable = (IGrowable)block;

            if (igrowable.func_149851_a(p_150919_1_, p_150919_2_, p_150919_3_, p_150919_4_, p_150919_1_.field_72995_K))
            {
                if (!p_150919_1_.field_72995_K)
                {
                    if (igrowable.func_149852_a(p_150919_1_, p_150919_1_.field_73012_v, p_150919_2_, p_150919_3_, p_150919_4_))
                    {
                        igrowable.func_149853_b(p_150919_1_, p_150919_1_.field_73012_v, p_150919_2_, p_150919_3_, p_150919_4_);
                    }

                    --p_150919_0_.count;
                }

                return true;
            }
        }

        return false;
    }

    @SideOnly(Side.CLIENT)
    public static void func_150918_a(World p_150918_0_, int p_150918_1_, int p_150918_2_, int p_150918_3_, int p_150918_4_)
    {
        if (p_150918_4_ == 0)
        {
            p_150918_4_ = 15;
        }

        Block block = p_150918_0_.func_147439_a(p_150918_1_, p_150918_2_, p_150918_3_);

        if (block.func_149688_o() != Material.field_151579_a)
        {
            block.func_149719_a(p_150918_0_, p_150918_1_, p_150918_2_, p_150918_3_);

            for (int i1 = 0; i1 < p_150918_4_; ++i1)
            {
                double d0 = randomizer.nextGaussian() * 0.02D;
                double d1 = randomizer.nextGaussian() * 0.02D;
                double d2 = randomizer.nextGaussian() * 0.02D;
                p_150918_0_.func_72869_a("happyVillager", (double)((float)p_150918_1_ + randomizer.nextFloat()), (double)p_150918_2_ + (double) randomizer.nextFloat() * block.func_149669_A(), (double)((float)p_150918_3_ + randomizer.nextFloat()), d0, d1, d2);
            }
        }
    }

    public boolean func_111207_a(ItemStack p_111207_1_, EntityPlayer p_111207_2_, EntityLivingBase p_111207_3_)
    {
        if (p_111207_3_ instanceof EntitySheep)
        {
            EntitySheep entitysheep = (EntitySheep)p_111207_3_;
            int i = BlockColored.func_150032_b(p_111207_1_.func_77960_j());

            if (!entitysheep.func_70892_o() && entitysheep.func_70896_n() != i)
            {
                entitysheep.func_70891_b(i);
                --p_111207_1_.count;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
        for (int i = 0; i < 16; ++i)
        {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_)
    {
        this.field_150920_d = new IIcon[field_150921_b.length];

        for (int i = 0; i < field_150921_b.length; ++i)
        {
            this.field_150920_d[i] = p_94581_1_.func_94245_a(this.func_111208_A() + "_" + field_150921_b[i]);
        }
    }
}