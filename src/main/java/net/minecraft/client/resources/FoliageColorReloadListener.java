package net.minecraft.client.resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ColorizerFoliage;

@SideOnly(Side.CLIENT)
public class FoliageColorReloadListener implements IResourceManagerReloadListener
{
    private static final ResourceLocation field_130079_a = new ResourceLocation("textures/colormap/foliage.png");
    private static final String __OBFID = "CL_00001077";

    public void func_110549_a(IResourceManager p_110549_1_)
    {
        try
        {
            ColorizerFoliage.func_77467_a(TextureUtil.func_110986_a(p_110549_1_, field_130079_a));
        }
        catch (IOException ioexception)
        {
            ;
        }
    }
}