package screenplay;
import java.sql.Date;

import person.Person;
public class Screenplay extends Person{
	private int id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Screenplay(String name, Date date_of_birth, String country) {
		super(name, date_of_birth, country);
	}
	public String toString(){
		return id + super.toString();
	}
}
