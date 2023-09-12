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
        String isbn, regexISBN = "\\d{3}-\\d{1}-\\d{2}-\\d{6}-\\d{1}";
        try {
            Book get_book = null;
            do {
                System.out.println("Entre the ISBN: ");
                System.out.println("Ex: 000-0-00-000000-0");
                isbn = input.nextLine();
                get_book = bookDeo.readByIsbnBook(isbn);
                if (get_book == null) {
                    System.out.println("Faile try again!");
                }
            } while (!Pattern.matches(regexISBN, isbn) || get_book == null);

            return get_book;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void saveBook(){
        String title, description, date_publication, isbn, firstname_author, lastname_author;
        boolean check_author = false;
        String regexISBN = "\\d{3}-\\d{1}-\\d{2}-\\d{6}-\\d{1}";
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
            } while (!Pattern.matches(regexISBN, isbn));
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
            while(!check_author){
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
                    bookDeo.saveBook(book);
                } else {
                    System.out.println("This is author not found!!");
                }
            }

            System.out.println("Added Book Successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Book> searchBook(){
        try{
            return bookDeo.searchBook();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void statisticBook() {
        try{
            String count_book = bookDeo.statisticBook();
            System.out.println("Total Books: "+count_book);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
