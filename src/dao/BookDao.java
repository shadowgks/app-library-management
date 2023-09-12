package dao;
import domain.entitys.Author;
import domain.entitys.Book;

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
        Book book = new Book();
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    //book
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

    public List<Book> searchBook() throws SQLException {
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

    public void deleteBook(int isbn) throws SQLException {
        String query = "DELETE FROM book WHERE isbn = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, isbn);

            preparedStatement.executeUpdate();
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
