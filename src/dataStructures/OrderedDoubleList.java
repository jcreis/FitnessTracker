package dataStructures;

import dataStructures.exceptions.*;

public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	private static final long serialVersionUID = 0L;

	// Node at the head of the list.
	protected DListNode<Entry<K,V>> head;

	// Node at the tail of the list.
	protected DListNode<Entry<K,V>> tail;

	// Number of elements in the list.
	protected int currentSize;

	public OrderedDoubleList( )
	{
		head = null;
		tail = null;
		currentSize = 0;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public int size() {
		return currentSize;
	}

	//return null se a key for maior que todas da lista
	//return node esperado se a key existir na lista
	//return proximo node se a key nao existir e houver um proximo node na lista
	private DListNode<Entry<K,V>> findNode(K key) {
		DListNode<Entry<K,V>> node = head;
		while ( node != null && node.getElement().getKey().compareTo(key) < 0 )
		{
			node = node.getNext();
		}
		return node;
	}

	public V find(K key) {
		DListNode<Entry<K,V>> node = findNode(key);

		if(node==null)
			return null;

		if(node.getElement().getKey().equals(key))
			return node.getElement().getValue();
		else return null;
	}

	protected void addFirst( Entry<K,V> element )
	{
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(element, null, head);
		if ( this.isEmpty() )
			tail = newNode;
		else
			head.setPrevious(newNode);
		head = newNode;
		currentSize++;
	}

	// Inserts the specified element at the last position in the list.
	protected void addLast( Entry<K,V> element )
	{
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(element, tail, null);
		if ( this.isEmpty() )
			head = newNode;
		else
			tail.setNext(newNode);
		tail = newNode;
		currentSize++;
	}

	// Inserts the specified element at the specified position in the list.
	// Pre-condition: position ranges from 1 to currentSize-1.
	protected void addMiddle( DListNode<Entry<K,V>> next, Entry<K,V> element )
	{	
		DListNode<Entry<K,V>> prevNode = next.getPrevious();
		DListNode<Entry<K,V>> nextNode = next;
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(element, prevNode, nextNode);
		prevNode.setNext(newNode);
		nextNode.setPrevious(newNode);
		currentSize++;
	}

	public V insert(K key, V value) {
		Entry<K,V> newEntry = new EntryClass<K, V>(key, value);
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(newEntry);

		//ver se a lista e vazia
		if ( this.isEmpty() ) {
			head = newNode;
			tail = newNode;
			currentSize++;
			return value;
		}

		//procurar o no na lista, se nao existir receber o nextNode
		DListNode<Entry<K,V>> nodeFound = findNode(key);

		//ver se e para inserir a cauda
		if ( nodeFound == null ) {
			this.addLast(newEntry);
			return value;
		}
		
		if(nodeFound.getElement().getKey().equals(key)) //ver se o elemento ja existe
			nodeFound.setElement(newEntry);
		else if ( nodeFound.getElement().getKey().equals(head.getElement().getKey()) ) //ver se e para inserir a cabeca
			this.addFirst(newEntry);
		else
			this.addMiddle(nodeFound, newEntry);
		
		return value;
	}

	protected void removeFirstNode( )
	{
		head = head.getNext();
		if ( head == null )
			tail = null;
		else
			head.setPrevious(null);
		currentSize--;
	}

	protected void removeLastNode( )
	{
		tail = tail.getPrevious();
		if ( tail == null )
			head = null;
		else
			tail.setNext(null);
		currentSize--;
	}

	protected void removeMiddleNode( DListNode<Entry<K,V>> node )
	{
		DListNode<Entry<K,V>> prevNode = node.getPrevious();
		DListNode<Entry<K,V>> nextNode = node.getNext();
		prevNode.setNext(nextNode);            
		nextNode.setPrevious(prevNode);            
		currentSize--;
	}

	public V remove(K key) {
		//procurar o no na lista, se nao existir receber o nextNode
		DListNode<Entry<K,V>> nodeFound = findNode(key);

		//se o no nao esta na lista
		if ( nodeFound == null || !nodeFound.getElement().getKey().equals(key) )
			return null;
		else {
			if(nodeFound==head)
				removeFirstNode();
			else if(nodeFound==tail)
				removeLastNode();
			else removeMiddleNode(nodeFound);
		}

		return nodeFound.getElement().getValue();
	}

	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		return head.getElement();
	}

	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		return tail.getElement();
	}

	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<Entry<K, V>>(head, tail);
	}
}
