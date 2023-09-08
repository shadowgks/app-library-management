package domain.entitys;

import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String description;
    private Date datePublication;
    private int quantity;
    private String isbn;
    private Author author;

    public Book(){}
    public Book(String title, String description, Date datePublication, int quantity, String isbn) {
        this.title = title;
        this.description = description;
        this.datePublication = datePublication;
        this.quantity = quantity;
        this.isbn = isbn;
    }

    //Getter
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    //Setter
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

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "\nBook: " + getId() +
                "\ntitle= " + getTitle() +
                "\ndescription= " + getDescription() +
                "\ndatePublication= " + getDatePublication() +
                "\nquantity= " + getQuantity() +
                "\nisbn= " + getIsbn() +
                "\nAuthors= " + getAuthor();
    }
}
