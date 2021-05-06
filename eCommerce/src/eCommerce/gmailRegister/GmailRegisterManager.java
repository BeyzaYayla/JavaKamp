package eCommerce.gmailRegister;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailRegisterManager {

	public void register(String email) {
		Pattern pattern = Pattern.compile("@gmail");
		Matcher matcher = pattern.matcher(email);
		
		if(matcher.find()) {
			System.out.println("User registered with google");
		    return;
		}
		System.out.println("Email doesn't fit the gmail format");
	}
}
