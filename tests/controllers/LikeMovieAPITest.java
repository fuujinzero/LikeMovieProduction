package controllers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import controllers.LikeMovieAPI;
import models.Movie;
import models.User;

public class LikeMovieAPITest {


private LikeMovieAPI likeMovie=new LikeMovieAPI();

//Testing that the id's are being given correctly
	@Test
	public void testUser() {
		User u1 = new User("bill", "willson", "23",  "Male", "vloger","password","5344");
		assertEquals(0, likeMovie.getUsers().size());
		likeMovie.addUser("bill", "willson", "23",  "Male", "vloger","password","5344");
		assertEquals(1, likeMovie.getUsers().size());
		assertEquals(u1,likeMovie.getUser(2));
	}
	
	//delete function
	@Test
	public void testDeleteUser()
	{
		assertEquals(0, likeMovie.getUsers().size());
		likeMovie.addUser("bill", "willson", "23",  "Male", "vloger","password","5344");
		likeMovie.addUser("bart", "willson", "8",  "Male", "child","password","8976");
		assertEquals(2, likeMovie.getUsers().size());
		likeMovie.deleteUser(4);
		assertEquals(1,likeMovie.getUsers().size());
	}
	@Test
	public void testMovie()
	{
		Movie mov1 = new Movie("lotr", "2001", "JRR-Tolkien.com");
		assertEquals(0, likeMovie.getMovies().size());
		likeMovie.addMovie("lotr", "2001", "JRR-Tolkien.com");
		assertEquals(1, likeMovie.getMovies().size());
		assertEquals(mov1,likeMovie.getMovie(4));
	}
	@Test
	public void testDeleteMovie()
	{
		assertEquals(0, likeMovie.getMovies().size());
		likeMovie.addMovie("lotr", "2001", "JRR-Tolkien.com");
		likeMovie.addMovie("lotr2", "2002", "JRR-Tolkien2.com");
		assertEquals(2, likeMovie.getMovies().size());
		likeMovie.deleteMovie(2);
		assertEquals(1,likeMovie.getMovies().size());
	}
}