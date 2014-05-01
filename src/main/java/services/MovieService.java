package services;

import java.util.List;

import movie.JdbcMovieDAO;
import movie.Movie;
import movie.MovieDAO;

import org.springframework.transaction.annotation.Transactional;

import person.Actor;
import person.Director;
import person.JdbcActorDAO;
import person.JdbcDirectorDAO;
import person.JdbcScreenplayDAO;
import person.JdbcUserDAO;
import person.Screenplay;
import person.User;
import java.sql.Date;

public class MovieService {
	
	private MovieDAO movieDAO;
	private JdbcUserDAO userDAO;
	private JdbcDirectorDAO directorDAO;
	public JdbcDirectorDAO getDirectorDAO() {
		return directorDAO;
	}

	public void setDirectorDAO(JdbcDirectorDAO directorDAO) {
		this.directorDAO = directorDAO;
	}

	public JdbcScreenplayDAO getScreenplayDAO() {
		return screenplayDAO;
	}

	public void setScreenplayDAO(JdbcScreenplayDAO screenplayDAO) {
		this.screenplayDAO = screenplayDAO;
	}

	public JdbcActorDAO getActorDAO() {
		return actorDAO;
	}

	public void setActorDAO(JdbcActorDAO actorDAO) {
		this.actorDAO = actorDAO;
	}

	private JdbcScreenplayDAO screenplayDAO;
	private JdbcActorDAO actorDAO;
	
	/*accesors y mutators*/
	
	
	public MovieDAO getMovieDAO() {
		return movieDAO;
	}
	
	public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	public JdbcUserDAO getUserDAO() {
		return userDAO;
	}

	
	public void setUserDAO(JdbcUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
	
	
	public List<Movie> getAllMovies(){
		List<Movie> movie = null;
		try{
			movie = movieDAO.getAll();
		} catch (RuntimeException e){}
		
		return movie;
	
		
	}
	public Movie findMoviebyId(Integer id){
		Movie movie = null;
		try{
			movie = movieDAO.findbyId(id);
		} catch (RuntimeException e){}
		
		return movie;
	}
	
	public List<Movie> findMoviebyYear(Integer year){
		List<Movie> movie = null;
		try{
			movie = movieDAO.findbyYear(year);
		} catch (RuntimeException e){}
		
		return movie;
	}
	
	public List<Movie> findMoviebyCountry(String c){
		List<Movie> movie = null;
		try{
			movie = movieDAO.findbyCountry(c);
		} catch (RuntimeException e){}
		
		return movie;
	}
	
	@Transactional
	public Movie addMovie(Movie m){
		try{
			movieDAO.insert(m);
		}catch(RuntimeException e){}
		
		return m;
			
	}
	
	
	@Transactional
	public Movie updateMovie(Movie m){
		Movie movie = null;
		try{
			movie = movieDAO.update(m);
		}catch(RuntimeException e){}
		
		return movie;
		}

	@Transactional
	public List<Movie> deleteMovie(Movie m){
		List<Movie> movie = null;
		try{
			movie = movieDAO.deleteMovie(m);
		}catch(RuntimeException e){}
		
		return movie;
	}
	
	@Transactional
	public Integer setMark(Movie m, Integer mark, User u){
		Integer defaultMark = -1;
		try{
			
			defaultMark = userDAO.setMarkbyUser(m,mark,u);
			
		}catch(RuntimeException e){}
		
		return defaultMark;
	}
	
	public Integer getMark(Movie m, User u){
		Integer defaultMark = -1;
		try{
			
			defaultMark = userDAO.getMarkbyUser(m,u);
			
		}catch(RuntimeException e){}
		
		return defaultMark;
	}
	
	@Transactional
	public List<Movie> addWishlist(Movie m, User u){
		List<Movie> movies = null;
		try{
			movies = movieDAO.addMovietoWishlist(m,u);
			
		}catch(RuntimeException e){}
	return movies;
	}

	public List<Movie> getWishlist(User u){
		List<Movie> movies = null;
		try{
			movies = movieDAO.getWishlistbyUser(u);
			
		}catch(RuntimeException e){}
	return movies;
	}

	@Transactional
	public List<Movie> deleteMoviefromWishlist(Movie m, User u){
		List<Movie> movies = null;
		try{
			movies = movieDAO.deleteMoviefromWishlistbyUser(m,u);
			
		}catch(RuntimeException e){}
	return movies;
	}
	@Transactional
	public Director addDirector(Director d){
		Director director = null;
		try{
			director = directorDAO.insert(d);
			
		}catch(RuntimeException e){}
		return director;
	}
	@Transactional
	public Actor addActor(Actor a){
		Actor actor = null;
		try{
			actor = actorDAO.insert(a);
			
		}catch(RuntimeException e){}
		return actor;
		
	}
	@Transactional
	public Screenplay addScreenplay(Screenplay sp){
		Screenplay s = null;
		try{
			s = screenplayDAO.insert(sp);
			
		}catch(RuntimeException e){}
		return sp;
	}
	
	public Actor findActor(String name, Date date_of_birth) {
		Actor actor = null;
		try{
			actor = actorDAO.find(name, date_of_birth);
		}catch(RuntimeException e){}
		return actor;
	}
	
	
	
	public Director findDiretor(String name, Date date_of_birth) {
		Director director= null;
		try{
			director = directorDAO.find(name, date_of_birth);
		}catch(RuntimeException e){}
		return director;
	}
	
	public Screenplay findScreeplay(String name, Date date_of_birth) {
		Screenplay sp = null;
		try{
			sp = screenplayDAO.find(name, date_of_birth);
		}catch(RuntimeException e){}
		return sp;
	}
	
	
		
}
