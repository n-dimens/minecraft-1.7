package net.minecraft.util;

public interface IRegistry
{
    Object get(Object key);

    void add(Object key, Object registryItem);
}