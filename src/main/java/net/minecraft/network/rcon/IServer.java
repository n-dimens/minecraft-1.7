package net.minecraft.network.rcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public interface IServer
{
    int func_71327_a(String p_71327_1_, int p_71327_2_);

    String func_71330_a(String p_71330_1_, String p_71330_2_);

    void func_71328_a(String p_71328_1_, Object p_71328_2_);

    void func_71326_a();

    String func_71329_c();

    String func_71277_t();

    int func_71234_u();

    String func_71274_v();

    String func_71249_w();

    int func_71233_x();

    int func_71275_y();

    String[] func_71213_z();

    String func_71270_I();

    String func_71258_A();

    String func_71252_i(String p_71252_1_);

    boolean func_71239_B();

    void func_71244_g(String p_71244_1_);

    void func_71236_h(String p_71236_1_);

    void func_71201_j(String p_71201_1_);

    void func_71198_k(String p_71198_1_);
}