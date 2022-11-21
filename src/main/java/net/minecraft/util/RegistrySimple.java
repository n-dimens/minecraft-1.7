package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrySimple implements IRegistry
{
    private static final Logger LOG = LogManager.getLogger();
    protected final Map<Object, Object> registry = this.createEmpty();
    private static final String __OBFID = "CL_00001210";

    protected Map<Object, Object> createEmpty()
    {
        return Maps.newHashMap();
    }

    @Override
    public Object get(Object key)
    {
        return this.registry.get(key);
    }

    @Override
    public void add(Object key, Object registryItem)
    {
        if (this.registry.containsKey(key))
        {
            LOG.debug("Adding duplicate key '" + key + "' to registry");
        }

        this.registry.put(key, registryItem);
    }

    public Set func_148742_b()
    {
        return Collections.unmodifiableSet(this.registry.keySet());
    }

    public boolean func_148741_d(Object p_148741_1_)
    {
        return this.registry.containsKey(p_148741_1_);
    }
}