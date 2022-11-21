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
    private final String id;
    private final int defaultEventKey;
    private final String category;
    private int eventKey;
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
            field_74514_b.func_76038_a(keybinding.eventKey, keybinding);
        }
    }

    public static Set func_151467_c()
    {
        return field_151473_c;
    }

    public KeyBinding(String id, int key, String category)
    {
        this.id = id;
        this.eventKey = key;
        this.defaultEventKey = key;
        this.category = category;
        field_74516_a.add(this);
        field_74514_b.func_76038_a(key, this);
        field_151473_c.add(category);
    }

    public boolean func_151470_d()
    {
        return this.field_74513_e;
    }

    public String getCategory()
    {
        return this.category;
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

    public String getId()
    {
        return this.id;
    }

    public int getDefaultEventKey()
    {
        return this.defaultEventKey;
    }

    public int getEventKey()
    {
        return this.eventKey;
    }

    public void setEventKey(int value)
    {
        this.eventKey = value;
    }

    public int compareTo(KeyBinding keyBinding)
    {
        int i = I18n.func_135052_a(this.category, new Object[0]).compareTo(I18n.func_135052_a(keyBinding.category, new Object[0]));

        if (i == 0)
        {
            i = I18n.func_135052_a(this.id, new Object[0]).compareTo(I18n.func_135052_a(keyBinding.id, new Object[0]));
        }

        return i;
    }

    public int compareTo(Object p_compareTo_1_)
    {
        return this.compareTo((KeyBinding)p_compareTo_1_);
    }
}