package com.santhosh.librarymanagement.manageUser;

import java.util.List;
import java.util.Scanner;

import com.santhosh.librarymanagement.bookmanagement.BookManagementView;
import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.model.User;

public class UserView {
	private UserModel userModel;
	Scanner scanner = new Scanner(System.in);

	public UserView() {
		userModel = new UserModel(this);
	}

	public void initAdd() {
		System.out.println("Enter the following user Details: ");
		User user = new User();
		System.out.println("Enter user name:");
		user.setUserName(scanner.next());
		System.out.println("Enter user emailId:");
		user.setMailId(scanner.next());
		userModel.addNewUser(user);
	}

	public void showLibraryName(String libraryName) {
		System.out.println("Current Library Name - " + libraryName);
	}

	public void onUserAdded(User user) {
		System.out.println("\n------- User '" + user.getUserName() + "' added successfully ------- \n");
		checkForAddNewUser();
	}

	public void onUserExist(User user) {
		System.out.println("\n------- User '" + user.getUserName() + "' already exist -------\n");
		checkForAddNewUser();
	}

	private void checkForAddNewUser() {
		System.out.println("Do you want to add more users? \nType Yes/No");
		String choice = scanner.next();
		if (choice.equalsIgnoreCase("yes")) {
			initAdd();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding users");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewUser();
		}
	}

	public void deleteUser() {
		if(new BookManagementView().getCredentails()) {
			System.out.println("Enter the Name of the User you want to delete");
			System.out.println(userModel.deleteUser(scanner.next()));
		}
		else {
			System.out.println("Invalid Credentails,No Access");
		}
		
	}

	public void viewUsers() {
		if(new BookManagementView().getCredentails()) {
		System.out.println("\nList of Users\n");
		int i = 1;
		List<User> users = LibraryDatabase.getInstance().showUsers();
		for (User user : users) {
			System.out.println(i++ + "." + user.getUserName());
		}
		}
		else {
			System.out.println("Invalid Credentails,No Access");
		}
	}
}
