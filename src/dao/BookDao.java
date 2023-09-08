package dao;
import domain.entitys.Author;
import domain.entitys.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String query = "SELECT * FROM book b INNER JOIN author a ON b.authorID = a.id";
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
                    author.setId(resultSet.getInt("id"));
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

    public List<Book> SearchBook() throws SQLException {
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        String query = "SELECT * FROM book WHERE title LIKE CONCAT('%', ?, '%')";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    book.setTitle(resultSet.getString("title"));
                    book.setDescription(resultSet.getString("description"));
                    book.setDatePublication(resultSet.getDate("date_publication"));
                    book.setQuantity(resultSet.getInt("quantity"));
                    book.setIsbn(resultSet.getString("isbn"));

                    books.add(book);
                }
            }
            return books;
        }
    }

    public void insertBook() throws SQLException {
        String query = "INSERT INTO book (title, description, date_publication, quantity, isbn, authorID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, "title1");
            preparedStatement.setString(2, "description1");
            preparedStatement.setDate(3, java.sql.Date.valueOf("2013-09-04"));
            preparedStatement.setInt(4, 50);
            preparedStatement.setString(5, "isbn1");
            preparedStatement.setInt(6, 6);

            preparedStatement.executeUpdate();
        }
    }

    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE book SET title= ?, description= ?, date_publication= ?, quantity= ?, isbn= ?, authorID= ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, "title2");
            preparedStatement.setString(2, "descriptio2");
            preparedStatement.setDate(3, java.sql.Date.valueOf("2013-09-04"));
            preparedStatement.setInt(4, 50);
            preparedStatement.setString(5, "isbn2");
            preparedStatement.setInt(6, 2);
            preparedStatement.setInt(7, 1);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteBook(int id) throws SQLException {
        String query = "DELETE FROM book WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }


}
