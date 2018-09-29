package fitnessTracker.exceptions;

public class AthleteDoesNotExistException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public AthleteDoesNotExistException()
    {
        super();
    }

    public AthleteDoesNotExistException(String message)
    {
        super(message);
    }

}
