import DB.DBConnection;
import Domain.Entitys.Author;
import Services.AuthorDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = DBConnection.getConnection();
        if(con == null){
            System.out.println("not working");
        }else{
            System.out.println("working");
        }

        AuthorDao AD = new AuthorDao(con);
        AD.insertAuthor();
        System.out.println("------------------------");
        /*Domain.Entitys.Book myObj = new Domain.Entitys.Book(1,"bookA","ok",currentDate,40,"SA123",true);
        Domain.Entitys.Author myAuthor = new Domain.Entitys.Author(1,"victore","hego","Oscar");
        Domain.Entitys.Client myClient = new Domain.Entitys.Client(1,"saad","moumou","ha234565");
        System.out.println(myObj.getId()
                +"\nTitle: "+myObj.getTitle()
                +"\nDescription: "+myObj.getDescription()
                +"\nDatePublication: "+myObj.getDatePublication()
                +"\nQuantity: "+myObj.getQuantity()
                +"\nISBN: "+myObj.getIsbn()
                +"\nisAvailable: "+myObj.getAvailable()
        );
        System.out.println("\nID Domain.Entitys.Author: "+myAuthor.getId()
                +"\nFirst name:  "+ myAuthor.getFirstName()
                +"\nLast name:  "+ myAuthor.getLastName()
                +"\nAwards:  "+ myAuthor.getAwards()
        );
        System.out.println("\nID Domain.Entitys.Client: "+myClient.getId()
                +"\nFirst name:  "+ myClient.getFirstName()
                +"\nLast name:  "+ myClient.getLastName()
                +"\nAwards:  "+ myClient.getCin()
        );*/
    }
}
