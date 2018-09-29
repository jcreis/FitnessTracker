package fitnessTracker.exceptions;

public class AthleteHasGroupAlreadyException extends RuntimeException
{

	static final long serialVersionUID = 0L;

    public AthleteHasGroupAlreadyException()
    {
        super();
    }

    public AthleteHasGroupAlreadyException(String message)
    {
        super(message);
    }
    
}
