package dataStructures;

public class EmptyPriorityQueueException extends RuntimeException
{                                     

    static final long serialVersionUID = 0L;


    public EmptyPriorityQueueException( )   
    {
        super();
    }

    public EmptyPriorityQueueException( String message )
    {
        super(message);
    }

}
