package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.IEntitySelector;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemArmor extends Item
{
    private static final int[] field_77882_bY = new int[] {11, 16, 15, 13};
    private static final String[] field_94606_cu = new String[] {"leather_helmet_overlay", "leather_chestplate_overlay", "leather_leggings_overlay", "leather_boots_overlay"};
    public static final String[] field_94603_a = new String[] {"empty_armor_slot_helmet", "empty_armor_slot_chestplate", "empty_armor_slot_leggings", "empty_armor_slot_boots"};
    private static final IBehaviorDispenseItem field_96605_cw = new BehaviorDefaultDispenseItem()
    {
        private static final String __OBFID = "CL_00001767";
        protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
        {
            EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            int i = p_82487_1_.func_82623_d() + enumfacing.func_82601_c();
            int j = p_82487_1_.func_82622_e() + enumfacing.func_96559_d();
            int k = p_82487_1_.func_82621_f() + enumfacing.func_82599_e();
            AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
            List list = p_82487_1_.func_82618_k().func_82733_a(EntityLivingBase.class, axisalignedbb, new IEntitySelector.ArmoredMob(p_82487_2_));

            if (list.size() > 0)
            {
                EntityLivingBase entitylivingbase = (EntityLivingBase)list.get(0);
                int l = entitylivingbase instanceof EntityPlayer ? 1 : 0;
                int i1 = EntityLiving.func_82159_b(p_82487_2_);
                ItemStack itemstack1 = p_82487_2_.func_77946_l();
                itemstack1.field_77994_a = 1;
                entitylivingbase.func_70062_b(i1 - l, itemstack1);

                if (entitylivingbase instanceof EntityLiving)
                {
                    ((EntityLiving)entitylivingbase).func_96120_a(i1, 2.0F);
                }

                --p_82487_2_.field_77994_a;
                return p_82487_2_;
            }
            else
            {
                return super.func_82487_b(p_82487_1_, p_82487_2_);
            }
        }
    };
    public final int field_77881_a;
    public final int field_77879_b;
    public final int field_77880_c;
    private final ItemArmor.ArmorMaterial field_77878_bZ;
    @SideOnly(Side.CLIENT)
    private IIcon field_94605_cw;
    @SideOnly(Side.CLIENT)
    private IIcon field_94604_cx;
    private static final String __OBFID = "CL_00001766";

    public ItemArmor(ItemArmor.ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_)
    {
        this.field_77878_bZ = p_i45325_1_;
        this.field_77881_a = p_i45325_3_;
        this.field_77880_c = p_i45325_2_;
        this.field_77879_b = p_i45325_1_.func_78044_b(p_i45325_3_);
        this.func_77656_e(p_i45325_1_.func_78046_a(p_i45325_3_));
        this.field_77777_bU = 1;
        this.func_77637_a(CreativeTabs.field_78037_j);
        BlockDispenser.field_149943_a.add(this, field_96605_cw);
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_)
    {
        if (p_82790_2_ > 0)
        {
            return 16777215;
        }
        else
        {
            int j = this.func_82814_b(p_82790_1_);

            if (j < 0)
            {
                j = 16777215;
            }

            return j;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77623_v()
    {
        return this.field_77878_bZ == ItemArmor.ArmorMaterial.CLOTH;
    }

    public int func_77619_b()
    {
        return this.field_77878_bZ.func_78045_a();
    }

    public ItemArmor.ArmorMaterial func_82812_d()
    {
        return this.field_77878_bZ;
    }

    public boolean func_82816_b_(ItemStack p_82816_1_)
    {
        return this.field_77878_bZ != ItemArmor.ArmorMaterial.CLOTH ? false : (!p_82816_1_.func_77942_o() ? false : (!p_82816_1_.func_77978_p().func_150297_b("display", 10) ? false : p_82816_1_.func_77978_p().func_74775_l("display").func_150297_b("color", 3)));
    }

    public int func_82814_b(ItemStack p_82814_1_)
    {
        if (this.field_77878_bZ != ItemArmor.ArmorMaterial.CLOTH)
        {
            return -1;
        }
        else
        {
            NBTTagCompound nbttagcompound = p_82814_1_.func_77978_p();

            if (nbttagcompound == null)
            {
                return 10511680;
            }
            else
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
                return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.func_150297_b("color", 3) ? nbttagcompound1.func_74762_e("color") : 10511680);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77618_c(int p_77618_1_, int p_77618_2_)
    {
        return p_77618_2_ == 1 ? this.field_94605_cw : super.func_77618_c(p_77618_1_, p_77618_2_);
    }

    public void func_82815_c(ItemStack p_82815_1_)
    {
        if (this.field_77878_bZ == ItemArmor.ArmorMaterial.CLOTH)
        {
            NBTTagCompound nbttagcompound = p_82815_1_.func_77978_p();

            if (nbttagcompound != null)
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");

                if (nbttagcompound1.func_74764_b("color"))
                {
                    nbttagcompound1.func_82580_o("color");
                }
            }
        }
    }

    public void func_82813_b(ItemStack p_82813_1_, int p_82813_2_)
    {
        if (this.field_77878_bZ != ItemArmor.ArmorMaterial.CLOTH)
        {
            throw new UnsupportedOperationException("Can\'t dye non-leather!");
        }
        else
        {
            NBTTagCompound nbttagcompound = p_82813_1_.func_77978_p();

            if (nbttagcompound == null)
            {
                nbttagcompound = new NBTTagCompound();
                p_82813_1_.func_77982_d(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");

            if (!nbttagcompound.func_150297_b("display", 10))
            {
                nbttagcompound.func_74782_a("display", nbttagcompound1);
            }

            nbttagcompound1.func_74768_a("color", p_82813_2_);
        }
    }

    public boolean func_82789_a(ItemStack p_82789_1_, ItemStack p_82789_2_)
    {
        return this.field_77878_bZ.func_151685_b() == p_82789_2_.func_77973_b() ? true : super.func_82789_a(p_82789_1_, p_82789_2_);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_)
    {
        super.func_94581_a(p_94581_1_);

        if (this.field_77878_bZ == ItemArmor.ArmorMaterial.CLOTH)
        {
            this.field_94605_cw = p_94581_1_.func_94245_a(field_94606_cu[this.field_77881_a]);
        }

        this.field_94604_cx = p_94581_1_.func_94245_a(field_94603_a[this.field_77881_a]);
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        int i = EntityLiving.func_82159_b(p_77659_1_) - 1;
        ItemStack itemstack1 = p_77659_3_.func_82169_q(i);

        if (itemstack1 == null)
        {
            p_77659_3_.func_70062_b(i, p_77659_1_.func_77946_l());
            p_77659_1_.field_77994_a = 0;
        }

        return p_77659_1_;
    }

    @SideOnly(Side.CLIENT)
    public static IIcon func_94602_b(int p_94602_0_)
    {
        switch (p_94602_0_)
        {
            case 0:
                return Items.DIAMOND_HELMET.field_94604_cx;
            case 1:
                return Items.DIAMOND_CHESTPLATE.field_94604_cx;
            case 2:
                return Items.DIAMOND_LEGGINGS.field_94604_cx;
            case 3:
                return Items.DIAMOND_BOOTS.field_94604_cx;
            default:
                return null;
        }
    }

    public static enum ArmorMaterial
    {
        CLOTH(5, new int[]{1, 3, 2, 1}, 15),
        CHAIN(15, new int[]{2, 5, 4, 1}, 12),
        IRON(15, new int[]{2, 6, 5, 2}, 9),
        GOLD(7, new int[]{2, 5, 3, 1}, 25),
        DIAMOND(33, new int[]{3, 8, 6, 3}, 10);
        private int field_78048_f;
        private int[] field_78049_g;
        private int field_78055_h;

        private static final String __OBFID = "CL_00001768";

        private ArmorMaterial(int p_i1827_3_, int[] p_i1827_4_, int p_i1827_5_)
        {
            this.field_78048_f = p_i1827_3_;
            this.field_78049_g = p_i1827_4_;
            this.field_78055_h = p_i1827_5_;
        }

        public int func_78046_a(int p_78046_1_)
        {
            return ItemArmor.field_77882_bY[p_78046_1_] * this.field_78048_f;
        }

        public int func_78044_b(int p_78044_1_)
        {
            return this.field_78049_g[p_78044_1_];
        }

        public int func_78045_a()
        {
            return this.field_78055_h;
        }

        public Item func_151685_b()
        {
            return this == CLOTH ? Items.LEATHER : (this == CHAIN ? Items.IRON_INGOT : (this == GOLD ? Items.GOLD_INGOT : (this == IRON ? Items.IRON_INGOT : (this == DIAMOND ? Items.DIAMOND : null))));
        }
    }
}