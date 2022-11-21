package net.minecraft.client.resources.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Type;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;

@SideOnly(Side.CLIENT)
public class PackMetadataSectionSerializer extends BaseMetadataSectionSerializer implements JsonSerializer
{
    private static final String __OBFID = "CL_00001113";

    public PackMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_)
    {
        JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
        IChatComponent ichatcomponent = (IChatComponent)p_deserialize_3_.deserialize(jsonobject.get("description"), IChatComponent.class);
        int i = JsonUtils.func_151203_m(jsonobject, "pack_format");
        return new PackMetadataSection(ichatcomponent, i);
    }

    public JsonElement serialize(PackMetadataSection p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_)
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("pack_format", Integer.valueOf(p_serialize_1_.func_110462_b()));
        jsonobject.add("description", p_serialize_3_.serialize(p_serialize_1_.func_152805_a()));
        return jsonobject;
    }

    public String func_110483_a()
    {
        return "pack";
    }

    public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_)
    {
        return this.serialize((PackMetadataSection)p_serialize_1_, p_serialize_2_, p_serialize_3_);
    }
}