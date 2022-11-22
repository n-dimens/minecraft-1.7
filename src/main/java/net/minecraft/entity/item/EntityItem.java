package net.minecraft.entity.item;

import java.util.Iterator;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityItem extends Entity
{
    private static final Logger field_145803_d = LogManager.getLogger();
    public int field_70292_b;
    public int field_145804_b;
    private int field_70291_e;
    private String field_145801_f;
    private String field_145802_g;
    public float field_70290_d;
    private static final String __OBFID = "CL_00001669";

    public EntityItem(World p_i1709_1_, double p_i1709_2_, double p_i1709_4_, double p_i1709_6_)
    {
        super(p_i1709_1_);
        this.field_70291_e = 5;
        this.field_70290_d = (float)(Math.random() * Math.PI * 2.0D);
        this.func_70105_a(0.25F, 0.25F);
        this.field_70129_M = this.field_70131_O / 2.0F;
        this.func_70107_b(p_i1709_2_, p_i1709_4_, p_i1709_6_);
        this.field_70177_z = (float)(Math.random() * 360.0D);
        this.field_70159_w = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.field_70181_x = 0.20000000298023224D;
        this.field_70179_y = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    public EntityItem(World p_i1710_1_, double p_i1710_2_, double p_i1710_4_, double p_i1710_6_, ItemStack p_i1710_8_)
    {
        this(p_i1710_1_, p_i1710_2_, p_i1710_4_, p_i1710_6_);
        this.func_92058_a(p_i1710_8_);
    }

    protected boolean func_70041_e_()
    {
        return false;
    }

    public EntityItem(World p_i1711_1_)
    {
        super(p_i1711_1_);
        this.field_70291_e = 5;
        this.field_70290_d = (float)(Math.random() * Math.PI * 2.0D);
        this.func_70105_a(0.25F, 0.25F);
        this.field_70129_M = this.field_70131_O / 2.0F;
    }

    protected void func_70088_a()
    {
        this.func_70096_w().func_82709_a(10, 5);
    }

    public void func_70071_h_()
    {
        if (this.func_92059_d() == null)
        {
            this.func_70106_y();
        }
        else
        {
            super.func_70071_h_();

            if (this.field_145804_b > 0)
            {
                --this.field_145804_b;
            }

            this.field_70169_q = this.field_70165_t;
            this.field_70167_r = this.field_70163_u;
            this.field_70166_s = this.field_70161_v;
            this.field_70181_x -= 0.03999999910593033D;
            this.field_70145_X = this.func_145771_j(this.field_70165_t, (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0D, this.field_70161_v);
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            boolean flag = (int)this.field_70169_q != (int)this.field_70165_t || (int)this.field_70167_r != (int)this.field_70163_u || (int)this.field_70166_s != (int)this.field_70161_v;

            if (flag || this.field_70173_aa % 25 == 0)
            {
                if (this.world.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)).func_149688_o() == Material.field_151587_i)
                {
                    this.field_70181_x = 0.20000000298023224D;
                    this.field_70159_w = (double)((this.randomizer.nextFloat() - this.randomizer.nextFloat()) * 0.2F);
                    this.field_70179_y = (double)((this.randomizer.nextFloat() - this.randomizer.nextFloat()) * 0.2F);
                    this.func_85030_a("random.fizz", 0.4F, 2.0F + this.randomizer.nextFloat() * 0.4F);
                }

                if (!this.world.field_72995_K)
                {
                    this.func_85054_d();
                }
            }

            float f = 0.98F;

            if (this.field_70122_E)
            {
                f = this.world.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v)).field_149765_K * 0.98F;
            }

            this.field_70159_w *= (double)f;
            this.field_70181_x *= 0.9800000190734863D;
            this.field_70179_y *= (double)f;

            if (this.field_70122_E)
            {
                this.field_70181_x *= -0.5D;
            }

            ++this.field_70292_b;

            if (!this.world.field_72995_K && this.field_70292_b >= 6000)
            {
                this.func_70106_y();
            }
        }
    }

    private void func_85054_d()
    {
        Iterator iterator = this.world.func_72872_a(EntityItem.class, this.field_70121_D.func_72314_b(0.5D, 0.0D, 0.5D)).iterator();

        while (iterator.hasNext())
        {
            EntityItem entityitem = (EntityItem)iterator.next();
            this.func_70289_a(entityitem);
        }
    }

    public boolean func_70289_a(EntityItem p_70289_1_)
    {
        if (p_70289_1_ == this)
        {
            return false;
        }
        else if (p_70289_1_.func_70089_S() && this.func_70089_S())
        {
            ItemStack itemstack = this.func_92059_d();
            ItemStack itemstack1 = p_70289_1_.func_92059_d();

            if (itemstack1.getBaseItem() != itemstack.getBaseItem())
            {
                return false;
            }
            else if (itemstack1.func_77942_o() ^ itemstack.func_77942_o())
            {
                return false;
            }
            else if (itemstack1.func_77942_o() && !itemstack1.func_77978_p().equals(itemstack.func_77978_p()))
            {
                return false;
            }
            else if (itemstack1.getBaseItem() == null)
            {
                return false;
            }
            else if (itemstack1.getBaseItem().func_77614_k() && itemstack1.func_77960_j() != itemstack.func_77960_j())
            {
                return false;
            }
            else if (itemstack1.count < itemstack.count)
            {
                return p_70289_1_.func_70289_a(this);
            }
            else if (itemstack1.count + itemstack.count > itemstack1.func_77976_d())
            {
                return false;
            }
            else
            {
                itemstack1.count += itemstack.count;
                p_70289_1_.field_145804_b = Math.max(p_70289_1_.field_145804_b, this.field_145804_b);
                p_70289_1_.field_70292_b = Math.min(p_70289_1_.field_70292_b, this.field_70292_b);
                p_70289_1_.func_92058_a(itemstack1);
                this.func_70106_y();
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public void func_70288_d()
    {
        this.field_70292_b = 4800;
    }

    public boolean func_70072_I()
    {
        return this.world.func_72918_a(this.field_70121_D, Material.field_151586_h, this);
    }

    protected void func_70081_e(int p_70081_1_)
    {
        this.func_70097_a(DamageSource.field_76372_a, (float)p_70081_1_);
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else if (this.func_92059_d() != null && this.func_92059_d().getBaseItem() == Items.NETHER_STAR && p_70097_1_.func_94541_c())
        {
            return false;
        }
        else
        {
            this.func_70018_K();
            this.field_70291_e = (int)((float)this.field_70291_e - p_70097_2_);

            if (this.field_70291_e <= 0)
            {
                this.func_70106_y();
            }

            return false;
        }
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        p_70014_1_.func_74777_a("Health", (short)((byte)this.field_70291_e));
        p_70014_1_.func_74777_a("Age", (short)this.field_70292_b);

        if (this.func_145800_j() != null)
        {
            p_70014_1_.func_74778_a("Thrower", this.field_145801_f);
        }

        if (this.func_145798_i() != null)
        {
            p_70014_1_.func_74778_a("Owner", this.field_145802_g);
        }

        if (this.func_92059_d() != null)
        {
            p_70014_1_.func_74782_a("Item", this.func_92059_d().func_77955_b(new NBTTagCompound()));
        }
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        this.field_70291_e = p_70037_1_.func_74765_d("Health") & 255;
        this.field_70292_b = p_70037_1_.func_74765_d("Age");

        if (p_70037_1_.func_74764_b("Owner"))
        {
            this.field_145802_g = p_70037_1_.func_74779_i("Owner");
        }

        if (p_70037_1_.func_74764_b("Thrower"))
        {
            this.field_145801_f = p_70037_1_.func_74779_i("Thrower");
        }

        NBTTagCompound nbttagcompound1 = p_70037_1_.func_74775_l("Item");
        this.func_92058_a(ItemStack.func_77949_a(nbttagcompound1));

        if (this.func_92059_d() == null)
        {
            this.func_70106_y();
        }
    }

    public void func_70100_b_(EntityPlayer p_70100_1_)
    {
        if (!this.world.field_72995_K)
        {
            ItemStack itemstack = this.func_92059_d();
            int i = itemstack.count;

            if (this.field_145804_b == 0 && (this.field_145802_g == null || 6000 - this.field_70292_b <= 200 || this.field_145802_g.equals(p_70100_1_.func_70005_c_())) && p_70100_1_.inventory.func_70441_a(itemstack))
            {
                if (itemstack.getBaseItem() == Item.func_150898_a(Blocks.LOG))
                {
                    p_70100_1_.func_71029_a(AchievementList.MINE_WOOD);
                }

                if (itemstack.getBaseItem() == Item.func_150898_a(Blocks.LOG_2))
                {
                    p_70100_1_.func_71029_a(AchievementList.MINE_WOOD);
                }

                if (itemstack.getBaseItem() == Items.LEATHER)
                {
                    p_70100_1_.func_71029_a(AchievementList.KILL_COW);
                }

                if (itemstack.getBaseItem() == Items.DIAMOND)
                {
                    p_70100_1_.func_71029_a(AchievementList.DIAMONDS);
                }

                if (itemstack.getBaseItem() == Items.BLAZE_ROD)
                {
                    p_70100_1_.func_71029_a(AchievementList.BLAZE_ROD);
                }

                if (itemstack.getBaseItem() == Items.DIAMOND && this.func_145800_j() != null)
                {
                    EntityPlayer entityplayer1 = this.world.func_72924_a(this.func_145800_j());

                    if (entityplayer1 != null && entityplayer1 != p_70100_1_)
                    {
                        entityplayer1.func_71029_a(AchievementList.DIAMONDS_TO_YOU);
                    }
                }

                this.world.func_72956_a(p_70100_1_, "random.pop", 0.2F, ((this.randomizer.nextFloat() - this.randomizer.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                p_70100_1_.func_71001_a(this, i);

                if (itemstack.count <= 0)
                {
                    this.func_70106_y();
                }
            }
        }
    }

    public String func_70005_c_()
    {
        return StatCollector.func_74838_a("item." + this.func_92059_d().func_77977_a());
    }

    public boolean func_70075_an()
    {
        return false;
    }

    public void func_71027_c(int p_71027_1_)
    {
        super.func_71027_c(p_71027_1_);

        if (!this.world.field_72995_K)
        {
            this.func_85054_d();
        }
    }

    public ItemStack func_92059_d()
    {
        ItemStack itemstack = this.func_70096_w().func_82710_f(10);
        return itemstack == null ? new ItemStack(Blocks.STONE) : itemstack;
    }

    public void func_92058_a(ItemStack p_92058_1_)
    {
        this.func_70096_w().func_75692_b(10, p_92058_1_);
        this.func_70096_w().func_82708_h(10);
    }

    public String func_145798_i()
    {
        return this.field_145802_g;
    }

    public void func_145797_a(String p_145797_1_)
    {
        this.field_145802_g = p_145797_1_;
    }

    public String func_145800_j()
    {
        return this.field_145801_f;
    }

    public void func_145799_b(String p_145799_1_)
    {
        this.field_145801_f = p_145799_1_;
    }
}