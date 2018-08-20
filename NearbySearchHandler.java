public class NearbySearchHandler implements Handler 
{
	public void handle() 
	{
		if(!LocationsManager.instance.isInit())
		{
			System.out.println("Please load data first");
			return;
		}
		
		System.out.print("Please choose a limit (distance/d or time/t): ");
		String  type = InputUtils.getLimitType();
		
		double limit = 0;
		if(type.equals("distance"))
		{
			System.out.print("Please input the distance: ");
			limit = InputUtils.getLimit();
		}
		else if(type.equals("time"))
		{
			System.out.print("Please input the time: ");
			limit = InputUtils.getTime().getTs();
		}
		
		System.out.println("Please select a travel type: ");
		String tran = InputUtils.getTran();
		
		System.out.print("Please select peak or offpeak: ");
		boolean peak = InputUtils.getPeak();
		
		System.out.print("Please input the start location: ");
		Location start = null;
		while(start == null)
		{
			start = InputUtils.getLocation();
			if(start == null)
			{
				System.out.println("Can not find this city.");
				System.out.println("Please input a right location name: ");
			}
		}
		
		VLArray<Location> nearby = DistancesManager.instance.getNearbyLocation(start, peak, tran , type, limit);
		if(nearby.getLength() >= 1)
		{
			System.out.println("Here is the nearby location:");
			for (int i = 0; i < nearby.getLength() ; i++)
			{
				System.out.println("  " + i + ") " + nearby.get(i).getName());
			}
		}
		else
		{
			System.out.println("Can not find such nearby location.");
		}
	}

}
