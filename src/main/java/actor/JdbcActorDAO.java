package actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import exceptions.MyNotFoundException;



public class JdbcActorDAO  implements ActorDAO{
	
	private DataSource datasource;
	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
	public Actor insert(Actor a) throws MyNotFoundException {
		Connection connection = null;
		try{
			String query = "INSERT INTO actor (name, date_of_birth, country) VALUES (?, ?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, a.getName());
			statement.setDate(2, a.getDate_of_birth());
			statement.setString(3, a.getCountry());
			statement.executeUpdate();
			ResultSet resultset = statement.getGeneratedKeys();
			if(resultset != null && resultset.next()){
				a.setId(resultset.getInt(1));
			}else{
				throw new MyNotFoundException("no pude generar la clave :(");
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return a;
	}

	public Actor find(String name, Date date_of_birth) throws MyNotFoundException {
		Actor actor = null;
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM actor WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, date_of_birth);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				actor = new Actor(result.getString(2), result.getDate(3), result.getString(4));
				actor.setId(result.getInt(1));
			}else{
				throw new MyNotFoundException("no se encontro el actor :(");
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return actor;
	}
	

	public Actor find(int id) throws MyNotFoundException {
		Actor actor = null;
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				actor = new Actor(result.getString(2), result.getDate(3), result.getString(4));
				actor.setId(result.getInt(1));
			}else{
				throw new MyNotFoundException("no se encontro el actor :(");
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return actor;
	}
	
	
	public Actor delete(Actor a) throws MyNotFoundException {
		Connection connection = null;
		find(a.getId());
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  actor WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, a.getId());
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return a;
	}
	
	public Actor delete(int id) throws MyNotFoundException {
		Connection connection = null;
		Actor a = find(id);
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  actor WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return a;
	}

	

	public Actor update(Actor a) throws MyNotFoundException {

		Actor actor = null;
		actor = find(a.getId());
		Connection connection = null;
		try{
			actor = null;
			String query = "UPDATE actor SET country = ?,  name = ?, date_of_birth = ? WHERE id = ?"; 
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, a.getCountry());
			statement.setString(2, a.getName());
			statement.setDate(3, a.getDate_of_birth());
			statement.setInt(4, a.getId());
			statement.executeUpdate();
			actor = find(a.getName(), a.getDate_of_birth());
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return actor;
	}
}
