package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ArrayListT;
import model.data_structures.BinarySearchTree;

public class BinarySearchTreeTest 
{
	private BinarySearchTree<String, ArrayListT<Integer>> tree ;

	private ArrayListT<Integer> numerosAleatorios;

	@Before
	public void setupEscenario1()
	{
		tree = new BinarySearchTree<String, ArrayListT<Integer>>();
		for(int i= 0 ; i<5; i++)
		{
			int cont = i+1;
			numerosAleatorios = new ArrayListT<Integer>();
			for (int j = 0; j <6; j++)
			{
				int numeroA = (int) (Math.random()*170);
				numerosAleatorios.add(numeroA);
			}
			if(cont <10)
			{
				tree.put("H0"+cont,numerosAleatorios);
			}
			else
				tree.put("H"+cont,numerosAleatorios);
		}
	}

	@Test
	public void putTest()
	{
		setupEscenario1();
		tree.put("H17", new ArrayListT<Integer>());
		tree.get("H17").add(17);
		tree.get("H17").add(19);
		tree.get("H17").add(21);
		tree.get("H17").add(17);

		ArrayListT<Integer> lista = tree.get("H17");
		for (int j = 0; j < lista.size(); j++)
		{
			assertNotNull("No deberia existir la lista", tree.get("H17"));
		}
		lista.add(20);
		lista.add(23);
		tree.put("H16", lista);
		tree.put("H01", lista);
		tree.put("H02", lista);
		tree.put("H02", lista);
		tree.put("H04", lista);
		tree.put("H05", lista);

		assertEquals(tree.get("H16").size(), lista.size());
		int cont = 0;
		for(int i=0; i < 5 ; i++)
		{
			cont = i+1;
			System.out.println(tree.get("H0"+cont).toString());
		}	
		assertTrue("Debería ser un arbol binario aún" , tree.isBST());
	}

	@Test
	public void sizeTest()
	{
		setupEscenario1();
		assertEquals("No se halló correctamente el tamaño total del arbol.",5, tree.size());
	}

	@Test
	public void deleteTest()
	{
		setupEscenario1();
		tree.deleteMin();
		tree.put("H06", new ArrayListT<Integer>());
		assertEquals("El tamanio no es el esperado", 5,tree.size());
		assertEquals(null, tree.get("H01"));
		for (int i = 0; i < tree.size()+1 ; i++) 
		{
			if(i != 0)
			{
				ArrayListT<Integer> arrayList = tree.get("H0"+(i+1));
				System.out.println("--------------------- H0"+(i+1));
				for (int j = 0; j < arrayList.size() ; j++) 
				{
					System.out.println(arrayList.get(j));
				}
			}
		}
		tree.deleteMax();
		assertEquals("No debería existir",null, tree.get("H06"));
		tree.delete("H03");
		assertEquals("No debería existir",null, tree.get("H03"));
		
		assertTrue("Debería ser un arbol binario aún " , tree.isBST());
	}
}
