package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;

public class ItemDoublePlant extends ItemMultiTexture
{
    private static final String __OBFID = "CL_00000021";

    public ItemDoublePlant(Block p_i45335_1_, BlockDoublePlant p_i45335_2_, String[] p_i45335_3_)
    {
        super(p_i45335_1_, p_i45335_2_, p_i45335_3_);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_)
    {
        return BlockDoublePlant.func_149890_d(p_77617_1_) == 0 ? ((BlockDoublePlant)this.field_150941_b).field_149891_b[0] : ((BlockDoublePlant)this.field_150941_b).func_149888_a(true, p_77617_1_);
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_)
    {
        int j = BlockDoublePlant.func_149890_d(p_82790_1_.func_77960_j());
        return j != 2 && j != 3 ? super.func_82790_a(p_82790_1_, p_82790_2_) : ColorizerGrass.func_77480_a(0.5D, 1.0D);
    }
}