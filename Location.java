public class Location 
{
	int id;
	String name;
	State state;
	Position position;
	String description;

	public boolean equals(Object obj)
	{
		if(obj instanceof Location)
		{
			Location location = (Location) obj;
			return name.equals(location.name) && state.equals(location.state);
		}
		return super.equals(obj);
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setState(State state) 
	{
		this.state = state;
	}
	
	
	public void setPosition(Position position) 
	{
		this.position = position;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public String getName() 
	{
		return name;
	}
	public Position getPosition() 
	{
		return position;
	}
	public State getState() 
	{
		return state;
	}
	
	@Override
	public String toString() 
	{
		return name 
				+ ":STATE=" + state.getShortname() 
				+ ":COUNTRY="+ state.getCountry().getShortname() 
				+":COORDS=" + position 
				+":DESCRIPTION="+ description;
	}
}
