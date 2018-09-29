package fitnessTracker.exceptions;

public class InvalidStepsException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public InvalidStepsException()
    {
        super();
    }

    public InvalidStepsException(String message)
    {
        super(message);
    }

}
