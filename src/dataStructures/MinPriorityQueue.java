package dataStructures;

import java.io.Serializable;

/**
 * Minimum Priority Queue Abstract Data Type 
 * Includes description of general methods to be implemented by Priority Queues.
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic comparable priority (key)
 * @param <V> Generic value
 * 
 */

public interface MinPriorityQueue<K extends Comparable<K>, V>  
    extends Serializable
{

    /**
     * Returns true iff the priority queue contains no entries.
     * @return true if priority queue is empty
     */
    boolean isEmpty( );

    /**
     * Returns the number of entries in the priority queue.
     * @return number of elements in the priority queue
     */
    int size( );

    /**
     * Returns an entry with the smallest key in the priority queue.      
     * @return entry with smallest priority (key)
     * @throws EmptyPriorityQueueException
     */
    Entry<K,V> minEntry( ) throws EmptyPriorityQueueException; 

    /**
     * Inserts the entry (key, value) in the priority queue.
     * @param key - priority of the entry to be inserted
     * @param value - value of the entry to be inserted
     */
    void insert( K key, V value );

    /**
     * Removes an entry with the smallest key from the priority queue
     * and returns that entry.
     * @return the entry with the smallest priority (key)
     * @throws EmptyPriorityQueueException
     */
    Entry<K,V> removeMin( ) throws EmptyPriorityQueueException;

}
