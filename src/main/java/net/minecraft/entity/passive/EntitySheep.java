package net.minecraft.entity.passive;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySheep extends EntityAnimal
{
    private final InventoryCrafting field_90016_e = new InventoryCrafting(new Container()
    {
        private static final String __OBFID = "CL_00001649";
        @Override
        public boolean func_75145_c(EntityPlayer player)
        {
            return false;
        }
    }, 2, 1);
    public static final float[][] field_70898_d = new float[][] {{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};
    private int field_70899_e;
    private final EntityAIEatGrass eatGrassTask = new EntityAIEatGrass(this);
    private static final String __OBFID = "CL_00001648";

    public EntitySheep(World world)
    {
        super(world);
        this.func_70105_a(0.9F, 1.3F);
        this.func_70661_as().func_75491_a(true);
        this.aiTasks.func_75776_a(0, new EntityAISwimming(this));
        this.aiTasks.func_75776_a(1, new EntityAIPanic(this, 1.25D));
        this.aiTasks.func_75776_a(2, new EntityAIMate(this, 1.0D));
        this.aiTasks.func_75776_a(3, new EntityAITempt(this, 1.1D, Items.WHEAT, false));
        this.aiTasks.func_75776_a(4, new EntityAIFollowParent(this, 1.1D));
        this.aiTasks.func_75776_a(5, this.eatGrassTask);
        this.aiTasks.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.aiTasks.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.aiTasks.func_75776_a(8, new EntityAILookIdle(this));
        this.field_90016_e.putItem(0, new ItemStack(Items.DYE, 1, 0));
        this.field_90016_e.putItem(1, new ItemStack(Items.DYE, 1, 0));
    }

    protected boolean func_70650_aV()
    {
        return true;
    }

    protected void func_70619_bc()
    {
        this.field_70899_e = this.eatGrassTask.func_151499_f();
        super.func_70619_bc();
    }

    @Override
    public void func_70636_d()
    {
        if (this.world.field_72995_K)
        {
            this.field_70899_e = Math.max(0, this.field_70899_e - 1);
        }

        super.func_70636_d();
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
    }

    @Override
    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, new Byte((byte)0));
    }

    @Override
    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        if (!this.func_70892_o())
        {
            this.func_70099_a(new ItemStack(Item.func_150898_a(Blocks.WOOL), 1, this.func_70896_n()), 0.0F);
        }
    }

    @Override
    protected Item droppingItem()
    {
        return Item.func_150898_a(Blocks.WOOL);
    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte p_70103_1_)
    {
        if (p_70103_1_ == 10)
        {
            this.field_70899_e = 40;
        }
        else
        {
            super.func_70103_a(p_70103_1_);
        }
    }

    @Override
    public boolean resultOfImpact(EntityPlayer player)
    {
        ItemStack activeItemStack = player.inventory.getActiveItem();

        if (activeItemStack != null && activeItemStack.getBaseItem() == Items.SHEARS && !this.func_70892_o() && !this.func_70631_g_())
        {
            if (!this.world.field_72995_K)
            {
                this.func_70893_e(true);
                int i = 1 + this.randomizer.nextInt(3);

                for (int j = 0; j < i; ++j)
                {
                    EntityItem entityitem = this.func_70099_a(new ItemStack(Item.func_150898_a(Blocks.WOOL), 1, this.func_70896_n()), 1.0F);
                    entityitem.field_70181_x += (double)(this.randomizer.nextFloat() * 0.05F);
                    entityitem.field_70159_w += (double)((this.randomizer.nextFloat() - this.randomizer.nextFloat()) * 0.1F);
                    entityitem.field_70179_y += (double)((this.randomizer.nextFloat() - this.randomizer.nextFloat()) * 0.1F);
                }
            }

            activeItemStack.func_77972_a(1, player);
            this.func_85030_a("mob.sheep.shear", 1.0F, 1.0F);
        }

        return super.resultOfImpact(player);
    }

    @SideOnly(Side.CLIENT)
    public float func_70894_j(float p_70894_1_)
    {
        return this.field_70899_e <= 0 ? 0.0F : (this.field_70899_e >= 4 && this.field_70899_e <= 36 ? 1.0F : (this.field_70899_e < 4 ? ((float)this.field_70899_e - p_70894_1_) / 4.0F : -((float)(this.field_70899_e - 40) - p_70894_1_) / 4.0F));
    }

    @SideOnly(Side.CLIENT)
    public float func_70890_k(float p_70890_1_)
    {
        if (this.field_70899_e > 4 && this.field_70899_e <= 36)
        {
            float f1 = ((float)(this.field_70899_e - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.func_76126_a(f1 * 28.7F);
        }
        else
        {
            return this.field_70899_e > 0 ? ((float)Math.PI / 5F) : this.field_70125_A / (180F / (float)Math.PI);
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74757_a("Sheared", this.func_70892_o());
        p_70014_1_.func_74774_a("Color", (byte)this.func_70896_n());
    }

    @Override
    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.func_70893_e(p_70037_1_.func_74767_n("Sheared"));
        this.func_70891_b(p_70037_1_.func_74771_c("Color"));
    }

    @Override
    protected String func_70639_aQ()
    {
        return "mob.sheep.say";
    }

    protected String func_70621_aR()
    {
        return "mob.sheep.say";
    }

    protected String func_70673_aS()
    {
        return "mob.sheep.say";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.func_85030_a("mob.sheep.step", 0.15F, 1.0F);
    }

    public int func_70896_n()
    {
        return this.field_70180_af.func_75683_a(16) & 15;
    }

    public void func_70891_b(int p_70891_1_)
    {
        byte b0 = this.field_70180_af.func_75683_a(16);
        this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 240 | p_70891_1_ & 15)));
    }

    public boolean func_70892_o()
    {
        return (this.field_70180_af.func_75683_a(16) & 16) != 0;
    }

    public void func_70893_e(boolean p_70893_1_)
    {
        byte b0 = this.field_70180_af.func_75683_a(16);

        if (p_70893_1_)
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 16)));
        }
        else
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & -17)));
        }
    }

    public static int func_70895_a(Random p_70895_0_)
    {
        int i = p_70895_0_.nextInt(100);
        return i < 5 ? 15 : (i < 10 ? 7 : (i < 15 ? 8 : (i < 18 ? 12 : (p_70895_0_.nextInt(500) == 0 ? 6 : 0))));
    }

    public EntitySheep func_90011_a(EntityAgeable p_90011_1_)
    {
        EntitySheep entitysheep = (EntitySheep)p_90011_1_;
        EntitySheep entitysheep1 = new EntitySheep(this.world);
        int i = this.func_90014_a(this, entitysheep);
        entitysheep1.func_70891_b(15 - i);
        return entitysheep1;
    }

    public void func_70615_aA()
    {
        this.func_70893_e(false);

        if (this.func_70631_g_())
        {
            this.func_110195_a(60);
        }
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.func_110161_a(p_110161_1_);
        this.func_70891_b(func_70895_a(this.world.field_73012_v));
        return p_110161_1_;
    }

    private int func_90014_a(EntitySheep sheep, EntitySheep p_90014_2_)
    {
        int i = this.func_90013_b(sheep);
        int j = this.func_90013_b(p_90014_2_);
        this.field_90016_e.func_70301_a(0).func_77964_b(i);
        this.field_90016_e.func_70301_a(1).func_77964_b(j);
        ItemStack itemstack = CraftingManager.getInstance().func_82787_a(this.field_90016_e, sheep.world);
        int k;

        if (itemstack != null && itemstack.getBaseItem() == Items.DYE)
        {
            k = itemstack.func_77960_j();
        }
        else
        {
            k = this.world.field_73012_v.nextBoolean() ? i : j;
        }

        return k;
    }

    private int func_90013_b(EntitySheep sheep)
    {
        return 15 - sheep.func_70896_n();
    }
}