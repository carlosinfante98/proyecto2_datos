package model.vo;


import model.data_structures.ArrayListT;

public class Distancia implements Comparable<Distancia>
{
	private double distanciaEnMillas;
	
	private ArrayListT<Servicio> serviciosEnDistancia;
	
	public Distancia(double pDis) 
	{
		distanciaEnMillas = pDis;
		serviciosEnDistancia = new ArrayListT<Servicio>();
		// TODO Auto-generated constructor stub
	}

	public double getDistanciaEnMillas() {
		return distanciaEnMillas;
	}

	public void setDistanciaEnMillas(double distanciaEnMillas) {
		this.distanciaEnMillas = distanciaEnMillas;
	}

	public ArrayListT<Servicio> getServiciosEnDistancia() {
		return serviciosEnDistancia;
	}

	@Override
	public int compareTo(Distancia o)
	{
		int compare =  (int)(distanciaEnMillas - o.getDistanciaEnMillas());
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
