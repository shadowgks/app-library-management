package service;

import dao.ReservationDao;
import domain.entitys.Reservation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReservationService {
    private ReservationDao reservationdao;
    Scanner input = new Scanner(System.in);

    public ReservationService(Connection con){
        this.reservationdao = new ReservationDao(con);
    }

    public void insertReservation() throws SQLException {
        int generateIDR = generateRandomID();
        String isbn, cin, date_start;
        String duration;
        boolean checkBeforInsertReservation;

        do {
            System.out.println("Give me your cin: ");
            cin = input.nextLine();
        }while(!Pattern.matches("\\S+", cin));
        do {
            System.out.println("Give me ISBN book you want: ");
            isbn = input.nextLine();
        }while(!Pattern.matches("\\S+", isbn));


        Reservation reservation = new Reservation(generateIDR, "2000-02-02", 4);
        checkBeforInsertReservation = reservationdao.checkClientAndBookIfExist(isbn, cin);
        if(checkBeforInsertReservation){
            System.out.println("This is client reserved this book before");
        }else{
            reservationdao.getBookIsbn(isbn, reservation);
            reservationdao.getClientCin(cin, reservation);
            reservationdao.ReservationClient(reservation);
            reservationdao.decrementBookQuantity(reservation);
            System.out.println("Reserved Successfully");
        }
    }

    private static int generateRandomID() {
        Random random = new Random();
        return random.nextInt(1000000); // Change the range as needed
    }
}
