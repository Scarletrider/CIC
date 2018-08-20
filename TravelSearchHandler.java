import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TravelSearchHandler implements Handler 
{
	public void handle() 
	{
		if(!LocationsManager.instance.isInit())
		{
			System.out.println("Please load data first");
			return;
		}
		System.out.print("Please input the start location name: ");
		Location start = null;
		while(start == null)
		{
			start = InputUtils.getLocation();
			if(start == null)
			{
				System.out.println("Can not find this city.");
				System.out.print("Please input a right location name: ");
			}
		}
		
		System.out.print("Please input the dest city name: ");
		Location dest = null;
		while(dest == null)
		{
			dest = InputUtils.getLocation();
			if(dest == null)
			{
				System.out.println("Can not find this city.");
				System.out.print("Please input a right location name: ");
			}
		}
	
		System.out.println("Please select a travel type: ");
		String tran = InputUtils.getTran();
		
		System.out.print("Please select peak or offpeak: ");
		boolean peak = InputUtils.getPeak();
		
		VLArray<Distance> path = DistancesManager.instance.getShortDistance(start, dest, peak, tran);
		if(path.getLength() > 0)
		{
			double d = 0;
			int t = 0;
			for (int i = path.getLength() - 1; i >= 0 ; i--)
			{
				Location s = LocationsManager.instance.getLocation(path.get(i).getStart());
				Location e = LocationsManager.instance.getLocation(path.get(i).getEnd());
				System.out.println("    " + s.getName() + "-> "+ e.getName());
				d += path.get(i).getDistance();
				t += path.get(i).getTime().getTs();
			}	
			System.out.println("Total distance: " + d);
			System.out.println("Total time: " + Time.parseTime(t));
			

			System.out.print("Input detail to see more: ");
			if(InputUtils.getDestString("detail"))
			{
				for (int i = path.getLength() - 1; i >= 0 ; i--) 
				{
					System.out.println("    " + i + ") " + path.get(i));
				}	
				System.out.print("Input save to see save path to file: ");
				if(InputUtils.getDestString("save")){
					System.out.print("Please input the filename of the new distance file: ");
					Scanner scanner = new Scanner(System.in);
					File file = new File(scanner.nextLine());
					while(file.exists())
					{
						System.out.print("File already exists, overwrite it?(y/n):");
						String overwrite = scanner.nextLine();
						if(overwrite.equals("y"))
						{
							break;
						}
						else
						{
							System.out.print("Please input the filename: ");
							file = new File(scanner.nextLine());
						}
					}
					DistancesManager.instance.save(file, path);
				}
			}
		}
		else
		{
			System.out.println("Can not travel to " + dest.getName() + " from " + start.getName());
		}
		
	}

}
