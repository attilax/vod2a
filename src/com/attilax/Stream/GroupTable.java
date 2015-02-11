/**
 * @author attilax 老哇的爪子
	@since  o8e 1_41_50$
 */
package com.attilax.Stream;
import com.attilax.core;
 import static  com.attilax.core.*;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * @author  attilax 老哇的爪子
 *@since  o8e 1_41_50$
 */
public class GroupTable<ati>  implements List{

	protected String[] grpby_fld;
	List<ati> li=new ArrayList<ati>();
	//  attilax 老哇的爪子 1_41_50   o8e   
	protected Object key;

	/* (non-Javadoc)
	 * @see java.util.List#size()
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public int size() {
		// attilax 老哇的爪子  1_53_r   o8e 
		return 0;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public boolean isEmpty() {
		// attilax 老哇的爪子  1_53_r   o8e 
		return false;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public boolean contains(Object o) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return false;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#iterator()
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public Iterator iterator() {
		// attilax 老哇的爪子  1_53_r   o8e 
		return null;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray()
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public Object[] toArray() {
		// attilax 老哇的爪子  1_53_r   o8e 
		return null;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray(T[])
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public Object[] toArray(Object[] a) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return null;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public boolean add(Object e) {
		// attilax 老哇的爪子  1_53_r   o8e 
		this.li.add((ati) e);
		return true;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public boolean remove(Object o) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return false;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public boolean containsAll(Collection c) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return false;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public boolean addAll(Collection c) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return false;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public boolean addAll(int index, Collection c) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return false;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public boolean removeAll(Collection c) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return false;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#retainAll(java.util.Collection)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public boolean retainAll(Collection c) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return false;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#clear()
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public void clear() {
		// attilax 老哇的爪子  1_53_r   o8e 
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public Object get(int index) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return null;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public Object set(int index, Object element) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return null;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public void add(int index, Object element) {
		// attilax 老哇的爪子  1_53_r   o8e 
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public Object remove(int index) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return null;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public int indexOf(Object o) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return 0;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public int lastIndexOf(Object o) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return 0;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public ListIterator listIterator() {
		// attilax 老哇的爪子  1_53_r   o8e 
		return null;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public ListIterator listIterator(int index) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return null;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 * @author  attilax 老哇的爪子
	 *@since  o8e 1_53_r$
	 */
	@Override public List subList(int fromIndex, int toIndex) {
		// attilax 老哇的爪子  1_53_r   o8e 
		return null;
		
	}
}

//  attilax 老哇的爪子