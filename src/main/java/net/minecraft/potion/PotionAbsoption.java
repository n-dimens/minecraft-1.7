package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;

public class PotionAbsoption extends Potion
{
    private static final String __OBFID = "CL_00001524";

    protected PotionAbsoption(int p_i1569_1_, boolean p_i1569_2_, int p_i1569_3_)
    {
        super(p_i1569_1_, p_i1569_2_, p_i1569_3_);
    }

    public void func_111187_a(EntityLivingBase p_111187_1_, BaseAttributeMap p_111187_2_, int p_111187_3_)
    {
        p_111187_1_.func_110149_m(p_111187_1_.func_110139_bj() - (float)(4 * (p_111187_3_ + 1)));
        super.func_111187_a(p_111187_1_, p_111187_2_, p_111187_3_);
    }

    public void func_111185_a(EntityLivingBase p_111185_1_, BaseAttributeMap p_111185_2_, int p_111185_3_)
    {
        p_111185_1_.func_110149_m(p_111185_1_.func_110139_bj() + (float)(4 * (p_111185_3_ + 1)));
        super.func_111185_a(p_111185_1_, p_111185_2_, p_111185_3_);
    }
}