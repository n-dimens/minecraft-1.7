package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSnowMan extends RenderLiving
{
    private static final ResourceLocation field_110895_a = new ResourceLocation("textures/entity/snowman.png");
    private ModelSnowMan field_77094_a;
    private static final String __OBFID = "CL_00001025";

    public RenderSnowMan()
    {
        super(new ModelSnowMan(), 0.5F);
        this.field_77094_a = (ModelSnowMan)super.field_77045_g;
        this.func_77042_a(this.field_77094_a);
    }

    protected void func_77029_c(EntitySnowman p_77029_1_, float p_77029_2_)
    {
        super.func_77029_c(p_77029_1_, p_77029_2_);
        ItemStack itemstack = new ItemStack(Blocks.PUMPKIN, 1);

        if (itemstack.func_77973_b() instanceof ItemBlock)
        {
            GL11.glPushMatrix();
            this.field_77094_a.field_78195_c.func_78794_c(0.0625F);

            if (RenderBlocks.func_147739_a(Block.func_149634_a(itemstack.func_77973_b()).func_149645_b()))
            {
                float f1 = 0.625F;
                GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f1, -f1, f1);
            }

            this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack, 0);
            GL11.glPopMatrix();
        }
    }

    protected ResourceLocation func_110775_a(EntitySnowman p_110775_1_)
    {
        return field_110895_a;
    }

    protected void func_77029_c(EntityLivingBase p_77029_1_, float p_77029_2_)
    {
        this.func_77029_c((EntitySnowman)p_77029_1_, p_77029_2_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntitySnowman)p_110775_1_);
    }
}