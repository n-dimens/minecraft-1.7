package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.world.World;

public class BlockFlowerPot extends BlockContainer
{
    private static final String __OBFID = "CL_00000247";

    public BlockFlowerPot()
    {
        super(Material.field_151594_q);
        this.func_149683_g();
    }

    public void func_149683_g()
    {
        float f = 0.375F;
        float f1 = f / 2.0F;
        this.func_149676_a(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, f, 0.5F + f1);
    }

    public boolean func_149662_c()
    {
        return false;
    }

    public int func_149645_b()
    {
        return 33;
    }

    public boolean func_149686_d()
    {
        return false;
    }

    public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        ItemStack itemstack = p_149727_5_.inventory.getActiveItem();

        if (itemstack != null && itemstack.getBaseItem() instanceof ItemBlock)
        {
            TileEntityFlowerPot tileentityflowerpot = this.func_149929_e(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);

            if (tileentityflowerpot != null)
            {
                if (tileentityflowerpot.func_145965_a() != null)
                {
                    return false;
                }
                else
                {
                    Block block = Block.func_149634_a(itemstack.getBaseItem());

                    if (!this.func_149928_a(block, itemstack.func_77960_j()))
                    {
                        return false;
                    }
                    else
                    {
                        tileentityflowerpot.func_145964_a(itemstack.getBaseItem(), itemstack.func_77960_j());
                        tileentityflowerpot.func_70296_d();

                        if (!p_149727_1_.func_72921_c(p_149727_2_, p_149727_3_, p_149727_4_, itemstack.func_77960_j(), 2))
                        {
                            p_149727_1_.func_147471_g(p_149727_2_, p_149727_3_, p_149727_4_);
                        }

                        if (!p_149727_5_.capabilities.instabuild && --itemstack.count <= 0)
                        {
                            p_149727_5_.inventory.putItem(p_149727_5_.inventory.activeItemPosition, (ItemStack)null);
                        }

                        return true;
                    }
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

    private boolean func_149928_a(Block p_149928_1_, int p_149928_2_)
    {
        return p_149928_1_ != Blocks.YELLOW_FLOWER && p_149928_1_ != Blocks.RED_FLOWER && p_149928_1_ != Blocks.CACTUS && p_149928_1_ != Blocks.BROWN_MUSHROOM && p_149928_1_ != Blocks.RED_MUSHROOM && p_149928_1_ != Blocks.SAPLING && p_149928_1_ != Blocks.DEADBUSH ? p_149928_1_ == Blocks.TALLGRASS && p_149928_2_ == 2 : true;
    }

    @SideOnly(Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        TileEntityFlowerPot tileentityflowerpot = this.func_149929_e(p_149694_1_, p_149694_2_, p_149694_3_, p_149694_4_);
        return tileentityflowerpot != null && tileentityflowerpot.func_145965_a() != null ? tileentityflowerpot.func_145965_a() : Items.FLOWER_POT;
    }

    public int func_149643_k(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        TileEntityFlowerPot tileentityflowerpot = this.func_149929_e(p_149643_1_, p_149643_2_, p_149643_3_, p_149643_4_);
        return tileentityflowerpot != null && tileentityflowerpot.func_145965_a() != null ? tileentityflowerpot.func_145966_b() : 0;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_149648_K()
    {
        return true;
    }

    public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return super.func_149742_c(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_) && World.func_147466_a(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_);
    }

    public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        if (!World.func_147466_a(p_149695_1_, p_149695_2_, p_149695_3_ - 1, p_149695_4_))
        {
            this.func_149697_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_1_.func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_), 0);
            p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
        }
    }

    public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        TileEntityFlowerPot tileentityflowerpot = this.func_149929_e(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_);

        if (tileentityflowerpot != null && tileentityflowerpot.func_145965_a() != null)
        {
            this.func_149642_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, new ItemStack(tileentityflowerpot.func_145965_a(), 1, tileentityflowerpot.func_145966_b()));
        }

        super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

    public void func_149681_a(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_)
    {
        super.func_149681_a(p_149681_1_, p_149681_2_, p_149681_3_, p_149681_4_, p_149681_5_, p_149681_6_);

        if (p_149681_6_.capabilities.instabuild)
        {
            TileEntityFlowerPot tileentityflowerpot = this.func_149929_e(p_149681_1_, p_149681_2_, p_149681_3_, p_149681_4_);

            if (tileentityflowerpot != null)
            {
                tileentityflowerpot.func_145964_a(Item.func_150899_d(0), 0);
            }
        }
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Items.FLOWER_POT;
    }

    private TileEntityFlowerPot func_149929_e(World p_149929_1_, int p_149929_2_, int p_149929_3_, int p_149929_4_)
    {
        TileEntity tileentity = p_149929_1_.func_147438_o(p_149929_2_, p_149929_3_, p_149929_4_);
        return tileentity != null && tileentity instanceof TileEntityFlowerPot ? (TileEntityFlowerPot)tileentity : null;
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_)
    {
        Object object = null;
        byte b0 = 0;

        switch (p_149915_2_)
        {
            case 1:
                object = Blocks.RED_FLOWER;
                b0 = 0;
                break;
            case 2:
                object = Blocks.YELLOW_FLOWER;
                break;
            case 3:
                object = Blocks.SAPLING;
                b0 = 0;
                break;
            case 4:
                object = Blocks.SAPLING;
                b0 = 1;
                break;
            case 5:
                object = Blocks.SAPLING;
                b0 = 2;
                break;
            case 6:
                object = Blocks.SAPLING;
                b0 = 3;
                break;
            case 7:
                object = Blocks.RED_MUSHROOM;
                break;
            case 8:
                object = Blocks.BROWN_MUSHROOM;
                break;
            case 9:
                object = Blocks.CACTUS;
                break;
            case 10:
                object = Blocks.DEADBUSH;
                break;
            case 11:
                object = Blocks.TALLGRASS;
                b0 = 2;
                break;
            case 12:
                object = Blocks.SAPLING;
                b0 = 4;
                break;
            case 13:
                object = Blocks.SAPLING;
                b0 = 5;
        }

        return new TileEntityFlowerPot(Item.func_150898_a((Block)object), b0);
    }
}