package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.exceptions;

public class OccupiedKeyException extends RuntimeException
{
    public OccupiedKeyException()
    {
    }
    
    public OccupiedKeyException(String message)
    {
        super(message);
    }
    
    public OccupiedKeyException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public OccupiedKeyException(Throwable cause)
    {
        super(cause);
    }
}
