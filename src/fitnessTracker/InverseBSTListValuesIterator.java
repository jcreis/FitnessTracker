package fitnessTracker;

import dataStructures.*;
import dataStructures.exceptions.NoSuchElementException;

public class InverseBSTListValuesIterator<K,V> implements Iterator<V>{

	private static final long serialVersionUID = 0L;
	
	private Iterator<Entry<K,List<V>>> bstIterator;
	private Iterator<V> listIterator;
	
	public InverseBSTListValuesIterator(Iterator<Entry<K,List<V>>> it){
		this.bstIterator = it;
		this.rewind();
	}

	public boolean hasNext() {
		if(listIterator == null)
			return false;
		
		if(listIterator.hasNext())
			return true;
		
		return false;
	}

	public V next() throws NoSuchElementException {
		V aux = listIterator.next();
		if(!listIterator.hasNext()){
			if(bstIterator.hasNext())
//				IterableStack is;
//				while(bstIterator.hasNext()){
//					is = bstIterator.next();
//					if(!is.isEmpty())
//						break;
//				}
			listIterator = bstIterator.next().getValue().iterator();
		}
		return  aux;
	}

	
	public void rewind() {
		bstIterator.rewind();
		
		if(bstIterator.hasNext())
			listIterator =  bstIterator.next().getValue().iterator();
		else listIterator = null;
	}
}
