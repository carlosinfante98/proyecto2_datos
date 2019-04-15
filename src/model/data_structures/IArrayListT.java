package model.data_structures;

/**
 * Interfaz para poder manejar ArrayList genérico.
 * @param <T> Elemento genérico.
 */
public interface IArrayListT<T>
{
	//--------------------------------
	//METODOS
	//--------------------------------
	
	/**
	 * Agrega un elemento que se da por parámetro.
	 * @param element Elemento que se quiere agregar a la lista. element != null
	 * @return El elemento que agregó a la lista.
	 */
	public T add(T element);
	
	/**
	 * Remueve un elemento de la lista.
	 * @param element Elemento que se quiere remover de la lista.
	 * @return True si pudo remover un elemento de la lista. False de lo contrario.
	 */
	public boolean remove( T element);
	
	/**
	 * Remueve un elemento de la lista buscando por su posición.
	 * @param position Posición de la lista sobre la cual se quiere remover un dato.
	 * @return Elemento que se removió de la lista. Null si no encontró el elemento.
	 */
	public T remove (int position);
	
	/**
	 * Inserta un elemento en la lista con la posición dada por parámetro.
	 * @param position Posición del arreglo sobre la cual se quiere insertar un elemento.
	 * @param element Elemento el cual quiere ser insertado.
	 */
	public void insert( int position, T element);
	
	/**
	 * Agrega en una posición dada por parámetro en la lista y corre los demás elementos.
	 * @param position Posición del arreglo sobre la cual se quiere insertar un elemento.
	 * @param element Elemento el cual quiere ser insertado.
	 * @return True si pudo agregar el elemento en la lista. False de lo contrario.
	 */
	public boolean addIn(int position, T element);
	
	/**
	 * Busca el índice de un elemento.
	 * @param element Elemento al cual se le quiere buscar el índice.
	 * @return Indice del elemento que si pidió buscar.
	 */
	public int indexOf(T element);
	
	/**
	 * Consigue el elemento de la lista dada una posición.
	 * @param position Posición del elemento que se busca en la lista.
	 * @return Elemento que se busca dentro de la lista por posición.
	 */
	public T get(int position);
	
	/**
	 * Consigue el elemento de la lista.
	 * @param element Elemento que se busca en la lista.
	 * @return Elemento que se busca dentro de la lista.
	 */
	public T get(T element);
	
	/**
	 * Tamaño actual de la lista.
	 * @return Número del tamaño actual de la lista.
	 */
	public int size();
	
	/**
	 * Remueve todos los elementos que están en la lista.
	 */
	public void removeAll();
	
	/**
	 * Revisa si el arreglo está vacío.
	 * @return True si el arreglo está vacío. False de lo contrario.
	 */
	public boolean isEmpty();
}
