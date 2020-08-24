package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions.IllegalPrefixException;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions.OccupiedPrefixException;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A list records marks operators.
 */
public class MarksOperatorList<E extends MarksOperator>
{
    private Map<String, E> marksOperators = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    MarksOperatorList()
    {
    }
    
    private static final String LEGAL_CHARACTERS = "abcdefghijklmnopqrstuvwxyz1234567890";
    /**
     * Add a operator.
     *
     * @param marksOperator The operator.
     * @throws OccupiedPrefixException Already contains a operator with the same prefix.
     * @throws IllegalPrefixException The prefix is illegal.
     */
    public void add(@NotNull E marksOperator)
            throws OccupiedPrefixException,IllegalPrefixException
    {
        String prefix = marksOperator.getPrefix();
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
            marksOperators.put(prefix, marksOperator);
        }
        catch (IllegalArgumentException e)
        {
            throw new OccupiedPrefixException("A marks operator should have a unique prefix.", e);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Remove a operator.
     *
     * @param marksOperator The operator.
     * @return Whether the operator is successfully removed.
     */
    public boolean remove(@NotNull E marksOperator)
    {
        try
        {
            lock.writeLock().lock();
            return marksOperators.remove(marksOperator.getPrefix(), marksOperator);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Returns all operators' info.
     *
     * @return The info.
     */
    @NotNull
    public Set<String> getAllKeys()
    {
        try
        {
            lock.readLock().lock();
            return Collections.unmodifiableSet(marksOperators.keySet());
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Returns the number of operators in this list.
     *
     * @return The number.
     */
    public int size()
    {
        try
        {
            lock.readLock().lock();
            return marksOperators.size();
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
    
    @NotNull
    Collection<E> getAll()
    {
        try
        {
            lock.readLock().lock();
            return Collections.unmodifiableCollection(marksOperators.values());
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
    
    @Nullable
    E get(@NotNull String key)
    {
        try
        {
            lock.readLock().lock();
            return marksOperators.get(key);
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
}