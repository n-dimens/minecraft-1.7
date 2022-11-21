package net.minecraft.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;

@SideOnly(Side.CLIENT)
public class ImageBufferDownload implements IImageBuffer
{
    private int[] field_78438_a;
    private int field_78436_b;
    private int field_78437_c;
    private static final String __OBFID = "CL_00000956";

    public BufferedImage func_78432_a(BufferedImage p_78432_1_)
    {
        if (p_78432_1_ == null)
        {
            return null;
        }
        else
        {
            this.field_78436_b = 64;
            this.field_78437_c = 32;
            BufferedImage bufferedimage1 = new BufferedImage(this.field_78436_b, this.field_78437_c, 2);
            Graphics graphics = bufferedimage1.getGraphics();
            graphics.drawImage(p_78432_1_, 0, 0, (ImageObserver)null);
            graphics.dispose();
            this.field_78438_a = ((DataBufferInt)bufferedimage1.getRaster().getDataBuffer()).getData();
            this.func_78433_b(0, 0, 32, 16);
            this.func_78434_a(32, 0, 64, 32);
            this.func_78433_b(0, 16, 64, 32);
            return bufferedimage1;
        }
    }

    public void func_152634_a() {}

    private void func_78434_a(int p_78434_1_, int p_78434_2_, int p_78434_3_, int p_78434_4_)
    {
        if (!this.func_78435_c(p_78434_1_, p_78434_2_, p_78434_3_, p_78434_4_))
        {
            for (int i1 = p_78434_1_; i1 < p_78434_3_; ++i1)
            {
                for (int j1 = p_78434_2_; j1 < p_78434_4_; ++j1)
                {
                    this.field_78438_a[i1 + j1 * this.field_78436_b] &= 16777215;
                }
            }
        }
    }

    private void func_78433_b(int p_78433_1_, int p_78433_2_, int p_78433_3_, int p_78433_4_)
    {
        for (int i1 = p_78433_1_; i1 < p_78433_3_; ++i1)
        {
            for (int j1 = p_78433_2_; j1 < p_78433_4_; ++j1)
            {
                this.field_78438_a[i1 + j1 * this.field_78436_b] |= -16777216;
            }
        }
    }

    private boolean func_78435_c(int p_78435_1_, int p_78435_2_, int p_78435_3_, int p_78435_4_)
    {
        for (int i1 = p_78435_1_; i1 < p_78435_3_; ++i1)
        {
            for (int j1 = p_78435_2_; j1 < p_78435_4_; ++j1)
            {
                int k1 = this.field_78438_a[i1 + j1 * this.field_78436_b];

                if ((k1 >> 24 & 255) < 128)
                {
                    return true;
                }
            }
        }

        return false;
    }
}