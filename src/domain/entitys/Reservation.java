package domain.entitys;

import domain.enums.Status;

import java.util.Date;

public class Reservation {
    private int id;
    private int idRandom;
    private Date startDate;
    private int duration;
    private Status enumStatus;
    private Book book;
    private Client client;

    //Constructor
    public Reservation(){}


    public Reservation(int idRandom, Date startDate, int duration, Status enumStatus) {
        this.idRandom = idRandom;
        this.startDate = startDate;
        this.duration = duration;
        this.enumStatus = enumStatus;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setIdRandom(int idRandom) {
        this.idRandom = idRandom;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEnumStatus(Status enumStatus) {
        this.enumStatus = enumStatus;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //getter
    public int getId() {
        return id;
    }

    public int getIdRandom() {
        return idRandom;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Status getEnumStatus() {
        return enumStatus;
    }

    public int getDuration() {
        return duration;
    }

    public Book getBook() {
        return book;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", idRandom=" + idRandom +
                ", startDate='" + startDate + '\'' +
                ", duration=" + duration +
                ", enumStatus=" + enumStatus +
                ", book=" + book +
                ", client=" + client +
                '}';
    }
}
