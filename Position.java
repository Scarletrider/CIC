public class Position 
{
	public Position(String string) 
	{
		String [] positions = string.split(",");
		lon = Double.parseDouble(positions[0]);
		lat = Double.parseDouble(positions[1]);
	}
	double lon;
	double lat;
	
	public double getLat() 
	{
		return lat;
	}
	
	public double getLon() 
	{
		return lon;
	}
	
	public String toString() 
	{
		return lon+","+lat;
	}
}


