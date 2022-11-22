package net.minecraft.entity.monster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMagmaCube extends EntitySlime
{
    private static final String __OBFID = "CL_00001691";

    public EntityMagmaCube(World p_i1737_1_)
    {
        super(p_i1737_1_);
        this.field_70178_ae = true;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000000298023224D);
    }

    public boolean func_70601_bi()
    {
        return this.world.field_73013_u != EnumDifficulty.PEACEFUL && this.world.func_72855_b(this.field_70121_D) && this.world.func_72945_a(this, this.field_70121_D).isEmpty() && !this.world.func_72953_d(this.field_70121_D);
    }

    public int func_70658_aO()
    {
        return this.func_70809_q() * 3;
    }

    @SideOnly(Side.CLIENT)
    public int func_70070_b(float p_70070_1_)
    {
        return 15728880;
    }

    public float func_70013_c(float p_70013_1_)
    {
        return 1.0F;
    }

    protected String func_70801_i()
    {
        return "flame";
    }

    protected EntitySlime func_70802_j()
    {
        return new EntityMagmaCube(this.world);
    }

    protected Item droppingItem()
    {
        return Items.MAGMA_CREAM;
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        Item item = this.droppingItem();

        if (item != null && this.func_70809_q() > 1)
        {
            int j = this.field_70146_Z.nextInt(4) - 2;

            if (p_70628_2_ > 0)
            {
                j += this.field_70146_Z.nextInt(p_70628_2_ + 1);
            }

            for (int k = 0; k < j; ++k)
            {
                this.func_145779_a(item, 1);
            }
        }
    }

    public boolean func_70027_ad()
    {
        return false;
    }

    protected int func_70806_k()
    {
        return super.func_70806_k() * 4;
    }

    protected void func_70808_l()
    {
        this.field_70813_a *= 0.9F;
    }

    protected void func_70664_aZ()
    {
        this.field_70181_x = (double)(0.42F + (float)this.func_70809_q() * 0.1F);
        this.field_70160_al = true;
    }

    protected void func_70069_a(float p_70069_1_) {}

    protected boolean func_70800_m()
    {
        return true;
    }

    protected int func_70805_n()
    {
        return super.func_70805_n() + 2;
    }

    protected String func_70803_o()
    {
        return this.func_70809_q() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
    }

    public boolean func_70058_J()
    {
        return false;
    }

    protected boolean func_70804_p()
    {
        return true;
    }
}