package model.data_structures;

public class LinearProbingHashST  <K extends Comparable<K>, V >
{
	private static final int INIT_CAPACITY = 23;

	private int n;           // number of key-value pairs in the symbol table
	private int m;           // size of linear probing table
	private K[] keys;       // the keys
	private V[] vals;    // the values


	/**
	 * Initializes an empty symbol table.
	 */
	public LinearProbingHashST()
	{
		this(INIT_CAPACITY);
	}

	/**
	 * Initializes an empty symbol table with the specified initial capacity.
	 * @param capacity the initial capacity
	 */
	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int capacity) 
	{
		m = capacity;
		n = 0;
		keys = (K[])   new Comparable[m];
		vals = (V[])new Comparable[n];
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() 
	{
		return n;
	}

	/**
	 * Returns true if this symbol table is empty.
	 * @return {@code true} if this symbol table is empty;
	 *         {@code false} otherwise
	 */
	public boolean isEmpty()
	{
		return size() == 0;
	}

	/**
	 * Returns true if this symbol table contains the specified key.
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

	// hash function for keys - returns value between 0 and M-1
	private int hash(K key) 
	{
		return (key.hashCode() & 0x7fffffff) % m;
	}

	// resizes the hash table to the given capacity by re-hashing all of the keys
	private void resize(int capacity)
	{
		int capacidad = numPrimo(capacity);
		LinearProbingHashST<K, V> temp = new LinearProbingHashST<K, V>(capacidad);
		for (int i = 0; i < m; i++) 
		{
			if (keys[i] != null) 
			{
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		m = temp.m;
	}

	public int numPrimo(int pNum)
	{
		int numeropPrimo = pNum+1;
		for (int i = 2; i < numeropPrimo; i++)
		{
			
			if (numeropPrimo % i == 0) 
			{
				numeropPrimo++;
				i=2;
			}
		}
		return numeropPrimo;
	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is {@code null}.
	 *
	 * @param  key the key
	 * @param  val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(K key, V val)
	{
		boolean agrego = false;
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");

		if (val == null)
		{
			delete(key);
			agrego = true;
		}

		if(!agrego)
		{
			// double table size if 50% full
			if (n >= m/2) resize((int) (m*(1.25)));

			int i;
			for (i = hash(key); keys[i] != null; i = (i + 1) % m) 
			{
				if (keys[i].equals(key)) 
				{
					vals[i] = val;
					agrego = true;
				}
			}
			if(!agrego)
			{
				keys[i] = key;
				vals[i] = val;
				n++;
			}
		}
	}

	/**
	 * Returns the value associated with the specified key.
	 * @param key the key
	 * @return the value associated with {@code key};
	 *         {@code null} if no such value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public V get(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public V delete(K key)
	{
		V element = null;
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return element;

		// find position i of key
		int i = hash(key);
		while (!key.equals(keys[i]))
		{
			i = (i + 1) % m;
		}

		element = vals[i];

		// delete key and associated value
		keys[i] = null;
		vals[i] = null;

		// rehash all keys in same cluster
		i = (i + 1) % m;
		while (keys[i] != null)
		{
			// delete keys[i] and vals[i] and reinsert
			K   keyToRehash = keys[i];
			V valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			n--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % m;
		}

		n--;

		// halves size of array if it's 12.5% full or less
		if (n > 0 && n <= m/8) resize((m/2)+1);

		assert check();

		return element;
	}

	/**
	 * Returns all keys in this symbol table as an {@code Iterable}.
	 * To iterate over all of the keys in the symbol table named {@code st},
	 * use the foreach notation: {@code for (Key key : st.keys())}.
	 *
	 * @return all keys in this symbol table
	 */
	public Iterable<K> keys()
	{
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < m; i++)
			if (keys[i] != null) queue.enqueue(keys[i]);
		return queue;
	}

	// integrity check - don't check after each put() because integrity not maintained during a delete()
	private boolean check() 
	{
		// check that hash table is at most 50% full
		if (m < 2*n)
		{
			System.err.println("Hash table size m = " + m + "; array size n = " + n);
			return false;
		}

		// check that each key in table can be found by get()
		for (int i = 0; i < m; i++) 
		{
			if (keys[i] == null) continue;
			else if (get(keys[i]) != vals[i])
			{
				System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
				return false;
			}
		}
		return true;
	}

}
