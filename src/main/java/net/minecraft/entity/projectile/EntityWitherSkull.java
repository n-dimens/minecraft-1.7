package net.minecraft.entity.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityWitherSkull extends EntityFireball
{
    private static final String __OBFID = "CL_00001728";

    public EntityWitherSkull(World p_i1793_1_)
    {
        super(p_i1793_1_);
        this.func_70105_a(0.3125F, 0.3125F);
    }

    public EntityWitherSkull(World p_i1794_1_, EntityLivingBase p_i1794_2_, double p_i1794_3_, double p_i1794_5_, double p_i1794_7_)
    {
        super(p_i1794_1_, p_i1794_2_, p_i1794_3_, p_i1794_5_, p_i1794_7_);
        this.func_70105_a(0.3125F, 0.3125F);
    }

    protected float func_82341_c()
    {
        return this.func_82342_d() ? 0.73F : super.func_82341_c();
    }

    @SideOnly(Side.CLIENT)
    public EntityWitherSkull(World p_i1795_1_, double p_i1795_2_, double p_i1795_4_, double p_i1795_6_, double p_i1795_8_, double p_i1795_10_, double p_i1795_12_)
    {
        super(p_i1795_1_, p_i1795_2_, p_i1795_4_, p_i1795_6_, p_i1795_8_, p_i1795_10_, p_i1795_12_);
        this.func_70105_a(0.3125F, 0.3125F);
    }

    public boolean func_70027_ad()
    {
        return false;
    }

    public float func_145772_a(Explosion p_145772_1_, World p_145772_2_, int p_145772_3_, int p_145772_4_, int p_145772_5_, Block p_145772_6_)
    {
        float f = super.func_145772_a(p_145772_1_, p_145772_2_, p_145772_3_, p_145772_4_, p_145772_5_, p_145772_6_);

        if (this.func_82342_d() && p_145772_6_ != Blocks.BEDROCK && p_145772_6_ != Blocks.END_PORTAL && p_145772_6_ != Blocks.END_PORTAL_FRAME && p_145772_6_ != Blocks.COMMAND_BLOCK)
        {
            f = Math.min(0.8F, f);
        }

        return f;
    }

    protected void func_70227_a(MovingObjectPosition p_70227_1_)
    {
        if (!this.world.field_72995_K)
        {
            if (p_70227_1_.field_72308_g != null)
            {
                if (this.field_70235_a != null)
                {
                    if (p_70227_1_.field_72308_g.func_70097_a(DamageSource.func_76358_a(this.field_70235_a), 8.0F) && !p_70227_1_.field_72308_g.func_70089_S())
                    {
                        this.field_70235_a.func_70691_i(5.0F);
                    }
                }
                else
                {
                    p_70227_1_.field_72308_g.func_70097_a(DamageSource.field_76376_m, 5.0F);
                }

                if (p_70227_1_.field_72308_g instanceof EntityLivingBase)
                {
                    byte b0 = 0;

                    if (this.world.field_73013_u == EnumDifficulty.NORMAL)
                    {
                        b0 = 10;
                    }
                    else if (this.world.field_73013_u == EnumDifficulty.HARD)
                    {
                        b0 = 40;
                    }

                    if (b0 > 0)
                    {
                        ((EntityLivingBase)p_70227_1_.field_72308_g).func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 20 * b0, 1));
                    }
                }
            }

            this.world.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, false, this.world.func_82736_K().getBooleanValue("mobGriefing"));
            this.func_70106_y();
        }
    }

    public boolean func_70067_L()
    {
        return false;
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        return false;
    }

    protected void func_70088_a()
    {
        this.field_70180_af.func_75682_a(10, Byte.valueOf((byte)0));
    }

    public boolean func_82342_d()
    {
        return this.field_70180_af.func_75683_a(10) == 1;
    }

    public void func_82343_e(boolean p_82343_1_)
    {
        this.field_70180_af.func_75692_b(10, Byte.valueOf((byte)(p_82343_1_ ? 1 : 0)));
    }
}