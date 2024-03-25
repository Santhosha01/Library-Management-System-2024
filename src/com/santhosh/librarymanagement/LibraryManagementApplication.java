package com.santhosh.librarymanagement;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.santhosh.librarymanagement.login.LoginView;

public class LibraryManagementApplication {
	private static LibraryManagementApplication libraryManagement;

	private String appName = "Library Management System";

	private String appVersion = "0.0.1";

	private LibraryManagementApplication() {

	}

	public static LibraryManagementApplication getInstance() {
		if (libraryManagement == null) {
			libraryManagement = new LibraryManagementApplication();
		}
		return libraryManagement;
	}

	private void create() {
		LoginView loginView = new LoginView();
		System.out.println("--------------" + LibraryManagementApplication.getInstance().getAppName()
				+ " ---------------- \n\t\t  version " + "("
				+ LibraryManagementApplication.getInstance().getAppVersion() + ")");
		loginView.startMenu();
	}

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public static void main(String arg[])  {

		LibraryManagementApplication.getInstance().create();
	}

}