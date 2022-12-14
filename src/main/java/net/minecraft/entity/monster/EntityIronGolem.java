package net.minecraft.entity.monster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookAtVillager;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityIronGolem extends EntityGolem
{
    private int field_70858_e;
    Village field_70857_d;
    private int field_70855_f;
    private int field_70856_g;
    private static final String __OBFID = "CL_00001652";

    public EntityIronGolem(World p_i1694_1_)
    {
        super(p_i1694_1_);
        this.func_70105_a(1.4F, 2.9F);
        this.func_70661_as().func_75491_a(true);
        this.aiTasks.func_75776_a(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.aiTasks.func_75776_a(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.aiTasks.func_75776_a(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.aiTasks.func_75776_a(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.aiTasks.func_75776_a(5, new EntityAILookAtVillager(this));
        this.aiTasks.func_75776_a(6, new EntityAIWander(this, 0.6D));
        this.aiTasks.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.aiTasks.func_75776_a(8, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIDefendVillage(this));
        this.field_70715_bh.func_75776_a(2, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.field_82192_a));
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
    }

    public boolean func_70650_aV()
    {
        return true;
    }

    protected void func_70629_bd()
    {
        if (--this.field_70858_e <= 0)
        {
            this.field_70858_e = 70 + this.randomizer.nextInt(50);
            this.field_70857_d = this.world.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), 32);

            if (this.field_70857_d == null)
            {
                this.func_110177_bN();
            }
            else
            {
                ChunkCoordinates chunkcoordinates = this.field_70857_d.func_75577_a();
                this.func_110171_b(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c, (int)((float)this.field_70857_d.func_75568_b() * 0.6F));
            }
        }

        super.func_70629_bd();
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
    }

    protected int func_70682_h(int p_70682_1_)
    {
        return p_70682_1_;
    }

    protected void func_82167_n(Entity p_82167_1_)
    {
        if (p_82167_1_ instanceof IMob && this.func_70681_au().nextInt(20) == 0)
        {
            this.func_70624_b((EntityLivingBase)p_82167_1_);
        }

        super.func_82167_n(p_82167_1_);
    }

    public void func_70636_d()
    {
        super.func_70636_d();

        if (this.field_70855_f > 0)
        {
            --this.field_70855_f;
        }

        if (this.field_70856_g > 0)
        {
            --this.field_70856_g;
        }

        if (this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7D && this.randomizer.nextInt(5) == 0)
        {
            int i = MathHelper.func_76128_c(this.field_70165_t);
            int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D - (double)this.field_70129_M);
            int k = MathHelper.func_76128_c(this.field_70161_v);
            Block block = this.world.func_147439_a(i, j, k);

            if (block.func_149688_o() != Material.field_151579_a)
            {
                this.world.func_72869_a("blockcrack_" + Block.func_149682_b(block) + "_" + this.world.func_72805_g(i, j, k), this.field_70165_t + ((double)this.randomizer.nextFloat() - 0.5D) * (double)this.field_70130_N, this.field_70121_D.field_72338_b + 0.1D, this.field_70161_v + ((double)this.randomizer.nextFloat() - 0.5D) * (double)this.field_70130_N, 4.0D * ((double)this.randomizer.nextFloat() - 0.5D), 0.5D, ((double)this.randomizer.nextFloat() - 0.5D) * 4.0D);
            }
        }
    }

    public boolean func_70686_a(Class p_70686_1_)
    {
        return this.func_70850_q() && EntityPlayer.class.isAssignableFrom(p_70686_1_) ? false : super.func_70686_a(p_70686_1_);
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74757_a("PlayerCreated", this.func_70850_q());
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.func_70849_f(p_70037_1_.func_74767_n("PlayerCreated"));
    }

    public boolean func_70652_k(Entity p_70652_1_)
    {
        this.field_70855_f = 10;
        this.world.func_72960_a(this, (byte)4);
        boolean flag = p_70652_1_.func_70097_a(DamageSource.func_76358_a(this), (float)(7 + this.randomizer.nextInt(15)));

        if (flag)
        {
            p_70652_1_.field_70181_x += 0.4000000059604645D;
        }

        this.func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
        return flag;
    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte p_70103_1_)
    {
        if (p_70103_1_ == 4)
        {
            this.field_70855_f = 10;
            this.func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
        }
        else if (p_70103_1_ == 11)
        {
            this.field_70856_g = 400;
        }
        else
        {
            super.func_70103_a(p_70103_1_);
        }
    }

    public Village func_70852_n()
    {
        return this.field_70857_d;
    }

    @SideOnly(Side.CLIENT)
    public int func_70854_o()
    {
        return this.field_70855_f;
    }

    public void func_70851_e(boolean p_70851_1_)
    {
        this.field_70856_g = p_70851_1_ ? 400 : 0;
        this.world.func_72960_a(this, (byte)11);
    }

    protected String func_70621_aR()
    {
        return "mob.irongolem.hit";
    }

    protected String func_70673_aS()
    {
        return "mob.irongolem.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.randomizer.nextInt(3);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.func_145778_a(Item.func_150898_a(Blocks.RED_FLOWER), 1, 0.0F);
        }

        k = 3 + this.randomizer.nextInt(3);

        for (int l = 0; l < k; ++l)
        {
            this.func_145779_a(Items.IRON_INGOT, 1);
        }
    }

    public int func_70853_p()
    {
        return this.field_70856_g;
    }

    public boolean func_70850_q()
    {
        return (this.field_70180_af.func_75683_a(16) & 1) != 0;
    }

    public void func_70849_f(boolean p_70849_1_)
    {
        byte b0 = this.field_70180_af.func_75683_a(16);

        if (p_70849_1_)
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    public void func_70645_a(DamageSource p_70645_1_)
    {
        if (!this.func_70850_q() && this.field_70717_bb != null && this.field_70857_d != null)
        {
            this.field_70857_d.func_82688_a(this.field_70717_bb.func_70005_c_(), -5);
        }

        super.func_70645_a(p_70645_1_);
    }
}