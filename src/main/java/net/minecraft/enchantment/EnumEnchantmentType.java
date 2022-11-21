package net.minecraft.enchantment;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public enum EnumEnchantmentType
{
    all,
    armor,
    armor_feet,
    armor_legs,
    armor_torso,
    armor_head,
    weapon,
    digger,
    fishing_rod,
    breakable,
    bow;

    private static final String __OBFID = "CL_00000106";

    public boolean func_77557_a(Item p_77557_1_)
    {
        if (this == all)
        {
            return true;
        }
        else if (this == breakable && p_77557_1_.func_77645_m())
        {
            return true;
        }
        else if (p_77557_1_ instanceof ItemArmor)
        {
            if (this == armor)
            {
                return true;
            }
            else
            {
                ItemArmor itemarmor = (ItemArmor)p_77557_1_;
                return itemarmor.field_77881_a == 0 ? this == armor_head : (itemarmor.field_77881_a == 2 ? this == armor_legs : (itemarmor.field_77881_a == 1 ? this == armor_torso : (itemarmor.field_77881_a == 3 ? this == armor_feet : false)));
            }
        }
        else
        {
            return p_77557_1_ instanceof ItemSword ? this == weapon : (p_77557_1_ instanceof ItemTool ? this == digger : (p_77557_1_ instanceof ItemBow ? this == bow : (p_77557_1_ instanceof ItemFishingRod ? this == fishing_rod : false)));
        }
    }
}