package model.utils;

import model.vo.Taxi;

public class ComparatorTaxiCash implements ComparatorT<Taxi> {

	@Override
	public int compare(Taxi objI, Taxi objD)
	{
		if(objI != null && objD != null)
		{
			int comparar = (int)( objI.getPlataGanada()- objD.getPlataGanada());

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
		// TODO Auto-generated method stub
	}

}
