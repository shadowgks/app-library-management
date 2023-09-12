package dao;
import domain.entitys.Author;
import domain.entitys.Book;

import javax.swing.*;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private Connection con;

    public BookDao(){};
    public BookDao(Connection connection) {
        this.con = connection;
    }

    public List<Book> readAllBook() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book b INNER JOIN author a ON b.authorID = a.id WHERE quantity > 0";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    //book
                    Book book = new Book();
                    book.setTitle(resultSet.getString("title"));
                    book.setDescription(resultSet.getString("description"));
                    book.setDatePublication(resultSet.getDate("date_publication"));
                    book.setQuantity(resultSet.getInt("quantity"));
                    book.setIsbn(resultSet.getString("isbn"));
                    //author
                    Author author = new Author();
                    author.setFirstName(resultSet.getString("first_name"));
                    author.setLastName(resultSet.getString("last_name"));
                    author.setAwards(resultSet.getString("awards"));
                    book.setAuthor(author);

                    //set data in List
                    books.add(book);
                }
            }
            return books;
        }
    }

    public Book readByIsbnBook(String isbn) throws SQLException {
        String query = "SELECT * FROM book b INNER JOIN author a ON b.authorID = a.id WHERE b.isbn = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    //book
                    Book book = new Book();
                    book.setTitle(resultSet.getString("title"));
                    book.setDescription(resultSet.getString("description"));
                    book.setDatePublication(resultSet.getDate("date_publication"));
                    book.setQuantity(resultSet.getInt("quantity"));
                    book.setIsbn(resultSet.getString("isbn"));
                    //author
                    Author author = new Author();
                    author.setFirstName(resultSet.getString("first_name"));
                    author.setLastName(resultSet.getString("last_name"));
                    author.setAwards(resultSet.getString("awards"));

                    book.setAuthor(author);
                    return book;
                }
            }
        }
        return null;
    }


    public List<Book> searchBook(String name, String title, String firstname_author, String lastname_author) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "";

        if(name == "title"){
            query = "SELECT * FROM \n" +
                    "book b JOIN author a \n" +
                    "ON b.authorID = a.id\n" +
                    "WHERE b.title LIKE CONCAT('%',?,'%')";
        }else if(name == "author"){
            query = "SELECT * FROM \n" +
                    "book b JOIN author a \n" +
                    "ON b.authorID = a.id\n" +
                    "WHERE a.first_name LIKE CONCAT('%',?,'%') OR a.first_name LIKE CONCAT('%',?,'%')";
        }

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            if(name == "title"){
                preparedStatement.setString(1, title);
            }else if(name == "author"){
                preparedStatement.setString(1, firstname_author);
                preparedStatement.setString(2, lastname_author);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setTitle(resultSet.getString("title"));
                    book.setDescription(resultSet.getString("description"));
                    book.setDatePublication(resultSet.getDate("date_publication"));
                    book.setQuantity(resultSet.getInt("quantity"));
                    book.setIsbn(resultSet.getString("isbn"));

                    Author author = new Author();
                    author.setFirstName(resultSet.getString("first_name"));
                    author.setLastName(resultSet.getString("last_name"));
                    author.setAwards(resultSet.getString("awards"));

                    book.setAuthor(author);
                    books.add(book);
                }
            }
            return books;
        }
    }

    public void saveBook(Book book) throws SQLException {
        String query = "INSERT INTO book (title, description, date_publication, quantity, isbn, authorID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setDate(3, (Date) book.getDatePublication());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setString(5, book.getIsbn());
            preparedStatement.setInt(6, book.getAuthor().getId());

            preparedStatement.executeUpdate();
        }
    }

    public void updateBook(Book book, String isbn) throws SQLException {
        String query = "UPDATE book SET title= ?, description= ?, date_publication= ?, quantity= ?, authorID= ? WHERE isbn = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setDate(3, (Date) book.getDatePublication());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getAuthor().getId());
            preparedStatement.setString(6, isbn);

            preparedStatement.executeUpdate();
        }
    }

    public Boolean deleteBook(String isbn) throws SQLException {
        String query = "DELETE FROM book WHERE isbn = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, isbn);

            if(preparedStatement.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean checkAuthor(Book book, String first_name, String last_name) throws SQLException {
        String query = "SELECT id FROM author WHERE first_name = ? AND last_name = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Author author = new Author();
                    author.setId(resultSet.getInt("id"));
                    book.setAuthor(author);
                    return true;
                }
            }
        }
        return false;
    }

    public String statisticBook() throws SQLException{
        String query = "SELECT COUNT(*) AS 'statistics_book' FROM book;";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    String statistics_book = resultSet.getString("statistics_book");
                    return statistics_book;
                }
            }
        }
        return null;
    }

}
