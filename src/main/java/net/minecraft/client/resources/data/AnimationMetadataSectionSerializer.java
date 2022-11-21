package net.minecraft.client.resources.data;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Type;
import java.util.ArrayList;
import net.minecraft.util.JsonUtils;
import org.apache.commons.lang3.Validate;

@SideOnly(Side.CLIENT)
public class AnimationMetadataSectionSerializer extends BaseMetadataSectionSerializer implements JsonSerializer
{
    private static final String __OBFID = "CL_00001107";

    public AnimationMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_)
    {
        ArrayList arraylist = Lists.newArrayList();
        JsonObject jsonobject = JsonUtils.func_151210_l(p_deserialize_1_, "metadata section");
        int i = JsonUtils.func_151208_a(jsonobject, "frametime", 1);

        if (i != 1)
        {
            Validate.inclusiveBetween(Integer.valueOf(1), Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(i), "Invalid default frame time", new Object[0]);
        }

        int j;

        if (jsonobject.has("frames"))
        {
            try
            {
                JsonArray jsonarray = JsonUtils.func_151214_t(jsonobject, "frames");

                for (j = 0; j < jsonarray.size(); ++j)
                {
                    JsonElement jsonelement1 = jsonarray.get(j);
                    AnimationFrame animationframe = this.func_110492_a(j, jsonelement1);

                    if (animationframe != null)
                    {
                        arraylist.add(animationframe);
                    }
                }
            }
            catch (ClassCastException classcastexception)
            {
                throw new JsonParseException("Invalid animation->frames: expected array, was " + jsonobject.get("frames"), classcastexception);
            }
        }

        int k = JsonUtils.func_151208_a(jsonobject, "width", -1);
        j = JsonUtils.func_151208_a(jsonobject, "height", -1);

        if (k != -1)
        {
            Validate.inclusiveBetween(Integer.valueOf(1), Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(k), "Invalid width", new Object[0]);
        }

        if (j != -1)
        {
            Validate.inclusiveBetween(Integer.valueOf(1), Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(j), "Invalid height", new Object[0]);
        }

        return new AnimationMetadataSection(arraylist, k, j, i);
    }

    private AnimationFrame func_110492_a(int p_110492_1_, JsonElement p_110492_2_)
    {
        if (p_110492_2_.isJsonPrimitive())
        {
            return new AnimationFrame(JsonUtils.func_151215_f(p_110492_2_, "frames[" + p_110492_1_ + "]"));
        }
        else if (p_110492_2_.isJsonObject())
        {
            JsonObject jsonobject = JsonUtils.func_151210_l(p_110492_2_, "frames[" + p_110492_1_ + "]");
            int j = JsonUtils.func_151208_a(jsonobject, "time", -1);

            if (jsonobject.has("time"))
            {
                Validate.inclusiveBetween(Integer.valueOf(1), Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(j), "Invalid frame time", new Object[0]);
            }

            int k = JsonUtils.func_151203_m(jsonobject, "index");
            Validate.inclusiveBetween(Integer.valueOf(0), Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(k), "Invalid frame index", new Object[0]);
            return new AnimationFrame(k, j);
        }
        else
        {
            return null;
        }
    }

    public JsonElement serialize(AnimationMetadataSection p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_)
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("frametime", Integer.valueOf(p_serialize_1_.func_110469_d()));

        if (p_serialize_1_.func_110474_b() != -1)
        {
            jsonobject.addProperty("width", Integer.valueOf(p_serialize_1_.func_110474_b()));
        }

        if (p_serialize_1_.func_110471_a() != -1)
        {
            jsonobject.addProperty("height", Integer.valueOf(p_serialize_1_.func_110471_a()));
        }

        if (p_serialize_1_.func_110473_c() > 0)
        {
            JsonArray jsonarray = new JsonArray();

            for (int i = 0; i < p_serialize_1_.func_110473_c(); ++i)
            {
                if (p_serialize_1_.func_110470_b(i))
                {
                    JsonObject jsonobject1 = new JsonObject();
                    jsonobject1.addProperty("index", Integer.valueOf(p_serialize_1_.func_110468_c(i)));
                    jsonobject1.addProperty("time", Integer.valueOf(p_serialize_1_.func_110472_a(i)));
                    jsonarray.add(jsonobject1);
                }
                else
                {
                    jsonarray.add(new JsonPrimitive(Integer.valueOf(p_serialize_1_.func_110468_c(i))));
                }
            }

            jsonobject.add("frames", jsonarray);
        }

        return jsonobject;
    }

    public String func_110483_a()
    {
        return "animation";
    }

    public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_)
    {
        return this.serialize((AnimationMetadataSection)p_serialize_1_, p_serialize_2_, p_serialize_3_);
    }
}