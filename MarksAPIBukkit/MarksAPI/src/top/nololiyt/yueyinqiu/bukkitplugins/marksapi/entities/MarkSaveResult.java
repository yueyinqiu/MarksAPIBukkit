package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.permissions.Permission;

/**
 * Represents the result of saving a mark.
 */
public interface MarkSaveResult
{
    /**
     * Represents the result state of saving a mark.
     */
    enum ResultState
    {
        /**
         * Represents the mark is successfully saved.
         */
        SUCEEDED,
        /**
         * Represents an exception was thrown.
         */
        EXCEPTION,
        /**
         * Represents the permission checker doesn't contains a required permission.
         */
        WITHOUT_PERMISSION,
        /**
         * Represents that there isn't such a saver.
         */
        NO_SUCH_SAVER,
        /**
         * Represents the states not listed here.
         */
        OTHERS
    }
    
    /**
     * Returns the result state.
     * @return The result state.
     */
    @NotNull
    ResultState getState();
    
    /**
     * Returns the message.
     * @return The message.
     */
    @NotNull
    String getMessage();
    
    /**
     * Returns the exception if the state is <code>ResultState.EXCEPTION</code>.
     * @return The exception.
     */
    @Nullable
    Exception getException();
    
    /**
     * Returns the required permission if the state is <code>ResultState.WITHOUT_PERMISSION</code>.
     * @return The permission.
     */
    @Nullable
    Permission getPermission();
}