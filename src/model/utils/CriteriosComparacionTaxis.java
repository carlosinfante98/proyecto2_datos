package model.utils;


import model.vo.Taxi;

public enum CriteriosComparacionTaxis 
{
	ID_TAXI("Id taxi", new ComparatorIdTaxi());
	
	/**
	 * Nombre del criterios
	 */
	private String nombre;
	
	/**
	 * Comparador de servicios asociado al criterio 
	 */
	private ComparatorT<Taxi> comparador;
	
	/**
	 * Crea un criterio de comparaciï¿½n a partir de sus datos bï¿½sicos
	 * @param nombre el nombre del criterio. nombre != null && nombre != ""
	 * @param comparador comparador asociado al criterio. comaprador != null
	 */
	private CriteriosComparacionTaxis(String nombre, ComparatorT<Taxi> comparador)
	{
		this.nombre = nombre;
		this.comparador = comparador;
	}
	
	
	/**
	 * Devuelve el comparador asociado al criterio
	 * @return comparador
	 */
	public ComparatorT<Taxi> darComparador()
	{
		return comparador;
	}
	
	/**
	 * Devuelve la rerpresentacion en String del criterio
	 * @return <nombre>
	 */
	public String toString() 
	{
		return nombre;
	}
}
