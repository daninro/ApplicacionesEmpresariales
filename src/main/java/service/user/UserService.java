package service.user;

import exceptions.OperationUncompletedException;
import user.User;

public interface UserService {

	public User addUser(User u)throws OperationUncompletedException;
	public User editUser(User u)throws OperationUncompletedException;
	public User deleteUser(User u) throws OperationUncompletedException;
	public User getUserbyUsername(String u) throws OperationUncompletedException;
}
