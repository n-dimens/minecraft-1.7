package net.minecraft.potion;

public class PotionHealth extends Potion
{
    private static final String __OBFID = "CL_00001527";

    public PotionHealth(int p_i1572_1_, boolean p_i1572_2_, int p_i1572_3_)
    {
        super(p_i1572_1_, p_i1572_2_, p_i1572_3_);
    }

    public boolean func_76403_b()
    {
        return true;
    }

    public boolean func_76397_a(int p_76397_1_, int p_76397_2_)
    {
        return p_76397_1_ >= 1;
    }
}