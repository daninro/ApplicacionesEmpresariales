package user;

import movie.Movie;

public interface UserDAO {
		public Integer setMarkbyUser(Movie m, Integer mark, User u);
		public Integer getMarkbyUser(Movie m, User u);
		public User insert(User u) ;
		public User getUser(String username);
		public User update(User u);
		public User delete(User u);
		public Integer setMarkbyUser(int movieId, Integer mark, String username);
		
		
	
	
}
