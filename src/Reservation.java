import java.util.Date;

public class Reservation {
    private int id;
    private Date startDate;
    private Date endDate;
    private Status enumStatus;

    //Constructor
    public Reservation(int id, Date startDate, Date endDate, Status enumStatus) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enumStatus = enumStatus;
    }

    //getter
    public int getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Status getEnumStatus() {
        return enumStatus;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", enumStatus=" + enumStatus +
                '}';
    }
}
