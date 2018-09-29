package fitnessTracker.exceptions;

public class GroupAlreadyExistException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public GroupAlreadyExistException()
    {
        super();
    }

    public GroupAlreadyExistException(String message)
    {
        super(message);
    }

}
