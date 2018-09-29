package fitnessTracker.exceptions;

public class GroupHasNoAthletesException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public GroupHasNoAthletesException()
    {
        super();
    }

    public GroupHasNoAthletesException(String message)
    {
        super(message);
    }

}
