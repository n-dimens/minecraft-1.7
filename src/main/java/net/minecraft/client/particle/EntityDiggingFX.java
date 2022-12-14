package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityDiggingFX extends EntityFX
{
    private Block field_145784_a;
    private static final String __OBFID = "CL_00000932";

    public EntityDiggingFX(World p_i1234_1_, double p_i1234_2_, double p_i1234_4_, double p_i1234_6_, double p_i1234_8_, double p_i1234_10_, double p_i1234_12_, Block p_i1234_14_, int p_i1234_15_)
    {
        super(p_i1234_1_, p_i1234_2_, p_i1234_4_, p_i1234_6_, p_i1234_8_, p_i1234_10_, p_i1234_12_);
        this.field_145784_a = p_i1234_14_;
        this.func_110125_a(p_i1234_14_.func_149691_a(0, p_i1234_15_));
        this.field_70545_g = p_i1234_14_.field_149763_I;
        this.field_70552_h = this.field_70553_i = this.field_70551_j = 0.6F;
        this.field_70544_f /= 2.0F;
    }

    public EntityDiggingFX func_70596_a(int p_70596_1_, int p_70596_2_, int p_70596_3_)
    {
        if (this.field_145784_a == Blocks.GRASS)
        {
            return this;
        }
        else
        {
            int l = this.field_145784_a.func_149720_d(this.world, p_70596_1_, p_70596_2_, p_70596_3_);
            this.field_70552_h *= (float)(l >> 16 & 255) / 255.0F;
            this.field_70553_i *= (float)(l >> 8 & 255) / 255.0F;
            this.field_70551_j *= (float)(l & 255) / 255.0F;
            return this;
        }
    }

    public EntityDiggingFX func_90019_g(int p_90019_1_)
    {
        if (this.field_145784_a == Blocks.GRASS)
        {
            return this;
        }
        else
        {
            int j = this.field_145784_a.func_149741_i(p_90019_1_);
            this.field_70552_h *= (float)(j >> 16 & 255) / 255.0F;
            this.field_70553_i *= (float)(j >> 8 & 255) / 255.0F;
            this.field_70551_j *= (float)(j & 255) / 255.0F;
            return this;
        }
    }

    public int func_70537_b()
    {
        return 1;
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
    {
        float f6 = ((float)this.field_94054_b + this.field_70548_b / 4.0F) / 16.0F;
        float f7 = f6 + 0.015609375F;
        float f8 = ((float)this.field_94055_c + this.field_70549_c / 4.0F) / 16.0F;
        float f9 = f8 + 0.015609375F;
        float f10 = 0.1F * this.field_70544_f;

        if (this.field_70550_a != null)
        {
            f6 = this.field_70550_a.func_94214_a((double)(this.field_70548_b / 4.0F * 16.0F));
            f7 = this.field_70550_a.func_94214_a((double)((this.field_70548_b + 1.0F) / 4.0F * 16.0F));
            f8 = this.field_70550_a.func_94207_b((double)(this.field_70549_c / 4.0F * 16.0F));
            f9 = this.field_70550_a.func_94207_b((double)((this.field_70549_c + 1.0F) / 4.0F * 16.0F));
        }

        float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_70539_2_ - field_70556_an);
        float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_70539_2_ - field_70554_ao);
        float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_70539_2_ - field_70555_ap);
        p_70539_1_.func_78386_a(this.field_70552_h, this.field_70553_i, this.field_70551_j);
        p_70539_1_.func_78374_a((double)(f11 - p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - p_70539_4_ * f10), (double)(f13 - p_70539_5_ * f10 - p_70539_7_ * f10), (double)f6, (double)f9);
        p_70539_1_.func_78374_a((double)(f11 - p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + p_70539_4_ * f10), (double)(f13 - p_70539_5_ * f10 + p_70539_7_ * f10), (double)f6, (double)f8);
        p_70539_1_.func_78374_a((double)(f11 + p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + p_70539_4_ * f10), (double)(f13 + p_70539_5_ * f10 + p_70539_7_ * f10), (double)f7, (double)f8);
        p_70539_1_.func_78374_a((double)(f11 + p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - p_70539_4_ * f10), (double)(f13 + p_70539_5_ * f10 - p_70539_7_ * f10), (double)f7, (double)f9);
    }
}