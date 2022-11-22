package net.minecraft.entity.monster;

import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityPigZombie extends EntityZombie
{
    private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.45D, 0)).func_111168_a(false);
    private int field_70837_d;
    private int field_70838_e;
    private Entity field_110191_bu;
    private static final String __OBFID = "CL_00001693";

    public EntityPigZombie(World p_i1739_1_)
    {
        super(p_i1739_1_);
        this.field_70178_ae = true;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(field_110186_bp).func_111128_a(0.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
    }

    protected boolean func_70650_aV()
    {
        return false;
    }

    public void func_70071_h_()
    {
        if (this.field_110191_bu != this.field_70789_a && !this.world.field_72995_K)
        {
            IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
            iattributeinstance.func_111124_b(field_110190_br);

            if (this.field_70789_a != null)
            {
                iattributeinstance.func_111121_a(field_110190_br);
            }
        }

        this.field_110191_bu = this.field_70789_a;

        if (this.field_70838_e > 0 && --this.field_70838_e == 0)
        {
            this.func_85030_a("mob.zombiepig.zpigangry", this.func_70599_aP() * 2.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.func_70071_h_();
    }

    public boolean func_70601_bi()
    {
        return this.world.field_73013_u != EnumDifficulty.PEACEFUL && this.world.func_72855_b(this.field_70121_D) && this.world.func_72945_a(this, this.field_70121_D).isEmpty() && !this.world.func_72953_d(this.field_70121_D);
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74777_a("Anger", (short)this.field_70837_d);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.field_70837_d = p_70037_1_.func_74765_d("Anger");
    }

    protected Entity func_70782_k()
    {
        return this.field_70837_d == 0 ? null : super.func_70782_k();
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else
        {
            Entity entity = p_70097_1_.func_76346_g();

            if (entity instanceof EntityPlayer)
            {
                List list = this.world.func_72839_b(this, this.field_70121_D.func_72314_b(32.0D, 32.0D, 32.0D));

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity1 = (Entity)list.get(i);

                    if (entity1 instanceof EntityPigZombie)
                    {
                        EntityPigZombie entitypigzombie = (EntityPigZombie)entity1;
                        entitypigzombie.func_70835_c(entity);
                    }
                }

                this.func_70835_c(entity);
            }

            return super.func_70097_a(p_70097_1_, p_70097_2_);
        }
    }

    private void func_70835_c(Entity p_70835_1_)
    {
        this.field_70789_a = p_70835_1_;
        this.field_70837_d = 400 + this.field_70146_Z.nextInt(400);
        this.field_70838_e = this.field_70146_Z.nextInt(40);
    }

    protected String func_70639_aQ()
    {
        return "mob.zombiepig.zpig";
    }

    protected String func_70621_aR()
    {
        return "mob.zombiepig.zpighurt";
    }

    protected String func_70673_aS()
    {
        return "mob.zombiepig.zpigdeath";
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.field_70146_Z.nextInt(2 + p_70628_2_);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.func_145779_a(Items.ROTTEN_FLESH, 1);
        }

        j = this.field_70146_Z.nextInt(2 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
            this.func_145779_a(Items.GOLD_NUGGET, 1);
        }
    }

    public boolean func_70085_c(EntityPlayer p_70085_1_)
    {
        return false;
    }

    protected void func_70600_l(int p_70600_1_)
    {
        this.func_145779_a(Items.GOLD_INGOT, 1);
    }

    protected void func_82164_bB()
    {
        this.func_70062_b(0, new ItemStack(Items.GOLDEN_SWORD));
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
    {
        super.func_110161_a(p_110161_1_);
        this.func_82229_g(false);
        return p_110161_1_;
    }
}