package net.minecraft.client.gui.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiInventory extends InventoryEffectRenderer
{
    private float field_147048_u;
    private float field_147047_v;
    private static final String __OBFID = "CL_00000761";

    public GuiInventory(EntityPlayer p_i1094_1_)
    {
        super(p_i1094_1_.field_71069_bz);
        this.field_146291_p = true;
    }

    public void func_73876_c()
    {
        if (this.field_146297_k.field_71442_b.func_78758_h())
        {
            this.field_146297_k.func_147108_a(new GuiContainerCreative(this.field_146297_k.field_71439_g));
        }
    }

    public void func_73866_w_()
    {
        this.field_146292_n.clear();

        if (this.field_146297_k.field_71442_b.func_78758_h())
        {
            this.field_146297_k.func_147108_a(new GuiContainerCreative(this.field_146297_k.field_71439_g));
        }
        else
        {
            super.func_73866_w_();
        }
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_)
    {
        this.field_146289_q.func_78276_b(I18n.func_135052_a("container.crafting", new Object[0]), 86, 16, 4210752);
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
        this.field_147048_u = (float)p_73863_1_;
        this.field_147047_v = (float)p_73863_2_;
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(field_147001_a);
        int k = this.field_147003_i;
        int l = this.field_147009_r;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        func_147046_a(k + 51, l + 75, 30, (float)(k + 51) - this.field_147048_u, (float)(l + 75 - 50) - this.field_147047_v, this.field_146297_k.field_71439_g);
    }

    public static void func_147046_a(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 50.0F);
        GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = p_147046_5_.field_70761_aq;
        float f3 = p_147046_5_.field_70177_z;
        float f4 = p_147046_5_.field_70125_A;
        float f5 = p_147046_5_.field_70758_at;
        float f6 = p_147046_5_.field_70759_as;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.func_74519_b();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        p_147046_5_.field_70761_aq = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
        p_147046_5_.field_70177_z = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
        p_147046_5_.field_70125_A = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
        p_147046_5_.field_70759_as = p_147046_5_.field_70177_z;
        p_147046_5_.field_70758_at = p_147046_5_.field_70177_z;
        GL11.glTranslatef(0.0F, p_147046_5_.field_70129_M, 0.0F);
        RenderManager.field_78727_a.field_78735_i = 180.0F;
        RenderManager.field_78727_a.func_147940_a(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        p_147046_5_.field_70761_aq = f2;
        p_147046_5_.field_70177_z = f3;
        p_147046_5_.field_70125_A = f4;
        p_147046_5_.field_70758_at = f5;
        p_147046_5_.field_70759_as = f6;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        if (p_146284_1_.field_146127_k == 0)
        {
            this.field_146297_k.func_147108_a(new GuiAchievements(this, this.field_146297_k.field_71439_g.func_146107_m()));
        }

        if (p_146284_1_.field_146127_k == 1)
        {
            this.field_146297_k.func_147108_a(new GuiStats(this, this.field_146297_k.field_71439_g.func_146107_m()));
        }
    }
}