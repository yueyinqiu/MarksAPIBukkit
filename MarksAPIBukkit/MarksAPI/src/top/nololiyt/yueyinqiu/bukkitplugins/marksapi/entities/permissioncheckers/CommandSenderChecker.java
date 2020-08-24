package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.permissioncheckers;

import com.sun.istack.internal.NotNull;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

/**
 * Represents a permission checker whose object is a command sender.
 */
public class CommandSenderChecker implements PermissionChecker
{
    private CommandSender sender;
    
    /**
     * Constructs a new <code>CommandSenderChecker</code>.
     *
     * @param sender The command sender.
     */
    public CommandSenderChecker(@NotNull CommandSender sender)
    {
        this.sender = sender;
    }
    
    /**
     * Check whether the object has the permission.
     *
     * @param permission The permission.
     * @return Whether the object has the permission.
     */
    @Override
    public boolean containsPermission(Permission permission)
    {
        return sender.hasPermission(permission);
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
        return sender.hasPermission(permission);
    }
}