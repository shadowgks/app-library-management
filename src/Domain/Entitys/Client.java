package Domain.Entitys;

public class Client{
    private int id;
    private String firstName;
    private String lastName;
    private String cin;


    //Constructor
    public Client(int id, String firstName, String lastName, String cin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cin = cin;
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

    public String getCin() {
        return cin;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cin='" + cin + '\'' +
                '}';
    }
}
