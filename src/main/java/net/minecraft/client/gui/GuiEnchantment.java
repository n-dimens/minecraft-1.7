package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.Project;

@SideOnly(Side.CLIENT)
public class GuiEnchantment extends GuiContainer
{
    private static final ResourceLocation field_147078_C = new ResourceLocation("textures/gui/container/enchanting_table.png");
    private static final ResourceLocation field_147070_D = new ResourceLocation("textures/entity/enchanting_table_book.png");
    private static final ModelBook field_147072_E = new ModelBook();
    private Random field_147074_F = new Random();
    private ContainerEnchantment field_147075_G;
    public int field_147073_u;
    public float field_147071_v;
    public float field_147069_w;
    public float field_147082_x;
    public float field_147081_y;
    public float field_147080_z;
    public float field_147076_A;
    ItemStack field_147077_B;
    private String field_147079_H;
    private static final String __OBFID = "CL_00000757";

    public GuiEnchantment(InventoryPlayer p_i1090_1_, World p_i1090_2_, int p_i1090_3_, int p_i1090_4_, int p_i1090_5_, String p_i1090_6_)
    {
        super(new ContainerEnchantment(p_i1090_1_, p_i1090_2_, p_i1090_3_, p_i1090_4_, p_i1090_5_));
        this.field_147075_G = (ContainerEnchantment)this.field_147002_h;
        this.field_147079_H = p_i1090_6_;
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_)
    {
        this.field_146289_q.func_78276_b(this.field_147079_H == null ? I18n.func_135052_a("container.enchant", new Object[0]) : this.field_147079_H, 12, 5, 4210752);
        this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
    }

    public void func_73876_c()
    {
        super.func_73876_c();
        this.func_147068_g();
    }

    protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
        int l = (this.field_146294_l - this.field_146999_f) / 2;
        int i1 = (this.field_146295_m - this.field_147000_g) / 2;

        for (int j1 = 0; j1 < 3; ++j1)
        {
            int k1 = p_73864_1_ - (l + 60);
            int l1 = p_73864_2_ - (i1 + 14 + 19 * j1);

            if (k1 >= 0 && l1 >= 0 && k1 < 108 && l1 < 19 && this.field_147075_G.func_75140_a(this.field_146297_k.field_71439_g, j1))
            {
                this.field_146297_k.field_71442_b.func_78756_a(this.field_147075_G.field_75152_c, j1);
            }
        }
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(field_147078_C);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        GL11.glPushMatrix();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        ScaledResolution scaledresolution = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        GL11.glViewport((scaledresolution.func_78326_a() - 320) / 2 * scaledresolution.func_78325_e(), (scaledresolution.func_78328_b() - 240) / 2 * scaledresolution.func_78325_e(), 320 * scaledresolution.func_78325_e(), 240 * scaledresolution.func_78325_e());
        GL11.glTranslatef(-0.34F, 0.23F, 0.0F);
        Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
        float f1 = 1.0F;
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        RenderHelper.func_74519_b();
        GL11.glTranslatef(0.0F, 3.3F, -16.0F);
        GL11.glScalef(f1, f1, f1);
        float f2 = 5.0F;
        GL11.glScalef(f2, f2, f2);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(field_147070_D);
        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
        float f3 = this.field_147076_A + (this.field_147080_z - this.field_147076_A) * p_146976_1_;
        GL11.glTranslatef((1.0F - f3) * 0.2F, (1.0F - f3) * 0.1F, (1.0F - f3) * 0.25F);
        GL11.glRotatef(-(1.0F - f3) * 90.0F - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        float f4 = this.field_147069_w + (this.field_147071_v - this.field_147069_w) * p_146976_1_ + 0.25F;
        float f5 = this.field_147069_w + (this.field_147071_v - this.field_147069_w) * p_146976_1_ + 0.75F;
        f4 = (f4 - (float)MathHelper.func_76140_b((double)f4)) * 1.6F - 0.3F;
        f5 = (f5 - (float)MathHelper.func_76140_b((double)f5)) * 1.6F - 0.3F;

        if (f4 < 0.0F)
        {
            f4 = 0.0F;
        }

        if (f5 < 0.0F)
        {
            f5 = 0.0F;
        }

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }

        if (f5 > 1.0F)
        {
            f5 = 1.0F;
        }

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        field_147072_E.func_78088_a((Entity)null, 0.0F, f4, f5, f3, 0.0F, 0.0625F);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.func_74518_a();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glViewport(0, 0, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        EnchantmentNameParts.field_148338_a.func_148335_a(this.field_147075_G.field_75166_f);

        for (int i1 = 0; i1 < 3; ++i1)
        {
            String s = EnchantmentNameParts.field_148338_a.func_148334_a();
            this.field_73735_i = 0.0F;
            this.field_146297_k.func_110434_K().func_110577_a(field_147078_C);
            int j1 = this.field_147075_G.field_75167_g[i1];
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            if (j1 == 0)
            {
                this.func_73729_b(k + 60, l + 14 + 19 * i1, 0, 185, 108, 19);
            }
            else
            {
                String s1 = "" + j1;
                FontRenderer fontrenderer = this.field_146297_k.field_71464_q;
                int k1 = 6839882;

                if (this.field_146297_k.field_71439_g.field_71068_ca < j1 && !this.field_146297_k.field_71439_g.capabilities.instabuild)
                {
                    this.func_73729_b(k + 60, l + 14 + 19 * i1, 0, 185, 108, 19);
                    fontrenderer.func_78279_b(s, k + 62, l + 16 + 19 * i1, 104, (k1 & 16711422) >> 1);
                    fontrenderer = this.field_146297_k.field_71466_p;
                    k1 = 4226832;
                    fontrenderer.func_78261_a(s1, k + 62 + 104 - fontrenderer.func_78256_a(s1), l + 16 + 19 * i1 + 7, k1);
                }
                else
                {
                    int l1 = p_146976_2_ - (k + 60);
                    int i2 = p_146976_3_ - (l + 14 + 19 * i1);

                    if (l1 >= 0 && i2 >= 0 && l1 < 108 && i2 < 19)
                    {
                        this.func_73729_b(k + 60, l + 14 + 19 * i1, 0, 204, 108, 19);
                        k1 = 16777088;
                    }
                    else
                    {
                        this.func_73729_b(k + 60, l + 14 + 19 * i1, 0, 166, 108, 19);
                    }

                    fontrenderer.func_78279_b(s, k + 62, l + 16 + 19 * i1, 104, k1);
                    fontrenderer = this.field_146297_k.field_71466_p;
                    k1 = 8453920;
                    fontrenderer.func_78261_a(s1, k + 62 + 104 - fontrenderer.func_78256_a(s1), l + 16 + 19 * i1 + 7, k1);
                }
            }
        }
    }

    public void func_147068_g()
    {
        ItemStack itemstack = this.field_147002_h.func_75139_a(0).func_75211_c();

        if (!ItemStack.func_77989_b(itemstack, this.field_147077_B))
        {
            this.field_147077_B = itemstack;

            do
            {
                this.field_147082_x += (float)(this.field_147074_F.nextInt(4) - this.field_147074_F.nextInt(4));
            }
            while (this.field_147071_v <= this.field_147082_x + 1.0F && this.field_147071_v >= this.field_147082_x - 1.0F);
        }

        ++this.field_147073_u;
        this.field_147069_w = this.field_147071_v;
        this.field_147076_A = this.field_147080_z;
        boolean flag = false;

        for (int i = 0; i < 3; ++i)
        {
            if (this.field_147075_G.field_75167_g[i] != 0)
            {
                flag = true;
            }
        }

        if (flag)
        {
            this.field_147080_z += 0.2F;
        }
        else
        {
            this.field_147080_z -= 0.2F;
        }

        if (this.field_147080_z < 0.0F)
        {
            this.field_147080_z = 0.0F;
        }

        if (this.field_147080_z > 1.0F)
        {
            this.field_147080_z = 1.0F;
        }

        float f1 = (this.field_147082_x - this.field_147071_v) * 0.4F;
        float f = 0.2F;

        if (f1 < -f)
        {
            f1 = -f;
        }

        if (f1 > f)
        {
            f1 = f;
        }

        this.field_147081_y += (f1 - this.field_147081_y) * 0.9F;
        this.field_147071_v += this.field_147081_y;
    }
}