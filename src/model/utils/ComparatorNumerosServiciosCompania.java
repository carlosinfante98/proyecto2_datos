package model.utils;

import model.vo.Compania;

public class ComparatorNumerosServiciosCompania implements ComparatorT<Compania>
{

	@Override
	public int compare(Compania objI, Compania objD)
	{
		return (objI.getNumServiciosEnRango()-objD.getNumServiciosEnRango())>0?1:((objI.getNumServiciosEnRango()-objD.getNumServiciosEnRango())<0)?-1:0;
	}
	

}
