package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiMerchant extends GuiContainer
{
    private static final Logger field_147039_u = LogManager.getLogger();
    private static final ResourceLocation field_147038_v = new ResourceLocation("textures/gui/container/villager.png");
    private IMerchant field_147037_w;
    private GuiMerchant.MerchantButton field_147043_x;
    private GuiMerchant.MerchantButton field_147042_y;
    private int field_147041_z;
    private String field_147040_A;
    private static final String __OBFID = "CL_00000762";

    public GuiMerchant(InventoryPlayer p_i1096_1_, IMerchant p_i1096_2_, World p_i1096_3_, String p_i1096_4_)
    {
        super(new ContainerMerchant(p_i1096_1_, p_i1096_2_, p_i1096_3_));
        this.field_147037_w = p_i1096_2_;
        this.field_147040_A = p_i1096_4_ != null && p_i1096_4_.length() >= 1 ? p_i1096_4_ : I18n.func_135052_a("entity.Villager.name", new Object[0]);
    }

    public void func_73866_w_()
    {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        this.field_146292_n.add(this.field_147043_x = new GuiMerchant.MerchantButton(1, i + 120 + 27, j + 24 - 1, true));
        this.field_146292_n.add(this.field_147042_y = new GuiMerchant.MerchantButton(2, i + 36 - 19, j + 24 - 1, false));
        this.field_147043_x.field_146124_l = false;
        this.field_147042_y.field_146124_l = false;
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_)
    {
        this.field_146289_q.func_78276_b(this.field_147040_A, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(this.field_147040_A) / 2, 6, 4210752);
        this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
    }

    public void func_73876_c()
    {
        super.func_73876_c();
        MerchantRecipeList merchantrecipelist = this.field_147037_w.func_70934_b(this.field_146297_k.field_71439_g);

        if (merchantrecipelist != null)
        {
            this.field_147043_x.field_146124_l = this.field_147041_z < merchantrecipelist.size() - 1;
            this.field_147042_y.field_146124_l = this.field_147041_z > 0;
        }
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        boolean flag = false;

        if (p_146284_1_ == this.field_147043_x)
        {
            ++this.field_147041_z;
            flag = true;
        }
        else if (p_146284_1_ == this.field_147042_y)
        {
            --this.field_147041_z;
            flag = true;
        }

        if (flag)
        {
            ((ContainerMerchant)this.field_147002_h).func_75175_c(this.field_147041_z);
            ByteBuf bytebuf = Unpooled.buffer();

            try
            {
                bytebuf.writeInt(this.field_147041_z);
                this.field_146297_k.func_147114_u().func_147297_a(new C17PacketCustomPayload("MC|TrSel", bytebuf));
            }
            catch (Exception exception)
            {
                field_147039_u.error("Couldn\'t send trade info", exception);
            }
            finally
            {
                bytebuf.release();
            }
        }
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(field_147038_v);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        MerchantRecipeList merchantrecipelist = this.field_147037_w.func_70934_b(this.field_146297_k.field_71439_g);

        if (merchantrecipelist != null && !merchantrecipelist.isEmpty())
        {
            int i1 = this.field_147041_z;
            MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1);

            if (merchantrecipe.func_82784_g())
            {
                this.field_146297_k.func_110434_K().func_110577_a(field_147038_v);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                this.func_73729_b(this.field_147003_i + 83, this.field_147009_r + 21, 212, 0, 28, 21);
                this.func_73729_b(this.field_147003_i + 83, this.field_147009_r + 51, 212, 0, 28, 21);
            }
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
        MerchantRecipeList merchantrecipelist = this.field_147037_w.func_70934_b(this.field_146297_k.field_71439_g);

        if (merchantrecipelist != null && !merchantrecipelist.isEmpty())
        {
            int k = (this.field_146294_l - this.field_146999_f) / 2;
            int l = (this.field_146295_m - this.field_147000_g) / 2;
            int i1 = this.field_147041_z;
            MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1);
            GL11.glPushMatrix();
            ItemStack itemstack = merchantrecipe.func_77394_a();
            ItemStack itemstack1 = merchantrecipe.func_77396_b();
            ItemStack itemstack2 = merchantrecipe.func_77397_d();
            RenderHelper.func_74520_c();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glEnable(GL11.GL_LIGHTING);
            field_146296_j.field_77023_b = 100.0F;
            field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack, k + 36, l + 24);
            field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack, k + 36, l + 24);

            if (itemstack1 != null)
            {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack1, k + 62, l + 24);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack1, k + 62, l + 24);
            }

            field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack2, k + 120, l + 24);
            field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack2, k + 120, l + 24);
            field_146296_j.field_77023_b = 0.0F;
            GL11.glDisable(GL11.GL_LIGHTING);

            if (this.func_146978_c(36, 24, 16, 16, p_73863_1_, p_73863_2_))
            {
                this.func_146285_a(itemstack, p_73863_1_, p_73863_2_);
            }
            else if (itemstack1 != null && this.func_146978_c(62, 24, 16, 16, p_73863_1_, p_73863_2_))
            {
                this.func_146285_a(itemstack1, p_73863_1_, p_73863_2_);
            }
            else if (this.func_146978_c(120, 24, 16, 16, p_73863_1_, p_73863_2_))
            {
                this.func_146285_a(itemstack2, p_73863_1_, p_73863_2_);
            }

            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.func_74519_b();
        }
    }

    public IMerchant func_147035_g()
    {
        return this.field_147037_w;
    }

    @SideOnly(Side.CLIENT)
    static class MerchantButton extends GuiButton
        {
            private final boolean field_146157_o;
            private static final String __OBFID = "CL_00000763";

            public MerchantButton(int p_i1095_1_, int p_i1095_2_, int p_i1095_3_, boolean p_i1095_4_)
            {
                super(p_i1095_1_, p_i1095_2_, p_i1095_3_, 12, 19, "");
                this.field_146157_o = p_i1095_4_;
            }

            public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
            {
                if (this.field_146125_m)
                {
                    p_146112_1_.func_110434_K().func_110577_a(GuiMerchant.field_147038_v);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    boolean flag = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
                    int k = 0;
                    int l = 176;

                    if (!this.field_146124_l)
                    {
                        l += this.field_146120_f * 2;
                    }
                    else if (flag)
                    {
                        l += this.field_146120_f;
                    }

                    if (!this.field_146157_o)
                    {
                        k += this.field_146121_g;
                    }

                    this.func_73729_b(this.field_146128_h, this.field_146129_i, l, k, this.field_146120_f, this.field_146121_g);
                }
            }
        }
}