package net.minecraft.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityBeacon extends TileEntity implements IInventory
{
    public static final Potion[][] field_146009_a = new Potion[][] {{Potion.field_76424_c, Potion.field_76422_e}, {Potion.field_76429_m, Potion.field_76430_j}, {Potion.field_76420_g}, {Potion.field_76428_l}};
    @SideOnly(Side.CLIENT)
    private long field_146016_i;
    @SideOnly(Side.CLIENT)
    private float field_146014_j;
    private boolean field_146015_k;
    private int field_146012_l = -1;
    private int field_146013_m;
    private int field_146010_n;
    private ItemStack field_146011_o;
    private String field_146008_p;
    private static final String __OBFID = "CL_00000339";

    public void func_145845_h()
    {
        if (this.field_145850_b.func_82737_E() % 80L == 0L)
        {
            this.func_146003_y();
            this.func_146000_x();
        }
    }

    private void func_146000_x()
    {
        if (this.field_146015_k && this.field_146012_l > 0 && !this.field_145850_b.field_72995_K && this.field_146013_m > 0)
        {
            double d0 = (double)(this.field_146012_l * 10 + 10);
            byte b0 = 0;

            if (this.field_146012_l >= 4 && this.field_146013_m == this.field_146010_n)
            {
                b0 = 1;
            }

            AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(d0, d0, d0);
            axisalignedbb.field_72337_e = (double)this.field_145850_b.func_72800_K();
            List list = this.field_145850_b.func_72872_a(EntityPlayer.class, axisalignedbb);
            Iterator iterator = list.iterator();
            EntityPlayer entityplayer;

            while (iterator.hasNext())
            {
                entityplayer = (EntityPlayer)iterator.next();
                entityplayer.func_70690_d(new PotionEffect(this.field_146013_m, 180, b0, true));
            }

            if (this.field_146012_l >= 4 && this.field_146013_m != this.field_146010_n && this.field_146010_n > 0)
            {
                iterator = list.iterator();

                while (iterator.hasNext())
                {
                    entityplayer = (EntityPlayer)iterator.next();
                    entityplayer.func_70690_d(new PotionEffect(this.field_146010_n, 180, 0, true));
                }
            }
        }
    }

    private void func_146003_y()
    {
        int i = this.field_146012_l;

        if (!this.field_145850_b.func_72937_j(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e))
        {
            this.field_146015_k = false;
            this.field_146012_l = 0;
        }
        else
        {
            this.field_146015_k = true;
            this.field_146012_l = 0;

            for (int j = 1; j <= 4; this.field_146012_l = j++)
            {
                int k = this.field_145848_d - j;

                if (k < 0)
                {
                    break;
                }

                boolean flag = true;

                for (int l = this.field_145851_c - j; l <= this.field_145851_c + j && flag; ++l)
                {
                    for (int i1 = this.field_145849_e - j; i1 <= this.field_145849_e + j; ++i1)
                    {
                        Block block = this.field_145850_b.func_147439_a(l, k, i1);

                        if (block != Blocks.EMERALD_BLOCK && block != Blocks.GOLD_BLOCK && block != Blocks.DIAMOND_BLOCK && block != Blocks.IRON_BLOCK)
                        {
                            flag = false;
                            break;
                        }
                    }
                }

                if (!flag)
                {
                    break;
                }
            }

            if (this.field_146012_l == 0)
            {
                this.field_146015_k = false;
            }
        }

        if (!this.field_145850_b.field_72995_K && this.field_146012_l == 4 && i < this.field_146012_l)
        {
            Iterator iterator = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)this.field_145851_c, (double)(this.field_145848_d - 4), (double)this.field_145849_e).func_72314_b(10.0D, 5.0D, 10.0D)).iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();
                entityplayer.func_71029_a(AchievementList.FULL_BEACON);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public float func_146002_i()
    {
        if (!this.field_146015_k)
        {
            return 0.0F;
        }
        else
        {
            int i = (int)(this.field_145850_b.func_82737_E() - this.field_146016_i);
            this.field_146016_i = this.field_145850_b.func_82737_E();

            if (i > 1)
            {
                this.field_146014_j -= (float)i / 40.0F;

                if (this.field_146014_j < 0.0F)
                {
                    this.field_146014_j = 0.0F;
                }
            }

            this.field_146014_j += 0.025F;

            if (this.field_146014_j > 1.0F)
            {
                this.field_146014_j = 1.0F;
            }

            return this.field_146014_j;
        }
    }

    public int func_146007_j()
    {
        return this.field_146013_m;
    }

    public int func_146006_k()
    {
        return this.field_146010_n;
    }

    public int func_145998_l()
    {
        return this.field_146012_l;
    }

    @SideOnly(Side.CLIENT)
    public void func_146005_c(int p_146005_1_)
    {
        this.field_146012_l = p_146005_1_;
    }

    public void func_146001_d(int p_146001_1_)
    {
        this.field_146013_m = 0;

        for (int j = 0; j < this.field_146012_l && j < 3; ++j)
        {
            Potion[] apotion = field_146009_a[j];
            int k = apotion.length;

            for (int l = 0; l < k; ++l)
            {
                Potion potion = apotion[l];

                if (potion.field_76415_H == p_146001_1_)
                {
                    this.field_146013_m = p_146001_1_;
                    return;
                }
            }
        }
    }

    public void func_146004_e(int p_146004_1_)
    {
        this.field_146010_n = 0;

        if (this.field_146012_l >= 4)
        {
            for (int j = 0; j < 4; ++j)
            {
                Potion[] apotion = field_146009_a[j];
                int k = apotion.length;

                for (int l = 0; l < k; ++l)
                {
                    Potion potion = apotion[l];

                    if (potion.field_76415_H == p_146004_1_)
                    {
                        this.field_146010_n = p_146004_1_;
                        return;
                    }
                }
            }
        }
    }

    public Packet func_145844_m()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.func_145841_b(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 3, nbttagcompound);
    }

    @SideOnly(Side.CLIENT)
    public double func_145833_n()
    {
        return 65536.0D;
    }

    public void func_145839_a(NBTTagCompound p_145839_1_)
    {
        super.func_145839_a(p_145839_1_);
        this.field_146013_m = p_145839_1_.func_74762_e("Primary");
        this.field_146010_n = p_145839_1_.func_74762_e("Secondary");
        this.field_146012_l = p_145839_1_.func_74762_e("Levels");
    }

    public void func_145841_b(NBTTagCompound p_145841_1_)
    {
        super.func_145841_b(p_145841_1_);
        p_145841_1_.func_74768_a("Primary", this.field_146013_m);
        p_145841_1_.func_74768_a("Secondary", this.field_146010_n);
        p_145841_1_.func_74768_a("Levels", this.field_146012_l);
    }

    public int func_70302_i_()
    {
        return 1;
    }

    public ItemStack func_70301_a(int p_70301_1_)
    {
        return p_70301_1_ == 0 ? this.field_146011_o : null;
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_)
    {
        if (p_70298_1_ == 0 && this.field_146011_o != null)
        {
            if (p_70298_2_ >= this.field_146011_o.field_77994_a)
            {
                ItemStack itemstack = this.field_146011_o;
                this.field_146011_o = null;
                return itemstack;
            }
            else
            {
                this.field_146011_o.field_77994_a -= p_70298_2_;
                return new ItemStack(this.field_146011_o.func_77973_b(), p_70298_2_, this.field_146011_o.func_77960_j());
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack func_70304_b(int p_70304_1_)
    {
        if (p_70304_1_ == 0 && this.field_146011_o != null)
        {
            ItemStack itemstack = this.field_146011_o;
            this.field_146011_o = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_)
    {
        if (p_70299_1_ == 0)
        {
            this.field_146011_o = p_70299_2_;
        }
    }

    public String func_145825_b()
    {
        return this.func_145818_k_() ? this.field_146008_p : "container.beacon";
    }

    public boolean func_145818_k_()
    {
        return this.field_146008_p != null && this.field_146008_p.length() > 0;
    }

    public void func_145999_a(String p_145999_1_)
    {
        this.field_146008_p = p_145999_1_;
    }

    public int func_70297_j_()
    {
        return 1;
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_)
    {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D) <= 64.0D;
    }

    public void func_70295_k_() {}

    public void func_70305_f() {}

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_)
    {
        return p_94041_2_.func_77973_b() == Items.EMERALD || p_94041_2_.func_77973_b() == Items.DIAMOND || p_94041_2_.func_77973_b() == Items.GOLD_INGOT || p_94041_2_.func_77973_b() == Items.IRON_INGOT;
    }
}