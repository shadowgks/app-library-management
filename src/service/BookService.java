package service;

import domain.entitys.Book;
import dao.BookDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookService {
//    private BookDao bookDeo;
    private BookDao bookDeo;

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
            return bookDeo.readByIsbnBook("978-0-9767736-6-5");
        }catch (SQLException e){
            e.printStackTrace();
            return null;
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
