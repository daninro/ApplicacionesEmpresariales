package screenplay;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;



public class JdbcScreenplayDAO implements ScreenplayDAO{

	private DataSource datasource;
	
	public Screenplay insert(Screenplay sp) {
		
		Screenplay screenplay = null;
		Connection connection = null;
		try{
			String query = "INSERT INTO screenplay (name, date_of_birth, country) VALUES (?, ?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, sp.getName());
			statement.setDate(2, sp.getDate_of_birth());
			statement.setString(3, sp.getCountry());
			statement.executeUpdate();
		
			screenplay = find(sp.getName(), sp.getDate_of_birth());
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return screenplay;
	}

	public Screenplay find(String name, Date date_of_birth) {
		
		Screenplay sp = null;
		Connection connection = null;
		try{
			
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM screenplay WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, date_of_birth);
			
			ResultSet result = statement.executeQuery();
			if(result.next()){
			
				sp = new Screenplay(result.getString(1), result.getDate(2), result.getString(3));
				
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}
		
		return sp;
		
	}
	
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
	
	
	public Screenplay delete(Screenplay sp) {
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  screenplay WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, sp.getName());
			statement.setDate(2, sp.getDate_of_birth());
			statement.executeUpdate();
			
		}catch(SQLException e){}
		return sp;
	}

public Screenplay deletebykey(String name, Date date) {
	Screenplay sp= find(name, date);
		try{
			Connection connection = datasource.getConnection();
			String query = "DELETE FROM  screenplay WHERE name = ? AND date_of_birth = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setDate(2, date);
			statement.executeUpdate();
			
		}catch(SQLException e){}
		return sp;
	}

public Screenplay update(Screenplay sp) {
	Screenplay screenplay= null;
	try{
		String query = "UPDATE screenplay SET country = ? WHERE name = ? AND date_of_birth = ?"; 
		Connection connection = datasource.getConnection();
		PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, sp.getCountry());
		statement.setString(2, sp.getName());
		statement.setDate(3, sp.getDate_of_birth());
		statement.executeUpdate();
		screenplay = find(sp.getName(), sp.getDate_of_birth());
	}catch(SQLException e){
		throw new RuntimeException(e);
	}
	return screenplay;
	
}
	
	

}
