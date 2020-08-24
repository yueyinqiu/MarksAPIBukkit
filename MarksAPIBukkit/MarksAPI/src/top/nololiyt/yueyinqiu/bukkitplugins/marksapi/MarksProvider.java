package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.Location;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarkRelatedValues;

/**
 * Represents a marks provider which can provide marks.
 */
public interface MarksProvider
{
    /**
     * Returns the prefix of the provider.
     *
     * @return The prefix.
     */
    @NotNull
    String getPrefix();
    
    /**
     * Returns a mark whose key is the given key; or null if the mark doesn't exist or the permission checker doesn't contains the required permission.
     *
     * @param key           The given key.
     * @param markRelatedValues The related values.
     * @return The mark.
     */
    @Nullable
    Location getMark(@NotNull String key, @NotNull MarkRelatedValues markRelatedValues);
    
    /**
     * Returns all the marks' keys, except those the checker doesn't contains the required permission.
     *
     * @param markRelatedValues The related values.
     * @return All the keys.
     */
    @NotNull
    Iterable<String> getAllMarksKey(@NotNull MarkRelatedValues markRelatedValues);
}