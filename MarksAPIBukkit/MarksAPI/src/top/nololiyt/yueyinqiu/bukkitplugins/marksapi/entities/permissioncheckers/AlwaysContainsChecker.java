package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.permissioncheckers;

import org.bukkit.permissions.Permission;

/**
 * Represents a permission checker whose object contains every permissions.
 */
public class AlwaysContainsChecker implements PermissionChecker
{
    public AlwaysContainsChecker(){ }
    /**
     * Check whether the object has the permission.
     *
     * @param permission The permission.
     * @return Whether the object has the permission.
     */
    @Override
    public boolean containsPermission(Permission permission)
    {
        return true;
    }
    
    /**
     * Check whether the object has the permission.
     *
     * @param permission The permission.
     * @return Whether the object has the permission.
     */
    @Override
    public boolean containsPermission(String permission)
    {
        return true;
    }
}