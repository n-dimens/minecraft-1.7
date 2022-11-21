package net.minecraft.client.resources;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

@SideOnly(Side.CLIENT)
public class SimpleResource implements IResource
{
    private final Map field_110535_a = Maps.newHashMap();
    private final ResourceLocation field_110533_b;
    private final InputStream field_110534_c;
    private final InputStream field_110531_d;
    private final IMetadataSerializer field_110532_e;
    private boolean field_110529_f;
    private JsonObject field_110530_g;
    private static final String __OBFID = "CL_00001093";

    public SimpleResource(ResourceLocation p_i1300_1_, InputStream p_i1300_2_, InputStream p_i1300_3_, IMetadataSerializer p_i1300_4_)
    {
        this.field_110533_b = p_i1300_1_;
        this.field_110534_c = p_i1300_2_;
        this.field_110531_d = p_i1300_3_;
        this.field_110532_e = p_i1300_4_;
    }

    public InputStream func_110527_b()
    {
        return this.field_110534_c;
    }

    public boolean func_110528_c()
    {
        return this.field_110531_d != null;
    }

    public IMetadataSection func_110526_a(String p_110526_1_)
    {
        if (!this.func_110528_c())
        {
            return null;
        }
        else
        {
            if (this.field_110530_g == null && !this.field_110529_f)
            {
                this.field_110529_f = true;
                BufferedReader bufferedreader = null;

                try
                {
                    bufferedreader = new BufferedReader(new InputStreamReader(this.field_110531_d));
                    this.field_110530_g = (new JsonParser()).parse(bufferedreader).getAsJsonObject();
                }
                finally
                {
                    IOUtils.closeQuietly(bufferedreader);
                }
            }

            IMetadataSection imetadatasection = (IMetadataSection)this.field_110535_a.get(p_110526_1_);

            if (imetadatasection == null)
            {
                imetadatasection = this.field_110532_e.func_110503_a(p_110526_1_, this.field_110530_g);
            }

            return imetadatasection;
        }
    }

    public boolean equals(Object p_equals_1_)
    {
        if (this == p_equals_1_)
        {
            return true;
        }
        else if (p_equals_1_ instanceof SimpleResource)
        {
            SimpleResource simpleresource = (SimpleResource)p_equals_1_;
            return this.field_110533_b != null ? this.field_110533_b.equals(simpleresource.field_110533_b) : simpleresource.field_110533_b == null;
        }
        else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return this.field_110533_b == null ? 0 : this.field_110533_b.hashCode();
    }
}