package net.minecraft.server.management;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;

public class ItemInWorldManager
{
    public World field_73092_a;
    public EntityPlayerMP field_73090_b;
    private WorldSettings.GameType field_73091_c;
    private boolean field_73088_d;
    private int field_73089_e;
    private int field_73086_f;
    private int field_73087_g;
    private int field_73099_h;
    private int field_73100_i;
    private boolean field_73097_j;
    private int field_73098_k;
    private int field_73095_l;
    private int field_73096_m;
    private int field_73093_n;
    private int field_73094_o;
    private static final String __OBFID = "CL_00001442";

    public ItemInWorldManager(World p_i1524_1_)
    {
        this.field_73091_c = WorldSettings.GameType.NOT_SET;
        this.field_73094_o = -1;
        this.field_73092_a = p_i1524_1_;
    }

    public void func_73076_a(WorldSettings.GameType p_73076_1_)
    {
        this.field_73091_c = p_73076_1_;
        p_73076_1_.func_77147_a(this.field_73090_b.capabilities);
        this.field_73090_b.func_71016_p();
    }

    public WorldSettings.GameType func_73081_b()
    {
        return this.field_73091_c;
    }

    public boolean func_73083_d()
    {
        return this.field_73091_c.func_77145_d();
    }

    public void func_73077_b(WorldSettings.GameType p_73077_1_)
    {
        if (this.field_73091_c == WorldSettings.GameType.NOT_SET)
        {
            this.field_73091_c = p_73077_1_;
        }

        this.func_73076_a(this.field_73091_c);
    }

    public void func_73075_a()
    {
        ++this.field_73100_i;
        float f;
        int j;

        if (this.field_73097_j)
        {
            int i = this.field_73100_i - this.field_73093_n;
            Block block = this.field_73092_a.func_147439_a(this.field_73098_k, this.field_73095_l, this.field_73096_m);

            if (block.func_149688_o() == Material.field_151579_a)
            {
                this.field_73097_j = false;
            }
            else
            {
                f = block.func_149737_a(this.field_73090_b, this.field_73090_b.world, this.field_73098_k, this.field_73095_l, this.field_73096_m) * (float)(i + 1);
                j = (int)(f * 10.0F);

                if (j != this.field_73094_o)
                {
                    this.field_73092_a.func_147443_d(this.field_73090_b.func_145782_y(), this.field_73098_k, this.field_73095_l, this.field_73096_m, j);
                    this.field_73094_o = j;
                }

                if (f >= 1.0F)
                {
                    this.field_73097_j = false;
                    this.func_73084_b(this.field_73098_k, this.field_73095_l, this.field_73096_m);
                }
            }
        }
        else if (this.field_73088_d)
        {
            Block block1 = this.field_73092_a.func_147439_a(this.field_73086_f, this.field_73087_g, this.field_73099_h);

            if (block1.func_149688_o() == Material.field_151579_a)
            {
                this.field_73092_a.func_147443_d(this.field_73090_b.func_145782_y(), this.field_73086_f, this.field_73087_g, this.field_73099_h, -1);
                this.field_73094_o = -1;
                this.field_73088_d = false;
            }
            else
            {
                int k = this.field_73100_i - this.field_73089_e;
                f = block1.func_149737_a(this.field_73090_b, this.field_73090_b.world, this.field_73086_f, this.field_73087_g, this.field_73099_h) * (float)(k + 1);
                j = (int)(f * 10.0F);

                if (j != this.field_73094_o)
                {
                    this.field_73092_a.func_147443_d(this.field_73090_b.func_145782_y(), this.field_73086_f, this.field_73087_g, this.field_73099_h, j);
                    this.field_73094_o = j;
                }
            }
        }
    }

    public void func_73074_a(int p_73074_1_, int p_73074_2_, int p_73074_3_, int p_73074_4_)
    {
        if (!this.field_73091_c.func_82752_c() || this.field_73090_b.func_82246_f(p_73074_1_, p_73074_2_, p_73074_3_))
        {
            if (this.func_73083_d())
            {
                if (!this.field_73092_a.func_72886_a((EntityPlayer)null, p_73074_1_, p_73074_2_, p_73074_3_, p_73074_4_))
                {
                    this.func_73084_b(p_73074_1_, p_73074_2_, p_73074_3_);
                }
            }
            else
            {
                this.field_73092_a.func_72886_a((EntityPlayer)null, p_73074_1_, p_73074_2_, p_73074_3_, p_73074_4_);
                this.field_73089_e = this.field_73100_i;
                float f = 1.0F;
                Block block = this.field_73092_a.func_147439_a(p_73074_1_, p_73074_2_, p_73074_3_);

                if (block.func_149688_o() != Material.field_151579_a)
                {
                    block.func_149699_a(this.field_73092_a, p_73074_1_, p_73074_2_, p_73074_3_, this.field_73090_b);
                    f = block.func_149737_a(this.field_73090_b, this.field_73090_b.world, p_73074_1_, p_73074_2_, p_73074_3_);
                }

                if (block.func_149688_o() != Material.field_151579_a && f >= 1.0F)
                {
                    this.func_73084_b(p_73074_1_, p_73074_2_, p_73074_3_);
                }
                else
                {
                    this.field_73088_d = true;
                    this.field_73086_f = p_73074_1_;
                    this.field_73087_g = p_73074_2_;
                    this.field_73099_h = p_73074_3_;
                    int i1 = (int)(f * 10.0F);
                    this.field_73092_a.func_147443_d(this.field_73090_b.func_145782_y(), p_73074_1_, p_73074_2_, p_73074_3_, i1);
                    this.field_73094_o = i1;
                }
            }
        }
    }

    public void func_73082_a(int p_73082_1_, int p_73082_2_, int p_73082_3_)
    {
        if (p_73082_1_ == this.field_73086_f && p_73082_2_ == this.field_73087_g && p_73082_3_ == this.field_73099_h)
        {
            int l = this.field_73100_i - this.field_73089_e;
            Block block = this.field_73092_a.func_147439_a(p_73082_1_, p_73082_2_, p_73082_3_);

            if (block.func_149688_o() != Material.field_151579_a)
            {
                float f = block.func_149737_a(this.field_73090_b, this.field_73090_b.world, p_73082_1_, p_73082_2_, p_73082_3_) * (float)(l + 1);

                if (f >= 0.7F)
                {
                    this.field_73088_d = false;
                    this.field_73092_a.func_147443_d(this.field_73090_b.func_145782_y(), p_73082_1_, p_73082_2_, p_73082_3_, -1);
                    this.func_73084_b(p_73082_1_, p_73082_2_, p_73082_3_);
                }
                else if (!this.field_73097_j)
                {
                    this.field_73088_d = false;
                    this.field_73097_j = true;
                    this.field_73098_k = p_73082_1_;
                    this.field_73095_l = p_73082_2_;
                    this.field_73096_m = p_73082_3_;
                    this.field_73093_n = this.field_73089_e;
                }
            }
        }
    }

    public void func_73073_c(int p_73073_1_, int p_73073_2_, int p_73073_3_)
    {
        this.field_73088_d = false;
        this.field_73092_a.func_147443_d(this.field_73090_b.func_145782_y(), this.field_73086_f, this.field_73087_g, this.field_73099_h, -1);
    }

    private boolean func_73079_d(int p_73079_1_, int p_73079_2_, int p_73079_3_)
    {
        Block block = this.field_73092_a.func_147439_a(p_73079_1_, p_73079_2_, p_73079_3_);
        int l = this.field_73092_a.func_72805_g(p_73079_1_, p_73079_2_, p_73079_3_);
        block.func_149681_a(this.field_73092_a, p_73079_1_, p_73079_2_, p_73079_3_, l, this.field_73090_b);
        boolean flag = this.field_73092_a.func_147468_f(p_73079_1_, p_73079_2_, p_73079_3_);

        if (flag)
        {
            block.func_149664_b(this.field_73092_a, p_73079_1_, p_73079_2_, p_73079_3_, l);
        }

        return flag;
    }

    public boolean func_73084_b(int p_73084_1_, int p_73084_2_, int p_73084_3_)
    {
        if (this.field_73091_c.func_82752_c() && !this.field_73090_b.func_82246_f(p_73084_1_, p_73084_2_, p_73084_3_))
        {
            return false;
        }
        else if (this.field_73091_c.func_77145_d() && this.field_73090_b.func_70694_bm() != null && this.field_73090_b.func_70694_bm().getBaseItem() instanceof ItemSword)
        {
            return false;
        }
        else
        {
            Block block = this.field_73092_a.func_147439_a(p_73084_1_, p_73084_2_, p_73084_3_);
            int l = this.field_73092_a.func_72805_g(p_73084_1_, p_73084_2_, p_73084_3_);
            this.field_73092_a.func_72889_a(this.field_73090_b, 2001, p_73084_1_, p_73084_2_, p_73084_3_, Block.func_149682_b(block) + (this.field_73092_a.func_72805_g(p_73084_1_, p_73084_2_, p_73084_3_) << 12));
            boolean flag = this.func_73079_d(p_73084_1_, p_73084_2_, p_73084_3_);

            if (this.func_73083_d())
            {
                this.field_73090_b.field_71135_a.func_147359_a(new S23PacketBlockChange(p_73084_1_, p_73084_2_, p_73084_3_, this.field_73092_a));
            }
            else
            {
                ItemStack itemstack = this.field_73090_b.func_71045_bC();
                boolean flag1 = this.field_73090_b.func_146099_a(block);

                if (itemstack != null)
                {
                    itemstack.func_150999_a(this.field_73092_a, block, p_73084_1_, p_73084_2_, p_73084_3_, this.field_73090_b);

                    if (itemstack.count == 0)
                    {
                        this.field_73090_b.func_71028_bD();
                    }
                }

                if (flag && flag1)
                {
                    block.func_149636_a(this.field_73092_a, this.field_73090_b, p_73084_1_, p_73084_2_, p_73084_3_, l);
                }
            }

            return flag;
        }
    }

    public boolean func_73085_a(EntityPlayer p_73085_1_, World p_73085_2_, ItemStack p_73085_3_)
    {
        int i = p_73085_3_.count;
        int j = p_73085_3_.func_77960_j();
        ItemStack itemstack1 = p_73085_3_.func_77957_a(p_73085_2_, p_73085_1_);

        if (itemstack1 == p_73085_3_ && (itemstack1 == null || itemstack1.count == i && itemstack1.func_77988_m() <= 0 && itemstack1.func_77960_j() == j))
        {
            return false;
        }
        else
        {
            p_73085_1_.inventory.cells[p_73085_1_.inventory.activeItemPosition] = itemstack1;

            if (this.func_73083_d())
            {
                itemstack1.count = i;

                if (itemstack1.func_77984_f())
                {
                    itemstack1.func_77964_b(j);
                }
            }

            if (itemstack1.count == 0)
            {
                p_73085_1_.inventory.cells[p_73085_1_.inventory.activeItemPosition] = null;
            }

            if (!p_73085_1_.func_71039_bw())
            {
                ((EntityPlayerMP)p_73085_1_).func_71120_a(p_73085_1_.field_71069_bz);
            }

            return true;
        }
    }

    public boolean func_73078_a(EntityPlayer p_73078_1_, World p_73078_2_, ItemStack p_73078_3_, int p_73078_4_, int p_73078_5_, int p_73078_6_, int p_73078_7_, float p_73078_8_, float p_73078_9_, float p_73078_10_)
    {
        if ((!p_73078_1_.func_70093_af() || p_73078_1_.func_70694_bm() == null) && p_73078_2_.func_147439_a(p_73078_4_, p_73078_5_, p_73078_6_).func_149727_a(p_73078_2_, p_73078_4_, p_73078_5_, p_73078_6_, p_73078_1_, p_73078_7_, p_73078_8_, p_73078_9_, p_73078_10_))
        {
            return true;
        }
        else if (p_73078_3_ == null)
        {
            return false;
        }
        else if (this.func_73083_d())
        {
            int i1 = p_73078_3_.func_77960_j();
            int j1 = p_73078_3_.count;
            boolean flag = p_73078_3_.func_77943_a(p_73078_1_, p_73078_2_, p_73078_4_, p_73078_5_, p_73078_6_, p_73078_7_, p_73078_8_, p_73078_9_, p_73078_10_);
            p_73078_3_.func_77964_b(i1);
            p_73078_3_.count = j1;
            return flag;
        }
        else
        {
            return p_73078_3_.func_77943_a(p_73078_1_, p_73078_2_, p_73078_4_, p_73078_5_, p_73078_6_, p_73078_7_, p_73078_8_, p_73078_9_, p_73078_10_);
        }
    }

    public void func_73080_a(WorldServer p_73080_1_)
    {
        this.field_73092_a = p_73080_1_;
    }
}