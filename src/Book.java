import java.util.Date;

public class Book {

    private int id;
    private String title;
    private String description;
    private Date datePublication;
    private int quantity;
    private String isbn;
    private Boolean isAvailable;

    public Book(int id, String title, String description, Date datePublication, int quantity, String isbn, Boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.datePublication = datePublication;
        this.quantity = quantity;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }
}
