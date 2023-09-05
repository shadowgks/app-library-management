import java.util.Scanner;

public class Author{
    private int id;
    private String firstName;
    private String lastName;
    private String Awards;

    // Constructor
    public Author(int id, String firstName, String lastName, String awards) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Awards = awards;
    }

    //getter
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAwards() {
        return Awards;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Awards='" + Awards + '\'' +
                '}';
    }
}

