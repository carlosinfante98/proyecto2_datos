package model.utils;

import model.vo.Taxi;

public class ComparatorIdTaxi implements ComparatorT<Taxi>
{
	/**
	 * Compara dos taxis de acuerdo a su id identificador.
	 * @return 1 si el taxi o1 es mayor al taxi o2 por su id.
	 * 		   -1 si el taxi o1 es menor al taxi o2 por su id.
	 * 		   0 si el taxi o1 es igual al taxi o2 por su id.
	 */
	public int compare(Taxi o1, Taxi o2)
	{
		if(o1 != null && o2 != null)
		{
			int comparar = o1.getTaxiId().compareTo(o2.getTaxiId());

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
