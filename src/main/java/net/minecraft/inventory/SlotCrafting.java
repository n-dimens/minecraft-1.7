package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.AchievementList;

public class SlotCrafting extends Slot
{
    private final IInventory field_75239_a;
    private EntityPlayer field_75238_b;
    private int field_75237_g;
    private static final String __OBFID = "CL_00001761";

    public SlotCrafting(EntityPlayer p_i1823_1_, IInventory p_i1823_2_, IInventory p_i1823_3_, int p_i1823_4_, int p_i1823_5_, int p_i1823_6_)
    {
        super(p_i1823_3_, p_i1823_4_, p_i1823_5_, p_i1823_6_);
        this.field_75238_b = p_i1823_1_;
        this.field_75239_a = p_i1823_2_;
    }

    public boolean func_75214_a(ItemStack p_75214_1_)
    {
        return false;
    }

    public ItemStack func_75209_a(int p_75209_1_)
    {
        if (this.func_75216_d())
        {
            this.field_75237_g += Math.min(p_75209_1_, this.func_75211_c().field_77994_a);
        }

        return super.func_75209_a(p_75209_1_);
    }

    protected void func_75210_a(ItemStack p_75210_1_, int p_75210_2_)
    {
        this.field_75237_g += p_75210_2_;
        this.func_75208_c(p_75210_1_);
    }

    protected void func_75208_c(ItemStack p_75208_1_)
    {
        p_75208_1_.func_77980_a(this.field_75238_b.world, this.field_75238_b, this.field_75237_g);
        this.field_75237_g = 0;

        if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.CRAFTING_TABLE))
        {
            this.field_75238_b.func_71064_a(AchievementList.BUILD_WORK_BENCH, 1);
        }

        if (p_75208_1_.func_77973_b() instanceof ItemPickaxe)
        {
            this.field_75238_b.func_71064_a(AchievementList.BUILD_PICKAXE, 1);
        }

        if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.FURNACE))
        {
            this.field_75238_b.func_71064_a(AchievementList.BUILD_FURNACE, 1);
        }

        if (p_75208_1_.func_77973_b() instanceof ItemHoe)
        {
            this.field_75238_b.func_71064_a(AchievementList.BUILD_HOE, 1);
        }

        if (p_75208_1_.func_77973_b() == Items.BREAD)
        {
            this.field_75238_b.func_71064_a(AchievementList.MAKE_BREAD, 1);
        }

        if (p_75208_1_.func_77973_b() == Items.CAKE)
        {
            this.field_75238_b.func_71064_a(AchievementList.BAKE_CAKE, 1);
        }

        if (p_75208_1_.func_77973_b() instanceof ItemPickaxe && ((ItemPickaxe)p_75208_1_.func_77973_b()).func_150913_i() != Item.ToolMaterial.WOOD)
        {
            this.field_75238_b.func_71064_a(AchievementList.BUILD_BETTER_PICKAXE, 1);
        }

        if (p_75208_1_.func_77973_b() instanceof ItemSword)
        {
            this.field_75238_b.func_71064_a(AchievementList.BUILD_SWORD, 1);
        }

        if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.ENCHANTING_TABLE))
        {
            this.field_75238_b.func_71064_a(AchievementList.ENCHANTMENTS, 1);
        }

        if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.BOOKSHELF))
        {
            this.field_75238_b.func_71064_a(AchievementList.BOOKCASE, 1);
        }
    }

    public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
    {
        this.func_75208_c(p_82870_2_);

        for (int i = 0; i < this.field_75239_a.func_70302_i_(); ++i)
        {
            ItemStack itemstack1 = this.field_75239_a.func_70301_a(i);

            if (itemstack1 != null)
            {
                this.field_75239_a.func_70298_a(i, 1);

                if (itemstack1.func_77973_b().func_77634_r())
                {
                    ItemStack itemstack2 = new ItemStack(itemstack1.func_77973_b().func_77668_q());

                    if (!itemstack1.func_77973_b().func_77630_h(itemstack1) || !this.field_75238_b.field_71071_by.func_70441_a(itemstack2))
                    {
                        if (this.field_75239_a.func_70301_a(i) == null)
                        {
                            this.field_75239_a.func_70299_a(i, itemstack2);
                        }
                        else
                        {
                            this.field_75238_b.func_71019_a(itemstack2, false);
                        }
                    }
                }
            }
        }
    }
}