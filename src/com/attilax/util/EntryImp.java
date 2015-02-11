/**
 * @author attilax 老哇的爪子
	@since  o7f m38e$
 */
package com.attilax.util;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.util.Map.Entry;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o7f m38e$
 */
public class EntryImp<K,V> implements Entry<K, V> {

	private V val;
	private K key;

	/* (non-Javadoc)
	 * @see java.util.Map.Entry#getKey()
	 * @author  attilax 老哇的爪子
	 *@since  o7f m3851$
	 */
	@Override public K getKey() {
		// attilax 老哇的爪子  m3851   o7f 
		return this.key;
	 
		
	}

	/* (non-Javadoc)
	 * @see java.util.Map.Entry#getValue()
	 * @author  attilax 老哇的爪子
	 *@since  o7f m3851$
	 */
	@Override public V getValue() {
		// attilax 老哇的爪子  m3851   o7f 
		return this.val;
	 
	}

	/* (non-Javadoc)
	 * @see java.util.Map.Entry#setValue(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o7f m3851$
	 */
	@Override public V setValue(V value) {
		// attilax 老哇的爪子  m3851   o7f 
		this.val=value;
		return this.val;
	 
		
	}
	
	public V setKeyValue(K k,V value) {
		// attilax 老哇的爪子  m3851   o7f 
		this.val=value;
		this.key=k;
		return this.val;
	 
		
	}
	
	
	//  attilax 老哇的爪子 m38e   o7f   
}

//  attilax 老哇的爪子