package model.utils;

import model.vo.FechaServicios;

public class ComparatorFechaServicios implements ComparatorT<FechaServicios>
{
	/**
	 * Compara dos servicios de acuerdo a su fecha inicial de servicio.
	 * @return 1 si el servicio o1 es mayor a la banda o2 por su fecha inicial de servicio.
	 * 		   -1 si el servicio o1 es menor a la banda o2 por su fecha inicial de servicio.
	 * 		   0 si el servicio o1 es igual al servicio o2 por su fecha inicial de servicio.
	 */
	public int compare(FechaServicios o1, FechaServicios o2)
	{
		if(o1 != null && o2 != null)
		{
			int comparar = o1.getFechaDeReferencia().compareTo(o2.getFechaDeReferencia());

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
