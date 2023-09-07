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

    public List<Book> searchBook(){
        try{
            return bookDeo.SearchBook();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
