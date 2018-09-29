package fitnessTracker;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.exceptions.NoSuchElementException;

public class BSTKeyOrderValuesIterator<K,V> implements Iterator<V>{
	
	private static final long serialVersionUID = 0L;
	
	private Iterator<Entry<K,V>> it;
	
	public BSTKeyOrderValuesIterator(Iterator<Entry<K,V>> it){
		this.it = it;
		this.rewind();
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	public V next() throws NoSuchElementException {
		return  it.next().getValue();
	}

	public void rewind() {
		it.rewind();
	}
}
