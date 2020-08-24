package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksProviderInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions.OccupiedKeyException;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MarksProviderList
{
    private Map<MarksProviderInfo, MarksProvider> marksProviders = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public void add(@NotNull MarksProvider marksProvider)
    {
        lock.writeLock().lock();
        try
        {
            marksProviders.put(marksProvider.getInfo(), marksProvider);
        }
        catch (IllegalArgumentException e)
        {
            throw new OccupiedKeyException("A MarksProvider should have a unique key.", e);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }
    
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
}
