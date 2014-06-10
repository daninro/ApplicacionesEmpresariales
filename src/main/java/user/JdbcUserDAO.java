package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import movie.Movie;
import exceptions.MyNotFoundException;

public class JdbcUserDAO implements UserDAO{
	
	private DataSource datasource;
	
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public Integer setMarkbyUser(Movie m, Integer mark, User u) {
		Connection connection;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "UPDATE evaluate SET calification = ? WHERE user_name = ? AND id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, mark);
			statement.setString(2, u.getName());
			statement.setInt(3, m.getId());
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return mark;
	}

	public Integer getMarkbyUser(Movie m, User u) throws MyNotFoundException {
		Integer n=null;
		Connection connection;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT calification FROM  evaluate WHERE user_name = ? AND id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, u.getName());
			statement.setInt(2, m.getId());
			ResultSet result = statement.executeQuery();
			if(result.next()){
				n=new Integer(result.getInt(1));
			}else{
				throw new MyNotFoundException("no se encontro calificacion");
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return n;
	}

	public User insert(User u) {
		User user = null;
		Connection connection;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			
			String query = "INSERT INTO user1(user_name, password, name, date_of_birth, country, email, is_admin) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getPassword());
			statement.setString(3, u.getName());
			statement.setDate(4, u.getDate_of_birth());
			statement.setString(5, u.getCountry());
			statement.setString(6, u.getEmail());
			statement.setBoolean(7,  u.isAdmin());
			statement.executeUpdate();
			user = getUser(u.getUsername());
			connection.close();
		}catch(SQLException e){ 
			throw new RuntimeException(e);
		}catch (MyNotFoundException e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	public User getUser(String username) throws MyNotFoundException {
		User user = null;
		Connection connection;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			
			String query = "SELECT * FROM  user1 WHERE user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				user = new User(result.getString(3),result.getDate(4) , result.getString(5), result.getString(6), result.getString(2), result.getString(1), result.getBoolean(7));
			}else{
				throw new MyNotFoundException("no se encontro el usuario");
			}
		}catch(SQLException e){
			throw new RuntimeException();
		}
		
		return user;
	}

	public User update(User u) throws MyNotFoundException {
		User user = null;
		Connection connection;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "UPDATE user1 SET password = ?, name = ?, date_of_birth = ?, country = ?, email = ? WHERE user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, u.getPassword());
			statement.setString(2, u.getName());
			statement.setDate(3, u.getDate_of_birth());
			statement.setString(4, u.getCountry());
			statement.setString(5, u.getEmail());
			statement.setString(6, u.getUsername());
			statement.executeUpdate();
			user = getUser(u.getUsername());
		}catch(SQLException e){
			throw new RuntimeException();
		}
		return user;
	}
	
	public User delete(User u) throws MyNotFoundException {
		User user = null;
		user = getUser(u.getUsername());
		Connection connection;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM user1 WHERE user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, u.getUsername());
			statement.executeUpdate();
			user = getUser(u.getUsername());
		}catch(SQLException e){
			throw new RuntimeException();
		}
		return user;
			
	}

	@Override
	public Integer setMarkbyUser(int movieId, Integer mark, String username) {
		Connection connection;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "UPDATE evaluate SET calification = ? WHERE user_name = ? AND id = ?;" +  
			"INSERT INTO evaluate(user_name, id, calification)" + 
			"SELECT ?, ?, ?" +	"WHERE NOT EXISTS (SELECT * FROM evaluate WHERE id = ? AND user_name = ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, mark);
			statement.setString(2, username);
			statement.setInt(3, movieId);
			statement.setString(4, username);
			statement.setInt(5, movieId);
			statement.setInt(6, mark);
			statement.setInt(7, movieId);
			statement.setString(8, username);
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException();
		}
		return mark;
	}
	
	public int getMarkbyUser(int m, String u) throws MyNotFoundException {
		int n=0;
		Connection connection;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT calification FROM  evaluate WHERE user_name = ? AND id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, u);
			statement.setInt(2, m);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				n = result.getInt(1);
			}else{ 
				throw new MyNotFoundException("no se enonctro calificaioj");
			}
		}catch(SQLException e){
			throw new RuntimeException();
		}
		
		return n;
	}
	
}
