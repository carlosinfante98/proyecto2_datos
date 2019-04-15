package model.utils;

import model.vo.Servicio;

public class ComparatorServiciosDistancia implements ComparatorT<Servicio>
{
	/**
	 * Compara dos servicios de acuerdo a su distancia.
	 * @return 1 si el servicio o1 es mayor a la banda o2 por su distancia.
	 * 		   -1 si el servicio o1 es menor a la banda o2 por su distancia.
	 * 		   0 si el servicio o1 es igual al servicio o2 por su distancia.
	 */
	public int compare(Servicio o1, Servicio o2)
	{
		if(o1 != null && o2 != null)
		{
			int comparar = (int)( o1.getRangoDistancia().getLimiteInferior() - o2.getRangoDistancia().getLimiteInferior());

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
