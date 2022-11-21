package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLadder extends Block
{
    private static final String __OBFID = "CL_00000262";

    protected BlockLadder()
    {
        super(Material.field_151594_q);
        this.func_149647_a(CreativeTabs.field_78031_c);
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        this.func_149719_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
        return super.func_149668_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
    }

    public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        this.func_149797_b(p_149719_1_.func_72805_g(p_149719_2_, p_149719_3_, p_149719_4_));
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
    {
        this.func_149719_a(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
        return super.func_149633_g(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
    }

    public void func_149797_b(int p_149797_1_)
    {
        float f = 0.125F;

        if (p_149797_1_ == 2)
        {
            this.func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }

        if (p_149797_1_ == 3)
        {
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }

        if (p_149797_1_ == 4)
        {
            this.func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (p_149797_1_ == 5)
        {
            this.func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
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
        return 8;
    }

    public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return p_149742_1_.func_147439_a(p_149742_2_ - 1, p_149742_3_, p_149742_4_).func_149721_r() ? true : (p_149742_1_.func_147439_a(p_149742_2_ + 1, p_149742_3_, p_149742_4_).func_149721_r() ? true : (p_149742_1_.func_147439_a(p_149742_2_, p_149742_3_, p_149742_4_ - 1).func_149721_r() ? true : p_149742_1_.func_147439_a(p_149742_2_, p_149742_3_, p_149742_4_ + 1).func_149721_r()));
    }

    public int func_149660_a(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
    {
        int j1 = p_149660_9_;

        if ((p_149660_9_ == 0 || p_149660_5_ == 2) && p_149660_1_.func_147439_a(p_149660_2_, p_149660_3_, p_149660_4_ + 1).func_149721_r())
        {
            j1 = 2;
        }

        if ((j1 == 0 || p_149660_5_ == 3) && p_149660_1_.func_147439_a(p_149660_2_, p_149660_3_, p_149660_4_ - 1).func_149721_r())
        {
            j1 = 3;
        }

        if ((j1 == 0 || p_149660_5_ == 4) && p_149660_1_.func_147439_a(p_149660_2_ + 1, p_149660_3_, p_149660_4_).func_149721_r())
        {
            j1 = 4;
        }

        if ((j1 == 0 || p_149660_5_ == 5) && p_149660_1_.func_147439_a(p_149660_2_ - 1, p_149660_3_, p_149660_4_).func_149721_r())
        {
            j1 = 5;
        }

        return j1;
    }

    public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        int l = p_149695_1_.func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_);
        boolean flag = false;

        if (l == 2 && p_149695_1_.func_147439_a(p_149695_2_, p_149695_3_, p_149695_4_ + 1).func_149721_r())
        {
            flag = true;
        }

        if (l == 3 && p_149695_1_.func_147439_a(p_149695_2_, p_149695_3_, p_149695_4_ - 1).func_149721_r())
        {
            flag = true;
        }

        if (l == 4 && p_149695_1_.func_147439_a(p_149695_2_ + 1, p_149695_3_, p_149695_4_).func_149721_r())
        {
            flag = true;
        }

        if (l == 5 && p_149695_1_.func_147439_a(p_149695_2_ - 1, p_149695_3_, p_149695_4_).func_149721_r())
        {
            flag = true;
        }

        if (!flag)
        {
            this.func_149697_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, l, 0);
            p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
        }

        super.func_149695_a(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
    }

    public int func_149745_a(Random p_149745_1_)
    {
        return 1;
    }
}