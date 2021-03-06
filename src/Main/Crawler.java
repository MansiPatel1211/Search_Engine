package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler
{
	private static final String specurl = "https://www.w3.org/";//passing w3 url to generate repository
	private static final int maxfetchpages = 100; //maximum no.of pages to be fetched
	private static final int maxdepthsearch = 5;//max depth the crawler searches
	private static HashSet<String> alllinks;

    public Crawler() {
        alllinks = new HashSet<String>();
    }
    
    public static String HTMLtoText(String URL){
    	try {
		  Document doc = Jsoup.connect(URL).get();
		  String txt = doc.body().text();
		  return txt;
    	}
    	catch(Exception e) {
    		return "oops!not done :( ";
    	}
    }
    
    
    public Elements linksPage(String URL) throws IOException {
    	  Document doc = Jsoup.connect(URL).get();
          Elements allthelinks = doc.select("a[href]");//to get all the links
          return allthelinks;
    }
    
    public static String storepages(String URL) throws IOException {
	   String loc = "C:\\Users\\Saniya\\Desktop\\Masters-Sem1\\ACC\\FinalACCProject\\SN2M\\W3C Web Pages\\Text"+System.nanoTime()+".txt";//to store the pages 
       PrintWriter printo = new PrintWriter(loc);
       printo.println(HTMLtoText(URL));                	
       printo.close(); 
       return HTMLtoText(URL);
    }
    
    public HashSet<String> getlinks(String URL, int depth) {
    	
        if ((!alllinks.contains(URL) && (depth <= maxdepthsearch))) {
            try {
                alllinks.add(URL);
                if(alllinks.size() >= maxfetchpages)
            		return alllinks;
                Elements allthelinks = linksPage(URL);//all the links in the webpage
                depth++;
                for (Element pages : allthelinks) {//for the links
                    getlinks(pages.attr("abs:href"), depth);
                }   
            } catch (IOException e) {
            }
        }
        
        return alllinks;
    }
    
}