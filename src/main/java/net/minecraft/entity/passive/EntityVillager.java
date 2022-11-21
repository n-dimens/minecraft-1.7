package net.minecraft.entity.passive;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Tuple;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityVillager extends EntityAgeable implements IMerchant, INpc
{
    private int field_70955_e;
    private boolean field_70952_f;
    private boolean field_70953_g;
    Village field_70954_d;
    private EntityPlayer field_70962_h;
    private MerchantRecipeList field_70963_i;
    private int field_70961_j;
    private boolean field_70959_by;
    private int field_70956_bz;
    private String field_82189_bL;
    private boolean field_82190_bM;
    private float field_82191_bN;
    public static final Map field_70958_bB = new HashMap();
    public static final Map field_70960_bC = new HashMap();
    private static final String __OBFID = "CL_00001707";

    public EntityVillager(World p_i1747_1_)
    {
        this(p_i1747_1_, 0);
    }

    public EntityVillager(World p_i1748_1_, int p_i1748_2_)
    {
        super(p_i1748_1_);
        this.func_70938_b(p_i1748_2_);
        this.func_70105_a(0.6F, 1.8F);
        this.func_70661_as().func_75498_b(true);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.field_70714_bg.func_75776_a(1, new EntityAITradePlayer(this));
        this.field_70714_bg.func_75776_a(1, new EntityAILookAtTradePlayer(this));
        this.field_70714_bg.func_75776_a(2, new EntityAIMoveIndoors(this));
        this.field_70714_bg.func_75776_a(3, new EntityAIRestrictOpenDoor(this));
        this.field_70714_bg.func_75776_a(4, new EntityAIOpenDoor(this, true));
        this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.field_70714_bg.func_75776_a(6, new EntityAIVillagerMate(this));
        this.field_70714_bg.func_75776_a(7, new EntityAIFollowGolem(this));
        this.field_70714_bg.func_75776_a(8, new EntityAIPlay(this, 0.32D));
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityVillager.class, 5.0F, 0.02F));
        this.field_70714_bg.func_75776_a(9, new EntityAIWander(this, 0.6D));
        this.field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
    }

    public boolean func_70650_aV()
    {
        return true;
    }

    protected void func_70629_bd()
    {
        if (--this.field_70955_e <= 0)
        {
            this.field_70170_p.field_72982_D.func_75551_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
            this.field_70955_e = 70 + this.field_70146_Z.nextInt(50);
            this.field_70954_d = this.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), 32);

            if (this.field_70954_d == null)
            {
                this.func_110177_bN();
            }
            else
            {
                ChunkCoordinates chunkcoordinates = this.field_70954_d.func_75577_a();
                this.func_110171_b(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c, (int)((float)this.field_70954_d.func_75568_b() * 0.6F));

                if (this.field_82190_bM)
                {
                    this.field_82190_bM = false;
                    this.field_70954_d.func_82683_b(5);
                }
            }
        }

        if (!this.func_70940_q() && this.field_70961_j > 0)
        {
            --this.field_70961_j;

            if (this.field_70961_j <= 0)
            {
                if (this.field_70959_by)
                {
                    if (this.field_70963_i.size() > 1)
                    {
                        Iterator iterator = this.field_70963_i.iterator();

                        while (iterator.hasNext())
                        {
                            MerchantRecipe merchantrecipe = (MerchantRecipe)iterator.next();

                            if (merchantrecipe.func_82784_g())
                            {
                                merchantrecipe.func_82783_a(this.field_70146_Z.nextInt(6) + this.field_70146_Z.nextInt(6) + 2);
                            }
                        }
                    }

                    this.func_70950_c(1);
                    this.field_70959_by = false;

                    if (this.field_70954_d != null && this.field_82189_bL != null)
                    {
                        this.field_70170_p.func_72960_a(this, (byte)14);
                        this.field_70954_d.func_82688_a(this.field_82189_bL, 1);
                    }
                }

                this.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 200, 0));
            }
        }

        super.func_70629_bd();
    }

    public boolean func_70085_c(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
        boolean flag = itemstack != null && itemstack.func_77973_b() == Items.field_151063_bx;

        if (!flag && this.func_70089_S() && !this.func_70940_q() && !this.func_70631_g_())
        {
            if (!this.field_70170_p.field_72995_K)
            {
                this.func_70932_a_(p_70085_1_);
                p_70085_1_.func_71030_a(this, this.func_94057_bL());
            }

            return true;
        }
        else
        {
            return super.func_70085_c(p_70085_1_);
        }
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74768_a("Profession", this.func_70946_n());
        p_70014_1_.func_74768_a("Riches", this.field_70956_bz);

        if (this.field_70963_i != null)
        {
            p_70014_1_.func_74782_a("Offers", this.field_70963_i.func_77202_a());
        }
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.func_70938_b(p_70037_1_.func_74762_e("Profession"));
        this.field_70956_bz = p_70037_1_.func_74762_e("Riches");

        if (p_70037_1_.func_150297_b("Offers", 10))
        {
            NBTTagCompound nbttagcompound1 = p_70037_1_.func_74775_l("Offers");
            this.field_70963_i = new MerchantRecipeList(nbttagcompound1);
        }
    }

    protected boolean func_70692_ba()
    {
        return false;
    }

    protected String func_70639_aQ()
    {
        return this.func_70940_q() ? "mob.villager.haggle" : "mob.villager.idle";
    }

    protected String func_70621_aR()
    {
        return "mob.villager.hit";
    }

    protected String func_70673_aS()
    {
        return "mob.villager.death";
    }

    public void func_70938_b(int p_70938_1_)
    {
        this.field_70180_af.func_75692_b(16, Integer.valueOf(p_70938_1_));
    }

    public int func_70946_n()
    {
        return this.field_70180_af.func_75679_c(16);
    }

    public boolean func_70941_o()
    {
        return this.field_70952_f;
    }

    public void func_70947_e(boolean p_70947_1_)
    {
        this.field_70952_f = p_70947_1_;
    }

    public void func_70939_f(boolean p_70939_1_)
    {
        this.field_70953_g = p_70939_1_;
    }

    public boolean func_70945_p()
    {
        return this.field_70953_g;
    }

    public void func_70604_c(EntityLivingBase p_70604_1_)
    {
        super.func_70604_c(p_70604_1_);

        if (this.field_70954_d != null && p_70604_1_ != null)
        {
            this.field_70954_d.func_75575_a(p_70604_1_);

            if (p_70604_1_ instanceof EntityPlayer)
            {
                byte b0 = -1;

                if (this.func_70631_g_())
                {
                    b0 = -3;
                }

                this.field_70954_d.func_82688_a(p_70604_1_.func_70005_c_(), b0);

                if (this.func_70089_S())
                {
                    this.field_70170_p.func_72960_a(this, (byte)13);
                }
            }
        }
    }

    public void func_70645_a(DamageSource p_70645_1_)
    {
        if (this.field_70954_d != null)
        {
            Entity entity = p_70645_1_.func_76346_g();

            if (entity != null)
            {
                if (entity instanceof EntityPlayer)
                {
                    this.field_70954_d.func_82688_a(entity.func_70005_c_(), -2);
                }
                else if (entity instanceof IMob)
                {
                    this.field_70954_d.func_82692_h();
                }
            }
            else if (entity == null)
            {
                EntityPlayer entityplayer = this.field_70170_p.func_72890_a(this, 16.0D);

                if (entityplayer != null)
                {
                    this.field_70954_d.func_82692_h();
                }
            }
        }

        super.func_70645_a(p_70645_1_);
    }

    public void func_70932_a_(EntityPlayer p_70932_1_)
    {
        this.field_70962_h = p_70932_1_;
    }

    public EntityPlayer func_70931_l_()
    {
        return this.field_70962_h;
    }

    public boolean func_70940_q()
    {
        return this.field_70962_h != null;
    }

    public void func_70933_a(MerchantRecipe p_70933_1_)
    {
        p_70933_1_.func_77399_f();
        this.field_70757_a = -this.func_70627_aG();
        this.func_85030_a("mob.villager.yes", this.func_70599_aP(), this.func_70647_i());

        if (p_70933_1_.func_77393_a((MerchantRecipe)this.field_70963_i.get(this.field_70963_i.size() - 1)))
        {
            this.field_70961_j = 40;
            this.field_70959_by = true;

            if (this.field_70962_h != null)
            {
                this.field_82189_bL = this.field_70962_h.func_70005_c_();
            }
            else
            {
                this.field_82189_bL = null;
            }
        }

        if (p_70933_1_.func_77394_a().func_77973_b() == Items.field_151166_bC)
        {
            this.field_70956_bz += p_70933_1_.func_77394_a().field_77994_a;
        }
    }

    public void func_110297_a_(ItemStack p_110297_1_)
    {
        if (!this.field_70170_p.field_72995_K && this.field_70757_a > -this.func_70627_aG() + 20)
        {
            this.field_70757_a = -this.func_70627_aG();

            if (p_110297_1_ != null)
            {
                this.func_85030_a("mob.villager.yes", this.func_70599_aP(), this.func_70647_i());
            }
            else
            {
                this.func_85030_a("mob.villager.no", this.func_70599_aP(), this.func_70647_i());
            }
        }
    }

    public MerchantRecipeList func_70934_b(EntityPlayer p_70934_1_)
    {
        if (this.field_70963_i == null)
        {
            this.func_70950_c(1);
        }

        return this.field_70963_i;
    }

    private float func_82188_j(float p_82188_1_)
    {
        float f1 = p_82188_1_ + this.field_82191_bN;
        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }

    private void func_70950_c(int p_70950_1_)
    {
        if (this.field_70963_i != null)
        {
            this.field_82191_bN = MathHelper.func_76129_c((float)this.field_70963_i.size()) * 0.2F;
        }
        else
        {
            this.field_82191_bN = 0.0F;
        }

        MerchantRecipeList merchantrecipelist;
        merchantrecipelist = new MerchantRecipeList();
        int k;
        label50:

        switch (this.func_70946_n())
        {
            case 0:
                func_146091_a(merchantrecipelist, Items.field_151015_O, this.field_70146_Z, this.func_82188_j(0.9F));
                func_146091_a(merchantrecipelist, Item.func_150898_a(Blocks.field_150325_L), this.field_70146_Z, this.func_82188_j(0.5F));
                func_146091_a(merchantrecipelist, Items.field_151076_bf, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146091_a(merchantrecipelist, Items.field_151101_aQ, this.field_70146_Z, this.func_82188_j(0.4F));
                func_146089_b(merchantrecipelist, Items.field_151025_P, this.field_70146_Z, this.func_82188_j(0.9F));
                func_146089_b(merchantrecipelist, Items.field_151127_ba, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151034_e, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151106_aX, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151097_aZ, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151033_d, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151077_bg, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151032_g, this.field_70146_Z, this.func_82188_j(0.5F));

                if (this.field_70146_Z.nextFloat() < this.func_82188_j(0.5F))
                {
                    merchantrecipelist.add(new MerchantRecipe(new ItemStack(Blocks.field_150351_n, 10), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151145_ak, 4 + this.field_70146_Z.nextInt(2), 0)));
                }

                break;
            case 1:
                func_146091_a(merchantrecipelist, Items.field_151121_aF, this.field_70146_Z, this.func_82188_j(0.8F));
                func_146091_a(merchantrecipelist, Items.field_151122_aG, this.field_70146_Z, this.func_82188_j(0.8F));
                func_146091_a(merchantrecipelist, Items.field_151164_bB, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Item.func_150898_a(Blocks.field_150342_X), this.field_70146_Z, this.func_82188_j(0.8F));
                func_146089_b(merchantrecipelist, Item.func_150898_a(Blocks.field_150359_w), this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151111_aL, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151113_aN, this.field_70146_Z, this.func_82188_j(0.2F));

                if (this.field_70146_Z.nextFloat() < this.func_82188_j(0.07F))
                {
                    Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
                    int i1 = MathHelper.func_76136_a(this.field_70146_Z, enchantment.func_77319_d(), enchantment.func_77325_b());
                    ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, i1));
                    k = 2 + this.field_70146_Z.nextInt(5 + i1 * 10) + 3 * i1;
                    merchantrecipelist.add(new MerchantRecipe(new ItemStack(Items.field_151122_aG), new ItemStack(Items.field_151166_bC, k), itemstack));
                }

                break;
            case 2:
                func_146089_b(merchantrecipelist, Items.field_151061_bv, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151062_by, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151137_ax, this.field_70146_Z, this.func_82188_j(0.4F));
                func_146089_b(merchantrecipelist, Item.func_150898_a(Blocks.field_150426_aN), this.field_70146_Z, this.func_82188_j(0.3F));
                Item[] aitem = new Item[] {Items.field_151040_l, Items.field_151048_u, Items.field_151030_Z, Items.field_151163_ad, Items.field_151036_c, Items.field_151056_x, Items.field_151035_b, Items.field_151046_w};
                Item[] aitem1 = aitem;
                int j = aitem.length;
                k = 0;

                while (true)
                {
                    if (k >= j)
                    {
                        break label50;
                    }

                    Item item = aitem1[k];

                    if (this.field_70146_Z.nextFloat() < this.func_82188_j(0.05F))
                    {
                        merchantrecipelist.add(new MerchantRecipe(new ItemStack(item, 1, 0), new ItemStack(Items.field_151166_bC, 2 + this.field_70146_Z.nextInt(3), 0), EnchantmentHelper.func_77504_a(this.field_70146_Z, new ItemStack(item, 1, 0), 5 + this.field_70146_Z.nextInt(15))));
                    }

                    ++k;
                }
            case 3:
                func_146091_a(merchantrecipelist, Items.field_151044_h, this.field_70146_Z, this.func_82188_j(0.7F));
                func_146091_a(merchantrecipelist, Items.field_151042_j, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146091_a(merchantrecipelist, Items.field_151043_k, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146091_a(merchantrecipelist, Items.field_151045_i, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146089_b(merchantrecipelist, Items.field_151040_l, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146089_b(merchantrecipelist, Items.field_151048_u, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146089_b(merchantrecipelist, Items.field_151036_c, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151056_x, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151035_b, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146089_b(merchantrecipelist, Items.field_151046_w, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146089_b(merchantrecipelist, Items.field_151037_a, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151047_v, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151019_K, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151012_L, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151167_ab, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151175_af, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151028_Y, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151161_ac, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151030_Z, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151163_ad, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151165_aa, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151173_ae, this.field_70146_Z, this.func_82188_j(0.2F));
                func_146089_b(merchantrecipelist, Items.field_151029_X, this.field_70146_Z, this.func_82188_j(0.1F));
                func_146089_b(merchantrecipelist, Items.field_151020_U, this.field_70146_Z, this.func_82188_j(0.1F));
                func_146089_b(merchantrecipelist, Items.field_151023_V, this.field_70146_Z, this.func_82188_j(0.1F));
                func_146089_b(merchantrecipelist, Items.field_151022_W, this.field_70146_Z, this.func_82188_j(0.1F));
                break;
            case 4:
                func_146091_a(merchantrecipelist, Items.field_151044_h, this.field_70146_Z, this.func_82188_j(0.7F));
                func_146091_a(merchantrecipelist, Items.field_151147_al, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146091_a(merchantrecipelist, Items.field_151082_bd, this.field_70146_Z, this.func_82188_j(0.5F));
                func_146089_b(merchantrecipelist, Items.field_151141_av, this.field_70146_Z, this.func_82188_j(0.1F));
                func_146089_b(merchantrecipelist, Items.field_151027_R, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151021_T, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151024_Q, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151026_S, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151157_am, this.field_70146_Z, this.func_82188_j(0.3F));
                func_146089_b(merchantrecipelist, Items.field_151083_be, this.field_70146_Z, this.func_82188_j(0.3F));
        }

        if (merchantrecipelist.isEmpty())
        {
            func_146091_a(merchantrecipelist, Items.field_151043_k, this.field_70146_Z, 1.0F);
        }

        Collections.shuffle(merchantrecipelist);

        if (this.field_70963_i == null)
        {
            this.field_70963_i = new MerchantRecipeList();
        }

        for (int l = 0; l < p_70950_1_ && l < merchantrecipelist.size(); ++l)
        {
            this.field_70963_i.func_77205_a((MerchantRecipe)merchantrecipelist.get(l));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_70930_a(MerchantRecipeList p_70930_1_) {}

    public static void func_146091_a(MerchantRecipeList p_146091_0_, Item p_146091_1_, Random p_146091_2_, float p_146091_3_)
    {
        if (p_146091_2_.nextFloat() < p_146091_3_)
        {
            p_146091_0_.add(new MerchantRecipe(func_146088_a(p_146091_1_, p_146091_2_), Items.field_151166_bC));
        }
    }

    private static ItemStack func_146088_a(Item p_146088_0_, Random p_146088_1_)
    {
        return new ItemStack(p_146088_0_, func_146092_b(p_146088_0_, p_146088_1_), 0);
    }

    private static int func_146092_b(Item p_146092_0_, Random p_146092_1_)
    {
        Tuple tuple = (Tuple)field_70958_bB.get(p_146092_0_);
        return tuple == null ? 1 : (((Integer)tuple.func_76341_a()).intValue() >= ((Integer)tuple.func_76340_b()).intValue() ? ((Integer)tuple.func_76341_a()).intValue() : ((Integer)tuple.func_76341_a()).intValue() + p_146092_1_.nextInt(((Integer)tuple.func_76340_b()).intValue() - ((Integer)tuple.func_76341_a()).intValue()));
    }

    public static void func_146089_b(MerchantRecipeList p_146089_0_, Item p_146089_1_, Random p_146089_2_, float p_146089_3_)
    {
        if (p_146089_2_.nextFloat() < p_146089_3_)
        {
            int i = func_146090_c(p_146089_1_, p_146089_2_);
            ItemStack itemstack;
            ItemStack itemstack1;

            if (i < 0)
            {
                itemstack = new ItemStack(Items.field_151166_bC, 1, 0);
                itemstack1 = new ItemStack(p_146089_1_, -i, 0);
            }
            else
            {
                itemstack = new ItemStack(Items.field_151166_bC, i, 0);
                itemstack1 = new ItemStack(p_146089_1_, 1, 0);
            }

            p_146089_0_.add(new MerchantRecipe(itemstack, itemstack1));
        }
    }

    private static int func_146090_c(Item p_146090_0_, Random p_146090_1_)
    {
        Tuple tuple = (Tuple)field_70960_bC.get(p_146090_0_);
        return tuple == null ? 1 : (((Integer)tuple.func_76341_a()).intValue() >= ((Integer)tuple.func_76340_b()).intValue() ? ((Integer)tuple.func_76341_a()).intValue() : ((Integer)tuple.func_76341_a()).intValue() + p_146090_1_.nextInt(((Integer)tuple.func_76340_b()).intValue() - ((Integer)tuple.func_76341_a()).intValue()));
    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte p_70103_1_)
    {
        if (p_70103_1_ == 12)
        {
            this.func_70942_a("heart");
        }
        else if (p_70103_1_ == 13)
        {
            this.func_70942_a("angryVillager");
        }
        else if (p_70103_1_ == 14)
        {
            this.func_70942_a("happyVillager");
        }
        else
        {
            super.func_70103_a(p_70103_1_);
        }
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.func_110161_a(p_110161_1_);
        this.func_70938_b(this.field_70170_p.field_73012_v.nextInt(5));
        return p_110161_1_;
    }

    @SideOnly(Side.CLIENT)
    private void func_70942_a(String p_70942_1_)
    {
        for (int i = 0; i < 5; ++i)
        {
            double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
            this.field_70170_p.func_72869_a(p_70942_1_, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 1.0D + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, d0, d1, d2);
        }
    }

    public void func_82187_q()
    {
        this.field_82190_bM = true;
    }

    public EntityVillager func_90011_a(EntityAgeable p_90011_1_)
    {
        EntityVillager entityvillager = new EntityVillager(this.field_70170_p);
        entityvillager.func_110161_a((IEntityLivingData)null);
        return entityvillager;
    }

    public boolean func_110164_bC()
    {
        return false;
    }

    static
    {
        field_70958_bB.put(Items.field_151044_h, new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
        field_70958_bB.put(Items.field_151042_j, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        field_70958_bB.put(Items.field_151043_k, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        field_70958_bB.put(Items.field_151045_i, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        field_70958_bB.put(Items.field_151121_aF, new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
        field_70958_bB.put(Items.field_151122_aG, new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
        field_70958_bB.put(Items.field_151164_bB, new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
        field_70958_bB.put(Items.field_151079_bi, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        field_70958_bB.put(Items.field_151061_bv, new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
        field_70958_bB.put(Items.field_151147_al, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        field_70958_bB.put(Items.field_151082_bd, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        field_70958_bB.put(Items.field_151076_bf, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        field_70958_bB.put(Items.field_151101_aQ, new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
        field_70958_bB.put(Items.field_151014_N, new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
        field_70958_bB.put(Items.field_151081_bc, new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        field_70958_bB.put(Items.field_151080_bb, new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        field_70958_bB.put(Items.field_151015_O, new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
        field_70958_bB.put(Item.func_150898_a(Blocks.field_150325_L), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
        field_70958_bB.put(Items.field_151078_bh, new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
        field_70960_bC.put(Items.field_151033_d, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        field_70960_bC.put(Items.field_151097_aZ, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        field_70960_bC.put(Items.field_151040_l, new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        field_70960_bC.put(Items.field_151048_u, new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
        field_70960_bC.put(Items.field_151036_c, new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        field_70960_bC.put(Items.field_151056_x, new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
        field_70960_bC.put(Items.field_151035_b, new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
        field_70960_bC.put(Items.field_151046_w, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        field_70960_bC.put(Items.field_151037_a, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        field_70960_bC.put(Items.field_151047_v, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        field_70960_bC.put(Items.field_151019_K, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        field_70960_bC.put(Items.field_151012_L, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        field_70960_bC.put(Items.field_151167_ab, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        field_70960_bC.put(Items.field_151175_af, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        field_70960_bC.put(Items.field_151028_Y, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        field_70960_bC.put(Items.field_151161_ac, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        field_70960_bC.put(Items.field_151030_Z, new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
        field_70960_bC.put(Items.field_151163_ad, new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
        field_70960_bC.put(Items.field_151165_aa, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        field_70960_bC.put(Items.field_151173_ae, new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
        field_70960_bC.put(Items.field_151029_X, new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        field_70960_bC.put(Items.field_151020_U, new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        field_70960_bC.put(Items.field_151023_V, new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
        field_70960_bC.put(Items.field_151022_W, new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
        field_70960_bC.put(Items.field_151025_P, new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        field_70960_bC.put(Items.field_151127_ba, new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        field_70960_bC.put(Items.field_151034_e, new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        field_70960_bC.put(Items.field_151106_aX, new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        field_70960_bC.put(Item.func_150898_a(Blocks.field_150359_w), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        field_70960_bC.put(Item.func_150898_a(Blocks.field_150342_X), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        field_70960_bC.put(Items.field_151027_R, new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
        field_70960_bC.put(Items.field_151021_T, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        field_70960_bC.put(Items.field_151024_Q, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        field_70960_bC.put(Items.field_151026_S, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        field_70960_bC.put(Items.field_151141_av, new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        field_70960_bC.put(Items.field_151062_by, new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        field_70960_bC.put(Items.field_151137_ax, new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        field_70960_bC.put(Items.field_151111_aL, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        field_70960_bC.put(Items.field_151113_aN, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        field_70960_bC.put(Item.func_150898_a(Blocks.field_150426_aN), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        field_70960_bC.put(Items.field_151157_am, new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        field_70960_bC.put(Items.field_151083_be, new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        field_70960_bC.put(Items.field_151077_bg, new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        field_70960_bC.put(Items.field_151061_bv, new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        field_70960_bC.put(Items.field_151032_g, new Tuple(Integer.valueOf(-12), Integer.valueOf(-8)));
    }
}