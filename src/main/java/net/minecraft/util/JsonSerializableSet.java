package net.minecraft.util;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class JsonSerializableSet extends ForwardingSet implements IJsonSerializable
{
    private final Set field_151004_a = Sets.newHashSet();
    private static final String __OBFID = "CL_00001482";

    public void func_152753_a(JsonElement p_152753_1_)
    {
        if (p_152753_1_.isJsonArray())
        {
            Iterator iterator = p_152753_1_.getAsJsonArray().iterator();

            while (iterator.hasNext())
            {
                JsonElement jsonelement1 = (JsonElement)iterator.next();
                this.add(jsonelement1.getAsString());
            }
        }
    }

    public JsonElement func_151003_a()
    {
        JsonArray jsonarray = new JsonArray();
        Iterator iterator = this.iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            jsonarray.add(new JsonPrimitive(s));
        }

        return jsonarray;
    }

    protected Set delegate()
    {
        return this.field_151004_a;
    }
}