package eCommerce.business.abstracts;

import java.util.List;

import eCommerce.entities.concretes.User;

public interface UserService {

	void register(int id, String firstName, String lastName, String email, String password);
	void login(String email, String password);
	void delete(User user);
	void update(User user);
	User get(int id);
	List<User> getAll();
}
