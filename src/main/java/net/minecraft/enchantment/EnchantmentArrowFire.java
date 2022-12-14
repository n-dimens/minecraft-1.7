package net.minecraft.enchantment;

public class EnchantmentArrowFire extends Enchantment
{
    private static final String __OBFID = "CL_00000099";

    public EnchantmentArrowFire(int p_i1920_1_, int p_i1920_2_)
    {
        super(p_i1920_1_, p_i1920_2_, EnumEnchantmentType.bow);
        this.func_77322_b("arrowFire");
    }

    public int func_77321_a(int p_77321_1_)
    {
        return 20;
    }

    public int func_77317_b(int p_77317_1_)
    {
        return 50;
    }

    public int func_77325_b()
    {
        return 1;
    }
}