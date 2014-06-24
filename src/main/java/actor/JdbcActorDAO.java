package actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
			String query = "INSERT INTO actor (name, id_name) VALUES (?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, a.getName());
			statement.setString(2, a.getId_name());
			statement.executeUpdate();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return a;
	}

	public Actor find(String name) throws MyNotFoundException {
		Actor actor = null;
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM actor WHERE id_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				actor = new Actor(result.getString(2), result.getString(3), result.getInt(4));
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
		find(a.getId_name());
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  actor WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, a.getId_name());
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return a;
	}
	
	public Actor delete(String id) throws MyNotFoundException {
		Connection connection = null;
		Actor a = find(id);
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  actor WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return a;
	}

	

	public Actor update(Actor a) throws MyNotFoundException {
		Actor actor = null;
		actor = find(a.getId_name());
		Connection connection = null;
		try{
			actor = null;
			String query = "UPDATE actor SET name = ?  WHERE id_name = ?"; 
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, a.getName());
			statement.setString(2, a.getId_name());
			statement.executeUpdate();
			actor = find(a.getId_name());
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return actor;
	}
	
	
	public List<Actor> getActorsOfMovie(int id) throws MyNotFoundException{
		List<Actor> actor = new ArrayList<Actor>();
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM perform WHERE id = ? ORDER BY ranking";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				actor.add(new Actor(result.getString(3), result.getString(2), result.getInt(4)));
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return actor;
	}	
	
	
}
