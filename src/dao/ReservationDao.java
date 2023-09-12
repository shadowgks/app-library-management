package dao;

import domain.entitys.Author;
import domain.entitys.Book;
import domain.entitys.Client;
import domain.entitys.Reservation;
import domain.enums.Status;

import java.sql.*;
import java.util.Random;

public class ReservationDao {
    private Connection con;

    public ReservationDao(Connection connection){this.con = connection;}

    public void ReservationClient(Reservation res) throws SQLException {
            String query = "INSERT INTO reservation (id_random, start_date, duration, statut, bookID, clientID) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, res.getIdRandom());
                preparedStatement.setDate(2, (Date) res.getStartDate());
                preparedStatement.setInt(3, res.getDuration());
                preparedStatement.setString(4, res.getEnumStatus().toString());
                preparedStatement.setInt(5, res.getBook().getId());
                preparedStatement.setInt(6, res.getClient().getId());

                preparedStatement.executeUpdate();
            }
    }

    public String updateReservationToReturned(Reservation res) throws SQLException{
        String query = "UPDATE reservation SET statut = ? WHERE id_random = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setString(1, String.valueOf(Status.Returned));
            preparedStatement.setInt(2, res.getIdRandom());

            if(preparedStatement.executeUpdate() > 0){
                return "The updated stone has been successfully returned :)";
            }else {
                return "Something is wrong!!!";
            }
        }
    }

    public boolean checkClientAndBookIfExist(Reservation res, String isbn, String cin) throws SQLException{
        String query = "SELECT * FROM reservation r JOIN client c JOIN book b\n" +
                "ON r.bookID = b.id AND r.clientID = c.id \n" +
                "WHERE b.isbn = ? AND c.cin = ? AND statut = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, cin);
            preparedStatement.setString(3, String.valueOf(Status.Borrowed));
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    res.setIdRandom(resultSet.getInt("id_random"));
                    return true;
                }
            }
        }
        return false;
    }


    public void decrementBookQuantity(Reservation res) throws SQLException {
        // Decrement the book quantity by 1
        res.getBook().setQuantity(res.getBook().getQuantity() - 1);

        // Update the database with the new quantity
        String updateQuery = "UPDATE book SET quantity = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, res.getBook().getQuantity());
            preparedStatement.setInt(2, res.getBook().getId());
            preparedStatement.executeUpdate();
        }
    }

    public void getBookIsbn(Reservation res, String isbn) throws SQLException{
        String query = "SELECT * FROM book WHERE isbn = ? AND quantity > 0";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, isbn);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setQuantity(resultSet.getInt("quantity"));
                    res.setBook(book);
                }
            }
        }
    }

    public void getClientCin(Reservation res, String cin) throws SQLException{
        String query = "SELECT * FROM client WHERE cin = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, cin);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client();
                    client.setId(resultSet.getInt("id"));
                    res.setClient(client);
                }
            }
        }
    }

    public String statisticBookBorrowed() throws SQLException{
        String statistics_book_borrowed
        , query = "SELECT COUNT(*) AS 'statistics_book_borrowed' FROM reservation \n" +
                "WHERE statut = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setString(1, String.valueOf(Status.Borrowed));
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    statistics_book_borrowed = resultSet.getString("statistics_book_borrowed");
                    return statistics_book_borrowed;
                }
            }
        }
        return null;
    }

    public String statisticBookLost() throws SQLException{
        String query = "SELECT COUNT(*) AS 'statistics_book_lost' FROM reservation \n" +
                "WHERE statut = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setString(1, String.valueOf(Status.Lost));
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    String statistics_book_lost = resultSet.getString("statistics_book_lost");
                    return statistics_book_lost;
                }
            }
        }
        return null;
    }

}
