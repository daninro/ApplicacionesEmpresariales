package user;
import java.sql.Date;

import person.Person;
public class User extends Person{
	
	private String email;
	private String password;
	private String username;
	private boolean isAdmin;	
	public User(String name, Date date_of_birth, String country, String email, String password, String username, boolean isAdmin) {
		super(name, date_of_birth, country);
		this.setEmail(email);
		this.setPassword(password);
		this.setUsername(username);
		this.isAdmin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString(){
		return "User [username = " + username + ", password = " + password + ", name = " + this.getName() + ", mail = " + email + ", date of birth = " + this.getDate_of_birth() + ", country = " + this.getCountry() + "]";
	}
	
	public boolean isAdmin(){
		return isAdmin;
	}
	
	public boolean getisAdmin(){
		return isAdmin;
	}
}
