package service;

import domain.entitys.Author;
import domain.entitys.Book;
import dao.BookDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BookService {
//    private BookDao bookDeo;
    private BookDao bookDeo;
    Scanner input = new Scanner(System.in);

    public BookService(Connection con){
        this.bookDeo = new BookDao(con);
    }

    public List<Book> readAllBook(){
        try {
            return bookDeo.readAllBook();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Book readByIsbnBook(){
        try {
            return bookDeo.readByIsbnBook();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void insertBook(){
        String title, description, date_publication, isbn, firstname_author, lastname_author;
        boolean check_author;
        int quantity = 0;

        try {
            do {
                System.out.println("Entre the title: ");
                title = input.nextLine();
            } while (!Pattern.matches("\\S+", title));
            do {
                System.out.println("Entre the description: ");
                description = input.nextLine();
            } while (!Pattern.matches("\\S+", description));
            do {
                System.out.println("Entre the date publication: ");
                date_publication = input.nextLine();
            } while (!Pattern.matches("\\S+", date_publication));
            do {
                System.out.println("Entre the ISBN: ");
                System.out.println("Ex: 000-0-00-000000-0");
                isbn = input.nextLine();
            } while (!Pattern.matches("\\S+", isbn));
            do {
                System.out.println("Enter quantity of the book: ");
                if (input.hasNextInt()) {
                    quantity = input.nextInt();
                    if (quantity <= 0) {
                        System.out.println("Duration must be a positive integer.");
                    }
                } else {
                    System.out.println("Please enter a number.");
                    input.next();
                    quantity = 0; //Reset duration to an invalid value
                }
            } while (quantity <= 0);

            //author

            do {
                do {
                    System.out.println("Entre first name author: ");
                    firstname_author = input.nextLine();
                } while (!Pattern.matches("\\S+", firstname_author));
                do {
                    System.out.println("Entre last name author: ");
                    lastname_author = input.nextLine();
                } while (!Pattern.matches("\\S+", lastname_author));

                Book book = new Book(title, description, java.sql.Date.valueOf(date_publication), quantity, isbn);
                check_author = bookDeo.checkAuthor(book, firstname_author, lastname_author);

                if (check_author) {
                    bookDeo.checkAuthor(book, firstname_author, lastname_author);
                    bookDeo.insertBook(book);
                } else {
                    System.out.println("Not found this author!!");
                }
            } while (!check_author);

            System.out.println("Insert Book Successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Book> searchBook(){
        try{
            return bookDeo.SearchBook();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
