package model.vo;

import model.data_structures.ArrayListT;
import model.data_structures.SeparateChainingHashST;


/**
 * Representation of a taxi object
 */
public class Taxi implements Comparable<Taxi>
{
	private String taxi_id;
	private String company;
	private int numeroServicios;
	private double plataGanada;
	private SeparateChainingHashST<Integer,ArrayListT<Servicio>> tablaZonaServicios;
	private ArrayListT<Servicio> services ;
	private double distanciaTotal;
	private int tiempoServicios;
	private int numServiciosZona;

	private double rentabilidad;

	public Taxi(String pTaxi_id, String company_name)
	{
		taxi_id = pTaxi_id;
		company = company_name;
		numeroServicios = 0;
		plataGanada = 0;
		distanciaTotal = 0.0;
		tiempoServicios = 0;
		rentabilidad = 0;
		numServiciosZona = 0;
		services = new ArrayListT<>();
		tablaZonaServicios = new SeparateChainingHashST<Integer, ArrayListT<Servicio>>();
	}

	/**
	 * @return id - taxi_id
	 */
	public String getTaxiId()
	{
		return  taxi_id;
	}

	/**
	 * @return company
	 */
	public String getCompany()
	{
		return company;
	}

	public int getTiempoServicios()
	{
		return tiempoServicios;
	}

	public SeparateChainingHashST<Integer,ArrayListT<Servicio>> getHashTable()
	{
		return tablaZonaServicios;
	}
	public int getNumServicesInRange()
	{
		return numeroServicios;
	}
	public double getPlataGanada()
	{
		return plataGanada;
	}

	public double getDistanciaRecorrida()
	{
		return distanciaTotal;
	}
	public void setPlataGanada(double pPlata)
	{
		 plataGanada += pPlata;
	}
	public void setDistanciaRecorrida(double pDis )
	{
		 distanciaTotal += pDis;
	}
	public void setTotalServicios()
	{
		numeroServicios ++;
	}
	public double puntos()
	{
		return distanciaTotal != 0.0 ? (plataGanada/distanciaTotal)* numeroServicios: 0.0;
	}
	
	public double getRentabilidad()
	{
		return rentabilidad;
	}
	
	public void setNumServiciosZona(int pZona)
	{
		numeroServicios = tablaZonaServicios.get(pZona).size();
	}
	
	public int getNumServiciosZona()
	{
		return numeroServicios;
	}

	@Override
	public int compareTo(Taxi o) 
	{
		int compare = (int) (this.puntos() - o.puntos());
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

	public String toString()
	{
		return taxi_id;
	}

	public ArrayListT<Servicio> getServices() 
	{
		return services;
	}
}