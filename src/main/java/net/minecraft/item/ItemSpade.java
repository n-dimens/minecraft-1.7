package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ItemSpade extends ItemTool
{
    private static final Set field_150916_c = Sets.newHashSet(new Block[] {Blocks.GRASS, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL, Blocks.SNOW_LAYER, Blocks.SNOW, Blocks.CLAY, Blocks.FARMLAND, Blocks.SOUL_SAND, Blocks.MYCELIUM});
    private static final String __OBFID = "CL_00000063";

    public ItemSpade(Item.ToolMaterial p_i45353_1_)
    {
        super(1.0F, p_i45353_1_, field_150916_c);
    }

    public boolean func_150897_b(Block p_150897_1_)
    {
        return p_150897_1_ == Blocks.SNOW_LAYER ? true : p_150897_1_ == Blocks.SNOW;
    }
}