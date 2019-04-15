package model.data_structures;

import java.util.Iterator;

public class SingleLinkedList<T extends Comparable<T>> extends AbstractList<T>
{

	private SimpleNode<T> actualNode;
	private SimpleNode<T> lastNode;
	
	public  SingleLinkedList()
	{
		firstNode = null;
		actualNode = null;
		size = 0;
		lastNode = null;
	}
	
	public SingleLinkedList(T nFirst)
	{
		if(nFirst == null)
		{
			throw new NullPointerException("Se recibe un elemento nulo");
		}
		firstNode = new DoubleNode<T>(nFirst);
		lastNode = firstNode;
		actualNode = null;
		size = 1;
	}
	
	@Override
	public boolean add(T element)
	{
		boolean agregado = false;
		if(element == null)
		{
			throw new NullPointerException();
		}
		if(!this.contains(element))
		{
			if(this.isEmpty())
			{
				this.firstNode = new SimpleNode<T>(element);
				this.lastNode = firstNode;
			}
			else
			{
				SimpleNode<T> nuevo = new SimpleNode<T>(element);
				nuevo.changeNext(lastNode.getNextNode());
				lastNode.changeNext(nuevo);
				lastNode = nuevo;
			}
			agregado = true;
			size++;
		}
		return agregado;
	}

	@Override
	public boolean delete(T element)
	{
		boolean elimino = false;
		boolean encontro = false;

		SimpleNode<T> temp = (SimpleNode<T>) this.firstNode;
		SimpleNode<T> previous = (SimpleNode<T>)null;
		while(temp != null && ! encontro)
		{
			if(temp.getElement().compareTo((T)element) == 0)
			{
				encontro = true;
			}
			else
			{		
				temp = (SimpleNode<T>) temp.getNextNode();
				previous = temp;
			}
		}
		if(encontro)
		{
			if(temp.getNextNode() == null)
			{
				System.out.println("Es el último.");
				temp = null;
				previous.changeNext(null);
			}
			else if(temp.equals(firstNode))
			{
				firstNode = firstNode.getNextNode();
			}
			else
			{
				previous.changeNext(temp.getNextNode());
				temp = null;
			}	
			elimino = true;
			this.size--;
		}
		return elimino;	
	}

	@Override
	public T get(T o)
	{
		SimpleNode<T> temp = (SimpleNode<T>)this.firstNode;
		T searched = null;
		boolean found = false;
		if(temp != null)
		{
			while(temp != null && !found)
			{
				if(temp.getElement().compareTo((T)o) == 0)
				{
					found = true;
					searched = temp.getElement();
				}
				else
				{
					temp = (SimpleNode<T>) temp.getNextNode();
				}
			}
		}
		return searched;
	}

	@Override
	public void listing() 
	{
		actualNode = (SimpleNode<T>)this.firstNode;
	}

	@Override
	public T getCurrent() 
	{
		return actualNode.getElement();
	}

	@Override
	public boolean next() 
	{
		boolean exists = false;
		if(actualNode != null)
		{
			exists = true;
			actualNode = (SimpleNode<T>) actualNode.getNextNode();
		}
		return exists;
	}
	@Override
	public Iterator<T> iterator() 
	{
		return new IteratorList<T>(firstNode);
	}

}
