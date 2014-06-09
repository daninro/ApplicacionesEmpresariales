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
	public List<Movie> addWishlist(Movie m, User u)throws OperationUncompletedException;;
	@Transactional
	public List<Movie> deleteMoviefromWishlist(Movie m, User u) throws OperationUncompletedException;
	public List<Movie> getWishlist(User u)throws OperationUncompletedException;
	
	@Transactional
	public Director addDirector(Director d)throws OperationUncompletedException;
	@Transactional
	public Actor addActor(Actor a)throws OperationUncompletedException;
	@Transactional
	public Screenplay addScreenplay(Screenplay sp)throws OperationUncompletedException;
	
	public Actor findActor(String name, Date date_of_birth)throws OperationUncompletedException;
	public Director findDiretor(String name, Date date_of_birth)throws OperationUncompletedException;
	public Screenplay findScreeplay(String name, Date date_of_birth)throws OperationUncompletedException;
	
	@Transactional
	public Actor deleteActor(Actor a)throws OperationUncompletedException;
	@Transactional
	public Actor deleteActorbykey(String name, Date date)throws OperationUncompletedException;
	@Transactional
	public Actor updateActor(Actor a)throws OperationUncompletedException;
	
	@Transactional
	public Director deleteDirector(Director d)throws OperationUncompletedException;
	@Transactional
	public Director deleteDirectorbykey(String name, Date date)throws OperationUncompletedException;
	@Transactional
	public Director updateDirector(Director d)throws OperationUncompletedException;
	
	@Transactional
	public Screenplay deleteScreenplay(Screenplay sp)throws OperationUncompletedException;
	@Transactional
	public Screenplay deleteScreenplaybykey(String name, Date date) throws OperationUncompletedException;
	@Transactional
	public Screenplay updateScreenplay(Screenplay sp)throws OperationUncompletedException;
}
