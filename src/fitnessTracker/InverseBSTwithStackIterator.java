package fitnessTracker;
import dataStructures.*;
import dataStructures.exceptions.NoSuchElementException;
public class InverseBSTwithStackIterator<K,V> implements Iterator<V>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	
	private Iterator<Entry<K,IterableStack<V>>> bstIterator;
	private Iterator<V> stackIterator;
//	private Stack
	
	public InverseBSTwithStackIterator(Iterator<Entry<K,IterableStack<V>>> it){
		this.bstIterator = it;
		this.rewind();
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(stackIterator == null)
			return false;
		if(stackIterator.hasNext())
			return true;
		
		return false;
	}

	public V next() throws NoSuchElementException {
		V aux = stackIterator.next();
		if(!stackIterator.hasNext()){
		
			if(bstIterator.hasNext())
//				IterableStack is;
//				while(bstIterator.hasNext()){
//					is = bstIterator.next();
//					if(!is.isEmpty())
//						break;
//				}
				stackIterator = bstIterator.next().getValue().iterator();
			
		}
		return  aux;
	}

	
	public void rewind() {
		bstIterator.rewind();
		
		if(bstIterator.hasNext()){
			stackIterator =  bstIterator.next().getValue().iterator();
		}
		
		else
			stackIterator = null;
	}
	
}
