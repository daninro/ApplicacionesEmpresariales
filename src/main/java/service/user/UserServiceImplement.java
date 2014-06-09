package service.user;

import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;
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

	public User addUser(User u)throws OperationUncompletedException{
		User user = null;
		try{
			user = userDAO.insert(u);
		} catch (RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema durante la insercion");
		}
		
		return user;
	}

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
	
	public User deleteUser(User u){
		User user = null;
		try{
			user = userDAO.delete(u);
		} catch (RuntimeException e){}
		
		return user;
	}
	public User getUserbyUsername(String u){
		User user = null;
		try{
			System.out.println("fg");
			user = userDAO.getUser(u);
		} catch (RuntimeException e){}
		
		return user;
	}
	
	
}
