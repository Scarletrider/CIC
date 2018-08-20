import java.io.File;
import java.util.Scanner;

public class LoadDataHandler implements Handler
{
	public void handle()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please input the location data file:");
		File lfile = new File(scanner.nextLine());
		while(lfile == null || !lfile.exists() || lfile.isDirectory())
		{
			if(!lfile.exists())
			{
				System.out.println("not exists");
			}
			System.out.print("Please input the location data file:");
			lfile = new File(scanner.nextLine());
		}
		
		DataLoader loader = new DataLoader();
		loader.setInputFile(lfile);
		LocationsManager lm = LocationsManager.instance;
		lm.init();
		while (loader.hasNext()) 
		{
			lm.addLocation(loader.getNext());			
		}

		loader.close();
		
		System.out.print("Please input the distance data file:");
		File dfile = new File(scanner.nextLine());
		while(dfile == null || !dfile.exists() || dfile.isDirectory())
		{
			if(!dfile.exists())
			{
				System.out.println("not exists");
			}
			System.out.print("Please input the distance data file:");
			dfile = new File(scanner.nextLine());
		}
		
		loader.setInputFile(dfile);
		DistancesManager dm = DistancesManager.instance;
		dm.init(lm.getLocNumber());
		while (loader.hasNext()) 
		{
			dm.addDistance(loader.getNext());			
		}
		loader.close();
		
		System.out.println("    Success");
		System.out.println();
	}
}
