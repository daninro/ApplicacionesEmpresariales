package person;

import java.sql.Date;

public class Person  {
	private String name;
	private Date date_of_birth;
	private String country;

	public Person(String name, Date date_of_birth, String country){
		this.setName(name);
		this.setDate_of_birth(date_of_birth);
		this.setCountry(country);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString(){
		return "Person [name = " + name + "]";
	}

}
