package net.minecraft.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class EntitySilverfish extends EntityMob
{
    private int field_70843_d;
    private static final String __OBFID = "CL_00001696";

    public EntitySilverfish(World p_i1740_1_)
    {
        super(p_i1740_1_);
        this.func_70105_a(0.3F, 0.7F);
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.6000000238418579D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
    }

    protected boolean func_70041_e_()
    {
        return false;
    }

    protected Entity func_70782_k()
    {
        double d0 = 8.0D;
        return this.world.func_72856_b(this, d0);
    }

    protected String func_70639_aQ()
    {
        return "mob.silverfish.say";
    }

    protected String func_70621_aR()
    {
        return "mob.silverfish.hit";
    }

    protected String func_70673_aS()
    {
        return "mob.silverfish.kill";
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else
        {
            if (this.field_70843_d <= 0 && (p_70097_1_ instanceof EntityDamageSource || p_70097_1_ == DamageSource.field_76376_m))
            {
                this.field_70843_d = 20;
            }

            return super.func_70097_a(p_70097_1_, p_70097_2_);
        }
    }

    protected void func_70785_a(Entity p_70785_1_, float p_70785_2_)
    {
        if (this.field_70724_aR <= 0 && p_70785_2_ < 1.2F && p_70785_1_.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && p_70785_1_.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e)
        {
            this.field_70724_aR = 20;
            this.func_70652_k(p_70785_1_);
        }
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.func_85030_a("mob.silverfish.step", 0.15F, 1.0F);
    }

    protected Item droppingItem()
    {
        return Item.func_150899_d(0);
    }

    public void func_70071_h_()
    {
        this.field_70761_aq = this.field_70177_z;
        super.func_70071_h_();
    }

    protected void func_70626_be()
    {
        super.func_70626_be();

        if (!this.world.field_72995_K)
        {
            int i;
            int j;
            int k;
            int i1;

            if (this.field_70843_d > 0)
            {
                --this.field_70843_d;

                if (this.field_70843_d == 0)
                {
                    i = MathHelper.func_76128_c(this.field_70165_t);
                    j = MathHelper.func_76128_c(this.field_70163_u);
                    k = MathHelper.func_76128_c(this.field_70161_v);
                    boolean flag = false;

                    for (int l = 0; !flag && l <= 5 && l >= -5; l = l <= 0 ? 1 - l : 0 - l)
                    {
                        for (i1 = 0; !flag && i1 <= 10 && i1 >= -10; i1 = i1 <= 0 ? 1 - i1 : 0 - i1)
                        {
                            for (int j1 = 0; !flag && j1 <= 10 && j1 >= -10; j1 = j1 <= 0 ? 1 - j1 : 0 - j1)
                            {
                                if (this.world.func_147439_a(i + i1, j + l, k + j1) == Blocks.MONSTER_EGG)
                                {
                                    if (!this.world.func_82736_K().getBooleanValue("mobGriefing"))
                                    {
                                        int k1 = this.world.func_72805_g(i + i1, j + l, k + j1);
                                        ImmutablePair immutablepair = BlockSilverfish.func_150197_b(k1);
                                        this.world.func_147465_d(i + i1, j + l, k + j1, (Block)immutablepair.getLeft(), ((Integer)immutablepair.getRight()).intValue(), 3);
                                    }
                                    else
                                    {
                                        this.world.func_147480_a(i + i1, j + l, k + j1, false);
                                    }

                                    Blocks.MONSTER_EGG.func_149664_b(this.world, i + i1, j + l, k + j1, 0);

                                    if (this.randomizer.nextBoolean())
                                    {
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (this.field_70789_a == null && !this.func_70781_l())
            {
                i = MathHelper.func_76128_c(this.field_70165_t);
                j = MathHelper.func_76128_c(this.field_70163_u + 0.5D);
                k = MathHelper.func_76128_c(this.field_70161_v);
                int l1 = this.randomizer.nextInt(6);
                Block block = this.world.func_147439_a(i + Facing.field_71586_b[l1], j + Facing.field_71587_c[l1], k + Facing.field_71585_d[l1]);
                i1 = this.world.func_72805_g(i + Facing.field_71586_b[l1], j + Facing.field_71587_c[l1], k + Facing.field_71585_d[l1]);

                if (BlockSilverfish.func_150196_a(block))
                {
                    this.world.func_147465_d(i + Facing.field_71586_b[l1], j + Facing.field_71587_c[l1], k + Facing.field_71585_d[l1], Blocks.MONSTER_EGG, BlockSilverfish.func_150195_a(block, i1), 3);
                    this.func_70656_aK();
                    this.func_70106_y();
                }
                else
                {
                    this.func_70779_j();
                }
            }
            else if (this.field_70789_a != null && !this.func_70781_l())
            {
                this.field_70789_a = null;
            }
        }
    }

    public float func_70783_a(int p_70783_1_, int p_70783_2_, int p_70783_3_)
    {
        return this.world.func_147439_a(p_70783_1_, p_70783_2_ - 1, p_70783_3_) == Blocks.STONE ? 10.0F : super.func_70783_a(p_70783_1_, p_70783_2_, p_70783_3_);
    }

    protected boolean func_70814_o()
    {
        return true;
    }

    public boolean func_70601_bi()
    {
        if (super.func_70601_bi())
        {
            EntityPlayer entityplayer = this.world.func_72890_a(this, 5.0D);
            return entityplayer == null;
        }
        else
        {
            return false;
        }
    }

    public EnumCreatureAttribute func_70668_bt()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
}