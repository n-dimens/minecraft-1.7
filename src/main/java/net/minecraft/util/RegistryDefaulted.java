package net.minecraft.util;

public class RegistryDefaulted extends RegistrySimple
{
    private final Object field_82597_b;
    private static final String __OBFID = "CL_00001198";

    public RegistryDefaulted(Object p_i1366_1_)
    {
        this.field_82597_b = p_i1366_1_;
    }

    @Override
    public Object get(Object key)
    {
        Object object1 = super.get(key);
        return object1 == null ? this.field_82597_b : object1;
    }
}