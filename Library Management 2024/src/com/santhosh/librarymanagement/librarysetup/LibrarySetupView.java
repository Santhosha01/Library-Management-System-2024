package com.santhosh.librarymanagement.librarysetup;

import java.util.Scanner;

import com.santhosh.librarymanagement.LibraryManagementApplication;
import com.santhosh.librarymanagement.bookmanagement.BookManagementView;
import com.santhosh.librarymanagement.manageUser.UserView;

public class LibrarySetupView {

	private LibrarySetupModel librarySetupModel;

	public LibrarySetupView() {
		librarySetupModel = new LibrarySetupModel(this);
	}

	public void init() {
		librarySetupModel.startSetup();
	}

	public void onSetupComplete() {
		
		Scanner sc = new Scanner(System.in);
		 BookManagementView bookManagementView = new BookManagementView();
		 UserView userView=new UserView();
		while (true) {
			System.out.println(
					"\nLibrary Features\n \n 1.Add Book \n 2.View Books \n 3.Search Book \n 4.Add User \n 5.Delete Book \n 6.Update Book \n 7.Manage User \n 8.View Users \n 9.Exit ");
			System.out.println("\nEnter your Choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				bookManagementView.getBookDetails();
				break;
			case 2:
				bookManagementView.showBooks();
				break;
			case 3:
				bookManagementView.searchBook();
				break;
			case 4:
				userView.initAdd();
				break;
			case 5:
				bookManagementView.deleteBook();
				break;
			case 6:
				bookManagementView.updateBook();
			case 7:
				userView.deleteUser();
				break;
			case 8:
				userView.viewUsers();
				break;
			case 9:
				System.out.println(
						"\n-- Thanks for using " + LibraryManagementApplication.getInstance().getAppName() + " --");
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}
		}

	}

	public void showAlert(String alert) {
		System.out.println(alert);
	}

	public void initiateSetup() {
		System.out.println("----Library Details-----");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Library Name");
		String libraryName = sc.nextLine();

		System.out.println("Enter the Library ID");
		int libraryId = sc.nextInt();

		System.out.println("Enter the Library Contact Number");
		long phoneNumber = sc.nextLong();
		sc.nextLine();
		System.out.println("Enter the Library Mail ID");
		String mailId = sc.nextLine();
		System.out.println("Enter the Library Address");
		String address = sc.nextLine();

		librarySetupModel.setLibraryDetails(libraryName, libraryId, phoneNumber, mailId, address);
		System.out.println(); 	
	}
}
