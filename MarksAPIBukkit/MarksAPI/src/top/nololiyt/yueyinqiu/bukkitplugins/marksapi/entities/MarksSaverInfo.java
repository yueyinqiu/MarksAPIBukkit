package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

public final class MarksSaverInfo
{
    private String key;
    private int priority;
    
    public MarksSaverInfo(@NotNull String key, int priority)
    {
        this.key = key;
        this.priority = priority;
    }
    
    @NotNull
    public String getKey()
    {
        return key;
    }
    
    public int getPriority()
    {
        return priority;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarksSaverInfo that = (MarksSaverInfo) o;
        return priority == that.priority &&
                Objects.equals(key, that.key);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(key, priority);
    }
    
    @NotNull
    @Override
    public String toString()
    {
        return "MarksProviderInfo{" +
                "key='" + key + '\'' +
                ", priority=" + priority +
                '}';
    }
}
