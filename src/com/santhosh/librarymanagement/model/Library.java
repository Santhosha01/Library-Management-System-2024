package com.santhosh.librarymanagement.model;

import java.util.List;

public class Library {

	private String libraryName;
	private static int startid=1;
	private int libraryId;
	public int getLibraryId() {
		return libraryId;
	}

	public void setLibraryId() {
		this.libraryId = startid++;
	}

	private long phoneNumber;
	private String mailId;
	private String address;
//    private List<Book> books;
//	public List<Book> getBooks() {
//		return books;
//	}
//
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
