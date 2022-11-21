package net.minecraft.item;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;

public class ItemMap extends ItemMapBase
{
    private static final String __OBFID = "CL_00000047";

    protected ItemMap()
    {
        this.func_77627_a(true);
    }

    @SideOnly(Side.CLIENT)
    public static MapData func_150912_a(int p_150912_0_, World p_150912_1_)
    {
        String s = "map_" + p_150912_0_;
        MapData mapdata = (MapData)p_150912_1_.func_72943_a(MapData.class, s);

        if (mapdata == null)
        {
            mapdata = new MapData(s);
            p_150912_1_.func_72823_a(s, mapdata);
        }

        return mapdata;
    }

    public MapData func_77873_a(ItemStack p_77873_1_, World p_77873_2_)
    {
        String s = "map_" + p_77873_1_.func_77960_j();
        MapData mapdata = (MapData)p_77873_2_.func_72943_a(MapData.class, s);

        if (mapdata == null && !p_77873_2_.field_72995_K)
        {
            p_77873_1_.func_77964_b(p_77873_2_.func_72841_b("map"));
            s = "map_" + p_77873_1_.func_77960_j();
            mapdata = new MapData(s);
            mapdata.field_76197_d = 3;
            int i = 128 * (1 << mapdata.field_76197_d);
            mapdata.field_76201_a = Math.round((float)p_77873_2_.func_72912_H().func_76079_c() / (float)i) * i;
            mapdata.field_76199_b = Math.round((float)(p_77873_2_.func_72912_H().func_76074_e() / i)) * i;
            mapdata.field_76200_c = (byte)p_77873_2_.field_73011_w.field_76574_g;
            mapdata.func_76185_a();
            p_77873_2_.func_72823_a(s, mapdata);
        }

        return mapdata;
    }

    public void func_77872_a(World p_77872_1_, Entity p_77872_2_, MapData p_77872_3_)
    {
        if (p_77872_1_.field_73011_w.field_76574_g == p_77872_3_.field_76200_c && p_77872_2_ instanceof EntityPlayer)
        {
            int i = 1 << p_77872_3_.field_76197_d;
            int j = p_77872_3_.field_76201_a;
            int k = p_77872_3_.field_76199_b;
            int l = MathHelper.func_76128_c(p_77872_2_.field_70165_t - (double)j) / i + 64;
            int i1 = MathHelper.func_76128_c(p_77872_2_.field_70161_v - (double)k) / i + 64;
            int j1 = 128 / i;

            if (p_77872_1_.field_73011_w.field_76576_e)
            {
                j1 /= 2;
            }

            MapData.MapInfo mapinfo = p_77872_3_.func_82568_a((EntityPlayer)p_77872_2_);
            ++mapinfo.field_82569_d;

            for (int k1 = l - j1 + 1; k1 < l + j1; ++k1)
            {
                if ((k1 & 15) == (mapinfo.field_82569_d & 15))
                {
                    int l1 = 255;
                    int i2 = 0;
                    double d0 = 0.0D;

                    for (int j2 = i1 - j1 - 1; j2 < i1 + j1; ++j2)
                    {
                        if (k1 >= 0 && j2 >= -1 && k1 < 128 && j2 < 128)
                        {
                            int k2 = k1 - l;
                            int l2 = j2 - i1;
                            boolean flag = k2 * k2 + l2 * l2 > (j1 - 2) * (j1 - 2);
                            int i3 = (j / i + k1 - 64) * i;
                            int j3 = (k / i + j2 - 64) * i;
                            HashMultiset hashmultiset = HashMultiset.create();
                            Chunk chunk = p_77872_1_.func_72938_d(i3, j3);

                            if (!chunk.func_76621_g())
                            {
                                int k3 = i3 & 15;
                                int l3 = j3 & 15;
                                int i4 = 0;
                                double d1 = 0.0D;
                                int j4;

                                if (p_77872_1_.field_73011_w.field_76576_e)
                                {
                                    j4 = i3 + j3 * 231871;
                                    j4 = j4 * j4 * 31287121 + j4 * 11;

                                    if ((j4 >> 20 & 1) == 0)
                                    {
                                        hashmultiset.add(Blocks.DIRT.func_149728_f(0), 10);
                                    }
                                    else
                                    {
                                        hashmultiset.add(Blocks.STONE.func_149728_f(0), 100);
                                    }

                                    d1 = 100.0D;
                                }
                                else
                                {
                                    for (j4 = 0; j4 < i; ++j4)
                                    {
                                        for (int k4 = 0; k4 < i; ++k4)
                                        {
                                            int l4 = chunk.func_76611_b(j4 + k3, k4 + l3) + 1;
                                            Block block = Blocks.AIR;
                                            int i5 = 0;

                                            if (l4 > 1)
                                            {
                                                do
                                                {
                                                    --l4;
                                                    block = chunk.func_150810_a(j4 + k3, l4, k4 + l3);
                                                    i5 = chunk.func_76628_c(j4 + k3, l4, k4 + l3);
                                                }
                                                while (block.func_149728_f(i5) == MapColor.field_151660_b && l4 > 0);

                                                if (l4 > 0 && block.func_149688_o().func_76224_d())
                                                {
                                                    int j5 = l4 - 1;
                                                    Block block1;

                                                    do
                                                    {
                                                        block1 = chunk.func_150810_a(j4 + k3, j5--, k4 + l3);
                                                        ++i4;
                                                    }
                                                    while (j5 > 0 && block1.func_149688_o().func_76224_d());
                                                }
                                            }

                                            d1 += (double)l4 / (double)(i * i);
                                            hashmultiset.add(block.func_149728_f(i5));
                                        }
                                    }
                                }

                                i4 /= i * i;
                                double d2 = (d1 - d0) * 4.0D / (double)(i + 4) + ((double)(k1 + j2 & 1) - 0.5D) * 0.4D;
                                byte b0 = 1;

                                if (d2 > 0.6D)
                                {
                                    b0 = 2;
                                }

                                if (d2 < -0.6D)
                                {
                                    b0 = 0;
                                }

                                MapColor mapcolor = (MapColor)Iterables.getFirst(Multisets.copyHighestCountFirst(hashmultiset), MapColor.field_151660_b);

                                if (mapcolor == MapColor.field_151662_n)
                                {
                                    d2 = (double)i4 * 0.1D + (double)(k1 + j2 & 1) * 0.2D;
                                    b0 = 1;

                                    if (d2 < 0.5D)
                                    {
                                        b0 = 2;
                                    }

                                    if (d2 > 0.9D)
                                    {
                                        b0 = 0;
                                    }
                                }

                                d0 = d1;

                                if (j2 >= 0 && k2 * k2 + l2 * l2 < j1 * j1 && (!flag || (k1 + j2 & 1) != 0))
                                {
                                    byte b1 = p_77872_3_.field_76198_e[k1 + j2 * 128];
                                    byte b2 = (byte)(mapcolor.field_76290_q * 4 + b0);

                                    if (b1 != b2)
                                    {
                                        if (l1 > j2)
                                        {
                                            l1 = j2;
                                        }

                                        if (i2 < j2)
                                        {
                                            i2 = j2;
                                        }

                                        p_77872_3_.field_76198_e[k1 + j2 * 128] = b2;
                                    }
                                }
                            }
                        }
                    }

                    if (l1 <= i2)
                    {
                        p_77872_3_.func_76194_a(k1, l1, i2);
                    }
                }
            }
        }
    }

    public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_)
    {
        if (!p_77663_2_.field_72995_K)
        {
            MapData mapdata = this.func_77873_a(p_77663_1_, p_77663_2_);

            if (p_77663_3_ instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)p_77663_3_;
                mapdata.func_76191_a(entityplayer, p_77663_1_);
            }

            if (p_77663_5_)
            {
                this.func_77872_a(p_77663_2_, p_77663_3_, mapdata);
            }
        }
    }

    public Packet func_150911_c(ItemStack p_150911_1_, World p_150911_2_, EntityPlayer p_150911_3_)
    {
        byte[] abyte = this.func_77873_a(p_150911_1_, p_150911_2_).func_76193_a(p_150911_1_, p_150911_2_, p_150911_3_);
        return abyte == null ? null : new S34PacketMaps(p_150911_1_.func_77960_j(), abyte);
    }

    public void func_77622_d(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_)
    {
        if (p_77622_1_.func_77942_o() && p_77622_1_.func_77978_p().func_74767_n("map_is_scaling"))
        {
            MapData mapdata = Items.FILLED_MAP.func_77873_a(p_77622_1_, p_77622_2_);
            p_77622_1_.func_77964_b(p_77622_2_.func_72841_b("map"));
            MapData mapdata1 = new MapData("map_" + p_77622_1_.func_77960_j());
            mapdata1.field_76197_d = (byte)(mapdata.field_76197_d + 1);

            if (mapdata1.field_76197_d > 4)
            {
                mapdata1.field_76197_d = 4;
            }

            mapdata1.field_76201_a = mapdata.field_76201_a;
            mapdata1.field_76199_b = mapdata.field_76199_b;
            mapdata1.field_76200_c = mapdata.field_76200_c;
            mapdata1.func_76185_a();
            p_77622_2_.func_72823_a("map_" + p_77622_1_.func_77960_j(), mapdata1);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
    {
        MapData mapdata = this.func_77873_a(p_77624_1_, p_77624_2_.field_70170_p);

        if (p_77624_4_)
        {
            if (mapdata == null)
            {
                p_77624_3_.add("Unknown map");
            }
            else
            {
                p_77624_3_.add("Scaling at 1:" + (1 << mapdata.field_76197_d));
                p_77624_3_.add("(Level " + mapdata.field_76197_d + "/" + 4 + ")");
            }
        }
    }
}