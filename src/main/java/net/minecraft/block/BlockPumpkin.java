package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPumpkin extends BlockDirectional
{
    private boolean field_149985_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_149984_b;
    @SideOnly(Side.CLIENT)
    private IIcon field_149986_M;
    private static final String __OBFID = "CL_00000291";

    protected BlockPumpkin(boolean p_i45419_1_)
    {
        super(Material.field_151572_C);
        this.func_149675_a(true);
        this.field_149985_a = p_i45419_1_;
        this.func_149647_a(CreativeTabs.field_78030_b);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.field_149984_b : (p_149691_1_ == 0 ? this.field_149984_b : (p_149691_2_ == 2 && p_149691_1_ == 2 ? this.field_149986_M : (p_149691_2_ == 3 && p_149691_1_ == 5 ? this.field_149986_M : (p_149691_2_ == 0 && p_149691_1_ == 3 ? this.field_149986_M : (p_149691_2_ == 1 && p_149691_1_ == 4 ? this.field_149986_M : this.field_149761_L)))));
    }

    public void func_149726_b(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
    {
        super.func_149726_b(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);

        if (p_149726_1_.func_147439_a(p_149726_2_, p_149726_3_ - 1, p_149726_4_) == Blocks.SNOW && p_149726_1_.func_147439_a(p_149726_2_, p_149726_3_ - 2, p_149726_4_) == Blocks.SNOW)
        {
            if (!p_149726_1_.field_72995_K)
            {
                p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_, p_149726_4_, func_149729_e(0), 0, 2);
                p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_ - 1, p_149726_4_, func_149729_e(0), 0, 2);
                p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_ - 2, p_149726_4_, func_149729_e(0), 0, 2);
                EntitySnowman entitysnowman = new EntitySnowman(p_149726_1_);
                entitysnowman.func_70012_b((double)p_149726_2_ + 0.5D, (double)p_149726_3_ - 1.95D, (double)p_149726_4_ + 0.5D, 0.0F, 0.0F);
                p_149726_1_.func_72838_d(entitysnowman);
                p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_, p_149726_4_, func_149729_e(0));
                p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_ - 1, p_149726_4_, func_149729_e(0));
                p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_ - 2, p_149726_4_, func_149729_e(0));
            }

            for (int i1 = 0; i1 < 120; ++i1)
            {
                p_149726_1_.func_72869_a("snowshovel", (double)p_149726_2_ + p_149726_1_.field_73012_v.nextDouble(), (double)(p_149726_3_ - 2) + p_149726_1_.field_73012_v.nextDouble() * 2.5D, (double)p_149726_4_ + p_149726_1_.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
            }
        }
        else if (p_149726_1_.func_147439_a(p_149726_2_, p_149726_3_ - 1, p_149726_4_) == Blocks.IRON_BLOCK && p_149726_1_.func_147439_a(p_149726_2_, p_149726_3_ - 2, p_149726_4_) == Blocks.IRON_BLOCK)
        {
            boolean flag = p_149726_1_.func_147439_a(p_149726_2_ - 1, p_149726_3_ - 1, p_149726_4_) == Blocks.IRON_BLOCK && p_149726_1_.func_147439_a(p_149726_2_ + 1, p_149726_3_ - 1, p_149726_4_) == Blocks.IRON_BLOCK;
            boolean flag1 = p_149726_1_.func_147439_a(p_149726_2_, p_149726_3_ - 1, p_149726_4_ - 1) == Blocks.IRON_BLOCK && p_149726_1_.func_147439_a(p_149726_2_, p_149726_3_ - 1, p_149726_4_ + 1) == Blocks.IRON_BLOCK;

            if (flag || flag1)
            {
                p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_, p_149726_4_, func_149729_e(0), 0, 2);
                p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_ - 1, p_149726_4_, func_149729_e(0), 0, 2);
                p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_ - 2, p_149726_4_, func_149729_e(0), 0, 2);

                if (flag)
                {
                    p_149726_1_.func_147465_d(p_149726_2_ - 1, p_149726_3_ - 1, p_149726_4_, func_149729_e(0), 0, 2);
                    p_149726_1_.func_147465_d(p_149726_2_ + 1, p_149726_3_ - 1, p_149726_4_, func_149729_e(0), 0, 2);
                }
                else
                {
                    p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_ - 1, p_149726_4_ - 1, func_149729_e(0), 0, 2);
                    p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_ - 1, p_149726_4_ + 1, func_149729_e(0), 0, 2);
                }

                EntityIronGolem entityirongolem = new EntityIronGolem(p_149726_1_);
                entityirongolem.func_70849_f(true);
                entityirongolem.func_70012_b((double)p_149726_2_ + 0.5D, (double)p_149726_3_ - 1.95D, (double)p_149726_4_ + 0.5D, 0.0F, 0.0F);
                p_149726_1_.func_72838_d(entityirongolem);

                for (int l = 0; l < 120; ++l)
                {
                    p_149726_1_.func_72869_a("snowballpoof", (double)p_149726_2_ + p_149726_1_.field_73012_v.nextDouble(), (double)(p_149726_3_ - 2) + p_149726_1_.field_73012_v.nextDouble() * 3.9D, (double)p_149726_4_ + p_149726_1_.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
                }

                p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_, p_149726_4_, func_149729_e(0));
                p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_ - 1, p_149726_4_, func_149729_e(0));
                p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_ - 2, p_149726_4_, func_149729_e(0));

                if (flag)
                {
                    p_149726_1_.func_147444_c(p_149726_2_ - 1, p_149726_3_ - 1, p_149726_4_, func_149729_e(0));
                    p_149726_1_.func_147444_c(p_149726_2_ + 1, p_149726_3_ - 1, p_149726_4_, func_149729_e(0));
                }
                else
                {
                    p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_ - 1, p_149726_4_ - 1, func_149729_e(0));
                    p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_ - 1, p_149726_4_ + 1, func_149729_e(0));
                }
            }
        }
    }

    public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return p_149742_1_.func_147439_a(p_149742_2_, p_149742_3_, p_149742_4_).field_149764_J.func_76222_j() && World.func_147466_a(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_);
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.func_76128_c((double)(p_149689_5_.field_70177_z * 4.0F / 360.0F) + 2.5D) & 3;
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_)
    {
        this.field_149986_M = p_149651_1_.func_94245_a(this.func_149641_N() + "_face_" + (this.field_149985_a ? "on" : "off"));
        this.field_149984_b = p_149651_1_.func_94245_a(this.func_149641_N() + "_top");
        this.field_149761_L = p_149651_1_.func_94245_a(this.func_149641_N() + "_side");
    }
}