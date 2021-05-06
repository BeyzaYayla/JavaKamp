package eCommerce.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import eCommerce.dataAccess.abstracts.UserDao;
import eCommerce.entities.concretes.User;

public class InMemoryUserDao implements UserDao{

	List<User> users;
	
	public InMemoryUserDao() {
		users = new ArrayList<User>();
		
		users.add(new User(0,"Beyza","Yayla","by@gmail.com", "asdfgh"));
	}
	
	@Override
	public void add(User user) {
		this.users.add(user);
		System.out.println("User added: " + user.getFirstName() + " " + user.getLastName());
		
	}

	@Override
	public void delete(User user) {
		
		User userToDelete = get(user.getId());
		this.users.remove(userToDelete);
		System.out.println("User deleted: " + user.getFirstName() + " " + user.getLastName());
	}

	@Override
	public void update(User user) {
		
		User userToUpdate = get(user.getId());
		userToUpdate.setFirstName(user.getFirstName());
		userToUpdate.setLastName(user.getLastName());
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setPassword(user.getPassword());
		
		System.out.println("User updated: " + userToUpdate.getFirstName() + " " + userToUpdate.getLastName());
	}

	@Override
	public User get(int id) {
		
		for (User user : this.users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		return this.users;
	}

}
