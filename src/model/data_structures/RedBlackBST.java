package model.data_structures;

import java.util.NoSuchElementException;

//Ref : https://algs4.cs.princeton.edu/code/
public class RedBlackBST<K extends Comparable<K>, V >
{
	

	//--------------------------------
	//------CONSTANTES----------------
	//--------------------------------

	/**
	 * Constante booleana que indica si la rama es roja.
	 */
	private static final boolean RED = true;
	/**
	 * Constante booleana que indica si la rama es negra.
	 */
	private static final boolean BLACK = false;

	//--------------------------------
	//------Atributos----------------
	//--------------------------------

	/**
	 * Atributo nodo que hace referencia a la raiz del arbol.
	 */
	private Node root;

	private int totalValores;

	private class Node 
	{
		private K key;           // key
		private V values;         // associated data
		private Node left;
		private Node right;  // links to left and right subtrees
		private boolean color;     // color of parent link
		private int size;    

		public Node(K key,V val, boolean color, int size) 
		{
			this.setKey(key);
			this.setValues(val);
			this.setColor(color);
			this.setSize(size);
		}
		public void setKey(K key) 
		{
			this.key = key;
		}

		public V getVal()
		{
			return values;
		}

		public void setValues(V val)
		{
			this.values = val;
		}

		public Node getLeft() 
		{
			return left;
		}

		public void setLeft(Node left) 
		{
			this.left = left;
		}

		public Node getRight()
		{
			return right;
		}

		public void setRight(Node right)
		{
			this.right = right;
		}

		public boolean getColor() 
		{
			return color;
		}

		public void setColor(boolean color) 
		{
			this.color = color;
		}

		public int getSize()
		{
			return size;
		}

		public void setSize(int size)
		{
			this.size = size;
		}
	}

	public RedBlackBST()
	{
		totalValores = 0;
	}

	private boolean isRed(Node pNode) 
	{
		if(pNode == null)
			return false;
		return pNode.getColor()==RED;
	}
	
	public int darTotalValores()
	{
		return totalValores;
	}

	private int size(Node pNode)
	{
		if( pNode == null)
			return 0;
		else
			return pNode.getSize();
	}

	public int totalSize()
	{
		return size(root);
	}
	public boolean isEmpty()
	{
		return root == null;
	}

	private V get(Node pNode, K key)
	{
		while (pNode != null)
		{
			int cmp = key.compareTo(pNode.key);
			if (cmp < 0) pNode = pNode.left;
			else if (cmp > 0) pNode = pNode.right;
			else return pNode.getVal();
		}
		return null;


	}

	public V get(K key)
	{
		if(key == null)throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}

	public boolean contains(K key)
	{
		return get(key) != null;
	}

	//añadir lista de elementos en el arbol
	public void put(K key,V val)
	{
		boolean avance = false;
		if(key == null)throw new IllegalArgumentException("argument to put() is null");
		if(val == null)
		{
			avance = true;
		}
		if(!avance)
		{
			root = put(root, key, val);
			root.setColor(BLACK);
		}
	}

	private Node put(Node pNode, K key, V val) 
	{ 
		if (pNode == null) return new Node(key, val, RED, 1);

		int cmp = key.compareTo(pNode.key);
		if      (cmp < 0) pNode.setLeft(put(pNode.left,  key, val)); 
		else if (cmp > 0) pNode.setRight(put(pNode.right, key, val)); 
		else             pNode.setValues(val);

		// fix-up any right-leaning links
		if (isRed(pNode.getRight()) && !isRed(pNode.getLeft()))      pNode = rotateLeft(pNode);
		if (isRed(pNode.getLeft())  &&  isRed(pNode.getLeft().getLeft())) pNode = rotateRight(pNode);
		if (isRed(pNode.getLeft())  &&  isRed(pNode.getRight()))     flipColors(pNode);
		pNode.size = size(pNode.getLeft()) + size(pNode.getRight()) + 1;
		totalValores = pNode.size;

		return pNode;
	}
	public void deleteMin()
	{
		if(isEmpty())throw new NoSuchElementException();
		//cumpliendo la ley de que si se cambian dos ramas hijas a negro, su padre será rojo
		if(!isRed(root.getLeft()) &&  !isRed(root.getRight()))
		{
			root.setColor(RED);
		}
		root = deleteMin(root);
		//si no se eliminó la raiz, cambiela a negro.
		if(!isEmpty()) 
		{
			root.setColor(BLACK);
		}
	}

	private Node deleteMin(Node pNode) 
	{ 
		if (pNode.getLeft() == null)
			return null;

		if (!isRed(pNode.getLeft()) && !isRed(pNode.getLeft().getLeft()))
			pNode = moveRedLeft(pNode);

		pNode.setLeft(deleteMin(pNode.getLeft()));
		return balance(pNode);
	}

	public void deleteMax()
	{
		if(isEmpty())throw new NoSuchElementException();
		//cumpliendo la ley de que si se cambian dos ramas hijas a negro, su padre será rojo
		if(!isRed(root.getLeft()) &&  !isRed(root.getRight()))
		{
			root.setColor(RED);
		}
		root = deleteMax(root);
		//si no se eliminó la raiz, cambiela a negro.
		if(!isEmpty()) 
		{
			root.setColor(BLACK);
		}
	}

	private Node deleteMax(Node pNode)
	{
		if(isRed(pNode.getLeft()))
		{
			pNode = rotateRight(pNode);
		}
		if(pNode.getRight() == null)
		{
			return null;
		}
		if(!isRed(pNode.getRight()) && !isRed(pNode.getRight().getLeft()))
			pNode = moveRedRight(pNode);

		pNode.setRight(deleteMax(pNode.getRight()));

		return balance(pNode);
	}
	public void delete(K key)
	{ 
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return;

		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete(root, key);
		if (!isEmpty()) root.color = BLACK;
	}

	private Node delete(Node pNode, K key) { 
		// assert get(h, key) != null;

		if (key.compareTo(pNode.key) < 0)  
		{
			if (!isRed(pNode.left) && !isRed(pNode.left.left))
				pNode = moveRedLeft(pNode);
			pNode.left = delete(pNode.left, key);
		}
		else {
			if (isRed(pNode.left))
				pNode = rotateRight(pNode);
			if (key.compareTo(pNode.key) == 0 && (pNode.right == null))
				return null;
			if (!isRed(pNode.right) && !isRed(pNode.right.left))
				pNode = moveRedRight(pNode);
			if (key.compareTo(pNode.key) == 0) {
				Node x = min(pNode.right);
				pNode.setKey(x.key);
				pNode.setValues(x.getVal());
				// h.val = get(h.right, min(h.right).key);
				// h.key = min(h.right).key;
				pNode.right = deleteMin(pNode.right);
			}
			else pNode.right = delete(pNode.right, key);
		}
		return balance(pNode);
	}
	public K min() 
	{
		if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
		return min(root).key;
	} 

	private Node min(Node x)
	{ 
		if(x != null)
		{
			if (x.left == null) return x; 
			else return min(x.left); 
		}
		else
		{
			throw new IllegalArgumentException();
		}
	} 
	public K max() {
		if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
		return max(root).key;
	} 

	// the largest key in the subtree rooted at x; null if no such key
	private Node max(Node x) { 
		// assert x != null;
		if (x.right == null) return x; 
		else                 return max(x.right); 
	} 

	private Node rotateRight(Node pNode) 
	{
		Node x = pNode.getLeft();
		pNode.setLeft(x.getRight());
		x.setRight(pNode);
		x.setColor(x.getRight().getColor());
		x.getRight().setColor(RED);
		x.setSize(pNode.getSize());
		pNode.setSize(size(pNode.getLeft()) + size(pNode.getRight()) + 1);
		//nueva raiz
		return x;
	}

	private Node rotateLeft(Node pNode) 
	{
		Node x = pNode.getRight();
		pNode.setRight(x.getLeft());
		x.setLeft(pNode);
		x.setColor(x.getLeft().getColor());
		x.getLeft().setColor(RED);
		x.setSize(pNode.getSize());
		pNode.setSize(size(pNode.getLeft()) + size(pNode.getRight()) + 1);
		//nueva raiz
		return x;
	}

	//cuando las dos ramas de un padre son rojas.
	private void flipColors(Node h) 
	{
		h.setColor(!h.getColor()); 
		h.getLeft().setColor(!h.getLeft().getColor()); 
		h.getRight().setColor(!h.getRight().getColor()); 
	}

	// Assuming that h is red and both h.left and h.left.left
	// are black, make h.left or one of its children red.
	private Node moveRedLeft(Node h)
	{
		// assert (h != null);
		// assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

		flipColors(h);
		if (isRed(h.right.left)) 
		{ 
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	// Assuming that h is red and both h.right and h.right.left
	// are black, make h.right or one of its children red.
	private Node moveRedRight(Node h)
	{
		// assert (h != null);
		// assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
		flipColors(h);
		if (isRed(h.left.left))
		{ 
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	// restore red-black tree invariant
	private Node balance(Node pNode) 
	{
		if(pNode != null)
		{

			if (isRed(pNode.getRight())) pNode = rotateLeft(pNode);
			if (isRed(pNode.getLeft()) && isRed(pNode.getLeft().getLeft())) pNode = rotateRight(pNode);
			if (isRed(pNode.getLeft()) && isRed(pNode.getRight()))flipColors(pNode);

			pNode.setSize(size(pNode.getLeft()) + size(pNode.getRight()) + 1);
		}
		return pNode;
	}
	/**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public IArrayListT<V> values() {
        if (isEmpty()) return (IArrayListT<V>) new ArrayListNotComparable<V>();
        return values(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the sybol table between {@code lo} 
     *    (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *    is {@code null}
     */
    public IArrayListT<V> values(K lo, K hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        ArrayListNotComparable<V> queue = new ArrayListNotComparable<V>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        values(root, queue, lo, hi);
        return queue;
    } 

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void values(Node x, ArrayListNotComparable<V> queue, K lo, K hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) values(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.getVal()); 
        if (cmphi > 0) values(x.right, queue, lo, hi); 
    } 
	//utilitaries
	// does this binary tree satisfy symmetric order?
	// Note: this test also ensures that data structure is a binary tree since order is strict
	public boolean isBST() 
	{
		return isBST(root, null, null);
	}

	// is the tree rooted at x a BST with all keys strictly between min and max
	// (if min or max is null, treat as empty constraint)
	// Credit: Bob Dondero's elegant solution
	private boolean isBST(Node x, K min, K max)
	{
		if (x == null) return true;
		if (min != null && x.key.compareTo(min) <= 0) return false;
		if (max != null && x.key.compareTo(max) >= 0) return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	}


}
