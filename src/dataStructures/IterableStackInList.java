package dataStructures;

public class IterableStackInList<E> extends StackInList<E> implements IterableStack<E>{

	 static final long serialVersionUID = 0L;

	    public IterableStackInList()
	    {
	        super();
	    }      

	    public Iterator<E> iterator()
	    {
	        return list.iterator();
	    }
}
