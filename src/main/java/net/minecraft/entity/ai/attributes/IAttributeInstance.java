package net.minecraft.entity.ai.attributes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collection;
import java.util.UUID;

public interface IAttributeInstance
{
    IAttribute func_111123_a();

    double func_111125_b();

    void func_111128_a(double p_111128_1_);

    Collection func_111122_c();

    AttributeModifier func_111127_a(UUID p_111127_1_);

    void func_111121_a(AttributeModifier p_111121_1_);

    void func_111124_b(AttributeModifier p_111124_1_);

    @SideOnly(Side.CLIENT)
    void func_142049_d();

    double func_111126_e();
}