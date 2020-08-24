package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities;

import com.sun.istack.internal.NotNull;
import org.bukkit.Location;

import java.util.Objects;

public final class Mark
{
    private String key;
    private Location position;
    
    @NotNull
    public String getKey()
    {
        return key;
    }
    
    @NotNull
    public Location getPosition()
    {
        return position;
    }
    
    public Mark(@NotNull String key, @NotNull Location position)
    {
        this.key = key;
        this.position = position;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return Objects.equals(key, mark.key) &&
                Objects.equals(position, mark.position);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(key, position);
    }
    
    @NotNull
    @Override
    public String toString()
    {
        return "Mark{" +
                "key='" + key + '\'' +
                ", position=" + position +
                '}';
    }
}
