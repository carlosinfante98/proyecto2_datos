package model.utils;

import java.util.Random;

import model.data_structures.ArrayListT;
import model.data_structures.IArrayListT;

public class OrdenatorP<T extends Comparable<T>> 
{
	public enum Ordenamientos
	{
		MERGE, 
		QUICKSORT,
		SHELLSORT,
		INSERTION;
	}
	public void ordenar(Ordenamientos algo, boolean ascendente, ComparatorT<T> comparador, IArrayListT<T> lista)
	{
		switch(algo)
		{
		case MERGE:
			mergeSort(lista, ascendente, comparador);
			break;
		case QUICKSORT:
			quickSort(lista, ascendente, comparador);
			break;
		case SHELLSORT:
			shellSort(lista, ascendente, comparador);
			break;
		case INSERTION:
			insertionSort(lista, ascendente, comparador);
			break;
		}
	}

	private void quickSort(IArrayListT<T> lista, boolean ascendente, ComparatorT<T> comparador)
	{
		shuffleArray(lista);
		quickSorting(lista, 0, lista.size()-1, ascendente, comparador);
	}

	private void quickSorting(IArrayListT<T> lista, int low, int high, boolean ascendente, ComparatorT<T> comparador)
	{
		if (low < high)
		{
			/* pi is partitioning index, arr[pi] is 
            now at right place */
			int pi = quickSortpartition(lista, low, high, ascendente, comparador);

			// Recursively sort elements before
			// partition and after partition
			quickSorting(lista, low, pi-1, ascendente, comparador);
			quickSorting(lista, pi+1, high, ascendente, comparador);
		}
	}

	private void shellSort(IArrayListT<T> lista, boolean ascendente, ComparatorT<T> comparador)
	{
		int inner, outer;
		T temp;

		int h = 1;
		while (h <= lista.size() / 3) 
		{
			h = h * 3 + 1;
		}
		while (h > 0)
		{
			for (outer = h; outer < lista.size(); outer++) 
			{
				temp = lista.get(outer);
				inner = outer;

				if(ascendente)
				{
					while (inner > h - 1 && comparador.compare(lista.get(inner - h), temp) >= 0)
					{
						lista.insert(inner, lista.get(inner - h));
						inner -= h;
					}

					lista.insert(inner, temp);	        	
				}
				else if(!ascendente)
				{
					while (inner > h - 1 && comparador.compare(lista.get(inner - h), temp) <= 0)
					{
						lista.insert(inner, lista.get(inner - h));
						inner -= h;
					}

					lista.insert(inner, temp);
				}
			}
			h = (h - 1) / 3;
		}
	}

	/**
	 * Ordena la lista usando el algoritmo de inscerción post: la lista se encuentra ordenada
	 * @param lista La lista que se desea ordenar. lsita != null
	 * @param ascendente Indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenará de manera descendente.
	 * @param comparador Comparador de elementos tipo T que se usará para ordenar la lista, define el criterio de orden. comparador != null.
	 */
	private void insertionSort(IArrayListT<T> lista, boolean ascendente, ComparatorT<T> comparador)
	{
		for (int i = 1; i < lista.size(); i++) 
		{
			if(ascendente)
			{
				for (int j = i; j > 0 && comparador.compare(lista.get(j-1), lista.get(j)) > 0; j--)
				{
					T a = lista.get(j-1);
					T b = lista.get(j);

					lista.insert(j, a);
					lista.insert(j-1, b);

				}
			}
			else if(!ascendente)
			{
				for (int j = i; j > 0 && comparador.compare(lista.get(j-1), lista.get(j)) < 0; j--)
				{
					T a = lista.get(j-1);
					T b = lista.get(j);

					lista.insert(j, a);
					lista.insert(j-1, b);
				}
			}
		}

	}

	/* This function takes last element as pivot,
    places the pivot element at its correct
    position in sorted array, and places all
    smaller (smaller than pivot) to left of
    pivot and all greater elements to right
    of pivot */
	private int quickSortpartition(IArrayListT<T> lista, int low, int high, boolean ascendente, ComparatorT<T> comparador)
	{
		T pivot = lista.get(high); 
		int i = (low-1); // index of smaller element
		for (int j=low; j<high; j++)
		{

			if(ascendente)
			{
				// If current element is smaller than or
				// equal to pivot
				if (comparador.compare(lista.get(j), pivot) <= 0)
				{
					i++;

					// swap arr[i] and arr[j]
					T a = lista.get(i);
					T b = lista.get(j);

					lista.insert(j, a);
					lista.insert(i, b);
				}
			}
			else if(!ascendente)
			{
				if (comparador.compare(lista.get(j), pivot) >= 0)
				{
					i++;

					// swap arr[i] and arr[j]
					T a = lista.get(i);
					T b = lista.get(j);

					lista.insert(j, a);
					lista.insert(i, b);
				}
			}

		}
		// swap arr[i+1] and arr[high] (or pivot)
		T c = lista.get(i+1);
		lista.insert(i+1, lista.get(high));
		lista.insert(high, c);

		return i+1;
	}

	private void mergeSort(IArrayListT<T> a, boolean ascendente, ComparatorT<T> comparador)
	{
		ArrayListT<T> aux = new ArrayListT<T>(a.size());
		sort(a, aux, 0, a.size() - 1, ascendente, comparador);
	}

	private void sort(IArrayListT<T> a, IArrayListT<T> aux, int lo, int hi, boolean ascendente, ComparatorT<T> comparador)
	{
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid, ascendente, comparador);
		sort(a, aux, mid+1, hi, ascendente, comparador);
		merge(a, aux, lo, mid, hi, ascendente, comparador);
	}

	private void merge(IArrayListT<T> a, IArrayListT<T> aux, int lo, int mid, int hi, boolean ascendente, ComparatorT<T> comparador)
	{

		for (int k = lo; k <= hi; k++)
			aux.insert(k, a.get(k));
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++)
		{
			if(ascendente)
			{		
				if (i > mid) a.insert(k, aux.get(j++));
				else if (j > hi) a.insert(k, aux.get(i++));
				else if (comparador.compare(aux.get(j), aux.get(i)) <= 0) a.insert(k, aux.get(j++));
				else a.insert(k, aux.get(i++));
			}
			else if(!ascendente)
			{
				if (i > mid) a.insert(k, aux.get(j++));
				else if (j > hi) a.insert(k, aux.get(i++));
				else if (comparador.compare(aux.get(j), aux.get(i)) >= 0) a.insert(k, aux.get(j++));
				else a.insert(k, aux.get(i++));
			}
		}
	} 

	public void shuffleArray(IArrayListT<T> a)
	{
		int n = a.size();
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) 
		{
			int change = i + random.nextInt(n - i);
			swap(a, i, change);
		}
	}

	private void swap(IArrayListT<T> a, int i, int change)
	{
		T helper = a.get(i);
		a.insert(i, a.get(change));
		a.insert(change, helper);
	}


}
