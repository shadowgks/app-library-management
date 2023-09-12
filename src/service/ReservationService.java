package service;

import dao.ReservationDao;
import domain.entitys.Reservation;
import domain.enums.Status;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDate;

public class ReservationService {
    private ReservationDao reservationdao;
    Scanner input = new Scanner(System.in);

    public ReservationService(Connection con){
        this.reservationdao = new ReservationDao(con);
    }

    public void saveReservation() throws SQLException {
            int generateIDR = generateRandomID();
            String isbn, cin;
            int duration = 0;
            String regexISBN = "\\d{3}-\\d{1}-\\d{2}-\\d{6}-\\d{1}";
            boolean checkBeforeInsertReservation;
            // Get the current LocalDate
            LocalDate currentDate = LocalDate.now();

            do {
                System.out.println("Give me your cin: ");
                cin = input.nextLine();
            }while(!Pattern.matches("\\S+", cin));
            do {
                System.out.println("Give me ISBN book you want: ");
                System.out.println("Ex: 000-0-00-000000-0");
                isbn = input.nextLine();
            }while(!Pattern.matches(regexISBN, isbn));
            do {
                System.out.println("Give me duration returned the book: ");
                if (input.hasNextInt()) {
                    duration = input.nextInt();
                    if (duration <= 0) {
                        System.out.println("Duration must be a positive integer.");
                    }
                } else {
                    System.out.println("Please enter a number.");
                    input.next();
                    duration = 0; //Reset duration to an invalid value
                }
            }while(duration <= 0);

            Reservation reservation = new Reservation(generateIDR, java.sql.Date.valueOf(currentDate), duration, Status.Borrowed);
            checkBeforeInsertReservation = reservationdao.checkClientAndBookIfExist(isbn, cin);
            if(checkBeforeInsertReservation){
                System.out.println("This is client still reserved this book borrowed!");
            }else{
                reservationdao.getBookIsbn(reservation, isbn);
                reservationdao.getClientCin(reservation, cin);
                reservationdao.ReservationClient(reservation);
                reservationdao.decrementBookQuantity(reservation);
                System.out.println("Reserved Successfully :)");
            }
    }

    private static int generateRandomID() {
        Random random = new Random();
        return random.nextInt(1000000); // Change the range as needed
    }

    public void statisticBookBorrowed() {
        try{
            String count_book_borrowed = reservationdao.statisticBookBorrowed();
            System.out.println("Total Books Borrowed: "+count_book_borrowed);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void statisticBookLost() {
        try{
            String count_book_lost = reservationdao.statisticBookLost();
            System.out.println("Total Books Lost: "+count_book_lost);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
