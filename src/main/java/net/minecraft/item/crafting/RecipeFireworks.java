package net.minecraft.item.crafting;

import java.util.ArrayList;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class RecipeFireworks implements IRecipe
{
    private ItemStack field_92102_a;
    private static final String __OBFID = "CL_00000083";

    public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_)
    {
        this.field_92102_a = null;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = 0; k1 < p_77569_1_.func_70302_i_(); ++k1)
        {
            ItemStack itemstack = p_77569_1_.func_70301_a(k1);

            if (itemstack != null)
            {
                if (itemstack.getBaseItem() == Items.GUNPOWDER)
                {
                    ++j;
                }
                else if (itemstack.getBaseItem() == Items.FIREWORK_CHARGE)
                {
                    ++l;
                }
                else if (itemstack.getBaseItem() == Items.DYE)
                {
                    ++k;
                }
                else if (itemstack.getBaseItem() == Items.PAPER)
                {
                    ++i;
                }
                else if (itemstack.getBaseItem() == Items.GLOWSTONE_DUST)
                {
                    ++i1;
                }
                else if (itemstack.getBaseItem() == Items.DIAMOND)
                {
                    ++i1;
                }
                else if (itemstack.getBaseItem() == Items.FIRE_CHARGE)
                {
                    ++j1;
                }
                else if (itemstack.getBaseItem() == Items.FEATHER)
                {
                    ++j1;
                }
                else if (itemstack.getBaseItem() == Items.GOLD_NUGGET)
                {
                    ++j1;
                }
                else
                {
                    if (itemstack.getBaseItem() != Items.SKULL)
                    {
                        return false;
                    }

                    ++j1;
                }
            }
        }

        i1 += k + j1;

        if (j <= 3 && i <= 1)
        {
            NBTTagCompound nbttagcompound;
            NBTTagCompound nbttagcompound1;

            if (j >= 1 && i == 1 && i1 == 0)
            {
                this.field_92102_a = new ItemStack(Items.FIREWORKS);

                if (l > 0)
                {
                    nbttagcompound = new NBTTagCompound();
                    nbttagcompound1 = new NBTTagCompound();
                    NBTTagList nbttaglist = new NBTTagList();

                    for (int k2 = 0; k2 < p_77569_1_.func_70302_i_(); ++k2)
                    {
                        ItemStack itemstack3 = p_77569_1_.func_70301_a(k2);

                        if (itemstack3 != null && itemstack3.getBaseItem() == Items.FIREWORK_CHARGE && itemstack3.func_77942_o() && itemstack3.func_77978_p().func_150297_b("Explosion", 10))
                        {
                            nbttaglist.func_74742_a(itemstack3.func_77978_p().func_74775_l("Explosion"));
                        }
                    }

                    nbttagcompound1.func_74782_a("Explosions", nbttaglist);
                    nbttagcompound1.func_74774_a("Flight", (byte)j);
                    nbttagcompound.func_74782_a("Fireworks", nbttagcompound1);
                    this.field_92102_a.func_77982_d(nbttagcompound);
                }

                return true;
            }
            else if (j == 1 && i == 0 && l == 0 && k > 0 && j1 <= 1)
            {
                this.field_92102_a = new ItemStack(Items.FIREWORK_CHARGE);
                nbttagcompound = new NBTTagCompound();
                nbttagcompound1 = new NBTTagCompound();
                byte b0 = 0;
                ArrayList arraylist = new ArrayList();

                for (int l1 = 0; l1 < p_77569_1_.func_70302_i_(); ++l1)
                {
                    ItemStack itemstack2 = p_77569_1_.func_70301_a(l1);

                    if (itemstack2 != null)
                    {
                        if (itemstack2.getBaseItem() == Items.DYE)
                        {
                            arraylist.add(Integer.valueOf(ItemDye.field_150922_c[itemstack2.func_77960_j()]));
                        }
                        else if (itemstack2.getBaseItem() == Items.GLOWSTONE_DUST)
                        {
                            nbttagcompound1.func_74757_a("Flicker", true);
                        }
                        else if (itemstack2.getBaseItem() == Items.DIAMOND)
                        {
                            nbttagcompound1.func_74757_a("Trail", true);
                        }
                        else if (itemstack2.getBaseItem() == Items.FIRE_CHARGE)
                        {
                            b0 = 1;
                        }
                        else if (itemstack2.getBaseItem() == Items.FEATHER)
                        {
                            b0 = 4;
                        }
                        else if (itemstack2.getBaseItem() == Items.GOLD_NUGGET)
                        {
                            b0 = 2;
                        }
                        else if (itemstack2.getBaseItem() == Items.SKULL)
                        {
                            b0 = 3;
                        }
                    }
                }

                int[] aint1 = new int[arraylist.size()];

                for (int l2 = 0; l2 < aint1.length; ++l2)
                {
                    aint1[l2] = ((Integer)arraylist.get(l2)).intValue();
                }

                nbttagcompound1.func_74783_a("Colors", aint1);
                nbttagcompound1.func_74774_a("Type", b0);
                nbttagcompound.func_74782_a("Explosion", nbttagcompound1);
                this.field_92102_a.func_77982_d(nbttagcompound);
                return true;
            }
            else if (j == 0 && i == 0 && l == 1 && k > 0 && k == i1)
            {
                ArrayList arraylist1 = new ArrayList();

                for (int i2 = 0; i2 < p_77569_1_.func_70302_i_(); ++i2)
                {
                    ItemStack itemstack1 = p_77569_1_.func_70301_a(i2);

                    if (itemstack1 != null)
                    {
                        if (itemstack1.getBaseItem() == Items.DYE)
                        {
                            arraylist1.add(Integer.valueOf(ItemDye.field_150922_c[itemstack1.func_77960_j()]));
                        }
                        else if (itemstack1.getBaseItem() == Items.FIREWORK_CHARGE)
                        {
                            this.field_92102_a = itemstack1.func_77946_l();
                            this.field_92102_a.count = 1;
                        }
                    }
                }

                int[] aint = new int[arraylist1.size()];

                for (int j2 = 0; j2 < aint.length; ++j2)
                {
                    aint[j2] = ((Integer)arraylist1.get(j2)).intValue();
                }

                if (this.field_92102_a != null && this.field_92102_a.func_77942_o())
                {
                    NBTTagCompound nbttagcompound2 = this.field_92102_a.func_77978_p().func_74775_l("Explosion");

                    if (nbttagcompound2 == null)
                    {
                        return false;
                    }
                    else
                    {
                        nbttagcompound2.func_74783_a("FadeColors", aint);
                        return true;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public ItemStack func_77572_b(InventoryCrafting p_77572_1_)
    {
        return this.field_92102_a.func_77946_l();
    }

    public int func_77570_a()
    {
        return 10;
    }

    public ItemStack getOutputItem()
    {
        return this.field_92102_a;
    }
}