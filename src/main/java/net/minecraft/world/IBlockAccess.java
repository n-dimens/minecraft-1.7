package net.minecraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;

public interface IBlockAccess
{
    Block func_147439_a(int p_147439_1_, int p_147439_2_, int p_147439_3_);

    TileEntity func_147438_o(int p_147438_1_, int p_147438_2_, int p_147438_3_);

    @SideOnly(Side.CLIENT)
    int func_72802_i(int p_72802_1_, int p_72802_2_, int p_72802_3_, int p_72802_4_);

    int func_72805_g(int p_72805_1_, int p_72805_2_, int p_72805_3_);

    int func_72879_k(int p_72879_1_, int p_72879_2_, int p_72879_3_, int p_72879_4_);

    @SideOnly(Side.CLIENT)
    boolean func_147437_c(int p_147437_1_, int p_147437_2_, int p_147437_3_);

    @SideOnly(Side.CLIENT)
    BiomeGenBase func_72807_a(int p_72807_1_, int p_72807_2_);

    @SideOnly(Side.CLIENT)
    int func_72800_K();

    @SideOnly(Side.CLIENT)
    boolean func_72806_N();
}