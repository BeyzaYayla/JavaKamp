package eCommerce.core;

import eCommerce.gmailRegister.GmailRegisterManager;

public class GmailRegisterManagerAdapter implements RegisterService{

	@Override
	public void register(String email) {
		GmailRegisterManager registerManager = new GmailRegisterManager();
		registerManager.register(email);
	}

}
