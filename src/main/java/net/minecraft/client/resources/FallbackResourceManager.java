package net.minecraft.client.resources;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class FallbackResourceManager implements IResourceManager
{
    protected final List field_110540_a = new ArrayList();
    private final IMetadataSerializer field_110539_b;
    private static final String __OBFID = "CL_00001074";

    public FallbackResourceManager(IMetadataSerializer p_i1289_1_)
    {
        this.field_110539_b = p_i1289_1_;
    }

    public void func_110538_a(IResourcePack p_110538_1_)
    {
        this.field_110540_a.add(p_110538_1_);
    }

    public Set func_135055_a()
    {
        return null;
    }

    public IResource func_110536_a(ResourceLocation p_110536_1_) throws IOException
    {
        IResourcePack iresourcepack = null;
        ResourceLocation resourcelocation1 = func_110537_b(p_110536_1_);

        for (int i = this.field_110540_a.size() - 1; i >= 0; --i)
        {
            IResourcePack iresourcepack1 = (IResourcePack)this.field_110540_a.get(i);

            if (iresourcepack == null && iresourcepack1.func_110589_b(resourcelocation1))
            {
                iresourcepack = iresourcepack1;
            }

            if (iresourcepack1.func_110589_b(p_110536_1_))
            {
                InputStream inputstream = null;

                if (iresourcepack != null)
                {
                    inputstream = iresourcepack.func_110590_a(resourcelocation1);
                }

                return new SimpleResource(p_110536_1_, iresourcepack1.func_110590_a(p_110536_1_), inputstream, this.field_110539_b);
            }
        }

        throw new FileNotFoundException(p_110536_1_.toString());
    }

    public List func_135056_b(ResourceLocation p_135056_1_) throws IOException
    {
        ArrayList arraylist = Lists.newArrayList();
        ResourceLocation resourcelocation1 = func_110537_b(p_135056_1_);
        Iterator iterator = this.field_110540_a.iterator();

        while (iterator.hasNext())
        {
            IResourcePack iresourcepack = (IResourcePack)iterator.next();

            if (iresourcepack.func_110589_b(p_135056_1_))
            {
                InputStream inputstream = iresourcepack.func_110589_b(resourcelocation1) ? iresourcepack.func_110590_a(resourcelocation1) : null;
                arraylist.add(new SimpleResource(p_135056_1_, iresourcepack.func_110590_a(p_135056_1_), inputstream, this.field_110539_b));
            }
        }

        if (arraylist.isEmpty())
        {
            throw new FileNotFoundException(p_135056_1_.toString());
        }
        else
        {
            return arraylist;
        }
    }

    static ResourceLocation func_110537_b(ResourceLocation p_110537_0_)
    {
        return new ResourceLocation(p_110537_0_.func_110624_b(), p_110537_0_.func_110623_a() + ".mcmeta");
    }
}