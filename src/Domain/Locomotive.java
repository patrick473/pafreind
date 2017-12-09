package Domain;

public class Locomotive extends Component {

        Integer locomotiveID;

    public Locomotive(String name, Integer position, Integer locomotiveID) {
        super(name, position);
        this.locomotiveID = locomotiveID;
    }

    public Integer getLocomotiveID() {
        return locomotiveID;
    }

    public void setLocomotiveID(Integer locomotiveID) {
        this.locomotiveID = locomotiveID;
    }

}
