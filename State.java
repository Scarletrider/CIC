public class State 
{
	String name;
	Country country;
	String shortname;
	String area;
	String areaunit;
	String population;
	String popref;

	public boolean equals(Object obj) 
	{
		if(obj instanceof State)
		{
			State state = (State) obj;
			return name.equals(state.name) && country.equals(state.country);
		}
		return super.equals(obj);
	}

	public String getArea() 
	{
		return area;
	}
	
	public String getAreaunit() 
	{
		return areaunit;
	}

	public Country getCountry() 
	{
		return country;
	}

	public String getName() 
	{
		return name;
	}

	public String getPopref() 
	{
		return popref;
	}

	public String getPopulation() 
	{
		return population;
	}

	public String getShortname() 
	{
		return shortname;
	}
	
	public void setArea(String area) 
	{
		this.area = area;
	}

	public void setAreaunit(String areaunit) 
	{
		this.areaunit = areaunit;
	}

	public void setCountry(Country country) 
	{
		this.country = country;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public void setPopref(String popref) 
	{
		this.popref = popref;
	}

	public void setPopulation(String population) 
	{
		this.population = population;
	}
	
	public void setShortname(String shortname) 
	{
		this.shortname = shortname;
	}
}
