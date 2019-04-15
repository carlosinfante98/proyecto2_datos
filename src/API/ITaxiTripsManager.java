package API;

import model.data_structures.ArrayListT;
import model.data_structures.BinaryHeap;
import model.vo.Servicio;
import model.vo.Taxi;

/**
 * API para la clase de logica principal  
 */
public interface ITaxiTripsManager 
{

	//1C
	/**
	 * Dada la direccion del json que se desea cargar, se generan vo's, estructuras y datos necesarias
	 * @param direccionJson, ubicacion del json a cargar
	 * @return true si se lo logro cargar, false de lo contrario
	 */
	public boolean cargarSistema(String direccionJson);

	/**
	 * Dada una compania y una zona de inicio retornar el taxi con mas servicios (puede haber mas de uno) iniciando en la zona dada y 
	 * sus servicios ordenados en orden cronologico.
	 * @param zonaInicio: id de la zona de inicio
	 * @param compania: nombre de la compaNia
	 * @return Taxi(s) que mas servicios realizo (puede haber mas de uno) y sus respectivos servicios.
	 */
	public ArrayListT<Taxi> A1TaxiConMasServiciosEnZonaParaCompania(int zonaInicio, String compania);

	/**
	 * Dada una duracion en segundos retornar los servicios que esten en el mismo rango de duracion (mismo minuto) que la dada.
	 * Por cada servicio se debe mostrar el id del taxi, id del servicio y su duracion en segundos.
	 * @param duracion: duracion de consulta en segundos
	 * @return Lista con los servicios con duracion en el mismo minuto (rango) de la duracion dada
	 */
	public ArrayListT<Servicio> A2ServiciosPorDuracion(int duracion);

	/**
	 * Dada una distancia minima y maxima retornar los servicios que esten dentro del rango de estas distancias
	 * Por cada servicio se debe mostrar el id del taxi, id del servicio y su distancia recorrida.
	 * @param distanciaMinima: limite inferior de distancia. distanciaMinima <= distanciaMaxima
	 * @param distanciaMaxima: limite superior de distancia. distanciaMinima <= distanciaMaxima
	 * @return Lista con los servicios con distancia en el rango especificado
	 */
	public ArrayListT<Servicio> B1ServiciosPorDistancia(double distanciaMinima, double distanciaMaxima);

	/**
	 * Dada una zona de recogida y de terminacion "X-Y" y un rango de fechas y horas
	 * retornar los servicios que inician en la zona X y terminan en la zona Y y que esten en el rango de tiempo establecido.
	 * Los servicios deben estar ordenados cronologicamente.
	 * Por cada servicio se debe mostrar el id del servicio, zona de recogida, zona de terminaci�n y la hora de recogida.
	 * @param zona de recogida: id de zona de recogida
	 * @param zona de terminacion: id de zona de terminacion
	 * @param fechaI: fecha inicial
	 * @param fechaF: fecha final
	 * @param horaI: hora inicial
	 * @param horaF: hora final
	 * @return Lista con los servicios
	 */
	public ArrayListT<Servicio> B2ServiciosPorZonaRecogidaYLlegada(int zonaInicio, int zonaFinal, String fechaI, String fechaF, String horaI, String horaF);

	/**
	 * Metodo que permite ordenar Ascendentemente (de menor a Mayor) todos los taxis registrados en el sistema usando el algoritmo HeapSort (basado en un Heap orientado a Mayor), utilizando un sistema de puntos (prioridad)
	 * Despues del ordenamiento se debe mostrar en orden los 5 taxis con menor puntaje (prioridad) y los 5 taxis con mayor puntaje (prioridad).
	 * Por cada taxi se debe mostrar su Id y su puntaje (prioridad)
	 * @return
	 */
	public BinaryHeap<Taxi> R1C_OrdenarTaxisPorPuntos();

	/**
	 * Metodo que permite informar las localizaciones geograficas (latitud, longitud) de los servicios iniciados por un taxi (a partir de su Id) que estan dentro de la
	 * zona a X millas de una localizacion geografica (latitud, longitud) dada
	 * @param id del taxi
	 * @param numero X de millas de cubrimiento de la zona
	 * @param latitud de referencia
	 * @param longitud de referencia
	 * @return
	 */
	public ArrayListT<Servicio> R2C_LocalizacionesGeograficas(String taxiIDReq2C, double millasReq2C);

	/**
	 * Metodo que permite retornar todos los servicios registrados por hora de recogida en el rango de 15 minutos mas cercano, 
	 * que adicionalmente tengan zonas de recogida y de terminado diferentes
	 * Por cada servicio se debe mostrar su Id, fecha/hora recogida, zona recogida y zona terminacion 
	 * 
	 * @param fecha de recogida
	 * @param hora de recogida
	 * @return
	 */
	public ArrayListT<Servicio> R3C_ServiciosEn15Minutos(String fecha, String hora);  

}