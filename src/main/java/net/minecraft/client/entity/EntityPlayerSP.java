package net.minecraft.client.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseFilter;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityPlayerSP extends AbstractClientPlayer
{
    public MovementInput field_71158_b;
    protected Minecraft field_71159_c;
    protected int field_71156_d;
    public int field_71157_e;
    public float field_71154_f;
    public float field_71155_g;
    public float field_71163_h;
    public float field_71164_i;
    private int field_110320_a;
    private float field_110321_bQ;
    private MouseFilter field_71162_ch = new MouseFilter();
    private MouseFilter field_71160_ci = new MouseFilter();
    private MouseFilter field_71161_cj = new MouseFilter();
    public float field_71086_bY;
    public float field_71080_cy;
    private static final String __OBFID = "CL_00000938";

    public EntityPlayerSP(Minecraft p_i1238_1_, World p_i1238_2_, Session p_i1238_3_, int p_i1238_4_)
    {
        super(p_i1238_2_, p_i1238_3_.func_148256_e());
        this.field_71159_c = p_i1238_1_;
        this.field_71093_bK = p_i1238_4_;
    }

    public void func_70626_be()
    {
        super.func_70626_be();
        this.field_70702_br = this.field_71158_b.field_78902_a;
        this.field_70701_bs = this.field_71158_b.field_78900_b;
        this.field_70703_bu = this.field_71158_b.field_78901_c;
        this.field_71163_h = this.field_71154_f;
        this.field_71164_i = this.field_71155_g;
        this.field_71155_g = (float)((double)this.field_71155_g + (double)(this.field_70125_A - this.field_71155_g) * 0.5D);
        this.field_71154_f = (float)((double)this.field_71154_f + (double)(this.field_70177_z - this.field_71154_f) * 0.5D);
    }

    public void func_70636_d()
    {
        if (this.field_71157_e > 0)
        {
            --this.field_71157_e;

            if (this.field_71157_e == 0)
            {
                this.func_70031_b(false);
            }
        }

        if (this.field_71156_d > 0)
        {
            --this.field_71156_d;
        }

        if (this.field_71159_c.field_71442_b.func_78747_a())
        {
            this.field_70165_t = this.field_70161_v = 0.5D;
            this.field_70165_t = 0.0D;
            this.field_70161_v = 0.0D;
            this.field_70177_z = (float)this.field_70173_aa / 12.0F;
            this.field_70125_A = 10.0F;
            this.field_70163_u = 68.5D;
        }
        else
        {
            this.field_71080_cy = this.field_71086_bY;

            if (this.field_71087_bX)
            {
                if (this.field_71159_c.field_71462_r != null)
                {
                    this.field_71159_c.func_147108_a((GuiScreen)null);
                }

                if (this.field_71086_bY == 0.0F)
                {
                    this.field_71159_c.func_147118_V().func_147682_a(PositionedSoundRecord.func_147674_a(new ResourceLocation("portal.trigger"), this.randomizer.nextFloat() * 0.4F + 0.8F));
                }

                this.field_71086_bY += 0.0125F;

                if (this.field_71086_bY >= 1.0F)
                {
                    this.field_71086_bY = 1.0F;
                }

                this.field_71087_bX = false;
            }
            else if (this.func_70644_a(Potion.field_76431_k) && this.func_70660_b(Potion.field_76431_k).func_76459_b() > 60)
            {
                this.field_71086_bY += 0.006666667F;

                if (this.field_71086_bY > 1.0F)
                {
                    this.field_71086_bY = 1.0F;
                }
            }
            else
            {
                if (this.field_71086_bY > 0.0F)
                {
                    this.field_71086_bY -= 0.05F;
                }

                if (this.field_71086_bY < 0.0F)
                {
                    this.field_71086_bY = 0.0F;
                }
            }

            if (this.field_71088_bW > 0)
            {
                --this.field_71088_bW;
            }

            boolean flag = this.field_71158_b.field_78901_c;
            float f = 0.8F;
            boolean flag1 = this.field_71158_b.field_78900_b >= f;
            this.field_71158_b.func_78898_a();

            if (this.func_71039_bw() && !this.func_70115_ae())
            {
                this.field_71158_b.field_78902_a *= 0.2F;
                this.field_71158_b.field_78900_b *= 0.2F;
                this.field_71156_d = 0;
            }

            if (this.field_71158_b.field_78899_d && this.field_70139_V < 0.2F)
            {
                this.field_70139_V = 0.2F;
            }

            this.func_145771_j(this.field_70165_t - (double)this.field_70130_N * 0.35D, this.field_70121_D.field_72338_b + 0.5D, this.field_70161_v + (double)this.field_70130_N * 0.35D);
            this.func_145771_j(this.field_70165_t - (double)this.field_70130_N * 0.35D, this.field_70121_D.field_72338_b + 0.5D, this.field_70161_v - (double)this.field_70130_N * 0.35D);
            this.func_145771_j(this.field_70165_t + (double)this.field_70130_N * 0.35D, this.field_70121_D.field_72338_b + 0.5D, this.field_70161_v - (double)this.field_70130_N * 0.35D);
            this.func_145771_j(this.field_70165_t + (double)this.field_70130_N * 0.35D, this.field_70121_D.field_72338_b + 0.5D, this.field_70161_v + (double)this.field_70130_N * 0.35D);
            boolean flag2 = (float)this.func_71024_bL().func_75116_a() > 6.0F || this.capabilities.mayfly;

            if (this.field_70122_E && !flag1 && this.field_71158_b.field_78900_b >= f && !this.func_70051_ag() && flag2 && !this.func_71039_bw() && !this.func_70644_a(Potion.field_76440_q))
            {
                if (this.field_71156_d <= 0 && !this.field_71159_c.gameSettings.field_151444_V.func_151470_d())
                {
                    this.field_71156_d = 7;
                }
                else
                {
                    this.func_70031_b(true);
                }
            }

            if (!this.func_70051_ag() && this.field_71158_b.field_78900_b >= f && flag2 && !this.func_71039_bw() && !this.func_70644_a(Potion.field_76440_q) && this.field_71159_c.gameSettings.field_151444_V.func_151470_d())
            {
                this.func_70031_b(true);
            }

            if (this.func_70051_ag() && (this.field_71158_b.field_78900_b < f || this.field_70123_F || !flag2))
            {
                this.func_70031_b(false);
            }

            if (this.capabilities.mayfly && !flag && this.field_71158_b.field_78901_c)
            {
                if (this.field_71101_bC == 0)
                {
                    this.field_71101_bC = 7;
                }
                else
                {
                    this.capabilities.flying = !this.capabilities.flying;
                    this.func_71016_p();
                    this.field_71101_bC = 0;
                }
            }

            if (this.capabilities.flying)
            {
                if (this.field_71158_b.field_78899_d)
                {
                    this.field_70181_x -= 0.15D;
                }

                if (this.field_71158_b.field_78901_c)
                {
                    this.field_70181_x += 0.15D;
                }
            }

            if (this.func_110317_t())
            {
                if (this.field_110320_a < 0)
                {
                    ++this.field_110320_a;

                    if (this.field_110320_a == 0)
                    {
                        this.field_110321_bQ = 0.0F;
                    }
                }

                if (flag && !this.field_71158_b.field_78901_c)
                {
                    this.field_110320_a = -10;
                    this.func_110318_g();
                }
                else if (!flag && this.field_71158_b.field_78901_c)
                {
                    this.field_110320_a = 0;
                    this.field_110321_bQ = 0.0F;
                }
                else if (flag)
                {
                    ++this.field_110320_a;

                    if (this.field_110320_a < 10)
                    {
                        this.field_110321_bQ = (float)this.field_110320_a * 0.1F;
                    }
                    else
                    {
                        this.field_110321_bQ = 0.8F + 2.0F / (float)(this.field_110320_a - 9) * 0.1F;
                    }
                }
            }
            else
            {
                this.field_110321_bQ = 0.0F;
            }

            super.func_70636_d();

            if (this.field_70122_E && this.capabilities.flying)
            {
                this.capabilities.flying = false;
                this.func_71016_p();
            }
        }
    }

    public float func_71151_f()
    {
        float f = 1.0F;

        if (this.capabilities.flying)
        {
            f *= 1.1F;
        }

        IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
        f = (float)((double)f * ((iattributeinstance.func_111126_e() / (double)this.capabilities.func_75094_b() + 1.0D) / 2.0D));

        if (this.capabilities.func_75094_b() == 0.0F || Float.isNaN(f) || Float.isInfinite(f))
        {
            f = 1.0F;
        }

        if (this.func_71039_bw() && this.func_71011_bu().getBaseItem() == Items.BOW)
        {
            int i = this.func_71057_bx();
            float f1 = (float)i / 20.0F;

            if (f1 > 1.0F)
            {
                f1 = 1.0F;
            }
            else
            {
                f1 *= f1;
            }

            f *= 1.0F - f1 * 0.15F;
        }

        return f;
    }

    public void func_71053_j()
    {
        super.func_71053_j();
        this.field_71159_c.func_147108_a((GuiScreen)null);
    }

    public void func_146100_a(TileEntity p_146100_1_)
    {
        if (p_146100_1_ instanceof TileEntitySign)
        {
            this.field_71159_c.func_147108_a(new GuiEditSign((TileEntitySign)p_146100_1_));
        }
        else if (p_146100_1_ instanceof TileEntityCommandBlock)
        {
            this.field_71159_c.func_147108_a(new GuiCommandBlock(((TileEntityCommandBlock)p_146100_1_).func_145993_a()));
        }
    }

    public void func_146095_a(CommandBlockLogic p_146095_1_)
    {
        this.field_71159_c.func_147108_a(new GuiCommandBlock(p_146095_1_));
    }

    public void func_71048_c(ItemStack p_71048_1_)
    {
        Item item = p_71048_1_.getBaseItem();

        if (item == Items.WRITTEN_BOOK)
        {
            this.field_71159_c.func_147108_a(new GuiScreenBook(this, p_71048_1_, false));
        }
        else if (item == Items.WRITABLE_BOOK)
        {
            this.field_71159_c.func_147108_a(new GuiScreenBook(this, p_71048_1_, true));
        }
    }

    public void func_71007_a(IInventory p_71007_1_)
    {
        this.field_71159_c.func_147108_a(new GuiChest(this.inventory, p_71007_1_));
    }

    public void func_146093_a(TileEntityHopper p_146093_1_)
    {
        this.field_71159_c.func_147108_a(new GuiHopper(this.inventory, p_146093_1_));
    }

    public void func_96125_a(EntityMinecartHopper p_96125_1_)
    {
        this.field_71159_c.func_147108_a(new GuiHopper(this.inventory, p_96125_1_));
    }

    public void func_110298_a(EntityHorse p_110298_1_, IInventory p_110298_2_)
    {
        this.field_71159_c.func_147108_a(new GuiScreenHorseInventory(this.inventory, p_110298_2_, p_110298_1_));
    }

    public void func_71058_b(int p_71058_1_, int p_71058_2_, int p_71058_3_)
    {
        this.field_71159_c.func_147108_a(new GuiCrafting(this.inventory, this.world, p_71058_1_, p_71058_2_, p_71058_3_));
    }

    public void func_71002_c(int p_71002_1_, int p_71002_2_, int p_71002_3_, String p_71002_4_)
    {
        this.field_71159_c.func_147108_a(new GuiEnchantment(this.inventory, this.world, p_71002_1_, p_71002_2_, p_71002_3_, p_71002_4_));
    }

    public void func_82244_d(int p_82244_1_, int p_82244_2_, int p_82244_3_)
    {
        this.field_71159_c.func_147108_a(new GuiRepair(this.inventory, this.world, p_82244_1_, p_82244_2_, p_82244_3_));
    }

    public void func_146101_a(TileEntityFurnace p_146101_1_)
    {
        this.field_71159_c.func_147108_a(new GuiFurnace(this.inventory, p_146101_1_));
    }

    public void func_146098_a(TileEntityBrewingStand p_146098_1_)
    {
        this.field_71159_c.func_147108_a(new GuiBrewingStand(this.inventory, p_146098_1_));
    }

    public void func_146104_a(TileEntityBeacon p_146104_1_)
    {
        this.field_71159_c.func_147108_a(new GuiBeacon(this.inventory, p_146104_1_));
    }

    public void func_146102_a(TileEntityDispenser p_146102_1_)
    {
        this.field_71159_c.func_147108_a(new GuiDispenser(this.inventory, p_146102_1_));
    }

    public void func_71030_a(IMerchant p_71030_1_, String p_71030_2_)
    {
        this.field_71159_c.func_147108_a(new GuiMerchant(this.inventory, p_71030_1_, this.world, p_71030_2_));
    }

    public void func_71009_b(Entity p_71009_1_)
    {
        this.field_71159_c.field_71452_i.func_78873_a(new EntityCrit2FX(this.field_71159_c.field_71441_e, p_71009_1_));
    }

    public void func_71047_c(Entity p_71047_1_)
    {
        EntityCrit2FX entitycrit2fx = new EntityCrit2FX(this.field_71159_c.field_71441_e, p_71047_1_, "magicCrit");
        this.field_71159_c.field_71452_i.func_78873_a(entitycrit2fx);
    }

    public void func_71001_a(Entity p_71001_1_, int p_71001_2_)
    {
        this.field_71159_c.field_71452_i.func_78873_a(new EntityPickupFX(this.field_71159_c.field_71441_e, p_71001_1_, this, -0.5F));
    }

    public boolean func_70093_af()
    {
        return this.field_71158_b.field_78899_d && !this.field_71083_bS;
    }

    public void func_71150_b(float p_71150_1_)
    {
        float f1 = this.func_110143_aJ() - p_71150_1_;

        if (f1 <= 0.0F)
        {
            this.func_70606_j(p_71150_1_);

            if (f1 < 0.0F)
            {
                this.field_70172_ad = this.field_70771_an / 2;
            }
        }
        else
        {
            this.field_110153_bc = f1;
            this.func_70606_j(this.func_110143_aJ());
            this.field_70172_ad = this.field_70771_an;
            this.func_70665_d(DamageSource.field_76377_j, f1);
            this.field_70737_aN = this.field_70738_aO = 10;
        }
    }

    public void func_146105_b(IChatComponent p_146105_1_)
    {
        this.field_71159_c.field_71456_v.func_146158_b().func_146227_a(p_146105_1_);
    }

    private boolean func_71153_f(int p_71153_1_, int p_71153_2_, int p_71153_3_)
    {
        return this.world.func_147439_a(p_71153_1_, p_71153_2_, p_71153_3_).func_149721_r();
    }

    protected boolean func_145771_j(double p_145771_1_, double p_145771_3_, double p_145771_5_)
    {
        int i = MathHelper.func_76128_c(p_145771_1_);
        int j = MathHelper.func_76128_c(p_145771_3_);
        int k = MathHelper.func_76128_c(p_145771_5_);
        double d3 = p_145771_1_ - (double)i;
        double d4 = p_145771_5_ - (double)k;

        if (this.func_71153_f(i, j, k) || this.func_71153_f(i, j + 1, k))
        {
            boolean flag = !this.func_71153_f(i - 1, j, k) && !this.func_71153_f(i - 1, j + 1, k);
            boolean flag1 = !this.func_71153_f(i + 1, j, k) && !this.func_71153_f(i + 1, j + 1, k);
            boolean flag2 = !this.func_71153_f(i, j, k - 1) && !this.func_71153_f(i, j + 1, k - 1);
            boolean flag3 = !this.func_71153_f(i, j, k + 1) && !this.func_71153_f(i, j + 1, k + 1);
            byte b0 = -1;
            double d5 = 9999.0D;

            if (flag && d3 < d5)
            {
                d5 = d3;
                b0 = 0;
            }

            if (flag1 && 1.0D - d3 < d5)
            {
                d5 = 1.0D - d3;
                b0 = 1;
            }

            if (flag2 && d4 < d5)
            {
                d5 = d4;
                b0 = 4;
            }

            if (flag3 && 1.0D - d4 < d5)
            {
                d5 = 1.0D - d4;
                b0 = 5;
            }

            float f = 0.1F;

            if (b0 == 0)
            {
                this.field_70159_w = (double)(-f);
            }

            if (b0 == 1)
            {
                this.field_70159_w = (double)f;
            }

            if (b0 == 4)
            {
                this.field_70179_y = (double)(-f);
            }

            if (b0 == 5)
            {
                this.field_70179_y = (double)f;
            }
        }

        return false;
    }

    public void func_70031_b(boolean p_70031_1_)
    {
        super.func_70031_b(p_70031_1_);
        this.field_71157_e = p_70031_1_ ? 600 : 0;
    }

    public void func_71152_a(float p_71152_1_, int p_71152_2_, int p_71152_3_)
    {
        this.field_71106_cc = p_71152_1_;
        this.field_71067_cb = p_71152_2_;
        this.field_71068_ca = p_71152_3_;
    }

    public void func_145747_a(IChatComponent p_145747_1_)
    {
        this.field_71159_c.field_71456_v.func_146158_b().func_146227_a(p_145747_1_);
    }

    public boolean func_70003_b(int p_70003_1_, String p_70003_2_)
    {
        return p_70003_1_ <= 0;
    }

    public ChunkCoordinates func_82114_b()
    {
        return new ChunkCoordinates(MathHelper.func_76128_c(this.field_70165_t + 0.5D), MathHelper.func_76128_c(this.field_70163_u + 0.5D), MathHelper.func_76128_c(this.field_70161_v + 0.5D));
    }

    public void func_85030_a(String p_85030_1_, float p_85030_2_, float p_85030_3_)
    {
        this.world.func_72980_b(this.field_70165_t, this.field_70163_u - (double)this.field_70129_M, this.field_70161_v, p_85030_1_, p_85030_2_, p_85030_3_, false);
    }

    public boolean func_70613_aW()
    {
        return true;
    }

    public boolean func_110317_t()
    {
        return this.field_70154_o != null && this.field_70154_o instanceof EntityHorse;
    }

    public float func_110319_bJ()
    {
        return this.field_110321_bQ;
    }

    protected void func_110318_g() {}
}