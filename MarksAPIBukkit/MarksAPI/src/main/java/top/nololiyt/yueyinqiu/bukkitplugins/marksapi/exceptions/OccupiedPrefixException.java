package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions;

/**
 * This exception is thrown when you want to add items into an operator list but an operator with same prefix has existed.
 */
public class OccupiedPrefixException extends RuntimeException
{
    /**
     * Constructs a new OccupiedPrefixException.
     */
    public OccupiedPrefixException()
    {
    }
    
    /**
     * Constructs a new OccupiedPrefixException with a message.
     *
     * @param message The message.
     */
    public OccupiedPrefixException(String message)
    {
        super(message);
    }
    
    /**
     * Constructs a new OccupiedPrefixException with a message and a cause.
     *
     * @param message The message.
     * @param cause   The cause.
     */
    public OccupiedPrefixException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    /**
     * Constructs a new OccupiedPrefixException with a cause.
     *
     * @param cause The cause.
     */
    public OccupiedPrefixException(Throwable cause)
    {
        super(cause);
    }
}