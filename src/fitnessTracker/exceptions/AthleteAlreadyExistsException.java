package fitnessTracker.exceptions;

public class AthleteAlreadyExistsException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public AthleteAlreadyExistsException()
    {
        super();
    }

    public AthleteAlreadyExistsException(String message)
    {
        super(message);
    }

}
