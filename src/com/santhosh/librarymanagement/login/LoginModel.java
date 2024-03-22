package com.santhosh.librarymanagement.login;

import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.model.Credentials;

public class LoginModel {
	private LoginView loginView;
	private Credentials credentails;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
		credentails = new Credentials();
	}

	public void validateAdmin(String adminName, String password) {
		if (isValidAdminName(adminName)) {
			if (isValidPassword(password)) {
				System.out.println("");
				loginView.startLibrarySetup();
			} else {
				loginView.showAlert("Invalid Password");
				loginView.adminLogin();
			}
		} else {
			loginView.showAlert("Invalid Admin Name");
			loginView.adminLogin();
		}
	}

	private boolean isValidAdminName(String adminName) {
		return adminName.equals(credentails.getAdminName());
	}

	private boolean isValidPassword(String password) {
		return password.equals(credentails.getPassword());
	}

	public void createCredential(Credentials credential) {
		
	}

}
