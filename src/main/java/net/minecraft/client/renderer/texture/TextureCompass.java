package net.minecraft.client.renderer.texture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class TextureCompass extends TextureAtlasSprite
{
    public double field_94244_i;
    public double field_94242_j;
    private static final String __OBFID = "CL_00001071";

    public TextureCompass(String p_i1286_1_)
    {
        super(p_i1286_1_);
    }

    public void func_94219_l()
    {
        Minecraft minecraft = Minecraft.func_71410_x();

        if (minecraft.field_71441_e != null && minecraft.field_71439_g != null)
        {
            this.func_94241_a(minecraft.field_71441_e, minecraft.field_71439_g.field_70165_t, minecraft.field_71439_g.field_70161_v, (double)minecraft.field_71439_g.field_70177_z, false, false);
        }
        else
        {
            this.func_94241_a((World)null, 0.0D, 0.0D, 0.0D, true, false);
        }
    }

    public void func_94241_a(World p_94241_1_, double p_94241_2_, double p_94241_4_, double p_94241_6_, boolean p_94241_8_, boolean p_94241_9_)
    {
        if (!this.field_110976_a.isEmpty())
        {
            double d3 = 0.0D;

            if (p_94241_1_ != null && !p_94241_8_)
            {
                ChunkCoordinates chunkcoordinates = p_94241_1_.func_72861_E();
                double d4 = (double)chunkcoordinates.field_71574_a - p_94241_2_;
                double d5 = (double)chunkcoordinates.field_71573_c - p_94241_4_;
                p_94241_6_ %= 360.0D;
                d3 = -((p_94241_6_ - 90.0D) * Math.PI / 180.0D - Math.atan2(d5, d4));

                if (!p_94241_1_.field_73011_w.func_76569_d())
                {
                    d3 = Math.random() * Math.PI * 2.0D;
                }
            }

            if (p_94241_9_)
            {
                this.field_94244_i = d3;
            }
            else
            {
                double d6;

                for (d6 = d3 - this.field_94244_i; d6 < -Math.PI; d6 += (Math.PI * 2D))
                {
                    ;
                }

                while (d6 >= Math.PI)
                {
                    d6 -= (Math.PI * 2D);
                }

                if (d6 < -1.0D)
                {
                    d6 = -1.0D;
                }

                if (d6 > 1.0D)
                {
                    d6 = 1.0D;
                }

                this.field_94242_j += d6 * 0.1D;
                this.field_94242_j *= 0.8D;
                this.field_94244_i += this.field_94242_j;
            }

            int i;

            for (i = (int)((this.field_94244_i / (Math.PI * 2D) + 1.0D) * (double)this.field_110976_a.size()) % this.field_110976_a.size(); i < 0; i = (i + this.field_110976_a.size()) % this.field_110976_a.size())
            {
                ;
            }

            if (i != this.field_110973_g)
            {
                this.field_110973_g = i;
                TextureUtil.func_147955_a((int[][])this.field_110976_a.get(this.field_110973_g), this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
            }
        }
    }
}