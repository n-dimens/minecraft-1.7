package net.minecraft.client.settings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IntHashMap;

@SideOnly(Side.CLIENT)
public class KeyBinding implements Comparable
{
    private static final List field_74516_a = new ArrayList();
    private static final IntHashMap field_74514_b = new IntHashMap();
    private static final Set field_151473_c = new HashSet();
    private final String field_74515_c;
    private final int field_151472_e;
    private final String field_151471_f;
    private int field_74512_d;
    private boolean field_74513_e;
    private int field_151474_i;
    private static final String __OBFID = "CL_00000628";

    public static void func_74507_a(int p_74507_0_)
    {
        if (p_74507_0_ != 0)
        {
            KeyBinding keybinding = (KeyBinding)field_74514_b.func_76041_a(p_74507_0_);

            if (keybinding != null)
            {
                ++keybinding.field_151474_i;
            }
        }
    }

    public static void func_74510_a(int p_74510_0_, boolean p_74510_1_)
    {
        if (p_74510_0_ != 0)
        {
            KeyBinding keybinding = (KeyBinding)field_74514_b.func_76041_a(p_74510_0_);

            if (keybinding != null)
            {
                keybinding.field_74513_e = p_74510_1_;
            }
        }
    }

    public static void func_74506_a()
    {
        Iterator iterator = field_74516_a.iterator();

        while (iterator.hasNext())
        {
            KeyBinding keybinding = (KeyBinding)iterator.next();
            keybinding.func_74505_d();
        }
    }

    public static void func_74508_b()
    {
        field_74514_b.func_76046_c();
        Iterator iterator = field_74516_a.iterator();

        while (iterator.hasNext())
        {
            KeyBinding keybinding = (KeyBinding)iterator.next();
            field_74514_b.func_76038_a(keybinding.field_74512_d, keybinding);
        }
    }

    public static Set func_151467_c()
    {
        return field_151473_c;
    }

    public KeyBinding(String p_i45001_1_, int p_i45001_2_, String p_i45001_3_)
    {
        this.field_74515_c = p_i45001_1_;
        this.field_74512_d = p_i45001_2_;
        this.field_151472_e = p_i45001_2_;
        this.field_151471_f = p_i45001_3_;
        field_74516_a.add(this);
        field_74514_b.func_76038_a(p_i45001_2_, this);
        field_151473_c.add(p_i45001_3_);
    }

    public boolean func_151470_d()
    {
        return this.field_74513_e;
    }

    public String func_151466_e()
    {
        return this.field_151471_f;
    }

    public boolean func_151468_f()
    {
        if (this.field_151474_i == 0)
        {
            return false;
        }
        else
        {
            --this.field_151474_i;
            return true;
        }
    }

    private void func_74505_d()
    {
        this.field_151474_i = 0;
        this.field_74513_e = false;
    }

    public String func_151464_g()
    {
        return this.field_74515_c;
    }

    public int func_151469_h()
    {
        return this.field_151472_e;
    }

    public int func_151463_i()
    {
        return this.field_74512_d;
    }

    public void func_151462_b(int p_151462_1_)
    {
        this.field_74512_d = p_151462_1_;
    }

    public int compareTo(KeyBinding p_compareTo_1_)
    {
        int i = I18n.func_135052_a(this.field_151471_f, new Object[0]).compareTo(I18n.func_135052_a(p_compareTo_1_.field_151471_f, new Object[0]));

        if (i == 0)
        {
            i = I18n.func_135052_a(this.field_74515_c, new Object[0]).compareTo(I18n.func_135052_a(p_compareTo_1_.field_74515_c, new Object[0]));
        }

        return i;
    }

    public int compareTo(Object p_compareTo_1_)
    {
        return this.compareTo((KeyBinding)p_compareTo_1_);
    }
}