package com.santhosh.librarymanagement.login;

import java.util.Scanner;

import com.santhosh.librarymanagement.LibraryManagementApplication;
import com.santhosh.librarymanagement.librarysetup.LibrarySetupView;

public class LoginView {
	private LoginModel loginmodel;

	public LoginView() {
		loginmodel = new LoginModel(this);
	}

	
	public void init() {
		
		System.out.println("--------------" + LibraryManagementApplication.getInstance().getAppName()
				+ " ---------------- \n\t\t  version " + "("
				+ LibraryManagementApplication.getInstance().getAppVersion() + ")");
		login();
	}
   
	public void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Admin name");
		String userName = sc.next();
		System.out.println("Enter your Password");
		String password = sc.next();
		if(loginmodel.validateAdmin(userName, password).equalsIgnoreCase("Valid")){
			startLibrarySetup();
		}
		else {
			showAlert(loginmodel.validateAdmin(userName, password));
		}
	}
	
	public void showAlert(String massage) {
		System.out.println(massage);
	}

	public void startLibrarySetup() {
		System.out.println("Successfully login \n\n Welcome to Library Managemant System");
		System.out.println();
		LibrarySetupView librarySetupView = new LibrarySetupView();
		librarySetupView.init();
	}
}
