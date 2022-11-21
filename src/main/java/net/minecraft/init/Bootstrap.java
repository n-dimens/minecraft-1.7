package net.minecraft.init;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Bootstrap
{
    private static boolean field_151355_a = false;
    private static final String __OBFID = "CL_00001397";

    static void func_151353_a()
    {
        BlockDispenser.field_149943_a.add(Items.ARROW, new BehaviorProjectileDispense()
        {
            private static final String __OBFID = "CL_00001398";
            protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_)
            {
                EntityArrow entityarrow = new EntityArrow(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
                entityarrow.field_70251_a = 1;
                return entityarrow;
            }
        });
        BlockDispenser.field_149943_a.add(Items.EGG, new BehaviorProjectileDispense()
        {
            private static final String __OBFID = "CL_00001404";
            protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_)
            {
                return new EntityEgg(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
            }
        });
        BlockDispenser.field_149943_a.add(Items.SNOWBALL, new BehaviorProjectileDispense()
        {
            private static final String __OBFID = "CL_00001405";
            protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_)
            {
                return new EntitySnowball(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
            }
        });
        BlockDispenser.field_149943_a.add(Items.EXPERIENCE_BOTTLE, new BehaviorProjectileDispense()
        {
            private static final String __OBFID = "CL_00001406";
            protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_)
            {
                return new EntityExpBottle(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
            }
            protected float func_82498_a()
            {
                return super.func_82498_a() * 0.5F;
            }
            protected float func_82500_b()
            {
                return super.func_82500_b() * 1.25F;
            }
        });
        BlockDispenser.field_149943_a.add(Items.POTION, new IBehaviorDispenseItem()
        {
            private final BehaviorDefaultDispenseItem field_150843_b = new BehaviorDefaultDispenseItem();
            private static final String __OBFID = "CL_00001407";
            public ItemStack func_82482_a(IBlockSource p_82482_1_, final ItemStack p_82482_2_)
            {
                return ItemPotion.func_77831_g(p_82482_2_.func_77960_j()) ? (new BehaviorProjectileDispense()
                {
                    private static final String __OBFID = "CL_00001408";
                    protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_)
                    {
                        return new EntityPotion(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c(), p_82482_2_.func_77946_l());
                    }
                    protected float func_82498_a()
                    {
                        return super.func_82498_a() * 0.5F;
                    }
                    protected float func_82500_b()
                    {
                        return super.func_82500_b() * 1.25F;
                    }
                }).func_82482_a(p_82482_1_, p_82482_2_): this.field_150843_b.func_82482_a(p_82482_1_, p_82482_2_);
            }
        });
        BlockDispenser.field_149943_a.add(Items.SPAWN_EGG, new BehaviorDefaultDispenseItem()
        {
            private static final String __OBFID = "CL_00001410";
            public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
            {
                EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
                double d0 = p_82487_1_.func_82615_a() + (double)enumfacing.func_82601_c();
                double d1 = (double)((float)p_82487_1_.func_82622_e() + 0.2F);
                double d2 = p_82487_1_.func_82616_c() + (double)enumfacing.func_82599_e();
                Entity entity = ItemMonsterPlacer.func_77840_a(p_82487_1_.func_82618_k(), p_82487_2_.func_77960_j(), d0, d1, d2);

                if (entity instanceof EntityLivingBase && p_82487_2_.func_82837_s())
                {
                    ((EntityLiving)entity).func_94058_c(p_82487_2_.func_82833_r());
                }

                p_82487_2_.func_77979_a(1);
                return p_82487_2_;
            }
        });
        BlockDispenser.field_149943_a.add(Items.FIREWORKS, new BehaviorDefaultDispenseItem()
        {
            private static final String __OBFID = "CL_00001411";
            public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
            {
                EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
                double d0 = p_82487_1_.func_82615_a() + (double)enumfacing.func_82601_c();
                double d1 = (double)((float)p_82487_1_.func_82622_e() + 0.2F);
                double d2 = p_82487_1_.func_82616_c() + (double)enumfacing.func_82599_e();
                EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(p_82487_1_.func_82618_k(), d0, d1, d2, p_82487_2_);
                p_82487_1_.func_82618_k().func_72838_d(entityfireworkrocket);
                p_82487_2_.func_77979_a(1);
                return p_82487_2_;
            }
            protected void func_82485_a(IBlockSource p_82485_1_)
            {
                p_82485_1_.func_82618_k().func_72926_e(1002, p_82485_1_.func_82623_d(), p_82485_1_.func_82622_e(), p_82485_1_.func_82621_f(), 0);
            }
        });
        BlockDispenser.field_149943_a.add(Items.FIRE_CHARGE, new BehaviorDefaultDispenseItem()
        {
            private static final String __OBFID = "CL_00001412";
            public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
            {
                EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
                IPosition iposition = BlockDispenser.func_149939_a(p_82487_1_);
                double d0 = iposition.func_82615_a() + (double)((float)enumfacing.func_82601_c() * 0.3F);
                double d1 = iposition.func_82617_b() + (double)((float)enumfacing.func_82601_c() * 0.3F);
                double d2 = iposition.func_82616_c() + (double)((float)enumfacing.func_82599_e() * 0.3F);
                World world = p_82487_1_.func_82618_k();
                Random random = world.field_73012_v;
                double d3 = random.nextGaussian() * 0.05D + (double)enumfacing.func_82601_c();
                double d4 = random.nextGaussian() * 0.05D + (double)enumfacing.func_96559_d();
                double d5 = random.nextGaussian() * 0.05D + (double)enumfacing.func_82599_e();
                world.func_72838_d(new EntitySmallFireball(world, d0, d1, d2, d3, d4, d5));
                p_82487_2_.func_77979_a(1);
                return p_82487_2_;
            }
            protected void func_82485_a(IBlockSource p_82485_1_)
            {
                p_82485_1_.func_82618_k().func_72926_e(1009, p_82485_1_.func_82623_d(), p_82485_1_.func_82622_e(), p_82485_1_.func_82621_f(), 0);
            }
        });
        BlockDispenser.field_149943_a.add(Items.BOAT, new BehaviorDefaultDispenseItem()
        {
            private final BehaviorDefaultDispenseItem field_150842_b = new BehaviorDefaultDispenseItem();
            private static final String __OBFID = "CL_00001413";
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
                Material material = world.func_147439_a(i, j, k).func_149688_o();
                double d3;

                if (Material.field_151586_h.equals(material))
                {
                    d3 = 1.0D;
                }
                else
                {
                    if (!Material.field_151579_a.equals(material) || !Material.field_151586_h.equals(world.func_147439_a(i, j - 1, k).func_149688_o()))
                    {
                        return this.field_150842_b.func_82482_a(p_82487_1_, p_82487_2_);
                    }

                    d3 = 0.0D;
                }

                EntityBoat entityboat = new EntityBoat(world, d0, d1 + d3, d2);
                world.func_72838_d(entityboat);
                p_82487_2_.func_77979_a(1);
                return p_82487_2_;
            }
            protected void func_82485_a(IBlockSource p_82485_1_)
            {
                p_82485_1_.func_82618_k().func_72926_e(1000, p_82485_1_.func_82623_d(), p_82485_1_.func_82622_e(), p_82485_1_.func_82621_f(), 0);
            }
        });
        BehaviorDefaultDispenseItem behaviordefaultdispenseitem = new BehaviorDefaultDispenseItem()
        {
            private final BehaviorDefaultDispenseItem field_150841_b = new BehaviorDefaultDispenseItem();
            private static final String __OBFID = "CL_00001399";
            public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
            {
                ItemBucket itembucket = (ItemBucket)p_82487_2_.func_77973_b();
                int i = p_82487_1_.func_82623_d();
                int j = p_82487_1_.func_82622_e();
                int k = p_82487_1_.func_82621_f();
                EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());

                if (itembucket.func_77875_a(p_82487_1_.func_82618_k(), i + enumfacing.func_82601_c(), j + enumfacing.func_96559_d(), k + enumfacing.func_82599_e()))
                {
                    p_82487_2_.func_150996_a(Items.BUCKET);
                    p_82487_2_.field_77994_a = 1;
                    return p_82487_2_;
                }
                else
                {
                    return this.field_150841_b.func_82482_a(p_82487_1_, p_82487_2_);
                }
            }
        };
        BlockDispenser.field_149943_a.add(Items.LAVA_BUCKET, behaviordefaultdispenseitem);
        BlockDispenser.field_149943_a.add(Items.WATER_BUCKET, behaviordefaultdispenseitem);
        BlockDispenser.field_149943_a.add(Items.BUCKET, new BehaviorDefaultDispenseItem()
        {
            private final BehaviorDefaultDispenseItem field_150840_b = new BehaviorDefaultDispenseItem();
            private static final String __OBFID = "CL_00001400";
            public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
            {
                EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
                World world = p_82487_1_.func_82618_k();
                int i = p_82487_1_.func_82623_d() + enumfacing.func_82601_c();
                int j = p_82487_1_.func_82622_e() + enumfacing.func_96559_d();
                int k = p_82487_1_.func_82621_f() + enumfacing.func_82599_e();
                Material material = world.func_147439_a(i, j, k).func_149688_o();
                int l = world.func_72805_g(i, j, k);
                Item item;

                if (Material.field_151586_h.equals(material) && l == 0)
                {
                    item = Items.WATER_BUCKET;
                }
                else
                {
                    if (!Material.field_151587_i.equals(material) || l != 0)
                    {
                        return super.func_82487_b(p_82487_1_, p_82487_2_);
                    }

                    item = Items.LAVA_BUCKET;
                }

                world.func_147468_f(i, j, k);

                if (--p_82487_2_.field_77994_a == 0)
                {
                    p_82487_2_.func_150996_a(item);
                    p_82487_2_.field_77994_a = 1;
                }
                else if (((TileEntityDispenser)p_82487_1_.func_150835_j()).func_146019_a(new ItemStack(item)) < 0)
                {
                    this.field_150840_b.func_82482_a(p_82487_1_, new ItemStack(item));
                }

                return p_82487_2_;
            }
        });
        BlockDispenser.field_149943_a.add(Items.FLINT_AND_STEEL, new BehaviorDefaultDispenseItem()
        {
            private boolean field_150839_b = true;
            private static final String __OBFID = "CL_00001401";
            protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
            {
                EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
                World world = p_82487_1_.func_82618_k();
                int i = p_82487_1_.func_82623_d() + enumfacing.func_82601_c();
                int j = p_82487_1_.func_82622_e() + enumfacing.func_96559_d();
                int k = p_82487_1_.func_82621_f() + enumfacing.func_82599_e();

                if (world.func_147437_c(i, j, k))
                {
                    world.func_147449_b(i, j, k, Blocks.FIRE);

                    if (p_82487_2_.func_96631_a(1, world.field_73012_v))
                    {
                        p_82487_2_.field_77994_a = 0;
                    }
                }
                else if (world.func_147439_a(i, j, k) == Blocks.TNT)
                {
                    Blocks.TNT.func_149664_b(world, i, j, k, 1);
                    world.func_147468_f(i, j, k);
                }
                else
                {
                    this.field_150839_b = false;
                }

                return p_82487_2_;
            }
            protected void func_82485_a(IBlockSource p_82485_1_)
            {
                if (this.field_150839_b)
                {
                    p_82485_1_.func_82618_k().func_72926_e(1000, p_82485_1_.func_82623_d(), p_82485_1_.func_82622_e(), p_82485_1_.func_82621_f(), 0);
                }
                else
                {
                    p_82485_1_.func_82618_k().func_72926_e(1001, p_82485_1_.func_82623_d(), p_82485_1_.func_82622_e(), p_82485_1_.func_82621_f(), 0);
                }
            }
        });
        BlockDispenser.field_149943_a.add(Items.DYE, new BehaviorDefaultDispenseItem()
        {
            private boolean field_150838_b = true;
            private static final String __OBFID = "CL_00001402";
            protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
            {
                if (p_82487_2_.func_77960_j() == 15)
                {
                    EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
                    World world = p_82487_1_.func_82618_k();
                    int i = p_82487_1_.func_82623_d() + enumfacing.func_82601_c();
                    int j = p_82487_1_.func_82622_e() + enumfacing.func_96559_d();
                    int k = p_82487_1_.func_82621_f() + enumfacing.func_82599_e();

                    if (ItemDye.func_150919_a(p_82487_2_, world, i, j, k))
                    {
                        if (!world.field_72995_K)
                        {
                            world.func_72926_e(2005, i, j, k, 0);
                        }
                    }
                    else
                    {
                        this.field_150838_b = false;
                    }

                    return p_82487_2_;
                }
                else
                {
                    return super.func_82487_b(p_82487_1_, p_82487_2_);
                }
            }
            protected void func_82485_a(IBlockSource p_82485_1_)
            {
                if (this.field_150838_b)
                {
                    p_82485_1_.func_82618_k().func_72926_e(1000, p_82485_1_.func_82623_d(), p_82485_1_.func_82622_e(), p_82485_1_.func_82621_f(), 0);
                }
                else
                {
                    p_82485_1_.func_82618_k().func_72926_e(1001, p_82485_1_.func_82623_d(), p_82485_1_.func_82622_e(), p_82485_1_.func_82621_f(), 0);
                }
            }
        });
        BlockDispenser.field_149943_a.add(Item.func_150898_a(Blocks.TNT), new BehaviorDefaultDispenseItem()
        {
            private static final String __OBFID = "CL_00001403";
            protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_)
            {
                EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
                World world = p_82487_1_.func_82618_k();
                int i = p_82487_1_.func_82623_d() + enumfacing.func_82601_c();
                int j = p_82487_1_.func_82622_e() + enumfacing.func_96559_d();
                int k = p_82487_1_.func_82621_f() + enumfacing.func_82599_e();
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), (EntityLivingBase)null);
                world.func_72838_d(entitytntprimed);
                --p_82487_2_.field_77994_a;
                return p_82487_2_;
            }
        });
    }

    public static void func_151354_b()
    {
        if (!field_151355_a)
        {
            field_151355_a = true;
            Block.func_149671_p();
            BlockFire.func_149843_e();
            Item.func_150900_l();
            StatList.func_151178_a();
            func_151353_a();
        }
    }
}