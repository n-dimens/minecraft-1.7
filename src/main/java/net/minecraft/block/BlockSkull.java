package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSkull extends BlockContainer
{
    private static final String __OBFID = "CL_00000307";

    protected BlockSkull()
    {
        super(Material.field_151594_q);
        this.func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
    }

    public int func_149645_b()
    {
        return -1;
    }

    public boolean func_149662_c()
    {
        return false;
    }

    public boolean func_149686_d()
    {
        return false;
    }

    public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        int l = p_149719_1_.func_72805_g(p_149719_2_, p_149719_3_, p_149719_4_) & 7;

        switch (l)
        {
            case 1:
            default:
                this.func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
                break;
            case 2:
                this.func_149676_a(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
                break;
            case 3:
                this.func_149676_a(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
                break;
            case 4:
                this.func_149676_a(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
                break;
            case 5:
                this.func_149676_a(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
        }
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        this.func_149719_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
        return super.func_149668_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.func_76128_c((double)(p_149689_5_.field_70177_z * 4.0F / 360.0F) + 2.5D) & 3;
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntitySkull();
    }

    @SideOnly(Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Items.field_151144_bL;
    }

    public int func_149643_k(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        TileEntity tileentity = p_149643_1_.func_147438_o(p_149643_2_, p_149643_3_, p_149643_4_);
        return tileentity != null && tileentity instanceof TileEntitySkull ? ((TileEntitySkull)tileentity).func_145904_a() : super.func_149643_k(p_149643_1_, p_149643_2_, p_149643_3_, p_149643_4_);
    }

    public int func_149692_a(int p_149692_1_)
    {
        return p_149692_1_;
    }

    public void func_149690_a(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {}

    public void func_149681_a(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_)
    {
        if (p_149681_6_.field_71075_bZ.field_75098_d)
        {
            p_149681_5_ |= 8;
            p_149681_1_.func_72921_c(p_149681_2_, p_149681_3_, p_149681_4_, p_149681_5_, 4);
        }

        super.func_149681_a(p_149681_1_, p_149681_2_, p_149681_3_, p_149681_4_, p_149681_5_, p_149681_6_);
    }

    public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        if (!p_149749_1_.field_72995_K)
        {
            if ((p_149749_6_ & 8) == 0)
            {
                ItemStack itemstack = new ItemStack(Items.field_151144_bL, 1, this.func_149643_k(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_));
                TileEntitySkull tileentityskull = (TileEntitySkull)p_149749_1_.func_147438_o(p_149749_2_, p_149749_3_, p_149749_4_);

                if (tileentityskull.func_145904_a() == 3 && tileentityskull.func_152108_a() != null)
                {
                    itemstack.func_77982_d(new NBTTagCompound());
                    NBTTagCompound nbttagcompound = new NBTTagCompound();
                    NBTUtil.func_152460_a(nbttagcompound, tileentityskull.func_152108_a());
                    itemstack.func_77978_p().func_74782_a("SkullOwner", nbttagcompound);
                }

                this.func_149642_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, itemstack);
            }

            super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
        }
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Items.field_151144_bL;
    }

    public void func_149965_a(World p_149965_1_, int p_149965_2_, int p_149965_3_, int p_149965_4_, TileEntitySkull p_149965_5_)
    {
        if (p_149965_5_.func_145904_a() == 1 && p_149965_3_ >= 2 && p_149965_1_.field_73013_u != EnumDifficulty.PEACEFUL && !p_149965_1_.field_72995_K)
        {
            int l;
            EntityWither entitywither;
            Iterator iterator;
            EntityPlayer entityplayer;
            int i1;

            for (l = -2; l <= 0; ++l)
            {
                if (p_149965_1_.func_147439_a(p_149965_2_, p_149965_3_ - 1, p_149965_4_ + l) == Blocks.field_150425_aM && p_149965_1_.func_147439_a(p_149965_2_, p_149965_3_ - 1, p_149965_4_ + l + 1) == Blocks.field_150425_aM && p_149965_1_.func_147439_a(p_149965_2_, p_149965_3_ - 2, p_149965_4_ + l + 1) == Blocks.field_150425_aM && p_149965_1_.func_147439_a(p_149965_2_, p_149965_3_ - 1, p_149965_4_ + l + 2) == Blocks.field_150425_aM && this.func_149966_a(p_149965_1_, p_149965_2_, p_149965_3_, p_149965_4_ + l, 1) && this.func_149966_a(p_149965_1_, p_149965_2_, p_149965_3_, p_149965_4_ + l + 1, 1) && this.func_149966_a(p_149965_1_, p_149965_2_, p_149965_3_, p_149965_4_ + l + 2, 1))
                {
                    p_149965_1_.func_72921_c(p_149965_2_, p_149965_3_, p_149965_4_ + l, 8, 2);
                    p_149965_1_.func_72921_c(p_149965_2_, p_149965_3_, p_149965_4_ + l + 1, 8, 2);
                    p_149965_1_.func_72921_c(p_149965_2_, p_149965_3_, p_149965_4_ + l + 2, 8, 2);
                    p_149965_1_.func_147465_d(p_149965_2_, p_149965_3_, p_149965_4_ + l, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_, p_149965_3_, p_149965_4_ + l + 1, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_, p_149965_3_, p_149965_4_ + l + 2, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_, p_149965_3_ - 1, p_149965_4_ + l, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_, p_149965_3_ - 1, p_149965_4_ + l + 1, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_, p_149965_3_ - 1, p_149965_4_ + l + 2, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_, p_149965_3_ - 2, p_149965_4_ + l + 1, func_149729_e(0), 0, 2);

                    if (!p_149965_1_.field_72995_K)
                    {
                        entitywither = new EntityWither(p_149965_1_);
                        entitywither.func_70012_b((double)p_149965_2_ + 0.5D, (double)p_149965_3_ - 1.45D, (double)(p_149965_4_ + l) + 1.5D, 90.0F, 0.0F);
                        entitywither.field_70761_aq = 90.0F;
                        entitywither.func_82206_m();

                        if (!p_149965_1_.field_72995_K)
                        {
                            iterator = p_149965_1_.func_72872_a(EntityPlayer.class, entitywither.field_70121_D.func_72314_b(50.0D, 50.0D, 50.0D)).iterator();

                            while (iterator.hasNext())
                            {
                                entityplayer = (EntityPlayer)iterator.next();
                                entityplayer.func_71029_a(AchievementList.SPAWN_WITHER);
                            }
                        }

                        p_149965_1_.func_72838_d(entitywither);
                    }

                    for (i1 = 0; i1 < 120; ++i1)
                    {
                        p_149965_1_.func_72869_a("snowballpoof", (double)p_149965_2_ + p_149965_1_.field_73012_v.nextDouble(), (double)(p_149965_3_ - 2) + p_149965_1_.field_73012_v.nextDouble() * 3.9D, (double)(p_149965_4_ + l + 1) + p_149965_1_.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
                    }

                    p_149965_1_.func_147444_c(p_149965_2_, p_149965_3_, p_149965_4_ + l, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_, p_149965_3_, p_149965_4_ + l + 1, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_, p_149965_3_, p_149965_4_ + l + 2, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_, p_149965_3_ - 1, p_149965_4_ + l, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_, p_149965_3_ - 1, p_149965_4_ + l + 1, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_, p_149965_3_ - 1, p_149965_4_ + l + 2, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_, p_149965_3_ - 2, p_149965_4_ + l + 1, func_149729_e(0));
                    return;
                }
            }

            for (l = -2; l <= 0; ++l)
            {
                if (p_149965_1_.func_147439_a(p_149965_2_ + l, p_149965_3_ - 1, p_149965_4_) == Blocks.field_150425_aM && p_149965_1_.func_147439_a(p_149965_2_ + l + 1, p_149965_3_ - 1, p_149965_4_) == Blocks.field_150425_aM && p_149965_1_.func_147439_a(p_149965_2_ + l + 1, p_149965_3_ - 2, p_149965_4_) == Blocks.field_150425_aM && p_149965_1_.func_147439_a(p_149965_2_ + l + 2, p_149965_3_ - 1, p_149965_4_) == Blocks.field_150425_aM && this.func_149966_a(p_149965_1_, p_149965_2_ + l, p_149965_3_, p_149965_4_, 1) && this.func_149966_a(p_149965_1_, p_149965_2_ + l + 1, p_149965_3_, p_149965_4_, 1) && this.func_149966_a(p_149965_1_, p_149965_2_ + l + 2, p_149965_3_, p_149965_4_, 1))
                {
                    p_149965_1_.func_72921_c(p_149965_2_ + l, p_149965_3_, p_149965_4_, 8, 2);
                    p_149965_1_.func_72921_c(p_149965_2_ + l + 1, p_149965_3_, p_149965_4_, 8, 2);
                    p_149965_1_.func_72921_c(p_149965_2_ + l + 2, p_149965_3_, p_149965_4_, 8, 2);
                    p_149965_1_.func_147465_d(p_149965_2_ + l, p_149965_3_, p_149965_4_, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_ + l + 1, p_149965_3_, p_149965_4_, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_ + l + 2, p_149965_3_, p_149965_4_, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_ + l, p_149965_3_ - 1, p_149965_4_, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_ + l + 1, p_149965_3_ - 1, p_149965_4_, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_ + l + 2, p_149965_3_ - 1, p_149965_4_, func_149729_e(0), 0, 2);
                    p_149965_1_.func_147465_d(p_149965_2_ + l + 1, p_149965_3_ - 2, p_149965_4_, func_149729_e(0), 0, 2);

                    if (!p_149965_1_.field_72995_K)
                    {
                        entitywither = new EntityWither(p_149965_1_);
                        entitywither.func_70012_b((double)(p_149965_2_ + l) + 1.5D, (double)p_149965_3_ - 1.45D, (double)p_149965_4_ + 0.5D, 0.0F, 0.0F);
                        entitywither.func_82206_m();

                        if (!p_149965_1_.field_72995_K)
                        {
                            iterator = p_149965_1_.func_72872_a(EntityPlayer.class, entitywither.field_70121_D.func_72314_b(50.0D, 50.0D, 50.0D)).iterator();

                            while (iterator.hasNext())
                            {
                                entityplayer = (EntityPlayer)iterator.next();
                                entityplayer.func_71029_a(AchievementList.SPAWN_WITHER);
                            }
                        }

                        p_149965_1_.func_72838_d(entitywither);
                    }

                    for (i1 = 0; i1 < 120; ++i1)
                    {
                        p_149965_1_.func_72869_a("snowballpoof", (double)(p_149965_2_ + l + 1) + p_149965_1_.field_73012_v.nextDouble(), (double)(p_149965_3_ - 2) + p_149965_1_.field_73012_v.nextDouble() * 3.9D, (double)p_149965_4_ + p_149965_1_.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
                    }

                    p_149965_1_.func_147444_c(p_149965_2_ + l, p_149965_3_, p_149965_4_, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_ + l + 1, p_149965_3_, p_149965_4_, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_ + l + 2, p_149965_3_, p_149965_4_, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_ + l, p_149965_3_ - 1, p_149965_4_, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_ + l + 1, p_149965_3_ - 1, p_149965_4_, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_ + l + 2, p_149965_3_ - 1, p_149965_4_, func_149729_e(0));
                    p_149965_1_.func_147444_c(p_149965_2_ + l + 1, p_149965_3_ - 2, p_149965_4_, func_149729_e(0));
                    return;
                }
            }
        }
    }

    private boolean func_149966_a(World p_149966_1_, int p_149966_2_, int p_149966_3_, int p_149966_4_, int p_149966_5_)
    {
        if (p_149966_1_.func_147439_a(p_149966_2_, p_149966_3_, p_149966_4_) != this)
        {
            return false;
        }
        else
        {
            TileEntity tileentity = p_149966_1_.func_147438_o(p_149966_2_, p_149966_3_, p_149966_4_);
            return tileentity != null && tileentity instanceof TileEntitySkull ? ((TileEntitySkull)tileentity).func_145904_a() == p_149966_5_ : false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_) {}

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
    {
        return Blocks.field_150425_aM.func_149733_h(p_149691_1_);
    }

    @SideOnly(Side.CLIENT)
    public String func_149702_O()
    {
        return this.func_149641_N() + "_" + ItemSkull.field_94587_a[0];
    }
}