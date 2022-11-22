package net.minecraft.entity.monster;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySnowman extends EntityGolem implements IRangedAttackMob
{
    private static final String __OBFID = "CL_00001650";

    public EntitySnowman(World p_i1692_1_)
    {
        super(p_i1692_1_);
        this.func_70105_a(0.4F, 1.8F);
        this.func_70661_as().func_75491_a(true);
        this.aiTasks.func_75776_a(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
        this.aiTasks.func_75776_a(2, new EntityAIWander(this, 1.0D));
        this.aiTasks.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.aiTasks.func_75776_a(4, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.field_82192_a));
    }

    public boolean func_70650_aV()
    {
        return true;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000000298023224D);
    }

    public void func_70636_d()
    {
        super.func_70636_d();
        int i = MathHelper.func_76128_c(this.field_70165_t);
        int j = MathHelper.func_76128_c(this.field_70163_u);
        int k = MathHelper.func_76128_c(this.field_70161_v);

        if (this.func_70026_G())
        {
            this.func_70097_a(DamageSource.field_76369_e, 1.0F);
        }

        if (this.world.func_72807_a(i, k).func_150564_a(i, j, k) > 1.0F)
        {
            this.func_70097_a(DamageSource.field_76370_b, 1.0F);
        }

        for (int l = 0; l < 4; ++l)
        {
            i = MathHelper.func_76128_c(this.field_70165_t + (double)((float)(l % 2 * 2 - 1) * 0.25F));
            j = MathHelper.func_76128_c(this.field_70163_u);
            k = MathHelper.func_76128_c(this.field_70161_v + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));

            if (this.world.func_147439_a(i, j, k).func_149688_o() == Material.field_151579_a && this.world.func_72807_a(i, k).func_150564_a(i, j, k) < 0.8F && Blocks.SNOW_LAYER.func_149742_c(this.world, i, j, k))
            {
                this.world.func_147449_b(i, j, k, Blocks.SNOW_LAYER);
            }
        }
    }

    protected Item droppingItem()
    {
        return Items.SNOWBALL;
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.randomizer.nextInt(16);

        for (int k = 0; k < j; ++k)
        {
            this.func_145779_a(Items.SNOWBALL, 1);
        }
    }

    public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
        EntitySnowball entitysnowball = new EntitySnowball(this.world, this);
        double d0 = p_82196_1_.field_70165_t - this.field_70165_t;
        double d1 = p_82196_1_.field_70163_u + (double)p_82196_1_.func_70047_e() - 1.100000023841858D - entitysnowball.field_70163_u;
        double d2 = p_82196_1_.field_70161_v - this.field_70161_v;
        float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2) * 0.2F;
        entitysnowball.func_70186_c(d0, d1 + (double)f1, d2, 1.6F, 12.0F);
        this.func_85030_a("random.bow", 1.0F, 1.0F / (this.func_70681_au().nextFloat() * 0.4F + 0.8F));
        this.world.func_72838_d(entitysnowball);
    }
}