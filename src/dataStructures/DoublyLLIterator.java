package dataStructures;

import dataStructures.exceptions.*;

class DoublyLLIterator<E> implements TwoWayIterator<E>
{

    static final long serialVersionUID = 0L;


    // Node with the first element in the iteration.
    protected DListNode<E> firstNode;

    // Node with the last element in the iteration.
    protected DListNode<E> lastNode;

    // Node with the next element in the iteration.
    protected DListNode<E> nextToReturn;

    // Node with the previous element in the iteration.
    protected DListNode<E> prevToReturn;


    public DoublyLLIterator( DListNode<E> first, DListNode<E> last )
    {
        firstNode = first;
        lastNode = last;
        this.rewind();
    }      


    // Restarts the iteration.
    // After rewind, if the iteration is not empty,
    // next will return the first element in the iteration.
    public void rewind( )
    {
        nextToReturn = firstNode;
        prevToReturn = null;
    }


    // Restarts the iteration in the reverse direction.
    // After fullForward, if the iteration is not empty,
    // previous will return the last element in the iteration.
    public void fullForward( )
    {
        prevToReturn = lastNode;
        nextToReturn = null;
    }


    // Returns true iff the iteration has more elements. 
    // In other words, returns true if next would return an element 
    // rather than throwing an exception.
    public boolean hasNext( )
    {
        return nextToReturn != null;
    }


    // Returns true iff the iteration has more elements
    // in the reverse direction.
    // In other words, returns true if previous would return an element 
    // rather than throwing an exception.
    public boolean hasPrevious( )
    {
        return prevToReturn != null;
    }


    // Returns the next element in the iteration.
    public E next( ) throws NoSuchElementException
    {
        if ( !this.hasNext() )
            throw new NoSuchElementException();

        E element = nextToReturn.getElement();
        prevToReturn = nextToReturn.getPrevious();
        nextToReturn = nextToReturn.getNext();
        return element;
    }


    // Returns the previous element in the iteration.
    public E previous( ) throws NoSuchElementException
    {
        if ( !this.hasPrevious() )
            throw new NoSuchElementException();

        E element = prevToReturn.getElement();
        nextToReturn = prevToReturn.getNext();
        prevToReturn = prevToReturn.getPrevious();
        return element;
    }


}
