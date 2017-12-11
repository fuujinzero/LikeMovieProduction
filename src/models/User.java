package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

public class User {

	public String firstName;
	public String lastName;
	public String age;
	public String gender;
	public String occupation;
	public String password;
	public String zipCode;
	public String role;
	public static long counter = 01L;
	public long id=0L;
	
	public Map<Long,Rating> rating = new HashMap<>();

	public User()
	{
		
	}
	
	public User(String firstName, String lastName, String age, String gender,String occupation, String password, String zipCode) 
	{
		this(firstName,lastName,age,gender,occupation,password,zipCode,"default");
	}
	
	public User(String firstName, String lastName, String age, String gender, String occupation,String password,String zipCode,String role) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.zipCode=zipCode;
		this.password=password;
		this.role=role;
		this.id = counter++;
	}
	@Override
	public String toString()
	{
		return toStringHelper(this).addValue(id)
				.addValue(firstName)
				.addValue(lastName)
				.addValue(age)
				.addValue(gender)
				.addValue(occupation)
				.addValue(password)
				.addValue(zipCode)
				.addValue(role)
				.toString();
	}
	@Override
	public int hashCode()
	{
		return Objects.hashCode(this.firstName, this.lastName, this.age, this.gender, this.occupation,this.password,this.zipCode);
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if(obj instanceof User){
			final User other=(User)obj;
			return Objects.equal(firstName, 		other.firstName)
					&&Objects.equal(lastName,		other.lastName)
					&&Objects.equal(age, 			other.age)
					&&Objects.equal(gender,			other.gender)
					&&Objects.equal(occupation, 	other.occupation)
					&&Objects.equal(password,		other.password )
					&&Objects.equal(zipCode,		other.zipCode);
		}
		else
		{
			return false;
		}
	}
}