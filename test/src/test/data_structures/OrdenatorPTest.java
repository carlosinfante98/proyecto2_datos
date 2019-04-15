package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ArrayListNotComparable;
import model.data_structures.ArrayListT;
import model.utils.ComparatorT;
import model.utils.OrdenatorP;
import model.utils.OrdenatorP.Ordenamientos;

public class OrdenatorPTest
{
	private ArrayListNotComparable<String> array;

	private OrdenatorP<String> ordenador;

	private Comparador comparador;

	@Before
	public void setupEscenario1()
	{
		ordenador = new OrdenatorP<String>();
		array = new ArrayListNotComparable<String>();

		for(int i= 0 ; i<102 ; i++)
		{
			int cont = (int) Math.round(Math.random()*100);
			if(cont <100 && cont>=10)
			{
				array.add("A00"+cont);
			}
			else if(cont < 10)
			{
				array.add("A000"+cont);				
			}
		}
		comparador = new Comparador();
	}

	@Test
	public void insertionTest()
	{
		setupEscenario1();
		ordenador.ordenar(Ordenamientos.INSERTION, false, comparador, array);
		for (int i = 0; i < array.size()-1; i++) 
		{
			assertTrue("Estan mal ordenados de manera descendente", comparador.compare(array.get(i), array.get(i+1)) >= 0);
		}
		ordenador.ordenar(Ordenamientos.INSERTION, true, comparador, array);
		for (int i = 0; i < array.size()-1; i++) 
		{
			assertTrue("Estan mal ordenados de manera ascendente", comparador.compare(array.get(i), array.get(i+1)) <= 0);
		}
	}

	@Test
	public void shellSortTest()
	{
		setupEscenario1();
		ordenador.ordenar(Ordenamientos.SHELLSORT, false, comparador, array);
		for (int i = 0; i < array.size()-1; i++) 
		{
			assertTrue("Estan mal ordenados de manera descendente", comparador.compare(array.get(i), array.get(i+1)) >= 0);
		}
		ordenador.ordenar(Ordenamientos.SHELLSORT, true, comparador, array);
		for (int i = 0; i < array.size()-1; i++) 
		{
			assertTrue("Estan mal ordenados de manera ascendente", comparador.compare(array.get(i), array.get(i+1)) <= 0);
		}
	}

	@Test
	public void quickSortTest()
	{
		setupEscenario1();
		ordenador.ordenar(Ordenamientos.QUICKSORT, false, comparador, array);

		for (int i = 0; i < array.size()-1; i++) 
		{
			assertTrue("Estan mal ordenados de manera descendente", comparador.compare(array.get(i), array.get(i+1)) >= 0);
		}

		ordenador.ordenar(Ordenamientos.QUICKSORT, true, comparador, array);
		for (int i = 0; i < array.size()-1; i++) 
		{
			assertTrue("Estan mal ordenados de manera ascendente", comparador.compare(array.get(i), array.get(i+1)) <= 0);
		}
	}

	@Test
	public void mergeSortTest()
	{
		setupEscenario1();

		ordenador.ordenar(Ordenamientos.MERGE, false, comparador, array);

		for (int i = 0; i < array.size()-1; i++) 
		{
			System.out.println(array.get(i));
			assertTrue("Estan mal ordenados de manera descendente", comparador.compare(array.get(i), array.get(i+1)) >= 0);
		}

		ordenador.ordenar(Ordenamientos.MERGE, true, comparador, array);

		for (int i = 0; i < array.size()-1; i++) 
		{
			assertTrue("Estan mal ordenados de manera ascendente", comparador.compare(array.get(i), array.get(i+1)) <= 0);
		}
		
	}

	private class Comparador implements ComparatorT<String>
	{
		public int compare(String o1, String o2) 
		{
			if(o1 != null && o2 != null)
			{
				int comparar = o1.compareTo(o2);

				if(comparar > 0)
				{
					return 1;
				}
				else if(comparar < 0)
				{
					return -1;
				}
				else
				{
					return 0;
				}
			}
			return -2;
		}
	}

}
