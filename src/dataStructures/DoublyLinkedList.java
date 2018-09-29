package dataStructures;

import dataStructures.exceptions.*;

public class DoublyLinkedList<E> implements List<E>
{   

    static final long serialVersionUID = 0L;

    // Node at the head of the list.
    protected DListNode<E> head;

    // Node at the tail of the list.
    protected DListNode<E> tail;

    // Number of elements in the list.
    protected int currentSize;

    public DoublyLinkedList( )
    {
        head = null;
        tail = null;
        currentSize = 0;
    }
    
    // Returns true iff the list contains no elements.
    public boolean isEmpty( )
    {  
        return currentSize == 0;
    }

    // Returns the number of elements in the list.
    public int size( )
    {
        return currentSize;
    }

    // Returns an iterator of the elements in the list (in proper sequence).
    public Iterator<E> iterator( )
    {
        return new DoublyLLIterator<E>(head, tail);
    }
    
    // Returns the first element of the list.
    public E getFirst( ) throws EmptyListException
    {  
        if ( this.isEmpty() )
            throw new EmptyListException();

        return head.getElement();
    }

    // Returns the last element of the list.
    public E getLast( ) throws EmptyListException
    {  
        if ( this.isEmpty() )
            throw new EmptyListException();

        return tail.getElement();
    }
    
    // Returns the node at the specified position in the list.
    // Pre-condition: position ranges from 0 to currentSize-1.
    protected DListNode<E> getNode( int position ) 
    {
        DListNode<E> node;

        if ( position <= ( currentSize - 1 ) / 2 )
        {
            node = head;
            for ( int i = 0; i < position; i++ )
                node = node.getNext();
        }
        else
        {
            node = tail;
            for ( int i = currentSize - 1; i > position; i-- )
                node = node.getPrevious();

        }
        return node;
    }
    
    // Returns the element at the specified position in the list.
    // Range of valid positions: 0, ..., size()-1.
    // If the specified position is 0, get corresponds to getFirst. 
    // If the specified position is size()-1, get corresponds to getLast.    
    public E get( int position ) throws InvalidPositionException
    {
        if ( position < 0 || position >= currentSize )
            throw new InvalidPositionException();

        return this.getNode(position).getElement();
    }
    
    // Returns the position of the first occurrence of the specified element
    // in the list, if the list contains the element.
    // Otherwise, returns -1.
    public int find( E element )
    {
        DListNode<E> node = head;
        int position = 0;
        while ( node != null && !node.getElement().equals(element) )
        {
            node = node.getNext();
            position++;
        }
        if ( node == null )
            return -1;
        else
            return position;
    }
    
    public void addFirst( E element )
    {
        DListNode<E> newNode = new DListNode<E>(element, null, head);
        if ( this.isEmpty() )
            tail = newNode;
        else
            head.setPrevious(newNode);
        head = newNode;
        currentSize++;
    }

    // Inserts the specified element at the last position in the list.
    public void addLast( E element )
    {
        DListNode<E> newNode = new DListNode<E>(element, tail, null);
        if ( this.isEmpty() )
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        currentSize++;
    }

    // Inserts the specified element at the specified position in the list.
    // Pre-condition: position ranges from 1 to currentSize-1.
    protected void addMiddle( int position, E element )
    {
        DListNode<E> prevNode = this.getNode(position - 1);
        DListNode<E> nextNode = prevNode.getNext();
        DListNode<E> newNode = new DListNode<E>(element, prevNode, nextNode);
        prevNode.setNext(newNode);            
        nextNode.setPrevious(newNode);            
        currentSize++;
    }
    
    // Inserts the specified element at the specified position in the list.
    // Range of valid positions: 0, ..., size(). 
    // If the specified position is 0, add corresponds to addFirst.  
    // If the specified position is size(), add corresponds to addLast.
    public void add( int position, E element ) throws InvalidPositionException
    {
        if ( position < 0 || position > currentSize )
            throw new InvalidPositionException();

        if ( position == 0 )
            this.addFirst(element);
        else if ( position == currentSize )
            this.addLast(element);
        else
            this.addMiddle(position, element);
    }

    // Removes the first node in the list.
    // Pre-condition: the list is not empty.
    protected void removeFirstNode( )
    {
        head = head.getNext();
        if ( head == null )
            tail = null;
        else
            head.setPrevious(null);
        currentSize--;
    }
    
    // Removes and returns the element at the first position in the list.
    public E removeFirst( ) throws EmptyListException
    {
        if ( this.isEmpty() )
            throw new EmptyListException();

        E element = head.getElement();
        this.removeFirstNode();
        return element;
    }

    // Removes the last node in the list.
    // Pre-condition: the list is not empty.
    protected void removeLastNode( )
    {
        tail = tail.getPrevious();
        if ( tail == null )
            head = null;
        else
            tail.setNext(null);
        currentSize--;
    }

    // Removes and returns the element at the last position in the list.
    public E removeLast( ) throws EmptyListException
    {
        if ( this.isEmpty() )
            throw new EmptyListException();

        E element = tail.getElement();
        this.removeLastNode();
        return element;
    }

    // Removes the specified node from the list.
    // Pre-condition: the node is neither the head nor the tail of the list.
    protected void removeMiddleNode( DListNode<E> node )
    {
        DListNode<E> prevNode = node.getPrevious();
        DListNode<E> nextNode = node.getNext();
        prevNode.setNext(nextNode);            
        nextNode.setPrevious(prevNode);            
        currentSize--;
    }

    // Removes and returns the element at the specified position in the list.
    // Range of valid positions: 0, ..., size()-1.
    // If the specified position is 0, remove corresponds to removeFirst. 
    // If the specified position is size()-1, remove corresponds to removeLast.
    public E remove( int position ) throws InvalidPositionException
    {
        if ( position < 0 || position >= currentSize )
            throw new InvalidPositionException();

        if ( position == 0 )
            return this.removeFirst();
        else if ( position == currentSize - 1 )
            return this.removeLast();
        else 
        {
            DListNode<E> nodeToRemove = this.getNode(position);
            this.removeMiddleNode(nodeToRemove);
            return nodeToRemove.getElement();
        }
    }
    
    // Returns the node with the first occurrence of the specified element
    // in the list, if the list contains the element.
    // Otherwise, returns null.
    protected DListNode<E> findNode( E element )
    {
        DListNode<E> node = head;
        while ( node != null && !node.getElement().equals(element) )
            node = node.getNext();
        return node;
    }

    // Removes the first occurrence of the specified element from the list
    // and returns true, if the list contains the element.
    // Otherwise, returns false.
    public boolean remove( E element )
    {
        DListNode<E> node = this.findNode(element);
        if ( node == null )
            return false;
        else
        {
            if ( node == head )
                this.removeFirstNode();
            else if ( node == tail )
                this.removeLastNode();
            else
                this.removeMiddleNode(node);
            return true;
        }
    }
    
    // Removes all of the elements from the specified list and
    // inserts them at the end of the list (in proper sequence).
    public void append( DoublyLinkedList<E> list )
    {
    	if(list == null || list.isEmpty()) //isEmpty ou null
    		return;

    	if(this.isEmpty()) {
    		head = list.head;
    		tail = list.tail;
    		currentSize = list.size();
    	}
    	else {
    		DListNode<E> newTail = list.tail;
    		this.tail.setNext(list.head);
    		list.head.setPrevious(this.tail);
    		this.tail = newTail;
    		this.currentSize += list.size();
    	}
    }
}
