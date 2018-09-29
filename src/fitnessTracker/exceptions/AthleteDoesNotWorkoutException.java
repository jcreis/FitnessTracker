package fitnessTracker.exceptions;

public class AthleteDoesNotWorkoutException extends RuntimeException
{

    static final long serialVersionUID = 0L;

    public AthleteDoesNotWorkoutException()
    {
        super();
    }

    public AthleteDoesNotWorkoutException(String message)
    {
        super(message);
    }

}
