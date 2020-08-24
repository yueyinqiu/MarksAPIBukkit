package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

public final class MarksSaverInfo
{
    private String key;
    
    public MarksSaverInfo(@NotNull String key)
    {
        this.key = key;
    }
    
    @NotNull
    public String getKey()
    {
        return key;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarksSaverInfo that = (MarksSaverInfo) o;
        return Objects.equals(key, that.key);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(key);
    }
    
    @NotNull
    @Override
    public String toString()
    {
        return "MarksProviderInfo{" +
                "key='" + key + '\'' +
                '}';
    }
}
