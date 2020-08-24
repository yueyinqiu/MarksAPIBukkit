package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions.IllegalPrefixException;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions.OccupiedPrefixException;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A list records marks providers.
 */
public class MarksProviderList
{
    private Map<String, MarksProvider> marksProviders = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    MarksProviderList()
    {
    }
    
    private static final String LEGAL_CHARACTERS = "abcdefghijklmnopqrstuvwxyz1234567890";
    /**
     * Add a provider.
     *
     * @param marksProvider The provider.
     * @throws OccupiedPrefixException Already contains a provider with the same prefix.
     * @throws IllegalPrefixException The prefix is illegal.
     */
    public void add(@NotNull MarksProvider marksProvider)
            throws OccupiedPrefixException,IllegalPrefixException
    {
        String prefix = marksProvider.getPrefix();
        if (prefix.isEmpty())
            throw new IllegalPrefixException("Prefix should not be an empty string.");
    
        for (char c : prefix.toLowerCase().toCharArray())
        {
            if (LEGAL_CHARACTERS.indexOf(c) == -1)
                throw new IllegalPrefixException("Character '+" + c + "' shouldn't exists in a prefix.");
        }
        lock.writeLock().lock();
        try
        {
            marksProviders.put(prefix, marksProvider);
        }
        catch (IllegalArgumentException e)
        {
            throw new OccupiedPrefixException("A marks provider should have a unique prefix.", e);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Remove a provider.
     *
     * @param marksProvider The provider.
     * @return Whether the provider is successfully removed.
     */
    public boolean remove(@NotNull MarksProvider marksProvider)
    {
        try
        {
            lock.writeLock().lock();
            return marksProviders.remove(marksProvider.getPrefix(), marksProvider);
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
    public Set<String> getAllKeys()
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
    Collection<MarksProvider> getAll()
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
    MarksProvider get(@NotNull String key)
    {
        try
        {
            lock.readLock().lock();
            return marksProviders.get(key);
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
}