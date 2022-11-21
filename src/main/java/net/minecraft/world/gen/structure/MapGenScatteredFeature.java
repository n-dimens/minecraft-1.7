package net.minecraft.world.gen.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenScatteredFeature extends MapGenStructure
{
    private static List field_75061_e = Arrays.asList(new BiomeGenBase[] {BiomeGenBase.field_76769_d, BiomeGenBase.field_76786_s, BiomeGenBase.field_76782_w, BiomeGenBase.field_76792_x, BiomeGenBase.field_76780_h});
    private List field_82668_f;
    private int field_82669_g;
    private int field_82670_h;
    private static final String __OBFID = "CL_00000471";

    public MapGenScatteredFeature()
    {
        this.field_82668_f = new ArrayList();
        this.field_82669_g = 32;
        this.field_82670_h = 8;
        this.field_82668_f.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 1, 1, 1));
    }

    public MapGenScatteredFeature(Map p_i2061_1_)
    {
        this();
        Iterator iterator = p_i2061_1_.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();

            if (((String)entry.getKey()).equals("distance"))
            {
                this.field_82669_g = MathHelper.func_82714_a((String)entry.getValue(), this.field_82669_g, this.field_82670_h + 1);
            }
        }
    }

    public String func_143025_a()
    {
        return "Temple";
    }

    protected boolean func_75047_a(int p_75047_1_, int p_75047_2_)
    {
        int k = p_75047_1_;
        int l = p_75047_2_;

        if (p_75047_1_ < 0)
        {
            p_75047_1_ -= this.field_82669_g - 1;
        }

        if (p_75047_2_ < 0)
        {
            p_75047_2_ -= this.field_82669_g - 1;
        }

        int i1 = p_75047_1_ / this.field_82669_g;
        int j1 = p_75047_2_ / this.field_82669_g;
        Random random = this.field_75039_c.func_72843_D(i1, j1, 14357617);
        i1 *= this.field_82669_g;
        j1 *= this.field_82669_g;
        i1 += random.nextInt(this.field_82669_g - this.field_82670_h);
        j1 += random.nextInt(this.field_82669_g - this.field_82670_h);

        if (k == i1 && l == j1)
        {
            BiomeGenBase biomegenbase = this.field_75039_c.func_72959_q().func_76935_a(k * 16 + 8, l * 16 + 8);
            Iterator iterator = field_75061_e.iterator();

            while (iterator.hasNext())
            {
                BiomeGenBase biomegenbase1 = (BiomeGenBase)iterator.next();

                if (biomegenbase == biomegenbase1)
                {
                    return true;
                }
            }
        }

        return false;
    }

    protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_)
    {
        return new MapGenScatteredFeature.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_);
    }

    public boolean func_143030_a(int p_143030_1_, int p_143030_2_, int p_143030_3_)
    {
        StructureStart structurestart = this.func_143028_c(p_143030_1_, p_143030_2_, p_143030_3_);

        if (structurestart != null && structurestart instanceof MapGenScatteredFeature.Start && !structurestart.field_75075_a.isEmpty())
        {
            StructureComponent structurecomponent = (StructureComponent)structurestart.field_75075_a.getFirst();
            return structurecomponent instanceof ComponentScatteredFeaturePieces.SwampHut;
        }
        else
        {
            return false;
        }
    }

    public List func_82667_a()
    {
        return this.field_82668_f;
    }

    public static class Start extends StructureStart
        {
            private static final String __OBFID = "CL_00000472";

            public Start() {}

            public Start(World p_i2060_1_, Random p_i2060_2_, int p_i2060_3_, int p_i2060_4_)
            {
                super(p_i2060_3_, p_i2060_4_);
                BiomeGenBase biomegenbase = p_i2060_1_.func_72807_a(p_i2060_3_ * 16 + 8, p_i2060_4_ * 16 + 8);

                if (biomegenbase != BiomeGenBase.field_76782_w && biomegenbase != BiomeGenBase.field_76792_x)
                {
                    if (biomegenbase == BiomeGenBase.field_76780_h)
                    {
                        ComponentScatteredFeaturePieces.SwampHut swamphut = new ComponentScatteredFeaturePieces.SwampHut(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
                        this.field_75075_a.add(swamphut);
                    }
                    else
                    {
                        ComponentScatteredFeaturePieces.DesertPyramid desertpyramid = new ComponentScatteredFeaturePieces.DesertPyramid(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
                        this.field_75075_a.add(desertpyramid);
                    }
                }
                else
                {
                    ComponentScatteredFeaturePieces.JunglePyramid junglepyramid = new ComponentScatteredFeaturePieces.JunglePyramid(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
                    this.field_75075_a.add(junglepyramid);
                }

                this.func_75072_c();
            }
        }
}