package com.santhosh.librarymanagement.bookmanagement;

import java.util.List;

import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.model.Book;
import com.santhosh.librarymanagement.model.Credentials;
import com.santhosh.librarymanagement.model.Library;

public class BookManagementModel {

	private BookManagementView bookManagementView;
    private Credentials credentials;
	 BookManagementModel(BookManagementView bookManagementView) {
		this.bookManagementView = bookManagementView;
		credentials=new Credentials();
	}

	public void setBookDetails(String bookName, int bookId, String author, String publish, String edition,
			int availableCount, int volume) {
		Book book = new Book();
		book.setName(bookName);
		book.setId(bookId);
		book.setAuthor(author);
		book.setPublication(publish);
		book.setEdition(edition);
		book.setAvailableCount(availableCount);
		book.setVolume(volume);
		addBooks(book);
	}

	public void addBooks(Book book) {
		LibraryDatabase.getInstance().insertBooks(book);
	}

	public Book searchBookDetails(String search) {
		List<Book> books = LibraryDatabase.getInstance().showBooks();
		for (Book book : books) {
			if (search.equalsIgnoreCase(book.getName())) {
				return book;
			}
		}
		return null;
	}

	public void deleteBook(String delete) {
		List<Book> books = LibraryDatabase.getInstance().showBooks();
		Book deleteBook = searchBookDetails(delete);
		if(deleteBook==null) {
			bookManagementView.alertBook();
		}
		else {
		books.remove(deleteBook);
		bookManagementView.onSuccess();
		}
	}

	public void updateBookEdition(String updateBook,String edition) {
		Book updateEdition = searchBookDetails(updateBook);
		if(updateEdition==null) {
			bookManagementView.alertBook();
		}
		else {
			updateEdition.setEdition(edition);
			bookManagementView.onSuccess();
		}
	}

	public void updateBookCount(String updateBook,int count) {
		Book updateCount = searchBookDetails(updateBook);
		if(updateCount==null) {
			bookManagementView.alertBook();
		}
		else {
			updateCount.setAvailableCount(count);;
			bookManagementView.onSuccess();
		}
	}

	public void updateBookVolume(String updateBook,int volume) {
		Book updateVolume = searchBookDetails(updateBook);
		if(updateVolume==null) {
			bookManagementView.alertBook();
		}
		else {
			updateVolume.setVolume(volume);
			bookManagementView.onSuccess();
		}
	}

public boolean checkAdmin(String name,String password) {
	   if(name.equals(credentials.getUserName())&&password.equals(credentials.getPassword())) {
		   return true;
	   }
	   return false;
	}
	
}
