package service.movie;

import java.sql.Date;
import java.util.List;

import movie.Movie;
import movie.MovieDAO;

import org.springframework.transaction.annotation.Transactional;

import screenplay.JdbcScreenplayDAO;
import screenplay.Screenplay;
import user.User;
import user.UserDAO;
import actor.Actor;
import actor.JdbcActorDAO;
import director.Director;
import director.DirectorDAO;
import exceptions.MyNotFoundExeption;
import exceptions.OperationUncompletedException;

public class MovieServiceImplement implements MovieService{
	
	private MovieDAO movieDAO;
	private UserDAO userDAO;
	private DirectorDAO directorDAO;
	private JdbcScreenplayDAO screenplayDAO;
	private JdbcActorDAO actorDAO;

	/*accesors y mutators*/
	public MovieDAO getMovieDAO() {
		return movieDAO;
	}
	public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public DirectorDAO getDirectorDAO() {
		return directorDAO;
	}
	public void setDirectorDAO(DirectorDAO directorDAO) {
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

	
	/**
	 * @author Minux777
	 * @return a list of all the movies in the database
	 * @throws OperationUncompletedException if a problem is founded while
	 * the method is executed
	 */
	public List<Movie> getAllMovies() throws OperationUncompletedException{
		List<Movie> movie = null;
		try{
			movie = movieDAO.getAll();
		} catch (RuntimeException e){
			throw new OperationUncompletedException("No se pudieron obtener las peliculas");
		}
		return movie;
	}
		
	public Movie findMoviebyId(Integer id) throws OperationUncompletedException{
		Movie movie = null;
		try{
			movie = movieDAO.findbyId(id);
		} catch (RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la busqueda");
		} catch (MyNotFoundExeption e) {
			throw new OperationUncompletedException("El elemento buscado no existe");
		}
		return movie;
	}
	
	public List<Movie> searchByName(String name) throws OperationUncompletedException{
		List<Movie> movies = null;
		try{
			movies = movieDAO.searchByName(name);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la busqueda");
		}
		
		
		
		return movies;
	}
	
	public List<Movie> findMoviebyYear(Integer year) throws OperationUncompletedException{
		List<Movie> movie = null;
		try{
			movie = movieDAO.findbyYear(year);
		} catch (RuntimeException e){
			throw new OperationUncompletedException("El elemento buscado no existe");
		}
		
		return movie;
	}
	
	public List<Movie> findMoviebyCountry(String c) throws OperationUncompletedException{
		List<Movie> movie = null;
		try{
			movie = movieDAO.findbyCountry(c);
		} catch (RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema Durante la busqueda");
		}
		
		return movie;
	}
	
	@Transactional
	public Movie addMovie(Movie m) throws OperationUncompletedException{
		try{
			movieDAO.insert(m);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema Durante la insercion");
		}
		return m;
	}
	
	@Transactional
	public Movie updateMovie(Movie m) throws OperationUncompletedException{
		Movie movie = null;
		try{
			movie = movieDAO.update(m);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema durante la actualizacion");
		}catch (MyNotFoundExeption e) {
			throw new OperationUncompletedException("no se encontro la pelicula a actualizar");
		}
		return movie;
	}

	@Transactional
	public List<Movie> deleteMovie(Movie m) throws OperationUncompletedException{
		List<Movie> movie = null;
		try{
			movie = movieDAO.deleteMovie(m);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("ocurrio un problema durante la eliminacion");
		}
		return movie;
	}
	
	@Transactional
	public Integer setMark(Movie m, Integer mark, User u) throws OperationUncompletedException{
		Integer defaultMark = -1;
		try{
			
			defaultMark = userDAO.setMarkbyUser(m,mark,u);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("ocurrio un problema mientras se agregaba la calificacion");
		}
		
		return defaultMark;
	}
	
	@Override
	@Transactional
	public Integer setMark(int movieId, Integer mark, String username) throws OperationUncompletedException{
		Integer defaultMark = -1;
		try{
			defaultMark = userDAO.setMarkbyUser(movieId,mark,username);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("ocurrio un problema mientras se insertaba la calificacion");
		}					
		return defaultMark;
	}
	
	public Integer getMark(Movie m, User u)throws OperationUncompletedException{
		Integer defaultMark = -1;
		try{
			
			defaultMark = userDAO.getMarkbyUser(m,u);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("ocurrio un problema mientras se o");
		}
		
		return defaultMark;
	}
	
	//@minux777: hasta aqui creo que esta listo, aunque no esta mal echarle una mirada
	
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
			
		}catch(RuntimeException e){
			
		} catch (MyNotFoundExeption e) {
			
			//e.printStackTrace();
		}
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

	
	//@minux777: creo que esto hay que sacarlo y crear otro servicio para "personas" o algo asi
	
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
		return s;
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

	@Transactional
	public Actor deleteActor(Actor a) {
		Actor actor = null;
		try{
			actor = actorDAO.delete(a);
			
		}catch(RuntimeException e){}
	return actor;
		
	}

	@Transactional
	public Actor deleteActorbykey(String name, Date date) {
		Actor actor = null;
		try{
			actor = actorDAO.deletebykey(name, date);
			
		}catch(RuntimeException e){}
	return actor;
		
	}

	@Transactional
	public Actor updateActor(Actor a) {
		Actor actor = null;
		try{
			actor = actorDAO.update(a);
			
		}catch(RuntimeException e){}
	return actor;
		
	}
		
	@Transactional
	public Director deleteDirector(Director d){
		Director director = null;
		try{
			director = directorDAO.delete(d);
			
		}catch(RuntimeException e){}
	return director;
		
	}

	@Transactional
	public Director deleteDirectorbykey(String name, Date date){
		Director director = null;
		try{
			director = directorDAO.deletebykey(name, date);
			
		}catch(RuntimeException e){}
	return director;
		
	}
	
	@Transactional
	public Director updateDirector(Director d){
		Director director = null;
		try{
			director = directorDAO.update(d);
			
		}catch(RuntimeException e){}
	return director;
		
	}
	
	@Transactional
	public Screenplay deleteScreenplay(Screenplay sp){
		Screenplay screenplay = null;
		try{
			screenplay = screenplayDAO.delete(sp);
			
		}catch(RuntimeException e){}
	return screenplay;
		
	}
	
	@Transactional
	public Screenplay deleteScreenplaybykey(String name, Date date){
		
		Screenplay screenplay = null;
		try{
			screenplay = screenplayDAO.deletebykey(name, date);
			
		}catch(RuntimeException e){}
	return screenplay;
		
	}
	
	@Transactional
	public Screenplay updateScreenplay(Screenplay sp){
		
		Screenplay screenplay = null;
		try{
			screenplay = screenplayDAO.update(sp);
			
		}catch(RuntimeException e){}
	return screenplay;
		
	}


		
}
