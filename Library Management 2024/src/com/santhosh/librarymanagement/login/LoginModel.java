package com.santhosh.librarymanagement.login;

import com.santhosh.librarymanagement.model.Credentials;

public class LoginModel {
	private LoginView loginView;
    private Credentials credentails;
	LoginModel(LoginView loginView) {
		this.loginView = loginView;
		credentails=new Credentials();
	}

	public String validateAdmin(String userName, String password) {
		if (isValidAdminName(userName)) {
			if (isValidPassword(password)) {
//				loginView.startLibrarySetup();
				return "Valid";
			} else {
//				loginView.showAlert("Invalid Password");
               return "Invalid Password";
			}
		} else {
			return "Invalid User Name";
//			loginView.showAlert("Invalid User Name");
		}
	}

	private boolean isValidAdminName(String userName) {
		return userName.equals(credentails.getUserName());
	}

	private boolean isValidPassword(String password) {
		return password.equals(credentails.getPassword());
	}

}
