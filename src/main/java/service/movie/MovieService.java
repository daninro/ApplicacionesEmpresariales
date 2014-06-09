package service.movie;

import java.sql.Date;
import java.util.List;

import movie.Movie;

import org.springframework.transaction.annotation.Transactional;

import screenplay.Screenplay;
import user.User;
import actor.Actor;
import director.Director;
import exceptions.OperationUncompletedException;

public interface MovieService {
	
	public List<Movie> getAllMovies() throws OperationUncompletedException;
	public Movie findMoviebyId(Integer id) throws OperationUncompletedException;
	public List<Movie> findMoviebyYear(Integer year) throws OperationUncompletedException;
	public List<Movie> findMoviebyCountry(String c) throws OperationUncompletedException;
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
	public Integer getMark(Movie m, User u) throws OperationUncompletedException;
	
	@Transactional
	public List<Movie> addWishlist(Movie m, User u);
	@Transactional
	public List<Movie> deleteMoviefromWishlist(Movie m, User u);
	public List<Movie> getWishlist(User u);
	
	@Transactional
	public Director addDirector(Director d);
	@Transactional
	public Actor addActor(Actor a);
	@Transactional
	public Screenplay addScreenplay(Screenplay sp);
	
	public Actor findActor(String name, Date date_of_birth);
	public Director findDiretor(String name, Date date_of_birth);
	public Screenplay findScreeplay(String name, Date date_of_birth);
	
	@Transactional
	public Actor deleteActor(Actor a);
	@Transactional
	public Actor deleteActorbykey(String name, Date date);
	@Transactional
	public Actor updateActor(Actor a);
	
	@Transactional
	public Director deleteDirector(Director d);
	@Transactional
	public Director deleteDirectorbykey(String name, Date date);
	@Transactional
	public Director updateDirector(Director d);
	
	@Transactional
	public Screenplay deleteScreenplay(Screenplay sp);
	@Transactional
	public Screenplay deleteScreenplaybykey(String name, Date date);
	@Transactional
	public Screenplay updateScreenplay(Screenplay sp);
}
