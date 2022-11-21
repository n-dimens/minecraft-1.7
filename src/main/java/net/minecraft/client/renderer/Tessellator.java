package net.minecraft.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.PriorityQueue;
import net.minecraft.client.shader.TesselatorVertexState;
import net.minecraft.client.util.QuadComparator;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class Tessellator
{
    private ByteBuffer field_78394_d;
    private IntBuffer field_147568_c;
    private FloatBuffer field_147566_d;
    private ShortBuffer field_147567_e;
    private int[] field_78405_h;
    private int field_78406_i;
    private double field_78403_j;
    private double field_78404_k;
    private int field_78401_l;
    private int field_78402_m;
    private boolean field_78399_n;
    private boolean field_78400_o;
    private boolean field_78414_p;
    private boolean field_78413_q;
    private int field_147569_p;
    private int field_78411_s;
    private boolean field_78410_t;
    private int field_78409_u;
    private double field_78408_v;
    private double field_78407_w;
    private double field_78417_x;
    private int field_78416_y;
    public static final Tessellator field_78398_a = new Tessellator(2097152);
    private boolean field_78415_z;
    private int field_78388_E;
    private static final String __OBFID = "CL_00000960";

    private Tessellator(int p_i1250_1_)
    {
        this.field_78388_E = p_i1250_1_;
        this.field_78394_d = GLAllocation.func_74524_c(p_i1250_1_ * 4);
        this.field_147568_c = this.field_78394_d.asIntBuffer();
        this.field_147566_d = this.field_78394_d.asFloatBuffer();
        this.field_147567_e = this.field_78394_d.asShortBuffer();
        this.field_78405_h = new int[p_i1250_1_];
    }

    public int func_78381_a()
    {
        if (!this.field_78415_z)
        {
            throw new IllegalStateException("Not tesselating!");
        }
        else
        {
            this.field_78415_z = false;

            if (this.field_78406_i > 0)
            {
                this.field_147568_c.clear();
                this.field_147568_c.put(this.field_78405_h, 0, this.field_147569_p);
                this.field_78394_d.position(0);
                this.field_78394_d.limit(this.field_147569_p * 4);

                if (this.field_78400_o)
                {
                    this.field_147566_d.position(3);
                    GL11.glTexCoordPointer(2, 32, this.field_147566_d);
                    GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                }

                if (this.field_78414_p)
                {
                    OpenGlHelper.func_77472_b(OpenGlHelper.field_77476_b);
                    this.field_147567_e.position(14);
                    GL11.glTexCoordPointer(2, 32, this.field_147567_e);
                    GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                    OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
                }

                if (this.field_78399_n)
                {
                    this.field_78394_d.position(20);
                    GL11.glColorPointer(4, true, 32, this.field_78394_d);
                    GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
                }

                if (this.field_78413_q)
                {
                    this.field_78394_d.position(24);
                    GL11.glNormalPointer(32, this.field_78394_d);
                    GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
                }

                this.field_147566_d.position(0);
                GL11.glVertexPointer(3, 32, this.field_147566_d);
                GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
                GL11.glDrawArrays(this.field_78409_u, 0, this.field_78406_i);
                GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);

                if (this.field_78400_o)
                {
                    GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                }

                if (this.field_78414_p)
                {
                    OpenGlHelper.func_77472_b(OpenGlHelper.field_77476_b);
                    GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
                    OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
                }

                if (this.field_78399_n)
                {
                    GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
                }

                if (this.field_78413_q)
                {
                    GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
                }
            }

            int i = this.field_147569_p * 4;
            this.func_78379_d();
            return i;
        }
    }

    public TesselatorVertexState func_147564_a(float p_147564_1_, float p_147564_2_, float p_147564_3_)
    {
        int[] aint = new int[this.field_147569_p];
        PriorityQueue priorityqueue = new PriorityQueue(this.field_147569_p, new QuadComparator(this.field_78405_h, p_147564_1_ + (float)this.field_78408_v, p_147564_2_ + (float)this.field_78407_w, p_147564_3_ + (float)this.field_78417_x));
        byte b0 = 32;
        int i;

        for (i = 0; i < this.field_147569_p; i += b0)
        {
            priorityqueue.add(Integer.valueOf(i));
        }

        for (i = 0; !priorityqueue.isEmpty(); i += b0)
        {
            int j = ((Integer)priorityqueue.remove()).intValue();

            for (int k = 0; k < b0; ++k)
            {
                aint[i + k] = this.field_78405_h[j + k];
            }
        }

        System.arraycopy(aint, 0, this.field_78405_h, 0, aint.length);
        return new TesselatorVertexState(aint, this.field_147569_p, this.field_78406_i, this.field_78400_o, this.field_78414_p, this.field_78413_q, this.field_78399_n);
    }

    public void func_147565_a(TesselatorVertexState p_147565_1_)
    {
        System.arraycopy(p_147565_1_.func_147572_a(), 0, this.field_78405_h, 0, p_147565_1_.func_147572_a().length);
        this.field_147569_p = p_147565_1_.func_147576_b();
        this.field_78406_i = p_147565_1_.func_147575_c();
        this.field_78400_o = p_147565_1_.func_147573_d();
        this.field_78414_p = p_147565_1_.func_147571_e();
        this.field_78399_n = p_147565_1_.func_147574_g();
        this.field_78413_q = p_147565_1_.func_147570_f();
    }

    private void func_78379_d()
    {
        this.field_78406_i = 0;
        this.field_78394_d.clear();
        this.field_147569_p = 0;
        this.field_78411_s = 0;
    }

    public void func_78382_b()
    {
        this.func_78371_b(7);
    }

    public void func_78371_b(int p_78371_1_)
    {
        if (this.field_78415_z)
        {
            throw new IllegalStateException("Already tesselating!");
        }
        else
        {
            this.field_78415_z = true;
            this.func_78379_d();
            this.field_78409_u = p_78371_1_;
            this.field_78413_q = false;
            this.field_78399_n = false;
            this.field_78400_o = false;
            this.field_78414_p = false;
            this.field_78410_t = false;
        }
    }

    public void func_78385_a(double p_78385_1_, double p_78385_3_)
    {
        this.field_78400_o = true;
        this.field_78403_j = p_78385_1_;
        this.field_78404_k = p_78385_3_;
    }

    public void func_78380_c(int p_78380_1_)
    {
        this.field_78414_p = true;
        this.field_78401_l = p_78380_1_;
    }

    public void func_78386_a(float p_78386_1_, float p_78386_2_, float p_78386_3_)
    {
        this.func_78376_a((int)(p_78386_1_ * 255.0F), (int)(p_78386_2_ * 255.0F), (int)(p_78386_3_ * 255.0F));
    }

    public void func_78369_a(float p_78369_1_, float p_78369_2_, float p_78369_3_, float p_78369_4_)
    {
        this.func_78370_a((int)(p_78369_1_ * 255.0F), (int)(p_78369_2_ * 255.0F), (int)(p_78369_3_ * 255.0F), (int)(p_78369_4_ * 255.0F));
    }

    public void func_78376_a(int p_78376_1_, int p_78376_2_, int p_78376_3_)
    {
        this.func_78370_a(p_78376_1_, p_78376_2_, p_78376_3_, 255);
    }

    public void func_78370_a(int p_78370_1_, int p_78370_2_, int p_78370_3_, int p_78370_4_)
    {
        if (!this.field_78410_t)
        {
            if (p_78370_1_ > 255)
            {
                p_78370_1_ = 255;
            }

            if (p_78370_2_ > 255)
            {
                p_78370_2_ = 255;
            }

            if (p_78370_3_ > 255)
            {
                p_78370_3_ = 255;
            }

            if (p_78370_4_ > 255)
            {
                p_78370_4_ = 255;
            }

            if (p_78370_1_ < 0)
            {
                p_78370_1_ = 0;
            }

            if (p_78370_2_ < 0)
            {
                p_78370_2_ = 0;
            }

            if (p_78370_3_ < 0)
            {
                p_78370_3_ = 0;
            }

            if (p_78370_4_ < 0)
            {
                p_78370_4_ = 0;
            }

            this.field_78399_n = true;

            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
            {
                this.field_78402_m = p_78370_4_ << 24 | p_78370_3_ << 16 | p_78370_2_ << 8 | p_78370_1_;
            }
            else
            {
                this.field_78402_m = p_78370_1_ << 24 | p_78370_2_ << 16 | p_78370_3_ << 8 | p_78370_4_;
            }
        }
    }

    public void func_154352_a(byte p_154352_1_, byte p_154352_2_, byte p_154352_3_)
    {
        this.func_78376_a(p_154352_1_ & 255, p_154352_2_ & 255, p_154352_3_ & 255);
    }

    public void func_78374_a(double p_78374_1_, double p_78374_3_, double p_78374_5_, double p_78374_7_, double p_78374_9_)
    {
        this.func_78385_a(p_78374_7_, p_78374_9_);
        this.func_78377_a(p_78374_1_, p_78374_3_, p_78374_5_);
    }

    public void func_78377_a(double p_78377_1_, double p_78377_3_, double p_78377_5_)
    {
        ++this.field_78411_s;

        if (this.field_78400_o)
        {
            this.field_78405_h[this.field_147569_p + 3] = Float.floatToRawIntBits((float)this.field_78403_j);
            this.field_78405_h[this.field_147569_p + 4] = Float.floatToRawIntBits((float)this.field_78404_k);
        }

        if (this.field_78414_p)
        {
            this.field_78405_h[this.field_147569_p + 7] = this.field_78401_l;
        }

        if (this.field_78399_n)
        {
            this.field_78405_h[this.field_147569_p + 5] = this.field_78402_m;
        }

        if (this.field_78413_q)
        {
            this.field_78405_h[this.field_147569_p + 6] = this.field_78416_y;
        }

        this.field_78405_h[this.field_147569_p + 0] = Float.floatToRawIntBits((float)(p_78377_1_ + this.field_78408_v));
        this.field_78405_h[this.field_147569_p + 1] = Float.floatToRawIntBits((float)(p_78377_3_ + this.field_78407_w));
        this.field_78405_h[this.field_147569_p + 2] = Float.floatToRawIntBits((float)(p_78377_5_ + this.field_78417_x));
        this.field_147569_p += 8;
        ++this.field_78406_i;

        if (this.field_78406_i % 4 == 0 && this.field_147569_p >= this.field_78388_E - 32)
        {
            this.func_78381_a();
            this.field_78415_z = true;
        }
    }

    public void func_78378_d(int p_78378_1_)
    {
        int j = p_78378_1_ >> 16 & 255;
        int k = p_78378_1_ >> 8 & 255;
        int l = p_78378_1_ & 255;
        this.func_78376_a(j, k, l);
    }

    public void func_78384_a(int p_78384_1_, int p_78384_2_)
    {
        int k = p_78384_1_ >> 16 & 255;
        int l = p_78384_1_ >> 8 & 255;
        int i1 = p_78384_1_ & 255;
        this.func_78370_a(k, l, i1, p_78384_2_);
    }

    public void func_78383_c()
    {
        this.field_78410_t = true;
    }

    public void func_78375_b(float p_78375_1_, float p_78375_2_, float p_78375_3_)
    {
        this.field_78413_q = true;
        byte b0 = (byte)((int)(p_78375_1_ * 127.0F));
        byte b1 = (byte)((int)(p_78375_2_ * 127.0F));
        byte b2 = (byte)((int)(p_78375_3_ * 127.0F));
        this.field_78416_y = b0 & 255 | (b1 & 255) << 8 | (b2 & 255) << 16;
    }

    public void func_78373_b(double p_78373_1_, double p_78373_3_, double p_78373_5_)
    {
        this.field_78408_v = p_78373_1_;
        this.field_78407_w = p_78373_3_;
        this.field_78417_x = p_78373_5_;
    }

    public void func_78372_c(float p_78372_1_, float p_78372_2_, float p_78372_3_)
    {
        this.field_78408_v += (double)p_78372_1_;
        this.field_78407_w += (double)p_78372_2_;
        this.field_78417_x += (double)p_78372_3_;
    }
}