package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.permissioncheckers;

import org.bukkit.permissions.Permission;

/**
 * Represents a permission checker.
 */
public interface PermissionChecker
{
    /**
     * Check whether the object has the permission.
     * @param permission The permission.
     * @return Whether the object has the permission.
     */
    boolean containsPermission(Permission permission);
    
    /**
     * Check whether the object has the permission.
     * @param permission The permission.
     * @return Whether the object has the permission.
     */
    boolean containsPermission(String permission);
}
