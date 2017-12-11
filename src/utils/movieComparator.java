package utils;
import java.util.Comparator;
import models.Movie;

public class movieComparator implements Comparator<Movie> {

	public int compare(Movie s1,Movie s2){
		return(int)(s1.ratingsMod-s2.ratingsMod);
	}


}
