package model.vo;


/**
 * Representation of a Service object
 */
public class Servicio implements Comparable<Servicio>
{	
	private String trip_id;
	private String taxi_id;
	private int trip_seconds;
	private double trip_miles;
	private int dropoff_area;
	private int pickup_area;
	private double pickup_centroid_latitude;
	private double pickup_centroid_longitude;
	private String trip_start_timestamp;
	private String trip_end_timestamp;
	private RangoFechaHora rangoFecha;
	private RangoDistancia rangoDistancia;
	
	private double harvesianDistance;

	//Cash
	private double trip_total;

	public Servicio(String pTrip_id, String pTaxi_id, int trip_sec, double pTrip_miles, double pTrip_total, int pDoffA , int pPiUpA, String pSt , String pEn, double pPcLat, double pPcLong) 
	{
		trip_id = pTrip_id;
		taxi_id = pTaxi_id;
		trip_seconds = trip_sec;
		trip_miles = pTrip_miles;
		trip_total = pTrip_total;
		dropoff_area = pDoffA;
		pickup_area = pPiUpA;
		trip_start_timestamp = pSt;
		trip_end_timestamp = pEn;
		pickup_centroid_latitude = pPcLat;
		pickup_centroid_longitude = pPcLong;
		setHarvesianDistance(0.0);
		rangoFecha = null;
		rangoDistancia = null;
	}

	public int getDropoffArea()
	{
		return dropoff_area;
	}

	public int getPickupArea() 
	{
		return pickup_area;
	}

	public RangoFechaHora getRangoFecha() 
	{
		return rangoFecha;
	}

	public void setRangoFechaHora(RangoFechaHora rango)
	{
		rangoFecha = rango;
	}

	public RangoDistancia getRangoDistancia()
	{
		return rangoDistancia;
	}

	public void setRangoDistancia(RangoDistancia rango)
	{
		rangoDistancia = rango;
	}

	/**
	 * @return id - Trip_id
	 */
	public String getTripId()
	{
		return trip_id;
	}	

	public String getTripStartTime()
	{
		return trip_start_timestamp;
	}

	public String getTripEndTime()
	{
		return trip_end_timestamp;
	}

	public String getFechaHoraInicial()
	{
		return trip_start_timestamp;
	}

	public String getFechaHoraFinal()
	{
		return trip_end_timestamp;
	}

	/**
	 * @return id - Taxi_id
	 */
	public String getTaxiId()
	{
		return taxi_id;
	}	

	/**
	 * @return time - Time of the trip in seconds.
	 */
	public int getTripSeconds() 
	{
		return trip_seconds;
	}

	/**
	 * @return miles - Distance of the trip in miles.
	 */
	public double getTripMiles() 
	{
		return trip_miles;
	}

	/**
	 * @return total - Total cost of the trip
	 */
	public double getTripTotal() 
	{
		return trip_total;
	}
	public boolean estaEnElRango(RangoFechaHora r) throws Exception
	{
		try
		{
			if(r.getFechaHoraInicial().compareTo(trip_start_timestamp) <= 0 && r.getFechaHoraFinal().compareTo(trip_end_timestamp) >= 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			throw new Exception("Valores digitados son erróneos.");
		}
	}

	@Override
	public int compareTo(Servicio o) 
	{
		int compare = trip_id.compareTo(o.getTripId());
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
		return trip_id;
	}

	public double getPickup_centroid_latitude()
	{
		return pickup_centroid_latitude;
	}

	public double getPickup_centroid_longitude()
	{
		return pickup_centroid_longitude;
	}

	public double getHarvesianDistance() 
	{
		return harvesianDistance;
	}

	public void setHarvesianDistance(double harvesianDistance) 
	{
		this.harvesianDistance = harvesianDistance;
	}

}
