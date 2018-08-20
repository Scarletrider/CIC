import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataLoader 
{
	private BufferedReader reader;
	private String next;
	
	public void setInputFile(File file)
	{
		try 
		{
			reader = new BufferedReader(new FileReader(file));
		}
		catch (FileNotFoundException e) 
		{
			reader = null;
		}	
	}
	
	public boolean hasNext()
	{		
		try 
		{
			if(reader != null && (next = reader.readLine()) != null )
			{
				if(next.isEmpty())
				{
					return hasNext();
				}
				return true;
			}
		} 
		catch (IOException e) 
		{
			return false;
		}
		return false;
	}
	
	public String getNext()
	{
		return next;
	}
	
	public void close()
	{
		try 
		{
			reader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
