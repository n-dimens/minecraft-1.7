package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.util.IIcon;

public class ItemLeaves extends ItemBlock
{
    private final BlockLeaves field_150940_b;
    private static final String __OBFID = "CL_00000046";

    public ItemLeaves(BlockLeaves p_i45344_1_)
    {
        super(p_i45344_1_);
        this.field_150940_b = p_i45344_1_;
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int p_77647_1_)
    {
        return p_77647_1_ | 4;
    }

    public String func_77667_c(ItemStack p_77667_1_)
    {
        int i = p_77667_1_.func_77960_j();

        if (i < 0 || i >= this.field_150940_b.func_150125_e().length)
        {
            i = 0;
        }

        return super.func_77658_a() + "." + this.field_150940_b.func_150125_e()[i];
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_)
    {
        return this.field_150940_b.func_149691_a(0, p_77617_1_);
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_)
    {
        return this.field_150940_b.func_149741_i(p_82790_1_.func_77960_j());
    }
}