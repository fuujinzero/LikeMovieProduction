package models;

public class Fixtures {
	public static User[] users = { 
			new User("bill", "willson", "23",  "Male", "vloger","password","5344"),
			new User("bart", "willson", "8",  "Male", "child","password","8976") 
	};

	public static Movie[] movies = { 
			new Movie("lotr", "2001", "JRR-Tolkien.com"),
			new Movie("lotr2", "2002", "JRR-Tolkien2.com"), 
			new Movie("lotro3", "2003", "JRR-Tolkien3.com"),

	};
	
	public static Rating[] rating = { 
			new Rating(1L, 2L, 3),
			new Rating(2L, 3L, 4), 
			new Rating(3L, 4L, 5)
	};
}

