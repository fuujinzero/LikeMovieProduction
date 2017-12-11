package models;


import static com.google.common.base.MoreObjects.toStringHelper;
import java.util.HashMap;
import java.util.Map;
import com.google.common.base.Objects;

public class Movie {

	public String title;
	public String year;
	public String url;
	static long counter = 01L;
	public long id=01L;
	public float ratingsMod=0;
	
	public Map<Long,Rating> rating = new HashMap<>();
	
	
	public Movie(){
		
	}

	public Movie(String title, String year, String url) {
		this.title = title;
		this.year = year;
		this.url = url;
		this.id = counter++;
	}

	@Override
	public String toString() {
		return toStringHelper(this).addValue(id).addValue(title).addValue(year).addValue(url).toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id,this.title, this.year, this.url);
	}
	
	@Override
	public boolean equals(final Object obj){
		if(obj instanceof Movie)
		{
			final Movie example=(Movie)obj;
			return Objects.equal(title, 	example.title)
					&&Objects.equal(year, 	example.year)
					&&Objects.equal(url, 	example.url);
		}
		else{
			return false;
		}
	}
}
