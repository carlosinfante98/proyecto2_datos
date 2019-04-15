package model.utils;

import model.vo.ZonaServicios;

public class ComparatorArea implements ComparatorT<ZonaServicios>{

	@Override
	public int compare(ZonaServicios objI, ZonaServicios objD)
	{
		return (objI.getIdZona()-objD.getIdZona())>0?1:((objI.getIdZona()-objD.getIdZona())<0)?-1:0;
		// TODO Auto-generated method stub
	}

}
