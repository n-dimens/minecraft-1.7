package net.minecraft.client.resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.InputStream;
import net.minecraft.client.resources.data.IMetadataSection;

@SideOnly(Side.CLIENT)
public interface IResource
{
    InputStream func_110527_b();

    boolean func_110528_c();

    IMetadataSection func_110526_a(String p_110526_1_);
}