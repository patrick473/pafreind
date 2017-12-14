package Domain;

public class Locomotive extends Component {

        Integer locomotiveID;

    public Locomotive(String name) {
        super(name);
    }

    public Locomotive(String name, Integer locomotiveID) {
        super(name);
        this.locomotiveID = locomotiveID;
    }

    public Integer getLocomotiveID() {
        return locomotiveID;
    }

    public void setLocomotiveID(Integer locomotiveID) {
        this.locomotiveID = locomotiveID;
    }

    @Override
    public String toString() {
        return "Locomotive{" +
                "locomotiveID=" + locomotiveID +
                ", name='" + name + '\'' +
                '}';
    }
}
