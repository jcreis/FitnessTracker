package fitnessTracker.exceptions;

public class ActivityDoesNotExistException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public ActivityDoesNotExistException()
    {
        super();
    }

    public ActivityDoesNotExistException(String message)
    {
        super(message);
    }

}
