package net.minecraft.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum EnumChatFormatting
{
    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),
    OBFUSCATED('k', true),
    BOLD('l', true),
    STRIKETHROUGH('m', true),
    UNDERLINE('n', true),
    ITALIC('o', true),
    RESET('r');
    private static final Map field_96321_w = new HashMap();
    private static final Map field_96331_x = new HashMap();
    private static final Pattern field_96330_y = Pattern.compile("(?i)" + String.valueOf('\u00a7') + "[0-9A-FK-OR]");
    private final char field_96329_z;
    private final boolean field_96303_A;
    private final String field_96304_B;

    private static final String __OBFID = "CL_00000342";

    private EnumChatFormatting(char p_i1336_3_)
    {
        this(p_i1336_3_, false);
    }

    private EnumChatFormatting(char p_i1337_3_, boolean p_i1337_4_)
    {
        this.field_96329_z = p_i1337_3_;
        this.field_96303_A = p_i1337_4_;
        this.field_96304_B = "\u00a7" + p_i1337_3_;
    }

    public char func_96298_a()
    {
        return this.field_96329_z;
    }

    public boolean func_96301_b()
    {
        return this.field_96303_A;
    }

    public boolean func_96302_c()
    {
        return !this.field_96303_A && this != RESET;
    }

    public String func_96297_d()
    {
        return this.name().toLowerCase();
    }

    public String toString()
    {
        return this.field_96304_B;
    }

    @SideOnly(Side.CLIENT)
    public static String func_110646_a(String p_110646_0_)
    {
        return p_110646_0_ == null ? null : field_96330_y.matcher(p_110646_0_).replaceAll("");
    }

    public static EnumChatFormatting func_96300_b(String p_96300_0_)
    {
        return p_96300_0_ == null ? null : (EnumChatFormatting)field_96331_x.get(p_96300_0_.toLowerCase());
    }

    public static Collection func_96296_a(boolean p_96296_0_, boolean p_96296_1_)
    {
        ArrayList arraylist = new ArrayList();
        EnumChatFormatting[] aenumchatformatting = values();
        int i = aenumchatformatting.length;

        for (int j = 0; j < i; ++j)
        {
            EnumChatFormatting enumchatformatting = aenumchatformatting[j];

            if ((!enumchatformatting.func_96302_c() || p_96296_0_) && (!enumchatformatting.func_96301_b() || p_96296_1_))
            {
                arraylist.add(enumchatformatting.func_96297_d());
            }
        }

        return arraylist;
    }

    static
    {
        EnumChatFormatting[] var0 = values();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2)
        {
            EnumChatFormatting var3 = var0[var2];
            field_96321_w.put(Character.valueOf(var3.func_96298_a()), var3);
            field_96331_x.put(var3.func_96297_d(), var3);
        }
    }
}