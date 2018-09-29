package fitnessTracker.exceptions;

public class ActivityAlreadyExistsException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public ActivityAlreadyExistsException()
    {
        super();
    }

    public ActivityAlreadyExistsException(String message)
    {
        super(message);
    }

}
