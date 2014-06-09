package movie;

import java.util.List;

import exceptions.MyNotFoundExeption;

import user.User;

public interface MovieDAO {
	public Movie insert(Movie movie);
	public Movie findbyId(Integer id) throws MyNotFoundExeption;
	public List<Movie> findbyYear(Integer year);
	public List<Movie> findbyCountry(String c);
	public Movie update(Movie movie) throws MyNotFoundExeption;
	public List<Movie> addMovietoWishlist(Movie movie, User u);
	public List<Movie> getWishlistbyUser(User u) throws MyNotFoundExeption;
	public List<Movie> deleteMoviefromWishlistbyUser(Movie m, User u);
	public List<Movie> getAll();
	public List<Movie> deleteMovie(Movie m);
	public List<Movie> deleteMovie(Integer m);
	public List<Movie> searchByName(String name);
}
