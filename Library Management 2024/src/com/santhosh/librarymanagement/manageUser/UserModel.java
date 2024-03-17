package com.santhosh.librarymanagement.manageUser;

import java.util.List;

import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.model.Book;
import com.santhosh.librarymanagement.model.User;

public class UserModel {
	private UserView userView;

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
		List<User> users=LibraryDatabase.getInstance().showUsers();
		for (User user: users) {
			if (user.getUserName().equals(deleteUser)) {
				users.remove(user);
				return "User Delete Successfully";
			}	
		}
		return "User Not Found";
	}
	
}
