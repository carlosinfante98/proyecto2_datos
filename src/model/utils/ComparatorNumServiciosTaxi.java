package model.utils;

import model.vo.Taxi;

public class ComparatorNumServiciosTaxi implements ComparatorT<Taxi>
{

	@Override
	public int compare(Taxi o1, Taxi o2)
	{
		if(o1 != null && o2 != null)
		{
			int comparar = o1.getNumServiciosZona() - o2.getNumServiciosZona();

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
		return -2;	}
	

}
