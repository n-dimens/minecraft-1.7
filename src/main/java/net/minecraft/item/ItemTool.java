package net.minecraft.item;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.World;

public class ItemTool extends Item
{
    private Set field_150914_c;
    protected float field_77864_a = 4.0F;
    private float field_77865_bY;
    protected Item.ToolMaterial field_77862_b;
    private static final String __OBFID = "CL_00000019";

    protected ItemTool(float p_i45333_1_, Item.ToolMaterial p_i45333_2_, Set p_i45333_3_)
    {
        this.field_77862_b = p_i45333_2_;
        this.field_150914_c = p_i45333_3_;
        this.stackMaxSize = 1;
        this.setDurability(p_i45333_2_.func_77997_a());
        this.field_77864_a = p_i45333_2_.func_77998_b();
        this.field_77865_bY = p_i45333_1_ + p_i45333_2_.func_78000_c();
        this.func_77637_a(CreativeTabs.field_78040_i);
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return this.field_150914_c.contains(p_150893_2_) ? this.field_77864_a : 1.0F;
    }

    public boolean func_77644_a(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        p_77644_1_.func_77972_a(2, p_77644_3_);
        return true;
    }

    public boolean func_150894_a(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        if ((double)p_150894_3_.func_149712_f(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
        {
            p_150894_1_.func_77972_a(1, p_150894_7_);
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77662_d()
    {
        return true;
    }

    public Item.ToolMaterial func_150913_i()
    {
        return this.field_77862_b;
    }

    public int func_77619_b()
    {
        return this.field_77862_b.func_77995_e();
    }

    public String func_77861_e()
    {
        return this.field_77862_b.toString();
    }

    public boolean func_82789_a(ItemStack p_82789_1_, ItemStack p_82789_2_)
    {
        return this.field_77862_b.func_150995_f() == p_82789_2_.getBaseItem() ? true : super.func_82789_a(p_82789_1_, p_82789_2_);
    }

    public Multimap func_111205_h()
    {
        Multimap multimap = super.func_111205_h();
        multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Tool modifier", (double)this.field_77865_bY, 0));
        return multimap;
    }
}