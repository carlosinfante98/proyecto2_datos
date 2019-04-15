package model.logic;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import API.ITaxiTripsManager;
import model.data_structures.*;
import model.utils.ComparatorHarvesianDistance;
import model.utils.OrdenatorP;
import model.utils.OrdenatorP.Ordenamientos;
import model.vo.Compania;
import model.vo.FechaServicios;
import model.vo.Servicio;
import model.vo.Taxi;
import model.vo.ZonaServicioXY;

public class TaxiTripsManager implements ITaxiTripsManager
{
	public static final String DIRECCION_SMALL_JSON = "./data/taxi-trips-wrvz-psew-subset-small.json";
	public static final String DIRECCION_MEDIUM_JSON = "./data/taxi-trips-wrvz-psew-subset-medium.json";
	public static final String DIRECCION_LARGE_JSON = "./data/taxi-trips-wrvz-psew-subset-large.json";

	//--------------------------------
	//CONSTANTES
	//--------------------------------

	/**
	 * Constante COMPANY
	 */
	public static final String COMPANY = "company";
	/**
	 * Constante TRIP_ID
	 */
	public static final String TRIP_ID = "trip_id";
	/**
	 * Constante TAXI_ID
	 */
	public static final String TAXI_ID ="taxi_id";
	/**
	 * Constante TRIP_SECONDS
	 */
	public static final String TRIP_SECONDS ="trip_seconds";
	/**
	 * Constante TRIP_MILES
	 */
	public static final String TRIP_MILES ="trip_miles";
	/**
	 * Constante TRIP_TOTAL
	 */
	public static final String TRIP_TOTAL ="trip_total";
	/**
	 * Constante DROPOFF_AREA
	 */
	public static final String DOFF_AREA ="dropoff_community_area";
	/**
	 * Constante PICK_UP_AREA
	 */
	public static final String PUP_AREA = "pickup_community_area";
	/**
	 * Constante TRIP_START_TIMESTAMP
	 */
	public static final String START_TIME = "trip_start_timestamp";
	/**
	 * Constante TRIP_END_TIMESTAMP
	 */
	public static final String END_TIME = "trip_end_timestamp";
	/**
	 * Constante PICK_UP_LATITUD
	 */
	public static final String PICK_UP_LATITUD = "pickup_centroid_latitude";
	/**
	 * Constante PICK_UP_LONGITUD
	 */
	public static final String PICK_UP_LONGITUD = "pickup_centroid_longitude";

	//--------------------------------
	//ATRIBUTOS
	//--------------------------------
	/**
	 * Arbol binario balanceado con los servicios agrupados por su distancia.
	 */
	private RedBlackBST<Double, ArrayListT<Servicio>> treeDistancia;
	private RedBlackBST<String, ArrayListT<Taxi>> treeCompania;
	private SeparateChainingHashST<Double, RedBlackBST<String,ArrayListT<Servicio>>> tablaHarvesian;
	private SeparateChainingHashST<ZonaServicioXY, RedBlackBST<String, ArrayListT<Servicio>>> tablaZonaXYServicios;
	private SeparateChainingHashST<String,ArrayListT<Servicio> >tablaRangoDuracionServicios;	
	private BinaryHeap<Taxi> heapTaxis;
	private RedBlackBST<String, ArrayListT<Servicio>> treeServiciosRangoMinutos;

	private ArrayListT<Servicio> servicios ;
	private double sumaLongServicios;
	private double sumaLatServicios;
	private int numServiciosTotal;


	public TaxiTripsManager()
	{
		treeDistancia = new RedBlackBST<Double, ArrayListT<Servicio>>();
		tablaZonaXYServicios = new SeparateChainingHashST<ZonaServicioXY, RedBlackBST<String, ArrayListT<Servicio>>>();
		tablaRangoDuracionServicios = new SeparateChainingHashST<String, ArrayListT<Servicio>>();
		treeCompania = new RedBlackBST<String, ArrayListT<Taxi>>();
		tablaHarvesian = new SeparateChainingHashST<Double,RedBlackBST<String,ArrayListT<Servicio>>>();
		heapTaxis = new BinaryHeap<>();
		treeServiciosRangoMinutos = new RedBlackBST<String, ArrayListT<Servicio>>();
		servicios = new ArrayListT<Servicio>(6000);
		sumaLongServicios = 0.0;
		sumaLatServicios = 0.0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean cargarSistema(String direccionJson)
	{
		JsonParser parser = new JsonParser();
		try 
		{
			/* Cargar todos los JsonObject (servicio) definidos en un JsonArray en el archivo */
			JsonArray arr= (JsonArray) parser.parse(new FileReader(direccionJson));
			/* Tratar cada JsonObject (Servicio taxi) del JsonArray */
			for (int i = 0; arr != null && i < arr.size(); i++)
			{
				JsonObject obj= (JsonObject) arr.get(i);

				String company_name = obj.get(COMPANY) != null ? obj.get(COMPANY).getAsString() : "Independent Owner";

				String taxi_id = obj.get(TAXI_ID) != null ? obj.get(TAXI_ID).getAsString() : "NaN";

				String trip_id = obj.get(TRIP_ID) != null ? obj.get(TRIP_ID).getAsString(): "NaN";			

				int drop_off_area = obj.get(DOFF_AREA) != null ? obj.get(DOFF_AREA).getAsInt() : -1;			

				int trip_sec = obj.get(TRIP_SECONDS) != null ? obj.get(TRIP_SECONDS).getAsInt(): -1;

				double trip_miles = obj.get(TRIP_MILES) != null ? obj.get(TRIP_MILES).getAsDouble() : -1.0;

				double trip_total = obj.get(TRIP_TOTAL) != null ? obj.get(TRIP_TOTAL).getAsDouble() : 0.0;

				double pickup_c_latitude = obj.get(PICK_UP_LATITUD) != null ? obj.get(PICK_UP_LATITUD).getAsDouble():0.0001700017;

				double pickup_c_longitude = obj.get(PICK_UP_LONGITUD) != null ? obj.get(PICK_UP_LONGITUD).getAsDouble():0.0001700017;

				int pick_up_area = obj.get(PUP_AREA) != null ? obj.get(PUP_AREA).getAsInt() : -1;

				String fi = obj.get(START_TIME) != null ? obj.get(START_TIME).getAsString() : "2025-09-24T00:00:00.000";

				String ff = obj.get(END_TIME) != null ? obj.get(END_TIME).getAsString() : "2025-09-24T00:00:00.000";



				Servicio s = new Servicio(trip_id, taxi_id, trip_sec, trip_miles, trip_total, drop_off_area, pick_up_area, fi, ff,pickup_c_latitude,pickup_c_longitude);
				Compania c = new Compania(company_name);
				Taxi t = new  Taxi(taxi_id, company_name);
				ZonaServicioXY zxy = new ZonaServicioXY(pick_up_area+"-"+drop_off_area);
				FechaServicios f = new FechaServicios(fi, ff);
				//Req 2c
				if(pickup_c_latitude != 0.0001700017 && pickup_c_longitude != 0.0001700017)
				{
					sumaLatServicios += pickup_c_latitude;
					sumaLongServicios += pickup_c_longitude;
					numServiciosTotal++;
					servicios.add(s);
				}




				//REQ1A
				ArrayListT<Taxi> listaTaxisCompania = treeCompania.get(c.getNombre());
				if(listaTaxisCompania == null)
				{
					listaTaxisCompania = new ArrayListT<Taxi>();
					t = listaTaxisCompania.add(t);
					treeCompania.put(company_name, listaTaxisCompania);
				}
				else
				{
					t = existsTaxi(c, t);
					if(t == null)
					{
						t = listaTaxisCompania.add(new Taxi(taxi_id, company_name));
					}
				}
				ArrayListT<Servicio> listaServiciosZona = t.getHashTable().get(pick_up_area);
				if(listaServiciosZona == null)
				{
					listaServiciosZona = new ArrayListT<Servicio>();
					listaServiciosZona.add(s);						
					t.getHashTable().put(pick_up_area, listaServiciosZona);
				}
				else
				{
					listaServiciosZona.add(s);	
				}

				if(trip_total > 0.0)
					t.setPlataGanada(trip_total);
				if(trip_miles > 0.0)
					t.setDistanciaRecorrida(trip_miles);

				t.setTotalServicios();

				//REQ2A
				int duracion = s.getTripSeconds();
				if(duracion >= 1)
				{
					boolean encontro = false;
					int rangoInferior = 1;
					int rangoSuperior = 60;
					while(!encontro)
					{
						int aumento = 60;
						if(duracion >= rangoInferior && duracion <= rangoSuperior)
						{
							String rango = rangoInferior + "-" + rangoSuperior;
							ArrayListT<Servicio> listaDuracion = tablaRangoDuracionServicios.get(rango);
							if(listaDuracion == null)
							{
								listaDuracion = new ArrayListT<Servicio>();
								listaDuracion.add(s);
								tablaRangoDuracionServicios.put(rango, listaDuracion);
							}
							else
							{
								listaDuracion.add(s);								
							}
							encontro = true;
						}
						else
						{
							rangoInferior += aumento;
							rangoSuperior += aumento;
						}
					}
				}

				//REQ1B

				ArrayListT<Servicio> listaDistancia = treeDistancia.get(trip_miles);
				if(listaDistancia == null)
				{
					listaDistancia = new ArrayListT<Servicio>();
					treeDistancia.put(trip_miles, listaDistancia);
				}
				listaDistancia.add(s);

				//REQ2B

				RedBlackBST<String, ArrayListT<Servicio>> arbolZonasXY = tablaZonaXYServicios.get(zxy);
				if(arbolZonasXY == null)
				{
					arbolZonasXY = new RedBlackBST<String, ArrayListT<Servicio>>();
					tablaZonaXYServicios.put(zxy, arbolZonasXY);
					arbolZonasXY = tablaZonaXYServicios.get(zxy);
				}
				ArrayListT<Servicio> listaServicios = arbolZonasXY.get(f.getFechaDeInicio());
				if(listaServicios == null)
				{
					listaServicios = new ArrayListT<Servicio>();
					arbolZonasXY.put(f.getFechaDeInicio(), listaServicios);
					listaServicios = arbolZonasXY.get(f.getFechaDeInicio());
				}
				listaServicios.add(s);
				//REQ3C
				try
				{
					int minutos = darRangoAproximado(fi);

					SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

					Date date = formateador.parse(fi);
					date.setMinutes(minutos);
					String rangoFecha = formateador.format(date);
					ArrayListT<Servicio>  listaServiciosEnRango = treeServiciosRangoMinutos.get(rangoFecha);
					if( listaServiciosEnRango == null)
					{
						listaServiciosEnRango = new ArrayListT<Servicio>();
						treeServiciosRangoMinutos.put(rangoFecha, listaServiciosEnRango);
						listaServiciosEnRango = treeServiciosRangoMinutos.get(rangoFecha);
					}
					if(s.getPickupArea() != s.getDropoffArea())
						listaServiciosEnRango.add(s);
				}
				catch( Exception e)
				{
					System.out.println("Error al parsear la fecha.");
				}
			}

			System.out.println("Numero servicios es "+ numServiciosTotal);

		}
		catch (JsonIOException e1 ) 
		{
			e1.printStackTrace();
		}
		catch (JsonSyntaxException e2) 
		{
			e2.printStackTrace();
		}
		catch (FileNotFoundException e3) 
		{
			e3.printStackTrace();
		} 

		return false;
	}

	public int darRangoAproximado(String pFecha)
	{
		try
		{
			SimpleDateFormat formateo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			Date date =  formateo.parse(pFecha);
			@SuppressWarnings("deprecation")
			int minutes = date.getMinutes()/15;

			if(minutes == 1)
			{
				minutes = 15;
			}
			else if(minutes == 2)
			{
				minutes = 30;
			}
			else if(minutes == 3)
			{
				minutes = 45;
			}
			else if(minutes == 0)
			{
				minutes = 0;
			}

			return minutes;
		}
		catch(Exception e)
		{
			System.out.println("Digitó mal la fecha. No concuerda con el formato recomendado");
			return -1;
		}
	}
	public Taxi existsTaxi(Compania c, Taxi tx)
	{
		Taxi t = null;
		boolean e = false;		
		for (int i = 0; i < treeCompania.get(c.getNombre()).size() && !e; i++) 
		{
			if(treeCompania.get(c.getNombre()).get(i).getTaxiId().compareTo(tx.getTaxiId()) == 0)
			{
				e = true;
				t = treeCompania.get(c.getNombre()).get(i);	
			}
		}
		return t;
	}


	@Override
	public ArrayListT<Taxi> A1TaxiConMasServiciosEnZonaParaCompania(int zonaInicio, String compania)
	{
		ArrayListT<Taxi> lista = new ArrayListT<Taxi>();
		ArrayListT<Taxi> taxis = treeCompania.get(compania);

		if(taxis != null)
		{
			for (int i = 0; i < taxis.size(); i++) 
			{
				if(taxis.get(i).getHashTable().get(zonaInicio) != null)
				{
					if(!taxis.get(i).getHashTable().get(zonaInicio).isEmpty())
						taxis.get(i).setNumServiciosZona(zonaInicio);
						lista.add(taxis.get(i));				
				}
			}		
		}

		return lista;
	}


	@Override
	public ArrayListT<Servicio> A2ServiciosPorDuracion(int duracion)
	{
		ArrayListT<Servicio> lista = new ArrayListT<Servicio>();
		if(duracion >= 1)
		{
			boolean encontro = false;
			int rangoInferior = 1;
			int rangoSuperior = 60;
			while(!encontro)
			{
				int aumento = 60;
				if(duracion >= rangoInferior && duracion <= rangoSuperior)
				{
					String rango = rangoInferior + "-" + rangoSuperior;
					ArrayListT<Servicio> listaDuracion = tablaRangoDuracionServicios.get(rango);
					if(listaDuracion != null)
					{
						lista = listaDuracion;
					}
					encontro = true;
				}
				else
				{
					rangoInferior += aumento;
					rangoSuperior += aumento;
				}
			}
		}
		return lista;
	}


	@Override
	public ArrayListT<Servicio> B1ServiciosPorDistancia(double distanciaMinima, double distanciaMaxima)
	{
		IArrayListT<ArrayListT<Servicio>> lista = treeDistancia.values(distanciaMinima, distanciaMaxima);
		ArrayListT<Servicio> listica = new ArrayListT<>();
		for (int i = 0; i < lista.size(); i++)
		{
			for(int j=0; j < lista.get(i).size() ; j++)
			{
				listica.add(lista.get(i).get(j));
			}
		}
		return listica;
	}

	public double[] getDistanceOfReference()
	{
		double[] distanceRef = new double[2];
		distanceRef[0] = (sumaLatServicios/numServiciosTotal);
		distanceRef[1] = (sumaLongServicios/numServiciosTotal);
		return distanceRef;
	}

	public double getDistanceHarv (double lat1, double lon1)
	{
		DecimalFormat format = new DecimalFormat("#.0");
		final int R = 6371*1000; // Radious of the earth in meters
		Double latDistance = Math.toRadians(getDistanceOfReference()[0]-lat1);
		Double lonDistance = Math.toRadians(getDistanceOfReference()[1]-lon1);
		Double a = Math.sin(latDistance/2) * Math.sin(latDistance/2) + Math.cos(Math.toRadians(lat1))
		* Math.cos(Math.toRadians(getDistanceOfReference()[0])) * Math.sin(lonDistance/2) * Math.sin(lonDistance/2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distance = R * c * 0.000621371;
		Double finalDistance =  (Double.parseDouble(format.format(distance).replace(",", ".")));
		return finalDistance;
	}

	public ArrayListT<Servicio> setHarvesianDistancesOfServices()
	{
		for (int i = 0; i < servicios.size(); i++) 
		{
			servicios.get(i).setHarvesianDistance(getDistanceHarv(servicios.get(i).getPickup_centroid_latitude(), servicios.get(i).getPickup_centroid_longitude()));
		}
		OrdenatorP<Servicio> ordenador = new OrdenatorP<Servicio>();
		ComparatorHarvesianDistance comparator = new ComparatorHarvesianDistance();
		ordenador.ordenar(Ordenamientos.MERGE, true, comparator, servicios);

		return servicios;
	}


	public void fillHarvesianHashTable()
	{
		ArrayListT<Servicio> servicios = setHarvesianDistancesOfServices();
		for (int i = 0; i < servicios.size(); i++)
		{
			RedBlackBST<String , ArrayListT<Servicio>> tree = tablaHarvesian.get(servicios.get(i).getHarvesianDistance());
			if(tree != null)
			{
				ArrayListT<Servicio> listk = tree.get(servicios.get(i).getTaxiId());
				if(listk == null)
				{
					listk = new ArrayListT<Servicio>();
					tree.put(servicios.get(i).getTaxiId(), listk);
					listk = tree.get(servicios.get(i).getTaxiId());
				}
				listk.add(servicios.get(i));
			}
			else
			{
				tree = new RedBlackBST<String,ArrayListT<Servicio>>();
				ArrayListT<Servicio> listk = new ArrayListT<Servicio>();
				listk.add(servicios.get(i));
				tree.put(servicios.get(i).getTaxiId(), listk);
				listk = tree.get(servicios.get(i).getTaxiId());
				tablaHarvesian.put(servicios.get(i).getHarvesianDistance(), tree);
			}
		}

	}

	@Override
	public ArrayListT<Servicio> B2ServiciosPorZonaRecogidaYLlegada(int zonaInicio, int zonaFinal, String fechaI, String fechaF, String horaI, String horaF)
	{
		ArrayListT<Servicio> listica = new ArrayListT<Servicio>();
		ZonaServicioXY zonaServicios = new ZonaServicioXY(zonaInicio+"-"+zonaFinal);
		RedBlackBST<String,ArrayListT<Servicio> > tree = tablaZonaXYServicios.get(zonaServicios);
		if(tree != null)
		{
			IArrayListT<ArrayListT<Servicio>> lista = tree.values(fechaI+"T"+horaI, fechaF+"T"+horaF);
			for (int i = 0; i < lista.size(); i++) 
			{
				for (int j = 0; j < lista.get(i).size(); j++)
				{
					listica.add(lista.get(i).get(j));
				}
			}
		}
		return listica;
	}


	@Override
	public BinaryHeap<Taxi> R1C_OrdenarTaxisPorPuntos()
	{
		IArrayListT<ArrayListT<Taxi>> lista = treeCompania.values();
		for (int i = 0; i < lista.size() ; i++) 
		{
			for(int j = 0; j < lista.get(i).size(); j++)
			{
				Taxi t = lista.get(i).get(j);
				double points = t.puntos();
				if(points > 0)
					heapTaxis.add(t);
			}
		}
		heapTaxis.sort(true);

		return heapTaxis;
	}

	@Override
	public ArrayListT<Servicio> R2C_LocalizacionesGeograficas(String taxiIDReq2C, double millasReq2C)
	{	
		fillHarvesianHashTable();
		try
		{
			return tablaHarvesian.get(millasReq2C).get(taxiIDReq2C);
		}
		catch(Exception e)
		{
			System.err.println("No existen las millas brindadas.");
			System.out.println();
			return new ArrayListT<Servicio>();
		}
	}


	@SuppressWarnings("deprecation")
	public ArrayListT<Servicio> R3C_ServiciosEn15Minutos(String fecha, String hora)
	{
		try
		{
			SimpleDateFormat formateo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			Date date =  formateo.parse(fecha+"T"+hora);
			int minutes = date.getMinutes()/15;

			if(minutes == 1)
			{
				minutes = 15;
			}
			else if(minutes == 2)
			{
				minutes = 30;
			}
			else if(minutes == 3)
			{
				minutes = 45;
			}
			else if(minutes == 0)
			{
				minutes = 0;
			}

			date.setMinutes(minutes);
			String fech = formateo.format(date);
			return treeServiciosRangoMinutos.get(fech);
		}
		catch(Exception e)
		{
			System.out.println("Digitó mal la fecha. No concuerda con el formato recomendado");
			return null;
		}
	}




}
