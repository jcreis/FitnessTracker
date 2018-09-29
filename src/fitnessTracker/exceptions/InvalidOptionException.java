package fitnessTracker.exceptions;

public class InvalidOptionException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public InvalidOptionException()
    {
        super();
    }

    public InvalidOptionException(String message)
    {
        super(message);
    }

}
