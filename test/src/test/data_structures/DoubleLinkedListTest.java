package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data_structures.DoubleLinkedList;

public class DoubleLinkedListTest {

	@Test
	public void test() 
	{
		String a = "Hi";
		String b = "Ha";
		String c = "Hi";
		String d = "He";
		String e = "Ho";

		DoubleLinkedList<String> lista = new DoubleLinkedList<String>(a);
		lista.add(b);
		lista.add(c);
		lista.add(d);
		lista.add(e);

		if(lista.size() == 0)
		{
			fail("No agrega.");
		}
		else
		{
			if(!lista.get(0).equals(a))
			{
				fail("Añadió mal el primer nodo.");
			}
			else if(!lista.get(3).equals(e))
			{
				fail("Error");
			}
		}
		if(!lista.delete(b))
		{
			fail("Error");
		}
		else
		{
			if(lista.delete("Huu"))
			{
				fail("Error");
			}
			if(lista.size() != 3)
			{
				fail("Error en el size");
			}
		}
		if(lista.isEmpty())
		{
			fail("Error");
		}
		lista.set(0, "RAIZ");
		if(!lista.get(0).equals("RAIZ"))
		{
			fail("Error");
		}
		//Prueba mï¿½todo next() y listing
		int contador = 0;
		lista.listing();
		while(lista.next())
		{
			contador++;
		}
		if(contador != 3)
		{
			fail("Error al usar next");
		}
		//Prueba mï¿½todo previous y relisting
		for(int i=0; i< lista.size();i++)
		{
			 if(lista.previous())
			 {
				if(i == lista.size()-1)
				{
					if(!lista.next())
					{
						fail("ERROR.");
					}
				}
			 }
		}

		
	}
}
