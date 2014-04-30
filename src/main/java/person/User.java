package person;
import java.sql.Date;
public class User extends Person{
	
	private String email;
	private String password;
	private String username;
		
	public User(String name, Date date_of_birth, String country, String email, String password, String username) {
		super(name, date_of_birth, country);
		this.setEmail(email);
		this.setPassword(password);
		this.setUsername(username);
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
		return "Movie [username = " + username + " " + this.getName() + "]";
	}
}
