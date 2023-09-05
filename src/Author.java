import java.util.Scanner;

public class Author extends Person{
    private String Awards;

    public Author(){
    };


    public Author(String firstName, String lastName, String awards) {
        super(firstName, lastName);
        Awards = awards;
    }

    //getter
    public String getAwards() {
        return Awards;
    }

    //setter
    public void setAwards(String awards) {
        Awards = awards;
    }
}

