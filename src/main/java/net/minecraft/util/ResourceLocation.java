package net.minecraft.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.apache.commons.lang3.Validate;

@SideOnly(Side.CLIENT)
public class ResourceLocation
{
    private final String field_110626_a;
    private final String field_110625_b;
    private static final String __OBFID = "CL_00001082";

    public ResourceLocation(String p_i1292_1_, String p_i1292_2_)
    {
        Validate.notNull(p_i1292_2_);

        if (p_i1292_1_ != null && p_i1292_1_.length() != 0)
        {
            this.field_110626_a = p_i1292_1_;
        }
        else
        {
            this.field_110626_a = "minecraft";
        }

        this.field_110625_b = p_i1292_2_;
    }

    public ResourceLocation(String p_i1293_1_)
    {
        String s1 = "minecraft";
        String s2 = p_i1293_1_;
        int i = p_i1293_1_.indexOf(58);

        if (i >= 0)
        {
            s2 = p_i1293_1_.substring(i + 1, p_i1293_1_.length());

            if (i > 1)
            {
                s1 = p_i1293_1_.substring(0, i);
            }
        }

        this.field_110626_a = s1.toLowerCase();
        this.field_110625_b = s2;
    }

    public String func_110623_a()
    {
        return this.field_110625_b;
    }

    public String func_110624_b()
    {
        return this.field_110626_a;
    }

    public String toString()
    {
        return this.field_110626_a + ":" + this.field_110625_b;
    }

    public boolean equals(Object p_equals_1_)
    {
        if (this == p_equals_1_)
        {
            return true;
        }
        else if (!(p_equals_1_ instanceof ResourceLocation))
        {
            return false;
        }
        else
        {
            ResourceLocation resourcelocation = (ResourceLocation)p_equals_1_;
            return this.field_110626_a.equals(resourcelocation.field_110626_a) && this.field_110625_b.equals(resourcelocation.field_110625_b);
        }
    }

    public int hashCode()
    {
        return 31 * this.field_110626_a.hashCode() + this.field_110625_b.hashCode();
    }
}