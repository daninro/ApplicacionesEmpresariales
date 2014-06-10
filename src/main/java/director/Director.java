package director;
import java.sql.Date;

import person.Person;
public class Director extends Person{
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Director(String name, Date date_of_birth, String country) {
		super(name, date_of_birth, country);
	}

	public String toString(){
		return id + super.toString();
	}
}
