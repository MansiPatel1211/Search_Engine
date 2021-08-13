
package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.io.FileReader;
import java.io.IOException;

public class Hashh {//to create hash sets for the input files

	public HashSet<String> storepages (String fname) {//function to store pages
		HashSet<String> h = new HashSet<String>();//object of hash set
		String l = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));//to read the file
			while ((l = br.readLine()) != null)//add to the hash set if it's not  null
			{
				h.add(l);
			}
			br.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("Oops!No file found :( ");//throw an exception if the file isn't found
			
			
		}
		catch (IOException e){
			System.out.println("An Error :(");
		}
		return h;
	}
}
