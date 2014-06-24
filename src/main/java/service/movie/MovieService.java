package service.movie;

import java.util.List;

import movie.Movie;

import org.springframework.transaction.annotation.Transactional;

import user.User;
import exceptions.OperationUncompletedException;

public interface MovieService {
	@Transactional
	public Movie findMoviebyId(Integer id) throws OperationUncompletedException;
	@Transactional
	public List<Movie> searchByName(String name) throws OperationUncompletedException;
	@Transactional
	public Movie addMovie(Movie m) throws OperationUncompletedException;
	@Transactional
	public Movie updateMovie(Movie m) throws OperationUncompletedException;
	@Transactional
	public void deleteMovie(Movie m) throws OperationUncompletedException;
	@Transactional
	public Integer setMark(Movie m, Integer mark, User u) throws OperationUncompletedException;
	@Transactional
	public Integer setMark(int movieId, Integer mark, String username) throws OperationUncompletedException;
	@Transactional
	public Integer getMark(Movie m, User u) throws OperationUncompletedException;
	@Transactional
	public List<Movie> addWishlist(Movie m, User u)throws OperationUncompletedException;;
	@Transactional
	public List<Movie> deleteMoviefromWishlist(Movie m, User u) throws OperationUncompletedException;
	@Transactional
	public List<Movie> deleteMoviefromWishlist(int m, String u) throws OperationUncompletedException;
	@Transactional
	public List<Movie> getWishlist(User u)throws OperationUncompletedException;
	@Transactional
	public List<Movie> top20()throws OperationUncompletedException;
	@Transactional
	public List<Movie> last10()throws OperationUncompletedException;
	@Transactional
	public List<Movie> FilterMovies(filter.Filter filter, int page, int limit) throws OperationUncompletedException;
	@Transactional
	public List<Movie> getWishlistbyUsername(String s) throws OperationUncompletedException;
	@Transactional
	public void executeAlgorithm();
	@Transactional
	public List<String> getGenres(int id);
	@Transactional
	public void setGenres(String genre, int id);
}
