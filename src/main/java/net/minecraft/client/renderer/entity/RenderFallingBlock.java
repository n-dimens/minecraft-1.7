package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderFallingBlock extends Render
{
    private final RenderBlocks field_147920_a = new RenderBlocks();
    private static final String __OBFID = "CL_00000994";

    public RenderFallingBlock()
    {
        this.field_76989_e = 0.5F;
    }

    public void func_76986_a(EntityFallingBlock p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        World world = p_76986_1_.func_145807_e();
        Block block = p_76986_1_.func_145805_f();
        int i = MathHelper.func_76128_c(p_76986_1_.field_70165_t);
        int j = MathHelper.func_76128_c(p_76986_1_.field_70163_u);
        int k = MathHelper.func_76128_c(p_76986_1_.field_70161_v);

        if (block != null && block != world.func_147439_a(i, j, k))
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
            this.func_110777_b(p_76986_1_);
            GL11.glDisable(GL11.GL_LIGHTING);
            Tessellator tessellator;

            if (block instanceof BlockAnvil)
            {
                this.field_147920_a.field_147845_a = world;
                tessellator = Tessellator.field_78398_a;
                tessellator.func_78382_b();
                tessellator.func_78373_b((double)((float)(-i) - 0.5F), (double)((float)(-j) - 0.5F), (double)((float)(-k) - 0.5F));
                this.field_147920_a.func_147780_a((BlockAnvil)block, i, j, k, p_76986_1_.field_145814_a);
                tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                tessellator.func_78381_a();
            }
            else if (block instanceof BlockDragonEgg)
            {
                this.field_147920_a.field_147845_a = world;
                tessellator = Tessellator.field_78398_a;
                tessellator.func_78382_b();
                tessellator.func_78373_b((double)((float)(-i) - 0.5F), (double)((float)(-j) - 0.5F), (double)((float)(-k) - 0.5F));
                this.field_147920_a.func_147802_a((BlockDragonEgg)block, i, j, k);
                tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                tessellator.func_78381_a();
            }
            else
            {
                this.field_147920_a.func_147775_a(block);
                this.field_147920_a.func_147749_a(block, world, i, j, k, p_76986_1_.field_145814_a);
            }

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    protected ResourceLocation func_110775_a(EntityFallingBlock p_110775_1_)
    {
        return TextureMap.field_110575_b;
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityFallingBlock)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityFallingBlock)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}