public class TrieTree<V>
{
	TrieNode<V> root;	
	static class TrieNode<V>
	{
		char key;
		V value;
		VLArray<TrieNode<V>> childs;
		
		
		public TrieNode(char key)
		{
			this.key = key;
			childs = new VLArray<>();
		}
		
		public void addChild(String prefix,int deep,V value)
		{
			if(deep == prefix.length())
			{
				this.value = value;
				return;
			}			
			boolean exist = false;
			for (int i = 0; i < childs.getLength(); i++) 
			{
				if(childs.get(i).getKey() == prefix.charAt(deep))
				{
					exist = true;
					childs.get(i).addChild(prefix, deep+1,value);
				}
			}
			if(!exist)
			{
				TrieNode<V> node = new TrieNode<V>(prefix.charAt(deep));
				childs.add(node);
				node.addChild(prefix, deep+1, value);
			}
		}
		
		public char getKey() 
		{
			return key;
		}
		
		public V getValue() 
		{
			return value;
		}
		
		public TrieNode<V> getChild(String prefix,int deep)
		{
			if(deep == prefix.length())
				return this;			
			for (int i = 0; i < childs.getLength(); i++) 
			{
				if(childs.get(i).getKey() == prefix.charAt(deep))
				{
					return childs.get(i).getChild(prefix, deep+1);
				}
			}
			return null;
		}
		
		public void getChilds(VLArray<V> array)
		{
			for (int i = 0; i < childs.getLength(); i++) 
			{
				V v = childs.get(i).getValue();
				if(v != null)
					array.add(v);
				childs.get(i).getChilds(array);
			}		
		}
	}
	
	public TrieTree() 
	{
		root = new TrieNode<V>(' ');
	}
	
	public void addNode(String key,V value)
	{
		root.addChild(key,0, value);
	}
	
	public V getNode(String prefix)
	{
		TrieNode<V> v = root.getChild(prefix, 0);
		if(v!=null)
			return v.getValue();
		return null;
	}
	
	public VLArray<V> getNodes(String prefix)
	{
		VLArray<V> array = new VLArray<>();
		TrieNode<V> node = root.getChild(prefix, 0);
		if(node != null)
			node.getChilds(array);
		return array;
	}
	
}
