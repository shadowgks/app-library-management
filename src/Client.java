public class Client extends Person {
    private String cin;

    public Client(String firstName, String lastName, String cin) {
        super(firstName, lastName);
        this.cin = cin;
    }

    public String getCin() {
        return cin;
    }
}
