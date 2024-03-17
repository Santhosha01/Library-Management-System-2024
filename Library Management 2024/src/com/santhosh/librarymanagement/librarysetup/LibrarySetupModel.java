package com.santhosh.librarymanagement.librarysetup;

import com.santhosh.librarymanagement.databaseManagemet.LibraryDatabase;
import com.santhosh.librarymanagement.model.Library;

public class LibrarySetupModel {
	private LibrarySetupView librarySetupView;

	private Library library;

	LibrarySetupModel(LibrarySetupView librarySetupView) {
		this.librarySetupView = librarySetupView;
		library=LibraryDatabase.getInstance().getLibrary();
	}

	public void setLibraryDetails(String libraryName, int libraryId, long phoneNumber, String mailId, String address) {
	   Library library=new Library();
	     library.setLibraryName(libraryName);
	     library.setLibraryId(libraryId);
	     library.setPhoneNumber(phoneNumber);
	     library.setMailId(mailId);
	     library.setAddress(address);
	     createLibrary(library);
      
	}
	public void startSetup() {
		if (library == null || library.getLibraryId() == 0) {
			librarySetupView.initiateSetup();
		} else {
			librarySetupView.onSetupComplete();
		}

	}
	public void createLibrary(Library library) {
		if(checkPhoneNumber(library)&&checkMailId(library)) {
			LibraryDatabase.getInstance().insertLibrary(library);
			librarySetupView.showAlert("\nLibrarySetup Completed");
			librarySetupView.onSetupComplete();
		}
		else {
			librarySetupView.showAlert("\nInvalid Library Details");
		}
	}
	public boolean checkPhoneNumber(Library library) {
		String phoneNumber=""+library.getPhoneNumber();
		if((phoneNumber.length()==10)&&!(phoneNumber.contains("0000000000")))
		{return true;}
		return false;
	}
	public boolean checkMailId(Library library) {
		int countAt=0,countdot=0;
		if(library.getMailId().contains("@")&&library.getMailId().length()>=6&&!(library.getMailId().charAt(0)=='@')&&library.getMailId().length()>=6) {
		for (int i = 0; i < library.getMailId().length(); i++) {
			if(library.getMailId().charAt(i)=='.') {
				if(i+1<library.getMailId().length()&&library.getMailId().charAt(i+1)=='.') {
					return false;
				}
			}
			if(((library.getMailId().charAt(i)>=32&&library.getMailId().charAt(i)<=45)||(library.getMailId().charAt(i)==47)||(library.getMailId().charAt(i)>=58&&		
					library.getMailId().charAt(i)<=63)||(library.getMailId().charAt(i)>=91&&library.getMailId().charAt(i)<=96)||(library.getMailId().charAt(i)>=123&&library.getMailId().charAt(i)<=126))) {
				return false;
			}
			if(library.getMailId().charAt(i)==64) {
				countAt++;
				if(countAt>=2) {
					return false;
				}
				if(((i-1)<library.getMailId().length()&&library.getMailId().charAt(i-1)=='.')||
						((i+1)<library.getMailId().length()&&library.getMailId().charAt(i+1)=='.')){
					return false;
				}
				int j=i;
				while(j+1<library.getMailId().length()) {
					if(library.getMailId().charAt(j+1)>='0'&&library.getMailId().charAt(j+1)<='9') {
						return false;
					}
					if(library.getMailId().charAt(j+1)=='.'){
						countdot++;
					}
					j++;
				}
				if(countdot>2) {
					return false;
				}
			}
			return true;
		}
		}
		return false;
	}
}
