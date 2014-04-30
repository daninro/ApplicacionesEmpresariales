package services;

import java.util.List;

import movie.JdbcMovieDAO;
import movie.Movie;

import org.springframework.transaction.annotation.Transactional;

import person.Director;
import person.JdbcUserDAO;
import person.User;

public class MovieService {
	
	private JdbcMovieDAO movieDAO;
	private JdbcUserDAO userDAO;
	/*accesors y mutators*/
	
	
	public JdbcMovieDAO getMovieDAO() {
		return movieDAO;
	}
	
	public void setMovieDAO(JdbcMovieDAO movieDAO) {
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
	public Director insertDirectortoMovie(Movie m, Director d){
		Director director=null;
		try{
			director = movieDAO.addDirectortoMovie(m,d);
		}catch(RuntimeException e){}
		
		return director;
			
	}
	@Transactional
	public Director insertDirector(Director d){
		Director director=null;
		try{
			director = movieDAO.addDirector(d);
		}catch(RuntimeException e){}
		
		return director;
			
	}
	
	@Transactional
	public List<Movie> findMoviebyDirector(Director director){
		List<Movie> movie = null;
		try{
			movie = movieDAO.findbyDirector(director);
		} catch (RuntimeException e){}
		
		return movie;
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
	

	
		
}
