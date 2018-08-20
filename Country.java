import java.io.ObjectInputStream.GetField;

public class Country {
	private String name;
	private String shortname;
	private String language;
	private String area;
	private String population;
	private String popref;
	
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Country)
		{
			Country country = (Country) obj;
			return name.equals(country.name);
		}
		return super.equals(obj);
	}
	
	
	
	public void setArea(String area) 
	{
		this.area = area;
	}
	
	public void setLanguage(String language)
	{
		this.language = language;
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
	
	
	public String getArea() 
	{
		return area;
	}
	
	public String getLanguage() 
	{
		return language;
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
}
