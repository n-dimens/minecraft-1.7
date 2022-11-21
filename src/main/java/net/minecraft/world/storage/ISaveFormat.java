package net.minecraft.world.storage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.util.IProgressUpdate;

public interface ISaveFormat
{
    @SideOnly(Side.CLIENT)
    String func_154333_a();

    ISaveHandler func_75804_a(String p_75804_1_, boolean p_75804_2_);

    @SideOnly(Side.CLIENT)
    List func_75799_b() throws AnvilConverterException;

    void func_75800_d();

    @SideOnly(Side.CLIENT)
    WorldInfo func_75803_c(String p_75803_1_);

    @SideOnly(Side.CLIENT)
    boolean func_154335_d(String p_154335_1_);

    boolean func_75802_e(String p_75802_1_);

    @SideOnly(Side.CLIENT)
    void func_75806_a(String p_75806_1_, String p_75806_2_);

    @SideOnly(Side.CLIENT)
    boolean func_154334_a(String p_154334_1_);

    boolean func_75801_b(String p_75801_1_);

    boolean func_75805_a(String p_75805_1_, IProgressUpdate p_75805_2_);

    @SideOnly(Side.CLIENT)
    boolean func_90033_f(String p_90033_1_);
}