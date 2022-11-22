package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityCloudFX extends EntityFX
{
    float field_70569_a;
    private static final String __OBFID = "CL_00000920";

    public EntityCloudFX(World p_i1221_1_, double p_i1221_2_, double p_i1221_4_, double p_i1221_6_, double p_i1221_8_, double p_i1221_10_, double p_i1221_12_)
    {
        super(p_i1221_1_, p_i1221_2_, p_i1221_4_, p_i1221_6_, 0.0D, 0.0D, 0.0D);
        float f = 2.5F;
        this.field_70159_w *= 0.10000000149011612D;
        this.field_70181_x *= 0.10000000149011612D;
        this.field_70179_y *= 0.10000000149011612D;
        this.field_70159_w += p_i1221_8_;
        this.field_70181_x += p_i1221_10_;
        this.field_70179_y += p_i1221_12_;
        this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F - (float)(Math.random() * 0.30000001192092896D);
        this.field_70544_f *= 0.75F;
        this.field_70544_f *= f;
        this.field_70569_a = this.field_70544_f;
        this.field_70547_e = (int)(8.0D / (Math.random() * 0.8D + 0.3D));
        this.field_70547_e = (int)((float)this.field_70547_e * f);
        this.field_70145_X = false;
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
    {
        float f6 = ((float)this.field_70546_d + p_70539_2_) / (float)this.field_70547_e * 32.0F;

        if (f6 < 0.0F)
        {
            f6 = 0.0F;
        }

        if (f6 > 1.0F)
        {
            f6 = 1.0F;
        }

        this.field_70544_f = this.field_70569_a * f6;
        super.func_70539_a(p_70539_1_, p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
    }

    public void func_70071_h_()
    {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;

        if (this.field_70546_d++ >= this.field_70547_e)
        {
            this.func_70106_y();
        }

        this.func_70536_a(7 - this.field_70546_d * 8 / this.field_70547_e);
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.9599999785423279D;
        this.field_70181_x *= 0.9599999785423279D;
        this.field_70179_y *= 0.9599999785423279D;
        EntityPlayer entityplayer = this.world.func_72890_a(this, 2.0D);

        if (entityplayer != null && this.field_70163_u > entityplayer.field_70121_D.field_72338_b)
        {
            this.field_70163_u += (entityplayer.field_70121_D.field_72338_b - this.field_70163_u) * 0.2D;
            this.field_70181_x += (entityplayer.field_70181_x - this.field_70181_x) * 0.2D;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }

        if (this.field_70122_E)
        {
            this.field_70159_w *= 0.699999988079071D;
            this.field_70179_y *= 0.699999988079071D;
        }
    }
}