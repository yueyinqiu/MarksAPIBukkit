package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

final class NullChecker
{
    static <E> void CheckArgument(String argumentName, E value)
    {
        if(value ==null)
            throw new IllegalArgumentException(argumentName + " could not be null.");
    }
}