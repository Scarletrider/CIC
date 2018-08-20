import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils 
{	
	public static Location getLocation()
	{	
		Scanner scanner = new Scanner(System.in);	
		LocationsManager lm = LocationsManager.instance;
		VLArray<Location> locations = lm.getLocations(scanner.nextLine());
		while(locations.getLength() == 0)
		{
			return null;
		}
		for (int i = 0; i < locations.getLength(); i++) 
		{
			System.out.println("  " + i + ") " + locations.get(i).getName());
		}
		System.out.print("Please choose the location(0~"+ (locations.getLength() - 1) + "): ");
		int index;
		for(;;)
		{
			try
			{
				index = scanner.nextInt();	
				if(index >= 0 && index < locations.getLength())
					break;
			}
			catch(InputMismatchException e)
			{
			}
			System.out.print("Please input a right number: ");
		}
		return locations.get(index);
	}
	
	public static  String getTran()
	{
		DistancesManager dm = DistancesManager.instance;
		VLArray<String> trans = dm.getTrans();

		for (int i = 0; i < trans.getLength(); i++) 
		{
			System.out.println("  "+i+". " + trans.get(i));
		}
		System.out.print("Index: ");
		Scanner scanner = new Scanner(System.in);	
		int index;
		for(;;)
		{
			try
			{
				index = scanner.nextInt();	
				if(index >= 0 && index < trans.getLength())
					break;
			}
			catch(InputMismatchException e)
			{
			}
			System.out.print("Please input a right number: ");
		}
		return trans.get(index);
	}
	
	public static  boolean getPeak()
	{
		Scanner scanner = new Scanner(System.in);	
		for(;;)
		{
			String peak = scanner.nextLine();
			if(peak.equals("peak"))
			{
				return true;
			}
			else if(peak.equals("offpeak"))
			{
				return false;
			}
			System.out.print("Please input peak or offpeak: ");
		}
	}
	
	public static String getLimitType()
	{
		Scanner scanner = new Scanner(System.in);	
		for(;;)
		{
			String limit = scanner.nextLine();
			if(limit.equals("distance") || limit.equals("time"))
			{
				return limit;
			}
			System.out.print("Please input distance or time: ");
		}
	}
	
	public static double getLimit()
	{
		Scanner scanner = new Scanner(System.in);	
		double limit = -1;
		for(;;)
		{
			try
			{
				limit = scanner.nextDouble();	
				if(limit > 0)
					break;
			}
			catch(InputMismatchException e)
			{
			}
			System.out.print("Please input a right number (>0): ");
		}
		return limit;
	}
	
	public static int getInt(int min,int max)
	{
		Scanner scanner = new Scanner(System.in);	
		int number = -1;
		for(;;)
		{
			try
			{
				number = scanner.nextInt();	
				if(number >= min && number <= max)
					break;
			}
			catch(InputMismatchException e)
			{
			}
			System.out.print("Please input a right number (between " + min +" and "+ max +"): ");
		}
		return number;
	}
	
	public static Time getTime()
	{
		Scanner scanner = new Scanner(System.in);	
		Time time = null;
		for(;;)
		{
			try
			{
				time = Time.parseTime(scanner.nextLine());
				break;
			}catch(Exception e)
			{
				System.out.print("Please input a right time: ");
			}
		}
		return time;
	}
	public static boolean getDestString(String dest)
	{
		Scanner scanner = new Scanner(System.in);	
		String next = scanner.nextLine();
		if(next.equals(dest))
		{
			return true;
		}
		return false;
	}
}
