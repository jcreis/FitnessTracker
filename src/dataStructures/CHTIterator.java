package dataStructures;

import dataStructures.exceptions.*;

public class CHTIterator<K,V> implements Iterator<Entry<K,V>> {
	
	static final long serialVersionUID = 0L;
	
	protected Dictionary<K,V>[] table;
	protected int vecPos;
	protected Iterator<Entry<K,V>> itPos;
	
	public CHTIterator( Dictionary<K,V>[] table )
    {
		this.table = table;
        this.rewind();
    } 
	
	@Override
	public boolean hasNext() {
		if(itPos==null)
			return false;
		
		return itPos.hasNext();	
		//return table.length >= vecPos; //&& table[vecPos]!=null	
	}

	@Override
	public Entry<K,V> next() throws NoSuchElementException {
		Entry<K,V> entryToReturn = itPos.next();
		
		//procurar o proximo elemento
		if(!itPos.hasNext()) {
			while(table[vecPos] == null  && vecPos<table.length)
				vecPos++;
			
			if(vecPos < table.length)
				itPos = table[vecPos].iterator();
		}
		
		return entryToReturn;	
	}

	@Override
	public void rewind() {
		vecPos = 0;
		itPos = null;
		
		//encontrar a primeira posicao do vector table preenchida
		while(table[vecPos] == null && vecPos<table.length)
			vecPos++;
		
		//se houver elementos no vector, cria um iterador de Entries na entrada vecPos
		if(vecPos < table.length)
			itPos = table[vecPos].iterator();
	}
}
