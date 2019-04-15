package model.utils;


import model.vo.Taxi;

public class ComparatorServiciosRango implements ComparatorT<Taxi>
{
	@Override
	public int compare(Taxi objI, Taxi objD)
	{
		if(objI != null && objD != null)
		{
			int comparar = objI.getNumServicesInRange() - objD.getNumServicesInRange();

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
