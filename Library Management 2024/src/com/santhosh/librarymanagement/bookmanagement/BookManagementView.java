package com.santhosh.librarymanagement.bookmanagement;

import java.util.List;
import java.util.Scanner;

import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.librarysetup.LibrarySetupView;
import com.santhosh.librarymanagement.login.LoginModel;
import com.santhosh.librarymanagement.model.Book;

public class BookManagementView {
	private BookManagementModel bookManagementModel;
	Scanner sc=new Scanner(System.in);
	public BookManagementView() {
		bookManagementModel = new BookManagementModel(this);
	}

	public void getBookDetails() {
		
		System.out.println("Enter the Name of the Book");
		String bookName = sc.nextLine();

		System.out.println("Enter the ID of the Book");
		int bookId = sc.nextInt();

		sc.nextLine();
		System.out.println("Enter the Author of the Book");
		String author = sc.nextLine();

		System.out.println("Enter the Publication Year of the Book");
		String publish = sc.nextLine();

		System.out.println("Enter the Edition of the Book");
		String edition = sc.nextLine();

		System.out.println("Enter the Available Count of the Book");
		int availableCount = sc.nextInt();

		System.out.println("Enter the Volume of the Book");
		int volume = sc.nextInt();
		bookManagementModel.setBookDetails(bookName, bookId, author, publish, edition, availableCount, volume);
	}

	public void showBooks() {
		// TODO Auto-generated method stub
		System.out.println("\nList of Books\n");
		int i = 1;
		List<Book> books = LibraryDatabase.getInstance().showBooks();
		for (Book book : books) {
			System.out.println(i++ + "." + book);
		}
	}

	public void searchBook() {
		System.out.println("Enter the Name of the Book you want to Search");
		String search = sc.next();
		Book searchBook = bookManagementModel.searchBookDetails(search);
		System.out.println(searchBook.getBookDetails());
	}

	public boolean getCredentails() {
		System.out.println("Enter your Admin name");
		String name = sc.next();
		System.out.println("Enter your Password");
		String password = sc.next();
		return bookManagementModel.checkAdmin(name,password);
	}
	public void deleteBook() {
		if(getCredentails()){
			System.out.println("Enter the Book Name you want to delete");
			String deleteBook=sc.nextLine();
			bookManagementModel.deleteBook(deleteBook);	
		}
		else {
		  System.out.println("Invalid Credentails,No Access");
		}
	}

	public void alertBook() {
		System.out.println("Book not found");
	}
	
	public void onSuccess() {
		System.out.println("Book Delete Successfully");
	}

	public void updateBook() {
		if(getCredentails()){
		System.out.println("Enter the Name of the Book you want to Update");
		String updatebook=sc.next();
	 while (true) {
			System.out.println("What you need to Update in the Book");
			System.out.println("\n 1.Update Book Edition \n 2.Update Book Count \n 3.Update Book Volume \n 4.Exit Updation Process \nEnter your Choice");
			System.out.println();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the Updated Edition of the Book");
				String edition=sc.next();
				bookManagementModel.updateBookEdition(updatebook,edition);
				break;
			case 2:
				System.out.println("Enter the Updated Count of the Book");
				int count=sc.nextInt();
				bookManagementModel.updateBookCount(updatebook,count);
				break;
			case 3:
				System.out.println("Enter the Updated Volume of the Book");
				int volume=sc.nextInt();
				bookManagementModel.updateBookVolume(updatebook,volume);
				break;
			case 4:
				System.out.println("\n-- Updation Completed-----");
				new LibrarySetupView().onSetupComplete();
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}
		}
	}
		else {
			System.out.println("Invalid Credentails,No Access");
		}
   }
}