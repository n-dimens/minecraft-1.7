package net.minecraft.client.resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public interface IResourcePack
{
    InputStream func_110590_a(ResourceLocation p_110590_1_) throws IOException;

    boolean func_110589_b(ResourceLocation p_110589_1_);

    Set func_110587_b();

    IMetadataSection func_135058_a(IMetadataSerializer p_135058_1_, String p_135058_2_) throws IOException;

    BufferedImage func_110586_a() throws IOException;

    String func_130077_b();
}