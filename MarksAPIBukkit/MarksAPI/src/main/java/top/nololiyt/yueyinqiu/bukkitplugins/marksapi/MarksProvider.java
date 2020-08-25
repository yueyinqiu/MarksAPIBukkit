package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

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
    String getPrefix();
    
    /**
     * Returns a mark whose key is the given key; or null if the mark doesn't exist or the permission checker doesn't contains the required permission.
     *
     * @param key           The given key.
     * @param markRelatedValues The related values.
     * @return The mark.
     */
    Location getMark( String key,  MarkRelatedValues markRelatedValues);
    
    /**
     * Returns all the marks' keys, except those the checker doesn't contains the required permission.
     *
     * @param markRelatedValues The related values.
     * @return All the keys.
     */
    
    Iterable<String> getAllMarksKey( MarkRelatedValues markRelatedValues);
}