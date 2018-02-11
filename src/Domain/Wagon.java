package Domain;

public class Wagon extends Component {

    Integer amountOfSeats;
    Integer wagonID;




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

    public static final class WagonBuilder {
        protected String name;
        Integer amountOfSeats;
        Integer wagonID;

        private WagonBuilder() {
        }

        public static WagonBuilder aWagon() {
            return new WagonBuilder();
        }

        public WagonBuilder setAmountOfSeats(Integer amountOfSeats) {
            this.amountOfSeats = amountOfSeats;
            return this;
        }

        public WagonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public WagonBuilder setWagonID(Integer wagonID) {
            this.wagonID = wagonID;
            return this;
        }

        public Wagon build() {
            Wagon wagon = new Wagon();
            wagon.setAmountOfSeats(amountOfSeats);
            wagon.setName(name);
            wagon.setWagonID(wagonID);
            return wagon;
        }
    }
}
