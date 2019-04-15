package model.utils;

import model.vo.Servicio;

public class ComparatorServicesMiles implements ComparatorT<Servicio>
{
	public int compare(Servicio o1, Servicio o2)
	{
		if(o1 != null && o2 != null)
		{
			double comparar = o1.getTripMiles() - o2.getTripMiles();

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
