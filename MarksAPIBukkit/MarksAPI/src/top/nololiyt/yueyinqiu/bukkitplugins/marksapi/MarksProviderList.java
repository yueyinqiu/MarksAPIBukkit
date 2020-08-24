package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksProviderInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions.OccupiedKeyException;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A list records marks providers.
 */
public class MarksProviderList
{
    private Map<MarksProviderInfo, MarksProvider> marksProviders = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    MarksProviderList()
    {
    }
    
    /**
     * Add a provider.
     *
     * @param marksProvider The provider.
     * @throws OccupiedKeyException Already contains a provider with the same key.
     */
    public void add(@NotNull MarksProvider marksProvider)
    {
        String name = marksProvider.getInfo().getKey();
    
        lock.readLock().lock();
        Set<MarksProviderInfo> infoSet = marksProviders.keySet();
        try
        {
            for (MarksProviderInfo info : infoSet)
            {
                if (name.equals(info.getKey()))
                    throw new OccupiedKeyException("A marks provider should have a unique key.");
            }
        }
        finally
        {
            lock.readLock().unlock();
        }
    
        lock.writeLock().lock();
        try
        {
            marksProviders.put(marksProvider.getInfo(), marksProvider);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Remove a provider.
     *
     * @param provider The provider.
     * @return Whether the provider is successfully removed.
     */
    public boolean remove(@NotNull MarksProvider provider)
    {
        try
        {
            lock.writeLock().lock();
            return marksProviders.remove(provider.getInfo(), provider);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Returns all providers' info.
     *
     * @return The info.
     */
    @NotNull
    public Set<MarksProviderInfo> getAllProvidersInfo()
    {
        try
        {
            lock.readLock().lock();
            return Collections.unmodifiableSet(marksProviders.keySet());
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Returns the number of providers in this list.
     *
     * @return The number.
     */
    public int size()
    {
        try
        {
            lock.readLock().lock();
            return marksProviders.size();
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
    
    @NotNull
    Collection<MarksProvider> getAllProviders()
    {
        try
        {
            lock.readLock().lock();
            return Collections.unmodifiableCollection(marksProviders.values());
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
    
    @Nullable
    MarksProvider getProvider(@NotNull MarksProviderInfo info)
    {
        try
        {
            lock.readLock().lock();
            return marksProviders.get(info);
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
}