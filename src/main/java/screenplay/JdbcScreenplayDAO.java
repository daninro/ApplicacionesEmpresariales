package screenplay;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import exceptions.MyNotFoundException;



public class JdbcScreenplayDAO implements ScreenplayDAO{

	private DataSource datasource;
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
	
	public Screenplay insert(Screenplay sp) throws MyNotFoundException {
			Connection connection = null;
			try{
				String query = "INSERT INTO screenplay (name, date_of_birth, country) VALUES (?, ?, ?)";
				connection = DataSourceUtils.getConnection(datasource);
				PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, sp.getName());
				statement.setDate(2, sp.getDate_of_birth());
				statement.setString(3, sp.getCountry());
				statement.executeUpdate();
				ResultSet resultset = statement.getGeneratedKeys();
				if(resultset != null && resultset.next()){
					sp.setId(resultset.getInt(1));
				}else{
					throw new MyNotFoundException("no se pudo generar la clave");
				}
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
			return sp;
		}

		public Screenplay find(String name, Date date_of_birth) throws MyNotFoundException {
			Screenplay screenplay = null;
			Connection connection = null;
			try{
				connection = DataSourceUtils.getConnection(datasource);
				String query = "SELECT * FROM screenplay WHERE name = ? AND date_of_birth = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, name);
				statement.setDate(2, date_of_birth);
				ResultSet result = statement.executeQuery();
				if(result.next()){
					screenplay = new Screenplay(result.getString(2), result.getDate(3), result.getString(4));
					screenplay.setId(result.getInt(1));
				}else{
					throw new MyNotFoundException("no se encontro el guionista");
				}
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
			return screenplay;
		}
		

		public Screenplay find(int id) throws MyNotFoundException {
			Screenplay screenplay = null;
			Connection connection = null;
			try{
				connection = DataSourceUtils.getConnection(datasource);
				String query = "SELECT * FROM screenplay WHERE id = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				if(result.next()){
					screenplay = new Screenplay(result.getString(2), result.getDate(3), result.getString(4));
					screenplay.setId(result.getInt(1));
				}else{
					throw new MyNotFoundException("no se encontro el actor :(");
				}
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
			return screenplay;
		}
		
		
		public Screenplay delete(Screenplay sp) throws MyNotFoundException {
			Connection connection = null;
			find(sp.getId());
			try{
				connection = DataSourceUtils.getConnection(datasource);
				String query = "DELETE FROM  screenplay WHERE id = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, sp.getId());
				statement.executeUpdate();
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
			return sp;
		}
		
		public Screenplay delete(int id) throws MyNotFoundException {
			Connection connection = null;
			Screenplay sp = find(id);
			try{
				connection = DataSourceUtils.getConnection(datasource);
				String query = "DELETE FROM  screenplay WHERE id = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				statement.executeUpdate();
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
			return sp;
		}

		

		public Screenplay update(Screenplay sp) throws MyNotFoundException {

			Screenplay screenplay = null;
			screenplay = find(sp.getId());
			Connection connection = null;
			try{
				screenplay = null;
				String query = "UPDATE screenplay SET country = ?,  name = ?, date_of_birth = ? WHERE id = ?"; 
				connection = DataSourceUtils.getConnection(datasource);
				PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, sp.getCountry());
				statement.setString(2, sp.getName());
				statement.setDate(3, sp.getDate_of_birth());
				statement.setInt(4, sp.getId());
				statement.executeUpdate();
				screenplay = find(sp.getName(), sp.getDate_of_birth());
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
			return screenplay;
		}
	}
