package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;

public class PotionHealthBoost extends Potion
{
    private static final String __OBFID = "CL_00001526";

    public PotionHealthBoost(int p_i1571_1_, boolean p_i1571_2_, int p_i1571_3_)
    {
        super(p_i1571_1_, p_i1571_2_, p_i1571_3_);
    }

    public void func_111187_a(EntityLivingBase p_111187_1_, BaseAttributeMap p_111187_2_, int p_111187_3_)
    {
        super.func_111187_a(p_111187_1_, p_111187_2_, p_111187_3_);

        if (p_111187_1_.func_110143_aJ() > p_111187_1_.func_110138_aP())
        {
            p_111187_1_.func_70606_j(p_111187_1_.func_110138_aP());
        }
    }
}