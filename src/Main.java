import dao.DBConnection;

import java.sql.Connection;
import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();
        if(con == null){
            System.out.println("not working");
        }else{
            System.out.println("working");
        }
        Date currentDate = new Date();
        System.out.println(currentDate);
        System.out.println("------------------------");
        /*Book myObj = new Book(1,"bookA","ok",currentDate,40,"SA123",true);
        Author myAuthor = new Author(1,"victore","hego","Oscar");
        Client myClient = new Client(1,"saad","moumou","ha234565");
        System.out.println(myObj.getId()
                +"\nTitle: "+myObj.getTitle()
                +"\nDescription: "+myObj.getDescription()
                +"\nDatePublication: "+myObj.getDatePublication()
                +"\nQuantity: "+myObj.getQuantity()
                +"\nISBN: "+myObj.getIsbn()
                +"\nisAvailable: "+myObj.getAvailable()
        );
        System.out.println("\nID Author: "+myAuthor.getId()
                +"\nFirst name:  "+ myAuthor.getFirstName()
                +"\nLast name:  "+ myAuthor.getLastName()
                +"\nAwards:  "+ myAuthor.getAwards()
        );
        System.out.println("\nID Client: "+myClient.getId()
                +"\nFirst name:  "+ myClient.getFirstName()
                +"\nLast name:  "+ myClient.getLastName()
                +"\nAwards:  "+ myClient.getCin()
        );*/
    }
}
