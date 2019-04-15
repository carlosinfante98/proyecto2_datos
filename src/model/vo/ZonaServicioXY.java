package model.vo;


public class ZonaServicioXY implements Comparable<ZonaServicioXY>
{
	private String idZonas;
		
	public ZonaServicioXY(String pZone)
	{
		idZonas = pZone;
		// TODO Auto-generated constructor stub
	}
	public String getIdZonas()
	{
		return idZonas;
	}
	public int hashCode()
	{
		return Integer.parseInt(idZonas.replace("-", "").trim());
	}
	@Override
	public int compareTo(ZonaServicioXY o)
	{
		int compare = idZonas.compareTo(o.getIdZonas());
		
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
