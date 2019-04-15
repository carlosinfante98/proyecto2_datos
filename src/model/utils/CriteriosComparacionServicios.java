package model.utils;


import model.vo.Servicio;

public enum CriteriosComparacionServicios
{
	DROP_OFF_AREA("Drop off area", new ComparatorDropoffArea()),
	PICK_UP_AREA("Pick up area", new ComparatorPickupArea()),
	SERVICIOS_DISTANCIA("Servicios Distancia", new ComparatorServiciosDistancia()),
	FECHA_SERVICIOS("Fecha Servicios", new ComparatorServicioFechaInicial());
	
	/**
	 * Nombre del criterios
	 */
	private String nombre;
	
	/**
	 * Comparador de servicios asociado al criterio 
	 */
	private ComparatorT<Servicio> comparador;
	
	/**
	 * Crea un criterio de comparaciï¿½n a partir de sus datos bï¿½sicos
	 * @param nombre el nombre del criterio. nombre != null && nombre != ""
	 * @param comparador comparador asociado al criterio. comaprador != null
	 */
	private CriteriosComparacionServicios(String nombre, ComparatorT<Servicio> comparador)
	{
		this.nombre = nombre;
		this.comparador = comparador;
	}
	
	/**
	 * Devuelve el comparador asociado al criterio
	 * @return comparador
	 */
	public ComparatorT<Servicio> darComparadorServicios()
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
