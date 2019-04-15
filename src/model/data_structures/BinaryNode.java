package model.data_structures;

public class BinaryNode<T extends Comparable<T>, V >	
{
	private T key;           // sorted by key
	private V val;         // associated data
	private BinaryNode<T,V> left, right;  // left and right subtrees
	private int size;          // number of nodes in subtree

	public BinaryNode(T key, V val, int size) 
	{
		this.key = key;
		this.val = val;
		this.size = size;
	}
	
	public int size()
	{
		return size;
	}
	
	public T getKey()
	{
		return key;
	}
	
	public BinaryNode<T,V> getLeft()
	{
		return left;
	}
	
	public BinaryNode<T,V> getRight()
	{
		return right;
	}
	
	public V getValue()
	{
		return val;
	}
	
	public void setLeft(BinaryNode<T,V> pNode)
	{
		left = pNode;
	}
	
	public void setRight(BinaryNode<T,V> pNode)
	{
		right = pNode;
	}
	
	public void setValue(V pValue)
	{
		val = pValue;
	}
	
	public void setSize(int pSize)
	{
		size = pSize;
	}
	
}