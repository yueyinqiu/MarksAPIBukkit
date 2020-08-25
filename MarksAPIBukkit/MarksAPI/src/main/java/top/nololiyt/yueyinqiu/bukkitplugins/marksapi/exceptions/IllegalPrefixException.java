package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions;

/**
 * This exception is thrown when the prefix of a operator has a illegal character, or prefix is empty.
 */
public class IllegalPrefixException extends RuntimeException
{
    /**
     * Constructs a new <code>IllegalPrefixException</code>.
     */
    public IllegalPrefixException()
    {
    }
    
    /**
     * Constructs a new <code>IllegalPrefixException</code> with a message.
     *
     * @param message The message.
     */
    public IllegalPrefixException(String message)
    {
        super(message);
    }
    
    /**
     * Constructs a new <code>IllegalPrefixException</code> with a message and a cause.
     *
     * @param message The message.
     * @param cause   The cause.
     */
    public IllegalPrefixException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    /**
     * Constructs a new <code>IllegalPrefixException</code> with a cause.
     *
     * @param cause The cause.
     */
    public IllegalPrefixException(Throwable cause)
    {
        super(cause);
    }
}