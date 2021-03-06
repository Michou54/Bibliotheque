package model;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.jasper.tagplugins.jstl.ForEach;

public class Library {
	private ArrayList<Book> books;
	private ArrayList<Borrow> borrows;
	private ArrayList<Reservation> reservations;
	private ArrayList<User> users;

	public Library(ArrayList<Book> books,ArrayList<Borrow> borrows, ArrayList<Reservation> reservations, ArrayList<User> users) {
		this.books = books;
		this.borrows = borrows;
		this.reservations = reservations;
		this.users = users;
	}
	
	
	public ArrayList<User> getUsers() {
		return users;
	}


	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}


	public ArrayList<Borrow> getBorrows() {
		return borrows;
	}


	public void setBorrows(ArrayList<Borrow> borrows) {
		this.borrows = borrows;
	}


	public ArrayList<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}


	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public int nbCopyRemaining(Book book) {
		int nbOfCopyRemaining = book.getNbCopy();
		for (Borrow borrow : borrows) {
			if (borrow.getBook() == book) {
				nbOfCopyRemaining--;
			}
		}
		for (Reservation reservation : reservations) {
			if (reservation.getBook() == book) {
				nbOfCopyRemaining--;
			}
		}
		return nbOfCopyRemaining;
	}
	
	public int nbReservationRemaining(Book book){
		int nbOfCopyRemaining = nbCopyRemaining(book);
		for (Reservation reservation : reservations) {
			if(reservation.getBook() == book){
				nbOfCopyRemaining--;
			}
		}
		return nbOfCopyRemaining;
	}
	
	public void addBook(Book book){
		books.add(book);
	}
	
	public void deleteBook(Book book){
		books.remove(book);
	}
	
	public void deleteCopyBook(Book book, int copyToDelete){
		for (Book b: books) {
			if (b.equals(book)){
				b.setNbCopy(b.getNbCopy() - copyToDelete);
			}
		}
	}
	
	public Book getBook(String title, String author){
		for (Book book : books) {
			if (book.getTitle().equals(title) && book.getAuthor().equals(author)){
				return book;
			}			
		};
		return null; 
	}
	
	public User getUserFromLogin(String login){
		for (User user : users) {
			if (user.getLogin().equals(login)){
				return user;
			}
		}
		return null;
	}
	
	public Borrow getBorrow(Member member, Book book){
		for (Borrow borrow : borrows) {
			if (borrow.getMember().equals(member) && borrow.getBook().equals(book)){
				return borrow;
			}
		}
		return null;
	}
	
	public Reservation getReservation(Member member, Book book){
		for (Reservation reservation : reservations) {
			if (reservation.getMember().equals(member) && reservation.getBook().equals(book)){
				return reservation;
			}
		}
		return null;
	}
}
