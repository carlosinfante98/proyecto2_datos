package model.data_structures;

/**
 * Clase para manejar la interfaz de cola genérica.
 * @param <T> Elemento genérico en la cola.
 */
public interface IQueue <T extends Comparable<T>>extends Iterable<T> 
{
	/**
	 * Agregar un item al tope de la cola
	 */
	public void enqueue(T item);

	/**
	 * Elimina el elemento al tope de la cola
	 */
	public T dequeue();

	/**
	 * Indica si la cola esta vacia
	 */
	public boolean isEmpty();

	/**
	 * Numeros de elementos en la lista
	 */
	public int size();
}