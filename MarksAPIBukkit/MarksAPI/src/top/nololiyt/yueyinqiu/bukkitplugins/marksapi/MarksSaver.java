package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import org.bukkit.Location;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarkSaveResult;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksSaverInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.permissioncheckers.PermissionChecker;

/**
 * Represents a marks saver which can save marks. Most saver should also be a provider.
 */
public interface MarksSaver
{
    /**
     * Returns the info of the current saver.
     *
     * @return The info.
     */
    @NotNull
    MarksSaverInfo getInfo();
    
    /**
     * Save a mark if the permission checker contains the required permission.
     *
     * @param key               The key.
     * @param mark              The mark.
     * @param permissionChecker The permission checker.
     * @return A result.
     */
    @NotNull
    MarkSaveResult saveMark(@NotNull String key,
                            @NotNull Location mark,
                            @NotNull PermissionChecker permissionChecker);
}