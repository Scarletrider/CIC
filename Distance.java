public class Distance 
{
	private String start;
	private String end;
	private String transportation;
	private double distance;
	private Time time;
	private Time peak;
	private int impairment;
	
	String src;
	
	public void setSrc(String src) 
	{
		this.src = src;
	}
	
	public String getSrc() 
	{
		return src;
	}
	
	public double getDistance()
	{
		return distance;
	}

	public String getEnd() 
	{
		return end;
	}

	public int getImpairment() 
	{
		return impairment;
	}

	public Time getPeak() 
	{
		return peak;
	}

	public String getStart()
	{
		return start;
	}

	public Time getTime() 
	{
		return time;
	}

	public String getTransportation() 
	{
		return transportation;
	}
	
	public void setDistance(double distance) 
	{
		this.distance = distance;
	}

	public void setEnd(String end) 
	{
		this.end = end;
	}

	public void setImpairment(int impairment)
	{
		this.impairment = impairment;
	}

	public void setPeak(Time peak) 
	{
		this.peak = peak;
	}

	public void setStart(String start) 
	{
		this.start = start;
	}

	public void setTime(Time time) 
	{
		this.time = time;
	}

	public void setTransportation(String transportation) 
	{
		this.transportation = transportation;
	}
	
	public String toString() 
	{
		StringBuilder sb = new StringBuilder("From: " + start + " To: " + end + "\n Tran:" +transportation + " Time: "+ time);
		if(peak != null)
		{
			sb.append(" Peak: " + peak);
		}
		if(impairment != 0)
		{
			sb.append(" Impairment: " + impairment);
		}
		return sb.toString();
	}	
}
