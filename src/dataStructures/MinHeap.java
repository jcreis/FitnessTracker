package dataStructures;

/**
 * Minimum Heap Implementation 
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic comparable priority (key)
 * @param <V> Generic value
 * 
 */

public class MinHeap<K extends Comparable<K>, V> 
    implements MinPriorityQueue<K,V>
{

	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;


    /**
     * Default capacity of the priority queue.     
     */
    public static final int DEFAULT_CAPACITY = 100;     

    /**
     * The growth factor of the extendable array.
     */
    public static final int GROWTH_FACTOR = 2;

    /**
     * Memory of the priority queue: an extendable array.
     */
    protected Entry<K,V>[] array;

    /**
     * Number of entries in the priority queue.
     */
    protected int currentSize;


    /**
     * Creates a heap with the specified capacity.
     * @param capacity - maximum number of entries to be inserted before extension
     */
    @SuppressWarnings("unchecked")
	public MinHeap( int capacity )
    {
        // Compiler gives a warning.
        array = (Entry<K,V>[]) new Entry[capacity];
        currentSize = 0;
    }


    /**
     * Creates a heap with the default capacity.
     */
    public MinHeap( )
    {
        this(DEFAULT_CAPACITY);
    }


    /**
     * Creates a heap with the capacity of the specified array
     * (which has theArray.length entries),
     * containing the entries stored in the array.
     * @param theArray - array containing entries for heap
     */
    public MinHeap( Entry<K,V>[] theArray )
    {
        // Build a complete tree.
        this.buildArray(theArray.length, theArray);
        currentSize = theArray.length;

        // Build a priority tree.
        this.buildPriorityTree();
    }


    /**
     * Establishes the heap order property,
     * when the extendable array has an arbitrary permutation of entries.
     */
    protected void buildPriorityTree( )  
    {
        // Node at position i has left child if 2i+1 <= currentSize - 1.
        for ( int i = (currentSize - 2) / 2; i >= 0; i-- )
            this.percolateDown(i);
    }

    @Override
    public boolean isEmpty( )
    { 
        return currentSize == 0;
    }


    /**
     * Returns true iff the array cannot contain more entries.
     * @return - true when heap needs to be extended
     */
    protected boolean isFull( )
    {
        return currentSize == array.length;
    }


    @Override
    public int size( ) 
    {
        return currentSize;
    }


    @Override
    public Entry<K,V> minEntry( ) throws EmptyPriorityQueueException
    {
        if ( this.isEmpty() )
            throw new EmptyPriorityQueueException("Priority queue is empty."); 

        return array[0];
    }


    @Override
    public void insert( K key, V value )
    {
        if ( this.isFull() )
            this.buildArray(GROWTH_FACTOR * array.length, array);
              
        // Percolate up.
        int hole = currentSize; 
        int parent = (hole - 1) / 2;
        while ( hole > 0 && key.compareTo( array[parent].getKey() ) < 0 )
        { 
            array[hole] = array[parent];  
            hole = parent;
            parent = (hole - 1) / 2;
        }
        array[hole] = new EntryClass<K,V>(key, value);
        currentSize++;
    }


    /**
     * Builds the extendable array with the specified capacity 
     * and with the contents of the specified array.
     * Requires: capacity >= contents.length.
     * @param capacity - capacity of newly extended heap
     * @param contents - contents of heap to be extended
     */
    @SuppressWarnings("unchecked")
	protected void buildArray( int capacity, Entry<K,V>[] contents )
    {
        // Compiler gives a warning.
        Entry<K,V>[] newArray = (Entry<K,V>[]) new Entry[capacity];
        System.arraycopy(contents, 0, newArray, 0, contents.length);
        array = newArray;
    }

    @Override
    public Entry<K,V> removeMin( ) throws EmptyPriorityQueueException
    {
        if ( this.isEmpty() ) 
            throw new EmptyPriorityQueueException("Priority queue is empty."); 
               
        Entry<K,V> minEntry = array[0];
        currentSize--;
        array[0] = array[currentSize];
        array[currentSize] = null;    // For garbage collection.
        if ( currentSize > 1 )
            this.percolateDown(0);  
        return minEntry;
    }


    /**
     * Establishes the heap order property 
     * from position firstPos to position currentSize - 1,
     * when the heap order property holds
     * from position firstPos + 1 to position currentSize - 1.
     * Requires: firstPos < currentSize.
     * @param firstPos - position to initiate percolateDown
     */
    protected void percolateDown( int firstPos )  
    {
        Entry<K,V> rootEntry = array[firstPos]; 
        K rootKey = rootEntry.getKey();
        int hole = firstPos;
        int child = 2 * hole + 1;    // Left child.  
              
        while ( child < currentSize ) 
        {
            // Find the smallest child.
            if ( child < currentSize - 1 &&
                 array[child+1].getKey().compareTo( array[child].getKey() ) < 0
               )
                child++;

            // Compare the smallest child with rootKey.
            if ( array[child].getKey().compareTo( rootKey ) < 0 ) 
            {
                array[hole] = array[child];
                hole = child; 
                child = 2 * hole + 1;    // Left child.
            }
            else
                break;
        }
        array[hole] = rootEntry;
    }


}
