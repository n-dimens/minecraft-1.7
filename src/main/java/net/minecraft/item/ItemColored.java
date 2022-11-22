package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

public class ItemColored extends ItemBlock
{
    private final Block field_150944_b;
    private String[] field_150945_c;
    private static final String __OBFID = "CL_00000003";

    public ItemColored(Block p_i45332_1_, boolean p_i45332_2_)
    {
        super(p_i45332_1_);
        this.field_150944_b = p_i45332_1_;

        if (p_i45332_2_)
        {
            this.setDurability(0);
            this.func_77627_a(true);
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_)
    {
        return this.field_150944_b.func_149741_i(p_82790_1_.func_77960_j());
    }

    public int func_77647_b(int p_77647_1_)
    {
        return p_77647_1_;
    }

    public ItemColored func_150943_a(String[] p_150943_1_)
    {
        this.field_150945_c = p_150943_1_;
        return this;
    }

    public String func_77667_c(ItemStack p_77667_1_)
    {
        if (this.field_150945_c == null)
        {
            return super.func_77667_c(p_77667_1_);
        }
        else
        {
            int i = p_77667_1_.func_77960_j();
            return i >= 0 && i < this.field_150945_c.length ? super.func_77667_c(p_77667_1_) + "." + this.field_150945_c[i] : super.func_77667_c(p_77667_1_);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_)
    {
        return this.field_150944_b.func_149691_a(0, p_77617_1_);
    }
}