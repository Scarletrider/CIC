public class LocationsManager 
{

	public static LocationsManager instance = new LocationsManager();
	
	private VLArray<Location> larray;  
	private TrieTree<Location> locations;  
	private TrieTree<Country> countries;
	private TrieTree<State> states;
	private int locNumber;  
	private boolean isInit;
	
	private LocationsManager() 
	{
		isInit = false;
	}
	

	public void init()
	{
		larray = new VLArray<>();
		locations = new TrieTree<Location>();
		countries = new TrieTree<Country>();
		states = new TrieTree<State>();
		locNumber = 0;
		isInit = true;
	}
	
	public boolean isInit() 
	{
		return isInit;
	}

	public Location decodeLocation(String str)
	{
		if(!str.startsWith("LOCATION"))
			return null;
		String [] strs = str.split(":");
		if(strs.length != 6)
			return null;
		
		Location location = new Location();
		location.setName(strs[1].split("=")[1]);
		location.setState(states.getNode(strs[2].split("=")[1]));
		location.setPosition(new Position(strs[4].split("=")[1]));
		location.setDescription(strs[5].split("=")[1]);
		
		location.setId(locNumber++);
		larray.add(location);
		
		return location;
	}

	public State decodeState(String str)
	{
		if(!str.startsWith("STATE"))
			return null;
		String [] strs = str.split(":");
		if(strs.length != 8)
			return null;
		
		State state = new State();
		state.setName(strs[1].split("=")[1]);
		state.setCountry(countries.getNode(strs[2].split("=")[1]));
		state.setShortname(strs[3].split("=")[1]);
		state.setArea(strs[4].split("=")[1]);
		state.setAreaunit(strs[5].split("=")[1]);
		state.setPopulation(strs[6].split("=")[1]);
		state.setPopref(strs[7].split("=")[1]);
		return state;
	}
	
	public Country decodeCountry(String str)
	{
		if(!str.startsWith("COUNTRY"))
			return null;
		String [] strs = str.split(":");
		if(strs.length != 7)
			return null;
		Country country = new Country();
		country.setName(strs[1].split("=")[1]);
		country.setShortname(strs[2].split("=")[1]);
		country.setLanguage(strs[3].split("=")[1]);
		country.setArea(strs[4].split("=")[1]);
		country.setPopulation(strs[5].split("=")[1]);
		country.setPopref(strs[6].split("=")[1]);
		return country;
	}
	
	public void addLocation(String input)
	{
		if(input.startsWith("COUNTRY"))
		{
			Country country = decodeCountry(input);
			if(country != null)
				countries.addNode(country.getName(), country);
		}
		else if(input.startsWith("STATE"))
		{
			State state = decodeState(input);
			if(state != null)
				states.addNode(state.getShortname(), state);
		}
		else if(input.startsWith("LOCATION"))
		{
			Location location = decodeLocation(input);
			if(location != null)
				locations.addNode(location.getName(), location);
		}
		else
		{
			System.out.println("Waring: Can not decode recode: " + input );
		}
	}
	
	public VLArray<Location> getLocations(String key)
	{
		return locations.getNodes(key);
	}
	
	public Location getLocation(String key)
	{
		return locations.getNode(key);
	}
	
	public int getLocNumber()
	{
		return locNumber;
	}
	
	public Location getLocaiton(int id)
	{
		return larray.get(id);
	}

}
