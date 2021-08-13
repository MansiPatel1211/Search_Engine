package Main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;	


public class HTMLtoText {

		public static void HTMLtoText(File file, String fname) throws IOException {
			
			Document doc = Jsoup.parse(file, "utf-8");//use jsoup to parse html file to text
			
			String docs = doc.text();
			
			String loc = "C:\\Users\\Saniya\\Desktop\\Masters-Sem1\\ACC\\FinalACCProject\\SN2M\\W3C Web Pages\\Text"+fname+".txt";

			PrintWriter pathh = new PrintWriter(loc);//giving location
			pathh.println(docs);
			pathh.close();
		}
}
