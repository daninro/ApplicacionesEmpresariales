package movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;

import exceptions.MyNotFoundException;
import user.User;



public class JdbcMovieDAO implements MovieDAO{

	private DataSource datasource;
	
	/** accesors and mutators**/
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	/** accesors and mutators
	 * @throws MyNotFoundException **/
	
	public Movie insert(Movie movie) throws MyNotFoundException{
		if(movie == null) return null;
		Connection connection = null;
		try{
			String query = "INSERT INTO movie (name, year, running_time, country, budget, box_office) VALUES (?, ?, ?, ?, ?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
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
			}else{
				throw new MyNotFoundException("no pude generar la clave :(");
				
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return movie;
	}

	public Movie findbyId(Integer id) throws MyNotFoundException {
		Movie m = null;
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM movie WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				m = new Movie(result.getString(2), result.getInt(3), result.getInt(4), result.getString(5), result.getInt(6), result.getInt(7));
				m.setId(result.getInt(1));
			}else{
				try {connection.close();} catch (SQLException | NullPointerException e2) {}
				throw new MyNotFoundException("id no encontrado");
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return m;
	}

	public List<Movie> findbyYear(Integer year) {
		List<Movie> m = new ArrayList<Movie>();
		Connection connection = null;
		try{
			
			connection = DataSourceUtils.getConnection(datasource);
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
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
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

	public Movie update(Movie movie) throws MyNotFoundException {
		if(movie == null) return null;
		Movie m = null;
		Connection connection = null;
		try{
			String query = "UPDATE movie SET name = ?, year = ?, running_time= ?, country = ?, budget = ?, box_office = ? WHERE id = ?"; 
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, movie.getName());
			statement.setInt(2, movie.getYear());
			statement.setInt(3, movie.getRunning_time());
			statement.setString(4, movie.getCountry());
			statement.setInt(5, movie.getBudget());
			statement.setInt(6, movie.getBox_office());
			statement.setInt(7, movie.getId());
			statement.executeUpdate();
			m = findbyId(movie.getId());
			
		}catch(SQLException e){
			
			throw new RuntimeException(e);
			
		}catch(MyNotFoundException e){
			
			e.addDetails("actualizacion fallida");
			throw e;
		
		}
		return m;
	}
	
	public List<Movie> addMovietoWishlist(Movie movie, User u) {
		List<Movie> movies = null; 
		if(movie == null) return null;
		Connection connection = null;
		try{
			String query = "INSERT INTO wishlist (user_name, id) VALUES (?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
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
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT id FROM  wishlist WHERE user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, u.getName());
			ResultSet result = statement.executeQuery();
			while(result.next()){
				movies.add(findbyId(result.getInt(1)));
			}
			
		}catch(SQLException e){
			
			throw new RuntimeException(e); 
		} catch (MyNotFoundException e) {
			
			System.out.println("getWishlistbyUser: no deberia entrar aqui jamás");
		}
		return movies;
	}

	public List<Movie> deleteMoviefromWishlistbyUser(Movie m, User u) {
		List<Movie> movies = null;
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  wishlist WHERE id = ? AND user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, m.getId());
			statement.setString(2, u.getName());
			statement.executeUpdate();
			movies = getWishlistbyUser(u);
			
		}catch(SQLException e){
			
			throw new RuntimeException(e);
		}
		return movies;
	}

	public List<Movie> getAll() {
		List<Movie> m = new ArrayList<Movie>();
		Connection connection = null;
		try{
			
			connection = DataSourceUtils.getConnection(datasource);
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
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  movie WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, m.getId());
			statement.executeUpdate();
			movies = getAll();
			
		}catch(SQLException e){
			
			throw new RuntimeException(e);
		}
		return movies;
	}
	
	public List<Movie> deleteMovie(Integer m) {
		List<Movie> movies = null;
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  movie WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, m);
			statement.executeUpdate();
			movies = getAll();
			
		}catch(SQLException e){
			
			throw new RuntimeException(e);
		}
		return movies;
	}

	@Override
	public List<Movie> searchByName(String name) {
		List<Movie> m = new ArrayList<Movie>();
		Connection connection = null;
		try{
			
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM movie WHERE name like ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + name + "%");
			ResultSet result = statement.executeQuery();
			while(result.next()){
				Movie movie = new Movie(result.getString(2), result.getInt(3), result.getInt(4), result.getString(5), result.getInt(6), result.getInt(7));
				movie.setId(result.getInt(1));
				m.add(movie);
			}
			//connection.close();
		}catch(SQLException e){
			//try {connection.close();} catch (SQLException | NullPointerException e2) {}
			throw new RuntimeException(e);
		}
		return m;	
	}
	
	public List<Movie> last10(){
		List<Movie> m = new ArrayList<Movie>();
		Connection connection = null;
		try{			
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM movie ORDER BY id DESC LIMIT 10; ";
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

	
	//REVISAR QUEDE ACAAAAAA
	public List<Movie> top20(){
		List<Movie> m = new ArrayList<Movie>();
		Connection connection = null;
		try{			
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT movie.*, AVG(evaluate.calification) as P " +
					"FROM evaluate, movie " +
					"WHERE movie.id = evaluate.id" +
					"GROUP BY(movie.id)" +
					"ORDER BY P DESC LIMIT 20";
			
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
}
