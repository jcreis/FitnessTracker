package dataStructures;

public interface InvertibleOrderedDictionary<K extends Comparable<K>, V> extends OrderedDictionary<K,V> {

	// Returns an iterator in the inverse order of the entries in the dictionary 
	Iterator<Entry<K,V>> inverseIterator( );  
	
}
