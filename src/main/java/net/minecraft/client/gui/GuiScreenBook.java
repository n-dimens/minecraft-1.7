package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiScreenBook extends GuiScreen
{
    private static final Logger field_146473_a = LogManager.getLogger();
    private static final ResourceLocation field_146466_f = new ResourceLocation("textures/gui/book.png");
    private final EntityPlayer field_146468_g;
    private final ItemStack field_146474_h;
    private final boolean field_146475_i;
    private boolean field_146481_r;
    private boolean field_146480_s;
    private int field_146479_t;
    private int field_146478_u = 192;
    private int field_146477_v = 192;
    private int field_146476_w = 1;
    private int field_146484_x;
    private NBTTagList field_146483_y;
    private String field_146482_z = "";
    private GuiScreenBook.NextPageButton field_146470_A;
    private GuiScreenBook.NextPageButton field_146471_B;
    private GuiButton field_146472_C;
    private GuiButton field_146465_D;
    private GuiButton field_146467_E;
    private GuiButton field_146469_F;
    private static final String __OBFID = "CL_00000744";

    public GuiScreenBook(EntityPlayer p_i1080_1_, ItemStack p_i1080_2_, boolean p_i1080_3_)
    {
        this.field_146468_g = p_i1080_1_;
        this.field_146474_h = p_i1080_2_;
        this.field_146475_i = p_i1080_3_;

        if (p_i1080_2_.func_77942_o())
        {
            NBTTagCompound nbttagcompound = p_i1080_2_.func_77978_p();
            this.field_146483_y = nbttagcompound.func_150295_c("pages", 8);

            if (this.field_146483_y != null)
            {
                this.field_146483_y = (NBTTagList)this.field_146483_y.func_74737_b();
                this.field_146476_w = this.field_146483_y.func_74745_c();

                if (this.field_146476_w < 1)
                {
                    this.field_146476_w = 1;
                }
            }
        }

        if (this.field_146483_y == null && p_i1080_3_)
        {
            this.field_146483_y = new NBTTagList();
            this.field_146483_y.func_74742_a(new NBTTagString(""));
            this.field_146476_w = 1;
        }
    }

    public void func_73876_c()
    {
        super.func_73876_c();
        ++this.field_146479_t;
    }

    public void func_73866_w_()
    {
        this.field_146292_n.clear();
        Keyboard.enableRepeatEvents(true);

        if (this.field_146475_i)
        {
            this.field_146292_n.add(this.field_146465_D = new GuiButton(3, this.field_146294_l / 2 - 100, 4 + this.field_146477_v, 98, 20, I18n.func_135052_a("book.signButton", new Object[0])));
            this.field_146292_n.add(this.field_146472_C = new GuiButton(0, this.field_146294_l / 2 + 2, 4 + this.field_146477_v, 98, 20, I18n.func_135052_a("gui.done", new Object[0])));
            this.field_146292_n.add(this.field_146467_E = new GuiButton(5, this.field_146294_l / 2 - 100, 4 + this.field_146477_v, 98, 20, I18n.func_135052_a("book.finalizeButton", new Object[0])));
            this.field_146292_n.add(this.field_146469_F = new GuiButton(4, this.field_146294_l / 2 + 2, 4 + this.field_146477_v, 98, 20, I18n.func_135052_a("gui.cancel", new Object[0])));
        }
        else
        {
            this.field_146292_n.add(this.field_146472_C = new GuiButton(0, this.field_146294_l / 2 - 100, 4 + this.field_146477_v, 200, 20, I18n.func_135052_a("gui.done", new Object[0])));
        }

        int i = (this.field_146294_l - this.field_146478_u) / 2;
        byte b0 = 2;
        this.field_146292_n.add(this.field_146470_A = new GuiScreenBook.NextPageButton(1, i + 120, b0 + 154, true));
        this.field_146292_n.add(this.field_146471_B = new GuiScreenBook.NextPageButton(2, i + 38, b0 + 154, false));
        this.func_146464_h();
    }

    public void func_146281_b()
    {
        Keyboard.enableRepeatEvents(false);
    }

    private void func_146464_h()
    {
        this.field_146470_A.field_146125_m = !this.field_146480_s && (this.field_146484_x < this.field_146476_w - 1 || this.field_146475_i);
        this.field_146471_B.field_146125_m = !this.field_146480_s && this.field_146484_x > 0;
        this.field_146472_C.field_146125_m = !this.field_146475_i || !this.field_146480_s;

        if (this.field_146475_i)
        {
            this.field_146465_D.field_146125_m = !this.field_146480_s;
            this.field_146469_F.field_146125_m = this.field_146480_s;
            this.field_146467_E.field_146125_m = this.field_146480_s;
            this.field_146467_E.field_146124_l = this.field_146482_z.trim().length() > 0;
        }
    }

    private void func_146462_a(boolean p_146462_1_)
    {
        if (this.field_146475_i && this.field_146481_r)
        {
            if (this.field_146483_y != null)
            {
                String s;

                while (this.field_146483_y.func_74745_c() > 1)
                {
                    s = this.field_146483_y.func_150307_f(this.field_146483_y.func_74745_c() - 1);

                    if (s.length() != 0)
                    {
                        break;
                    }

                    this.field_146483_y.func_74744_a(this.field_146483_y.func_74745_c() - 1);
                }

                if (this.field_146474_h.func_77942_o())
                {
                    NBTTagCompound nbttagcompound = this.field_146474_h.func_77978_p();
                    nbttagcompound.func_74782_a("pages", this.field_146483_y);
                }
                else
                {
                    this.field_146474_h.func_77983_a("pages", this.field_146483_y);
                }

                s = "MC|BEdit";

                if (p_146462_1_)
                {
                    s = "MC|BSign";
                    this.field_146474_h.func_77983_a("author", new NBTTagString(this.field_146468_g.func_70005_c_()));
                    this.field_146474_h.func_77983_a("title", new NBTTagString(this.field_146482_z.trim()));
                    this.field_146474_h.func_150996_a(Items.WRITTEN_BOOK);
                }

                ByteBuf bytebuf = Unpooled.buffer();

                try
                {
                    (new PacketBuffer(bytebuf)).func_150788_a(this.field_146474_h);
                    this.field_146297_k.func_147114_u().func_147297_a(new C17PacketCustomPayload(s, bytebuf));
                }
                catch (Exception exception)
                {
                    field_146473_a.error("Couldn\'t send book info", exception);
                }
                finally
                {
                    bytebuf.release();
                }
            }
        }
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        if (p_146284_1_.field_146124_l)
        {
            if (p_146284_1_.field_146127_k == 0)
            {
                this.field_146297_k.func_147108_a((GuiScreen)null);
                this.func_146462_a(false);
            }
            else if (p_146284_1_.field_146127_k == 3 && this.field_146475_i)
            {
                this.field_146480_s = true;
            }
            else if (p_146284_1_.field_146127_k == 1)
            {
                if (this.field_146484_x < this.field_146476_w - 1)
                {
                    ++this.field_146484_x;
                }
                else if (this.field_146475_i)
                {
                    this.func_146461_i();

                    if (this.field_146484_x < this.field_146476_w - 1)
                    {
                        ++this.field_146484_x;
                    }
                }
            }
            else if (p_146284_1_.field_146127_k == 2)
            {
                if (this.field_146484_x > 0)
                {
                    --this.field_146484_x;
                }
            }
            else if (p_146284_1_.field_146127_k == 5 && this.field_146480_s)
            {
                this.func_146462_a(true);
                this.field_146297_k.func_147108_a((GuiScreen)null);
            }
            else if (p_146284_1_.field_146127_k == 4 && this.field_146480_s)
            {
                this.field_146480_s = false;
            }

            this.func_146464_h();
        }
    }

    private void func_146461_i()
    {
        if (this.field_146483_y != null && this.field_146483_y.func_74745_c() < 50)
        {
            this.field_146483_y.func_74742_a(new NBTTagString(""));
            ++this.field_146476_w;
            this.field_146481_r = true;
        }
    }

    protected void func_73869_a(char p_73869_1_, int p_73869_2_)
    {
        super.func_73869_a(p_73869_1_, p_73869_2_);

        if (this.field_146475_i)
        {
            if (this.field_146480_s)
            {
                this.func_146460_c(p_73869_1_, p_73869_2_);
            }
            else
            {
                this.func_146463_b(p_73869_1_, p_73869_2_);
            }
        }
    }

    private void func_146463_b(char p_146463_1_, int p_146463_2_)
    {
        switch (p_146463_1_)
        {
            case 22:
                this.func_146459_b(GuiScreen.func_146277_j());
                return;
            default:
                switch (p_146463_2_)
                {
                    case 14:
                        String s = this.func_146456_p();

                        if (s.length() > 0)
                        {
                            this.func_146457_a(s.substring(0, s.length() - 1));
                        }

                        return;
                    case 28:
                    case 156:
                        this.func_146459_b("\n");
                        return;
                    default:
                        if (ChatAllowedCharacters.func_71566_a(p_146463_1_))
                        {
                            this.func_146459_b(Character.toString(p_146463_1_));
                        }
                }
        }
    }

    private void func_146460_c(char p_146460_1_, int p_146460_2_)
    {
        switch (p_146460_2_)
        {
            case 14:
                if (!this.field_146482_z.isEmpty())
                {
                    this.field_146482_z = this.field_146482_z.substring(0, this.field_146482_z.length() - 1);
                    this.func_146464_h();
                }

                return;
            case 28:
            case 156:
                if (!this.field_146482_z.isEmpty())
                {
                    this.func_146462_a(true);
                    this.field_146297_k.func_147108_a((GuiScreen)null);
                }

                return;
            default:
                if (this.field_146482_z.length() < 16 && ChatAllowedCharacters.func_71566_a(p_146460_1_))
                {
                    this.field_146482_z = this.field_146482_z + Character.toString(p_146460_1_);
                    this.func_146464_h();
                    this.field_146481_r = true;
                }
        }
    }

    private String func_146456_p()
    {
        return this.field_146483_y != null && this.field_146484_x >= 0 && this.field_146484_x < this.field_146483_y.func_74745_c() ? this.field_146483_y.func_150307_f(this.field_146484_x) : "";
    }

    private void func_146457_a(String p_146457_1_)
    {
        if (this.field_146483_y != null && this.field_146484_x >= 0 && this.field_146484_x < this.field_146483_y.func_74745_c())
        {
            this.field_146483_y.func_150304_a(this.field_146484_x, new NBTTagString(p_146457_1_));
            this.field_146481_r = true;
        }
    }

    private void func_146459_b(String p_146459_1_)
    {
        String s1 = this.func_146456_p();
        String s2 = s1 + p_146459_1_;
        int i = this.field_146289_q.func_78267_b(s2 + "" + EnumChatFormatting.BLACK + "_", 118);

        if (i <= 118 && s2.length() < 256)
        {
            this.func_146457_a(s2);
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(field_146466_f);
        int k = (this.field_146294_l - this.field_146478_u) / 2;
        byte b0 = 2;
        this.func_73729_b(k, b0, 0, 0, this.field_146478_u, this.field_146477_v);
        String s;
        String s1;
        int l;

        if (this.field_146480_s)
        {
            s = this.field_146482_z;

            if (this.field_146475_i)
            {
                if (this.field_146479_t / 6 % 2 == 0)
                {
                    s = s + "" + EnumChatFormatting.BLACK + "_";
                }
                else
                {
                    s = s + "" + EnumChatFormatting.GRAY + "_";
                }
            }

            s1 = I18n.func_135052_a("book.editTitle", new Object[0]);
            l = this.field_146289_q.func_78256_a(s1);
            this.field_146289_q.func_78276_b(s1, k + 36 + (116 - l) / 2, b0 + 16 + 16, 0);
            int i1 = this.field_146289_q.func_78256_a(s);
            this.field_146289_q.func_78276_b(s, k + 36 + (116 - i1) / 2, b0 + 48, 0);
            String s2 = I18n.func_135052_a("book.byAuthor", new Object[] {this.field_146468_g.func_70005_c_()});
            int j1 = this.field_146289_q.func_78256_a(s2);
            this.field_146289_q.func_78276_b(EnumChatFormatting.DARK_GRAY + s2, k + 36 + (116 - j1) / 2, b0 + 48 + 10, 0);
            String s3 = I18n.func_135052_a("book.finalizeWarning", new Object[0]);
            this.field_146289_q.func_78279_b(s3, k + 36, b0 + 80, 116, 0);
        }
        else
        {
            s = I18n.func_135052_a("book.pageIndicator", new Object[] {Integer.valueOf(this.field_146484_x + 1), Integer.valueOf(this.field_146476_w)});
            s1 = "";

            if (this.field_146483_y != null && this.field_146484_x >= 0 && this.field_146484_x < this.field_146483_y.func_74745_c())
            {
                s1 = this.field_146483_y.func_150307_f(this.field_146484_x);
            }

            if (this.field_146475_i)
            {
                if (this.field_146289_q.func_78260_a())
                {
                    s1 = s1 + "_";
                }
                else if (this.field_146479_t / 6 % 2 == 0)
                {
                    s1 = s1 + "" + EnumChatFormatting.BLACK + "_";
                }
                else
                {
                    s1 = s1 + "" + EnumChatFormatting.GRAY + "_";
                }
            }

            l = this.field_146289_q.func_78256_a(s);
            this.field_146289_q.func_78276_b(s, k - l + this.field_146478_u - 44, b0 + 16, 0);
            this.field_146289_q.func_78279_b(s1, k + 36, b0 + 16 + 16, 116, 0);
        }

        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton
        {
            private final boolean field_146151_o;
            private static final String __OBFID = "CL_00000745";

            public NextPageButton(int p_i1079_1_, int p_i1079_2_, int p_i1079_3_, boolean p_i1079_4_)
            {
                super(p_i1079_1_, p_i1079_2_, p_i1079_3_, 23, 13, "");
                this.field_146151_o = p_i1079_4_;
            }

            public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
            {
                if (this.field_146125_m)
                {
                    boolean flag = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    p_146112_1_.func_110434_K().func_110577_a(GuiScreenBook.field_146466_f);
                    int k = 0;
                    int l = 192;

                    if (flag)
                    {
                        k += 23;
                    }

                    if (!this.field_146151_o)
                    {
                        l += 13;
                    }

                    this.func_73729_b(this.field_146128_h, this.field_146129_i, k, l, 23, 13);
                }
            }
        }
}