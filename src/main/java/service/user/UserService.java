package service.user;

import java.util.List;

import movie.Movie;

import org.springframework.transaction.annotation.Transactional;

import exceptions.OperationUncompletedException;
import user.User;

public interface UserService {

	public User addUser(User u)throws OperationUncompletedException;
	public User editUser(User u)throws OperationUncompletedException;
	public User deleteUser(User u) throws OperationUncompletedException;
	public User getUserbyUsername(String u) throws OperationUncompletedException;
	public List<User> getAllUser() throws OperationUncompletedException;
	public void deleteUser(String user)throws OperationUncompletedException;;
	
}
