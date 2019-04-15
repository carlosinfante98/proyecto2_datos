package model.data_structures;

/** Class BinaryHeap **/
public class BinaryHeap<T extends Comparable<T>>
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
	private T[] heap;

	//--------------------------------
	//CONSTRUCTOR
	//--------------------------------

	/**
	 * Crea que heap binario donde los elementos están organizados de manera descendente.
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap()
	{
		tamanioBase = 50;
		tamanioActual = 0;
		heap = (T[]) new Comparable[tamanioBase];
	}

	//--------------------------------
	//METODOS
	//--------------------------------

	/**
	 * Revisa que un elemento sea menor a otro dentro de la lista heap.
	 * @param i Primer elemento a comparar.
	 * @param j Segundo elemento a comparar.
	 * @return True si el primer elemento es menor al segundo. False de lo contrario.
	 */
	private boolean less(int i, int j)
	{  
		return heap[i].compareTo(heap[j]) < 0;  
	}

	/**
	 * Cambia dos elementos de posición dentro de la lista heap.
	 * @param i Primer elemento a cambiar.
	 * @param j Segundo elemento a cambiar.
	 */
	private void exch(int i, int j)
	{  
		T element = heap[i];
		heap[i] = heap[j];
		heap[j] = element; 
	}

	/**
	 * Va subiendo un elemento por las posiciones de la lista si está violando el orden del heap.
	 * @param k Posición desde la que se va a empezar el sink.
	 */
	private void swim(int k)
	{
		while (k > 1 && less(k/2, k))
		{
			exch(k/2, k);
			k = k/2; 
		}
	}

	/**
	 * Va bajando los elementos si están violando el orden heap.
	 * @param k Posición desde la que se va a empezar el sink.
	 */
	private void sink(int k)
	{
		while (2*k <= tamanioActual-1)
		{
			int j = 2*k;
			if (j < tamanioActual && less(j, j+1))
				j++;
			if (!less(k, j)) 
				break;
			exch(k, j);
			k = j;
		} 
	}

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
			if( tamanioBase == tamanioActual+1)
			{
				tamanioBase = (int) (tamanioBase*(1.25)) ;
				T [] backUp = heap;
				heap = (T[]) new Comparable[tamanioBase];
				for(int i=1; i < tamanioActual+1; i++)
				{
					heap[i] = backUp[i];
				}
				heap[++tamanioActual] = element;
				ele = heap[tamanioActual];
				swim(tamanioActual);
			}
			else
			{
				heap[++tamanioActual] = element;
				ele = heap[tamanioActual];
				swim(tamanioActual);	
			}
		}
		return ele;
	}

	/**
	 * Consigue el elemento de la lista dada una posición.
	 * @param position Posición del elemento que se busca en la lista.
	 * @return Elemento que se busca dentro de la lista por posición.
	 */
	public T get(int position) 
	{
		return (T) heap[position];
	}

	/**
	 * Remueve un elemento de la lista.
	 * @param element Elemento que se quiere remover de la lista.
	 * @return True si pudo remover un elemento de la lista. False de lo contrario.
	 */
	public T remove()
	{
		T elem = null;
		if(tamanioActual > 0)
		{	
			elem = heap[1];
			exch(1, tamanioActual);
			sink(1);
			tamanioActual--;
		}
		return elem;
	}

	/**
	 * Va bajando los elementos si están violando el orden heap.
	 * @param a Lista que se va a modificar.
	 * @param k Posición desde la que se va a empezar el sink.
	 * @param n Posición hasta la que se va a dar el sink.
	 */
	private void sink(T[] a, int k, int n)
	{
		while (2*k <= n)
		{
			int j = 2*k;
			if (j < n && less(a, j, j+1))
				j++;
			if (!less(a, k, j)) 
				break;
			exch(a, k, j);
			k = j;
		} 
	}

	/**
	 * Cambia dos elementos de posición dentro de una lista.
	 * @param a lista de la que se van a cambiar elementos
	 * @param i Primer elemento a cambiar.
	 * @param j Segundo elemento a cambiar.
	 */
	private void exch(T[] a, int i, int j)
	{  
		T element = a[i];
		a[i] = a[j];
		a[j] = element; 
	}

	/**
	 * Revisa que un elemento sea menor a otro dentro de una lista.
	 * @param a Lista de la que se va a hacer comparación de elementos.
	 * @param i Primer elemento a comparar.
	 * @param j Segundo elemento a comparar.
	 * @return True si el primer elemento es menor al segundo. False de lo contrario.
	 */
	private boolean less(T[] a, int i, int j)
	{  
		return a[i].compareTo(a[j]) < 0;  
	}



	/**
	 * Ordena un arreglo que está implementado como heap.
	 * @param a Arreglo que se quiere ordenar.
	 */
	public void sort(T[] a)
	{
		int N = a.length;
		for (int k = N/2; k >= 1; k--)
			sink(a, k, N);
		while (N > 1)
		{
			exch(a, 1, N--);
			sink(a, 1, N);
		}
	}

	public void sort(boolean ascendente)
	{
		int N = size();
		if(ascendente)
		{		
			while (N > 1)
			{
				exch(heap, 1, N--);
				sink(heap, 1, N);
			}			
		}
		else
		{
			for (int k = N/2; k >= 1; k--)
				sink(heap, k, N);
			while (N > 1)
			{
				exch(heap, 1, N--);
				sink(heap, 1, N);
			}
			for(int i=1; i < (size()/2)+1; i++)
			{
				T temp = heap[i];
				heap[i] = heap[size() + 1 - i];
				heap[size() + 1 - i] = temp; 
			}			
		}
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

	/**
	 * Tamaño actual de la lista.
	 * @return Número del tamaño actual de la lista.
	 */
	public int size()
	{
		return tamanioActual;
	}

}