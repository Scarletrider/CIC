import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		Menu menu = new Menu();
		menu.addMenuItem("Travel Search", new TravelSearchHandler());;
		menu.addMenuItem("Location Search", new LocationSearchHandler());;
		menu.addMenuItem("Nearby Search", new NearbySearchHandler());;
		menu.addMenuItem("Update Data", new UpdateDataHandler());;
		menu.addMenuItem("Load Data", new LoadDataHandler());;
		menu.addMenuItem("Save Data", new SaveDataHandler());
		
		
		for(;;)
		{
			menu.printMenu();
			Scanner scanner = new Scanner(System.in);
			menu.handle(scanner.nextLine());
		}
		
	}
}
