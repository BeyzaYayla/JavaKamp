package eCommerce.business.concretes;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import eCommerce.business.abstracts.UserService;
import eCommerce.core.RegisterService;
import eCommerce.dataAccess.abstracts.UserDao;
import eCommerce.entities.concretes.User;

public class UserManager implements UserService{

	private UserDao userDao;
	private RegisterService registerService;
	
	public UserManager(UserDao userDao, RegisterService registerService) {
		this.userDao = userDao;
		this.registerService = registerService;
	}

	@Override
	public void register(int id, String firstName, String lastName, String email, String password) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1- Sign up with Google");
		System.out.println("2- Sign up with other mail");
		System.out.println("Enter the number of your choice: ");
		
		if (scanner.nextInt() == 1) {
			registerService.register(email);
		}
		else {
			if(checkEmailFormat(email) && checkIfUserExists(email) && checkPassword(password) && checkName(firstName, lastName) && sendVerificationMail()) {
				this.userDao.add(new User(id,firstName,lastName,email,password));
				System.out.println("User registered successfully");
				return;
			}
		}
		System.out.println("User couldn't register");
	}

	@Override
	public void login(String email, String password) {
		
		for (User user : this.userDao.getAll()) {
			if (user.getEmail() == email && user.getPassword() == password) {
				System.out.println("Login successful");
				return;
			}
		}
		System.out.println("Check your email and password again");
	}

	@Override
	public void delete(User user) {
		this.userDao.delete(user);
		
	}

	@Override
	public void update(User user) {
		this.userDao.update(user);
		
	}

	@Override
	public User get(int id) {
		
		return this.userDao.get(id);
	}

	@Override
	public List<User> getAll() {
		
		return this.userDao.getAll();
	}

	private boolean checkIfUserExists(String email) {
		
		for (User user : this.userDao.getAll()) {
			if (user.getEmail() == email) {
				System.out.println("User already exists!");
				return false;
			}
		}
		return true;
	}
	
	private boolean checkEmailFormat(String email) {
		Pattern pattern = Pattern.compile("[@]");
		Matcher matcher = pattern.matcher(email);
		
		if(matcher.find()) {
		      return true;
		}
		System.out.println("Email doesn't fit the format");
		return false;
	}
	
	private boolean checkPassword(String password) {
		if (password.length() < 6) {
			System.out.println("Password must be at least 6 character long");
			return false;
		}
		return true;
	}
	
	private boolean checkName(String firstName, String lastName) {
		if (firstName.length() > 1 && lastName.length() >1) {
			return true;
		}
		System.out.println("First name and last name must be at least 2 character long");
		return false;
	}
	
	private boolean sendVerificationMail() {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Verify your email by pressing y and enter");
		
	    String reply = myObj.nextLine();
	    
	    if (reply.equals("y")) {
	    	System.out.println("Email verified");
			return true;
		}
	    return false;
	}
}
