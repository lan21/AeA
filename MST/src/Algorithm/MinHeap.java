package Algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MinHeap<T> implements Collection<T> {

	/**
	 * Represents a node in the heap. It contains the element held by the node
	 * and its value
	 * 
	 * @author rakotoarivony
	 * 
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

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o) {
			if (o == null)
				return false;
			if (o instanceof MinHeapNode) {
				MinHeapNode<Object> other = (MinHeapNode<Object>) o;
				return (this.key.equals(other.key));
			}
			return false;
		}

		public void setValue(int newValue) {
			this.value = newValue;
		}

	}

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
		this.nodes.add(new MinHeapNode<T>(e, value));
		this.percolationUp(this.nodes.size() - 1);
		return true;
	}

	/**
	 * Insert the specified element into this heap By default, the value of the
	 * added element is Integer.MAX_VALUE
	 * 
	 * @param e
	 *            element to be added to this heap
	 * @return true if this collection changed as a result of the call
	 */
	@Override
	public boolean add(T e) {
		return this.add(e, Integer.MAX_VALUE);
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present. If the heap does not contain the element, it is unchanged.
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
		MinHeapNode<Object> object = new MinHeapNode<Object>(o,
				Integer.MAX_VALUE);
		int index = this.nodes.indexOf(object);
		if (index > -1) {
			this.nodes.set(index, this.nodes.get(this.nodes.size() - 1));
			this.nodes.remove(this.nodes.size() - 1);
			if (this.nodes.size() > 0) {
				percolationDown(index);
			}
			return true;
		}
		return false;

	}

	/**
	 * Retrieves and removes the head of this heap, or returns null if this heap
	 * is empty.
	 * 
	 * @return the head of this heap
	 */
	public T poll() {
		if (this.isEmpty())
			return null;
		MinHeapNode<T> element = this.nodes.get(0);
		this.nodes.set(0, this.nodes.get(this.nodes.size() - 1));
		this.nodes.remove(this.nodes.size() - 1);
		if (this.nodes.size() > 0) {
			percolationDown(0);
		}
		return element.key;
	}

	/**
	 * Retrieves but does not remove the head of this heap
	 * 
	 * @return the head of this heap
	 */
	public T peek() {
		if (this.isEmpty())
			return null;
		return this.nodes.get(0).key;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		Object[] elements = new Object[this.nodes.size()];
		int i = 0;
		for (MinHeapNode<T> object : this.nodes) {
			elements[i] = object.key;
			i++;
		}
		return elements;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.nodes.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	/**
	 * permet de remonter un noeud tant qu'il est inférieur à son père Le père
	 * d'un noeud est toujours à l'indice (indiceElement-1)/2
	 */
	private void percolationUp(int indiceElement) {
		MinHeapNode<T> element = this.nodes.get(indiceElement);
		int indicePere;
		while (indiceElement > 0) {
			indicePere = (indiceElement - 1) / 2;
			MinHeapNode<T> pere = this.nodes.get(indicePere);
			if (element.compareTo(pere) < 0) {
				this.nodes.set(indiceElement, pere);
				indiceElement = indicePere;
			} else {
				break;
			}
		}
		this.nodes.set(indiceElement, element);
	}

	/**
	 * permet de descendre un noeud tant qu'il est supérieur à son fils Les fils
	 * sont toujours à l'indice 2*indiceElement+1 et 2*indiceElement+2
	 */
	private void percolationDown(int indiceElement) {
		MinHeapNode<T> element = this.nodes.get(indiceElement);
		int indiceFils1;
		int indiceFils2;
		indiceFils1 = 2 * indiceElement + 1;
		indiceFils2 = 2 * indiceElement + 2;
		// tant que l'élément a un fils
		while (indiceFils1 < this.nodes.size()
				&& indiceFils2 < this.nodes.size()) {
			MinHeapNode<T> fils1 = this.nodes.get(indiceFils1);
			MinHeapNode<T> fils2 = this.nodes.get(indiceFils2);
			if (fils1.compareTo(fils2) < 0 && element.compareTo(fils1) > 0) {
				this.nodes.set(indiceElement, fils1);
				indiceElement = indiceFils1;
			} else if (fils2.compareTo(fils1) < 0
					&& element.compareTo(fils2) > 0) {
				this.nodes.set(indiceElement, fils2);
				indiceElement = indiceFils2;
			} else
				break;
			indiceFils1 = 2 * indiceElement + 1;
			indiceFils2 = 2 * indiceElement + 2;
		}
		// si l'élément n'a qu'un fils
		if (indiceFils1 < this.nodes.size()) {
			MinHeapNode<T> fils1 = this.nodes.get(indiceFils1);
			if (element.compareTo(fils1) > 0) {
				this.nodes.set(indiceElement, fils1);
				indiceElement = indiceFils1;
			}
		}
		this.nodes.set(indiceElement, element);
	}

	/**
	 * 
	 * @param element
	 * @param newValue
	 */
	public boolean setValue(T element, int newValue) {
		MinHeapNode<T> object = new MinHeapNode<T>(element, Integer.MAX_VALUE);
		int index = this.nodes.indexOf(object);
		if (index > -1) {
			object = this.nodes.get(index);
			object.setValue(newValue);
			//si il est inférieur à son père percolation up
			if(index>0){
				int indexFather = (index - 1) / 2;
				if(this.nodes.get(indexFather).compareTo(object)>0){
					this.percolationUp(index);
				}
				else{
					this.percolationDown(index);
				}
			}
			return true;
		}
		return false;
	}

}
