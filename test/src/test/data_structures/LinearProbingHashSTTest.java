package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ArrayListT;
import model.data_structures.LinearProbingHashST;

public class LinearProbingHashSTTest 
{
	/**
	 * Tabla de hash que usa linearProbing para manejar colisiones
	 */
	private LinearProbingHashST<String, ArrayListT<Integer>> lphasttable;
	
	/**
	 * ArrayList que contiene numeros aleatorios
	 */
	private ArrayListT<Integer> numerosAleatorios;

	/**
	 * Escenario de prueba 1
	 */
	@Before
	public void setupEscenario1()
	{
		lphasttable = new LinearProbingHashST<String, ArrayListT<Integer>>();
		for(int i= 0 ; i<5; i++)
		{
			int cont = i+1;
			numerosAleatorios = new ArrayListT<Integer>();
			for (int j = 0; j <10; j++)
			{
				int numeroA = (int) (Math.random()*170);
				numerosAleatorios.add(numeroA);
			}
			lphasttable.put("H"+cont,numerosAleatorios);
		}
	}
	
	/**
	 * Escenario de prueba 2
	 */
	@Before
	public void setupEscenario2()
	{
		lphasttable = new LinearProbingHashST<String, ArrayListT<Integer>>();
		for(int i= 0 ; i<50; i++)
		{
			int cont = i+1;
			numerosAleatorios = new ArrayListT<Integer>();
			for (int j = 0; j <10; j++)
			{
				int numeroA = (int) (Math.random()*170);
				numerosAleatorios.add(numeroA);
			}
			lphasttable.put("H"+cont,numerosAleatorios);
		}
	}
	
	/**
	 * Test para el metodo put de la clase LinearProbingHashST
	 */
	@Test
	public void putTest()
	{
		setupEscenario1();

//		lphasttable.put("H17", 17);
//		lphasttable.put("H17", 19);
//		lphasttable.put("H17", 21);
//		lphasttable.put("H17", 17);
		ArrayListT<Integer>  lista  = lphasttable.get("H2");
		assertTrue("Deberia dar el tamanio indicado", 10 == lista.size());
		assertNotNull("Deberia haber un elemento", lphasttable.get("H5"));
		assertNull("No deberia haber un elemento", lphasttable.get("H16"));
	}
	
	/**
	 * Test para el metodo delete de la clase LinearProbingHashST
	 */
	@Test
	public void deleteTest()
	{
		setupEscenario1();
		assertEquals("Eliminó un elemento que no hace parte de la lista.",null, lphasttable.delete("H10"));
		ArrayListT<Integer> listaAEliminar = lphasttable.get("H1");
		assertEquals("Eliminó un elemento que no hace parte de la lista.",listaAEliminar, lphasttable.delete("H1"));
	}
	
	/**
	 * Test para el metodo resize de la clase LinearProbingHashST
	 */
	@Test
	public void resizeTest()
	{
		setupEscenario2();
		int contador = 0;

		try
		{
			for (int i = 0; i < lphasttable.size(); i++) 
			{
				int cont = i+1;
				lphasttable.get("H" + cont);
				contador++;
			}
		}
		catch(Exception e)
		{
			fail("No debio botar error porque se agrando el tamaño");
		}

		assertTrue("Deberia haber más de 23 elementos", contador > 23);
	}
	
	/**
	 * Test para el metodo get de la clase LinearProbingHashST
	 */
	@Test
	public void getTest()
	{
		setupEscenario2();
		
		assertNotNull("Deberia retornar un elemento no nulo", lphasttable.get("H17"));
		assertNull("Deberia retornar un elemento nulo", lphasttable.get("H100"));
	}
	
	/**
	 * Test para el metodo keys de la clase LinearProbingHashST
	 */
	@Test
	public void keysTest()
	{
		setupEscenario1();
		int contador = 0;
		Iterable<String> ite = lphasttable.keys();
		for (String iterable_element : ite) 
		{
			assertNotNull("El elemento no debería ser nulo",iterable_element);
			contador++;
		}
		assertTrue("El tamaño deberia ser igual al numero de elementos en la tabla", contador == lphasttable.size());
	}
	
	
}
