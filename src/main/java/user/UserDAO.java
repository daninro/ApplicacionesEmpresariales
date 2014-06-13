package user;

import java.util.List;

import exceptions.MyNotFoundException;
import movie.Movie;

public interface UserDAO {
		public Integer setMarkbyUser(Movie m, Integer mark, User u);
		public Integer getMarkbyUser(Movie m, User u) throws MyNotFoundException;
		public User insert(User u) ;
		public User getUser(String username) throws MyNotFoundException;
		public User update(User u)throws MyNotFoundException;
		public User delete(User u) throws MyNotFoundException;
		public Integer setMarkbyUser(int movieId, Integer mark, String username);
		public List<User> getAll();
		public void delete(String user);
		
		
		
	
	
}
