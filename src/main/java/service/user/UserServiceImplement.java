package service.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;
import movie.Movie;
import movie.MovieDAO;
import user.User;
import user.UserDAO;

public class UserServiceImplement  implements UserService{

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
	@Transactional
	public User addUser(User u)throws OperationUncompletedException{
		User user = null;
		try{
			user = userDAO.insert(u);
		} catch (RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema durante la insercion");
		}
		
		return user;
	}
	@Transactional
	public User editUser(User u)throws OperationUncompletedException{
		User user = null;
		try{
			user = userDAO.update(u);
		} catch (RuntimeException e){	
			throw new OperationUncompletedException("Ocurrio un problema durante la actualizacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("no se encontro la pelicula a actualizar");
		}
		
		return user;
	}
	@Transactional
	public User deleteUser(User u) throws OperationUncompletedException{
		User user = null;
		try{
			user = userDAO.delete(u);
		} catch (RuntimeException e){
			throw new OperationUncompletedException("Ocurio un problema al intenar eliminar el usuario");
		} catch (MyNotFoundException e) {
			throw new OperationUncompletedException("no se encontro el usuario a eliminar");		
		}
		
		return user;
	}
	@Transactional
	public User getUserbyUsername(String u)throws OperationUncompletedException{
		User user = null;
		try{
			System.out.println("fg");
			user = userDAO.getUser(u);
		} catch (RuntimeException e){
			throw new OperationUncompletedException("Ocurio un problema en ls base de datos");
		} catch (MyNotFoundException e) {
			throw new OperationUncompletedException("no se encontro el usuario");
		}
		
		return user;
	}
	@Transactional
	public List<User> getAllUser() throws OperationUncompletedException{
		List<User> user = null;
		try{
			user = userDAO.getAll();
		} catch (RuntimeException e){
			throw new OperationUncompletedException("No se pudieron obtener los usuarios");
		}
		return user;
	}
	@Transactional
	public void deleteUser(String user)throws OperationUncompletedException{
		try{
			userDAO.delete(user);
		} catch (RuntimeException e){
			
		}
	}
	
}
