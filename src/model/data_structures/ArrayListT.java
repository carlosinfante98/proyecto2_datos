package model.data_structures;

/**
 * Clase de ArrayList genérica.
 * @param <T> Elemento genérico dentro de la lista.
 */
public class ArrayListT<T extends Comparable<T>> implements IArrayListT<T>
{
	//--------------------------------
	//ATRIBUTOS
	//--------------------------------

	/**
	 * Atributo que hace referencia al tamaño base de la lista.
	 */
	private int tamanioBase;

	/**
	 * Atributo que hace referencia al tamaño actual de la lista.
	 */
	private int tamanioActual;

	/**
	 * Atributo que hace referencia al arreglo para implementar la lista.
	 */
	private T[] elements;

	//--------------------------------
	//CONSTRUCTORES
	//--------------------------------

	/**
	 * Crea el arrayList con un tamaño base predeterminado e inicializa el arreglo para la lista.
	 */
	@SuppressWarnings("unchecked")
	public ArrayListT()
	{
		tamanioBase = 50;
		tamanioActual = 0;
		elements = (T[]) new Comparable[tamanioBase];
	}

	/**
	 * Crea el arrayList con un tamaño base elegido por parámetro e inicializa el arreglo para la lista.
	 */
	@SuppressWarnings("unchecked")
	public ArrayListT(int pTamanioBase)
	{
		tamanioBase = pTamanioBase;
		tamanioActual = 0;
		elements = (T[]) new Comparable[pTamanioBase];
	}

	//--------------------------------
	//METODOS
	//--------------------------------

	/**
	 * Agrega un elemento que se da por parámetro.
	 * @param element Elemento que se quiere agregar a la lista. element != null
	 * @return El elemento que agregó a la lista.
	 */
	@SuppressWarnings("unchecked")
	public T add(T element)
	{
		T ele = null;
		if(element != null)
		{
			if( tamanioBase == tamanioActual)
			{
				tamanioBase = (int) (tamanioBase*(1.25)) ;
				T [] backUp = elements;
				elements = (T[]) new Comparable[tamanioBase];
				for(int i=0; i< tamanioActual; i++)
				{
					elements[i] = backUp[i];
				}
				elements[tamanioActual] = element;
				ele = elements[tamanioActual];
			}
			else
			{
				elements[tamanioActual] = element;
				ele = elements[tamanioActual];
			}
			tamanioActual++;
		}
		return ele;
	}

	/**
	 * Remueve un elemento de la lista.
	 * @param element Elemento que se quiere remover de la lista.
	 * @return True si pudo remover un elemento de la lista. False de lo contrario.
	 */
	public boolean remove(T element)
	{
		boolean encontrado = false;
		for(int i=0;i< tamanioActual && !encontrado; i++)
		{
			if(elements[i].compareTo(element) == 0)
			{
				elements[i] = null;
				encontrado = true;
				tamanioActual--;
			}
		}
		return encontrado;
	}

	/**
	 * Remueve un elemento de la lista buscando por su posición.
	 * @param position Posición de la lista sobre la cual se quiere remover un dato.
	 * @return Elemento que se removió de la lista. Null si no encontró el elemento.
	 */
	public T remove(int position)
	{
		if(position > tamanioBase - 1 || position < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		T elemento = (T) elements[position];
		elements[position] = null;
		tamanioActual--;
		return elemento;
	}

	/**
	 * Inserta un elemento en la lista con la posición dada por parámetro.
	 * @param position Posición del arreglo sobre la cual se quiere insertar un elemento.
	 * @param element Elemento el cual quiere ser insertado.
	 */
	public void insert(int position,T element)
	{
		if(position > tamanioBase - 1 || position < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(elements[position] == null)
		{
			tamanioActual++;
		}
		elements[position]= element;
	}

	/**
	 * Agrega en una posición dada por parámetro en la lista y corre los demás elementos.
	 * @param position Posición del arreglo sobre la cual se quiere insertar un elemento.
	 * @param element Elemento el cual quiere ser insertado.
	 * @return True si pudo agregar el elemento en la lista. False de lo contrario.
	 */
	@SuppressWarnings("unchecked")
	public boolean addIn(int position, T element)
	{
		boolean encontrado = false;
		if(position > tamanioBase || position < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			if(tamanioActual == tamanioBase)
			{
				tamanioBase = (int)(tamanioBase * (1.20)) ;
				T[] backUp = elements;
				elements = (T[]) new Comparable[tamanioBase];
				for(int i=0; i< elements.length; i++)
				{
					elements[i] = backUp[i];
				}	
			}
			int i = position;
			encontrado = true;
			tamanioActual++;
			T[] temp = elements;
			for(int k=i+1; k < tamanioActual ;k++)
			{
				T siguiente = temp[k];
				elements[k] = temp[i];
				i++;
				temp[i] = siguiente;
			}
			elements[position] = element;
		}
		return encontrado;
	}

	/**
	 * Busca el índice de un elemento.
	 * @param element Elemento al cual se le quiere buscar el índice.
	 * @return Indice del elemento que si pidió buscar.
	 */
	public int indexOf(T element)
	{
		boolean encontrado = false;
		int indice = 0;
		for(int i=0;i< tamanioActual && !encontrado; i++)
		{
			if(elements[i].compareTo(element) == 0)
			{
				indice = i;
				encontrado = true;
			}
		}
		return indice;
	}

	/**
	 * Consigue el elemento de la lista dada una posición.
	 * @param position Posición del elemento que se busca en la lista.
	 * @return Elemento que se busca dentro de la lista por posición.
	 */
	public T get(int position) 
	{
		return (T) elements[position];
	}

	/**
	 * Consigue el elemento de la lista.
	 * @param element Elemento que se busca en la lista.
	 * @return Elemento que se busca dentro de la lista.
	 */
	public T get(T element)
	{
		T obj = null;
		boolean enco = false;
		for (int i = 0; i < tamanioActual && !enco; i++) 
		{
			if(elements[i].compareTo(element) == 0)
			{
				obj = (T) elements[i];
				enco = true;
			}
		}
		return obj;
	}

	/**
	 * Tamaño actual de la lista.
	 * @return Número del tamaño actual de la lista.
	 */
	public int size() 
	{
		return tamanioActual;
	}

	/**
	 * Remueve todos los elementos que están en la lista.
	 */
	public void removeAll() 
	{
		elements = null;
		tamanioActual = 0;
	}
	
	/**
	 * Se convierte el arreglo a una lista.
	 * @return Lista que en su inicio era un arreglo.
	 */
	public DoubleLinkedList<T> toList()
	{
		DoubleLinkedList<T> list = new DoubleLinkedList<T>();
		for (int i = 0; i < tamanioActual; i++) 
		{
			list.add(elements[i]);
		}
		return list;
	}

	/**
	 * Revisa si el arreglo está vacío.
	 * @return True si el arreglo está vacío. False de lo contrario.
	 */
	public boolean isEmpty()
	{
		if(tamanioActual == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
