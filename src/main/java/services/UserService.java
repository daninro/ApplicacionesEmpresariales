package services;

import movie.JdbcMovieDAO;
import person.JdbcUserDAO;
import person.User;

public class UserService {

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
