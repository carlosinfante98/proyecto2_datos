package model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.vo.Servicio;

public class ComparatorServicioFechaInicial implements ComparatorT<Servicio> {
	/**
	 * Compara dos servicios de acuerdo a su fecha inicial de servicio.
	 * @return 1 si el servicio o1 es mayor a la banda o2 por su fecha inicial de servicio.
	 * 		   -1 si el servicio o1 es menor a la banda o2 por su fecha inicial de servicio.
	 * 		   0 si el servicio o1 es igual al servicio o2 por su fecha inicial de servicio.
	 */
	public int compare(Servicio o1, Servicio o2)
	{
		SimpleDateFormat form = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss.SSS");

		int comparar;
		try {
			if(o1 != null && o2 != null)
			{
				comparar = form.parse(o1.getFechaHoraInicial()).compareTo(form.parse(o2.getFechaHoraInicial()));

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
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		return -2;
	}

}


