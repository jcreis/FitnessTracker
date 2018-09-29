package fitnessTracker.exceptions;

public class NoGroupsInSystemException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public NoGroupsInSystemException()
    {
        super();
    }

    public NoGroupsInSystemException(String message)
    {
        super(message);
    }

}
