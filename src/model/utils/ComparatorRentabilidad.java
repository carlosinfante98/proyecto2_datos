package model.utils;

import model.vo.Taxi;

public class ComparatorRentabilidad implements ComparatorT<Taxi> {

	@Override
	public int compare(Taxi objI, Taxi objD)
	{
		double r = objI.getRentabilidad() - objD.getRentabilidad();
		if(r > 0)
		{
			return 1;
		}
		else if(r < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
		// TODO Auto-generated method stub
	}

}
