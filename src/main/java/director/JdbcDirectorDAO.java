package director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;


import exceptions.MyNotFoundException;



public class JdbcDirectorDAO implements DirectorDAO{
	
	private DataSource datasource;
	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
	public Director insert(Director d) throws MyNotFoundException{
		Connection connection = null;
		try{
			String query = "INSERT INTO director (name, date_of_birth, country) VALUES (?, ?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, d.getName());
			statement.setDate(2, d.getDate_of_birth());
			statement.setString(3, d.getCountry());
			statement.executeUpdate();
			ResultSet resultset = statement.getGeneratedKeys();
		
			if(resultset != null && resultset.next()){
				d.setId(resultset.getInt(1));
			}
			else{
				throw new MyNotFoundException("No se pudo generar la clave");
			}
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return d;
	}

	public Director find(String name, Date date_of_birth)throws MyNotFoundException {
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
				director = new Director(result.getString(2), result.getDate(3), result.getString(4));
				director.setId(result.getInt(1));
			}else{
				throw new MyNotFoundException("no se encontro el director");
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return director;
		
	}

	public Director find(int id)throws MyNotFoundException {
		Director director = null;
		Connection connection = null;
		try{
			
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM director WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				director = new Director(result.getString(2), result.getDate(3), result.getString(4));
				director.setId(result.getInt(1));
			}else{
				throw new MyNotFoundException("no se encontro el director");
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return director;
		
	}
	
	public Director delete(Director d) throws MyNotFoundException  {
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  director WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, d.getId());
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return d;
	}

	public Director delete(int id) throws MyNotFoundException {
		Connection connection = null;
		Director d = find(id);
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  director WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return d;
	}

	

	public Director update(Director d) throws MyNotFoundException {

		Director director = null;
		director = find(d.getId());
		Connection connection = null;
		try{
			director = null;
			String query = "UPDATE director SET country = ?,  name = ?, date_of_birth = ? WHERE id = ?"; 
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, d.getCountry());
			statement.setString(2, d.getName());
			statement.setDate(3, d.getDate_of_birth());
			statement.setInt(4, d.getId());
			statement.executeUpdate();
			director = find(d.getName(), d.getDate_of_birth());
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return director;
	}
}
