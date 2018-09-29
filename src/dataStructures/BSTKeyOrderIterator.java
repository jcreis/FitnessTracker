package dataStructures;

import dataStructures.exceptions.*;

public class BSTKeyOrderIterator<K,V> implements Iterator<Entry<K,V>> {

			//a
		//b		  c
	//d		e		f

	//infixo bac (so com 3), dbeacf (neste caso)
	//prefixo abc
	//sufico bca
	
	//infixo desce o maximo a esquerda
	//
	
	static final long serialVersionUID = 0L;
	
	protected BSTNode<K,V> root;
	protected Stack<BSTNode<K,V>> stk;
	
	public BSTKeyOrderIterator( BSTNode<K,V> root )
    {
		this.root = root;
		this.rewind();
    }

	public boolean hasNext() {
		return !stk.isEmpty();
	}

	public Entry<K, V> next() throws NoSuchElementException, EmptyStackException {
		BSTNode<K, V> current = stk.pop();
		BSTNode<K, V> nextNode = current.getRight();
		
		while(nextNode!=null) {
			stk.push(nextNode);
			nextNode = nextNode.getLeft();
		}
		
		return current.getEntry();
	}

	public void rewind() {
		this.stk = new StackInList<BSTNode<K, V>>();
		BSTNode<K, V> current = this.root;
		
		while(current != null) {
			stk.push(current);
			current = current.getLeft();
		}
	}
}
