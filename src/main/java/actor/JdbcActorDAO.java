package actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.sql.DataSource;

public class JdbcActorDAO  implements ActorDAO{
	
	private DataSource datasource;
	
	public Actor insert(Actor a) {
		Actor actor = null;
		try{
			String query = "INSERT INTO actor (name, date_of_birth, country) VALUES (?, ?, ?)";
			
			Connection connection = datasource.getConnection();
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

	public Actor find(String name, Date date_of_birth) {
		Actor actor = null;
		try{
			
			Connection connection = datasource.getConnection();
			String query = "SELECT * FROM actor WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, date_of_birth);
			System.out.println(name+ " " + date_of_birth.toString());
			
			ResultSet result = statement.executeQuery();
			if(result.next()){
				
				actor = new Actor(result.getString(1), result.getDate(2), result.getString(3));
				
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}
		
		return actor;
	}
	
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

}
