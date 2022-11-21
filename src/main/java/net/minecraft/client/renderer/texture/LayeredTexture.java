package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class LayeredTexture extends AbstractTexture
{
    private static final Logger field_147638_c = LogManager.getLogger();
    public final List field_110567_b;
    private static final String __OBFID = "CL_00001051";

    public LayeredTexture(String ... p_i1274_1_)
    {
        this.field_110567_b = Lists.newArrayList(p_i1274_1_);
    }

    public void func_110551_a(IResourceManager p_110551_1_) throws IOException
    {
        this.func_147631_c();
        BufferedImage bufferedimage = null;

        try
        {
            Iterator iterator = this.field_110567_b.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();

                if (s != null)
                {
                    InputStream inputstream = p_110551_1_.func_110536_a(new ResourceLocation(s)).func_110527_b();
                    BufferedImage bufferedimage1 = ImageIO.read(inputstream);

                    if (bufferedimage == null)
                    {
                        bufferedimage = new BufferedImage(bufferedimage1.getWidth(), bufferedimage1.getHeight(), 2);
                    }

                    bufferedimage.getGraphics().drawImage(bufferedimage1, 0, 0, (ImageObserver)null);
                }
            }
        }
        catch (IOException ioexception)
        {
            field_147638_c.error("Couldn\'t load layered image", ioexception);
            return;
        }

        TextureUtil.func_110987_a(this.func_110552_b(), bufferedimage);
    }
}