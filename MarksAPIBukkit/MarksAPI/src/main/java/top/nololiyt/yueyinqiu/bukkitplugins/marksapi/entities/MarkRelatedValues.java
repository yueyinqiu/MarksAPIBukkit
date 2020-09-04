package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

/**
 * Represents some values related to a mark.
 */
public interface MarkRelatedValues
{
    /**
     * Returns the owner of the mark.
     * With this value, different players could have different marks with a same key.
     * It could be null and the provider should just return the static marks which don't have an owner.
     * When it is specified, the provider should still returns the marks owned by the owner and also the static marks.
     *
     * @return The owner.
     */
    Player getOwner();
    
    /**
     * Returns permissible trying to get the mark.
     * The provider should only return the marks the permissible have access to.
     * Sometimes the provider can cast it to Player to get other information and decide whether the player having access to a mark.
     * It could be null and the provider should just return all the marks.
     *
     * @return The permissible.
     */
    Permissible getPermissible();
}