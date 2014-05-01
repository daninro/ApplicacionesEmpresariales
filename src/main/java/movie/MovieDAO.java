package movie;

import java.util.List;

import person.User;

public interface MovieDAO {
	public Movie insert(Movie movie);
	public Movie findbyId(Integer id);
	public List<Movie> findbyYear(Integer year);
	public List<Movie> findbyCountry(String c);
	public Movie update(Movie movie);
	public List<Movie> addMovietoWishlist(Movie movie, User u);
	public List<Movie> getWishlistbyUser(User u);
	public List<Movie> deleteMoviefromWishlistbyUser(Movie m, User u);
	public List<Movie> getAll();
	public List<Movie> deleteMovie(Movie m);
	public List<Movie> deleteMovie(Integer m);
}
