package net.minecraft.client.gui;

import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ServerListEntryNormal implements GuiListExtended.IGuiListEntry
{
    private static final Logger field_148304_a = LogManager.getLogger();
    private static final ThreadPoolExecutor field_148302_b = new ScheduledThreadPoolExecutor(5, (new ThreadFactoryBuilder()).setNameFormat("Server Pinger #%d").setDaemon(true).build());
    private final GuiMultiplayer field_148303_c;
    private final Minecraft field_148300_d;
    private final ServerData field_148301_e;
    private long field_148298_f;
    private String field_148299_g;
    private DynamicTexture field_148305_h;
    private ResourceLocation field_148306_i;
    private static final String __OBFID = "CL_00000817";

    protected ServerListEntryNormal(GuiMultiplayer p_i45048_1_, ServerData p_i45048_2_)
    {
        this.field_148303_c = p_i45048_1_;
        this.field_148301_e = p_i45048_2_;
        this.field_148300_d = Minecraft.func_71410_x();
        this.field_148306_i = new ResourceLocation("servers/" + p_i45048_2_.field_78845_b + "/icon");
        this.field_148305_h = (DynamicTexture)this.field_148300_d.func_110434_K().func_110581_b(this.field_148306_i);
    }

    public void func_148279_a(int p_148279_1_, int p_148279_2_, int p_148279_3_, int p_148279_4_, int p_148279_5_, Tessellator p_148279_6_, int p_148279_7_, int p_148279_8_, boolean p_148279_9_)
    {
        if (!this.field_148301_e.field_78841_f)
        {
            this.field_148301_e.field_78841_f = true;
            this.field_148301_e.field_78844_e = -2L;
            this.field_148301_e.field_78843_d = "";
            this.field_148301_e.field_78846_c = "";
            field_148302_b.submit(new Runnable()
            {
                private static final String __OBFID = "CL_00000818";
                public void run()
                {
                    try
                    {
                        ServerListEntryNormal.this.field_148303_c.func_146789_i().func_147224_a(ServerListEntryNormal.this.field_148301_e);
                    }
                    catch (UnknownHostException unknownhostexception)
                    {
                        ServerListEntryNormal.this.field_148301_e.field_78844_e = -1L;
                        ServerListEntryNormal.this.field_148301_e.field_78843_d = EnumChatFormatting.DARK_RED + "Can\'t resolve hostname";
                    }
                    catch (Exception exception)
                    {
                        ServerListEntryNormal.this.field_148301_e.field_78844_e = -1L;
                        ServerListEntryNormal.this.field_148301_e.field_78843_d = EnumChatFormatting.DARK_RED + "Can\'t connect to server.";
                    }
                }
            });
        }

        boolean flag1 = this.field_148301_e.field_82821_f > 5;
        boolean flag2 = this.field_148301_e.field_82821_f < 5;
        boolean flag3 = flag1 || flag2;
        this.field_148300_d.field_71466_p.func_78276_b(this.field_148301_e.field_78847_a, p_148279_2_ + 32 + 3, p_148279_3_ + 1, 16777215);
        List list = this.field_148300_d.field_71466_p.func_78271_c(this.field_148301_e.field_78843_d, p_148279_4_ - 32 - 2);

        for (int l1 = 0; l1 < Math.min(list.size(), 2); ++l1)
        {
            this.field_148300_d.field_71466_p.func_78276_b((String)list.get(l1), p_148279_2_ + 32 + 3, p_148279_3_ + 12 + this.field_148300_d.field_71466_p.field_78288_b * l1, 8421504);
        }

        String s2 = flag3 ? EnumChatFormatting.DARK_RED + this.field_148301_e.field_82822_g : this.field_148301_e.field_78846_c;
        int i2 = this.field_148300_d.field_71466_p.func_78256_a(s2);
        this.field_148300_d.field_71466_p.func_78276_b(s2, p_148279_2_ + p_148279_4_ - i2 - 15 - 2, p_148279_3_ + 1, 8421504);
        byte b0 = 0;
        String s = null;
        int j2;
        String s1;

        if (flag3)
        {
            j2 = 5;
            s1 = flag1 ? "Client out of date!" : "Server out of date!";
            s = this.field_148301_e.field_147412_i;
        }
        else if (this.field_148301_e.field_78841_f && this.field_148301_e.field_78844_e != -2L)
        {
            if (this.field_148301_e.field_78844_e < 0L)
            {
                j2 = 5;
            }
            else if (this.field_148301_e.field_78844_e < 150L)
            {
                j2 = 0;
            }
            else if (this.field_148301_e.field_78844_e < 300L)
            {
                j2 = 1;
            }
            else if (this.field_148301_e.field_78844_e < 600L)
            {
                j2 = 2;
            }
            else if (this.field_148301_e.field_78844_e < 1000L)
            {
                j2 = 3;
            }
            else
            {
                j2 = 4;
            }

            if (this.field_148301_e.field_78844_e < 0L)
            {
                s1 = "(no connection)";
            }
            else
            {
                s1 = this.field_148301_e.field_78844_e + "ms";
                s = this.field_148301_e.field_147412_i;
            }
        }
        else
        {
            b0 = 1;
            j2 = (int)(Minecraft.func_71386_F() / 100L + (long)(p_148279_1_ * 2) & 7L);

            if (j2 > 4)
            {
                j2 = 8 - j2;
            }

            s1 = "Pinging...";
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_148300_d.func_110434_K().func_110577_a(Gui.field_110324_m);
        Gui.func_146110_a(p_148279_2_ + p_148279_4_ - 15, p_148279_3_, (float)(b0 * 10), (float)(176 + j2 * 8), 10, 8, 256.0F, 256.0F);

        if (this.field_148301_e.func_147409_e() != null && !this.field_148301_e.func_147409_e().equals(this.field_148299_g))
        {
            this.field_148299_g = this.field_148301_e.func_147409_e();
            this.func_148297_b();
            this.field_148303_c.func_146795_p().func_78855_b();
        }

        if (this.field_148305_h != null)
        {
            this.field_148300_d.func_110434_K().func_110577_a(this.field_148306_i);
            Gui.func_146110_a(p_148279_2_, p_148279_3_, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
        }

        int k2 = p_148279_7_ - p_148279_2_;
        int l2 = p_148279_8_ - p_148279_3_;

        if (k2 >= p_148279_4_ - 15 && k2 <= p_148279_4_ - 5 && l2 >= 0 && l2 <= 8)
        {
            this.field_148303_c.func_146793_a(s1);
        }
        else if (k2 >= p_148279_4_ - i2 - 15 - 2 && k2 <= p_148279_4_ - 15 - 2 && l2 >= 0 && l2 <= 8)
        {
            this.field_148303_c.func_146793_a(s);
        }
    }

    private void func_148297_b()
    {
        if (this.field_148301_e.func_147409_e() == null)
        {
            this.field_148300_d.func_110434_K().func_147645_c(this.field_148306_i);
            this.field_148305_h = null;
        }
        else
        {
            ByteBuf bytebuf = Unpooled.copiedBuffer(this.field_148301_e.func_147409_e(), Charsets.UTF_8);
            ByteBuf bytebuf1 = Base64.decode(bytebuf);
            BufferedImage bufferedimage;
            label74:
            {
                try
                {
                    bufferedimage = ImageIO.read(new ByteBufInputStream(bytebuf1));
                    Validate.validState(bufferedimage.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
                    Validate.validState(bufferedimage.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
                    break label74;
                }
                catch (Exception exception)
                {
                    field_148304_a.error("Invalid icon for server " + this.field_148301_e.field_78847_a + " (" + this.field_148301_e.field_78845_b + ")", exception);
                    this.field_148301_e.func_147407_a((String)null);
                }
                finally
                {
                    bytebuf.release();
                    bytebuf1.release();
                }

                return;
            }

            if (this.field_148305_h == null)
            {
                this.field_148305_h = new DynamicTexture(bufferedimage.getWidth(), bufferedimage.getHeight());
                this.field_148300_d.func_110434_K().func_110579_a(this.field_148306_i, this.field_148305_h);
            }

            bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), this.field_148305_h.func_110565_c(), 0, bufferedimage.getWidth());
            this.field_148305_h.func_110564_a();
        }
    }

    public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_)
    {
        this.field_148303_c.func_146790_a(p_148278_1_);

        if (Minecraft.func_71386_F() - this.field_148298_f < 250L)
        {
            this.field_148303_c.func_146796_h();
        }

        this.field_148298_f = Minecraft.func_71386_F();
        return false;
    }

    public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {}

    public ServerData func_148296_a()
    {
        return this.field_148301_e;
    }
}