package Domain;

public class Wagon extends Component {

    Integer amountOfSeats;
    Integer wagonID;


    public Wagon(String name, Integer amountOfSeats, Integer wagonID) {
        super(name);
        if (amountOfSeats== 0){ amountOfSeats=20;}
        this.amountOfSeats = amountOfSeats;
        this.wagonID = wagonID;
    }

    public Integer getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(Integer amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public Integer getWagonID() {
        return wagonID;
    }

    public void setWagonID(Integer wagonID) {
        this.wagonID = wagonID;
    }

    @Override
    public String toString() {
        return "Wagon{" +
                "amountOfSeats=" + amountOfSeats +
                ", wagonID=" + wagonID +
                ", name='" + name + '\'' +
                '}';
    }
}
