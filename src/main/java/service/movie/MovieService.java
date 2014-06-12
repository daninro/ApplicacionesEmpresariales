package service.movie;

import java.util.List;

import movie.Movie;

import org.springframework.transaction.annotation.Transactional;

import user.User;
import exceptions.OperationUncompletedException;

public interface MovieService {
	@Transactional
	public List<Movie> getAllMovies() throws OperationUncompletedException;
	@Transactional
	public Movie findMoviebyId(Integer id) throws OperationUncompletedException;
	@Transactional
	public List<Movie> findMoviebyYear(Integer year) throws OperationUncompletedException;
	@Transactional
	public List<Movie> findMoviebyCountry(String c) throws OperationUncompletedException;
	@Transactional
	public List<Movie> searchByName(String name) throws OperationUncompletedException;
	@Transactional
	public Movie addMovie(Movie m) throws OperationUncompletedException;
	@Transactional
	public Movie updateMovie(Movie m) throws OperationUncompletedException;
	@Transactional
	public List<Movie> deleteMovie(Movie m) throws OperationUncompletedException;
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
	public List<Movie> getWishlist(User u)throws OperationUncompletedException;
	@Transactional
	public List<Movie> top20()throws OperationUncompletedException;
	@Transactional
	public List<Movie> last10()throws OperationUncompletedException;
	@Transactional
	public List<Movie> getAllMovies(int page, int i, String username) throws OperationUncompletedException;
	
	
	
	
}
