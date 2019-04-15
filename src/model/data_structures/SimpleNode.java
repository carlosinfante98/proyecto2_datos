package model.data_structures;

public class SimpleNode<T extends Comparable<T>> 
{
	private T element;
	
	private SimpleNode<T> nextNode;
	
	public SimpleNode(T element)
	{
		this.element = element;
	}
	
	public void changeNext(SimpleNode<T> next)
	{
		this.nextNode = next;
	}
	
	public T getElement()
	{
		return element;
	}
	
	public void setElement(T element)
	{
		this.element = element;
	}
	
	public SimpleNode<T> getNextNode()
	{
		return nextNode;
	}
	
	@Override
	public String toString()
	{
		return element.toString();
	}
}
