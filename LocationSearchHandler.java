import java.util.Scanner;

public class LocationSearchHandler implements Handler 
{

	public void handle() 
	{
		if(!LocationsManager.instance.isInit())
		{
			System.out.println("Please load data first");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please input the prefix :");
		
		LocationsManager lm = LocationsManager.instance;
		VLArray<Location> locations = lm.getLocations(scanner.nextLine());
		for (int i = 0; i < locations.getLength(); i++) 
		{
			System.out.print("    ");
			System.out.println(locations.get(i).getName());
		}
		System.out.println();
	}

}
