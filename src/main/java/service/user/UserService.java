package service.user;

import user.User;

public interface UserService {

	public User addUser(User u);
	public User editUser(User u);
	public User deleteUser(User u);
	public User getUserbyUsername(String u);
}
