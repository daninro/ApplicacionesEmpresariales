package person;

public class Person  {
	private String name;
	private String date_of_birth;
	private String country;

	public Person(String name, String date_of_birth, String country){
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

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
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
		return "Movie [name = " + name + "]";
	}

}
