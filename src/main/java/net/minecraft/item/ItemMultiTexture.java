package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

public class ItemMultiTexture extends ItemBlock
{
    protected final Block field_150941_b;
    protected final String[] field_150942_c;
    private static final String __OBFID = "CL_00000051";

    public ItemMultiTexture(Block p_i45346_1_, Block p_i45346_2_, String[] p_i45346_3_)
    {
        super(p_i45346_1_);
        this.field_150941_b = p_i45346_2_;
        this.field_150942_c = p_i45346_3_;
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_)
    {
        return this.field_150941_b.func_149691_a(2, p_77617_1_);
    }

    public int func_77647_b(int p_77647_1_)
    {
        return p_77647_1_;
    }

    public String func_77667_c(ItemStack p_77667_1_)
    {
        int i = p_77667_1_.func_77960_j();

        if (i < 0 || i >= this.field_150942_c.length)
        {
            i = 0;
        }

        return super.func_77658_a() + "." + this.field_150942_c[i];
    }
}