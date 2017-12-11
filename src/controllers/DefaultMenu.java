package controllers;

import java.util.Collection;
import java.util.Map;
import models.Movie;
import models.Rating;
import models.User;
import asg.cliche.Command;
import asg.cliche.Param;

public class DefaultMenu {
	
	
	private User user;
	private LikeMovieAPI likeMovies;
	private String name;

	
	public DefaultMenu (LikeMovieAPI likeMovies, User user){
		this.likeMovies=likeMovies;
		this.setName(user.firstName);
		this.user=user;
	}
	

	public void setName(String name) {
		this.name=name;
	}
	
	public String getName()
	{
		return name;
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
	
	@Command(description="Get User Ratings")
	public Map<Long,Rating> getUserRating(@Param(name="User ID")long id)
	{
		return likeMovies.getUserRating(id);
	}
	
	@Command(description="Get Movies Rating")
	public Map<Long,Rating> getMovieRating(@Param(name="Movie Id")long id)
	{
		return likeMovies.getMovieRating(id);
	}
	
	@Command(description="Return a Rating")
	public Rating getRating(@Param(name="Rating Id")long id)
	{
		return likeMovies.getRating(id);
	}
	
	@Command(description="Delete a Rating")
	public void deleteRating(@Param(name="Rating Id")long id)
	{
		likeMovies.deleteRating(id);
	}
	
	@Command(description="Get top 10 Movies")
	public void Top10()
	{
		likeMovies.Top10Movies();
	}
	
	@Command(description="Get User Recommended Movies")
	public void Recommender()
	{
		likeMovies.recommenderGen(likeMovies.curntUser.get().id);
	}

	@Command(description="Search for Movie using title")
	public void searchMovie(String word)
	{
		for(Movie movie : likeMovies.titleSearchMovie(word))
		{
			System.out.println(movie);
		}
	}
}
