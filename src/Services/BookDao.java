package Services;
import DB.DBConnection;
import Domain.Entitys.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookDao {



    List<Book> findAll(){
        return null;
    };

    Book findAllByID(int id){
        return null;
    }

    void save(Book book){}

    void delete(int id){}

}
