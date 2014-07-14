package movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
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
			String query = "INSERT INTO movie (name, year, country, avg, number_users, picture, id_director, director_name) VALUES (?, ?, ?,'0','0',?, ?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, movie.getName());
			statement.setInt(2, movie.getYear());
			statement.setString(3, movie.getCountry());
			statement.setString(4, movie.getImage());
			
				statement.setString(5, movie.getId_director());
				statement.setString(6, movie.getDirector());
		
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
			m = new Movie(result.getString(2), result.getInt(3), result.getString(4), result.getString(7));
				m.setId(result.getInt(1));
				m.setId_director(result.getString(8));
				m.setDirector(result.getString(9));
			}else{
				
				throw new MyNotFoundException("id no encontrado");
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
			String query = "UPDATE movie SET name = ?, year = ?, country = ? WHERE id = ?"; 
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, movie.getName());
			statement.setInt(2, movie.getYear());
			statement.setString(3, movie.getCountry());
			statement.setInt(4, movie.getId());
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
			//String query = "INSERT INTO wishlist (user_name, id) VALUES (?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
			String query = "UPDATE evaluate2 SET iswished = ? WHERE user_name = ? AND id = ?;" +  
					"INSERT INTO evaluate2(user_name, id, iswished)" + 
					"SELECT ?, ?, ? " +	"WHERE NOT EXISTS (SELECT * FROM evaluate2 WHERE user_name = ? AND  id = ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, true);
			statement.setString(2, u.getUsername());
			statement.setInt(3, movie.getId());
			statement.setString(4, u.getUsername());
			statement.setInt(5, movie.getId());
			statement.setBoolean(6, true);
			statement.setString(7, u.getUsername());
			statement.setInt(8, movie.getId());

			
			
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
			
		
		}
		return movies;
	}
	public List<Movie> getWishlistbyUsername(String user){
		List<Movie> movies = new ArrayList<Movie>();
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT id FROM  evaluate2 WHERE user_name = ? AND iswished = 'TRUE'";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				movies.add(findbyId(result.getInt(1)));
			}
			
		}catch(SQLException e){
			
			throw new RuntimeException(e); 
		} catch (MyNotFoundException e) {
			
		
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
	
	public List<Movie> deleteMoviefromWishlistbyUser(int m, String u) {
		List<Movie> movies = null;
		Connection connection = null;
	
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  evaluate2 WHERE id = ? AND user_name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, m);
			statement.setString(2, u);
			statement.executeUpdate();
			movies = getWishlistbyUsername(u);
			
		}catch(SQLException e){
			
			throw new RuntimeException(e);
		}
		return movies;
	}

	public void deleteMovie(Movie m) {
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  movie WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, m.getId());
			statement.executeUpdate();
			
		}catch(SQLException e){
			
			throw new RuntimeException(e);
		}
	}
	
	public void deleteMovie(Integer m) {
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "DELETE FROM  movie WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, m);
			statement.executeUpdate();
			
		}catch(SQLException e){
			
			throw new RuntimeException(e);
		}
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
				Movie movie = new Movie(result.getString(2), result.getInt(3), result.getString(4), result.getString(7));
				movie.setId(result.getInt(1));
				m.add(movie);
			}
			
		}catch(SQLException e){
			
			throw new RuntimeException(e);
		}
		return m;	
	}
	
	@Override
	public List<Movie> Filter(filter.Filter F, int page, int limit) {
		List<Movie> m = new ArrayList<Movie>();
		Connection connection = null;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query;
			if(F.getUsername() != null)
				query = F.getQueryByUser(F.getUsername()) + " ORDER BY name LIMIT "+limit+" OFFSET " + (limit * (page - 1));
			else
				query = F.getQuery() + " ORDER BY name LIMIT "+limit+" OFFSET " + (limit * (page - 1));
			System.out.println(query);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
				while(result.next()){
					System.out.println(result);
					Movie movie = new Movie(result.getString(2), result.getInt(3), result.getString(4), result.getString(7));;
					movie.setId(result.getInt(1));
					if(F.getUsername() != null){
						movie.setAvg(result.getInt(10));
						movie.setIsWishlist(result.getBoolean(11));
					}
					m.add(movie);
				}
		}catch(SQLException e){
				
			throw new RuntimeException(e);
		}
		return m;	
	}
	
	
	public List<Movie> last10(String username){
		List<Movie> m = new ArrayList<Movie>();
		Connection connection = null;
		try{			
			connection = DataSourceUtils.getConnection(datasource);
			//String query = "SELECT * FROM movie ORDER BY id DESC LIMIT 10; ";
				String query ="SELECT DISTINCT movie.*, evaluate2.calification, evaluate2.iswished " +
				"FROM movie LEFT JOIN evaluate2 " +
				"ON movie.id = evaluate2.id AND evaluate2.user_name = ? ORDER BY id DESC LIMIT 10";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				Movie movie = new Movie(result.getString(2), result.getInt(3), result.getString(4), result.getString(7));
				movie.setId(result.getInt(1));
				movie.setAvg(result.getInt(10));
				movie.setIsWishlist(result.getBoolean(11));
				m.add(movie);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return m;
	}

	//REVISAR QUEDE ACAAAAAA
	public List<Movie> top(int limit, String username){
		List<Movie> m = new ArrayList<Movie>();
		Connection connection = null;
		try{			
			connection = DataSourceUtils.getConnection(datasource);
			/*String query = "SELECT movie.*, AVG(evaluate.calification) as P " +
					"FROM evaluate, movie " +
					"WHERE movie.id = evaluate.id" +
					"GROUP BY(movie.id)" +
					"ORDER BY P DESC LIMIT 20";
			*/
			//String query = "SELECT * FROM movie ORDER BY avg DESC LIMIT 20";
			//String query = "SELECT * from movie WHERE id IN (SELECT id FROM evaluate WUERE user_name = ?) ORDER BY avg DESC LIMIT " + limit;
			String query = "SELECT movie.*, E.calification " +  
			"FROM movie, evaluate AS E " +
			"WHERE movie.id = E.id AND E.calification > 0 AND movie.id IN (SELECT id FROM evaluate AS E WHERE E.user_name = ?) "+ 
			"ORDER BY E.calification DESC LIMIT " + limit;
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				Movie movie = new Movie(result.getString(2), result.getInt(3), result.getString(4), result.getString(7));
			
				movie.setId(result.getInt(1));
				m.add(movie);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return m;
	}
	
	public void setGenre(String Genre, int id){
		Connection connection = null;
		try{
			String query = "INSERT INTO has (name, id) VALUES (?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, Genre);
			statement.setInt(2, id);
			
			System.out.println("antes de instertar");
			statement.executeUpdate();
			System.out.println("despues de instertar");
		}catch(SQLException e){
			System.out.println("aqui: " + e.toString());
			throw new RuntimeException(e);
		}
	}
	
	public List<String> getGenres(int id){
		Connection connection = null;
		List<String> l = new ArrayList<String>();
		try{
			String query = "SELECT name FROM has WHERE id = ?";
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				l.add(result.getString(1));
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return l;
	}
	
	
	
	
	public double corr(int id1, int id2){
		Connection connection;
		double toret;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT * FROM corr WHERE id1 = ? AND id2 = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id1);
			statement.setInt(2, id2);
			ResultSet result = statement.executeQuery();
			List<Integer> m = new ArrayList<Integer>();
			List<Integer> n = new ArrayList<Integer>();
			while(result.next()){
				m.add(result.getInt(4));
				n.add(result.getInt(5));
			}
			double[] m1 = new double[m.size()];
			double[] n1 = new double[n.size()];
			for(int i = 0; i < m.size(); i++){
				m1[i] = m.get(i);
				n1[i] = n.get(i);
			}
			PearsonsCorrelation correlation = new PearsonsCorrelation();
			if(m1.length > 1)
				toret = correlation.correlation(m1, n1);
			else
				toret = 0;
			//connection.close();
		}catch(SQLException e){
			System.out.println(e.toString());
			throw new RuntimeException(e);
		}
		return toret;
	}
	
	public double P(String username, int id){
		Connection connection;
		double toret = 0;
		try{
			connection = DataSourceUtils.getConnection(datasource);
			String query = "SELECT id, calification FROM evaluate2 WHERE user_name = ? AND calification > '0'";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			List<Integer> l_id = new ArrayList<Integer>();
			List<Integer> l_cal = new ArrayList<Integer>();
			while(result.next()){
				l_id.add(result.getInt(1));
				l_cal.add(result.getInt(2));
			}
			double sum = 0;
			double div = 0;
			for(int i = 0; i < l_id.size(); i++){
				double corr = corr(id, l_id.get(i));
				if(Double.isNaN(corr)) corr = 5;
				sum += corr * l_cal.get(i);
				div += Math.abs(corr);
			}
			if(!(div == 0))
				toret = sum / div;
			
		}catch(SQLException e){
			System.out.println(e.toString());
			throw new RuntimeException(e);
		}
		
		return Math.abs(toret);
	}
	
	public  void algorithm(){
		Connection connection = null;
		try{			
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE evaluate;");
			statement.executeUpdate();
			
			String query = "SELECT A.id, U.user_name " +
			"FROM (SELECT DISTINCT id FROM evaluate2) AS A, (SELECT DISTINCT user_name FROM evaluate2) AS U, movie " +
			"WHERE "+
			"'0'= ( "+
			"SELECT COUNT(id) "+
			"FROM evaluate2 "+
			"WHERE id = A.id AND user_name = U.user_name) AND movie.id = A.id";
			PreparedStatement statement2 = connection.prepareStatement(query);
			ResultSet result = statement2.executeQuery();
			while(result.next()){
				recomendInsert(result.getString(2), result.getInt(1), P(result.getString(2), result.getInt(1)));
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	public void recomendInsert(String username, int id, double calification){
		if(calification == 0) return;
		
		Connection connection = null;
		try{
			
			String query = "INSERT INTO evaluate (user_name, id, calification) VALUES (?, ?, ?)";
			connection = DataSourceUtils.getConnection(datasource);
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setInt(2, id);
			statement.setDouble(3, calification);
			
			statement.executeUpdate();
		}catch(SQLException e){
			System.out.println("recomendinsert");
		}
	}

	

}
