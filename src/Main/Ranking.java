

package Main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Ranking {
	private static String regularwordexp = "[[ ]*|[,]*|[)]*|[(]*|[\"]*|[;]*|[-]*|[:]*|[']*|[’]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+";
	public static int  numberwords(String URL, String WORD) throws IOException{
		Document conn = Jsoup.connect(URL).get();//page connection
		Map<String, WordElement> mapobj = new HashMap<String, WordElement>();//to store the rank of the corresponding word
		String getContent = conn.body().text();//page content from body of the webpages
		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(getContent.getBytes(StandardCharsets.UTF_8))));
		String l;
		while ((l = br.readLine()) != null)
		{
			String words [] = l.split(regularwordexp);
			for (String word : words)
			{
				if ("".equals(word))
				{
					continue;
				}
				
				WordElement welm = mapobj.get(word);
				
				if (word.equalsIgnoreCase(WORD))
				{
					
					if (welm == null) 
					{
						
						welm = new WordElement();
						welm.word = word;
						welm.count = 0;
						mapobj.put(word, welm);
						
					}
					welm.count++;//increment the word elements
				}
			}
		}
		
		br.close();
		SortedSet<WordElement> sortwords = new TreeSet<WordElement>(mapobj.values());
		int k = 0;
		int maxxx = 1000;

		LinkedList <String> uselessWords = new LinkedList <>();
		try {
			
			BufferedReader brrobj = new BufferedReader(new FileReader("stopwords.txt"));
			String wr;
			while ((wr = brrobj.readLine()) != null)
			{
				uselessWords.add(wr);
			}
			brrobj.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("Sorry! Not found :(");
		}

		for (WordElement word : sortwords) {
			if (k >= maxxx) { 
				break;
			}

			if (uselessWords.contains(word.word)) {
				k++;
				maxxx++;//max words
			}
			else {
				k++;
				return word.count;
			}

		}
		return 0;

	}
	
	public static class WordElement implements Comparable<WordElement>
	{
		String word;//search words in hashmap
		int count;

		@Override
		public int hashCode() {
			return word.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return word.equals(((WordElement) obj).word);
		}

		@Override
		public int compareTo(WordElement b) {
			return b.count - count;
		}
	}
	
}
