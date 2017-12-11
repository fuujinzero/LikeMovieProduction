package controllers;

import java.util.Collection;
import java.util.Map;
import models.Movie;
import models.Rating;
import models.User;
import asg.cliche.Command;
import asg.cliche.Param;


public class Admin {
	private String name;
	private LikeMovieAPI likeMovies;
	
	public Admin(LikeMovieAPI likeMovies,String name)
	{
		this.likeMovies=likeMovies;
		this.setName(name);
		
	}

	private void setName(String name) {
		this.name=name;
	}
	private String getName(){
		return name;
	}
	
	@Command(description="Create new User")
	public void addUser(@Param(name="First Name") String fname,@Param(name="Last Name") String lname,@Param(name="Age") String age,@Param(name="Gender") String gender,
			@Param(name="occupation")String occupation,@Param(name="password") String password,@Param(name="zipCode") String zipCode)
	{
		likeMovies.addUser(fname,lname,age,gender,occupation,password,zipCode);
	}
	
	@Command(description="Delete a User")
	public void deleteUser(@Param(name="User ID")long id)
	{
		likeMovies.deleteUser(id);
	}
	
	@Command(description="Delete ALL Users")
	public void clearUsers()
	{
		likeMovies.removeUsers();
	}
	
	@Command(description="Add a new Movie")
	public void addMovie(@Param(name="Title")String title,@Param(name="Release Date")String releaseDate,@Param(name="Link")String link)
	{
		likeMovies.addMovie(title, releaseDate, link);
	}
	
	
	@Command(description="Delete Movie")
	public void deleteMovie(@Param(name="Movie Id")long id)
	{
		likeMovies.deleteMovie(id);
	}
	
	@Command(description="Clear all movies")
	public void clearMovies()
	{
		likeMovies.clearMovies();
	}
	
	
	@Command(description="Delete all Ratings")
	public void clearRating()
	{
		likeMovies.deleteRatings();
	}
	@Command(description ="Get all User Details")
	public void getUsers()
	{
		Collection<User> users = likeMovies.getUsers();
		System.out.println(users);
	}
	
	@Command(description="Search for a User")
	public User getUser(@Param(name="User Id")long id)
	{
		return likeMovies.getUser(id);
	}
	@Command(description="Get all Movies")
	public void getMovies()
	{
		Collection<Movie> movies = likeMovies.getMovies();
		System.out.println(movies);
	}
	
	@Command(description="Get a Movie")
	public Movie getMovie(@Param(name="Movie Id")long id)
	{
		return likeMovies.getMovie(id);
	}	
	@Command(description="Get All Ratings")
	public void getRatings()
	{
		Collection<Rating> ratings = likeMovies.getRatings();
		System.out.println(ratings);
	}
	
	@Command(description="Add a new Rating")
	public void addRating(@Param(name="User id")long UserId,@Param(name="Movies Id")long movieId,@Param(name="Rating Value")float rating)
	{
		likeMovies.addRating(UserId, movieId, rating);
	}
	
	@Command(description="Gets User Ratings")
	public Map<Long,Rating> getUserRating(@Param(name="User ID")long id)
	{
		return likeMovies.getUserRating(id);
	}
	
	@Command(description="Get Movies Ratings")
	public Map<Long,Rating> getMovieRating(@Param(name="Movie Id")long id)
	{
		return likeMovies.getMovieRating(id);
	}
	
	@Command(description="Return a rating")
	public Rating getRating(@Param(name="Rating Id")long id)
	{
		return likeMovies.getRating(id);
	}
	
	@Command(description="Delete a rating")
	public void deleteRating(@Param(name="Rating Id")long id)
	{
		likeMovies.deleteRating(id);
	}
	
	@Command(description="Get TOP 10 Movies")
	public void Top10()
	{
		likeMovies.Top10Movies();
	}
	
	@Command(description="Get User Recommended Movies")
	public void Recommender()
	{
		likeMovies.recommenderGen(likeMovies.curntUser.get().id);
	}
	
	@Command(description="Get Movie via title")
	public void searchMovie(String word)
	{
		for(Movie movie : likeMovies.titleSearchMovie(word))
		{
			System.out.println(movie);
		}
	}
	
}

	