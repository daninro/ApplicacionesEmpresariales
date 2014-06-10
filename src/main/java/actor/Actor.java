package actor;

import java.sql.Date;

import person.Person;

public class Actor extends Person{
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Actor(String name, Date date_of_birth, String country) {
		super(name, date_of_birth, country);
	}
}
