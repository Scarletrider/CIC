public class Menu 
{
	private VLArray<String> options;
	private VLArray<Handler> handlers;

	public Menu()
	{
		options = new VLArray<>();
		handlers = new VLArray<>();
		
		
		addMenuItem("Quit", new Handler() 
		{
			public void handle() 
			{
				System.exit(0);
			}
		});
	}

	public void addMenuItem(String option,Handler handler)
	{
		options.add(option);
		handlers.add(handler);
	}
	
	public void printMenu()
	{
		for (int i = 1; i < options.getLength(); i++) 
		{
			System.out.println("(" + i + ")    - " + options.get(i));
		}
		System.out.println("(" + 0 + ")    - " + options.get(0));
		System.out.print("Choice:> ");
	}
	
	public void handle(String input)
	{
		int option = -1;
		try
		{
			option = Integer.parseInt(input);	
			
		}
		catch(NumberFormatException e)
		{
			System.out.println("Please input a number");
		}

		if(option >= 0 && option < handlers.getLength())
		{
			handlers.get(option).handle();
		}
		else
		{
			System.out.println("Please input a number between 0 and " + (options.getLength()-1));
		}
	}
}
