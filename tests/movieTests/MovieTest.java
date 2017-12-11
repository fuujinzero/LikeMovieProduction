package movieTests;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import models.Movie;

public class MovieTest {

	Movie test = new Movie("lotr", "2001", "JRR-Tolkien.com");
	
	@Test
	public void testAdd()
	{
		assertEquals("lotr", 				test.title);
		assertEquals("2001", 				test.year);
		assertEquals("JRR-Tolkien.com", 	test.url);
	}
	
	@Test
	public void testToString()
	{
		assertEquals("Movie{"+test.id+", lotr, 2001, JRR-Tolkien.com}", test.toString());
	}
}
