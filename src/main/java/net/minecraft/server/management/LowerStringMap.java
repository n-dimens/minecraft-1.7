package net.minecraft.server.management;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class LowerStringMap implements Map
{
    private final Map field_76117_a = new LinkedHashMap();
    private static final String __OBFID = "CL_00001488";

    public int size()
    {
        return this.field_76117_a.size();
    }

    public boolean isEmpty()
    {
        return this.field_76117_a.isEmpty();
    }

    public boolean containsKey(Object p_containsKey_1_)
    {
        return this.field_76117_a.containsKey(p_containsKey_1_.toString().toLowerCase());
    }

    public boolean containsValue(Object p_containsValue_1_)
    {
        return this.field_76117_a.containsKey(p_containsValue_1_);
    }

    public Object get(Object p_get_1_)
    {
        return this.field_76117_a.get(p_get_1_.toString().toLowerCase());
    }

    public Object put(String p_put_1_, Object p_put_2_)
    {
        return this.field_76117_a.put(p_put_1_.toLowerCase(), p_put_2_);
    }

    public Object remove(Object p_remove_1_)
    {
        return this.field_76117_a.remove(p_remove_1_.toString().toLowerCase());
    }

    public void putAll(Map p_putAll_1_)
    {
        Iterator iterator = p_putAll_1_.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();
            this.put((String)entry.getKey(), entry.getValue());
        }
    }

    public void clear()
    {
        this.field_76117_a.clear();
    }

    public Set keySet()
    {
        return this.field_76117_a.keySet();
    }

    public Collection values()
    {
        return this.field_76117_a.values();
    }

    public Set entrySet()
    {
        return this.field_76117_a.entrySet();
    }

    public Object put(Object p_put_1_, Object p_put_2_)
    {
        return this.put((String)p_put_1_, p_put_2_);
    }
}