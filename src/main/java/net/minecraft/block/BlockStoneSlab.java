package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockStoneSlab extends BlockSlab
{
    public static final String[] field_150006_b = new String[] {"stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick", "netherBrick", "quartz"};
    @SideOnly(Side.CLIENT)
    private IIcon field_150007_M;
    private static final String __OBFID = "CL_00000320";

    public BlockStoneSlab(boolean p_i45431_1_)
    {
        super(p_i45431_1_, Material.field_151576_e);
        this.func_149647_a(CreativeTabs.field_78030_b);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
    {
        int k = p_149691_2_ & 7;

        if (this.field_150004_a && (p_149691_2_ & 8) != 0)
        {
            p_149691_1_ = 1;
        }

        return k == 0 ? (p_149691_1_ != 1 && p_149691_1_ != 0 ? this.field_150007_M : this.field_149761_L) : (k == 1 ? Blocks.field_150322_A.func_149733_h(p_149691_1_) : (k == 2 ? Blocks.field_150344_f.func_149733_h(p_149691_1_) : (k == 3 ? Blocks.field_150347_e.func_149733_h(p_149691_1_) : (k == 4 ? Blocks.field_150336_V.func_149733_h(p_149691_1_) : (k == 5 ? Blocks.field_150417_aV.func_149691_a(p_149691_1_, 0) : (k == 6 ? Blocks.field_150385_bj.func_149733_h(1) : (k == 7 ? Blocks.field_150371_ca.func_149733_h(p_149691_1_) : this.field_149761_L)))))));
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_)
    {
        this.field_149761_L = p_149651_1_.func_94245_a("stone_slab_top");
        this.field_150007_M = p_149651_1_.func_94245_a("stone_slab_side");
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.func_150898_a(Blocks.field_150333_U);
    }

    protected ItemStack func_149644_j(int p_149644_1_)
    {
        return new ItemStack(Item.func_150898_a(Blocks.field_150333_U), 2, p_149644_1_ & 7);
    }

    public String func_150002_b(int p_150002_1_)
    {
        if (p_150002_1_ < 0 || p_150002_1_ >= field_150006_b.length)
        {
            p_150002_1_ = 0;
        }

        return super.func_149739_a() + "." + field_150006_b[p_150002_1_];
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        if (p_149666_1_ != Item.func_150898_a(Blocks.field_150334_T))
        {
            for (int i = 0; i <= 7; ++i)
            {
                if (i != 2)
                {
                    p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
                }
            }
        }
    }
}