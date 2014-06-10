package service.movie;

import java.sql.Date;
import java.util.List;

import movie.Movie;
import movie.MovieDAO;

import org.springframework.transaction.annotation.Transactional;

import screenplay.JdbcScreenplayDAO;
import screenplay.ScreenplayDAO;
import screenplay.Screenplay;
import user.User;
import user.UserDAO;
import director.Director;
import director.DirectorDAO;
import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;

public class MovieServiceImplement implements MovieService{
	
	private MovieDAO movieDAO;
	private UserDAO userDAO;
	private DirectorDAO directorDAO;
	private ScreenplayDAO screenplayDAO;

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
	public ScreenplayDAO getScreenplayDAO() {
		return screenplayDAO;
	}
	public void setScreenplayDAO(JdbcScreenplayDAO screenplayDAO) {
		this.screenplayDAO = screenplayDAO;
	}

	
	/**
	 * @author Minux777
	 * @return a list of all the movies in the database
	 * @throws OperationUncompletedException if a problem is founded while
	 * the method is executed
	 */
	@Transactional
	public List<Movie> getAllMovies() throws OperationUncompletedException{
		List<Movie> movie = null;
		try{
			movie = movieDAO.getAll();
		} catch (RuntimeException e){
			throw new OperationUncompletedException("No se pudieron obtener las peliculas");
		}
		return movie;
	}
		
	@Transactional
	public Movie findMoviebyId(Integer id) throws OperationUncompletedException{
		Movie movie = null;
		try{
			movie = movieDAO.findbyId(id);
		} catch (RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la busqueda");
		} catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El elemento buscado no existe");
		}
		return movie;
	}
	@Transactional
	public List<Movie> searchByName(String name) throws OperationUncompletedException{
		List<Movie> movies = null;
		try{
			movies = movieDAO.searchByName(name);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la busqueda");
		} 
		
		
		return movies;
	}
	@Transactional
	public List<Movie> findMoviebyYear(Integer year) throws OperationUncompletedException{
		List<Movie> movie = null;
		try{
			movie = movieDAO.findbyYear(year);
		} catch (RuntimeException e){
			throw new OperationUncompletedException("El elemento buscado no existe");
		}
		
		return movie;
	}
	@Transactional
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
		} catch (MyNotFoundException e) {
			throw new OperationUncompletedException("Ocurrio un problema durante la insercion: " + e.getMessage());
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
		}catch (MyNotFoundException e) {
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
	@Transactional
	public Integer getMark(Movie m, User u)throws OperationUncompletedException{
		Integer defaultMark = -1;
		try{
			
			defaultMark = userDAO.getMarkbyUser(m,u);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("ocurrio un problema al obtener las calificaciones");
		}
		
		return defaultMark;
	}
	
	//@minux777: hasta aqui creo que esta listo, aunque no esta mal echarle una mirada
	
	@Transactional
	public List<Movie> addWishlist(Movie m, User u) throws OperationUncompletedException{
		List<Movie> movies = null;
		try{
			movies = movieDAO.addMovietoWishlist(m,u);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema al insertar pelicula a la wishlist");
		}
	return movies;
	}

	public List<Movie> getWishlist(User u)throws OperationUncompletedException{
		List<Movie> movies = null;
		try{
			movies = movieDAO.getWishlistbyUser(u);
			
		}catch(RuntimeException e){
			
		} catch (MyNotFoundException e) {
			throw new OperationUncompletedException("Ocurrio un problema al obtener la wishlist");
		}
	return movies;
	}

	@Transactional
	public List<Movie> deleteMoviefromWishlist(Movie m, User u)throws OperationUncompletedException{
		List<Movie> movies = null;
		try{
			movies = movieDAO.deleteMoviefromWishlistbyUser(m,u);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema al eliminar la pelicula de la wishlist");
		}
	return movies;
	}

	
	//@minux777: creo que esto hay que sacarlo y crear otro servicio para "personas" o algo asi
	
	@Transactional
	public Director addDirector(Director d)throws OperationUncompletedException{
		Director director = null;
		try{
			director = directorDAO.insert(d);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema durante la insercion del director");
		}
		return director;
	}
	
	
	
	@Transactional
	public Screenplay addScreenplay(Screenplay sp)throws OperationUncompletedException{
		Screenplay s = null;
		try{
			s = screenplayDAO.insert(sp);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema durante la insercion del guionista");
		}
		return s;
	}
	
	
	
	public Director findDiretor(String name, Date date_of_birth)throws OperationUncompletedException {
		Director director= null;
		try{
			director = directorDAO.find(name, date_of_birth);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la busqueda");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El director buscado no existe");
		}
		return director;
	}
	
	public Screenplay findScreeplay(String name, Date date_of_birth) throws OperationUncompletedException{
		Screenplay sp = null;
		try{
			sp = screenplayDAO.find(name, date_of_birth);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la busqueda");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El guionista buscado no existe");
		}
		return sp;
	}

			
	@Transactional
	public Director deleteDirector(Director d)throws OperationUncompletedException{
		Director director = null;
		try{
			director = directorDAO.delete(d);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El Director no existe");
			
		}
	return director;
		
	}

	@Transactional
	public Director deleteDirectorbykey(String name, Date date) throws OperationUncompletedException{
		Director director = null;
		try{
			director = directorDAO.deletebykey(name, date);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El director no existe");
		}
	return director;
		
	}
	
	@Transactional
	public Director updateDirector(Director d)throws OperationUncompletedException{
		Director director = null;
		try{
			director = directorDAO.update(d);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la actualizacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El director no existe");
		}
	return director;
		
	}
	
	@Transactional
	public Screenplay deleteScreenplay(Screenplay sp)throws OperationUncompletedException{
		Screenplay screenplay = null;
		try{
			screenplay = screenplayDAO.delete(sp);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El guionista no existe");
		}
	return screenplay;
		
	}
	
	@Transactional
	public Screenplay deleteScreenplaybykey(String name, Date date)throws OperationUncompletedException{
		
		Screenplay screenplay = null;
		try{
			screenplay = screenplayDAO.deletebykey(name, date);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El guionista no existe");
		}
	return screenplay;
		
	}
	
	@Transactional
	public Screenplay updateScreenplay(Screenplay sp)throws OperationUncompletedException{
		
		Screenplay screenplay = null;
		try{
			screenplay = screenplayDAO.update(sp);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la actualizacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El guionista no existe");
		}
	return screenplay;
		
	}


		
}
