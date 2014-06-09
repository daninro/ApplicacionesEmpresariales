package director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;



public class JdbcDirectorDAO implements DirectorDAO{
	
	private DataSource datasource;
	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
	public Director insert(Director d) {
		Director director = null;
		Connection connection = null;
		try{
			String query = "INSERT INTO director (name, date_of_birth, country) VALUES (?, ?, ?)";
			
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, d.getName());
			statement.setDate(2, d.getDate_of_birth());
			statement.setString(3, d.getCountry());
			statement.executeUpdate();
		
			director = find(d.getName(), d.getDate_of_birth());
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return director;
	}

	public Director find(String name, Date date_of_birth) {
		Director director = null;
		Connection connection = null;
		try{
			
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM director WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, date_of_birth);
			
			ResultSet result = statement.executeQuery();
			if(result.next()){
			
				director = new Director(result.getString(1), result.getDate(2), result.getString(3));
				
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}
		
		return director;
		
	}

	public Director delete(Director d) {
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  director WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, d.getName());
			statement.setDate(2, d.getDate_of_birth());
			statement.executeUpdate();
			
		}catch(SQLException e){
			throw new RuntimeException();
		}
		return d;
	}

public Director deletebykey(String name, Date date) {
	Director d = find(name, date);
	Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  director WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, date);
			statement.executeUpdate();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return d;
	}

public Director update(Director d) {
	Director director = null;
	Connection connection = null;
	try{
		String query = "UPDATE director SET country = ? WHERE name = ? AND date_of_birth = ?"; 
		connection = DataSourceUtils.getConnection(datasource);
		PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, d.getCountry());
		statement.setString(2, d.getName());
		statement.setDate(3, d.getDate_of_birth());
		statement.executeUpdate();
		director = find(d.getName(), d.getDate_of_birth());
	}catch(SQLException e){
		throw new RuntimeException(e);
	}
	return director;
	
}
	
}
