package net.minecraft.enchantment;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class EnchantmentUntouching extends Enchantment
{
    private static final String __OBFID = "CL_00000123";

    protected EnchantmentUntouching(int p_i1938_1_, int p_i1938_2_)
    {
        super(p_i1938_1_, p_i1938_2_, EnumEnchantmentType.digger);
        this.func_77322_b("untouching");
    }

    public int func_77321_a(int p_77321_1_)
    {
        return 15;
    }

    public int func_77317_b(int p_77317_1_)
    {
        return super.func_77321_a(p_77317_1_) + 50;
    }

    public int func_77325_b()
    {
        return 1;
    }

    public boolean func_77326_a(Enchantment p_77326_1_)
    {
        return super.func_77326_a(p_77326_1_) && p_77326_1_.field_77352_x != field_77346_s.field_77352_x;
    }

    public boolean func_92089_a(ItemStack p_92089_1_)
    {
        return p_92089_1_.getBaseItem() == Items.SHEARS ? true : super.func_92089_a(p_92089_1_);
    }
}