package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.Location;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksProviderInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.permissioncheckers.PermissionChecker;

import java.util.Collection;

/**
 * Represents a marks provider which can provide marks.
 */
public interface MarksProvider
{
    /**
     * Returns the info of the current provider.
     *
     * @return The info.
     */
    @NotNull
    MarksProviderInfo getInfo();
    
    /**
     * Returns a mark whose key is the given key; or null if the mark doesn't exist or the permission checker doesn't contains the required permission.
     *
     * @param key               The given key.
     * @param permissionChecker The permission checker.
     * @return The mark.
     */
    @Nullable
    Location getMark(@NotNull String key, @NotNull PermissionChecker permissionChecker);
    
    /**
     * Returns all the marks' keys, except those the checker doesn't contains the required permission.
     *
     * @param permissionChecker The permission checker.
     * @return All the keys.
     */
    @NotNull
    Collection<String> getAllMarksKey(@NotNull PermissionChecker permissionChecker);
}