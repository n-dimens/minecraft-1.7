package net.minecraft.client.renderer.texture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class SimpleTexture extends AbstractTexture
{
    private static final Logger field_147639_c = LogManager.getLogger();
    protected final ResourceLocation field_110568_b;
    private static final String __OBFID = "CL_00001052";

    public SimpleTexture(ResourceLocation p_i1275_1_)
    {
        this.field_110568_b = p_i1275_1_;
    }

    public void func_110551_a(IResourceManager p_110551_1_) throws IOException
    {
        this.func_147631_c();
        InputStream inputstream = null;

        try
        {
            IResource iresource = p_110551_1_.func_110536_a(this.field_110568_b);
            inputstream = iresource.func_110527_b();
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            boolean flag = false;
            boolean flag1 = false;

            if (iresource.func_110528_c())
            {
                try
                {
                    TextureMetadataSection texturemetadatasection = (TextureMetadataSection)iresource.func_110526_a("texture");

                    if (texturemetadatasection != null)
                    {
                        flag = texturemetadatasection.func_110479_a();
                        flag1 = texturemetadatasection.func_110480_b();
                    }
                }
                catch (RuntimeException runtimeexception)
                {
                    field_147639_c.warn("Failed reading metadata of: " + this.field_110568_b, runtimeexception);
                }
            }

            TextureUtil.func_110989_a(this.func_110552_b(), bufferedimage, flag, flag1);
        }
        finally
        {
            if (inputstream != null)
            {
                inputstream.close();
            }
        }
    }
}