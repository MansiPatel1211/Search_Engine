package Main;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class Console {

	private static final String specurl = "https://www.w3.org/";
	private static final String Rxword = "[[ ]*|[,]*|[)]*|[(]*|[\"]*|[;]*|[-]*|[:]*|[']*|[’]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+";
	
	public static void main(String [] args) throws IOException {
		
		
		Engine sobj = new Engine();
		System.out.println("----------------------------------------------------------------------");
		System.out.println("____________________ACC PROJECT : SEARCH ENGINE- SN^2M_____________");
		System.out.println("__________________________Team Members_____________________________");
		System.out.println("\t\t 1.Saniya  Sayed\n\t\t 2.Mansi Patel\n\t\t 3.Nisha Jasoliya\n\t\t 4.Naisargi Joshipura");
		System.out.println("\n_______________________ UNDER THE GUIDANCE OF ______________________");
		System.out.println("\t\t\t Dr. Ikjot Saini");
		System.out.println("----------------------------------------------------------------------");

		

		HashSet<String> l = sobj.createTrie(specurl);//create hashtable using trie
		boolean bool = true;
		Scanner scobj = new Scanner(System.in);
		String wordsearch;
		while(bool)
		{
			System.out.println("\t\t^^WORD SEARCH^^");
			wordsearch = scobj.next();//take the input from the user
			if(!wordsearch.equals(null)) 
			{
				String [] idx = wordsearch.split(Rxword);//spilt the regular expression word and the entered word
				String[] allpages = sobj.search(idx);
				try {
					if (allpages == null)
					{//to suggest the similar words
					}
					else 
					{
						Map<String, Integer> uL = null;//for unsorted Links
						uL = new HashMap<>();
						
						for (String url : allpages) {
							
							uL.put(url, Ranking.numberwords(url, wordsearch));//store all the links found in the hashmap
						}
					LinkedHashMap<String, Integer> invertsMap = new LinkedHashMap<>();//to reverse the map , in accordance with the frequency
						
				    uL.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> invertsMap.put(x.getKey(), x.getValue()));
			        System.out.println("_____________________________________________________________");

				        System.out.println("\t\t | Page Rank ||Search |");
				        System.out.println("_____________________________________________________________\n");
				        int counter = 1;
				        for (Map.Entry<String, Integer> putin : invertsMap.entrySet()) {
				        	if(counter > 10)
				        		break;
				            System.out.println("\t\t | "+putin.getValue() + " ||" + putin.getKey()+" ");
				            counter++;
				        }
				        System.out.println("_______________________________________________");
				        System.out.println("Want to search more?\n -->Press 0 to search more \n -->Press 1 to exit");
				        
				        while(true) {
				        	String inp = scobj.next();
				        	if(inp.equals("0")) {
					        	break;
					        }
				        	else if(inp.equals("1")) {
				        		bool = false;
					        	System.out.println("Thank you :) Have a Great Day!");
					        	System.exit(1);
				        	}
				        	else 
				        	{
				        		System.out.println("Oops! A wrong input was provided.\n -->Press 0 to search more \n -->Press 1 to exit ");
				        	}
				        }
					}
				
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
		}
		else {
			System.out.println("The Search word isn't valid. Kindly enter a new word to search");
			continue;
		}
		
	}
}
	
}
