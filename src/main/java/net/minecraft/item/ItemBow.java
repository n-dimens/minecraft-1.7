package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBow extends Item
{
    public static final String[] field_94601_a = new String[] {"pulling_0", "pulling_1", "pulling_2"};
    @SideOnly(Side.CLIENT)
    private IIcon[] field_94600_b;
    private static final String __OBFID = "CL_00001777";

    public ItemBow()
    {
        this.field_77777_bU = 1;
        this.func_77656_e(384);
        this.func_77637_a(CreativeTabs.field_78037_j);
    }

    public void func_77615_a(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
    {
        boolean flag = p_77615_3_.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, p_77615_1_) > 0;

        if (flag || p_77615_3_.field_71071_by.func_146028_b(Items.ARROW))
        {
            int j = this.func_77626_a(p_77615_1_) - p_77615_4_;
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(p_77615_2_, p_77615_3_, f * 2.0F);

            if (f == 1.0F)
            {
                entityarrow.func_70243_d(true);
            }

            int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, p_77615_1_);

            if (k > 0)
            {
                entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, p_77615_1_);

            if (l > 0)
            {
                entityarrow.func_70240_a(l);
            }

            if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, p_77615_1_) > 0)
            {
                entityarrow.func_70015_d(100);
            }

            p_77615_1_.func_77972_a(1, p_77615_3_);
            p_77615_2_.func_72956_a(p_77615_3_, "random.bow", 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.field_70251_a = 2;
            }
            else
            {
                p_77615_3_.field_71071_by.func_146026_a(Items.ARROW);
            }

            if (!p_77615_2_.field_72995_K)
            {
                p_77615_2_.func_72838_d(entityarrow);
            }
        }
    }

    public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        return p_77654_1_;
    }

    public int func_77626_a(ItemStack p_77626_1_)
    {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack p_77661_1_)
    {
        return EnumAction.bow;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        if (p_77659_3_.field_71075_bZ.field_75098_d || p_77659_3_.field_71071_by.func_146028_b(Items.ARROW))
        {
            p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
        }

        return p_77659_1_;
    }

    public int func_77619_b()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_)
    {
        this.field_77791_bV = p_94581_1_.func_94245_a(this.func_111208_A() + "_standby");
        this.field_94600_b = new IIcon[field_94601_a.length];

        for (int i = 0; i < this.field_94600_b.length; ++i)
        {
            this.field_94600_b[i] = p_94581_1_.func_94245_a(this.func_111208_A() + "_" + field_94601_a[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_94599_c(int p_94599_1_)
    {
        return this.field_94600_b[p_94599_1_];
    }
}