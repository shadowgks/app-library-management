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

    public void saveReservation(){
            int generateIDR = generateRandomID();
            String isbn, cin;
            int duration = 0;
            String regexISBN = "\\d{3}-\\d{1}-\\d{2}-\\d{6}-\\d{1}";
            boolean checkBeforeInsertReservation;
            // Get the current LocalDate
            LocalDate currentDate = LocalDate.now();

            try{
                do {
                    System.out.println("Enter your cin: ");
                    cin = input.nextLine();
                }while(!Pattern.matches("\\S+", cin));
                do {
                    System.out.println("Enter ISBN book you want: ");
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
                checkBeforeInsertReservation = reservationdao.checkClientAndBookIfExist(reservation, isbn, cin);
                if(checkBeforeInsertReservation){
                    System.out.println("This is client still reserved this book borrowed!");
                }else{
                    reservationdao.getBookIsbn(reservation, isbn);
                    reservationdao.getClientCin(reservation, cin);
                    reservationdao.ReservationClient(reservation);
                    reservationdao.BookQuantityDicOrInc(reservation, Status.Borrowed);
                    System.out.println("Reserved Successfully :)");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
    }

    public String updateReservationToReturned(){
        String isbn, cin;
        String regexISBN = "\\d{3}-\\d{1}-\\d{2}-\\d{6}-\\d{1}";
        boolean check_client_book;

        try {
            do {
                System.out.println("Enter your cin: ");
                cin = input.nextLine();
            }while(!Pattern.matches("\\S+", cin));
            do {
                System.out.println("Enter ISBN book you want: ");
                System.out.println("Ex: 000-0-00-000000-0");
                isbn = input.nextLine();
            }while(!Pattern.matches(regexISBN, isbn));

            Reservation reservation = new Reservation();
            check_client_book = reservationdao.checkClientAndBookIfExist(reservation, isbn, cin);
            if(check_client_book){
                reservationdao.getBookIsbn(reservation, isbn);
                reservationdao.getClientCin(reservation, cin);
                reservationdao.BookQuantityDicOrInc(reservation, Status.Returned);
                reservationdao.updateReservationToReturned(reservation);
                System.out.println("Reserved Successfully :)");
            }else{
                System.out.println("Sorry this book was returned before!!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private static int generateRandomID() {
        Random random = new Random();
        return random.nextInt(1000000); // Change the range as needed
    }

    public String statisticBookBorrowed() {
        try{
            return reservationdao.statisticBookBorrowed();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public String statisticBookLost() {
        try{
            return reservationdao.statisticBookLost();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
