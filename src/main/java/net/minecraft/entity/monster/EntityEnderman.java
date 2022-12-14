package net.minecraft.entity.monster;

import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnderman extends EntityMob
{
    private static final UUID field_110192_bp = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier field_110193_bq = (new AttributeModifier(field_110192_bp, "Attacking speed boost", 6.199999809265137D, 0)).func_111168_a(false);
    private static boolean[] field_70827_d = new boolean[256];
    private int field_70828_e;
    private int field_70826_g;
    private Entity field_110194_bu;
    private boolean field_104003_g;
    private static final String __OBFID = "CL_00001685";

    public EntityEnderman(World p_i1734_1_)
    {
        super(p_i1734_1_);
        this.func_70105_a(0.6F, 2.9F);
        this.field_70138_W = 1.0F;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, new Byte((byte)0));
        this.field_70180_af.func_75682_a(17, new Byte((byte)0));
        this.field_70180_af.func_75682_a(18, new Byte((byte)0));
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74777_a("carried", (short)Block.func_149682_b(this.func_146080_bZ()));
        p_70014_1_.func_74777_a("carriedData", (short)this.func_70824_q());
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.func_146081_a(Block.func_149729_e(p_70037_1_.func_74765_d("carried")));
        this.func_70817_b(p_70037_1_.func_74765_d("carriedData"));
    }

    protected Entity func_70782_k()
    {
        EntityPlayer entityplayer = this.world.func_72856_b(this, 64.0D);

        if (entityplayer != null)
        {
            if (this.func_70821_d(entityplayer))
            {
                this.field_104003_g = true;

                if (this.field_70826_g == 0)
                {
                    this.world.func_72908_a(entityplayer.field_70165_t, entityplayer.field_70163_u, entityplayer.field_70161_v, "mob.endermen.stare", 1.0F, 1.0F);
                }

                if (this.field_70826_g++ == 5)
                {
                    this.field_70826_g = 0;
                    this.func_70819_e(true);
                    return entityplayer;
                }
            }
            else
            {
                this.field_70826_g = 0;
            }
        }

        return null;
    }

    private boolean func_70821_d(EntityPlayer p_70821_1_)
    {
        ItemStack itemstack = p_70821_1_.inventory.field_70460_b[3];

        if (itemstack != null && itemstack.getBaseItem() == Item.func_150898_a(Blocks.PUMPKIN))
        {
            return false;
        }
        else
        {
            Vec3 vec3 = p_70821_1_.func_70676_i(1.0F).func_72432_b();
            Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t - p_70821_1_.field_70165_t, this.field_70121_D.field_72338_b + (double)(this.field_70131_O / 2.0F) - (p_70821_1_.field_70163_u + (double)p_70821_1_.func_70047_e()), this.field_70161_v - p_70821_1_.field_70161_v);
            double d0 = vec31.func_72433_c();
            vec31 = vec31.func_72432_b();
            double d1 = vec3.func_72430_b(vec31);
            return d1 > 1.0D - 0.025D / d0 && p_70821_1_.func_70685_l(this);
        }
    }

    public void func_70636_d()
    {
        if (this.func_70026_G())
        {
            this.func_70097_a(DamageSource.field_76369_e, 1.0F);
        }

        if (this.field_110194_bu != this.field_70789_a)
        {
            IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
            iattributeinstance.func_111124_b(field_110193_bq);

            if (this.field_70789_a != null)
            {
                iattributeinstance.func_111121_a(field_110193_bq);
            }
        }

        this.field_110194_bu = this.field_70789_a;
        int k;

        if (!this.world.field_72995_K && this.world.func_82736_K().getBooleanValue("mobGriefing"))
        {
            int i;
            int j;
            Block block;

            if (this.func_146080_bZ().func_149688_o() == Material.field_151579_a)
            {
                if (this.randomizer.nextInt(20) == 0)
                {
                    k = MathHelper.func_76128_c(this.field_70165_t - 2.0D + this.randomizer.nextDouble() * 4.0D);
                    i = MathHelper.func_76128_c(this.field_70163_u + this.randomizer.nextDouble() * 3.0D);
                    j = MathHelper.func_76128_c(this.field_70161_v - 2.0D + this.randomizer.nextDouble() * 4.0D);
                    block = this.world.func_147439_a(k, i, j);

                    if (field_70827_d[Block.func_149682_b(block)])
                    {
                        this.func_146081_a(block);
                        this.func_70817_b(this.world.func_72805_g(k, i, j));
                        this.world.func_147449_b(k, i, j, Blocks.AIR);
                    }
                }
            }
            else if (this.randomizer.nextInt(2000) == 0)
            {
                k = MathHelper.func_76128_c(this.field_70165_t - 1.0D + this.randomizer.nextDouble() * 2.0D);
                i = MathHelper.func_76128_c(this.field_70163_u + this.randomizer.nextDouble() * 2.0D);
                j = MathHelper.func_76128_c(this.field_70161_v - 1.0D + this.randomizer.nextDouble() * 2.0D);
                block = this.world.func_147439_a(k, i, j);
                Block block1 = this.world.func_147439_a(k, i - 1, j);

                if (block.func_149688_o() == Material.field_151579_a && block1.func_149688_o() != Material.field_151579_a && block1.func_149686_d())
                {
                    this.world.func_147465_d(k, i, j, this.func_146080_bZ(), this.func_70824_q(), 3);
                    this.func_146081_a(Blocks.AIR);
                }
            }
        }

        for (k = 0; k < 2; ++k)
        {
            this.world.func_72869_a("portal", this.field_70165_t + (this.randomizer.nextDouble() - 0.5D) * (double)this.field_70130_N, this.field_70163_u + this.randomizer.nextDouble() * (double)this.field_70131_O - 0.25D, this.field_70161_v + (this.randomizer.nextDouble() - 0.5D) * (double)this.field_70130_N, (this.randomizer.nextDouble() - 0.5D) * 2.0D, -this.randomizer.nextDouble(), (this.randomizer.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.world.func_72935_r() && !this.world.field_72995_K)
        {
            float f = this.func_70013_c(1.0F);

            if (f > 0.5F && this.world.func_72937_j(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)) && this.randomizer.nextFloat() * 30.0F < (f - 0.4F) * 2.0F)
            {
                this.field_70789_a = null;
                this.func_70819_e(false);
                this.field_104003_g = false;
                this.func_70820_n();
            }
        }

        if (this.func_70026_G() || this.func_70027_ad())
        {
            this.field_70789_a = null;
            this.func_70819_e(false);
            this.field_104003_g = false;
            this.func_70820_n();
        }

        if (this.func_70823_r() && !this.field_104003_g && this.randomizer.nextInt(100) == 0)
        {
            this.func_70819_e(false);
        }

        this.field_70703_bu = false;

        if (this.field_70789_a != null)
        {
            this.func_70625_a(this.field_70789_a, 100.0F, 100.0F);
        }

        if (!this.world.field_72995_K && this.func_70089_S())
        {
            if (this.field_70789_a != null)
            {
                if (this.field_70789_a instanceof EntityPlayer && this.func_70821_d((EntityPlayer)this.field_70789_a))
                {
                    if (this.field_70789_a.func_70068_e(this) < 16.0D)
                    {
                        this.func_70820_n();
                    }

                    this.field_70828_e = 0;
                }
                else if (this.field_70789_a.func_70068_e(this) > 256.0D && this.field_70828_e++ >= 30 && this.func_70816_c(this.field_70789_a))
                {
                    this.field_70828_e = 0;
                }
            }
            else
            {
                this.func_70819_e(false);
                this.field_70828_e = 0;
            }
        }

        super.func_70636_d();
    }

    protected boolean func_70820_n()
    {
        double d0 = this.field_70165_t + (this.randomizer.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.field_70163_u + (double)(this.randomizer.nextInt(64) - 32);
        double d2 = this.field_70161_v + (this.randomizer.nextDouble() - 0.5D) * 64.0D;
        return this.func_70825_j(d0, d1, d2);
    }

    protected boolean func_70816_c(Entity p_70816_1_)
    {
        Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t - p_70816_1_.field_70165_t, this.field_70121_D.field_72338_b + (double)(this.field_70131_O / 2.0F) - p_70816_1_.field_70163_u + (double)p_70816_1_.func_70047_e(), this.field_70161_v - p_70816_1_.field_70161_v);
        vec3 = vec3.func_72432_b();
        double d0 = 16.0D;
        double d1 = this.field_70165_t + (this.randomizer.nextDouble() - 0.5D) * 8.0D - vec3.field_72450_a * d0;
        double d2 = this.field_70163_u + (double)(this.randomizer.nextInt(16) - 8) - vec3.field_72448_b * d0;
        double d3 = this.field_70161_v + (this.randomizer.nextDouble() - 0.5D) * 8.0D - vec3.field_72449_c * d0;
        return this.func_70825_j(d1, d2, d3);
    }

    protected boolean func_70825_j(double p_70825_1_, double p_70825_3_, double p_70825_5_)
    {
        double d3 = this.field_70165_t;
        double d4 = this.field_70163_u;
        double d5 = this.field_70161_v;
        this.field_70165_t = p_70825_1_;
        this.field_70163_u = p_70825_3_;
        this.field_70161_v = p_70825_5_;
        boolean flag = false;
        int i = MathHelper.func_76128_c(this.field_70165_t);
        int j = MathHelper.func_76128_c(this.field_70163_u);
        int k = MathHelper.func_76128_c(this.field_70161_v);

        if (this.world.func_72899_e(i, j, k))
        {
            boolean flag1 = false;

            while (!flag1 && j > 0)
            {
                Block block = this.world.func_147439_a(i, j - 1, k);

                if (block.func_149688_o().func_76230_c())
                {
                    flag1 = true;
                }
                else
                {
                    --this.field_70163_u;
                    --j;
                }
            }

            if (flag1)
            {
                this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);

                if (this.world.func_72945_a(this, this.field_70121_D).isEmpty() && !this.world.func_72953_d(this.field_70121_D))
                {
                    flag = true;
                }
            }
        }

        if (!flag)
        {
            this.func_70107_b(d3, d4, d5);
            return false;
        }
        else
        {
            short short1 = 128;

            for (int l = 0; l < short1; ++l)
            {
                double d6 = (double)l / ((double)short1 - 1.0D);
                float f = (this.randomizer.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.randomizer.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.randomizer.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.randomizer.nextDouble() - 0.5D) * (double)this.field_70130_N * 2.0D;
                double d8 = d4 + (this.field_70163_u - d4) * d6 + this.randomizer.nextDouble() * (double)this.field_70131_O;
                double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.randomizer.nextDouble() - 0.5D) * (double)this.field_70130_N * 2.0D;
                this.world.func_72869_a("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
            }

            this.world.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    protected String func_70639_aQ()
    {
        return this.func_70823_r() ? "mob.endermen.scream" : "mob.endermen.idle";
    }

    protected String func_70621_aR()
    {
        return "mob.endermen.hit";
    }

    protected String func_70673_aS()
    {
        return "mob.endermen.death";
    }

    protected Item droppingItem()
    {
        return Items.ENDER_PEARL;
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        Item item = this.droppingItem();

        if (item != null)
        {
            int j = this.randomizer.nextInt(2 + p_70628_2_);

            for (int k = 0; k < j; ++k)
            {
                this.func_145779_a(item, 1);
            }
        }
    }

    public void func_146081_a(Block p_146081_1_)
    {
        this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(Block.func_149682_b(p_146081_1_) & 255)));
    }

    public Block func_146080_bZ()
    {
        return Block.func_149729_e(this.field_70180_af.func_75683_a(16));
    }

    public void func_70817_b(int p_70817_1_)
    {
        this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)(p_70817_1_ & 255)));
    }

    public int func_70824_q()
    {
        return this.field_70180_af.func_75683_a(17);
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else
        {
            this.func_70819_e(true);

            if (p_70097_1_ instanceof EntityDamageSource && p_70097_1_.func_76346_g() instanceof EntityPlayer)
            {
                this.field_104003_g = true;
            }

            if (p_70097_1_ instanceof EntityDamageSourceIndirect)
            {
                this.field_104003_g = false;

                for (int i = 0; i < 64; ++i)
                {
                    if (this.func_70820_n())
                    {
                        return true;
                    }
                }

                return false;
            }
            else
            {
                return super.func_70097_a(p_70097_1_, p_70097_2_);
            }
        }
    }

    public boolean func_70823_r()
    {
        return this.field_70180_af.func_75683_a(18) > 0;
    }

    public void func_70819_e(boolean p_70819_1_)
    {
        this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)(p_70819_1_ ? 1 : 0)));
    }

    static
    {
        field_70827_d[Block.func_149682_b(Blocks.GRASS)] = true;
        field_70827_d[Block.func_149682_b(Blocks.DIRT)] = true;
        field_70827_d[Block.func_149682_b(Blocks.SAND)] = true;
        field_70827_d[Block.func_149682_b(Blocks.GRAVEL)] = true;
        field_70827_d[Block.func_149682_b(Blocks.YELLOW_FLOWER)] = true;
        field_70827_d[Block.func_149682_b(Blocks.RED_FLOWER)] = true;
        field_70827_d[Block.func_149682_b(Blocks.BROWN_MUSHROOM)] = true;
        field_70827_d[Block.func_149682_b(Blocks.RED_MUSHROOM)] = true;
        field_70827_d[Block.func_149682_b(Blocks.TNT)] = true;
        field_70827_d[Block.func_149682_b(Blocks.CACTUS)] = true;
        field_70827_d[Block.func_149682_b(Blocks.CLAY)] = true;
        field_70827_d[Block.func_149682_b(Blocks.PUMPKIN)] = true;
        field_70827_d[Block.func_149682_b(Blocks.MELON_BLOCK)] = true;
        field_70827_d[Block.func_149682_b(Blocks.MYCELIUM)] = true;
    }
}