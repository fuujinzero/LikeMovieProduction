package controllers;

import java.util.Collection;
import java.io.File;
import java.io.IOException;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import models.User;
import utils.Serializer;
import utils.movieComparator;
import models.Movie;
import models.Rating;


public class LikeMovieAPI {

	private Serializer serializer;
	
	private Map<Long, User> userIndex = new HashMap<>();
	private Map<Long, Movie> movieIndex=new HashMap<>();
	private Map<Long, Rating> ratingIndex=new HashMap<>();
	public Optional<User> curntUser;
	
	public LikeMovieAPI(){
		
	}
	
	public LikeMovieAPI(Serializer serializer)
	{
		this.serializer=serializer;
	}
	
	@SuppressWarnings("unchecked")
	public void load()throws Exception
	{
		serializer.read();
		userIndex=(Map<Long, User>)serializer.pop();
		movieIndex=(Map<Long, Movie>)serializer.pop();
		ratingIndex= (Map<Long, Rating>)serializer.pop();
	}
	
	public void store() throws Exception
	{
		serializer.push(ratingIndex);
		serializer .push(movieIndex);
		serializer.push(userIndex);
		serializer.write();
	}
	//serializer^^
	
	/*
	 * login logout section
	 */
	public boolean logIn(Long userId,String password)
	{
		Optional<User> user = Optional.fromNullable(userIndex.get(userId));
		if(user.isPresent() && user.get().password.equals(password)) {
			curntUser = user;
			System.out.println(curntUser.get().id + ""+curntUser.get().firstName + "logged in ...");
			return true;
		}
		return false;
	}
	
	public void logout()
	{
		Optional<User> user=curntUser;
		if(user.isPresent()) {
			System.out.println(curntUser.get().firstName + "logged out ..");
			curntUser=Optional.absent();
		}
	}
	
	
	//controllers for users
	
	public Collection<User> getUsers() {
		return userIndex.values();
	}
	
	public User getUser(long id)
	{
		return userIndex.get(id);
	}
	

	public void removeUsers() {
		userIndex.clear();
	}

	public void addUser(String fName,String lName,String age,String gender,String occupation,String password,String Code) {
		User user = new User(fName,lName,age,gender,occupation,password,Code);
		userIndex.put(user.id,user);
	}
	
	
	public User getUserRatings(long id) {
		return userIndex.get(id);
	}

	public void deleteUser(long id) {
		userIndex.remove(id);
		
	}
	
	public User getUserById(Long id) {
		return userIndex.get(id);
	}
	//end of user controls^^^^^
	
	//controllers for movie
	
	
	public void addMovie(String title,String year,String url)
	{
		Movie movie = new Movie(title,year,url);
        movieIndex.put(movie.id, movie);
	}
	
	public Collection<Movie> getMovies()
	{
		return movieIndex.values();
	}
	
	public Movie getMovie(long id)
	{
		return movieIndex.get(id);
		
	}
	
	public void clearMovies()
	{
		movieIndex.clear();
	}
	
	public void deleteMovies()
	{
		movieIndex.clear();
	}
	
	public void deleteMovie(long id)
	{
		movieIndex.remove(id);
	}
	
	public List<Movie> titleSearchMovie (String word)
	{
		List<Movie> MovieResult = new ArrayList<Movie>();
		
		for(long i : movieIndex.keySet())
		{
			if(movieIndex.get(i).title.toLowerCase().contains(word.toLowerCase()))
					{
				MovieResult.add(movieIndex.get(i));
					}
		}
		return MovieResult;
	}
	
	//end of movie controls^^^^^
	
	//controllers for rating
	public Collection<Rating> getRatings()
	{
		return ratingIndex.values();
	}
	
	public Rating getRating(long id)
    {
    	return ratingIndex.get(id);
    }
	
	public void addRating(long userId,long movieId, float rating)
	{
		Rating ratings = null;
		Optional<User> user = Optional.fromNullable(userIndex.get(userId));
		Optional<Movie> movie = Optional.fromNullable(movieIndex.get(movieId));
		if(movie.isPresent() && user.isPresent()) {
			ratings = new Rating(userId,movieId,rating);
			
			movie.get().rating.put(ratings.ratingId, ratings);
			user.get().rating.put(ratings.ratingId,ratings);
			ratingIndex.put(ratings.ratingId, ratings);
			movie.get().ratingsMod = movie.get().ratingsMod + rating ;
		}
	}
	
	public Map<Long, Rating> getMovieRating(long id) {
		Optional<Movie> movie = Optional.fromNullable(movieIndex.get(id));
			return movie.get().rating;
	}
	
	public Map<Long, Rating> getUserRating(long id) {
		Optional<User> user = Optional.fromNullable(userIndex.get(id));
			return user.get().rating;
	}
	
	public void deleteRatings()
	{
		ratingIndex.clear();
	}
	public void deleteRating(long id)
    {
    	ratingIndex.remove(id);
    }
	//end of rating controls^^^^^
	
	
	/*
	 * recommender + top ten list
	 * 
	 */
	
	public void recommenderGen  (long id)
	{
		 Map<Long,Movie> MovieIndex2 = new HashMap<>();
		 MovieIndex2.putAll(movieIndex);
		 
		Optional<User> user = Optional.fromNullable(userIndex.get(id));
		Set<Long> list;
		list= user.get().rating.keySet();
		Iterator<Long> iter = list.iterator();
		while(iter.hasNext()) {
			long s = iter.next();
			Rating temp = ratingIndex.get(s);
			s = temp.movieId;
			MovieIndex2.remove(s);
		}
		
		List<Movie> list2 = new ArrayList<Movie>(MovieIndex2.values());
		Collections.sort(list2, new movieComparator().reversed());
		Iterator<Movie> iter2 = list2.iterator();
		while (iter2.hasNext()) {
			Movie s = iter2.next();
			System.out.println(s.title + "  " + (s.ratingsMod / s.rating.size()));
		}
	}
	
	public void Top10Movies()
	{
		List<Movie> list = new ArrayList<Movie>(movieIndex.values());
		Collections.sort(list, new movieComparator().reversed());
		Iterator<Movie> in = list.iterator();
		while (in.hasNext()) {
			Movie s = in.next();
			System.out.println(s.title + "  " + (s.ratingsMod / s.rating.size()));
	}
	}

	
	
	public void initialLoad()throws IOException{
		String delims="[|]";
		Scanner scanner = new Scanner(new File("./lib/users5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 8) {
				addUser(userTokens[1], userTokens[2], userTokens[3], userTokens[4], userTokens[5],userTokens[6],userTokens[7]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}
		scanner.close();
		
		   Scanner scanner1 = new Scanner( new File("./lib/items5.dat"));
           while (scanner1.hasNextLine()) {
              String userDetails1 = scanner1.nextLine();
               // parse user details string
              String[]  userTokens1 = userDetails1.split(delims);
               if (userTokens1.length == 23) {
                   addMovie(userTokens1[1],userTokens1[2],userTokens1[3]);
               } else {
                   scanner1.close();
                   throw new IOException("Invalid member length: " + userTokens1.length);
               }
           }
           scanner1.close();
               
          Scanner scanner2 = new Scanner (new File("./lib/ratings5.dat"));
               while (scanner2.hasNextLine()) {
                 String  userDetails2 = scanner2.nextLine();
                   // parse user details string
                  String[]  userTokens2 = userDetails2.split(delims);
                   if (userTokens2.length == 4) {
                       addRating(Long.valueOf(userTokens2[0]),Long.valueOf(userTokens2[1]),Float.valueOf(userTokens2[2]));
                   } else {
                       scanner2.close();
                       throw new IOException("Invalid member length: " + userTokens2.length);
                   }
      
           }
               scanner2.close();
       }
	
}

