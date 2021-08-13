package Main;

import java.util.HashMap;

public class Trie<t> {
	private Node<t> root;
	public int size;
	
	public Trie() {
		this.root = new Node<t>();
		this.size = 0;
	}
	public void ins(String key, t val) 
	{//function to insert keys and values
		HashMap<Character, Node<t>> child = this.root.child;
		Node<t> n = null;
		int kl=key.length();
		int i=0;
		while( i < kl) 
		{
			char loc = key.charAt(i);
			
			if (child.containsKey(loc)) 
			{
				n = child.get(loc);
			} else 
			{
				n = new Node<t>(loc);
				child.put(loc,n);//insert the children at node n
			}
			
			if (i==kl-1) //the word ends here
			{
				n.value = val;
			}
			
			child = n.child;
			++i;
		}
		this.size += 1;
	}
	
	 
	public t wordsearch(String words) 
	{
		
		HashMap<Character, Node<t>> child = this.root.child;//create objectc of hash map
		Node<t> n = null;
		t returnpagein = null;//to r eturn page index
		int i=0;
		int wl=words.length();//the length of the word
		while(i < wl) {
			char c = words.charAt(i);
			
			if (child.containsKey(c))
			{
				n = child.get(c);
			} else
			{
				return null;
			}
			if (i == wl-1) 
			{
				returnpagein = n.value;//return page index
			}
			child = n.child;
			++i;
		}
		return returnpagein; 
	}

}
