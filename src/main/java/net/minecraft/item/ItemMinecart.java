package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemMinecart extends Item
{
    private static final IBehaviorDispenseItem field_96602_b = new BehaviorDefaultDispenseItem()
    {
        private final BehaviorDefaultDispenseItem field_96465_b = new BehaviorDefaultDispenseItem();
        private static final String __OBFID = "CL_00000050";
        public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
        {
            EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            World world = p_82487_1_.func_82618_k();
            double d0 = p_82487_1_.func_82615_a() + (double)((float)enumfacing.func_82601_c() * 1.125F);
            double d1 = p_82487_1_.func_82617_b() + (double)((float)enumfacing.func_96559_d() * 1.125F);
            double d2 = p_82487_1_.func_82616_c() + (double)((float)enumfacing.func_82599_e() * 1.125F);
            int i = p_82487_1_.func_82623_d() + enumfacing.func_82601_c();
            int j = p_82487_1_.func_82622_e() + enumfacing.func_96559_d();
            int k = p_82487_1_.func_82621_f() + enumfacing.func_82599_e();
            Block block = world.func_147439_a(i, j, k);
            double d3;

            if (BlockRailBase.func_150051_a(block))
            {
                d3 = 0.0D;
            }
            else
            {
                if (block.func_149688_o() != Material.field_151579_a || !BlockRailBase.func_150051_a(world.func_147439_a(i, j - 1, k)))
                {
                    return this.field_96465_b.func_82482_a(p_82487_1_, p_82487_2_);
                }

                d3 = -1.0D;
            }

            EntityMinecart entityminecart = EntityMinecart.func_94090_a(world, d0, d1 + d3, d2, ((ItemMinecart)p_82487_2_.func_77973_b()).field_77841_a);

            if (p_82487_2_.func_82837_s())
            {
                entityminecart.func_96094_a(p_82487_2_.func_82833_r());
            }

            world.func_72838_d(entityminecart);
            p_82487_2_.func_77979_a(1);
            return p_82487_2_;
        }
        protected void func_82485_a(IBlockSource p_82485_1_)
        {
            p_82485_1_.func_82618_k().func_72926_e(1000, p_82485_1_.func_82623_d(), p_82485_1_.func_82622_e(), p_82485_1_.func_82621_f(), 0);
        }
    };
    public int field_77841_a;
    private static final String __OBFID = "CL_00000049";

    public ItemMinecart(int p_i45345_1_)
    {
        this.field_77777_bU = 1;
        this.field_77841_a = p_i45345_1_;
        this.func_77637_a(CreativeTabs.field_78029_e);
        BlockDispenser.field_149943_a.add(this, field_96602_b);
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (BlockRailBase.func_150051_a(p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_)))
        {
            if (!p_77648_3_.field_72995_K)
            {
                EntityMinecart entityminecart = EntityMinecart.func_94090_a(p_77648_3_, (double)((float)p_77648_4_ + 0.5F), (double)((float)p_77648_5_ + 0.5F), (double)((float)p_77648_6_ + 0.5F), this.field_77841_a);

                if (p_77648_1_.func_82837_s())
                {
                    entityminecart.func_96094_a(p_77648_1_.func_82833_r());
                }

                p_77648_3_.func_72838_d(entityminecart);
            }

            --p_77648_1_.field_77994_a;
            return true;
        }
        else
        {
            return false;
        }
    }
}