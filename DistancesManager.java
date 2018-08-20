import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DistancesManager
{
	public static DistancesManager instance = new DistancesManager();
	
	private VLArray<String> trans;
	
	private LocationsManager lm;
	private VLArray<Distance>[][] distances;
	private VLArray<Distance> ds;
	private boolean isInit;
	private DistancesManager() 
	{
		isInit = false;
	}
	
	public void init(int size)
	{
		distances = new VLArray[size][size];
		lm = LocationsManager.instance;
		trans = new VLArray<>();
		ds = new VLArray<>();
		isInit = true;
	}
	public boolean isInit() 
	{
		return isInit;
	}

	private Distance decode(String str)
	{
		String[] strs = str.split(",",-1);
		
		if(strs.length != 11)
			return null;
		
		Distance distance = new Distance();
		distance.setStart(strs[0]);
		distance.setEnd(strs[3]);
		distance.setTransportation(strs[6]);
		
		int i = 0;
		for (i = 0; i < trans.getLength(); i++) 
		{
			if(trans.get(i).equals(strs[6]))
				break;
		}
		if(i == trans.getLength())
		{
			trans.add(strs[6]);
		}
		
		distance.setDistance(Double.parseDouble(strs[7]));
		distance.setTime(Time.parseTime(strs[8]));
		
		if(!strs[9].isEmpty())
		{
			distance.setPeak(Time.parseTime(strs[9].split("=")[1]));
		}

		if(!strs[10].isEmpty())
		{
			if(strs[10].startsWith("Unaccessible"))
			{
				distance.setImpairment(100);
			}
			else
			{
				Time pt = Time.parseTime(strs[10]);
				Time t = distance.getTime();
				
				int impair = 100 - (100 * t.getTs() / pt.getTs());
				distance.setImpairment(impair);
			}
		}
		return distance;
	}
	
	public void addDistance(String str)
	{
		Distance distance = decode(str);
		
		if(distance == null)
		{
			return;
		}
		
		ds.add(distance);
		
		Location sl = lm.getLocation(distance.getStart());
		Location el = lm.getLocation(distance.getEnd());
		
		if(sl != null && el != null)
		{
			int start = sl.getId();
			int end = el.getId();
			if(distances[start][end] == null)
			{
				distances[start][end] = new VLArray<>();
			}
			distances[start][end].add(distance);
		}
		else
		{
			System.out.print("    Warning: ");
			if(sl == null)
				System.out.println("Start not find: " + distance.getStart());
			if(el == null)
				System.out.println("End not find: " + distance.getEnd());
			
			System.out.print("      In Distance: ");
			System.out.println(str);
			distance.setSrc(str);
		}
	}
	
	public VLArray<Distance> getShortDistance(Location start,Location end,boolean peak,String type)
	{
		VLArray<Distance> path = new VLArray<>();
		int size = distances.length;
		
		Distance[][] time = new Distance[size][size];
		int[][] w = new int[size][size];
		
		for(int i = 0 ; i < size ; i++)
		{
			for (int j = 0; j < size; j++)
			{
				VLArray<Distance> d = distances[i][j];
				if(i != j)
				{
					w[i][j] = -1;
				}
				else
				{
					w[i][j] = 0;
				}

				if(d != null)
				{
					for (int k = 0; k < d.getLength(); k++) 
					{
						if(d.get(k).getTransportation().equals(type))
						{
							time[i][j] = d.get(k);
							if(peak && d.get(k).getPeak()!=null)
							{
								w[i][j] = d.get(k).getPeak().getTs();
							}
							else
							{
								w[i][j] = d.get(k).getTime().getTs();
							}
						}
					}
				}
			}
		}
		
		int[] t = new int[size];
		int[] v = new int[size];
		int[] prefix = new int[size];
		int s = start.getId();

		for (int i = 0; i < size; i++) 
		{
			t[i] = w[s][i];
			prefix[i] = s;
		}

		for(int i = 0 ; i < size ; i++)
		{
			int index = -1;
			for (int j = 0; j < size; j++)
			{
				if( v[j] == 0 && t[j] != -1 && (index == -1 || t[j] < t[index]))
				{
					index = j;
				}
			}
			if(index == -1)
				break;
			v[index] = 1;

			for (int j = 0; j < size ; j++) 
			{
				if(v[j] == 0)
				{
					if(w[index][j] != -1)
					{
						if(t[j] == -1 || (t[j] > t[index] + w[index][j]))
						{
							prefix[j] = index;
							t[j] = t[index] + w[index][j];
						}
					}
				}
			}
			
		}
		
		if(t[end.getId()] != -1)
		{
			double distance = 0;
			for (int i = end.getId(); i != s; i = prefix[i])
			{
				path.add(time[prefix[i]][i]);
			}
		}
		return path;
	}
	
	public VLArray<Location> getNearbyLocation(Location start,boolean peak,String tran,String type,double limit){
		VLArray<Location> location = new VLArray<>();
		int size = distances.length;
		
		double[][] w = new double[size][size];
		
		for(int i = 0 ; i < size ; i++)
		{
			for (int j = 0; j < size; j++) 
			{
				VLArray<Distance> d = distances[i][j];
				if(i != j)
					w[i][j] = -1;
				else 
					w[i][j] = 0;
				if(d != null)
				{
					for (int k = 0; k < d.getLength(); k++) 
					{
						if(d.get(k).getTransportation().equals(tran))
						{
							if(type.equals("time"))
							{
								if(peak && d.get(k).getPeak()!=null)
								{
									w[i][j] = d.get(k).getPeak().getTs();
								}
								else
								{
									w[i][j] = d.get(k).getTime().getTs();
								}
							}
							else if(type.equals("distance"))
							{
									w[i][j] = d.get(k).getDistance();
							}
						}
					}
				}
			}
		}
		
		double[] t = new double[size];
		int[] v = new int[size];
		int[] prefix = new int[size];
		int s = start.getId();
		for (int i = 0; i < size; i++) 
		{
			t[i] = w[s][i];
			prefix[i] = s;
		}
		for(int i = 0 ; i < size ; i++)
		{
			int index = -1;
			for (int j = 0; j < size; j++) 
			{
				if( v[j] == 0 && t[j] != -1 && (index == -1 || t[j] < t[index]))
				{
					index = j;
				}
			}
			if(index == -1)
				break;
			v[index] = 1;

			for (int j = 0; j < size ; j++) 
			{
				if(v[j] == 0)
				{
					if(w[index][j] > 0)
					{
						if(t[j] == -1 || (t[j] > t[index] + w[index][j]))
						{
							prefix[j] = index;
							t[j] = t[index] + w[index][j];
						}
					}
				}
			}
			
		}
		
		for (int i = 0; i < size ; i++) 
		{
			if( t[i] != -1 && t[i] <= limit)
			{
				location.add(lm.getLocaiton(i));
			}
		}
		
		return location;
	}
	
	public VLArray<String> getTrans() 
	{
		return trans;
	}

	public void save(File dest) 
	{
		BufferedWriter writer = null;
		try 
		{
			writer = new BufferedWriter(new FileWriter(dest));
			for (int i = 0; i < ds.getLength(); i++) 
			{
				Distance distance = ds.get(i);
				if(distance.getSrc() != null)
				{
					writer.write(distance.getSrc());
					writer.newLine();
					writer.flush();
					continue;
				}
				
				Location s = lm.getLocation(distance.getStart());
				
				writer.write(s.getName());
				writer.write(",");
				writer.write(s.getState().getShortname());
				writer.write(",");
				writer.write(s.getState().getCountry().getName());
				writer.write(",");
						
				Location e = lm.getLocation(distance.getEnd());
				writer.write(e.getName());
				writer.write(",");
				writer.write(e.getState().getShortname());
				writer.write(",");
				writer.write(e.getState().getCountry().getName());
				writer.write(",");
						
				writer.write(distance.getTransportation());
				writer.write(",");
						
				writer.write(String.valueOf(distance.getDistance()));
				writer.write(",");
							
				writer.write(String.valueOf(distance.getTime()));
				writer.write(",");
						
				if(distance.getPeak() != null)
				{
					writer.write("peak=" + String.valueOf(distance.getPeak()));
				}
				writer.write(",");
						
				if(distance.getImpairment() != 0)
				{
					int impair = distance.getImpairment();
					if(impair < 100 && impair > 0)
					{
						writer.write(String.valueOf(Time.parseTime(distance.getTime().getTs() * 100 / (100 - impair))));
					}
					else 
					{
						writer.write("Unaccessible");
					}
				}
							
				writer.newLine();
				writer.flush();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			if(writer != null)
			{
				try 
				{
					writer.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public void save(File dest,VLArray<Distance> ds) 
	{
		BufferedWriter writer = null;
		try 
		{
			writer = new BufferedWriter(new FileWriter(dest));
			for (int i = 0; i < ds.getLength(); i++) 
			{
				writer.write(ds.get(i).toString());
				Location s = lm.getLocation(ds.get(i).getStart());
				Location e= lm.getLocation(ds.get(i).getEnd());
				if(!s.getState().getCountry().equals(e.getState().getCountry()))
				{
					writer.write(" (International  Travel) ");
				}
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			if(writer != null)
			{
				try 
				{
					writer.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	public VLArray<Distance> getDistances(Location start, Location dest) 
	{
		return distances[start.getId()][dest.getId()];
	}
}
