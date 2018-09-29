package fitnessTracker.exceptions;

public class InvalidMETException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public InvalidMETException()
    {
        super();
    }

    public InvalidMETException(String message)
    {
        super(message);
    }

}
