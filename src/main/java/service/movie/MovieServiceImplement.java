package service.movie;

import java.util.List;

import movie.Movie;
import movie.MovieDAO;

import org.springframework.transaction.annotation.Transactional;

import user.User;
import user.UserDAO;
import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;

public class MovieServiceImplement implements MovieService{
	
	private MovieDAO movieDAO;
	private UserDAO userDAO;
	
	
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
	
	
	
	


		
}
