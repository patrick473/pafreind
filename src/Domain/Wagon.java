package Domain;

public class Wagon extends Component {

    Integer amountOfSeats;
    Integer wagonID;

    public Wagon(String name, Integer position, Integer amountOfSeats, Integer wagonID) {
        super(name, position);
        if (amountOfSeats== 0){ amountOfSeats=20;}
        this.amountOfSeats = amountOfSeats;
        this.wagonID = wagonID;
    }
}
