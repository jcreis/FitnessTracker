package fitnessTracker.exceptions;

public class AthleteDoesNotBelongToGroupException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public AthleteDoesNotBelongToGroupException()
    {
        super();
    }

    public AthleteDoesNotBelongToGroupException(String message)
    {
        super(message);
    }

}
