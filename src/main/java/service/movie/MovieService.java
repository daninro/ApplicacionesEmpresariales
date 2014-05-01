package service.movie;

import java.sql.Date;
import java.util.List;

import movie.Movie;

import org.springframework.transaction.annotation.Transactional;

import screenplay.Screenplay;
import user.User;
import actor.Actor;
import director.Director;

public interface MovieService {
	
	public List<Movie> getAllMovies();
	public Movie findMoviebyId(Integer id);
	public List<Movie> findMoviebyYear(Integer year);
	public List<Movie> findMoviebyCountry(String c);
	@Transactional
	public Movie addMovie(Movie m);
	@Transactional
	public Movie updateMovie(Movie m);
	@Transactional
	public List<Movie> deleteMovie(Movie m);
	@Transactional
	public Integer setMark(Movie m, Integer mark, User u);
	public Integer getMark(Movie m, User u);
	@Transactional
	public List<Movie> addWishlist(Movie m, User u);
	public List<Movie> getWishlist(User u);
	@Transactional
	public List<Movie> deleteMoviefromWishlist(Movie m, User u);
	@Transactional
	public Director addDirector(Director d);
	@Transactional
	public Actor addActor(Actor a);
	@Transactional
	public Screenplay addScreenplay(Screenplay sp);
	public Actor findActor(String name, Date date_of_birth);
	public Director findDiretor(String name, Date date_of_birth);
	public Screenplay findScreeplay(String name, Date date_of_birth);
}
