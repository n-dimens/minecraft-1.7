package net.minecraft.stats;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class Achievement extends StatBase
{
    public final int field_75993_a;
    public final int field_75991_b;
    public final Achievement field_75992_c;
    private final String field_75996_k;
    @SideOnly(Side.CLIENT)
    private IStatStringFormat field_75994_l;
    public final ItemStack field_75990_d;
    private boolean field_75995_m;
    private static final String __OBFID = "CL_00001466";

    public Achievement(String p_i45300_1_, String p_i45300_2_, int p_i45300_3_, int p_i45300_4_, Item p_i45300_5_, Achievement p_i45300_6_)
    {
        this(p_i45300_1_, p_i45300_2_, p_i45300_3_, p_i45300_4_, new ItemStack(p_i45300_5_), p_i45300_6_);
    }

    public Achievement(String p_i45301_1_, String p_i45301_2_, int p_i45301_3_, int p_i45301_4_, Block p_i45301_5_, Achievement p_i45301_6_)
    {
        this(p_i45301_1_, p_i45301_2_, p_i45301_3_, p_i45301_4_, new ItemStack(p_i45301_5_), p_i45301_6_);
    }

    public Achievement(String p_i45302_1_, String p_i45302_2_, int p_i45302_3_, int p_i45302_4_, ItemStack p_i45302_5_, Achievement p_i45302_6_)
    {
        super(p_i45302_1_, new ChatComponentTranslation("achievement." + p_i45302_2_, new Object[0]));
        this.field_75990_d = p_i45302_5_;
        this.field_75996_k = "achievement." + p_i45302_2_ + ".desc";
        this.field_75993_a = p_i45302_3_;
        this.field_75991_b = p_i45302_4_;

        if (p_i45302_3_ < AchievementList.field_76010_a)
        {
            AchievementList.field_76010_a = p_i45302_3_;
        }

        if (p_i45302_4_ < AchievementList.field_76008_b)
        {
            AchievementList.field_76008_b = p_i45302_4_;
        }

        if (p_i45302_3_ > AchievementList.field_76009_c)
        {
            AchievementList.field_76009_c = p_i45302_3_;
        }

        if (p_i45302_4_ > AchievementList.field_76006_d)
        {
            AchievementList.field_76006_d = p_i45302_4_;
        }

        this.field_75992_c = p_i45302_6_;
    }

    public Achievement awardOnlyLocal()
    {
        this.awardLocallyOnly = true;
        return this;
    }

    public Achievement func_75987_b()
    {
        this.field_75995_m = true;
        return this;
    }

    public Achievement add()
    {
        super.add();
        AchievementList.field_76007_e.add(this);
        return this;
    }

    public boolean func_75967_d()
    {
        return true;
    }

    public IChatComponent func_150951_e()
    {
        IChatComponent ichatcomponent = super.func_150951_e();
        ichatcomponent.func_150256_b().func_150238_a(this.func_75984_f() ? EnumChatFormatting.DARK_PURPLE : EnumChatFormatting.GREEN);
        return ichatcomponent;
    }

    public Achievement func_150953_b(Class p_150953_1_)
    {
        return (Achievement)super.func_150953_b(p_150953_1_);
    }

    @SideOnly(Side.CLIENT)
    public String func_75989_e()
    {
        return this.field_75994_l != null ? this.field_75994_l.func_74535_a(StatCollector.func_74838_a(this.field_75996_k)) : StatCollector.func_74838_a(this.field_75996_k);
    }

    @SideOnly(Side.CLIENT)
    public Achievement func_75988_a(IStatStringFormat p_75988_1_)
    {
        this.field_75994_l = p_75988_1_;
        return this;
    }

    public boolean func_75984_f()
    {
        return this.field_75995_m;
    }
}