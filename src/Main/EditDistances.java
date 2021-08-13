package Main;

import java.util.Random;

public class EditDistances {
	
	public static int calculate(String firstw, String secondw) {
		int firstl = firstw.length();
		int secondl = secondw.length();
		int[][] dist = new int[firstl + 1][secondl + 1];//matrix-dist to store the distances
		int i=0;
		while(i<=firstl)
		 {
			dist[i][0] = i;
			i++;
		  }                              
		int j=0;
		while(j <= secondl){                 
			dist[0][j] = j;                 
			j++;
		}
		
		i=0;
		while(i < firstl) 
		{
			char charatfirst = firstw.charAt(i);
			j=0;
			while( j < secondl)
			{
				char charatsecond = secondw.charAt(j);
				if (charatfirst == charatsecond) //if the last two letters are same
				{
					dist[i + 1][j + 1] = dist[i][j];//update the matrix
				} else
				{
					int change = dist[i][j] + 1;//update/change
					int put = dist[i][j + 1] + 1;//insert it 
					int remove = dist[i + 1][j] + 1;//delete it
	 
					int mind = change > put ? put : change;
					mind = remove > mind ? mind : remove;
					dist[i + 1][j + 1] = mind;
				}j++;
			}i++;
		}
	 
		return dist[firstl][secondl];//return the distance
	}
	
}
