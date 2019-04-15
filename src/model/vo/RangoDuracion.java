package model.vo;

import model.data_structures.ArrayListT;

public class RangoDuracion implements Comparable<RangoDuracion>
{
	private int limiteInferior;
	private int limiteSuperior;
	
	private ArrayListT<Servicio> serviciosEnRango;
	
	public RangoDuracion(int pLimiteInferior, int pLimiteSuperior) 
	{
		setLimiteInferior(pLimiteInferior);
		setLimiteSuperior(pLimiteSuperior);
		serviciosEnRango = new ArrayListT<Servicio>();
	}

	public int getLimiteInferior()
	{
		return limiteInferior;
	}

	public void setLimiteInferior(int limiteInferior) 
	{
		this.limiteInferior = limiteInferior;
	}

	public int getLimiteSuperior()
	{
		return limiteSuperior;
	}
	/**
	 * @return the serviciosEnRango
	 */
	public ArrayListT<Servicio> getServiciosEnRango() 
	{
		return serviciosEnRango;
	}

	public void setLimiteSuperior(int limiteSuperior)
	{
		this.limiteSuperior = limiteSuperior;
	}

	@Override
	public int compareTo(RangoDuracion o) 
	{
		 int compare = limiteSuperior - o.getLimiteSuperior();
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
		// TODO Auto-generated method stub
	}

}
