package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions;

/**
 * This exception is thrown when you want to add items into a collection but an item with same key has existed.
 */
public class OccupiedKeyException extends RuntimeException
{
    /**
     * Constructs a new <code>NoSuchMechanismException</code>.
     */
    public OccupiedKeyException()
    {
    }
    
    /**
     * Constructs a new <code>NoSuchMechanismException</code> with a message.
     *
     * @param message The message.
     */
    public OccupiedKeyException(String message)
    {
        super(message);
    }
    
    /**
     * Constructs a new <code>NoSuchMechanismException</code> with a message and a cause.
     *
     * @param message The message.
     * @param cause   The cause.
     */
    public OccupiedKeyException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    /**
     * Constructs a new <code>NoSuchMechanismException</code> with a cause.
     *
     * @param cause The cause.
     */
    public OccupiedKeyException(Throwable cause)
    {
        super(cause);
    }
}