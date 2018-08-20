public class VLArray<T> 
{
	private static final int deafultInitLength = 10;
	private T [] array;
	private int length;
	
	public VLArray()
	{
		array = (T[]) new Object[deafultInitLength];
		length = 0;
	}
	
	
	public void add(T item)
	{
		if(length >= array.length)
		{
			resize();
		}
		array[length++] = item;		
	}
	
	public void addAll(VLArray<T> items)
	{
		for(int i = 0 ; i < items.getLength() ; i++)
		{
			add(items.get(i));
		}
	}
	
	public int getLength() 
	{
		return length;
	}

	private void resize() 
	{
		T[] temp = (T[]) new Object[length + deafultInitLength];
		for (int i = 0; i < length; i++) 
		{
			temp[i] = array[i];
		}
		array = temp;
	}
	
	public T get(int index)
	{
		if(index < length)
			return array[index];
		return null;
	}
	
}
