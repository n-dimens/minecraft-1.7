package net.minecraft.client.resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

@SideOnly(Side.CLIENT)
public interface IReloadableResourceManager extends IResourceManager
{
    void func_110541_a(List p_110541_1_);

    void func_110542_a(IResourceManagerReloadListener p_110542_1_);
}