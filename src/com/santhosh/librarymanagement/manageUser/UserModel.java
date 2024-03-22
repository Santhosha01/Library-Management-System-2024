package com.santhosh.librarymanagement.manageUser;

import java.util.List;
import java.util.Scanner;

import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.model.User;

public class UserModel {
	private UserView userView;
	private User user = new User();
	private Scanner sc = new Scanner(System.in);

	UserModel(UserView userView) {
		this.userView = userView;
	}

	public void addNewUser(User user) {
		if (LibraryDatabase.getInstance().insertUser(user)) {
			userView.onUserAdded(user);
		} else {
			userView.onUserExist(user);
		}
	}

	public String deleteUser(String deleteUser) {
		List<User> users = LibraryDatabase.getInstance().showUsers();
		for (User user : users) {
			if (user.getUserName().equals(deleteUser)) {
				users.remove(user);
				return "User Delete Successfully";
			}
		}
		return "User Not Found";
	}

	public boolean checkPhoneNumber(long phoneNumber) {
		String number = "" + phoneNumber;
		String phoneNumberFormat = "\\d{10}";
		return number.matches(phoneNumberFormat);
	}

	public boolean checkMailId(String mailId) {
		String mail = mailId;
		String mailformat = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.]+$";
		return mail.matches(mailformat);
	}

	public boolean searchUserDetails(String userName, String password) {
		List<User> users = LibraryDatabase.getInstance().showUsers();
		for (User user : users) {
			if (userName == (user.getUserName()) && password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}

	public void setUserDetails(String userName, String password, String mailId, long phoneNumber) {
		user.setUserName(userName);
		user.setPassword(password);
		user.setMailId(mailId);
		user.setPhoneNumber(phoneNumber);
		addNewUser(user);
	}

	public User searchUser(String userName) {
		List<User> users = LibraryDatabase.getInstance().showUsers();
		for (User user : users) {
			if (userName == (user.getUserName())) {
				return user;
			}
		}
		return null;
	}

	public void setUpadatedMailId(String mailId, String userName) {
		User user = searchUser(userName);
		user.setMailId(mailId);
	}

	public void setUpadatedPhoneNumber(long phoneNumber, String userName) {
		System.out.println("Phone number updating.....");
		User user = searchUser(userName);
		user.setPhoneNumber(phoneNumber);

	}

	public void ViewUserDetails(String userName) {
		User user = searchUser(userName);
		if (user != null) {
			System.out.println(user.toString());
		} else {
			System.out.println("No User");
		}
	}

	public void createUserDetails(String userName, String password, String mailId, long phoneNumber) {
		user.setUserName(userName);
//		user.setUserId(userId);
		user.setPassword(password);
		user.setMailId(mailId);
		user.setPhoneNumber(phoneNumber);
		addUser(user);
	}

	private void addUser(User user) {
		if (LibraryDatabase.getInstance().insertUser(user)) {
			userView.onSucess("Sign Up Successfully");
		} else {
			userView.onExist("User Already Exist");
		}
	}

}
