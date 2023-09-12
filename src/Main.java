import domain.entitys.Book;
import service.AuthorService;
import service.BookService;
import service.ReservationService;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            System.out.println("not working");
        } else {
            System.out.println("working");
        }

        AuthorService authorS = new AuthorService(con);
        BookService bookS = new BookService(con);
        ReservationService resS = new ReservationService(con);
//        System.out.println(authorS.readAllAuthor());
//        authorS.insertAuthors();
//        resS.insertReservation();
//        bookS.statisticBook();
//        resS.statisticBookBorrowed();
//        resS.statisticBookLost();


        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n\n\n------------------------------------------");

            System.out.println("Choose an action:");
            System.out.println("1. Create New Book");
            System.out.println("2. Edit a Book");
            System.out.println("3. Delete a Book");
            System.out.println("4. Show All Books");
            System.out.println("5. Search Book By Title");
            System.out.println("6. Search Book By Author");
            System.out.println("7. Borrow  Book");
            System.out.println("8. return  Book");
            System.out.println("9. Generate Statistics");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Book readByIsbnBook = bookS.readByIsbnBook();
                    System.out.println(readByIsbnBook);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:
                    System.out.println("Exiting the program.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }


            System.out.println("------------------------");
        }
    }
}
