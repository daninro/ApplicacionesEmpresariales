package service.user;

import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import exceptions.OperationUncompletedException;
import user.User;

public interface UserService {
	@Transactional
	public User addUser(User u)throws OperationUncompletedException;
	@Transactional
	public User editUser(User u)throws OperationUncompletedException;
	@Transactional
	public User deleteUser(User u) throws OperationUncompletedException;
	@Transactional
	public User getUserbyUsername(String u) throws OperationUncompletedException;
	@Transactional
	public List<User> getAllUser() throws OperationUncompletedException;
	@Transactional
	public void deleteUser(String user)throws OperationUncompletedException;;
	
}
