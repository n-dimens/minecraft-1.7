package net.minecraft.enchantment;

public class EnchantmentWaterWorker extends Enchantment
{
    private static final String __OBFID = "CL_00000124";

    public EnchantmentWaterWorker(int p_i1939_1_, int p_i1939_2_)
    {
        super(p_i1939_1_, p_i1939_2_, EnumEnchantmentType.armor_head);
        this.func_77322_b("waterWorker");
    }

    public int func_77321_a(int p_77321_1_)
    {
        return 1;
    }

    public int func_77317_b(int p_77317_1_)
    {
        return this.func_77321_a(p_77317_1_) + 40;
    }

    public int func_77325_b()
    {
        return 1;
    }
}