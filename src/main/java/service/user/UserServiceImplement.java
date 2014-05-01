package service.user;

import movie.JdbcMovieDAO;
import movie.MovieDAO;
import user.JdbcUserDAO;
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

	public User addUser(User u){
		User user = null;
		try{
			user = userDAO.insert(u);
		} catch (RuntimeException e){}
		
		return user;
	}

	public User editUser(User u){
		User user = null;
		try{
			user = userDAO.update(u);
		} catch (RuntimeException e){}
		
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
