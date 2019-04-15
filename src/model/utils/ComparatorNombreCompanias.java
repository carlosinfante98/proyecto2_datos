package model.utils;

import model.vo.Compania;

public class ComparatorNombreCompanias implements ComparatorT<Compania> {

	@Override
	public int compare(Compania objI, Compania objD) 
	{
		if(objI.getNombre().compareTo(objD.getNombre()) > 0)
		{
			return 1;
		}
		else if(objI.getNombre().compareTo(objD.getNombre()) < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
		// TODO Auto-generated method stu
	}

}
