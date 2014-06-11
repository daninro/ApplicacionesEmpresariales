package movie;

import java.util.List;

import exceptions.MyNotFoundException;

import user.User;

public interface MovieDAO {
	public Movie insert(Movie movie) throws MyNotFoundException;
	public Movie findbyId(Integer id) throws MyNotFoundException;
	public List<Movie> findbyYear(Integer year);
	public List<Movie> findbyCountry(String c);
	public Movie update(Movie movie) throws MyNotFoundException;
	public List<Movie> addMovietoWishlist(Movie movie, User u);
	public List<Movie> getWishlistbyUser(User u) throws MyNotFoundException;
	public List<Movie> deleteMoviefromWishlistbyUser(Movie m, User u);
	public List<Movie> getAll();
	public List<Movie> deleteMovie(Movie m);
	public List<Movie> deleteMovie(Integer m);
	public List<Movie> searchByName(String name);
	public List<Movie> last10();
	public List<Movie> top20();
	public List<Movie> getAll(int page, int i);
	
	
}
