package model.data_structures;

public class SequentialSearchST<K extends Comparable<K>, V>
{
	private int n;           // number of key-value pairs
	private Node first;      // the linked list of key-value pairs

	// a helper linked list data type
	private class Node 
	{
		private K key;
		private V values;
		private Node next;

		public Node(K key, V val, Node next) 
		{
			this.key  = key;
			this.values  = val;
			this.next = next;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public SequentialSearchST() 
	{
		//Empty symbol table
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 *
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() 
	{
		return n;
	}

	/**
	 * Returns true if this symbol table is empty.
	 *
	 * @return {@code true} if this symbol table is empty;
	 *         {@code false} otherwise
	 */
	public boolean isEmpty() 
	{
		return size() == 0;
	}

	/**
	 * Returns true if this symbol table contains the specified key.
	 *
	 * @param  key the key
	 * @return {@code true} if this symbol table contains {@code key};
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean contains(K key)
	{
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the given key in this symbol table.
	 *
	 * @param  key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 *     and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public V get(K key)
	{
		if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
		for (Node x = first; x != null; x = x.next) 
		{
			if ( key.hashCode() == x.key.hashCode())
				return x.values;
		}
		return null;
	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is {@code null}.
	 * @param  key the key
	 * @param  val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(K key, V val) 
	{
		if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 
		if (val == null)
		{
			delete(key);
			return;
		}

		for (Node x = first; x != null; x = x.next)
		{
			if (key.equals(x.key)) 
			{
				x.values = val;
				//Aquí si sobreescribe
				return;
			}
		}
		first = new Node(key, val, first);
		n++;
	}
	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to delete() is null"); 
		first = delete(first, key);
	}

	// delete key in linked list beginning at Node x
	// warning: function call stack too large if table is large
	private Node delete(Node x, K key)
	{
		if (x == null) return null;
		if (key.equals(x.key)) 
		{
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}


	/**
	 * Returns all keys in the symbol table as an {@code Iterable}.
	 * To iterate over all of the keys in the symbol table named {@code st},
	 * use the foreach notation: {@code for (Key key : st.keys())}.
	 * @return all keys in the symbol table
	 */
	public Iterable<K> keys() 
	{
		Queue<K> queue = new Queue<K>();
		for (Node x = first; x != null; x = x.next)
			queue.enqueue(x.key);
		return queue;
	}
}
