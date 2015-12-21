package model;

import java.util.Date;
import java.util.HashMap;

public class Movie {

	
	private String title;
	
	private int year;
	
	private HashMap<String, String> ids;
	
	private String tagline;
	
	private String overview;
	
	private Date released;
	
	private int runtime;
	
	private float rating;
	
	private String[] genres;
	
	private String language;
	
	private String homepage;
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Tried to set a Movie title to null.");
		}
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public HashMap<String, String> getIds() {
		return this.ids;
	}

	public String getId(String platform) {
		return this.getIds().get(platform);
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	
	
	
	
	
	
}
