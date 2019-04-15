package model.data_structures;

import java.util.NoSuchElementException;

/**
 * Clase de cola genérica.
 * @param <T> Elemento genérico en la cola.
 */
public class Queue<T extends Comparable<T>> implements IQueue<T> 
{
	//--------------------------------
	//ATRIBUTOS
	//--------------------------------
	
	/**
	 * Atributo que hace referencia al primer nodo.
	 */
	private SimpleNode<T> firstNode;
	
	/**
	 * Atributo que hace referencia al último nodo.
	 */
	private SimpleNode<T> lastNode;
	
	/**
	 * Atributo que hace referencia al tamaño de la cola.
	 */
	private int size;

	//--------------------------------
	//CONSTRUCTOR
	//--------------------------------
	
	/**
	 * Crea una cola con nodos vacíos.
	 */
	public Queue()
	{
		firstNode = null;
		lastNode = null;
		size = 0;	
		verifyInvariant();
	}
	
	//--------------------------------
	//METODOS
	//--------------------------------
	
	/**
	 * Agregar un item al tope de la cola
	 */
	public void enqueue(T item)
	{
		SimpleNode<T> tempLast = lastNode;
		lastNode = new SimpleNode<T>(item);
		lastNode.changeNext(null);
		if(isEmpty())
		{
			firstNode = lastNode;
		}
		else
		{
			tempLast.changeNext(lastNode);
		}
		size++;
		verifyInvariant();
	}
	
	/**
	 * Elimina el elemento al tope de la cola
	 */
	@Override
	public T dequeue() 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		T element = firstNode.getElement();
		firstNode = firstNode.getNextNode();
		size--;
		
		if(isEmpty())
		{
			lastNode = null;
		}
		verifyInvariant();

		return element;
	}

	/**
	 * Indica si la cola esta vacia
	 */
	@Override
	public boolean isEmpty()
	{
		return firstNode == null;
	}

	/**
	 * Numeros de elementos en la lista
	 */
	@Override
	public int size() 
	{
		return size;
	}
	
	/**
	 * Da el primer nodo.
	 * @return Primer nodo.
	 */
	public SimpleNode<T> getFirstNode()
	{
		return firstNode;
	}
	
	/**
	 * Da el último nodo.
	 * @return Último nodo.
	 */
	public SimpleNode<T> getLastNode()
	{
		return lastNode;
	}
	
	/**
	 * Da el iterador de la cola desde el primer nodo.
	 */
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
		assert size >= 0: "El tamanio no puede ser menor a 0";
		if(size == 0)
		{
			assert firstNode == null: "Primer nodo debe ser null si no hay ningun elemento";
			assert lastNode == null: "Ultimo nodo debe ser null si no hay ningun elemento";

		}
		else if( size == 1)
		{
			assert firstNode != null : "Primer nodo deberia ser diferente de null";
			assert firstNode == lastNode : "El primer nodo deberia ser igual al ultimo";
			assert firstNode.getNextNode() == null :"El siguiente del primer nodo deberia ser null";
		}
		else
		{
			assert firstNode != null: "Primer nodo deberia ser diferente de null";
			assert firstNode != lastNode : "El primer nodo no deberia ser igual al ultimo";
			assert firstNode.getNextNode() != null : "El primer nodo deberia tener un siguiente";
			assert lastNode.getNextNode() == null : "El siguiente del ultimo nodo deberia ser null";
		}
	}
	
}
