package com.santhosh.librarymanagement.databaseManagemet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.santhosh.librarymanagement.model.Book;
import com.santhosh.librarymanagement.model.Credentials;
import com.santhosh.librarymanagement.model.Library;
import com.santhosh.librarymanagement.model.User;

public class LibraryDatabase {
	private static LibraryDatabase libraryDatabase;
	private Library library;
	private List<Book> books;
	private List<User> userList;
	private List<Map<String, Book>> issuedBooks;
	private Credentials credential;
	private List<Credentials> userCredentials;
	private String bookFile="C:\\Users\\santh\\eclipse-workspace\\Library Management 2024\\src\\com\\santhosh\\librarymanagement\\JsonFile\\book.json";
	private String userFile="C:\\Users\\santh\\eclipse-workspace\\Library Management 2024\\src\\com\\santhosh\\librarymanagement\\JsonFile\\user.json";
    private String mappedBookFile="C:\\Users\\santh\\eclipse-workspace\\Library Management 2024\\src\\com\\santhosh\\librarymanagement\\JsonFile\\mappedBooks.json";
	private String UserCredentials="C:\\Users\\santh\\eclipse-workspace\\Library Management 2024\\src\\com\\santhosh\\librarymanagement\\JsonFile\\UserCredentials.json";
    private String libraryFile="C:\\Users\\santh\\eclipse-workspace\\Library Management 2024\\src\\com\\santhosh\\librarymanagement\\JsonFile\\Library.json";
	
	//	private  List<Map<String, Book>> existingmappedbooks;
//	private  List<Book> existingBooks;
//	private List<User> existingUsers;
    ObjectMapper mapper=new ObjectMapper();
	
	private LibraryDatabase() {
//		this.existingUsers=new ArrayList<User>();
//		this.existingBooks=new ArrayList<Book>();
//		this.existingmappedbooks=new ArrayList<Map<String,Book>>();
		this.books = new ArrayList<>();
		this.userList = new ArrayList<>();
		this.issuedBooks = new ArrayList<>();
		this.userCredentials=new ArrayList<>();
	}

	public static LibraryDatabase getInstance() {
		if (libraryDatabase == null) {
			libraryDatabase = new LibraryDatabase();
		}
		return libraryDatabase;
	}
	
	
	
	public Library getLibrary() {
		ObjectMapper libraryObj = new ObjectMapper();
		try {
			return libraryObj.readValue(new File(libraryFile), Library.class);
		} catch (Exception e) {
			return null;
		}		
	}

	public void insertLibrary(Library library) {
		this.library = library;
		try {
			File file = new File(libraryFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(file, library);
		} catch (Exception e) {
		}
	}

	public boolean insertBooks(Book book) {
		System.out.println("book");
		ObjectMapper bookObj = new ObjectMapper();
		try {
			File fileCandidate = new File(bookFile);
			if (!fileCandidate.exists())
			{
				fileCandidate.createNewFile();}
			if (fileCandidate.length() > 0) {// verifying that the length of the file is not empty
				System.out.println("length");
				books = bookObj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
				});
				for (Book b : books) {
					if (b.getName().equals(book.getName()))
						return false;
				}
				
				books.add(book);
				bookObj.writeValue(fileCandidate, books);
				return true;
			} else {
					books.add(book);		
				bookObj.writeValue(fileCandidate, books);
				return true;}
		} catch (Exception e) {
			System.out.println("interviewer");
		}
		return false;
		
	}

	public List<Book> showBooks() {
		ObjectMapper bookObj = new ObjectMapper();
		try {
			return books = bookObj.readValue(new File(bookFile),
					new TypeReference<List<Book>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	public boolean insertUser(User user) {
		
		ObjectMapper userObj = new ObjectMapper();
		try {
			File fileCandidate = new File(userFile);
			if (!fileCandidate.exists())
			{	System.out.println("file");
				fileCandidate.createNewFile();}
			if (fileCandidate.length() > 0) {// verifying that the length of the file is not empty
				userList = userObj.readValue(new File(userFile), new TypeReference<List<User>>() {
				});
				for (User u : userList) {
					if (u.getMailId().equals(user.getMailId()))
						return false;
				}
				userList.add(user);
				userObj.writeValue(fileCandidate, userList);
				return true;
			} else { 
				
				userList.add(user);
				userObj.writeValue(fileCandidate, userList);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("User");
		}
		return false;
		
	}

	public void removeandUpdateUser(User updateUser) {
		System.out.println("update");
		ObjectMapper userObj=new ObjectMapper();
		try {
		userList=userObj.readValue(new File(userFile), new TypeReference<List<User>>() {});; 
		for(User u:userList) {
			 if(u.getUserName().equals(updateUser.getUserName())) {
				 System.out.println("update");
				 FileWriter file=new FileWriter(userFile,false);
				 userList.remove(u);
				 System.out.println(insertUser(updateUser));
				 break;
			 }
		 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<User> showUsers() {
		ObjectMapper userObj = new ObjectMapper();
		try {
			return userList = userObj.readValue(new File(userFile),
					new TypeReference<List<User>>() {});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}

	public void insertIssuedBooks(Map<String, Book> mapbooktocustomer) {
		issuedBooks.add(mapbooktocustomer);
		try {
        	System.out.println("file");
            File file = new File(mappedBookFile);
            if (!file.exists()) {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<>());
            }
  
            List<Map<String, Book>> existingmappedbooks = mapper.readValue(file,
                    new TypeReference<List<Map<String, Book>>>() {});

            existingmappedbooks.add(mapbooktocustomer);
            mapper.writeValue(file, existingmappedbooks);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	public List<Map<String, Book>> showIssuedBooks() {
		try {
             issuedBooks = mapper.readValue(new File(mappedBookFile),
                     new TypeReference<List<Map<String,Book>>>() {});
         } catch (IOException e) {
             e.printStackTrace();
         }
		return issuedBooks;

	}

	public void insertCredentials(Credentials credential) {
       this.credential=credential;
	}
	
	public Credentials getCredentials() {
		return credential;
	}
    
	public void insertUserCredentials(Credentials credential) {
		userCredentials.add(credential);
		try {
            File file = new File(UserCredentials);
            if (!file.exists()) {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<>());
            }
  
            List<Credentials> existinguserCredentials = mapper.readValue(file,
                    new TypeReference<List<Credentials>>() {});
             existinguserCredentials.add(credential);   
             mapper.writeValue(file, existinguserCredentials);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public List<Credentials> getUserCredentials() {
		try {
			userCredentials = mapper.readValue(new File(UserCredentials),
                    new TypeReference<List<Credentials>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
		return userCredentials;
	}

	public void removeandUpdateBook(Book updateBook) {
		ObjectMapper bookObj=new ObjectMapper();
		try {
		books=bookObj.readValue(new File(bookFile), new TypeReference<List<Book>>() {});; 
		for(Book b:books) {
			 if(b.getName().equals(updateBook.getName())) {
				 System.out.println("update");
				 FileWriter file=new FileWriter(bookFile,false);
				 books.remove(b);
				 System.out.println(insertBooks(updateBook));
				 break;
			 }
		 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(Book deleteBook) {
		ObjectMapper deleteObj=new ObjectMapper();
		int count=1;
		try {
		books=deleteObj.readValue(new File(bookFile), new TypeReference<List<Book>>() {});; 
		for(Book b:books) {
			 if(b.getName().equals(deleteBook.getName())) {
				 System.out.println("delete");
				 FileWriter file=new FileWriter(bookFile,false);
				 books.remove(b);
//				 break;
			 }
			 count++;
		 }
		System.out.println(count);
		 for(Book b:books) {
			 System.out.println(books.size());
//			 if(books.size())
			 insertBooks(b);
			 System.out.println(b.getName());
			 
		 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    }


}

