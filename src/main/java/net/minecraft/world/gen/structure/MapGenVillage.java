package net.minecraft.world.gen.structure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenVillage extends MapGenStructure
{
    public static List field_75055_e = Arrays.asList(new BiomeGenBase[] {BiomeGenBase.field_76772_c, BiomeGenBase.field_76769_d, BiomeGenBase.field_150588_X});
    private int field_75054_f;
    private int field_82665_g;
    private int field_82666_h;
    private static final String __OBFID = "CL_00000514";

    public MapGenVillage()
    {
        this.field_82665_g = 32;
        this.field_82666_h = 8;
    }

    public MapGenVillage(Map p_i2093_1_)
    {
        this();
        Iterator iterator = p_i2093_1_.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("size"))
            {
                this.field_75054_f = MathHelper.func_82714_a((String)entry.getValue(), this.field_75054_f, 0);
            }
            else if (((String)entry.getKey()).equals("distance"))
            {
                this.field_82665_g = MathHelper.func_82714_a((String)entry.getValue(), this.field_82665_g, this.field_82666_h + 1);
            }
        }
    }

    public String func_143025_a()
    {
        return "Village";
    }

    protected boolean func_75047_a(int p_75047_1_, int p_75047_2_)
    {
        int k = p_75047_1_;
        int l = p_75047_2_;

        if (p_75047_1_ < 0)
        {
            p_75047_1_ -= this.field_82665_g - 1;
        }

        if (p_75047_2_ < 0)
        {
            p_75047_2_ -= this.field_82665_g - 1;
        }

        int i1 = p_75047_1_ / this.field_82665_g;
        int j1 = p_75047_2_ / this.field_82665_g;
        Random random = this.field_75039_c.func_72843_D(i1, j1, 10387312);
        i1 *= this.field_82665_g;
        j1 *= this.field_82665_g;
        i1 += random.nextInt(this.field_82665_g - this.field_82666_h);
        j1 += random.nextInt(this.field_82665_g - this.field_82666_h);

        if (k == i1 && l == j1)
        {
            boolean flag = this.field_75039_c.func_72959_q().func_76940_a(k * 16 + 8, l * 16 + 8, 0, field_75055_e);

            if (flag)
            {
                return true;
            }
        }

        return false;
    }

    protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_)
    {
        return new MapGenVillage.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_, this.field_75054_f);
    }

    public static class Start extends StructureStart
        {
            private boolean field_75076_c;
            private static final String __OBFID = "CL_00000515";

            public Start() {}

            public Start(World p_i2092_1_, Random p_i2092_2_, int p_i2092_3_, int p_i2092_4_, int p_i2092_5_)
            {
                super(p_i2092_3_, p_i2092_4_);
                List list = StructureVillagePieces.func_75084_a(p_i2092_2_, p_i2092_5_);
                StructureVillagePieces.Start start = new StructureVillagePieces.Start(p_i2092_1_.func_72959_q(), 0, p_i2092_2_, (p_i2092_3_ << 4) + 2, (p_i2092_4_ << 4) + 2, list, p_i2092_5_);
                this.field_75075_a.add(start);
                start.func_74861_a(start, this.field_75075_a, p_i2092_2_);
                List list1 = start.field_74930_j;
                List list2 = start.field_74932_i;
                int l;

                while (!list1.isEmpty() || !list2.isEmpty())
                {
                    StructureComponent structurecomponent;

                    if (list1.isEmpty())
                    {
                        l = p_i2092_2_.nextInt(list2.size());
                        structurecomponent = (StructureComponent)list2.remove(l);
                        structurecomponent.func_74861_a(start, this.field_75075_a, p_i2092_2_);
                    }
                    else
                    {
                        l = p_i2092_2_.nextInt(list1.size());
                        structurecomponent = (StructureComponent)list1.remove(l);
                        structurecomponent.func_74861_a(start, this.field_75075_a, p_i2092_2_);
                    }
                }

                this.func_75072_c();
                l = 0;
                Iterator iterator = this.field_75075_a.iterator();

                while (iterator.hasNext())
                {
                    StructureComponent structurecomponent1 = (StructureComponent)iterator.next();

                    if (!(structurecomponent1 instanceof StructureVillagePieces.Road))
                    {
                        ++l;
                    }
                }

                this.field_75076_c = l > 2;
            }

            public boolean func_75069_d()
            {
                return this.field_75076_c;
            }

            public void func_143022_a(NBTTagCompound p_143022_1_)
            {
                super.func_143022_a(p_143022_1_);
                p_143022_1_.func_74757_a("Valid", this.field_75076_c);
            }

            public void func_143017_b(NBTTagCompound p_143017_1_)
            {
                super.func_143017_b(p_143017_1_);
                this.field_75076_c = p_143017_1_.func_74767_n("Valid");
            }
        }
}