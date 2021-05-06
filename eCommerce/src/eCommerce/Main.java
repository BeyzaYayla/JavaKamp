package eCommerce;

import eCommerce.business.abstracts.UserService;
import eCommerce.business.concretes.UserManager;
import eCommerce.core.GmailRegisterManagerAdapter;
import eCommerce.dataAccess.concretes.InMemoryUserDao;
import eCommerce.entities.concretes.User;

public class Main {

	public static void main(String[] args) {
		
		UserService userService = new UserManager(new InMemoryUserDao(), new GmailRegisterManagerAdapter());

		userService.register(1, "Engin", "Demiroð", "engin@kodlama.io", "123456");
		
		listUsers(userService);
		
		userService.login("engin@kodlama.io", "123456");
		userService.login("by@gmail.com", "asdfgh");
		
		userService.delete(userService.get(1));
		
		listUsers(userService);
	}

	private static void listUsers(UserService userService) {
		for (User user : userService.getAll()) {
			System.out.println(user.getFirstName());
		}
	}

}
