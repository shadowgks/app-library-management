package domain.entitys;

public class Author{
    private int id;
    private String firstName;
    private String lastName;
    private String awards;

    // Constructor
    public Author(){};
    public Author(String firstName, String lastName, String awards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.awards = awards;
    }

    //Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAwards(String awards) {
       this.awards = awards;
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
        return awards;
    }

    @Override
    public String toString() {
        return "Author: " + id + "\n" +
                "firstName= " + firstName + '\n' +
                "lastName= " + lastName + '\n' +
                "Awards= " + awards + '\n';
    }
}

