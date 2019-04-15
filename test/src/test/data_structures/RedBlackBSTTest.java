package test.data_structures;

import model.data_structures.ArrayListT;
import model.data_structures.RedBlackBST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RedBlackBSTTest 
{

	private RedBlackBST<String, ArrayListT<Integer>> tree ;

	private ArrayListT<Integer> numerosAleatorios;

	@Before
	public void setupEscenario1()
	{
		tree = new RedBlackBST<String, ArrayListT<Integer>>();
		for(int i= 0 ; i<5; i++)
		{
			int cont = i+1;
			numerosAleatorios = new ArrayListT<Integer>();
			for (int j = 0; j <6; j++)
			{
				int numeroA = (int) (Math.random()*170);
				numerosAleatorios.add(numeroA);
			}

			tree.put("H"+cont,numerosAleatorios);
		}
	}

	@Test
	public void putTest()
	{
		setupEscenario1();
		ArrayListT<Integer> lista = tree.get("H4");
		lista.add(20);
		lista.add(23);
		tree.put("H16", lista);
		assertEquals(tree.get("H16").size(), lista.size());
		int cont = 0;
		for(int i=0; i < 5 ; i++)
		{
			cont = i+1;
			System.out.println(tree.get("H"+cont).toString());
		}	
		assertTrue("Debería ser un arbol binario aún " , tree.isBST());
	}
	
	@Test
	public void sizeTest()
	{
		setupEscenario1();
		assertEquals("No se halló correctamente el tamaño total del arbol.",5, tree.totalSize());
	}
	
	@Test
	public void deleteTest()
	{
		setupEscenario1();
		tree.deleteMin();
		tree.put("H6", new ArrayListT<Integer>());
		assertEquals("El tamanio no es el esperado", 5,tree.totalSize());
		assertEquals(null, tree.get("H1"));
		for (int i = 0; i < tree.totalSize()+1 ; i++) 
		{
			if(i != 0)
			{
				ArrayListT<Integer> arrayList = tree.get("H"+(i+1));
				System.out.println("--------------------- H"+(i+1));
				for (int j = 0; j < arrayList.size() ; j++) 
				{
					System.out.println(arrayList.get(j));
				}
			}
		}
		tree.deleteMax();
		assertEquals("No debería existir",null, tree.get("H6"));
		tree.delete("H3");
		assertEquals("No debería existir",null, tree.get("H3"));
		
		assertTrue("Debería ser un arbol binario aún " , tree.isBST());
	}

}
