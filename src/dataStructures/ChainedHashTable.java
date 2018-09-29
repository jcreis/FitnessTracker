package dataStructures;  

/**
 * Chained Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public ChainedHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new OrderedDoubleList<K,V>();
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @SuppressWarnings("unchecked")
	protected void rehash() {
    	Iterator<Entry<K,V>> it = this.iterator();
    	maxSize = maxSize*2;
    	
    	int arraySize = HashTable.nextPrime((int) (1.1 * maxSize));
        // Compiler gives a warning.
    	Dictionary<K,V>[] table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new OrderedDoubleList<K,V>();
       
        Entry<K,V> e;
        while(it.hasNext()) {
        	e = it.next();
        	V value = e.getValue();
        	K key = e.getKey();
        	this.insert(key, value);
        }
    }
    
    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            this.rehash();
        	
        V previousValue = table[this.hash(key)].insert(key, value);
    	
        if(previousValue==null)
    		currentSize++;
    	
        return previousValue;
    }

    @Override
    public V remove( K key )
    {
    	//if(this.isEmpty())
    		//return null;
    	
    	V value = table[this.hash(key)].remove(key);
    	
    	if(value!=null)
    		currentSize--;
    	
        return value;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        return new CHTIterator<K,V>(table);
    } 
}
