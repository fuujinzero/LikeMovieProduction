package userTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import models.User;
import static models.Fixtures.users;

public class UserTest {
User bill=new User("bill", "willson", "23",  "Male", "vloger","da33","340307");
	
	@Test
	public void testAdd(){
		assertEquals("bill", bill.firstName);
		assertEquals("willson", bill.lastName);
		assertEquals("23", bill.age);
		assertEquals("Male", bill.gender);
		assertEquals("vloger", bill.occupation);
		assertEquals("da33", bill.password);
		assertEquals("340307", bill.zipCode);
	}
	
	@Test 
	public void IDsTest(){
		Set<Long>testID=new HashSet<>();
		for(User user: users)
		{
			testID.add(user.id);
		}
		assertEquals(users.length, testID.size());
	}
	
	
	@Test
	public void testToString(){
		assertEquals("User{"+bill.id+", bill, willson, 23, Male, vlogger, da33, 340307, default}",bill.toString());
	}
}
