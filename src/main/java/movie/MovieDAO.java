package movie;

import java.util.List;

import exceptions.MyNotFoundException;

import user.User;

public interface MovieDAO {
	public Movie insert(Movie movie) throws MyNotFoundException;
	public Movie findbyId(Integer id) throws MyNotFoundException;
	public Movie update(Movie movie) throws MyNotFoundException;
	public List<Movie> addMovietoWishlist(Movie movie, User u);
	public List<Movie> getWishlistbyUser(User u) throws MyNotFoundException;
	public List<Movie> deleteMoviefromWishlistbyUser(Movie m, User u);
	public List<Movie> deleteMoviefromWishlistbyUser(int m, String u);
	public void deleteMovie(Movie m);
	public void deleteMovie(Integer m);
	public List<Movie> searchByName(String name);
	public List<Movie> last10();
	public List<Movie> top20();
	public List<Movie> Filter(filter.Filter F, int page, int limit);
	public List<Movie> getWishlistbyUsername(String user);
	
	
}
