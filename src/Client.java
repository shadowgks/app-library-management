public class Client extends Person{
    private String cin;

    public Client(int id, String firstName, String lastName, String cin) {
        super(id, firstName, lastName);
        this.cin = cin;
    }

    public String getCin() {
        return cin;
    }
}
