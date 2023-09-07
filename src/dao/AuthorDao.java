package dao;

import domain.entitys.Author;
import domain.entitys.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    private Connection con;

    public AuthorDao(Connection connection) {
        this.con = connection;
    }

    public List<Author> readAllAuthor() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM author";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Author author = new Author();
                    author.setFirstName(resultSet.getString("first_name"));
                    author.setLastName(resultSet.getString("last_name"));
                    author.setAwards(resultSet.getString("awards"));

                    //set data in List
                    authors.add(author);
                }
            }
            return authors;
        }
    }

    public Author readByIDAuthor(int id) throws SQLException {
        String query = "SELECT * FROM author WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                preparedStatement.setInt(1, id);
                if (resultSet.next()) {
                    Author author = new Author();
                    author.setFirstName(resultSet.getString("first_name"));
                    author.setLastName(resultSet.getString("last_name"));
                    author.setAwards(resultSet.getString("awards"));

                    return author;
                }
            }
        }
        return null;
    }

    public void insertAuthor() throws SQLException {
        String query = "INSERT INTO author (first_name, last_name, awards) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, "name2");
            preparedStatement.setString(2, "last2");
            preparedStatement.setString(3, "award2");

            preparedStatement.executeUpdate();
        }
    }

    public void updateAuthor(Author auth) throws SQLException {
        String query = "UPDATE author SET first_name = ?, last_name = ?, awards = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, auth.getFirstName());
            preparedStatement.setString(2, auth.getLastName());
            preparedStatement.setString(3, auth.getAwards());
            preparedStatement.setInt(4, auth.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteAuthor(int id) throws SQLException {
        String query = "DELETE FROM author WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.getErrorCode();
        }
    }




}
