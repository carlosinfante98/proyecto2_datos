package model.vo;

import model.data_structures.ArrayListT;
/**
 * VO utilizado en Req 5A, tiene el rango de distancia y la lista de servicios cuya distancia recorrida 
 * pertenece a dicho rango
 */
public class RangoDistancia implements Comparable<RangoDistancia>
{
	//ATRIBUTOS
	
    /**
     * Modela el valor minimo del rango
     */
	private double limiteSuperior;
	
	/**
	 * Modela el valor maximo del rango
	 */
	private double limiteInferior;
	
	/**
	 * Modela la lista de servicios cuya distancia recorrida esta entre el limite inferior y el limite superior
	 */
	private ArrayListT<Servicio> serviciosEnRango;
	
	public RangoDistancia(double pLimiteInf, double pLimiteSup)
	{
		limiteInferior = pLimiteInf;
		limiteSuperior = pLimiteSup;
		serviciosEnRango = new ArrayListT<Servicio>();
	}

	//METODOS
	
	/**
	 * @return the limiteSuperior
	 */
	public double getLimiteSuperior()
	{
		return limiteSuperior;
	}

	/**
	 * @param limiteSuperior the limiteSuperior to set
	 */
	public void setLimiteSuperior(double limiteSuperior) 
	{
		this.limiteSuperior = limiteSuperior;
	}

	/**
	 * @return the limiteInferior
	 */
	public double getLimiteInferior() 
	{
		return limiteInferior;
	}

	/**
	 * @param limiteInferior the limineInferior to set
	 */
	public void setLimiteInferior(double limiteInferior) 
	{
		this.limiteInferior = limiteInferior;
	}

	/**
	 * @return the serviciosEnRango
	 */
	public ArrayListT<Servicio> getServiciosEnRango() 
	{
		return serviciosEnRango;
	}

	/**
	 * @param serviciosEnRango the serviciosEnRango to set
	 */
	public void setServiciosEnRango(ArrayListT<Servicio> serviciosEnRango)
	{
		this.serviciosEnRango = serviciosEnRango;
	}

	@Override
	public int compareTo(RangoDistancia o)
	{
		int compare = (int)(limiteInferior - o.getLimiteInferior());
		if(compare < 0)
		{
			return -1;
		}
		else if(compare > 0)
		{
			return 1;
		}
		else
		{
			return 0;			
		}
	}


}
