package dataStructures;

import dataStructures.exceptions.EmptyStackException;
import dataStructures.exceptions.NoSuchElementException;

public class BSTInverseKeyOrderIterator<K,V> extends BSTKeyOrderIterator<K,V> implements Iterator<Entry<K,V>>{

	static final long serialVersionUID = 0L;
	
	public BSTInverseKeyOrderIterator(BSTNode<K, V> root) {
		super(root);
	}
	
	public Entry<K, V> next() throws NoSuchElementException, EmptyStackException {
		BSTNode<K, V> current = stk.pop();
		BSTNode<K, V> nextNode = current.getLeft();
		
		while(nextNode!=null) {
			stk.push(nextNode);
			nextNode = nextNode.getRight();
		}
		
		return current.getEntry();
	}

	public void rewind() {
		this.stk = new StackInList<BSTNode<K, V>>();
		BSTNode<K, V> current = this.root;
		
		while(current != null) {
			stk.push(current);
			current = current.getRight();
		}
	}
}
