package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import model.data_structures.ArrayListT;

public class ArrayListTest 
{
	private ArrayListT<String> array;
	
	@Before
	public void setupEscenario1()
	{
		array = new ArrayListT<String>();
		for(int i= 0 ; i<50; i++)
		{
			int cont = i+1;
			array.add("H"+cont);
		}
	}
	
	@Test
	public void testAdd()
	{
		setupEscenario1();
		array.add("H500");
		if(array.size() != 51)
		{
			fail("No duplica.");
		}
		array.add("H501");
	}
	
	@Test
	public void testRemove()
	{
		setupEscenario1();
		array.remove("H30");
		if(array.size()!= 49)
		{
			fail("Elimino mal.");
		}
		assertEquals(1, array.indexOf("H2"));
		assertTrue("Debio borrar el elemento", array.remove("H1"));
	}
	
	@Test
	public void testIsEmpty()
	{
		setupEscenario1();
		array.removeAll();
		assertTrue(array.isEmpty());
	}
	
	@Test
	public void getMethodTest()
	{
		setupEscenario1();
		
		assertTrue("Deberia dar el elemento pedido", array.get(3).equals("H4"));
		assertTrue("Deberia dar el elemento pedido", array.get(48).equals("H49"));
	}


}
