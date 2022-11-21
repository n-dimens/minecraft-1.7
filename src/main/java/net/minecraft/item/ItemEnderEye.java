package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public class ItemEnderEye extends Item
{
    private static final String __OBFID = "CL_00000026";

    public ItemEnderEye()
    {
        this.func_77637_a(CreativeTabs.field_78026_f);
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        Block block = p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
        int i1 = p_77648_3_.func_72805_g(p_77648_4_, p_77648_5_, p_77648_6_);

        if (p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && block == Blocks.END_PORTAL_FRAME && !BlockEndPortalFrame.func_150020_b(i1))
        {
            if (p_77648_3_.field_72995_K)
            {
                return true;
            }
            else
            {
                p_77648_3_.func_72921_c(p_77648_4_, p_77648_5_, p_77648_6_, i1 + 4, 2);
                p_77648_3_.func_147453_f(p_77648_4_, p_77648_5_, p_77648_6_, Blocks.END_PORTAL_FRAME);
                --p_77648_1_.field_77994_a;
                int j1;

                for (j1 = 0; j1 < 16; ++j1)
                {
                    double d0 = (double)((float)p_77648_4_ + (5.0F + field_77697_d.nextFloat() * 6.0F) / 16.0F);
                    double d1 = (double)((float)p_77648_5_ + 0.8125F);
                    double d2 = (double)((float)p_77648_6_ + (5.0F + field_77697_d.nextFloat() * 6.0F) / 16.0F);
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;
                    p_77648_3_.func_72869_a("smoke", d0, d1, d2, d3, d4, d5);
                }

                j1 = i1 & 3;
                int j2 = 0;
                int k1 = 0;
                boolean flag1 = false;
                boolean flag = true;
                int k2 = Direction.field_71577_f[j1];
                int l1;
                int i2;
                int l2;

                for (l1 = -2; l1 <= 2; ++l1)
                {
                    l2 = p_77648_4_ + Direction.field_71583_a[k2] * l1;
                    i2 = p_77648_6_ + Direction.field_71581_b[k2] * l1;

                    if (p_77648_3_.func_147439_a(l2, p_77648_5_, i2) == Blocks.END_PORTAL_FRAME)
                    {
                        if (!BlockEndPortalFrame.func_150020_b(p_77648_3_.func_72805_g(l2, p_77648_5_, i2)))
                        {
                            flag = false;
                            break;
                        }

                        k1 = l1;

                        if (!flag1)
                        {
                            j2 = l1;
                            flag1 = true;
                        }
                    }
                }

                if (flag && k1 == j2 + 2)
                {
                    for (l1 = j2; l1 <= k1; ++l1)
                    {
                        l2 = p_77648_4_ + Direction.field_71583_a[k2] * l1;
                        i2 = p_77648_6_ + Direction.field_71581_b[k2] * l1;
                        l2 += Direction.field_71583_a[j1] * 4;
                        i2 += Direction.field_71581_b[j1] * 4;

                        if (p_77648_3_.func_147439_a(l2, p_77648_5_, i2) != Blocks.END_PORTAL_FRAME || !BlockEndPortalFrame.func_150020_b(p_77648_3_.func_72805_g(l2, p_77648_5_, i2)))
                        {
                            flag = false;
                            break;
                        }
                    }

                    int i3;

                    for (l1 = j2 - 1; l1 <= k1 + 1; l1 += 4)
                    {
                        for (l2 = 1; l2 <= 3; ++l2)
                        {
                            i2 = p_77648_4_ + Direction.field_71583_a[k2] * l1;
                            i3 = p_77648_6_ + Direction.field_71581_b[k2] * l1;
                            i2 += Direction.field_71583_a[j1] * l2;
                            i3 += Direction.field_71581_b[j1] * l2;

                            if (p_77648_3_.func_147439_a(i2, p_77648_5_, i3) != Blocks.END_PORTAL_FRAME || !BlockEndPortalFrame.func_150020_b(p_77648_3_.func_72805_g(i2, p_77648_5_, i3)))
                            {
                                flag = false;
                                break;
                            }
                        }
                    }

                    if (flag)
                    {
                        for (l1 = j2; l1 <= k1; ++l1)
                        {
                            for (l2 = 1; l2 <= 3; ++l2)
                            {
                                i2 = p_77648_4_ + Direction.field_71583_a[k2] * l1;
                                i3 = p_77648_6_ + Direction.field_71581_b[k2] * l1;
                                i2 += Direction.field_71583_a[j1] * l2;
                                i3 += Direction.field_71581_b[j1] * l2;
                                p_77648_3_.func_147465_d(i2, p_77648_5_, i3, Blocks.END_PORTAL, 0, 2);
                            }
                        }
                    }
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        MovingObjectPosition movingobjectposition = this.func_77621_a(p_77659_2_, p_77659_3_, false);

        if (movingobjectposition != null && movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && p_77659_2_.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d) == Blocks.END_PORTAL_FRAME)
        {
            return p_77659_1_;
        }
        else
        {
            if (!p_77659_2_.field_72995_K)
            {
                ChunkPosition chunkposition = p_77659_2_.func_147440_b("Stronghold", (int)p_77659_3_.field_70165_t, (int)p_77659_3_.field_70163_u, (int)p_77659_3_.field_70161_v);

                if (chunkposition != null)
                {
                    EntityEnderEye entityendereye = new EntityEnderEye(p_77659_2_, p_77659_3_.field_70165_t, p_77659_3_.field_70163_u + 1.62D - (double)p_77659_3_.field_70129_M, p_77659_3_.field_70161_v);
                    entityendereye.func_70220_a((double)chunkposition.field_151329_a, chunkposition.field_151327_b, (double)chunkposition.field_151328_c);
                    p_77659_2_.func_72838_d(entityendereye);
                    p_77659_2_.func_72956_a(p_77659_3_, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
                    p_77659_2_.func_72889_a((EntityPlayer)null, 1002, (int)p_77659_3_.field_70165_t, (int)p_77659_3_.field_70163_u, (int)p_77659_3_.field_70161_v, 0);

                    if (!p_77659_3_.field_71075_bZ.field_75098_d)
                    {
                        --p_77659_1_.field_77994_a;
                    }
                }
            }

            return p_77659_1_;
        }
    }
}