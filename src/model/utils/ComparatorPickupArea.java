package model.utils;

import model.vo.Servicio;

public class ComparatorPickupArea implements ComparatorT<Servicio>
{

	/**
	 * Compara dos servicios de acuerdo a su zona de recogida.
	 * @return 1 si el servicio o1 es mayor a la banda o2 por su zona de recogida.
	 * 		   -1 si el servicio o1 es menor a la banda o2 por su zona de recogida.
	 * 		   0 si el servicio o1 es igual al servicio o2 por su zona de recogida.
	 */
	public int compare(Servicio o1, Servicio o2)
	{
		if(o1 != null && o2 != null)
		{
			int comparar = o1.getPickupArea() - o2.getPickupArea();

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
