package com.santhosh.librarymanagement.databaseManagemet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.santhosh.librarymanagement.model.Book;
import com.santhosh.librarymanagement.model.Library;
import com.santhosh.librarymanagement.model.User;

public class LibraryDatabase {
	private static LibraryDatabase libraryDatabase;
	private Library library;
	private List<Book> books;
	private List<User> userList;
	private List<Map<String, Book>> issuedBooks;

	private LibraryDatabase() {
		this.books = new ArrayList();
		this.userList = new ArrayList();
		this.issuedBooks = new ArrayList<>();
	}

	public static LibraryDatabase getInstance() {
		if (libraryDatabase == null) {
			libraryDatabase = new LibraryDatabase();
		}
		return libraryDatabase;
	}

	
	public Library getLibrary() {
		return library;
	}

	public void insertLibrary(Library library) {
		this.library = library;
	}

	public void insertBooks(Book book) {
		books.add(book);
	}

	public List<Book> showBooks() {
		return books;
	}

	public boolean insertUser(User user) {
		boolean hasUser = false;
		for (User addedUser : userList) {
			if (addedUser.getMailId().equals(user.getMailId())) {
				hasUser = true;
				break;
			}
		}
		if (hasUser) {
			return false;
		} else {
			userList.add(user);
			return true;
		}
	}

	public List<User> showUsers() {
		return userList;
	}

	public void insertIssuedBooks(Map<String, Book> mapbooktocustomer) {
		issuedBooks.add(mapbooktocustomer);
	}

	public List<Map<String, Book>> showIssuedBooks() {
		return issuedBooks;

	}
}
