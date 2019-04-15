package model.data_structures;

import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> implements IStack<T>  
{
	
	private SimpleNode<T> firstNode;
	
	private int size;
		
	public Stack()
	{
		firstNode = null;
		size = 0;
		verifyInvariant();
	}
	
	@Override
	public void push(T item)
	{
		SimpleNode<T> tempFirst = firstNode;
		firstNode = new SimpleNode<T>(item);
		firstNode.changeNext(tempFirst);
		size++;
		verifyInvariant();
	}

	@Override
	public T pop() 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("No se puede eliminar ya que no hay nada en la lista");
		}
		T element = firstNode.getElement();
		firstNode = firstNode.getNextNode();
		size--;
		verifyInvariant();
		
		return element;
	}

	@Override
	public boolean isEmpty() 
	{
		return firstNode == null;
	}
	
	public int size()
	{
		return size;
	}
	
	public SimpleNode<T> getActualNode()
	{
		return firstNode;
	}

	@Override
	public IteratorList<T> iterator() 
	{
		return new IteratorList<T>(firstNode);
	}
	
	/**
	 * Verifica la invariante de la clase.
	 * Mira que el tamaño de la lista sea mayor a 0. Si es igual a 1 mira que si haya un nodo. Si es mayor a 1 mira que haya nodos.
	 */
	public void verifyInvariant()
	{
		assert size >= 0: "El size de la pila no puede ser menor a 0";
		
		if(size == 0)
		{
			assert firstNode == null: "El primer nodo tiene que ser null si el size es 0";
		}
		else if( size == 1)
		{
			assert firstNode != null : "El primer nodo no puede ser nulo si el size > 0";
			assert firstNode.getNextNode() == null : "Si solo existe el primer nodo su siguiente es nulo";
		}
		else
		{
			assert firstNode != null : "Primer nodo no puede ser nulo";
			assert firstNode.getNextNode() != null : "Siguiente del primer nodo no puede ser nulo";
		}
	}


}
