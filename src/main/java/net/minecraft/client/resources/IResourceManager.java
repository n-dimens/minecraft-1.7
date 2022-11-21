package net.minecraft.client.resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public interface IResourceManager
{
    Set func_135055_a();

    IResource func_110536_a(ResourceLocation p_110536_1_) throws IOException;

    List func_135056_b(ResourceLocation p_135056_1_) throws IOException;
}