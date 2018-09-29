package fitnessTracker.exceptions;

public class InvalidTimeException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public InvalidTimeException()
    {
        super();
    }

    public InvalidTimeException(String message)
    {
        super(message);
    }

}
