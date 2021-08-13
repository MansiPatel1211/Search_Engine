package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Engine {
	
	private static final String specurl = "https://www.w3.org/";
	private static Trie<ArrayList<Integer>> trie;
	private final String regularexp = "[[ ]*|[,]*|[)]*|[(]*|[\"]*|[;]*|[-]*|[:]*|[']*|[’]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+";
	private static String [] allpages;
	private static HashSet<String> allLinks;
	private static String [] allpagesArray;
	private static LinkedList<String> suggestions = new LinkedList<>(); 
	
	public HashSet<String> createTrie(String urlName) throws IOException {
		Hashh h =  new Hashh();
		Crawler c = new Crawler();		
		trie = new Trie<ArrayList<Integer>>();
		HashSet<String> uselessWords = h.storepages("stopwords.txt");
		allLinks = c.getlinks(urlName, 1);
		HashSet<String> temp = null;
		String txt;
		String word;String[] splitWords;
		
		Iterator<String> linkIterator = null;
		Iterator<String> wordIterator = null;
		
		linkIterator = allLinks.iterator();
		
		
		int i = 0;
		while(linkIterator.hasNext()) {
			String str = linkIterator.next();
			txt = Crawler.HTMLtoText(str);
			
			if(txt.length() == 0) {
				continue;
			}
			
			txt = txt.toLowerCase();
			splitWords = txt.split(regularexp);
			
			for(String s: splitWords) {
				suggestions.add(s);
			}
			
			suggestions.removeAll(uselessWords);
			
			temp = new HashSet<String>(Arrays.asList(splitWords));
			temp.remove(uselessWords);
			
			wordIterator = temp.iterator();
			
			while(wordIterator.hasNext()) {
				word = (String) wordIterator.next();
				ArrayList<Integer> ar = trie.wordsearch(word);
				
				
				if (ar == null) {
					trie.ins(word, new ArrayList<Integer>(Arrays.asList(i)));
				} else {
					ar.add(i);
				}
			}
			

			i++;
		}
		
		return allLinks;
	
	}
	
	public String[] search (String[] index) {
		
		int[]  find = new int[allLinks.size()];
		List<String> links = new ArrayList<String>(allLinks);		
		ArrayList<Integer> tmp = null;
		int s=0;
		while(s<index.length)
		{
			tmp = trie.wordsearch(index[s].toLowerCase());
				if (tmp != null)
				{
				for (int j = 0; j < tmp.size(); j++) 
				{
					find[tmp.get(j)]++;
				}
			    }
				else 
				{
				System.out.println("Sorry, the word ** " + index[s] + " ** is not present in any of the files :(" );
				suggestionsofwords(index[s]);
				return null;
			    }++s;
		}
		
		ArrayList<String> allpages = new ArrayList<String>();
		int z=0;
		while(z<find.length)
		{
			if (find[z] == index.length)
			{
				allpages.add(links.get(z));//to store the indexes of the pages
			}
			++z;
		}
		return allpages.toArray(new String[0]);
	}
	
	public static void suggestionsofwords(String str) {
				
		int dist = 10000;
		String suggest = "Sorry! No Suitable Suggestion Found :(";
		for(String tempv: suggestions) {
			int d = EditDistances.calculate(str, tempv);
			if(d < dist && d != 0)
			{
				suggest = tempv;
				dist = d;
			}
		}
		
		System.out.println("**Do you mean :  " + suggest + " ?");
		
	}
	
	
	public static void main(String args[]) throws IOException {
		Engine e = new Engine();
		e.createTrie(specurl);
		String[] sr = {"W3"};
		e.search(sr);
	}
}


