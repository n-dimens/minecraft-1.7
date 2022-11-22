package net.minecraft.inventory;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;

public class SlotFurnace extends Slot
{
    private EntityPlayer field_75229_a;
    private int field_75228_b;
    private static final String __OBFID = "CL_00001749";

    public SlotFurnace(EntityPlayer p_i1813_1_, IInventory p_i1813_2_, int p_i1813_3_, int p_i1813_4_, int p_i1813_5_)
    {
        super(p_i1813_2_, p_i1813_3_, p_i1813_4_, p_i1813_5_);
        this.field_75229_a = p_i1813_1_;
    }

    public boolean func_75214_a(ItemStack p_75214_1_)
    {
        return false;
    }

    public ItemStack func_75209_a(int p_75209_1_)
    {
        if (this.func_75216_d())
        {
            this.field_75228_b += Math.min(p_75209_1_, this.func_75211_c().count);
        }

        return super.func_75209_a(p_75209_1_);
    }

    public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
    {
        this.func_75208_c(p_82870_2_);
        super.func_82870_a(p_82870_1_, p_82870_2_);
    }

    protected void func_75210_a(ItemStack p_75210_1_, int p_75210_2_)
    {
        this.field_75228_b += p_75210_2_;
        this.func_75208_c(p_75210_1_);
    }

    protected void func_75208_c(ItemStack p_75208_1_)
    {
        p_75208_1_.func_77980_a(this.field_75229_a.world, this.field_75229_a, this.field_75228_b);

        if (!this.field_75229_a.world.field_72995_K)
        {
            int i = this.field_75228_b;
            float f = FurnaceRecipes.func_77602_a().func_151398_b(p_75208_1_);
            int j;

            if (f == 0.0F)
            {
                i = 0;
            }
            else if (f < 1.0F)
            {
                j = MathHelper.func_76141_d((float)i * f);

                if (j < MathHelper.func_76123_f((float)i * f) && (float)Math.random() < (float)i * f - (float)j)
                {
                    ++j;
                }

                i = j;
            }

            while (i > 0)
            {
                j = EntityXPOrb.func_70527_a(i);
                i -= j;
                this.field_75229_a.world.func_72838_d(new EntityXPOrb(this.field_75229_a.world, this.field_75229_a.field_70165_t, this.field_75229_a.field_70163_u + 0.5D, this.field_75229_a.field_70161_v + 0.5D, j));
            }
        }

        this.field_75228_b = 0;

        if (p_75208_1_.getBaseItem() == Items.IRON_INGOT)
        {
            this.field_75229_a.func_71064_a(AchievementList.ACQUIRE_IRON, 1);
        }

        if (p_75208_1_.getBaseItem() == Items.COOKED_FISHED)
        {
            this.field_75229_a.func_71064_a(AchievementList.COOK_FISH, 1);
        }
    }
}