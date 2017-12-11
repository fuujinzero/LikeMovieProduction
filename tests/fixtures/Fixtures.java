package fixtures;

import models.Movie;
import models.Rating;
import models.User;;

public class Fixtures {
	
	public static User[]users=
		{
			new User("bill", "willson", "23",  "Male", "vloger","54321","ds985"),
			new User("bart", "willson", "8",  "Male", "child","2f6233","bc652")
		};
	
	public static Rating[]ratings=
		{
			new Rating(5,26,1),
			new Rating(6L,3L,2)
		};
	
	public static Movie[]movies=
		{
				new Movie("lotr", "2001", "JRR-Tolkien.com"),
				new Movie("lotr2", "2002", "JRR-Tolkien2.com"), 
		};
}
