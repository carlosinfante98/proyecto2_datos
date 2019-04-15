package model.data_structures;

import java.util.NoSuchElementException;

public class BinarySearchTree<K extends Comparable<K>,V> 
//Ref : https://algs4.cs.princeton.edu/code/
{
	private BinaryNode<K,V> root;

	/**
	 * Initializes an empty symbol table.
	 */
	public BinarySearchTree() 
	{

	}

	/**
	 * Returns true if this symbol table is empty.
	 * @return {@code true} if this symbol table is empty; {@code false} otherwise
	 */
	public boolean isEmpty() 
	{
		return size() == 0;
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() 
	{
		return size(root);
	}

	// return number of key-value pairs in BST rooted at x
	private int size(BinaryNode<K,V> x)
	{
		if (x == null) return 0;
		else return x.size();
	}

	/**
	 * Does this symbol table contain the given key?
	 *
	 * @param  key the key
	 * @return {@code true} if this symbol table contains {@code key} and
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean contains(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the given key.
	 *
	 * @param  key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 *         and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public V get(K key) 
	{
		return get(root, key);
	}

	private V get(BinaryNode<K,V> x, K key)
	{
		if (key == null) throw new IllegalArgumentException("calls get() with a null key");
		if (x == null) return null;
		int cmp = key.compareTo(x.getKey());
		if      (cmp < 0) return get(x.getLeft(), key);
		else if (cmp > 0) return get(x.getRight(), key);
		else              return x.getValue();
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
		if (key == null) throw new IllegalArgumentException("calls put() with a null key");
		if (val == null) 
		{
			delete(key);
			return;
		}
		root = put(root, key, val);
		assert check();
	}

	private BinaryNode<K,V> put(BinaryNode<K,V> x, K key, V val) 
	{
		if (x == null) return new BinaryNode<K,V>(key, val, 1);
		int cmp = key.compareTo(x.getKey());
		if(cmp < 0) 
		{
			x.setLeft(put(x.getLeft(),  key, val));
		}
		else if(cmp > 0)
		{
			x.setRight(put(x.getRight(),  key, val));
		}
		else        
		{
			x.setValue(val);
		}
		x.setSize(1 + size(x.getLeft()) + size(x.getRight()));
		return x;
	}


	/**
	 * Removes the smallest key and associated value from the symbol table.
	 *
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public void deleteMin() 
	{
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
		assert check();
	}

	private BinaryNode<K,V> deleteMin(BinaryNode<K,V> x)
	{
		if (x.getLeft() == null) return x.getRight();
		x.setLeft(deleteMin(x.getLeft()));
		x.setSize(size(x.getLeft()) + size(x.getRight()) + 1);
		return x;
	}

	/**
	 * Removes the largest key and associated value from the symbol table.
	 *
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public void deleteMax() 
	{
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMax(root);
		assert check();
	}

	private BinaryNode<K,V> deleteMax(BinaryNode<K,V> x)
	{
		if (x.getRight() == null) return x.getLeft();
		x.setRight(deleteMax(x.getRight()));
		x.setSize(size(x.getLeft()) + size(x.getRight()) + 1);
		return x;
	}

	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 *
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(K key) 
	{
		if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
		root = delete(root, key);
		assert check();
	}

	private BinaryNode<K,V> delete(BinaryNode<K,V> x, K key)
	{
		if (x == null) return null;

		int cmp = key.compareTo(x.getKey());
		if      (cmp < 0) x.setLeft(delete(x.getLeft(),  key));
		else if (cmp > 0) x.setRight(delete(x.getRight(), key));
		else { 
			if (x.getRight() == null) return x.getLeft();
			if (x.getLeft()  == null) return x.getRight();
			BinaryNode<K,V> t = x;
			x = min(t.getRight());
			x.setRight(deleteMin(t.getRight()));
			x.setLeft(t.getLeft());
		} 
		x.setSize(size(x.getLeft()) + size(x.getRight()) + 1);
		return x;
	} 


	/**
	 * Returns the smallest key in the symbol table.
	 *
	 * @return the smallest key in the symbol table
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public K min()
	{
		if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
		return min(root).getKey();
	} 

	private BinaryNode<K,V> min(BinaryNode<K,V> x)
	{ 
		if (x.getLeft() == null) return x; 
		else  return min(x.getLeft()); 
	} 

	/**
	 * Returns the largest key in the symbol table.
	 *
	 * @return the largest key in the symbol table
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public K max()
	{
		if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
		return max(root).getKey();
	} 

	private BinaryNode<K,V> max(BinaryNode<K,V> x)
	{
		if (x.getRight() == null) return x; 
		else return max(x.getRight()); 
	} 

	/**
	 * Returns the largest key in the symbol table less than or equal to {@code key}.
	 *
	 * @param  key the key
	 * @return the largest key in the symbol table less than or equal to {@code key}
	 * @throws NoSuchElementException if there is no such key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public K floor(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to floor() is null");
		if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
		BinaryNode<K,V> x = floor(root, key);
		if (x == null) return null;
		else return x.getKey();
	} 

	private BinaryNode<K,V> floor(BinaryNode<K,V> x, K key)
	{
		if (x == null) return null;
		int cmp = key.compareTo(x.getKey());
		if (cmp == 0) return x;
		if (cmp <  0) return floor(x.getLeft(), key);
		BinaryNode<K,V> t = floor(x.getRight(), key); 
		if (t != null) return t;
		else return x; 
	} 

	public K floor2(K key) 
	{
		return floor2(root, key, null);
	}

	private K floor2(BinaryNode<K,V> x, K key, K best) 
	{
		if (x == null) return best;
		int cmp = key.compareTo(x.getKey());
		if      (cmp  < 0) return floor2(x.getLeft(), key, best);
		else if (cmp  > 0) return floor2(x.getRight(), key, x.getKey());
		else return x.getKey();
	} 

	/**
	 * Returns the smallest key in the symbol table greater than or equal to {@code key}.
	 *
	 * @param  key the key
	 * @return the smallest key in the symbol table greater than or equal to {@code key}
	 * @throws NoSuchElementException if there is no such key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public K ceiling(K key)
	{
		if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
		if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
		BinaryNode<K,V> x = ceiling(root, key);
		if (x == null) return null;
		else return x.getKey();
	}

	private BinaryNode<K,V> ceiling(BinaryNode<K,V> x, K key) 
	{
		if (x == null) return null;
		int cmp = key.compareTo(x.getKey());
		if (cmp == 0) return x;
		if (cmp < 0) { 
			BinaryNode<K,V> t = ceiling(x.getLeft(), key); 
			if (t != null) return t;
			else return x; 
		} 
		return ceiling(x.getRight(), key); 
	} 

	/**
	 * Return the key in the symbol table whose rank is {@code k}.
	 * This is the (k+1)st smallest key in the symbol table.
	 *
	 * @param  k the order statistic
	 * @return the key in the symbol table of rank {@code k}
	 * @throws IllegalArgumentException unless {@code k} is between 0 and
	 *        <em>n</em>-1
	 */
	public K select(int k) 
	{
		if (k < 0 || k >= size()) 
		{
			throw new IllegalArgumentException("argument to select() is invalid: " + k);
		}
		BinaryNode<K,V> x = select(root, k);
		return x.getKey();
	}

	// Return key of rank k. 
	private BinaryNode<K,V> select(BinaryNode<K,V> x, int k) 
	{
		if (x == null) return null; 
		int t = size(x.getLeft()); 
		if      (t > k) return select(x.getLeft(),  k); 
		else if (t < k) return select(x.getRight(), k-t-1); 
		else            return x; 
	} 

	/**
	 * Return the number of keys in the symbol table strictly less than {@code key}.
	 *
	 * @param  key the key
	 * @return the number of keys in the symbol table strictly less than {@code key}
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public int rank(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to rank() is null");
		return rank(key, root);
	} 

	// Number of keys in the subtree less than key.
	private int rank(K key, BinaryNode<K,V> x) 
	{
		if (x == null) return 0; 
		int cmp = key.compareTo(x.getKey()); 
		if      (cmp < 0) return rank(key, x.getLeft()); 
		else if (cmp > 0) return 1 + size(x.getLeft()) + rank(key, x.getRight()); 
		else              return size(x.getLeft()); 
	} 

	/**
	 * Returns all keys in the symbol table as an {@code Iterable}.
	 * To iterate over all of the keys in the symbol table named {@code st},
	 * use the foreach notation: {@code for (Key key : st.keys())}.
	 *
	 * @return all keys in the symbol table
	 */
	public Iterable<K> keys() 
	{
		if (isEmpty()) return new Queue<K>();
		return keys(min(), max());
	}

	/**
	 * Returns all keys in the symbol table in the given range,
	 * as an {@code Iterable}.
	 *
	 * @param  lo minimum endpoint
	 * @param  hi maximum endpoint
	 * @return all keys in the symbol table between {@code lo} 
	 *         (inclusive) and {@code hi} (inclusive)
	 * @throws IllegalArgumentException if either {@code lo} or {@code hi}
	 *         is {@code null}
	 */
	public Iterable<K> keys(K lo, K hi) 
	{
		if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
		if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

		Queue<K> queue = new Queue<K>();
		keys(root, queue, lo, hi);
		return queue;
	} 

	private void keys(BinaryNode<K,V> x, Queue<K> queue, K lo, K hi) 
	{ 
		if (x == null) return; 
		int cmplo = lo.compareTo(x.getKey()); 
		int cmphi = hi.compareTo(x.getKey()); 
		if (cmplo < 0) keys(x.getLeft(), queue, lo, hi); 
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.getKey()); 
		if (cmphi > 0) keys(x.getRight(), queue, lo, hi); 
	} 

	/**
	 * Returns the number of keys in the symbol table in the given range.
	 *
	 * @param  lo minimum endpoint
	 * @param  hi maximum endpoint
	 * @return the number of keys in the symbol table between {@code lo} 
	 *         (inclusive) and {@code hi} (inclusive)
	 * @throws IllegalArgumentException if either {@code lo} or {@code hi}
	 *         is {@code null}
	 */
	public int size(K lo, K hi)
	{
		if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
		if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

		if (lo.compareTo(hi) > 0) return 0;
		if (contains(hi)) return rank(hi) - rank(lo) + 1;
		else              return rank(hi) - rank(lo);
	}

	/**
	 * Returns the height of the BST (for debugging).
	 *
	 * @return the height of the BST (a 1-node tree has height 0)
	 */
	public int height() 
	{
		return height(root);
	}
	private int height(BinaryNode<K,V> x) 
	{
		if (x == null) return -1;
		return 1 + Math.max(height(x.getLeft()), height(x.getRight()));
	}

	/**
	 * Returns the keys in the BST in level order (for debugging).
	 *
	 * @return the keys in the BST in level order traversal
	 */
	@SuppressWarnings("unchecked")
	public Iterable<K> levelOrder()
	{
		Queue<K> keys = new Queue<K>();
		Queue<K> queue = new Queue<K>();
		queue.enqueue((K) root);
		while (!queue.isEmpty()) 
		{
			BinaryNode<K,V> x = (BinaryNode<K, V>) queue.dequeue();
			if (x == null) continue;
			keys.enqueue(x.getKey());
			queue.enqueue((K) x.getLeft());
			queue.enqueue((K) x.getRight());
		}
		return keys;
	}

	/*************************************************************************
	 *  Check integrity of BST data structure.
	 ***************************************************************************/
	private boolean check() 
	{
		if (!isBST())            System.out.println("Not in symmetric order");
		if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
		if (!isRankConsistent()) System.out.println("Ranks not consistent");
		return isBST() && isSizeConsistent() && isRankConsistent();
	}

	// does this binary tree satisfy symmetric order?
	// Note: this test also ensures that data structure is a binary tree since order is strict
	public boolean isBST() 
	{
		return isBST(root, null, null);
	}

	// is the tree rooted at x a BST with all keys strictly between min and max
	// (if min or max is null, treat as empty constraint)
	// Credit: Bob Dondero's elegant solution
	private boolean isBST(BinaryNode<K,V> x, K min, K max) 
	{
		if (x == null) return true;
		if (min != null && x.getKey().compareTo(min) <= 0) return false;
		if (max != null && x.getKey().compareTo(max) >= 0) return false;
		return isBST(x.getLeft(), min, x.getKey()) && isBST(x.getRight(), x.getKey(), max);
	} 

	// are the size fields correct?
	private boolean isSizeConsistent() 
	{ 
		return isSizeConsistent(root); 
	}

	private boolean isSizeConsistent(BinaryNode<K,V> x)
	{
		if (x == null) return true;
		if (x.size() != size(x.getLeft()) + size(x.getRight()) + 1) return false;
		return isSizeConsistent(x.getLeft()) && isSizeConsistent(x.getRight());
	} 

	// check that ranks are consistent
	private boolean isRankConsistent() 
	{
		for (int i = 0; i < size(); i++)
			if (i != rank(select(i))) return false;
		for (K key : keys())
			if (key.compareTo(select(rank(key))) != 0) return false;
		return true;
	}

}
