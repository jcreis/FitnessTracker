package dataStructures;

public class EntryClass<K,V> implements Entry<K,V>{

	static final long serialVersionUID = 0L;

	
	private K key;
	private V value;
	
	public EntryClass(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	public void setKey(K newKey){
		key = newKey;
	}
	
	public void setValue(V newValue){
		value = newValue;
	}

}
