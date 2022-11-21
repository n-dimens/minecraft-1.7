package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBiped extends RenderLiving
{
    public ModelBiped field_77071_a;
    protected float field_77070_b;
    protected ModelBiped field_82423_g;
    protected ModelBiped field_82425_h;
    private static final Map field_110859_k = Maps.newHashMap();
    public static String[] field_82424_k = new String[] {"leather", "chainmail", "iron", "diamond", "gold"};
    private static final String __OBFID = "CL_00001001";

    public RenderBiped(ModelBiped p_i1257_1_, float p_i1257_2_)
    {
        this(p_i1257_1_, p_i1257_2_, 1.0F);
    }

    public RenderBiped(ModelBiped p_i1258_1_, float p_i1258_2_, float p_i1258_3_)
    {
        super(p_i1258_1_, p_i1258_2_);
        this.field_77071_a = p_i1258_1_;
        this.field_77070_b = p_i1258_3_;
        this.func_82421_b();
    }

    protected void func_82421_b()
    {
        this.field_82423_g = new ModelBiped(1.0F);
        this.field_82425_h = new ModelBiped(0.5F);
    }

    public static ResourceLocation func_110857_a(ItemArmor p_110857_0_, int p_110857_1_)
    {
        return func_110858_a(p_110857_0_, p_110857_1_, (String)null);
    }

    public static ResourceLocation func_110858_a(ItemArmor p_110858_0_, int p_110858_1_, String p_110858_2_)
    {
        String s1 = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[] {field_82424_k[p_110858_0_.field_77880_c], Integer.valueOf(p_110858_1_ == 2 ? 2 : 1), p_110858_2_ == null ? "" : String.format("_%s", new Object[]{p_110858_2_})});
        ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1);

        if (resourcelocation == null)
        {
            resourcelocation = new ResourceLocation(s1);
            field_110859_k.put(s1, resourcelocation);
        }

        return resourcelocation;
    }

    protected int func_77032_a(EntityLiving p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        ItemStack itemstack = p_77032_1_.func_130225_q(3 - p_77032_2_);

        if (itemstack != null)
        {
            Item item = itemstack.func_77973_b();

            if (item instanceof ItemArmor)
            {
                ItemArmor itemarmor = (ItemArmor)item;
                this.func_110776_a(func_110857_a(itemarmor, p_77032_2_));
                ModelBiped modelbiped = p_77032_2_ == 2 ? this.field_82425_h : this.field_82423_g;
                modelbiped.field_78116_c.field_78806_j = p_77032_2_ == 0;
                modelbiped.field_78114_d.field_78806_j = p_77032_2_ == 0;
                modelbiped.field_78115_e.field_78806_j = p_77032_2_ == 1 || p_77032_2_ == 2;
                modelbiped.field_78112_f.field_78806_j = p_77032_2_ == 1;
                modelbiped.field_78113_g.field_78806_j = p_77032_2_ == 1;
                modelbiped.field_78123_h.field_78806_j = p_77032_2_ == 2 || p_77032_2_ == 3;
                modelbiped.field_78124_i.field_78806_j = p_77032_2_ == 2 || p_77032_2_ == 3;
                this.func_77042_a(modelbiped);
                modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
                modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
                modelbiped.field_78091_s = this.field_77045_g.field_78091_s;

                if (itemarmor.func_82812_d() == ItemArmor.ArmorMaterial.CLOTH)
                {
                    int j = itemarmor.func_82814_b(itemstack);
                    float f1 = (float)(j >> 16 & 255) / 255.0F;
                    float f2 = (float)(j >> 8 & 255) / 255.0F;
                    float f3 = (float)(j & 255) / 255.0F;
                    GL11.glColor3f(f1, f2, f3);

                    if (itemstack.func_77948_v())
                    {
                        return 31;
                    }

                    return 16;
                }

                GL11.glColor3f(1.0F, 1.0F, 1.0F);

                if (itemstack.func_77948_v())
                {
                    return 15;
                }

                return 1;
            }
        }

        return -1;
    }

    protected void func_82408_c(EntityLiving p_82408_1_, int p_82408_2_, float p_82408_3_)
    {
        ItemStack itemstack = p_82408_1_.func_130225_q(3 - p_82408_2_);

        if (itemstack != null)
        {
            Item item = itemstack.func_77973_b();

            if (item instanceof ItemArmor)
            {
                this.func_110776_a(func_110858_a((ItemArmor)item, p_82408_2_, "overlay"));
                float f1 = 1.0F;
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }
        }
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        ItemStack itemstack = p_76986_1_.func_70694_bm();
        this.func_82420_a(p_76986_1_, itemstack);
        double d3 = p_76986_4_ - (double)p_76986_1_.field_70129_M;

        if (p_76986_1_.func_70093_af())
        {
            d3 -= 0.125D;
        }

        super.func_76986_a(p_76986_1_, p_76986_2_, d3, p_76986_6_, p_76986_8_, p_76986_9_);
        this.field_82423_g.field_78118_o = this.field_82425_h.field_78118_o = this.field_77071_a.field_78118_o = false;
        this.field_82423_g.field_78117_n = this.field_82425_h.field_78117_n = this.field_77071_a.field_78117_n = false;
        this.field_82423_g.field_78120_m = this.field_82425_h.field_78120_m = this.field_77071_a.field_78120_m = 0;
    }

    protected ResourceLocation func_110775_a(EntityLiving p_110775_1_)
    {
        return null;
    }

    protected void func_82420_a(EntityLiving p_82420_1_, ItemStack p_82420_2_)
    {
        this.field_82423_g.field_78120_m = this.field_82425_h.field_78120_m = this.field_77071_a.field_78120_m = p_82420_2_ != null ? 1 : 0;
        this.field_82423_g.field_78117_n = this.field_82425_h.field_78117_n = this.field_77071_a.field_78117_n = p_82420_1_.func_70093_af();
    }

    protected void func_77029_c(EntityLiving p_77029_1_, float p_77029_2_)
    {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        super.func_77029_c(p_77029_1_, p_77029_2_);
        ItemStack itemstack = p_77029_1_.func_70694_bm();
        ItemStack itemstack1 = p_77029_1_.func_130225_q(3);
        Item item;
        float f1;

        if (itemstack1 != null)
        {
            GL11.glPushMatrix();
            this.field_77071_a.field_78116_c.func_78794_c(0.0625F);
            item = itemstack1.func_77973_b();

            if (item instanceof ItemBlock)
            {
                if (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))
                {
                    f1 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(f1, -f1, -f1);
                }

                this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack1, 0);
            }
            else if (item == Items.field_151144_bL)
            {
                f1 = 1.0625F;
                GL11.glScalef(f1, -f1, -f1);
                GameProfile gameprofile = null;

                if (itemstack1.func_77942_o())
                {
                    NBTTagCompound nbttagcompound = itemstack1.func_77978_p();

                    if (nbttagcompound.func_150297_b("SkullOwner", 10))
                    {
                        gameprofile = NBTUtil.func_152459_a(nbttagcompound.func_74775_l("SkullOwner"));
                    }
                    else if (nbttagcompound.func_150297_b("SkullOwner", 8) && !StringUtils.func_151246_b(nbttagcompound.func_74779_i("SkullOwner")))
                    {
                        gameprofile = new GameProfile((UUID)null, nbttagcompound.func_74779_i("SkullOwner"));
                    }
                }

                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack1.func_77960_j(), gameprofile);
            }

            GL11.glPopMatrix();
        }

        if (itemstack != null && itemstack.func_77973_b() != null)
        {
            item = itemstack.func_77973_b();
            GL11.glPushMatrix();

            if (this.field_77045_g.field_78091_s)
            {
                f1 = 0.5F;
                GL11.glTranslatef(0.0F, 0.625F, 0.0F);
                GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
                GL11.glScalef(f1, f1, f1);
            }

            this.field_77071_a.field_78112_f.func_78794_c(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            if (item instanceof ItemBlock && RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))
            {
                f1 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f1 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f1, -f1, f1);
            }
            else if (item == Items.field_151031_f)
            {
                f1 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f1, -f1, f1);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (item.func_77662_d())
            {
                f1 = 0.625F;

                if (item.func_77629_n_())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                this.func_82422_c();
                GL11.glScalef(f1, -f1, f1);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f1 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f1, f1, f1);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f2;
            int i;
            float f5;

            if (itemstack.func_77973_b().func_77623_v())
            {
                for (i = 0; i <= 1; ++i)
                {
                    int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
                    f5 = (float)(j >> 16 & 255) / 255.0F;
                    f2 = (float)(j >> 8 & 255) / 255.0F;
                    float f3 = (float)(j & 255) / 255.0F;
                    GL11.glColor4f(f5, f2, f3, 1.0F);
                    this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack, i);
                }
            }
            else
            {
                i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
                float f4 = (float)(i >> 16 & 255) / 255.0F;
                f5 = (float)(i >> 8 & 255) / 255.0F;
                f2 = (float)(i & 255) / 255.0F;
                GL11.glColor4f(f4, f5, f2, 1.0F);
                this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack, 0);
            }

            GL11.glPopMatrix();
        }
    }

    protected void func_82422_c()
    {
        GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
    }

    protected void func_82408_c(EntityLivingBase p_82408_1_, int p_82408_2_, float p_82408_3_)
    {
        this.func_82408_c((EntityLiving)p_82408_1_, p_82408_2_, p_82408_3_);
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.func_77032_a((EntityLiving)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected void func_77029_c(EntityLivingBase p_77029_1_, float p_77029_2_)
    {
        this.func_77029_c((EntityLiving)p_77029_1_, p_77029_2_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityLiving)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}