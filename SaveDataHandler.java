import java.io.File;
import java.util.Scanner;

public class SaveDataHandler implements Handler
{
	public void handle()
	{
		if(!LocationsManager.instance.isInit())
		{
			System.out.println("Please load data first");
			return;
		}
		
		System.out.print("Please input the filename of the new distance file: ");
		Scanner scanner = new Scanner(System.in);
		File dest = new File(scanner.nextLine());
		while(dest.exists())
		{
			System.out.print("File already exists, overwrite it?(y/n):");
			String overwrite = scanner.nextLine();
			if(overwrite.equals("y"))
			{
				break;
			}
			else
			{
				System.out.print("Please input the filename of the new distance file: ");
				dest = new File(scanner.nextLine());
			}
		}
		
		DistancesManager.instance.save(dest);	
	}
}
