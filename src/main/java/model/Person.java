package model;

import java.util.Date;
import java.util.HashMap;

/**
 * Represents a person such as actor or a director.
 * @author Jojo
 *
 */
public class Person {

	
	private String name;
	
	private HashMap<String, String> ids;
	
	private String biography;
	
	private Date birthday;
	
	private Date death;
	
	private String birthplace;
	
	private String homepage;

	
	/**
	 * Gets the persons name.
	 * @return A String containing the first and second name of the Person.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the persons name.
	 * @param name The name the person will have.
	 */
	public void setName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("Tried to the name of a person to null.");
		}
		this.name = name;
	}

	public HashMap<String, String> getIds() {
		return ids;
	}
	
	public String getId(String platform) {
		return this.getIds().get(platform);
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getDeath() {
		return death;
	}

	public void setDeath(Date death) {
		this.death = death;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
}
