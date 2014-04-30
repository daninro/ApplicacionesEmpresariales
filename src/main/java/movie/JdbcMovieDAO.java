package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import person.Director;
import person.User;



public class JdbcMovieDAO {

	private DataSource datasource;
	
	public Movie insert(Movie movie){
		if(movie == null) return null;
		
		try{
			String query = "INSERT INTO movie (name, year, running_time, country, budget, box_office) VALUES (?, ?, ?, ?, ?, ?)";
			
			Connection connection = datasource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, movie.getName());
			statement.setInt(2, movie.getYear());
			statement.setInt(3, movie.getRunning_time());
			statement.setString(4, movie.getCountry());
			statement.setInt(5, movie.getBudget());
			statement.setInt(6, movie.getBox_office());
			statement.executeUpdate();
			ResultSet resultset = statement.getGeneratedKeys();
			
			if(resultset != null && resultset.next()){
				movie.setId(resultset.getInt(1));
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return movie;
		
	}

	public Movie findbyId(Integer id) {
		
		Movie m = null;
		try{
			
			Connection connection = datasource.getConnection();
			String query = "SELECT * FROM movie WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				m = new Movie(result.getString(2), result.getInt(3), result.getInt(4), result.getString(5), result.getInt(6), result.getInt(7));
				m.setId(result.getInt(1));
				
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}
		
		return m;
	}






	public List<Movie> findbyYear(Integer year) {
		List<Movie> m = new ArrayList<Movie>();
		try{
			
			Connection connection = datasource.getConnection();
			String query = "SELECT * FROM movie WHERE year = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, year);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				Movie movie = new Movie(result.getString(2), result.getInt(3), result.getInt(4), result.getString(5), result.getInt(6), result.getInt(7));
				movie.setId(result.getInt(1));
				m.add(movie);
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}
		
		return m;
	}



	public List<Movie> findbyCountry(String c) {
	
		List<Movie> m = new ArrayList<Movie>();
		try{
			
			Connection connection = datasource.getConnection();
			String query = "SELECT * FROM movie WHERE country = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, c);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				Movie movie = new Movie(result.getString(2), result.getInt(3), result.getInt(4), result.getString(5), result.getInt(6), result.getInt(7));
				movie.setId(result.getInt(1));
				m.add(movie);
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return m;
		
	}


	public Movie update(Movie movie) {
		if(movie == null) return null;
		Movie m = null;
		try{
			String query = "UPDATE movie SET name = ?, year = ?, running_time= ?, country = ?, budget = ?, box_office = ?"; 
			
			Connection connection = datasource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, movie.getName());
			statement.setInt(2, movie.getYear());
			statement.setInt(3, movie.getRunning_time());
			statement.setString(4, movie.getCountry());
			statement.setInt(5, movie.getBudget());
			statement.setInt(6, movie.getBox_office());
			statement.executeUpdate();
			
			m = findbyId(movie.getId());
			
			
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return m;
		
		
	}
	
	
	
public List<Movie> addMovietoWishlist(Movie movie, User u) {
		
		List<Movie> movies = null; 
		
		if(movie == null) return null;
		
		try{
			String query = "INSERT INTO wishlist (user_name, id) VALUES (?, ?)";
			
			Connection connection = datasource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, u.getName());
			statement.setInt(2, movie.getId());
			statement.executeUpdate();
			
			movies = getWishlistbyUser(u);
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return movies;
		
		
		
	}






	public List<Movie> getWishlistbyUser(User u) {
		
		List<Movie> movies = new ArrayList<Movie>();
		
		try{
			Connection connection = datasource.getConnection();
			
			String query = "SELECT id FROM  wishlist WHERE user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, u.getName());
			
			ResultSet result = statement.executeQuery();
			while(result.next()){
				movies.add(findbyId(result.getInt(1)));
			}
			
			
		}catch(SQLException e){}
		
		return movies;
		
		
	}






	public List<Movie> deleteMoviefromWishlistbyUser(Movie m, User u) {
		
		List<Movie> movies = null;
		
		try{
			Connection connection = datasource.getConnection();
			
			String query = "DELETE FROM  wishlist WHERE id = ? AND user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, m.getId());
			statement.setString(2, u.getName());
			statement.executeUpdate();
			
			movies = getWishlistbyUser(u);
			
			
		}catch(SQLException e){}
		
		return movies;
		
		
	}


	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public List<Movie> getAll() {
		List<Movie> m = new ArrayList<Movie>();
		try{
			
			Connection connection = datasource.getConnection();
			String query = "SELECT * FROM movie ";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				Movie movie = new Movie(result.getString(2), result.getInt(3), result.getInt(4), result.getString(5), result.getInt(6), result.getInt(7));
				movie.setId(result.getInt(1));
				m.add(movie);
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}
		
		return m;	
		
	}

	public List<Movie> deleteMovie(Movie m) {
		List<Movie> movies = null;
		
		try{
			Connection connection = datasource.getConnection();
			
			String query = "DELETE FROM  movie WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, m.getId());
			statement.executeUpdate();
			
			movies = getAll();
			
			
		}catch(SQLException e){}
		
		return movies;
		
	}

	
	
	
}
