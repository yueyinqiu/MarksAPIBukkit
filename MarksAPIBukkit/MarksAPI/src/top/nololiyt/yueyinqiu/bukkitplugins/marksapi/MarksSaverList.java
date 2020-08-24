package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksSaverInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions.OccupiedKeyException;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MarksSaverList
{
    private Map<MarksSaverInfo, MarksSaver> marksSavers = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public void add(@NotNull MarksSaver marksSaver)
    {
        String name = marksSaver.getInfo().getKey();
        int priority = marksSaver.getInfo().getPriority();
    
        lock.readLock().lock();
        Set<MarksSaverInfo> infoSet = marksSavers.keySet();
        try
        {
            for (MarksSaverInfo info : infoSet)
            {
                if (name.equals(info.getKey()))
                    throw new OccupiedKeyException("A MarksSaver should have a unique key.");
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
    
    @Nullable
    MarksSaver getSaver(MarksSaverInfo info)
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
}
