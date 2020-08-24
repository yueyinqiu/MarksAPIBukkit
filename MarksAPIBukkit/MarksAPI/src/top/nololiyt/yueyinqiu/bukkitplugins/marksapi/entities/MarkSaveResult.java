package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities;


public interface MarkSaveResult
{
    enum ResultState
    {
        SUCEEDED,
        EXCEPTION,
        NO_SUCH_SAVER
    }
    
    ResultState getState();
    
    Exception getException();
    
    String getMessage();
}