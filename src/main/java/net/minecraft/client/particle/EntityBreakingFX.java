package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityBreakingFX extends EntityFX
{
    private static final String __OBFID = "CL_00000897";

    public EntityBreakingFX(World p_i1195_1_, double p_i1195_2_, double p_i1195_4_, double p_i1195_6_, Item p_i1195_8_)
    {
        this(p_i1195_1_, p_i1195_2_, p_i1195_4_, p_i1195_6_, p_i1195_8_, 0);
    }

    public EntityBreakingFX(World p_i1196_1_, double p_i1196_2_, double p_i1196_4_, double p_i1196_6_, Item p_i1196_8_, int p_i1196_9_)
    {
        super(p_i1196_1_, p_i1196_2_, p_i1196_4_, p_i1196_6_, 0.0D, 0.0D, 0.0D);
        this.func_110125_a(p_i1196_8_.func_77617_a(p_i1196_9_));
        this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
        this.field_70545_g = Blocks.field_150433_aE.field_149763_I;
        this.field_70544_f /= 2.0F;
    }

    public EntityBreakingFX(World p_i1197_1_, double p_i1197_2_, double p_i1197_4_, double p_i1197_6_, double p_i1197_8_, double p_i1197_10_, double p_i1197_12_, Item p_i1197_14_, int p_i1197_15_)
    {
        this(p_i1197_1_, p_i1197_2_, p_i1197_4_, p_i1197_6_, p_i1197_14_, p_i1197_15_);
        this.field_70159_w *= 0.10000000149011612D;
        this.field_70181_x *= 0.10000000149011612D;
        this.field_70179_y *= 0.10000000149011612D;
        this.field_70159_w += p_i1197_8_;
        this.field_70181_x += p_i1197_10_;
        this.field_70179_y += p_i1197_12_;
    }

    public int func_70537_b()
    {
        return 2;
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