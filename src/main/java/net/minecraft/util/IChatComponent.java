package net.minecraft.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public interface IChatComponent extends Iterable
{
    IChatComponent func_150255_a(ChatStyle p_150255_1_);

    ChatStyle func_150256_b();

    IChatComponent func_150258_a(String p_150258_1_);

    IChatComponent func_150257_a(IChatComponent p_150257_1_);

    String func_150261_e();

    String func_150260_c();

    @SideOnly(Side.CLIENT)
    String func_150254_d();

    List func_150253_a();

    IChatComponent func_150259_f();

    public static class Serializer implements JsonDeserializer, JsonSerializer
        {
            private static final Gson field_150700_a;
            private static final String __OBFID = "CL_00001263";

            public IChatComponent deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_)
            {
                if (p_deserialize_1_.isJsonPrimitive())
                {
                    return new ChatComponentText(p_deserialize_1_.getAsString());
                }
                else if (!p_deserialize_1_.isJsonObject())
                {
                    if (p_deserialize_1_.isJsonArray())
                    {
                        JsonArray jsonarray1 = p_deserialize_1_.getAsJsonArray();
                        IChatComponent ichatcomponent = null;
                        Iterator iterator = jsonarray1.iterator();

                        while (iterator.hasNext())
                        {
                            JsonElement jsonelement1 = (JsonElement)iterator.next();
                            IChatComponent ichatcomponent1 = this.deserialize(jsonelement1, jsonelement1.getClass(), p_deserialize_3_);

                            if (ichatcomponent == null)
                            {
                                ichatcomponent = ichatcomponent1;
                            }
                            else
                            {
                                ichatcomponent.func_150257_a(ichatcomponent1);
                            }
                        }

                        return ichatcomponent;
                    }
                    else
                    {
                        throw new JsonParseException("Don\'t know how to turn " + p_deserialize_1_.toString() + " into a Component");
                    }
                }
                else
                {
                    JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
                    Object object;

                    if (jsonobject.has("text"))
                    {
                        object = new ChatComponentText(jsonobject.get("text").getAsString());
                    }
                    else
                    {
                        if (!jsonobject.has("translate"))
                        {
                            throw new JsonParseException("Don\'t know how to turn " + p_deserialize_1_.toString() + " into a Component");
                        }

                        String s = jsonobject.get("translate").getAsString();

                        if (jsonobject.has("with"))
                        {
                            JsonArray jsonarray = jsonobject.getAsJsonArray("with");
                            Object[] aobject = new Object[jsonarray.size()];

                            for (int i = 0; i < aobject.length; ++i)
                            {
                                aobject[i] = this.deserialize(jsonarray.get(i), p_deserialize_2_, p_deserialize_3_);

                                if (aobject[i] instanceof ChatComponentText)
                                {
                                    ChatComponentText chatcomponenttext = (ChatComponentText)aobject[i];

                                    if (chatcomponenttext.func_150256_b().func_150229_g() && chatcomponenttext.func_150253_a().isEmpty())
                                    {
                                        aobject[i] = chatcomponenttext.getMessage();
                                    }
                                }
                            }

                            object = new ChatComponentTranslation(s, aobject);
                        }
                        else
                        {
                            object = new ChatComponentTranslation(s, new Object[0]);
                        }
                    }

                    if (jsonobject.has("extra"))
                    {
                        JsonArray jsonarray2 = jsonobject.getAsJsonArray("extra");

                        if (jsonarray2.size() <= 0)
                        {
                            throw new JsonParseException("Unexpected empty array of components");
                        }

                        for (int j = 0; j < jsonarray2.size(); ++j)
                        {
                            ((IChatComponent)object).func_150257_a(this.deserialize(jsonarray2.get(j), p_deserialize_2_, p_deserialize_3_));
                        }
                    }

                    ((IChatComponent)object).func_150255_a((ChatStyle)p_deserialize_3_.deserialize(p_deserialize_1_, ChatStyle.class));
                    return (IChatComponent)object;
                }
            }

            private void func_150695_a(ChatStyle p_150695_1_, JsonObject p_150695_2_, JsonSerializationContext p_150695_3_)
            {
                JsonElement jsonelement = p_150695_3_.serialize(p_150695_1_);

                if (jsonelement.isJsonObject())
                {
                    JsonObject jsonobject1 = (JsonObject)jsonelement;
                    Iterator iterator = jsonobject1.entrySet().iterator();

                    while (iterator.hasNext())
                    {
                        Entry entry = (Entry)iterator.next();
                        p_150695_2_.add((String)entry.getKey(), (JsonElement)entry.getValue());
                    }
                }
            }

            public JsonElement serialize(IChatComponent p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_)
            {
                if (p_serialize_1_ instanceof ChatComponentText && p_serialize_1_.func_150256_b().func_150229_g() && p_serialize_1_.func_150253_a().isEmpty())
                {
                    return new JsonPrimitive(((ChatComponentText)p_serialize_1_).getMessage());
                }
                else
                {
                    JsonObject jsonobject = new JsonObject();

                    if (!p_serialize_1_.func_150256_b().func_150229_g())
                    {
                        this.func_150695_a(p_serialize_1_.func_150256_b(), jsonobject, p_serialize_3_);
                    }

                    if (!p_serialize_1_.func_150253_a().isEmpty())
                    {
                        JsonArray jsonarray = new JsonArray();
                        Iterator iterator = p_serialize_1_.func_150253_a().iterator();

                        while (iterator.hasNext())
                        {
                            IChatComponent ichatcomponent1 = (IChatComponent)iterator.next();
                            jsonarray.add(this.serialize(ichatcomponent1, ichatcomponent1.getClass(), p_serialize_3_));
                        }

                        jsonobject.add("extra", jsonarray);
                    }

                    if (p_serialize_1_ instanceof ChatComponentText)
                    {
                        jsonobject.addProperty("text", ((ChatComponentText)p_serialize_1_).getMessage());
                    }
                    else
                    {
                        if (!(p_serialize_1_ instanceof ChatComponentTranslation))
                        {
                            throw new IllegalArgumentException("Don\'t know how to serialize " + p_serialize_1_ + " as a Component");
                        }

                        ChatComponentTranslation chatcomponenttranslation = (ChatComponentTranslation)p_serialize_1_;
                        jsonobject.addProperty("translate", chatcomponenttranslation.func_150268_i());

                        if (chatcomponenttranslation.func_150271_j() != null && chatcomponenttranslation.func_150271_j().length > 0)
                        {
                            JsonArray jsonarray1 = new JsonArray();
                            Object[] aobject = chatcomponenttranslation.func_150271_j();
                            int i = aobject.length;

                            for (int j = 0; j < i; ++j)
                            {
                                Object object = aobject[j];

                                if (object instanceof IChatComponent)
                                {
                                    jsonarray1.add(this.serialize((IChatComponent)object, object.getClass(), p_serialize_3_));
                                }
                                else
                                {
                                    jsonarray1.add(new JsonPrimitive(String.valueOf(object)));
                                }
                            }

                            jsonobject.add("with", jsonarray1);
                        }
                    }

                    return jsonobject;
                }
            }

            public static String func_150696_a(IChatComponent p_150696_0_)
            {
                return field_150700_a.toJson(p_150696_0_);
            }

            public static IChatComponent func_150699_a(String p_150699_0_)
            {
                return (IChatComponent)field_150700_a.fromJson(p_150699_0_, IChatComponent.class);
            }

            public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_)
            {
                return this.serialize((IChatComponent)p_serialize_1_, p_serialize_2_, p_serialize_3_);
            }

            static
            {
                GsonBuilder var0 = new GsonBuilder();
                var0.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent.Serializer());
                var0.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle.Serializer());
                var0.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
                field_150700_a = var0.create();
            }
        }
}