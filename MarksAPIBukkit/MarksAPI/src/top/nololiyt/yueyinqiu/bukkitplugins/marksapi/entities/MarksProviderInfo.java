package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

public final class MarksProviderInfo
{
    private String key;
    
    public MarksProviderInfo(@NotNull String key)
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
        MarksProviderInfo that = (MarksProviderInfo) o;
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
