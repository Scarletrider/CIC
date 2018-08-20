public class UpdateDataHandler implements Handler 
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
				System.out.print("Please input a right location name: ");
			}
		}
		
		VLArray<Distance> ds = DistancesManager.instance.getDistances(start,dest);
		for (int i = 0; i < ds.getLength(); i++) 
		{
			System.out.println("  " + i + ") " + ds.get(i));
		}
		System.out.print("Please choose the distance you want to update: ");
		int index = InputUtils.getInt(0, ds.getLength() - 1);
		System.out.print("Please input the new impairment(0~100): ");
		ds.get(index).setImpairment(InputUtils.getInt(0, 100));
	}

}
