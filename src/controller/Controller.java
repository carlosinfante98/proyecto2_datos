package controller;

import API.ITaxiTripsManager;
import model.data_structures.ArrayListT;
import model.data_structures.BinaryHeap;
import model.logic.TaxiTripsManager;
import model.vo.Servicio;
import model.vo.Taxi;

public class Controller 
{
	/**
	 * modela el manejador de la clase l�gica
	 */
	private static ITaxiTripsManager manager =new TaxiTripsManager();

	//Carga El sistema
	public static boolean cargarSistema(String direccionJson)
	{
		return manager.cargarSistema(direccionJson);
	}
	//1A
	public static ArrayListT<Taxi> R1A(int zonaInicio, String compania)
	{
		return manager.A1TaxiConMasServiciosEnZonaParaCompania(zonaInicio, compania);
	}

	//2A
	public static ArrayListT<Servicio> R2A(int duracion)
	{
		return manager.A2ServiciosPorDuracion(duracion);
	}

	//1B
	public static ArrayListT<Servicio> R1B(double distanciaMinima, double distanciaMaxima)
	{
		return manager.B1ServiciosPorDistancia(distanciaMinima, distanciaMaxima);
	}

	//2B
	public static ArrayListT<Servicio> R2B(int zonaInicio, int zonaFinal, String fechaI, String fechaF, String horaI, String horaF)
	{
		return manager.B2ServiciosPorZonaRecogidaYLlegada(zonaInicio, zonaFinal, fechaI, fechaF, horaI, horaF);
	}	
	//1C
	public static BinaryHeap<Taxi> R1C()
	{
		return manager.R1C_OrdenarTaxisPorPuntos();
	}	
	//2C
	public static ArrayListT<Servicio> R2C_LocalizacionesGeograficas(String taxiIDReq2C, double millasReq2C)
	{
		return manager.R2C_LocalizacionesGeograficas(taxiIDReq2C, millasReq2C);
	}
	//3C
	public static ArrayListT<Servicio> R3C(String fecha, String hora) 
	{
		return manager.R3C_ServiciosEn15Minutos(fecha, hora);
	}	
}
