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
		Actor actor = null;
		Connection connection = null;
		try{
			String query = "INSERT INTO actor (name, date_of_birth, country) VALUES (?, ?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, a.getName());
			statement.setDate(2, a.getDate_of_birth());
			statement.setString(3, a.getCountry());
			
			System.out.println(a.getName());
			System.out.println(statement.executeUpdate());
			System.out.println(a.getName());
			
			actor = find(a.getName(), a.getDate_of_birth());
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return actor;
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
			System.out.println(name+ " " + date_of_birth.toString());
			
			ResultSet result = statement.executeQuery();
			if(result.next()){
				
				actor = new Actor(result.getString(1), result.getDate(2), result.getString(3));
				
			}else{
				throw new MyNotFoundException("no se encontro el actor :(");
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return actor;
	}
	

	public Actor delete(Actor a) {
		Connection connection = null;
		try{
			
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  actor WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, a.getName());
			statement.setDate(2, a.getDate_of_birth());
			statement.executeUpdate();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return a;
	}

public Actor deletebykey(String name, Date date) throws MyNotFoundException {
		Actor a = null;
		a = find(name, date);
		Connection connection = null;
			
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  actor WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, date);
			statement.executeUpdate();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return a;
	}

	public Actor update(Actor a) throws MyNotFoundException {

		Actor actor = null;
		actor = find(a.getName(), a.getDate_of_birth());
		Connection connection = null;
		try{
			actor = null;
			String query = "UPDATE actor SET country = ? WHERE name = ? AND date_of_birth = ?"; 
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, a.getCountry());
			statement.setString(2, a.getName());
			statement.setDate(3, a.getDate_of_birth());
			statement.executeUpdate();
		
			actor = find(a.getName(), a.getDate_of_birth());
		
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return actor;
	}
}
