package model.vo;

import model.data_structures.ArrayListT;
import model.utils.ComparatorIdTaxi;
import model.utils.OrdenatorP;
import model.utils.OrdenatorP.Ordenamientos;

public class Compania implements Comparable<Compania> 
{
	//--------------------------------
	//ATRIBUTOS
	//--------------------------------
	
	/**
	 * Nombre de las compañia.
	 */
	private String nombre;
	/**
	 * Lista de taxis compania
	 */
	private ArrayListT<Taxi> taxisCompania;
	/**
	 * Lista de servicios compania
	 */
	private ArrayListT<Servicio> serviciosCompania;	
	/**
	 * Numero de taxis compania
	 */
	private int numTaxisCompania;
	/**
	 * Numero de servicios compania
	 */
	private int numServiciosCompania;
	/**
	 * Numero de servicios en rango
	 */
	private int numServiciosEnRango;
	
	//--------------------------------
	//CONSTRUCTOR
	//--------------------------------
	
	/**
	 * Constructor Compania	
	 * @param company_name Nombre de la compania
	 */
	public Compania(String company_name) 
	{ 
		nombre = company_name;
		taxisCompania = new ArrayListT<Taxi>();
		serviciosCompania = new ArrayListT<Servicio>();
		numTaxisCompania = 0;
		numServiciosCompania = 0;
		numServiciosEnRango = 0;
	}
	
	//--------------------------------
	//METODOS
	//--------------------------------
	
	/**
	 * Nombre de la compania
	 * @return String nombre
	 */
	public String getNombre() 
	{
		return nombre;
	}
	/**
	 * Numero de taxis de la compania
	 * @return int numTaxisCompania
	 */
	public int getNumTaxisCompania()
	{
		return numTaxisCompania;
	}
	/**
	 * Numero de servicios de la compania
	 * @return int numServiciosCompania
	 */
	public int getNumServiciosCompania()
	{
		return numServiciosCompania;
	}
	/**
	 * Numero de servicios en rango de la compania
	 * @return int numServiciosEnRango
	 */
	public int getNumServiciosEnRango()
	{
		return numServiciosEnRango;
	}
	/**
	 * Lista de taxis compania
	 * @return ArrayListT taxisCompania
	 */
	public ArrayListT<Taxi> getTaxisCompania() 
	{
		return taxisCompania;
	}
	/**
	 * Añade a la lista de taxis compania
	 */
	public void addTaxisCompania(String pIdTaxi)
	{
		if(existsTaxi(pIdTaxi) == null)
		{
		    taxisCompania.add(new Taxi(pIdTaxi, this.getNombre()));
			numTaxisCompania++;
		}
	}
	/**
	 * Busca binariamente un taxi
	 * @param pTaxiId id del taxi a buscar
	 * @return Taxi taxi a buscar
	 */
	public Taxi binarySearchTaxi(String pTaxiId)
	{
		ordenarTaxisCompania();
		Taxi tx = null;
		boolean existe = false;

		int inicio = 0;
		int fin = taxisCompania.size() - 1;

		while( inicio <= fin && ! existe)
		{
			int medio = (inicio+fin)/2;
			int res = taxisCompania.get(medio).getTaxiId().compareTo(pTaxiId);
			if(res == 0)
			{
				existe = true;
				tx = taxisCompania.get(medio);
			}
			else if(res > 0)

			{
				fin = medio-1;
			}
			else if(res < 0)
			{
				inicio = medio+1;
			}
		}
		return tx;
	}
	/**
	 * Ordenar taxis de una compania
	 */
	public void ordenarTaxisCompania()
	{
		OrdenatorP<Taxi> ordenator = new OrdenatorP<Taxi>();
		ComparatorIdTaxi com = new ComparatorIdTaxi();

		ordenator.ordenar(Ordenamientos.QUICKSORT, true, com, taxisCompania);
	}
	/**
	 * Lista de servicios compania
	 * @return ArrayListT serviciosCompania
	 */
	public ArrayListT<Servicio> getServiciosCompania()
	{
		return serviciosCompania;
	}
	/**
	 * Añade a la lista de serviciosCompania un servicio
	 */
	public void addServiciosCompania(Servicio element)
	{
		serviciosCompania.add(element);
		numServiciosCompania++;
	}
	/**
	 * Verifica la existencia de un un taxi
	 * @param pId id del taxi a buscar
	 * @return Taxi taxi a buscar
	 */
	public Taxi existsTaxi(String pId)
	{
		boolean existe = false;
		Taxi miTaxi = null;
		if(!taxisCompania.isEmpty())
		{
			for(int j=0; j< taxisCompania.size() && !existe; j++)
			{
				if(taxisCompania.get(j).getTaxiId().contains(pId))
				{
					miTaxi = taxisCompania.get(j);
					existe = true;
				}
			}
		}
		return miTaxi;
	}
	/**
	 * Asignar numero de servicios en rango
	 * @param r RangoFechaHora
	 */
	public void asignarNumServiciosEnRango(RangoFechaHora r)
	{
		try
		{

			for(int i=0; i < serviciosCompania.size(); i++)
			{
				if(serviciosCompania.get(i).estaEnElRango(r))
				{
					numServiciosEnRango++;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * @param o Compania compania con la que se comparara
	 * @return int resultado de comparar una compania
	 */
	@Override
	public int compareTo(Compania o) 
	{
		int compare = nombre.compareTo(o.getNombre());
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
	/**
	 * @return nombre de la compania
	 */
	public String toString()
	{
		return nombre;
	}




}