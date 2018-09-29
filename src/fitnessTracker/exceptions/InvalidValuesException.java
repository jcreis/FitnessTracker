package fitnessTracker.exceptions;

public class InvalidValuesException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public InvalidValuesException()
    {
        super();
    }

    public InvalidValuesException(String message)
    {
        super(message);
    }

}
