
package models;

import static com.google.common.base.MoreObjects.toStringHelper;
import com.google.common.base.Objects;

public class Rating {
	static long counter=01L;
	public long ratingId=0L;
	public long userId=0L;
	public long movieId=0L;
	public float ratings;
	
	public Rating()
	{
		
	}
	public Rating(long userId,long movieId,float ratings)
	{
		this.ratingId=counter++;
		this.userId=userId;
		this.movieId=movieId;
		this.ratings=ratings;
	}
	
	@Override
	public String toString()
	{
		return toStringHelper(this).addValue(ratingId)
								   .addValue(userId)
								   .addValue(movieId)
								   .addValue(ratings)
								   .toString();
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hashCode(this.userId,this.movieId,this.ratings);
	}

}

