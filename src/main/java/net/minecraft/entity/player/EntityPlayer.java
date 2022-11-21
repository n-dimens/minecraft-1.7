package net.minecraft.entity.player;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.IChunkProvider;

public abstract class EntityPlayer extends EntityLivingBase implements ICommandSender
{
    public InventoryPlayer field_71071_by = new InventoryPlayer(this);
    private InventoryEnderChest field_71078_a = new InventoryEnderChest();
    public Container field_71069_bz;
    public Container field_71070_bA;
    protected FoodStats field_71100_bB = new FoodStats();
    protected int field_71101_bC;
    public float field_71107_bF;
    public float field_71109_bG;
    public int field_71090_bL;
    public double field_71091_bM;
    public double field_71096_bN;
    public double field_71097_bO;
    public double field_71094_bP;
    public double field_71095_bQ;
    public double field_71085_bR;
    protected boolean field_71083_bS;
    public ChunkCoordinates field_71081_bT;
    private int field_71076_b;
    public float field_71079_bU;
    @SideOnly(Side.CLIENT)
    public float field_71082_cx;
    public float field_71089_bV;
    private ChunkCoordinates field_71077_c;
    private boolean field_82248_d;
    private ChunkCoordinates field_71073_d;
    public PlayerCapabilities field_71075_bZ = new PlayerCapabilities();
    public int field_71068_ca;
    public int field_71067_cb;
    public float field_71106_cc;
    private ItemStack field_71074_e;
    private int field_71072_f;
    protected float field_71108_cd = 0.1F;
    protected float field_71102_ce = 0.02F;
    private int field_82249_h;
    private final GameProfile field_146106_i;
    public EntityFishHook field_71104_cf;
    private static final String __OBFID = "CL_00001711";

    public EntityPlayer(World p_i45324_1_, GameProfile p_i45324_2_)
    {
        super(p_i45324_1_);
        this.field_96093_i = func_146094_a(p_i45324_2_);
        this.field_146106_i = p_i45324_2_;
        this.field_71069_bz = new ContainerPlayer(this.field_71071_by, !p_i45324_1_.field_72995_K, this);
        this.field_71070_bA = this.field_71069_bz;
        this.field_70129_M = 1.62F;
        ChunkCoordinates chunkcoordinates = p_i45324_1_.func_72861_E();
        this.func_70012_b((double)chunkcoordinates.field_71574_a + 0.5D, (double)(chunkcoordinates.field_71572_b + 1), (double)chunkcoordinates.field_71573_c + 0.5D, 0.0F, 0.0F);
        this.field_70741_aB = 180.0F;
        this.field_70174_ab = 20;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
        this.field_70180_af.func_75682_a(17, Float.valueOf(0.0F));
        this.field_70180_af.func_75682_a(18, Integer.valueOf(0));
    }

    @SideOnly(Side.CLIENT)
    public ItemStack func_71011_bu()
    {
        return this.field_71074_e;
    }

    @SideOnly(Side.CLIENT)
    public int func_71052_bv()
    {
        return this.field_71072_f;
    }

    public boolean func_71039_bw()
    {
        return this.field_71074_e != null;
    }

    @SideOnly(Side.CLIENT)
    public int func_71057_bx()
    {
        return this.func_71039_bw() ? this.field_71074_e.func_77988_m() - this.field_71072_f : 0;
    }

    public void func_71034_by()
    {
        if (this.field_71074_e != null)
        {
            this.field_71074_e.func_77974_b(this.field_70170_p, this, this.field_71072_f);
        }

        this.func_71041_bz();
    }

    public void func_71041_bz()
    {
        this.field_71074_e = null;
        this.field_71072_f = 0;

        if (!this.field_70170_p.field_72995_K)
        {
            this.func_70019_c(false);
        }
    }

    public boolean func_70632_aY()
    {
        return this.func_71039_bw() && this.field_71074_e.func_77973_b().func_77661_b(this.field_71074_e) == EnumAction.block;
    }

    public void func_70071_h_()
    {
        if (this.field_71074_e != null)
        {
            ItemStack itemstack = this.field_71071_by.func_70448_g();

            if (itemstack == this.field_71074_e)
            {
                if (this.field_71072_f <= 25 && this.field_71072_f % 4 == 0)
                {
                    this.func_71010_c(itemstack, 5);
                }

                if (--this.field_71072_f == 0 && !this.field_70170_p.field_72995_K)
                {
                    this.func_71036_o();
                }
            }
            else
            {
                this.func_71041_bz();
            }
        }

        if (this.field_71090_bL > 0)
        {
            --this.field_71090_bL;
        }

        if (this.func_70608_bn())
        {
            ++this.field_71076_b;

            if (this.field_71076_b > 100)
            {
                this.field_71076_b = 100;
            }

            if (!this.field_70170_p.field_72995_K)
            {
                if (!this.func_71065_l())
                {
                    this.func_70999_a(true, true, false);
                }
                else if (this.field_70170_p.func_72935_r())
                {
                    this.func_70999_a(false, true, true);
                }
            }
        }
        else if (this.field_71076_b > 0)
        {
            ++this.field_71076_b;

            if (this.field_71076_b >= 110)
            {
                this.field_71076_b = 0;
            }
        }

        super.func_70071_h_();

        if (!this.field_70170_p.field_72995_K && this.field_71070_bA != null && !this.field_71070_bA.func_75145_c(this))
        {
            this.func_71053_j();
            this.field_71070_bA = this.field_71069_bz;
        }

        if (this.func_70027_ad() && this.field_71075_bZ.field_75102_a)
        {
            this.func_70066_B();
        }

        this.field_71091_bM = this.field_71094_bP;
        this.field_71096_bN = this.field_71095_bQ;
        this.field_71097_bO = this.field_71085_bR;
        double d3 = this.field_70165_t - this.field_71094_bP;
        double d0 = this.field_70163_u - this.field_71095_bQ;
        double d1 = this.field_70161_v - this.field_71085_bR;
        double d2 = 10.0D;

        if (d3 > d2)
        {
            this.field_71091_bM = this.field_71094_bP = this.field_70165_t;
        }

        if (d1 > d2)
        {
            this.field_71097_bO = this.field_71085_bR = this.field_70161_v;
        }

        if (d0 > d2)
        {
            this.field_71096_bN = this.field_71095_bQ = this.field_70163_u;
        }

        if (d3 < -d2)
        {
            this.field_71091_bM = this.field_71094_bP = this.field_70165_t;
        }

        if (d1 < -d2)
        {
            this.field_71097_bO = this.field_71085_bR = this.field_70161_v;
        }

        if (d0 < -d2)
        {
            this.field_71096_bN = this.field_71095_bQ = this.field_70163_u;
        }

        this.field_71094_bP += d3 * 0.25D;
        this.field_71085_bR += d1 * 0.25D;
        this.field_71095_bQ += d0 * 0.25D;

        if (this.field_70154_o == null)
        {
            this.field_71073_d = null;
        }

        if (!this.field_70170_p.field_72995_K)
        {
            this.field_71100_bB.func_75118_a(this);
            this.func_71064_a(StatList.field_75948_k, 1);
        }
    }

    public int func_82145_z()
    {
        return this.field_71075_bZ.field_75102_a ? 0 : 80;
    }

    protected String func_145776_H()
    {
        return "game.player.swim";
    }

    protected String func_145777_O()
    {
        return "game.player.swim.splash";
    }

    public int func_82147_ab()
    {
        return 10;
    }

    public void func_85030_a(String p_85030_1_, float p_85030_2_, float p_85030_3_)
    {
        this.field_70170_p.func_85173_a(this, p_85030_1_, p_85030_2_, p_85030_3_);
    }

    protected void func_71010_c(ItemStack p_71010_1_, int p_71010_2_)
    {
        if (p_71010_1_.func_77975_n() == EnumAction.drink)
        {
            this.func_85030_a("random.drink", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
        }

        if (p_71010_1_.func_77975_n() == EnumAction.eat)
        {
            for (int j = 0; j < p_71010_2_; ++j)
            {
                Vec3 vec3 = Vec3.func_72443_a(((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
                vec3.func_72440_a(-this.field_70125_A * (float)Math.PI / 180.0F);
                vec3.func_72442_b(-this.field_70177_z * (float)Math.PI / 180.0F);
                Vec3 vec31 = Vec3.func_72443_a(((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.3D, (double)(-this.field_70146_Z.nextFloat()) * 0.6D - 0.3D, 0.6D);
                vec31.func_72440_a(-this.field_70125_A * (float)Math.PI / 180.0F);
                vec31.func_72442_b(-this.field_70177_z * (float)Math.PI / 180.0F);
                vec31 = vec31.func_72441_c(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
                String s = "iconcrack_" + Item.func_150891_b(p_71010_1_.func_77973_b());

                if (p_71010_1_.func_77981_g())
                {
                    s = s + "_" + p_71010_1_.func_77960_j();
                }

                this.field_70170_p.func_72869_a(s, vec31.field_72450_a, vec31.field_72448_b, vec31.field_72449_c, vec3.field_72450_a, vec3.field_72448_b + 0.05D, vec3.field_72449_c);
            }

            this.func_85030_a("random.eat", 0.5F + 0.5F * (float)this.field_70146_Z.nextInt(2), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected void func_71036_o()
    {
        if (this.field_71074_e != null)
        {
            this.func_71010_c(this.field_71074_e, 16);
            int i = this.field_71074_e.field_77994_a;
            ItemStack itemstack = this.field_71074_e.func_77950_b(this.field_70170_p, this);

            if (itemstack != this.field_71074_e || itemstack != null && itemstack.field_77994_a != i)
            {
                this.field_71071_by.field_70462_a[this.field_71071_by.field_70461_c] = itemstack;

                if (itemstack.field_77994_a == 0)
                {
                    this.field_71071_by.field_70462_a[this.field_71071_by.field_70461_c] = null;
                }
            }

            this.func_71041_bz();
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte p_70103_1_)
    {
        if (p_70103_1_ == 9)
        {
            this.func_71036_o();
        }
        else
        {
            super.func_70103_a(p_70103_1_);
        }
    }

    protected boolean func_70610_aX()
    {
        return this.func_110143_aJ() <= 0.0F || this.func_70608_bn();
    }

    public void func_71053_j()
    {
        this.field_71070_bA = this.field_71069_bz;
    }

    public void func_70078_a(Entity p_70078_1_)
    {
        if (this.field_70154_o != null && p_70078_1_ == null)
        {
            if (!this.field_70170_p.field_72995_K)
            {
                this.func_110145_l(this.field_70154_o);
            }

            if (this.field_70154_o != null)
            {
                this.field_70154_o.field_70153_n = null;
            }

            this.field_70154_o = null;
        }
        else
        {
            super.func_70078_a(p_70078_1_);
        }
    }

    public void func_70098_U()
    {
        if (!this.field_70170_p.field_72995_K && this.func_70093_af())
        {
            this.func_70078_a((Entity)null);
            this.func_70095_a(false);
        }
        else
        {
            double d0 = this.field_70165_t;
            double d1 = this.field_70163_u;
            double d2 = this.field_70161_v;
            float f = this.field_70177_z;
            float f1 = this.field_70125_A;
            super.func_70098_U();
            this.field_71107_bF = this.field_71109_bG;
            this.field_71109_bG = 0.0F;
            this.func_71015_k(this.field_70165_t - d0, this.field_70163_u - d1, this.field_70161_v - d2);

            if (this.field_70154_o instanceof EntityPig)
            {
                this.field_70125_A = f1;
                this.field_70177_z = f;
                this.field_70761_aq = ((EntityPig)this.field_70154_o).field_70761_aq;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_70065_x()
    {
        this.field_70129_M = 1.62F;
        this.func_70105_a(0.6F, 1.8F);
        super.func_70065_x();
        this.func_70606_j(this.func_110138_aP());
        this.field_70725_aQ = 0;
    }

    protected void func_70626_be()
    {
        super.func_70626_be();
        this.func_82168_bl();
    }

    public void func_70636_d()
    {
        if (this.field_71101_bC > 0)
        {
            --this.field_71101_bC;
        }

        if (this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL && this.func_110143_aJ() < this.func_110138_aP() && this.field_70170_p.func_82736_K().func_82766_b("naturalRegeneration") && this.field_70173_aa % 20 * 12 == 0)
        {
            this.func_70691_i(1.0F);
        }

        this.field_71071_by.func_70429_k();
        this.field_71107_bF = this.field_71109_bG;
        super.func_70636_d();
        IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);

        if (!this.field_70170_p.field_72995_K)
        {
            iattributeinstance.func_111128_a((double)this.field_71075_bZ.func_75094_b());
        }

        this.field_70747_aH = this.field_71102_ce;

        if (this.func_70051_ag())
        {
            this.field_70747_aH = (float)((double)this.field_70747_aH + (double)this.field_71102_ce * 0.3D);
        }

        this.func_70659_e((float)iattributeinstance.func_111126_e());
        float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        float f1 = (float)Math.atan(-this.field_70181_x * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F)
        {
            f = 0.1F;
        }

        if (!this.field_70122_E || this.func_110143_aJ() <= 0.0F)
        {
            f = 0.0F;
        }

        if (this.field_70122_E || this.func_110143_aJ() <= 0.0F)
        {
            f1 = 0.0F;
        }

        this.field_71109_bG += (f - this.field_71109_bG) * 0.4F;
        this.field_70726_aT += (f1 - this.field_70726_aT) * 0.8F;

        if (this.func_110143_aJ() > 0.0F)
        {
            AxisAlignedBB axisalignedbb = null;

            if (this.field_70154_o != null && !this.field_70154_o.field_70128_L)
            {
                axisalignedbb = this.field_70121_D.func_111270_a(this.field_70154_o.field_70121_D).func_72314_b(1.0D, 0.0D, 1.0D);
            }
            else
            {
                axisalignedbb = this.field_70121_D.func_72314_b(1.0D, 0.5D, 1.0D);
            }

            List list = this.field_70170_p.func_72839_b(this, axisalignedbb);

            if (list != null)
            {
                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity = (Entity)list.get(i);

                    if (!entity.field_70128_L)
                    {
                        this.func_71044_o(entity);
                    }
                }
            }
        }
    }

    private void func_71044_o(Entity p_71044_1_)
    {
        p_71044_1_.func_70100_b_(this);
    }

    public int func_71037_bA()
    {
        return this.field_70180_af.func_75679_c(18);
    }

    public void func_85040_s(int p_85040_1_)
    {
        this.field_70180_af.func_75692_b(18, Integer.valueOf(p_85040_1_));
    }

    public void func_85039_t(int p_85039_1_)
    {
        int j = this.func_71037_bA();
        this.field_70180_af.func_75692_b(18, Integer.valueOf(j + p_85039_1_));
    }

    public void func_70645_a(DamageSource p_70645_1_)
    {
        super.func_70645_a(p_70645_1_);
        this.func_70105_a(0.2F, 0.2F);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70181_x = 0.10000000149011612D;

        if (this.func_70005_c_().equals("Notch"))
        {
            this.func_146097_a(new ItemStack(Items.field_151034_e, 1), true, false);
        }

        if (!this.field_70170_p.func_82736_K().func_82766_b("keepInventory"))
        {
            this.field_71071_by.func_70436_m();
        }

        if (p_70645_1_ != null)
        {
            this.field_70159_w = (double)(-MathHelper.func_76134_b((this.field_70739_aP + this.field_70177_z) * (float)Math.PI / 180.0F) * 0.1F);
            this.field_70179_y = (double)(-MathHelper.func_76126_a((this.field_70739_aP + this.field_70177_z) * (float)Math.PI / 180.0F) * 0.1F);
        }
        else
        {
            this.field_70159_w = this.field_70179_y = 0.0D;
        }

        this.field_70129_M = 0.1F;
        this.func_71064_a(StatList.field_75960_y, 1);
    }

    protected String func_70621_aR()
    {
        return "game.player.hurt";
    }

    protected String func_70673_aS()
    {
        return "game.player.die";
    }

    public void func_70084_c(Entity p_70084_1_, int p_70084_2_)
    {
        this.func_85039_t(p_70084_2_);
        Collection collection = this.func_96123_co().func_96520_a(IScoreObjectiveCriteria.field_96640_e);

        if (p_70084_1_ instanceof EntityPlayer)
        {
            this.func_71064_a(StatList.field_75932_A, 1);
            collection.addAll(this.func_96123_co().func_96520_a(IScoreObjectiveCriteria.field_96639_d));
        }
        else
        {
            this.func_71064_a(StatList.field_75959_z, 1);
        }

        Iterator iterator = collection.iterator();

        while (iterator.hasNext())
        {
            ScoreObjective scoreobjective = (ScoreObjective)iterator.next();
            Score score = this.func_96123_co().func_96529_a(this.func_70005_c_(), scoreobjective);
            score.func_96648_a();
        }
    }

    public EntityItem func_71040_bB(boolean p_71040_1_)
    {
        return this.func_146097_a(this.field_71071_by.func_70298_a(this.field_71071_by.field_70461_c, p_71040_1_ && this.field_71071_by.func_70448_g() != null ? this.field_71071_by.func_70448_g().field_77994_a : 1), false, true);
    }

    public EntityItem func_71019_a(ItemStack p_71019_1_, boolean p_71019_2_)
    {
        return this.func_146097_a(p_71019_1_, false, false);
    }

    public EntityItem func_146097_a(ItemStack p_146097_1_, boolean p_146097_2_, boolean p_146097_3_)
    {
        if (p_146097_1_ == null)
        {
            return null;
        }
        else if (p_146097_1_.field_77994_a == 0)
        {
            return null;
        }
        else
        {
            EntityItem entityitem = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u - 0.30000001192092896D + (double)this.func_70047_e(), this.field_70161_v, p_146097_1_);
            entityitem.field_145804_b = 40;

            if (p_146097_3_)
            {
                entityitem.func_145799_b(this.func_70005_c_());
            }

            float f = 0.1F;
            float f1;

            if (p_146097_2_)
            {
                f1 = this.field_70146_Z.nextFloat() * 0.5F;
                float f2 = this.field_70146_Z.nextFloat() * (float)Math.PI * 2.0F;
                entityitem.field_70159_w = (double)(-MathHelper.func_76126_a(f2) * f1);
                entityitem.field_70179_y = (double)(MathHelper.func_76134_b(f2) * f1);
                entityitem.field_70181_x = 0.20000000298023224D;
            }
            else
            {
                f = 0.3F;
                entityitem.field_70159_w = (double)(-MathHelper.func_76126_a(this.field_70177_z / 180.0F * (float)Math.PI) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float)Math.PI) * f);
                entityitem.field_70179_y = (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * (float)Math.PI) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float)Math.PI) * f);
                entityitem.field_70181_x = (double)(-MathHelper.func_76126_a(this.field_70125_A / 180.0F * (float)Math.PI) * f + 0.1F);
                f = 0.02F;
                f1 = this.field_70146_Z.nextFloat() * (float)Math.PI * 2.0F;
                f *= this.field_70146_Z.nextFloat();
                entityitem.field_70159_w += Math.cos((double)f1) * (double)f;
                entityitem.field_70181_x += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F);
                entityitem.field_70179_y += Math.sin((double)f1) * (double)f;
            }

            this.func_71012_a(entityitem);
            this.func_71064_a(StatList.field_75952_v, 1);
            return entityitem;
        }
    }

    public void func_71012_a(EntityItem p_71012_1_)
    {
        this.field_70170_p.func_72838_d(p_71012_1_);
    }

    public float func_146096_a(Block p_146096_1_, boolean p_146096_2_)
    {
        float f = this.field_71071_by.func_146023_a(p_146096_1_);

        if (f > 1.0F)
        {
            int i = EnchantmentHelper.func_77509_b(this);
            ItemStack itemstack = this.field_71071_by.func_70448_g();

            if (i > 0 && itemstack != null)
            {
                float f1 = (float)(i * i + 1);

                if (!itemstack.func_150998_b(p_146096_1_) && f <= 1.0F)
                {
                    f += f1 * 0.08F;
                }
                else
                {
                    f += f1;
                }
            }
        }

        if (this.func_70644_a(Potion.field_76422_e))
        {
            f *= 1.0F + (float)(this.func_70660_b(Potion.field_76422_e).func_76458_c() + 1) * 0.2F;
        }

        if (this.func_70644_a(Potion.field_76419_f))
        {
            f *= 1.0F - (float)(this.func_70660_b(Potion.field_76419_f).func_76458_c() + 1) * 0.2F;
        }

        if (this.func_70055_a(Material.field_151586_h) && !EnchantmentHelper.func_77510_g(this))
        {
            f /= 5.0F;
        }

        if (!this.field_70122_E)
        {
            f /= 5.0F;
        }

        return f;
    }

    public boolean func_146099_a(Block p_146099_1_)
    {
        return this.field_71071_by.func_146025_b(p_146099_1_);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.field_96093_i = func_146094_a(this.field_146106_i);
        NBTTagList nbttaglist = p_70037_1_.func_150295_c("Inventory", 10);
        this.field_71071_by.func_70443_b(nbttaglist);
        this.field_71071_by.field_70461_c = p_70037_1_.func_74762_e("SelectedItemSlot");
        this.field_71083_bS = p_70037_1_.func_74767_n("Sleeping");
        this.field_71076_b = p_70037_1_.func_74765_d("SleepTimer");
        this.field_71106_cc = p_70037_1_.func_74760_g("XpP");
        this.field_71068_ca = p_70037_1_.func_74762_e("XpLevel");
        this.field_71067_cb = p_70037_1_.func_74762_e("XpTotal");
        this.func_85040_s(p_70037_1_.func_74762_e("Score"));

        if (this.field_71083_bS)
        {
            this.field_71081_bT = new ChunkCoordinates(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
            this.func_70999_a(true, true, false);
        }

        if (p_70037_1_.func_150297_b("SpawnX", 99) && p_70037_1_.func_150297_b("SpawnY", 99) && p_70037_1_.func_150297_b("SpawnZ", 99))
        {
            this.field_71077_c = new ChunkCoordinates(p_70037_1_.func_74762_e("SpawnX"), p_70037_1_.func_74762_e("SpawnY"), p_70037_1_.func_74762_e("SpawnZ"));
            this.field_82248_d = p_70037_1_.func_74767_n("SpawnForced");
        }

        this.field_71100_bB.func_75112_a(p_70037_1_);
        this.field_71075_bZ.func_75095_b(p_70037_1_);

        if (p_70037_1_.func_150297_b("EnderItems", 9))
        {
            NBTTagList nbttaglist1 = p_70037_1_.func_150295_c("EnderItems", 10);
            this.field_71078_a.func_70486_a(nbttaglist1);
        }
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74782_a("Inventory", this.field_71071_by.func_70442_a(new NBTTagList()));
        p_70014_1_.func_74768_a("SelectedItemSlot", this.field_71071_by.field_70461_c);
        p_70014_1_.func_74757_a("Sleeping", this.field_71083_bS);
        p_70014_1_.func_74777_a("SleepTimer", (short)this.field_71076_b);
        p_70014_1_.func_74776_a("XpP", this.field_71106_cc);
        p_70014_1_.func_74768_a("XpLevel", this.field_71068_ca);
        p_70014_1_.func_74768_a("XpTotal", this.field_71067_cb);
        p_70014_1_.func_74768_a("Score", this.func_71037_bA());

        if (this.field_71077_c != null)
        {
            p_70014_1_.func_74768_a("SpawnX", this.field_71077_c.field_71574_a);
            p_70014_1_.func_74768_a("SpawnY", this.field_71077_c.field_71572_b);
            p_70014_1_.func_74768_a("SpawnZ", this.field_71077_c.field_71573_c);
            p_70014_1_.func_74757_a("SpawnForced", this.field_82248_d);
        }

        this.field_71100_bB.func_75117_b(p_70014_1_);
        this.field_71075_bZ.func_75091_a(p_70014_1_);
        p_70014_1_.func_74782_a("EnderItems", this.field_71078_a.func_70487_g());
    }

    public void func_71007_a(IInventory p_71007_1_) {}

    public void func_146093_a(TileEntityHopper p_146093_1_) {}

    public void func_96125_a(EntityMinecartHopper p_96125_1_) {}

    public void func_110298_a(EntityHorse p_110298_1_, IInventory p_110298_2_) {}

    public void func_71002_c(int p_71002_1_, int p_71002_2_, int p_71002_3_, String p_71002_4_) {}

    public void func_82244_d(int p_82244_1_, int p_82244_2_, int p_82244_3_) {}

    public void func_71058_b(int p_71058_1_, int p_71058_2_, int p_71058_3_) {}

    public float func_70047_e()
    {
        return 0.12F;
    }

    protected void func_71061_d_()
    {
        this.field_70129_M = 1.62F;
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else if (this.field_71075_bZ.field_75102_a && !p_70097_1_.func_76357_e())
        {
            return false;
        }
        else
        {
            this.field_70708_bq = 0;

            if (this.func_110143_aJ() <= 0.0F)
            {
                return false;
            }
            else
            {
                if (this.func_70608_bn() && !this.field_70170_p.field_72995_K)
                {
                    this.func_70999_a(true, true, false);
                }

                if (p_70097_1_.func_76350_n())
                {
                    if (this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL)
                    {
                        p_70097_2_ = 0.0F;
                    }

                    if (this.field_70170_p.field_73013_u == EnumDifficulty.EASY)
                    {
                        p_70097_2_ = p_70097_2_ / 2.0F + 1.0F;
                    }

                    if (this.field_70170_p.field_73013_u == EnumDifficulty.HARD)
                    {
                        p_70097_2_ = p_70097_2_ * 3.0F / 2.0F;
                    }
                }

                if (p_70097_2_ == 0.0F)
                {
                    return false;
                }
                else
                {
                    Entity entity = p_70097_1_.func_76346_g();

                    if (entity instanceof EntityArrow && ((EntityArrow)entity).field_70250_c != null)
                    {
                        entity = ((EntityArrow)entity).field_70250_c;
                    }

                    this.func_71064_a(StatList.field_75961_x, Math.round(p_70097_2_ * 10.0F));
                    return super.func_70097_a(p_70097_1_, p_70097_2_);
                }
            }
        }
    }

    public boolean func_96122_a(EntityPlayer p_96122_1_)
    {
        Team team = this.func_96124_cp();
        Team team1 = p_96122_1_.func_96124_cp();
        return team == null ? true : (!team.func_142054_a(team1) ? true : team.func_96665_g());
    }

    protected void func_70675_k(float p_70675_1_)
    {
        this.field_71071_by.func_70449_g(p_70675_1_);
    }

    public int func_70658_aO()
    {
        return this.field_71071_by.func_70430_l();
    }

    public float func_82243_bO()
    {
        int i = 0;
        ItemStack[] aitemstack = this.field_71071_by.field_70460_b;
        int j = aitemstack.length;

        for (int k = 0; k < j; ++k)
        {
            ItemStack itemstack = aitemstack[k];

            if (itemstack != null)
            {
                ++i;
            }
        }

        return (float)i / (float)this.field_71071_by.field_70460_b.length;
    }

    protected void func_70665_d(DamageSource p_70665_1_, float p_70665_2_)
    {
        if (!this.func_85032_ar())
        {
            if (!p_70665_1_.func_76363_c() && this.func_70632_aY() && p_70665_2_ > 0.0F)
            {
                p_70665_2_ = (1.0F + p_70665_2_) * 0.5F;
            }

            p_70665_2_ = this.func_70655_b(p_70665_1_, p_70665_2_);
            p_70665_2_ = this.func_70672_c(p_70665_1_, p_70665_2_);
            float f1 = p_70665_2_;
            p_70665_2_ = Math.max(p_70665_2_ - this.func_110139_bj(), 0.0F);
            this.func_110149_m(this.func_110139_bj() - (f1 - p_70665_2_));

            if (p_70665_2_ != 0.0F)
            {
                this.func_71020_j(p_70665_1_.func_76345_d());
                float f2 = this.func_110143_aJ();
                this.func_70606_j(this.func_110143_aJ() - p_70665_2_);
                this.func_110142_aN().func_94547_a(p_70665_1_, f2, p_70665_2_);
            }
        }
    }

    public void func_146101_a(TileEntityFurnace p_146101_1_) {}

    public void func_146102_a(TileEntityDispenser p_146102_1_) {}

    public void func_146100_a(TileEntity p_146100_1_) {}

    public void func_146095_a(CommandBlockLogic p_146095_1_) {}

    public void func_146098_a(TileEntityBrewingStand p_146098_1_) {}

    public void func_146104_a(TileEntityBeacon p_146104_1_) {}

    public void func_71030_a(IMerchant p_71030_1_, String p_71030_2_) {}

    public void func_71048_c(ItemStack p_71048_1_) {}

    public boolean func_70998_m(Entity p_70998_1_)
    {
        ItemStack itemstack = this.func_71045_bC();
        ItemStack itemstack1 = itemstack != null ? itemstack.func_77946_l() : null;

        if (!p_70998_1_.func_130002_c(this))
        {
            if (itemstack != null && p_70998_1_ instanceof EntityLivingBase)
            {
                if (this.field_71075_bZ.field_75098_d)
                {
                    itemstack = itemstack1;
                }

                if (itemstack.func_111282_a(this, (EntityLivingBase)p_70998_1_))
                {
                    if (itemstack.field_77994_a <= 0 && !this.field_71075_bZ.field_75098_d)
                    {
                        this.func_71028_bD();
                    }

                    return true;
                }
            }

            return false;
        }
        else
        {
            if (itemstack != null && itemstack == this.func_71045_bC())
            {
                if (itemstack.field_77994_a <= 0 && !this.field_71075_bZ.field_75098_d)
                {
                    this.func_71028_bD();
                }
                else if (itemstack.field_77994_a < itemstack1.field_77994_a && this.field_71075_bZ.field_75098_d)
                {
                    itemstack.field_77994_a = itemstack1.field_77994_a;
                }
            }

            return true;
        }
    }

    public ItemStack func_71045_bC()
    {
        return this.field_71071_by.func_70448_g();
    }

    public void func_71028_bD()
    {
        this.field_71071_by.func_70299_a(this.field_71071_by.field_70461_c, (ItemStack)null);
    }

    public double func_70033_W()
    {
        return (double)(this.field_70129_M - 0.5F);
    }

    public void func_71059_n(Entity p_71059_1_)
    {
        if (p_71059_1_.func_70075_an())
        {
            if (!p_71059_1_.func_85031_j(this))
            {
                float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
                int i = 0;
                float f1 = 0.0F;

                if (p_71059_1_ instanceof EntityLivingBase)
                {
                    f1 = EnchantmentHelper.func_77512_a(this, (EntityLivingBase)p_71059_1_);
                    i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)p_71059_1_);
                }

                if (this.func_70051_ag())
                {
                    ++i;
                }

                if (f > 0.0F || f1 > 0.0F)
                {
                    boolean flag = this.field_70143_R > 0.0F && !this.field_70122_E && !this.func_70617_f_() && !this.func_70090_H() && !this.func_70644_a(Potion.field_76440_q) && this.field_70154_o == null && p_71059_1_ instanceof EntityLivingBase;

                    if (flag && f > 0.0F)
                    {
                        f *= 1.5F;
                    }

                    f += f1;
                    boolean flag1 = false;
                    int j = EnchantmentHelper.func_90036_a(this);

                    if (p_71059_1_ instanceof EntityLivingBase && j > 0 && !p_71059_1_.func_70027_ad())
                    {
                        flag1 = true;
                        p_71059_1_.func_70015_d(1);
                    }

                    boolean flag2 = p_71059_1_.func_70097_a(DamageSource.func_76365_a(this), f);

                    if (flag2)
                    {
                        if (i > 0)
                        {
                            p_71059_1_.func_70024_g((double)(-MathHelper.func_76126_a(this.field_70177_z * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.func_76134_b(this.field_70177_z * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                            this.field_70159_w *= 0.6D;
                            this.field_70179_y *= 0.6D;
                            this.func_70031_b(false);
                        }

                        if (flag)
                        {
                            this.func_71009_b(p_71059_1_);
                        }

                        if (f1 > 0.0F)
                        {
                            this.func_71047_c(p_71059_1_);
                        }

                        if (f >= 18.0F)
                        {
                            this.func_71029_a(AchievementList.OVERKILL);
                        }

                        this.func_130011_c(p_71059_1_);

                        if (p_71059_1_ instanceof EntityLivingBase)
                        {
                            EnchantmentHelper.func_151384_a((EntityLivingBase)p_71059_1_, this);
                        }

                        EnchantmentHelper.func_151385_b(this, p_71059_1_);
                        ItemStack itemstack = this.func_71045_bC();
                        Object object = p_71059_1_;

                        if (p_71059_1_ instanceof EntityDragonPart)
                        {
                            IEntityMultiPart ientitymultipart = ((EntityDragonPart)p_71059_1_).field_70259_a;

                            if (ientitymultipart != null && ientitymultipart instanceof EntityLivingBase)
                            {
                                object = (EntityLivingBase)ientitymultipart;
                            }
                        }

                        if (itemstack != null && object instanceof EntityLivingBase)
                        {
                            itemstack.func_77961_a((EntityLivingBase)object, this);

                            if (itemstack.field_77994_a <= 0)
                            {
                                this.func_71028_bD();
                            }
                        }

                        if (p_71059_1_ instanceof EntityLivingBase)
                        {
                            this.func_71064_a(StatList.field_75951_w, Math.round(f * 10.0F));

                            if (j > 0)
                            {
                                p_71059_1_.func_70015_d(j * 4);
                            }
                        }

                        this.func_71020_j(0.3F);
                    }
                    else if (flag1)
                    {
                        p_71059_1_.func_70066_B();
                    }
                }
            }
        }
    }

    public void func_71009_b(Entity p_71009_1_) {}

    public void func_71047_c(Entity p_71047_1_) {}

    @SideOnly(Side.CLIENT)
    public void func_71004_bE() {}

    public void func_70106_y()
    {
        super.func_70106_y();
        this.field_71069_bz.func_75134_a(this);

        if (this.field_71070_bA != null)
        {
            this.field_71070_bA.func_75134_a(this);
        }
    }

    public boolean func_70094_T()
    {
        return !this.field_71083_bS && super.func_70094_T();
    }

    public GameProfile func_146103_bH()
    {
        return this.field_146106_i;
    }

    public EntityPlayer.EnumStatus func_71018_a(int p_71018_1_, int p_71018_2_, int p_71018_3_)
    {
        if (!this.field_70170_p.field_72995_K)
        {
            if (this.func_70608_bn() || !this.func_70089_S())
            {
                return EntityPlayer.EnumStatus.OTHER_PROBLEM;
            }

            if (!this.field_70170_p.field_73011_w.func_76569_d())
            {
                return EntityPlayer.EnumStatus.NOT_POSSIBLE_HERE;
            }

            if (this.field_70170_p.func_72935_r())
            {
                return EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW;
            }

            if (Math.abs(this.field_70165_t - (double)p_71018_1_) > 3.0D || Math.abs(this.field_70163_u - (double)p_71018_2_) > 2.0D || Math.abs(this.field_70161_v - (double)p_71018_3_) > 3.0D)
            {
                return EntityPlayer.EnumStatus.TOO_FAR_AWAY;
            }

            double d0 = 8.0D;
            double d1 = 5.0D;
            List list = this.field_70170_p.func_72872_a(EntityMob.class, AxisAlignedBB.func_72330_a((double)p_71018_1_ - d0, (double)p_71018_2_ - d1, (double)p_71018_3_ - d0, (double)p_71018_1_ + d0, (double)p_71018_2_ + d1, (double)p_71018_3_ + d0));

            if (!list.isEmpty())
            {
                return EntityPlayer.EnumStatus.NOT_SAFE;
            }
        }

        if (this.func_70115_ae())
        {
            this.func_70078_a((Entity)null);
        }

        this.func_70105_a(0.2F, 0.2F);
        this.field_70129_M = 0.2F;

        if (this.field_70170_p.func_72899_e(p_71018_1_, p_71018_2_, p_71018_3_))
        {
            int i1 = this.field_70170_p.func_72805_g(p_71018_1_, p_71018_2_, p_71018_3_);
            int l = BlockBed.func_149895_l(i1);
            float f1 = 0.5F;
            float f = 0.5F;

            switch (l)
            {
                case 0:
                    f = 0.9F;
                    break;
                case 1:
                    f1 = 0.1F;
                    break;
                case 2:
                    f = 0.1F;
                    break;
                case 3:
                    f1 = 0.9F;
            }

            this.func_71013_b(l);
            this.func_70107_b((double)((float)p_71018_1_ + f1), (double)((float)p_71018_2_ + 0.9375F), (double)((float)p_71018_3_ + f));
        }
        else
        {
            this.func_70107_b((double)((float)p_71018_1_ + 0.5F), (double)((float)p_71018_2_ + 0.9375F), (double)((float)p_71018_3_ + 0.5F));
        }

        this.field_71083_bS = true;
        this.field_71076_b = 0;
        this.field_71081_bT = new ChunkCoordinates(p_71018_1_, p_71018_2_, p_71018_3_);
        this.field_70159_w = this.field_70179_y = this.field_70181_x = 0.0D;

        if (!this.field_70170_p.field_72995_K)
        {
            this.field_70170_p.func_72854_c();
        }

        return EntityPlayer.EnumStatus.OK;
    }

    private void func_71013_b(int p_71013_1_)
    {
        this.field_71079_bU = 0.0F;
        this.field_71089_bV = 0.0F;

        switch (p_71013_1_)
        {
            case 0:
                this.field_71089_bV = -1.8F;
                break;
            case 1:
                this.field_71079_bU = 1.8F;
                break;
            case 2:
                this.field_71089_bV = 1.8F;
                break;
            case 3:
                this.field_71079_bU = -1.8F;
        }
    }

    public void func_70999_a(boolean p_70999_1_, boolean p_70999_2_, boolean p_70999_3_)
    {
        this.func_70105_a(0.6F, 1.8F);
        this.func_71061_d_();
        ChunkCoordinates chunkcoordinates = this.field_71081_bT;
        ChunkCoordinates chunkcoordinates1 = this.field_71081_bT;

        if (chunkcoordinates != null && this.field_70170_p.func_147439_a(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c) == Blocks.field_150324_C)
        {
            BlockBed.func_149979_a(this.field_70170_p, chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c, false);
            chunkcoordinates1 = BlockBed.func_149977_a(this.field_70170_p, chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c, 0);

            if (chunkcoordinates1 == null)
            {
                chunkcoordinates1 = new ChunkCoordinates(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b + 1, chunkcoordinates.field_71573_c);
            }

            this.func_70107_b((double)((float)chunkcoordinates1.field_71574_a + 0.5F), (double)((float)chunkcoordinates1.field_71572_b + this.field_70129_M + 0.1F), (double)((float)chunkcoordinates1.field_71573_c + 0.5F));
        }

        this.field_71083_bS = false;

        if (!this.field_70170_p.field_72995_K && p_70999_2_)
        {
            this.field_70170_p.func_72854_c();
        }

        if (p_70999_1_)
        {
            this.field_71076_b = 0;
        }
        else
        {
            this.field_71076_b = 100;
        }

        if (p_70999_3_)
        {
            this.func_71063_a(this.field_71081_bT, false);
        }
    }

    private boolean func_71065_l()
    {
        return this.field_70170_p.func_147439_a(this.field_71081_bT.field_71574_a, this.field_71081_bT.field_71572_b, this.field_71081_bT.field_71573_c) == Blocks.field_150324_C;
    }

    public static ChunkCoordinates func_71056_a(World p_71056_0_, ChunkCoordinates p_71056_1_, boolean p_71056_2_)
    {
        IChunkProvider ichunkprovider = p_71056_0_.func_72863_F();
        ichunkprovider.func_73158_c(p_71056_1_.field_71574_a - 3 >> 4, p_71056_1_.field_71573_c - 3 >> 4);
        ichunkprovider.func_73158_c(p_71056_1_.field_71574_a + 3 >> 4, p_71056_1_.field_71573_c - 3 >> 4);
        ichunkprovider.func_73158_c(p_71056_1_.field_71574_a - 3 >> 4, p_71056_1_.field_71573_c + 3 >> 4);
        ichunkprovider.func_73158_c(p_71056_1_.field_71574_a + 3 >> 4, p_71056_1_.field_71573_c + 3 >> 4);

        if (p_71056_0_.func_147439_a(p_71056_1_.field_71574_a, p_71056_1_.field_71572_b, p_71056_1_.field_71573_c) == Blocks.field_150324_C)
        {
            ChunkCoordinates chunkcoordinates1 = BlockBed.func_149977_a(p_71056_0_, p_71056_1_.field_71574_a, p_71056_1_.field_71572_b, p_71056_1_.field_71573_c, 0);
            return chunkcoordinates1;
        }
        else
        {
            Material material = p_71056_0_.func_147439_a(p_71056_1_.field_71574_a, p_71056_1_.field_71572_b, p_71056_1_.field_71573_c).func_149688_o();
            Material material1 = p_71056_0_.func_147439_a(p_71056_1_.field_71574_a, p_71056_1_.field_71572_b + 1, p_71056_1_.field_71573_c).func_149688_o();
            boolean flag1 = !material.func_76220_a() && !material.func_76224_d();
            boolean flag2 = !material1.func_76220_a() && !material1.func_76224_d();
            return p_71056_2_ && flag1 && flag2 ? p_71056_1_ : null;
        }
    }

    @SideOnly(Side.CLIENT)
    public float func_71051_bG()
    {
        if (this.field_71081_bT != null)
        {
            int i = this.field_70170_p.func_72805_g(this.field_71081_bT.field_71574_a, this.field_71081_bT.field_71572_b, this.field_71081_bT.field_71573_c);
            int j = BlockBed.func_149895_l(i);

            switch (j)
            {
                case 0:
                    return 90.0F;
                case 1:
                    return 0.0F;
                case 2:
                    return 270.0F;
                case 3:
                    return 180.0F;
            }
        }

        return 0.0F;
    }

    public boolean func_70608_bn()
    {
        return this.field_71083_bS;
    }

    public boolean func_71026_bH()
    {
        return this.field_71083_bS && this.field_71076_b >= 100;
    }

    @SideOnly(Side.CLIENT)
    public int func_71060_bI()
    {
        return this.field_71076_b;
    }

    @SideOnly(Side.CLIENT)
    protected boolean func_82241_s(int p_82241_1_)
    {
        return (this.field_70180_af.func_75683_a(16) & 1 << p_82241_1_) != 0;
    }

    protected void func_82239_b(int p_82239_1_, boolean p_82239_2_)
    {
        byte b0 = this.field_70180_af.func_75683_a(16);

        if (p_82239_2_)
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 1 << p_82239_1_)));
        }
        else
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & ~(1 << p_82239_1_))));
        }
    }

    public void func_146105_b(IChatComponent p_146105_1_) {}

    public ChunkCoordinates func_70997_bJ()
    {
        return this.field_71077_c;
    }

    public boolean func_82245_bX()
    {
        return this.field_82248_d;
    }

    public void func_71063_a(ChunkCoordinates p_71063_1_, boolean p_71063_2_)
    {
        if (p_71063_1_ != null)
        {
            this.field_71077_c = new ChunkCoordinates(p_71063_1_);
            this.field_82248_d = p_71063_2_;
        }
        else
        {
            this.field_71077_c = null;
            this.field_82248_d = false;
        }
    }

    public void func_71029_a(StatBase p_71029_1_)
    {
        this.func_71064_a(p_71029_1_, 1);
    }

    public void func_71064_a(StatBase p_71064_1_, int p_71064_2_) {}

    public void func_70664_aZ()
    {
        super.func_70664_aZ();
        this.func_71064_a(StatList.field_75953_u, 1);

        if (this.func_70051_ag())
        {
            this.func_71020_j(0.8F);
        }
        else
        {
            this.func_71020_j(0.2F);
        }
    }

    public void func_70612_e(float p_70612_1_, float p_70612_2_)
    {
        double d0 = this.field_70165_t;
        double d1 = this.field_70163_u;
        double d2 = this.field_70161_v;

        if (this.field_71075_bZ.field_75100_b && this.field_70154_o == null)
        {
            double d3 = this.field_70181_x;
            float f2 = this.field_70747_aH;
            this.field_70747_aH = this.field_71075_bZ.func_75093_a();
            super.func_70612_e(p_70612_1_, p_70612_2_);
            this.field_70181_x = d3 * 0.6D;
            this.field_70747_aH = f2;
        }
        else
        {
            super.func_70612_e(p_70612_1_, p_70612_2_);
        }

        this.func_71000_j(this.field_70165_t - d0, this.field_70163_u - d1, this.field_70161_v - d2);
    }

    public float func_70689_ay()
    {
        return (float)this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e();
    }

    public void func_71000_j(double p_71000_1_, double p_71000_3_, double p_71000_5_)
    {
        if (this.field_70154_o == null)
        {
            int i;

            if (this.func_70055_a(Material.field_151586_h))
            {
                i = Math.round(MathHelper.func_76133_a(p_71000_1_ * p_71000_1_ + p_71000_3_ * p_71000_3_ + p_71000_5_ * p_71000_5_) * 100.0F);

                if (i > 0)
                {
                    this.func_71064_a(StatList.field_75957_q, i);
                    this.func_71020_j(0.015F * (float)i * 0.01F);
                }
            }
            else if (this.func_70090_H())
            {
                i = Math.round(MathHelper.func_76133_a(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);

                if (i > 0)
                {
                    this.func_71064_a(StatList.field_75946_m, i);
                    this.func_71020_j(0.015F * (float)i * 0.01F);
                }
            }
            else if (this.func_70617_f_())
            {
                if (p_71000_3_ > 0.0D)
                {
                    this.func_71064_a(StatList.field_75944_o, (int)Math.round(p_71000_3_ * 100.0D));
                }
            }
            else if (this.field_70122_E)
            {
                i = Math.round(MathHelper.func_76133_a(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);

                if (i > 0)
                {
                    this.func_71064_a(StatList.field_75945_l, i);

                    if (this.func_70051_ag())
                    {
                        this.func_71020_j(0.099999994F * (float)i * 0.01F);
                    }
                    else
                    {
                        this.func_71020_j(0.01F * (float)i * 0.01F);
                    }
                }
            }
            else
            {
                i = Math.round(MathHelper.func_76133_a(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);

                if (i > 25)
                {
                    this.func_71064_a(StatList.field_75958_p, i);
                }
            }
        }
    }

    private void func_71015_k(double p_71015_1_, double p_71015_3_, double p_71015_5_)
    {
        if (this.field_70154_o != null)
        {
            int i = Math.round(MathHelper.func_76133_a(p_71015_1_ * p_71015_1_ + p_71015_3_ * p_71015_3_ + p_71015_5_ * p_71015_5_) * 100.0F);

            if (i > 0)
            {
                if (this.field_70154_o instanceof EntityMinecart)
                {
                    this.func_71064_a(StatList.field_75956_r, i);

                    if (this.field_71073_d == null)
                    {
                        this.field_71073_d = new ChunkCoordinates(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
                    }
                    else if ((double)this.field_71073_d.func_71569_e(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)) >= 1000000.0D)
                    {
                        this.func_71064_a(AchievementList.ON_A_RAIL, 1);
                    }
                }
                else if (this.field_70154_o instanceof EntityBoat)
                {
                    this.func_71064_a(StatList.field_75955_s, i);
                }
                else if (this.field_70154_o instanceof EntityPig)
                {
                    this.func_71064_a(StatList.field_75954_t, i);
                }
                else if (this.field_70154_o instanceof EntityHorse)
                {
                    this.func_71064_a(StatList.field_151185_q, i);
                }
            }
        }
    }

    protected void func_70069_a(float p_70069_1_)
    {
        if (!this.field_71075_bZ.field_75101_c)
        {
            if (p_70069_1_ >= 2.0F)
            {
                this.func_71064_a(StatList.field_75943_n, (int)Math.round((double)p_70069_1_ * 100.0D));
            }

            super.func_70069_a(p_70069_1_);
        }
    }

    protected String func_146067_o(int p_146067_1_)
    {
        return p_146067_1_ > 4 ? "game.player.hurt.fall.big" : "game.player.hurt.fall.small";
    }

    public void func_70074_a(EntityLivingBase p_70074_1_)
    {
        if (p_70074_1_ instanceof IMob)
        {
            this.func_71029_a(AchievementList.KILL_ENEMY);
        }

        int i = EntityList.func_75619_a(p_70074_1_);
        EntityList.EntityEggInfo entityegginfo = (EntityList.EntityEggInfo)EntityList.field_75627_a.get(Integer.valueOf(i));

        if (entityegginfo != null)
        {
            this.func_71064_a(entityegginfo.field_151512_d, 1);
        }
    }

    public void func_70110_aj()
    {
        if (!this.field_71075_bZ.field_75100_b)
        {
            super.func_70110_aj();
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_70620_b(ItemStack p_70620_1_, int p_70620_2_)
    {
        IIcon iicon = super.func_70620_b(p_70620_1_, p_70620_2_);

        if (p_70620_1_.func_77973_b() == Items.field_151112_aM && this.field_71104_cf != null)
        {
            iicon = Items.field_151112_aM.func_94597_g();
        }
        else
        {
            if (p_70620_1_.func_77973_b().func_77623_v())
            {
                return p_70620_1_.func_77973_b().func_77618_c(p_70620_1_.func_77960_j(), p_70620_2_);
            }

            if (this.field_71074_e != null && p_70620_1_.func_77973_b() == Items.field_151031_f)
            {
                int j = p_70620_1_.func_77988_m() - this.field_71072_f;

                if (j >= 18)
                {
                    return Items.field_151031_f.func_94599_c(2);
                }

                if (j > 13)
                {
                    return Items.field_151031_f.func_94599_c(1);
                }

                if (j > 0)
                {
                    return Items.field_151031_f.func_94599_c(0);
                }
            }
        }

        return iicon;
    }

    public ItemStack func_82169_q(int p_82169_1_)
    {
        return this.field_71071_by.func_70440_f(p_82169_1_);
    }

    public void func_71023_q(int p_71023_1_)
    {
        this.func_85039_t(p_71023_1_);
        int j = Integer.MAX_VALUE - this.field_71067_cb;

        if (p_71023_1_ > j)
        {
            p_71023_1_ = j;
        }

        this.field_71106_cc += (float)p_71023_1_ / (float)this.func_71050_bK();

        for (this.field_71067_cb += p_71023_1_; this.field_71106_cc >= 1.0F; this.field_71106_cc /= (float)this.func_71050_bK())
        {
            this.field_71106_cc = (this.field_71106_cc - 1.0F) * (float)this.func_71050_bK();
            this.func_82242_a(1);
        }
    }

    public void func_82242_a(int p_82242_1_)
    {
        this.field_71068_ca += p_82242_1_;

        if (this.field_71068_ca < 0)
        {
            this.field_71068_ca = 0;
            this.field_71106_cc = 0.0F;
            this.field_71067_cb = 0;
        }

        if (p_82242_1_ > 0 && this.field_71068_ca % 5 == 0 && (float)this.field_82249_h < (float)this.field_70173_aa - 100.0F)
        {
            float f = this.field_71068_ca > 30 ? 1.0F : (float)this.field_71068_ca / 30.0F;
            this.field_70170_p.func_72956_a(this, "random.levelup", f * 0.75F, 1.0F);
            this.field_82249_h = this.field_70173_aa;
        }
    }

    public int func_71050_bK()
    {
        return this.field_71068_ca >= 30 ? 62 + (this.field_71068_ca - 30) * 7 : (this.field_71068_ca >= 15 ? 17 + (this.field_71068_ca - 15) * 3 : 17);
    }

    public void func_71020_j(float p_71020_1_)
    {
        if (!this.field_71075_bZ.field_75102_a)
        {
            if (!this.field_70170_p.field_72995_K)
            {
                this.field_71100_bB.func_75113_a(p_71020_1_);
            }
        }
    }

    public FoodStats func_71024_bL()
    {
        return this.field_71100_bB;
    }

    public boolean func_71043_e(boolean p_71043_1_)
    {
        return (p_71043_1_ || this.field_71100_bB.func_75121_c()) && !this.field_71075_bZ.field_75102_a;
    }

    public boolean func_70996_bM()
    {
        return this.func_110143_aJ() > 0.0F && this.func_110143_aJ() < this.func_110138_aP();
    }

    public void func_71008_a(ItemStack p_71008_1_, int p_71008_2_)
    {
        if (p_71008_1_ != this.field_71074_e)
        {
            this.field_71074_e = p_71008_1_;
            this.field_71072_f = p_71008_2_;

            if (!this.field_70170_p.field_72995_K)
            {
                this.func_70019_c(true);
            }
        }
    }

    public boolean func_82246_f(int p_82246_1_, int p_82246_2_, int p_82246_3_)
    {
        if (this.field_71075_bZ.field_75099_e)
        {
            return true;
        }
        else
        {
            Block block = this.field_70170_p.func_147439_a(p_82246_1_, p_82246_2_, p_82246_3_);

            if (block.func_149688_o() != Material.field_151579_a)
            {
                if (block.func_149688_o().func_85157_q())
                {
                    return true;
                }

                if (this.func_71045_bC() != null)
                {
                    ItemStack itemstack = this.func_71045_bC();

                    if (itemstack.func_150998_b(block) || itemstack.func_150997_a(block) > 1.0F)
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean func_82247_a(int p_82247_1_, int p_82247_2_, int p_82247_3_, int p_82247_4_, ItemStack p_82247_5_)
    {
        return this.field_71075_bZ.field_75099_e ? true : (p_82247_5_ != null ? p_82247_5_.func_82835_x() : false);
    }

    protected int func_70693_a(EntityPlayer p_70693_1_)
    {
        if (this.field_70170_p.func_82736_K().func_82766_b("keepInventory"))
        {
            return 0;
        }
        else
        {
            int i = this.field_71068_ca * 7;
            return i > 100 ? 100 : i;
        }
    }

    protected boolean func_70684_aJ()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_94059_bO()
    {
        return true;
    }

    public void func_71049_a(EntityPlayer p_71049_1_, boolean p_71049_2_)
    {
        if (p_71049_2_)
        {
            this.field_71071_by.func_70455_b(p_71049_1_.field_71071_by);
            this.func_70606_j(p_71049_1_.func_110143_aJ());
            this.field_71100_bB = p_71049_1_.field_71100_bB;
            this.field_71068_ca = p_71049_1_.field_71068_ca;
            this.field_71067_cb = p_71049_1_.field_71067_cb;
            this.field_71106_cc = p_71049_1_.field_71106_cc;
            this.func_85040_s(p_71049_1_.func_71037_bA());
            this.field_82152_aq = p_71049_1_.field_82152_aq;
        }
        else if (this.field_70170_p.func_82736_K().func_82766_b("keepInventory"))
        {
            this.field_71071_by.func_70455_b(p_71049_1_.field_71071_by);
            this.field_71068_ca = p_71049_1_.field_71068_ca;
            this.field_71067_cb = p_71049_1_.field_71067_cb;
            this.field_71106_cc = p_71049_1_.field_71106_cc;
            this.func_85040_s(p_71049_1_.func_71037_bA());
        }

        this.field_71078_a = p_71049_1_.field_71078_a;
    }

    protected boolean func_70041_e_()
    {
        return !this.field_71075_bZ.field_75100_b;
    }

    public void func_71016_p() {}

    public void func_71033_a(WorldSettings.GameType p_71033_1_) {}

    public String func_70005_c_()
    {
        return this.field_146106_i.getName();
    }

    public World func_130014_f_()
    {
        return this.field_70170_p;
    }

    public InventoryEnderChest func_71005_bN()
    {
        return this.field_71078_a;
    }

    public ItemStack func_71124_b(int p_71124_1_)
    {
        return p_71124_1_ == 0 ? this.field_71071_by.func_70448_g() : this.field_71071_by.field_70460_b[p_71124_1_ - 1];
    }

    public ItemStack func_70694_bm()
    {
        return this.field_71071_by.func_70448_g();
    }

    public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_)
    {
        this.field_71071_by.field_70460_b[p_70062_1_] = p_70062_2_;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_98034_c(EntityPlayer p_98034_1_)
    {
        if (!this.func_82150_aj())
        {
            return false;
        }
        else
        {
            Team team = this.func_96124_cp();
            return team == null || p_98034_1_ == null || p_98034_1_.func_96124_cp() != team || !team.func_98297_h();
        }
    }

    public ItemStack[] func_70035_c()
    {
        return this.field_71071_by.field_70460_b;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_82238_cc()
    {
        return this.func_82241_s(1);
    }

    public boolean func_96092_aw()
    {
        return !this.field_71075_bZ.field_75100_b;
    }

    public Scoreboard func_96123_co()
    {
        return this.field_70170_p.func_96441_U();
    }

    public Team func_96124_cp()
    {
        return this.func_96123_co().func_96509_i(this.func_70005_c_());
    }

    public IChatComponent func_145748_c_()
    {
        ChatComponentText chatcomponenttext = new ChatComponentText(ScorePlayerTeam.func_96667_a(this.func_96124_cp(), this.func_70005_c_()));
        chatcomponenttext.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + this.func_70005_c_() + " "));
        return chatcomponenttext;
    }

    public void func_110149_m(float p_110149_1_)
    {
        if (p_110149_1_ < 0.0F)
        {
            p_110149_1_ = 0.0F;
        }

        this.func_70096_w().func_75692_b(17, Float.valueOf(p_110149_1_));
    }

    public float func_110139_bj()
    {
        return this.func_70096_w().func_111145_d(17);
    }

    public static UUID func_146094_a(GameProfile p_146094_0_)
    {
        UUID uuid = p_146094_0_.getId();

        if (uuid == null)
        {
            uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + p_146094_0_.getName()).getBytes(Charsets.UTF_8));
        }

        return uuid;
    }

    public static enum EnumChatVisibility
    {
        FULL(0, "options.chat.visibility.full"),
        SYSTEM(1, "options.chat.visibility.system"),
        HIDDEN(2, "options.chat.visibility.hidden");
        private static final EntityPlayer.EnumChatVisibility[] field_151432_d = new EntityPlayer.EnumChatVisibility[values().length];
        private final int field_151433_e;
        private final String field_151430_f;

        private static final String __OBFID = "CL_00001714";

        private EnumChatVisibility(int p_i45323_3_, String p_i45323_4_)
        {
            this.field_151433_e = p_i45323_3_;
            this.field_151430_f = p_i45323_4_;
        }

        public int func_151428_a()
        {
            return this.field_151433_e;
        }

        public static EntityPlayer.EnumChatVisibility func_151426_a(int p_151426_0_)
        {
            return field_151432_d[p_151426_0_ % field_151432_d.length];
        }

        @SideOnly(Side.CLIENT)
        public String func_151429_b()
        {
            return this.field_151430_f;
        }

        static
        {
            EntityPlayer.EnumChatVisibility[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2)
            {
                EntityPlayer.EnumChatVisibility var3 = var0[var2];
                field_151432_d[var3.field_151433_e] = var3;
            }
        }
    }

    public static enum EnumStatus
    {
        OK,
        NOT_POSSIBLE_HERE,
        NOT_POSSIBLE_NOW,
        TOO_FAR_AWAY,
        OTHER_PROBLEM,
        NOT_SAFE;

        private static final String __OBFID = "CL_00001712";
    }
}