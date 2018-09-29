package fitnessTracker.exceptions;

public class GroupDoesNotExistException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public GroupDoesNotExistException()
    {
        super();
    }

    public GroupDoesNotExistException(String message)
    {
        super(message);
    }

}
