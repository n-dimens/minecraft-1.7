package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemGlassBottle extends Item
{
    private static final String __OBFID = "CL_00001776";

    public ItemGlassBottle()
    {
        this.func_77637_a(CreativeTabs.field_78038_k);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_)
    {
        return Items.POTION.func_77617_a(0);
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        MovingObjectPosition movingobjectposition = this.func_77621_a(p_77659_2_, p_77659_3_, true);

        if (movingobjectposition == null)
        {
            return p_77659_1_;
        }
        else
        {
            if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int i = movingobjectposition.field_72311_b;
                int j = movingobjectposition.field_72312_c;
                int k = movingobjectposition.field_72309_d;

                if (!p_77659_2_.func_72962_a(p_77659_3_, i, j, k))
                {
                    return p_77659_1_;
                }

                if (!p_77659_3_.func_82247_a(i, j, k, movingobjectposition.field_72310_e, p_77659_1_))
                {
                    return p_77659_1_;
                }

                if (p_77659_2_.func_147439_a(i, j, k).func_149688_o() == Material.field_151586_h)
                {
                    --p_77659_1_.field_77994_a;

                    if (p_77659_1_.field_77994_a <= 0)
                    {
                        return new ItemStack(Items.POTION);
                    }

                    if (!p_77659_3_.field_71071_by.func_70441_a(new ItemStack(Items.POTION)))
                    {
                        p_77659_3_.func_71019_a(new ItemStack(Items.POTION, 1, 0), false);
                    }
                }
            }

            return p_77659_1_;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_) {}
}