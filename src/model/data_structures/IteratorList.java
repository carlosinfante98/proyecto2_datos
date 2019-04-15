package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase iteradora de elementos genéricos.
 * @param <T> Elemento genérico dentro de la lista.
 */
public class IteratorList<T extends Comparable<T>> implements Iterator<T>
{
	/**
	 * Nodo actual del iterador.
	 */
	private SimpleNode<T> actual;

	/**
     * Crea un nuevo iterador sobre un nodo actual
     * @param firstNode el nodo donde se desea que inicie el iterador
     */
	public IteratorList(SimpleNode<T> firstNode) 
	{
		actual = firstNode;
	}
	
	/**
     * Indica si hay elementos por recorrer en el iterador
     * @return True en caso de que haya elemetos por recorrer. False de lo contrario
     */
	public boolean hasNext() 
	{
		return actual != null;
	}

	/**
     * Retorna el siguiente elemento del iterador.
     * <b>post:</b> El nodo siguiente ahora es el actual del iterador.
     * @return Objeto actual del iterador.
     */
	public T next() 
	{
		if(!hasNext())
		{
			throw new NoSuchElementException("No existe ningun nodo");

		}
		T valor = actual.getElement();
		actual = actual.getNextNode();
		return valor;
	}

}
