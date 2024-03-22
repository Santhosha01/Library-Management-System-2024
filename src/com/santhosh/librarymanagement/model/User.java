package com.santhosh.librarymanagement.model;

public class User {

	private String userName;
	private static int startid = 1;
	private int userId;
	private long phoneNumber;
	private String mailId;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId() {
		this.userId = startid++;
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

	@Override
	public String toString() {
		return "   User Details : \n\n User Name= " + userName + "\n User ID = " + userId + "\n Phone Number= "
				+ phoneNumber + "\n Mail ID= " + mailId;
	}

}
