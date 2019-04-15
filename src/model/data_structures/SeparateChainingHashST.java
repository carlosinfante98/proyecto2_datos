package model.data_structures;

public class SeparateChainingHashST <K extends Comparable<K>, V >
{
	/**
	 * Initial capacity for the table
	 */
	private static final int INIT_CAPACITY = 23;

	/**
	 * Number of key-value pairs
	 */
	private int n;   

	/**
	 * Has table size
	 */
	private int m;

	/**
	 * Array of linked-list symbol tables
	 */
	private SequentialSearchST<K, V>[] st;


	/**
	 * Initializes an empty symbol table.
	 */
	public SeparateChainingHashST()
	{
		this(INIT_CAPACITY);
	} 

	/**
	 * Initializes an empty symbol table with {@code m} chains.
	 * @param m the initial number of chains
	 */
	@SuppressWarnings("unchecked")
	public SeparateChainingHashST(int m) 
	{
		this.m = m;
		st = (SequentialSearchST<K, V>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<K, V>();
	} 

	/**
	 * Resize the hash table to have the given number of chains by rehashing all of the keys
	 * @param chains
	 */
	private void resize(int chains)
	{
		int capacidad = numPrimo(chains);
		SeparateChainingHashST<K, V> temp = new SeparateChainingHashST<K, V>(capacidad);
		for (int i = 0; i < m; i++) 
		{
			for (K key : st[i].keys()) 
				temp.put(key, st[i].get(key));
		}
		this.m  = temp.m;
		this.n  = temp.n;
		this.st = temp.st;
	}


	public int numPrimo(int pNum)
	{
		int numeroPrimo = pNum+1;
		for (int i = 2; i < numeroPrimo; i++)
		{

			if (numeroPrimo % i == 0) 
			{
				numeroPrimo++;
				i=2;
			}

		}
		return numeroPrimo;
	}

	/**
	 * Hash value between 0 and m-1
	 * @param key Key that is going to be hashed to enter in the table.
	 * @return Integer that will be the hash or position in the table for the K given.
	 */
	private int hash(K key) 
	{
		return (key.hashCode() & 0x7fffffff) % m;
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

	/**
	 * Returns the value associated with the specified key in this symbol table.
	 * @param  key the key
	 * @return the value associated with {@code key} in the symbol table;
	 *         {@code null} if no such value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public V get(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
 		int i = hash(key);
		return st[i].get(key);
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
		boolean agregado = false;

		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) 
		{
			delete(key);
			agregado = true;
		}
		if(!agregado)
		{
			// double table size if the charge factor is 0.6 or greater.
			if (n/m >= 0.6) resize((2*m));

			int i = hash(key);
			if (!st[i].contains(key)) n++;
			st[i].put(key, val);
		}
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

		int i = hash(key);
		if (st[i].contains(key)) n--;

		element = st[i].get(key);
		st[i].delete(key);

		// halve table size if average length of list <= 2
		if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);

		return element;
	} 

	// return keys in symbol table as an Iterable
	public Iterable<K> keys() 
	{
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < m; i++)
		{
			for (K key : st[i].keys())
				queue.enqueue(key);
		}
		return queue;
	} 
	public ArrayListNotComparable<V> values() 
	{
		ArrayListNotComparable<V> values = new ArrayListNotComparable<V>();
		for (int i = 0; i < m; i++)
		{
			for (K key : st[i].keys())
			{
				values.add(st[i].get(key));
			}
		}
		return values;
	} 

}
