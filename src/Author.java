public class Author extends Person{
    private String Awards;

    public Author(int id, String firstName, String lastName, String awards) {
        super(id, firstName, lastName);
        Awards = awards;
    }

    public String getAwards() {
        return Awards;
    }
}
