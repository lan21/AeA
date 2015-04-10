package Algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MinHeap<T> implements Collection<T> {

	private ArrayList<MinHeapNode<T>> nodes;

	public MinHeap() {
		this.nodes = new ArrayList<MinHeapNode<T>>();
	}

	public MinHeap(int size) {
		this.nodes = new ArrayList<MinHeapNode<T>>(size);
	}

	public MinHeap(Collection<T> c) {
		this.nodes = new ArrayList<MinHeapNode<T>>(c.size());
		this.addAll(c);
	}

	/**
	 * Returns the number of elements in this heap
	 * 
	 * @return the number of elements in this heap
	 */
	@Override
	public int size() {
		return nodes.size();
	}

	/**
	 * Returns true if this heap doesn't contain any element.
	 * 
	 * @return true if this heap is empty
	 */
	@Override
	public boolean isEmpty() {
		return this.nodes.isEmpty();
	}

	/**
	 * Returns true if this heap contains the specified element. More formally,
	 * returns true if and only if this heap contains at least one element e
	 * such that (o==null ? e==null : o.equals(e)).
	 * 
	 * @param o
	 *            element whose presence in this heap is to be tested.
	 * @return true if this list contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		return this.nodes.contains(o);
	}

	/**
	 * Inserts all elements in the specified collection into this heap
	 * 
	 * @param c
	 *            collections containing the elements to be removed
	 * @return true if all elements have been added successfully
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		try {
			for (T object : c) {
				this.add(object);
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Removes all elements in the specified collection from this heap
	 * 
	 * @param c
	 *            collections containing the elements to be removed
	 * @return true if all elements have been removed
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		try {
			for (Object object : c) {
				this.remove(object);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Removes all the elements in this heap
	 */
	@Override
	public void clear() {
		this.nodes.clear();
	}

	/**
	 * Insert the specified element into this heap
	 * 
	 * @param e
	 *            element to be added to this heap
	 * @param value
	 *            the value of this element in the heap
	 * @return true if this collection changed as a result of the call
	 */
	public boolean add(T e, int value) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Insert the specified element into this heap
	 * 
	 * @param e
	 *            element to be added to this heap
	 * @param value
	 *            the value of this element in the heap
	 * @return true if this collection changed as a result of the call
	 */
	@Override
	public boolean add(T e) {
		return false;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present. If the list does not contain the element, it is unchanged.
	 * More formally, removes the element with the lowest index i such that
	 * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
	 * Returns true if this list contained the specified element (or
	 * equivalently, if this list changed as a result of the call).
	 * 
	 * @param o
	 *            element to be removed from this list, if present
	 * @return true if this list contained the specified element
	 */
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Retrieves and removes the head of this heap, or returns null if this heap
	 * is empty.
	 * 
	 * @return the head of this heap
	 */
	public T poll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrieves but does not remove the head of this heap
	 */
	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Represents a node in the heap. It contains the element held by the node
	 * and its value
	 * 
	 * @author rakotoarivony
	 * 
	 * @param <T>
	 */
	private class MinHeapNode<T> implements Comparable<MinHeapNode<T>> {

		T key;
		int value;

		public MinHeapNode(T key, int value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(MinHeapNode<T> o) {
			return ((Integer) this.value).compareTo(o.value);
		}

		public int getValue() {
			return value;
		}

		/**
		 * Sets the value of this node in the heap
		 * 
		 * @param value
		 */
		public void setValue(int value) {
			this.value = value;
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

}
