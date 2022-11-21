package net.minecraft.nbt;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.Iterator;
import java.util.UUID;
import net.minecraft.util.StringUtils;

public final class NBTUtil
{
    private static final String __OBFID = "CL_00001901";

    public static GameProfile func_152459_a(NBTTagCompound p_152459_0_)
    {
        String s = null;
        String s1 = null;

        if (p_152459_0_.func_150297_b("Name", 8))
        {
            s = p_152459_0_.func_74779_i("Name");
        }

        if (p_152459_0_.func_150297_b("Id", 8))
        {
            s1 = p_152459_0_.func_74779_i("Id");
        }

        if (StringUtils.func_151246_b(s) && StringUtils.func_151246_b(s1))
        {
            return null;
        }
        else
        {
            UUID uuid;

            try
            {
                uuid = UUID.fromString(s1);
            }
            catch (Throwable throwable)
            {
                uuid = null;
            }

            GameProfile gameprofile = new GameProfile(uuid, s);

            if (p_152459_0_.func_150297_b("Properties", 10))
            {
                NBTTagCompound nbttagcompound1 = p_152459_0_.func_74775_l("Properties");
                Iterator iterator = nbttagcompound1.func_150296_c().iterator();

                while (iterator.hasNext())
                {
                    String s2 = (String)iterator.next();
                    NBTTagList nbttaglist = nbttagcompound1.func_150295_c(s2, 10);

                    for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
                    {
                        NBTTagCompound nbttagcompound2 = nbttaglist.func_150305_b(i);
                        String s3 = nbttagcompound2.func_74779_i("Value");

                        if (nbttagcompound2.func_150297_b("Signature", 8))
                        {
                            gameprofile.getProperties().put(s2, new Property(s2, s3, nbttagcompound2.func_74779_i("Signature")));
                        }
                        else
                        {
                            gameprofile.getProperties().put(s2, new Property(s2, s3));
                        }
                    }
                }
            }

            return gameprofile;
        }
    }

    public static void func_152460_a(NBTTagCompound p_152460_0_, GameProfile p_152460_1_)
    {
        if (!StringUtils.func_151246_b(p_152460_1_.getName()))
        {
            p_152460_0_.func_74778_a("Name", p_152460_1_.getName());
        }

        if (p_152460_1_.getId() != null)
        {
            p_152460_0_.func_74778_a("Id", p_152460_1_.getId().toString());
        }

        if (!p_152460_1_.getProperties().isEmpty())
        {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            Iterator iterator = p_152460_1_.getProperties().keySet().iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                NBTTagList nbttaglist = new NBTTagList();
                NBTTagCompound nbttagcompound2;

                for (Iterator iterator1 = p_152460_1_.getProperties().get(s).iterator(); iterator1.hasNext(); nbttaglist.func_74742_a(nbttagcompound2))
                {
                    Property property = (Property)iterator1.next();
                    nbttagcompound2 = new NBTTagCompound();
                    nbttagcompound2.func_74778_a("Value", property.getValue());

                    if (property.hasSignature())
                    {
                        nbttagcompound2.func_74778_a("Signature", property.getSignature());
                    }
                }

                nbttagcompound1.func_74782_a(s, nbttaglist);
            }

            p_152460_0_.func_74782_a("Properties", nbttagcompound1);
        }
    }
}