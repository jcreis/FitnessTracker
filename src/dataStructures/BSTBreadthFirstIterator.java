package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

public class BSTBreadthFirstIterator<K,V> implements Iterator<Entry<K,V>> {
	
	static final long serialVersionUID = 0L;
	
	protected BSTNode<K,V> root;
	protected Queue<BSTNode<K,V>> list;
	
	public BSTBreadthFirstIterator( BSTNode<K,V> root )
    {
		this.root = root;
		this.rewind();
    }

	public boolean hasNext() {
		return !list.isEmpty();
	}

	public Entry<K, V> next() throws NoSuchElementException {
		BSTNode<K, V> current = list.dequeue();
		BSTNode<K, V> leftChild = current.getLeft();
		BSTNode<K, V> rightChild = current.getRight();
		
		if(leftChild != null)
			list.enqueue(leftChild);
		
		if(rightChild != null)
			list.enqueue(rightChild);
		
		return current.getEntry();
	}

	public void rewind() {
		this.list = new QueueInList<BSTNode<K, V>>();
		list.enqueue(root);
	}
}
