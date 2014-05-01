package person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import movie.Movie;

public class JdbcUserDAO{
	
	private DataSource datasource;
	
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public Integer setMarkbyUser(Movie m, Integer mark, User u) {
		
		try{
			Connection connection = datasource.getConnection();
			
			String query = "UPDATE evaluate SET calification = ? WHERE user_name = ? AND id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, mark);
			statement.setString(2, u.getName());
			statement.setInt(3, m.getId());
			statement.executeUpdate();
			
			
			
		}catch(SQLException e){}
		
		return mark;
	}

	public Integer getMarkbyUser(Movie m, User u) {
	
		Integer n=null;
		
		try{
			Connection connection = datasource.getConnection();
			
			String query = "SELECT calification FROM  evaluate WHERE user_name = ? AND id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, u.getName());
			statement.setInt(2, m.getId());
			ResultSet result = statement.executeQuery();
			if(result.next()){
				n=new Integer(result.getInt(1));
			}
			
			
		}catch(SQLException e){}
		
		return n;
	}

	public User insert(User u) {
		User user = null;
		try{
			Connection connection = datasource.getConnection();
			
			String query = "INSERT INTO user1(user_name, password, name, date_of_birth, country, email) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getPassword());
			statement.setString(3, u.getName());
			statement.setDate(4, u.getDate_of_birth());
			statement.setString(5, u.getCountry());
			statement.setString(6, u.getEmail());
			System.out.println("sdsfg");
			statement.executeUpdate();
			System.out.println("sdsfg");
			user = getUser(u.getUsername());
			System.out.println(user);
		}catch(SQLException e){ System.out.println(e);}
		return user;
	}

	public User getUser(String username) {
		User user = null;
		
		try{
			Connection connection = datasource.getConnection();
			
			String query = "SELECT * FROM  user1 WHERE user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			System.out.println("asf");
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if(result.next()){
			
				user = new User(result.getString(1),result.getDate(4) , result.getString(5), result.getString(6), result.getString(2), result.getString(3));
			}
		}catch(SQLException e){}
		
		return user;
	}

	public User update(User u) {
		User user = null;
		try{
			Connection connection = datasource.getConnection();
			
			String query = "UPDATE user SET password = ?, name = ?, date_of_birdth = ?, country = ?, email = ? WHERE user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			
			statement.setString(1, u.getPassword());
			statement.setString(2, u.getName());
			statement.setDate(3, u.getDate_of_birth());
			statement.setString(4, u.getCountry());
			statement.setString(5, u.getEmail());
			statement.setString(6, u.getUsername());
			statement.executeUpdate();
			
			user = getUser(u.getUsername());
			
		}catch(SQLException e){}
		
		return user;
	}
	
	public User delete(User u) {
		User user = null;
		try{
			Connection connection = datasource.getConnection();
			
			String query = "DELETE FROM user1 WHERE user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, u.getUsername());
			
			/*statement.setString(1, u.getPassword());
			statement.setString(2, u.getName());
			statement.setDate(3, u.getDate_of_birth());
			statement.setString(4, u.getCountry());
			statement.setString(5, u.getEmail());
			statement.setString(6, u.getUsername());*/
			
			statement.executeUpdate();
			
			user = getUser(u.getUsername());
			
		}catch(SQLException e){}
		
		return user;
		
		
	}

	
}
