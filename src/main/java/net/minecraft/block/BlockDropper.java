package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

public class BlockDropper extends BlockDispenser
{
    private final IBehaviorDispenseItem field_149947_P = new BehaviorDefaultDispenseItem();
    private static final String __OBFID = "CL_00000233";

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_)
    {
        this.field_149761_L = p_149651_1_.func_94245_a("furnace_side");
        this.field_149944_M = p_149651_1_.func_94245_a("furnace_top");
        this.field_149945_N = p_149651_1_.func_94245_a(this.func_149641_N() + "_front_horizontal");
        this.field_149946_O = p_149651_1_.func_94245_a(this.func_149641_N() + "_front_vertical");
    }

    protected IBehaviorDispenseItem func_149940_a(ItemStack p_149940_1_)
    {
        return this.field_149947_P;
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityDropper();
    }

    protected void func_149941_e(World p_149941_1_, int p_149941_2_, int p_149941_3_, int p_149941_4_)
    {
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(p_149941_1_, p_149941_2_, p_149941_3_, p_149941_4_);
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser)blocksourceimpl.func_150835_j();

        if (tileentitydispenser != null)
        {
            int l = tileentitydispenser.func_146017_i();

            if (l < 0)
            {
                p_149941_1_.func_72926_e(1001, p_149941_2_, p_149941_3_, p_149941_4_, 0);
            }
            else
            {
                ItemStack itemstack = tileentitydispenser.func_70301_a(l);
                int i1 = p_149941_1_.func_72805_g(p_149941_2_, p_149941_3_, p_149941_4_) & 7;
                IInventory iinventory = TileEntityHopper.func_145893_b(p_149941_1_, (double)(p_149941_2_ + Facing.field_71586_b[i1]), (double)(p_149941_3_ + Facing.field_71587_c[i1]), (double)(p_149941_4_ + Facing.field_71585_d[i1]));
                ItemStack itemstack1;

                if (iinventory != null)
                {
                    itemstack1 = TileEntityHopper.func_145889_a(iinventory, itemstack.func_77946_l().func_77979_a(1), Facing.field_71588_a[i1]);

                    if (itemstack1 == null)
                    {
                        itemstack1 = itemstack.func_77946_l();

                        if (--itemstack1.count == 0)
                        {
                            itemstack1 = null;
                        }
                    }
                    else
                    {
                        itemstack1 = itemstack.func_77946_l();
                    }
                }
                else
                {
                    itemstack1 = this.field_149947_P.func_82482_a(blocksourceimpl, itemstack);

                    if (itemstack1 != null && itemstack1.count == 0)
                    {
                        itemstack1 = null;
                    }
                }

                tileentitydispenser.putItem(l, itemstack1);
            }
        }
    }
}