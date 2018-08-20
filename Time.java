public class Time
{
	int h;
	int m;
	int s;
	
	int ts;
	
	public static Time parseTime(int second) 
	{
		Time time = new Time();
		time.s = second % 60;
		second = second / 60;
		time.m = second % 60;
		time.h = second / 60;
		return time;
	}
	
	public static Time parseTime(String string) 
	{
		String[] strs = string.split(":");		
		Time time = new Time();
		if(strs.length == 2)
		{
			time.h = Integer.parseInt(strs[0]);
			time.m = Integer.parseInt(strs[1]);
			time.ts = time.h*3600 + time.m * 60;
		}
		else if(strs.length == 3)
		{
			time.h = Integer.parseInt(strs[0]);
			time.m = Integer.parseInt(strs[1]);
			time.s = Integer.parseInt(strs[2]);
			time.ts = time.h*3600 + time.m*60 + time.s;
		}
		else 
		{
			return null;
		}
		return time;
	}
	
	public int getTs() 
	{
		return ts;
	}

	public String toString() 
	{
		return String.format("%d:%02d:%02d", h,m,s);
	}
}
