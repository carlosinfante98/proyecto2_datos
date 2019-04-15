package model.vo;

import model.data_structures.ArrayListT;
import model.utils.ComparatorFechaServicios;
import model.utils.OrdenatorP;
import model.utils.OrdenatorP.Ordenamientos;

public class ZonaServicios implements Comparable<ZonaServicios>
{
	//--------------------------------
	//ATRIBUTOS
	//--------------------------------
	
	/**
	 * id de la zona
	 */
	private int idZona;
	/**
	 * lista de servicios zonados
	 */
	private ArrayListT<Servicio> serviciosZonados;
	/**
	 * lista de fecha servicios
	 */
	private ArrayListT<FechaServicios> fechasServicio;
	/**
	 * Constructor ZonaServicios
	 */
	
	//--------------------------------
	//CONSTRUCTOR
	//--------------------------------
	
	public ZonaServicios(int pIdZona)
	{
		idZona = pIdZona;
		serviciosZonados = new ArrayListT<Servicio>();
		fechasServicio = new ArrayListT<FechaServicios>();
	}
	
	//--------------------------------
	//METODOS
	//--------------------------------
	
	/**
	 * id de la zona
	 * @return int idZona
	 */
	public int getIdZona() 
	{
		return idZona;
	}
	/**
	 * Lista de servicios de la zona
	 * @return ArrayListT serviciosZonados
	 */
	public ArrayListT<Servicio> getServiciosZonados()
	{
		return serviciosZonados;
	}
	/**
	 * Lista de fecha de la zona
	 * @return ArrayListT fechasServicios
	 */
	public ArrayListT<FechaServicios> getFechasServicios()
	{
		return fechasServicio;
	}
	/**
	 * Ordenar fechas de la zona
	 */
	public void ordenarFechasServicio()
	{
		OrdenatorP<FechaServicios> ordene = new OrdenatorP<FechaServicios>();
		ComparatorFechaServicios compart = new ComparatorFechaServicios();
		ordene.ordenar(Ordenamientos.QUICKSORT, true, compart, fechasServicio);
	}
	/**
	 * Verifica existencia de la fecha en la lista
	 * @return FechaServicios FechasServicios si existe . null si no existe
	 */
	public FechaServicios existsFechaServ(String pFecha)
	{
		FechaServicios fechaS = null;
		boolean existe = false;
		if(fechasServicio.size() >0 )
		{
			for (int i = 0; i < fechasServicio.size() && !existe; i++) 
			{
				if(fechasServicio.get(i).getFechaDeReferencia().equals(pFecha))
				{
					existe = true;
					fechaS = fechasServicio.get(i);
				}
			}
		}
		return fechaS;
	}
	/**
	 * Lista de zonas acotada por un rango.
	 * @param r RangoFechaHora
	 * @return ArrayListT lista de zonas acotadas
	 */
	public ArrayListT<Servicio> listaZonasAcotada(RangoFechaHora r)
	{
		try
		{
			ArrayListT<Servicio> arr = new ArrayListT<Servicio>();
			//Aplicar búsqueda binaria para simplicar la búsqueda del rango
			for(int j=0; j < serviciosZonados.size(); j++)
			{
				if(serviciosZonados.get(j).estaEnElRango(r))
				{
					arr.add(serviciosZonados.get(j));
				}

			}
			return arr;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}	
	/**
	 * Lista de fechas acotada por un rango.
	 * @param r RangoFechaHora
	 * @return ArrayListT lista de fecha acotadas
	 */
	public ArrayListT<FechaServicios> listaAcotada(RangoFechaHora r)
	{
		ArrayListT<FechaServicios> arr = new ArrayListT<FechaServicios>();
		for (int i = 0; i < fechasServicio.size(); i++)
		{
			if(fechasServicio.get(i).perteneceAlRango(r))
			{
				arr.add(fechasServicio.get(i));
			}
		}
		return arr;
	}
	/**
	 * @param o ZonaServicios zonaServicios con la que se comparara
	 * @return int resultado de comparar una zonaservicios
	 */
	@Override
	public int compareTo(ZonaServicios o) 
	{
		int compare = idZona - o.idZona;
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