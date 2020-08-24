package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksSaverInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions.OccupiedKeyException;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A list records marks savers.
 */
public class MarksSaverList
{
    private Map<MarksSaverInfo, MarksSaver> marksSavers = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    MarksSaverList()
    {
    }
    
    /**
     * Add a saver.
     *
     * @param marksSaver The saver.
     * @throws OccupiedKeyException Already contains a saver with the same key.
     */
    public void add(@NotNull MarksSaver marksSaver)
    {
        String name = marksSaver.getInfo().getKey();
    
        lock.readLock().lock();
        Set<MarksSaverInfo> infoSet = marksSavers.keySet();
        try
        {
            for (MarksSaverInfo info : infoSet)
            {
                if (name.equals(info.getKey()))
                    throw new OccupiedKeyException("A marks saver should have a unique key.");
            }
        }
        finally
        {
            lock.readLock().unlock();
        }
    
        lock.writeLock().lock();
        try
        {
            marksSavers.put(marksSaver.getInfo(), marksSaver);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Remove a saver.
     *
     * @param saver The saver.
     * @return Whether the saver is successfully removed.
     */
    public boolean remove(@NotNull MarksSaver saver)
    {
        try
        {
            lock.writeLock().lock();
            return marksSavers.remove(saver.getInfo(), saver);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Returns all savers' info.
     *
     * @return The info.
     */
    @NotNull
    public Set<MarksSaverInfo> getAllSaversInfo()
    {
        try
        {
            lock.readLock().lock();
            return Collections.unmodifiableSet(marksSavers.keySet());
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Returns the number of savers in this list.
     *
     * @return The number.
     */
    public int size()
    {
        try
        {
            lock.readLock().lock();
            return marksSavers.size();
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
    
    @Nullable
    MarksSaver getSaver(@NotNull MarksSaverInfo info)
    {
        try
        {
            lock.readLock().lock();
            return marksSavers.get(info);
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
    
    @NotNull
    Collection<MarksSaver> getAllProviders()
    {
        try
        {
            lock.readLock().lock();
            return Collections.unmodifiableCollection(marksSavers.values());
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
}
