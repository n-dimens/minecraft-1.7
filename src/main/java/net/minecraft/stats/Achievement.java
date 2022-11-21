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
    public final int field_75993_a; // x coord
    public final int field_75991_b; // y coord
    public final Achievement requiredAchievement;
    private final String descriptionId;
    @SideOnly(Side.CLIENT)
    private IStatStringFormat field_75994_l;
    public final ItemStack renderItem;
    private boolean field_75995_m;
    private static final String __OBFID = "CL_00001466";

    public Achievement(String id, String name, int p_i45300_3_, int p_i45300_4_, Item renderItem, Achievement requiredAchievement)
    {
        this(id, name, p_i45300_3_, p_i45300_4_, new ItemStack(renderItem), requiredAchievement);
    }

    public Achievement(String id, String name, int p_i45301_3_, int p_i45301_4_, Block renderBlock, Achievement requiredAchievement)
    {
        this(id, name, p_i45301_3_, p_i45301_4_, new ItemStack(renderBlock), requiredAchievement);
    }

    public Achievement(String id, String name, int p_i45302_3_, int p_i45302_4_, ItemStack renderItemStack, Achievement requiredAchievement)
    {
        super(id, new ChatComponentTranslation("achievement." + name, new Object[0]));
        this.renderItem = renderItemStack;
        this.descriptionId = "achievement." + name + ".desc";
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

        this.requiredAchievement = requiredAchievement;
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
        AchievementList.ACHIEVEMENTS.add(this);
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
        return this.field_75994_l != null ? this.field_75994_l.func_74535_a(StatCollector.func_74838_a(this.descriptionId)) : StatCollector.func_74838_a(this.descriptionId);
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